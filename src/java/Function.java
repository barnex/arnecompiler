/**
 * With "Function" we mean a function call with arguments.
 * The corresponding function code body is stored in the belonging "FunctionDefinition."
 */

public class Function extends Feature{
  
	/**
	 * Each function keeps a reference to the place where it was declared.
	 * This allows to retrieve the code body, parameter list, ...
	 */
	FunctionDefinition declaration;
	
	TypeDefinition returntype(){
		return declaration.returntype;
	}
	
	void typecheck_node(){
		Node argDeclarations = declaration.child_declarations();
		if(argDeclarations.n_children() != n_children())
			throw new Error("function " + declaration.name +  ": wrong number of arguments");
			
		for(int i=0; i<n_children(); i++){
			TypeDefinition argType = ((VariableDefinition)(argDeclarations.child(i))).declared_type;
			TypeDefinition childType = child(i).returntype();
			if(childType != null && argType != null && childType != argType)
				throw new Error("function " + declaration.name + ": argument type mismatch: expected " 
						+ argType.name + " but found " + childType.name);
		}
	}
	
	/** this assumes the function gets called: f(args)
	 * clashes with functions as variables: function f = g;
	 */ 
	void compile_tree_asm(){
		/*
		 * Calculate the arguments and push them on the stack in reverse order.
		 * This is the C calling convention.
		 */
		for(int n = n_children()-1; n >= 0; n--){
			child(n).compile_tree_asm();
			asm(text, push, a);	// the argument returns it value in register %eax, so we push this on the stack
		}
		asm(text, call, declaration.label());
		// After calling, we remove the arguments from the stack by moving the stack pointer back up.
		asm(text, add, immediate((WORDSIZE * children.size()) + ""), sp, "pop " + n_children() + " arguments back off the stack");
	}
	
	public void compile_it_impl(){
		// Intentionally empty
	}
}