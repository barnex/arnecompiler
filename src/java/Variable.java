import java.io.PrintStream;

/**
 * A variable. Similar to Function, this only stores an address of a variable.
 * The code for allocating the variable and initializing it is in Declarevarible.
 * @author arne
 */
public class Variable extends Feature implements LValue{

	Type clazz;
	
	/**
	 * All copies of the same variable share a link to their unique declaration.
	 * There, the shared data is stored.
	 */
	VariableDefinition declaration;
	
	
	/**
	 * returns an asm string for the address of this variable.
	 * static: returns the label of the static address
	 * local/argument: returns a base pointer addressing 
	 */
	String address(){
		if(declaration.allocation_type == 0){
			throw new Error("A developer forgot to set my declaration.type. Variable.");
		}
		else if(declaration.allocation_type == ARGUMENT){
			return (WORDSIZE * ( 2 + declaration.offset) ) + "(" + bp + ")"; // offset + 2: skip the return addres and the previous base pointer
		}
		else if(declaration.allocation_type == LOCAL){
			return (-WORDSIZE * (1 + declaration.offset)) + "(" + bp + ")"; // offset + 1: skip the return address
		}
		else if(declaration.allocation_type == STATIC){
			return declaration.label();//, a, "fetch static variable " + name().string());
		}
		else throw new Error();
	}
	
	public void compile_address_asm(){
		asm(text, lea, address(), a);
	}
	
	/**
	 * Moves the value of the variable to %eax
	 */
	void compile_tree_asm(){
		asm(text, mov, address(), a, "fetch " + typenames[declaration.allocation_type] + " " + declaration.name);
	}
	
	/**
	 * possible 'types' of variables
	 */
	public static final int ARGUMENT = 1, LOCAL = 2, STATIC = 3, MEMBER = 4;
	public static final String[] typenames = new String[]{"?", "argument", "local", "static", "member"};
	

	void printNode(PrintStream out){
		super.printNode(out);
		//out.print(":" + declaration.name);
	}
	
	public void compile_it_impl(){
		if(n_children() == 1){
			/*
			 * Beware, this could be simplified as:
			 * replaceme(new Set(this, child(0));
			 * HOWEVER: the argument of replaceme should not contain a reference to me
			 * because 'me' will be killed (parent removed etc) after beining replaced.
			 */
			Set set = new Set();
			replaceme(set);
			set.add(this);
			set.add(child(0));
			this.children.clear();
		}
	}
	
//	public Variable(Variable other){
//		
//	}
	
}