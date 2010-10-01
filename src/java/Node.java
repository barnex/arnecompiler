import java.util.*;
import java.io.*;

public abstract class Node implements Cloneable{

	///////////////////////////////////////////////////////////////////////// types
	
	// TODO: many different Class Nodes that refer to the same ClassDefinition may occur in the source
	// should be replaced by a ClassDefinition, which is unique
	TypeDefinition returntype(){
		//
		// TODO: throw unimplemented here
		//
		return null;
	}
	
	/**
	 * Iterative type checking
	 */
	final void typecheck_tree(){
		typecheck_node();
		for(Node n: children)
			n.typecheck_tree();
	}
	
	/**
	 * Type checking implementation for only this node.
	 */
	void typecheck_node(){
		// throw unimplemented here?
	}
	
	/**
	 * Makes sure no unresolved symbols managed to remain in the tree. (iterative)
	 */
	void checkUnresolved_tree(){
		if(this instanceof Unresolved)
			throw new Error("Unresolved symbol: " + ((Unresolved)this).name());
		else{
			for(Node child: children){
				child.checkUnresolved_tree();
			}
		}
	}
	
	
	////////////////////////////////////////////////////////////////////////// Robust compilation

	/**
	 * If compileit_impl (compile iteration of only this node) made any change
	 * to the tree, it reports back here.
	 */
	protected boolean changemade;

	/**
	 * Checks if every node of the tree is fully compiled
	 */
	boolean fullyCompiled_tree(){
		if(!fullyCompiled_node())
			return false;
		else{
			for(Node c: children){
				if(!c.fullyCompiled_tree())
					return false;
			}
			return true;
		}
	}

	/**
	 * Override this to return whether this single node is fully compiled
	 */
	boolean fullyCompiled_node(){
		throw new Error(getClass().getName() + ".fullycompiled_impl() not fully implemented, ironically...");
	}

	/**
	 * One iteration of trying to compile this node. Ignores it if not (yet) neccessary. Reports
	 * success by setting changemade=true;
	 */
	final void compile_it_node(){
		changemade = false;
		compile_it_impl();
	}

	/**
	 * Override this method to compile this (and only this) node.
	 * If any change is made to the node, changemade=true should be set so
	 * that the compiler knows that iterating further may still be necessary.
	 * 
	 * Compilation of the children is taken care of automatically, so for
	 * many Nodes, nothing has to be done here. Doing nothing is therefore
	 * a sensible default implementation.
	 */
	abstract void compile_it_impl();//{
	//	// intentionally empty
	//}

	/**
	 * Does one iteration over the tree and compiles whatever can be compiled.
	 */
	/*
	 * TODO: check this: first compile_it_node() and than comiple children, or the
	 * other way around, SHOULD not matter. however, doing it the other way around
	 * fails to compile the unittests, something's odd here.
	 * 
	 * perhaps: contexts! when the parent is not yet compiled, a variabledefinition
	 * gets confused about its context, because it does not see, e.g., its function
	 * context yet.
	 */
	void compiletree_it(){
		compile_it_node();
		for(int i=0; i<children.size(); i++){		// important:
			child(i).compiletree_it();				// need to get the child by index each time
			changemade |= child(i).changemade;		// because it could have replaced itself
		}
		//compile_it_node();
	}

	/**
	 * Does many iterations over the tree until nothing can be compiled
	 * further anymore. At that point, the tree is either fully compiled
	 * or contains errors like unresolved symbols (which can be reported).
	 */
	protected void compiletreefully(){
		int iterations = 0;
		do{
			System.err.print('-');
			compiletree_it();
			test_tree();
			iterations++;
		}
		while(changemade);
		System.out.println();
		System.out.println("Compiled in " + iterations + " iterations.");
		
	}

	/**
	 * Internal test of consistency. 
	 */
	void test_tree(){
		test_node();
		for(Node c: children){
			c.test_tree();
		}
	}

	/**
	 * Internal test of consistency. 
	 */
	void test_node(){
		// test parent-child relation
		for(Node c: children)
			if(c.parent != this)
				throw new Error("Inconsistent tree, my child's parent is not me: " + getClass().getName());

	}


	////////////////////////////////////////////////////////////////////////// assembly 

	/**
	 * The number of bytes per machine word for the target platform
	 * 32-bit: WORDSIZE = 4
	 * 64-bit: WORDSIZE = 8
	 */
	static final int WORDSIZE = 4; // 32-bit

	/**
	 * Central definition of opcodes and registers representing one machine word.
	 * Currently implemented as 32-bit codes.
	 * They can be replaced by other opcodes/registers (64-bit, e.g.) in this central place
	 * instead of having to replace strings in many different source files.
	 */
	// opcodes
	static final String mov = "movl", push = "pushl", pop = "popl";
	static final String lea = "leal";
	static final String call = "call", leave = "leave", ret = "ret";
	static final String add = "addl", sub = "subl", mul = "imull", div = "idivl", cdq = "cdq";
	static final String cmp = "cmpl", jmp = "jmp", je = "je", jl = "jl", jg = "jg", jge = "jge";
	static final String inc = "incl", dec = "decl";

	// registers
	static final String a = "%eax", b = "%ebx", c = "%ecx", d = "%edx";
	static final String bp = "%ebp", sp = "%esp";

	/*
	 * Instead of manually appending "#"", :", "$", ... to assembly codes, we define the functions:
	 * label (returns an asm label by appending ":")
	 * immediate (returns an immediate asm value by prepending "$")
	 * comment (returns an asm comment by prepending "#")
	 * If we later want to output to a different assembly format (e.g. Intel instead of the current AT&T format),
	 * we only have to change these functions instead of many many string in many source files.
	 */

	/**
	 * returns an asm label by appending ":"
	 */
	String label_(String label){
		return "." + getClass().getName().toUpperCase() + id + label.toUpperCase();
	}

	/**
	 * returns an asm code representing an immediate value by prepending "$"
	 */
	static String immediate(String data){
		return "$" + data;
	}

	/**
	 * returns an asm comment by prepending "#"
	 */
	static String comment(String comment){
		return "#" + comment;
	}

	/*
	 * Assembly file building.
	 * An assembly program contains different sections:
	 * .data contains initialized data
	 * .bss contains uninitialized data
	 * .text contains the program code, we split it in two parts:
	 * 		'text':  the first part, where the execution starts
	 * 		'text0': the second part, that holds function declarations
	 * 
	 *  For each part of the assembly program, we keep a StringBuffer to which codes can be appended.
	 *  When ready, dump_asm() is used to concatenate these StringBuffers in the right order and output
	 *  the whole to a file.
	 */

	/**
	 * Holds the part of the .text section where the execution starts
	 */
	static StringBuffer text = new StringBuffer();

	/**
	 * Holds the part of the .text section containing function declarations 
	 */
	static StringBuffer text0 = new StringBuffer();

	/**
	 * Holds the .data section
	 * TODO: confusing with Stringnode.data
	 */
	static StringBuffer data = new StringBuffer();

	/*
	 * Instead of manually concatenating asm opcodes and operands into asm lines,
	 * we define functions that do this:
	 * asm(section, opcode)
	 * asm(section, opcode, argument1)
	 * ...
	 * ams(section, opcode, argument1, argument2, comment)
	 * 
	 * These functions add comma's, hashmarks and newlines where neccesary
	 * 'section' is the StringBuffer to which the asm code will be appended.
	 */


	/**
	 * Appends an opcode to the section defined by the bufer
	 */
	static void asm(StringBuffer buffer, String opcode){
		buffer.append('\t');
		buffer.append(opcode);
		buffer.append('\n');
	}

	/**
	 * Appends an opcode with one argument
	 */
	static void asm(StringBuffer buffer, String opcode, String arg){
		buffer.append('\t');
		buffer.append(opcode);
		buffer.append('\t');
		buffer.append(arg);
		buffer.append('\n');
	}

	/**
	 * Appends an opcode with two arguments, automatically separated by a comma
	 */
	static void asm(StringBuffer buffer, String opcode, String arg1, String arg2){
		buffer.append('\t');
		buffer.append(opcode);
		buffer.append('\t');
		buffer.append(arg1);
		buffer.append(", ");
		buffer.append(arg2);
		buffer.append('\n');
	}

	/**
	 * Appends an opcode with two arguments and a comment automatically separated by a hashmark
	 */
	static void asm(StringBuffer buffer, String opcode, String arg1, String arg2, String comment){
		buffer.append('\t');
		buffer.append(opcode);
		buffer.append('\t');
		buffer.append(arg1);
		buffer.append(", ");
		buffer.append(arg2);
		buffer.append(" \t #");
		buffer.append(comment);
		buffer.append('\n');
	}

	static void asmlabel(StringBuffer buffer, String label){
		buffer.append(label);
		buffer.append('\n');
	}
	/**
	 * Initialization of the section buffers
	 */
	static{
		asmlabel(text, ".section .text");
		asmlabel(text, ".globl _start");
		asmlabel(text, "_start:\n");
		asmlabel(data, ".section .data");
	}

	/**s
	 * Concatenates the section buffers in the correct order (data, text, text0, bss) and
	 * outputs them to a printstream (a file, e.g.).
	 */
	static void dump_asm(PrintStream out){
		out.println(data);
		asm(text, "\n");
		asm(text, push, immediate("0"));
		asm(text, call, "exit");
		asm(text, "\n");
		out.print(text);
		out.print(text0);
	}

	///////////////////////////////////////////////////////////////////////////////////////////// compilation

	/*
	 * To compile a tree of Nodes to assembly, first call rootnode.precompile(). 
	 * This does a complete syntax check and transforms the tree (e.g. resolves symbols), but does not output
	 * assembly code yet.
	 * 
	 * After precompile(), call compile() to generate the asm code in the section buffers followed by
	 * dump_asm() to output the buffered asm code to a file.
	 */

	/**
	 * Compiles the tree to assembly code, stored in a buffer.
	 * It can be output later with dump_asm()
	 */
	abstract void compile_tree_asm();//{
//		throw new Error("compile_tree_asm unimplemented in " + getClass().getName());
//	}


	////////////////////////////////////////////////////////////////////////// precompile

	/*
	 * Certain nodes are special because they impose a context on their children.
	 * 
	 * The root block imposes a static context: anything defined therein is static:
	 * variables and functions (a function is statically stored as its address).
	 * 
	 * This static context gets overridden by function bodies (anything defined therein
	 * has a local context) and by function argument lists (anything defined therein
	 * is an argument).
	 * 
	 * The distinction between these contexts is necessary because static/local/argument
	 * variables are stored in different ways: static variables have a fixed address and
	 * are accessed by the cpu in direct mode. locals and arguments are addressed as an
	 * offset from the base pointer (arguments as negative offsets, locals as positive 
	 * offsets) and are accessed by the cpu in base pointer access mode.
	 * 
	 * In project barnex, we do not require the user to specify a context (e.g., there is
	 * no 'static' keyword). The compiler determines the context automatically.
	 */
	int context = 0; // possible values: Variable.STATIC, LOCAL, ARGUMENT. 0 means not set.

	/**
	 * Determines the context in which a node was defined. This is the first one
	 * of its parents that is either the root block (static contex), function
	 * (local context) or list of function arguments (argument context).
	 * 
	 * todo: add class for "member" context.
	 */
	Node contextNode(){
		Node current = this;
		while(current.context == 0){
			current = current.parent;
		}
		return current;
	}


	/**
	 * Optimization loop.
	 * Optimizes the tree and returns true if *some* optimization was made.
	 * First asks all the children to optimize, over and over, until nothing changes anymore.
	 * then, optimizes the root node, once.
	 */
	/*boolean optimize(){
		boolean something_was_optimized = true;

		while(something_was_optimized){
			something_was_optimized = false;
			//first the children
			for(Node child: children){
				something_was_optimized |= child.optimize();
			}
		}
		// then me
		something_was_optimized |= optimize_impl();

		return something_was_optimized;
	}*/

	/**
	 * Optimizes itself (not its children) and returns true if
	 * an optimization could be made. Default: do nothing and
	 * return false.
	 */
	/*public boolean optimize_impl(){
		// do nothing
		return false;
	}*/



///////////////////////////////////////////////////////////////////// scope/declarations

	//scope: list of defined symbols, visible to the children
	private Vector<Definition> declarations = new Vector<Definition>();
	
	Vector<Definition> declarations(){
		return declarations;
	}


///////////////////////////////////////////////////////////////////// construction


	/**
	 * Every node has a unique id against name clashes.
	 */ 
	final int id;

	public Node(){
		this.id = newID();
	}


	/**
	 * For nice, readable numbering we keep a counter for each class.
	 * E.g.: For0, If0, For1, ..
	 */
	private int newID(){
		Int id_ = ids.get(getClass().getName());
		if(id_ == null){
			id_ = new Int();
			ids.put(getClass().getName(), id_);
		}
		else{
			id_.value = id_.value + 1;
		}
		return id_.value;
	}
	static Hashtable<String, Int> ids = new Hashtable<String, Int>();

	/**
	 * @return Returns a copy of this node.
	 * Used for, e.g. functions.
	 */
	Node copy_node(){
		try {
			return (Node)clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			throw new Error("Huh, nodes were supposed to be clonable!");
		}
	}

	//////////////////////////////////////////////////////////// tree manipulation

	Vector<Node> children = new Vector<Node>();
	Node parent = null;

	/**
	 * Replaces this by an other node
	 * 
	 * WARNING: The argument of replaceme should not contain a reference to me (this)
	 * because 'me' will be killed (parent removed etc) after beining replaced.
	 */
	void replaceme(Node other){
		int myindex = 0;
		while(parent.child(myindex) != this)
			myindex++;
		parent.set(myindex, other);
		other.changemade = true;
	}
	
	public void removeme(){
		int myindex = 0;
		while(parent.child(myindex) != this)
			myindex++;
		parent.children.remove(myindex);
		parent = null;
	}

	Vector<Node> listDeepChildren(){
		Vector<Node> list = new Vector<Node>();
		for(Node child: children)
			child.treeToList(list);
		return list;
	}
	
	void treeToList(Vector<Node> list){
		list.add(this);
		for(Node child: children)
			child.treeToList(list);
	}
	
	/**
	 * Adds a child node.
	 */
	public void add(Node child){
		children.add(child);
		child.parent = this;
	}

	public void set(int index, Node child){
		// grow if necessary
		while(index >= children.size())
			children.add(null);

		children.set(index, child);
		child.parent = this;
	}


	/**
	 * Gets a child node.
	 */
	public Node child(int child_index){
		return children.get(child_index);
	}

	/**
	 * Adopts the children from the other node.
	 */
	public void set_children(Node other){
		this.children = other.children;
		for(Node n:children){
			n.parent = this;
		}
		other.children = null;
	}

	/**
	 * Number of children.
	 */
	public int n_children(){
		return children.size();
	}



	private static int indent = 0;
	/**
	 * Prints the entire tree.
	 */
	public final void print(PrintStream out){
		printIndentation(out);
		printNode(out);

		out.print("<" + (returntype() == null? "?": returntype().name ) + ">");

//		if(declarations.size() > 0){
//		printIndentation(out);
//		out.print("declarations[");
//		for(Definition s: declarations){
//		out.print(s.name + ":");
//		s.printNode(out);
//		out.print(", ");
//		}
//		//printIndentation(out);
//		out.println("]");
//		}

		if(n_children() != 0){
			out.println('(');

			indent++;
			for(int i=0; i<n_children(); i++){
				child(i).print(out);
				out.println();
			}
			indent--;

			printIndentation(out);
			out.print(')');
		}
	}

	private void printIndentation(PrintStream out){
		for(int i=0; i<indent; i++)
			out.print("    ");
	}


	/**
	 * Prints this node only, may be overridden.
	 */
	void printNode(PrintStream out){
		out.print(this.getClass().getName() + id);
	}

	public String toString(){
		return this.getClass().getName() + id;
	}
	
	/**
	 * I hate how java uses equals() to compare contents for operations like Vector.remove().
	 * It should compare pointers, not contents. Therefore, I have disabled this method to
	 * prevent accidental use.
	 */
	public boolean equals(Object other){
		throw new Error("You shall not compare Nodes!");
	}
}