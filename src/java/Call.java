public class Call extends Node{
	
	Node child_function(){
		return child(0);
	}
	
	Block child_arguments(){
		return (Block)child(1);
	}
	
	StructDefinition returntype(){
		return null;
//		if(child_function() instanceof Function)
//			return ((Function)child_function()).declaration.returntype;
//		else
//			return null; // temp hack for printing of returntype.
	}
	
	void typecheck_node(){
		//todo: implement
//		Node argDeclarations = child_function().declaration.child_declarations();
//		if(argDeclarations.n_children() != child_arguments().n_children())
//			throw new Error("call " + child_function().declaration.name +  ": wrong number of arguments");
//			
//		for(int i=0; i<n_children(); i++){
//			ClassDefinition argType = ((VariableDefinition)(argDeclarations.child(i))).returntype;
//			ClassDefinition childType = child(i).returntype();
//			if(childType != null && argType != null && childType != argType)
//				throw new Error("function " + child_function().declaration.name + ": argument type mismatch: expected " 
//						+ argType.name + " but found " + childType.name);
//		}
	}
	
	
	void compile_tree_asm(){
		/*
		 * Calculate the arguments and push them on the stack in reverse order.
		 * This is the C calling convention.
		 */
		Block args = child_arguments();
		for(int n = args.n_children()-1; n >= 0; n--){
			args.child(n).compile_tree_asm();
			asm(text, push, a);	// the argument returns it value in register %eax, so we push this on the stack
		}
		
		/*
		 * Call the function definition, function pointer returned in %eax 
		 */
		child_function().compile_tree_asm();
		
		/*
		 * And finally call the function pointer.
		 */
		asm(text, call, "*" + a); // '*' required by the assembler, no special meaning
		
		// After calling, we remove the arguments from the stack by moving the stack pointer back up.
		asm(text, add, immediate((WORDSIZE * args.children.size()) + ""), sp, "pop " + n_children() + " arguments back off the stack");
	}
	
	public void compile_it_impl(){
		// Intentionally empty
	}
}