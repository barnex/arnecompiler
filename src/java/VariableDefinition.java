import java.io.PrintStream;

/**
 * Declaration of a typed variable.
 * child 0: variable type
 * child 1: variable name
 * child 2: (optional) initializer code.
 * 
 * The corresponding Variable is stored in 'symbol'.
 * A copy can occur as the cild of many nodes.
 * 
 * Should still check that a variable is not used before its declaration.
 */
public class VariableDefinition extends Definition{

	/**
	 * Static, local, argument...
	 */ 
	int allocation_type;
	
	// TODO: group functionality in DeclareFeature.
	// the type how this variable was declared
	/**
	 * 
	 */
	TypeDefinition declared_type;
	
	/**
	 * If a variable is local or an argument, its offset from the base pointer is encoded in offset.
	 * Offset just stores the number of the variable (0 is the first, 1 is the second, ...).
	 * When compiled to asm, this count is multiplied by the WORDSIZE and words are added to skip
	 * the return address (an 'invisible' parameter on the stack). See address().
	 */
	int offset = 999; // bogus value for debugging
	
	void compile_it_impl(){
		
//		 Fetch the returntype
		if(declared_type == null && !(child(0) instanceof Unresolved)){
			declared_type = ((Type) child(0)).declaration;
			changemade = true;
      assert declared_type != null;
		}
		
		// (1) fetch the name of the declared variable
		if(name == null){
			 this.name = ((Unresolved)child(1)).name();
			 set(1, new Identifier(name));
			 changemade = true;
       assert this.name != null;
		}
		
		// (2) create a prototype of te symbol declared here, will be copied each time it's used
		if(symbol == null){
			Variable var = new Variable();
			this.symbol = var;
			var.parent = null;
			var.declaration = this;
			parent.declarations().add(this);
			changemade = true;
      assert this.symbol != null;
		}
		
		// (3) set the context of the variable
		/* TODO: danger, danger! 
		 * Not an imminent problem for the moment, but this kind of assumes the parents
		 * are already compiled so the context can be deduced. If Node.compile_tree_it
		 * first compiles the children, and than the node itself (instead of the other way
		 * around, like it is now the case), then we crash here.
		 */
		if(symbol != null && allocation_type == 0 && contextNode() != null){
			allocation_type = contextNode().context;
      
			if(allocation_type == Variable.LOCAL){
				/* if I'm declared in a local context:
				 * find my FunctionDefinition: ((FunctionDefinition)contextNode().parent)
				 * increments its number of local variables and set my offset accordingly
				 */
				offset = ((FunctionDefinition)contextNode().parent).n_local_variables;
				((FunctionDefinition)contextNode().parent).n_local_variables++;
			}
			changemade = true;
      assert allocation_type != 0;
		}
	}
	
	
  
  void compile_tree_asm(){
	 
	  /* For static variables, we need to statically allocate space in the
	   * .data section.
	   */
	  if(allocation_type == Variable.STATIC){
		  asm(data, ".lcomm ",  label() + ", " + WORDSIZE);
	  }
	  
	  // User-defined initialization:
	  if(initializer() != null){
		  initializer().compile_tree_asm();
		  asm(text, mov, a, ((Variable)symbol).address(), comment("initialize " + name)); 	  
	  }
	  else{
		  // Default initialization to 0
		  asm(text, mov, immediate("0"), ((Variable)symbol).address(), comment("autoinitialize " + name + " as 0"));
	  }
  }
  
  	/**
	 * If a variable is static, this returns a unique label for its static address in the .data section.
	 */
	String label(){
		if(allocation_type != Variable.STATIC)
			throw new Error("Bug: requesting label for non-static variable");
		else
			return "_static_variable_"  + id + "_" + name;
	}
	
  Node initializer(){
	  if(n_children() > 2)
		  return child(2);
	  else
		  return null;
  }
  
  void printNode(PrintStream out){
	  super.printNode(out);
	  out.print("[type:" + Variable.typenames[allocation_type] + ", offset:" + offset + "]");
  }
  
}
