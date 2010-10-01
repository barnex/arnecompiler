/**
 * Gets an element from an array.
 * Returns the address of the element, so it can be get or set.
 */
public class ArrayGet extends Node implements LValue{

	void compile_it_impl(){
/*
		if(struct == null && child_struct().fullyCompiled_tree()){
			struct = (StructDefinition)(((Variable)child_struct()).declaration.declared_type);
			changemade = true;
		}
		if(member_name == null){
			member_name = ((Unresolved)child_name()).name();
			child_name().replaceme(new Identifier(member_name));
			changemade = true;
		}
		if(offset == -1 && struct != null && struct.member_offsets != null){	// a bit ad-hoc (not using fullyCompiled) but safe
			if(struct.member_offsets.get(member_name) == null)
				throw new Error("Member not found in struct " + struct.name + ": " + member_name);
			Integer offset_ = struct.member_offsets.get(member_name);
			offset = offset_;
			changemade = true;
		}*/
	}	
	
	Node child_array(){
		return child(0);
	}
	
	Node child_element(){
		return child(1);
	}
	
	/**
	 * Moves the value of the variable to %eax
	 */
	void compile_tree_asm(){
		compile_address_asm();
		asm(text, mov, "(" + a + ")", a);
	}

	public void compile_address_asm() {

    /*
     * Bound-checked version
     */
//    // base addres of the array:
//		((LValue)child_array()).compile_address_asm();
//    asm(text, push, a); // push a aside
//
//    // index of the element:
//		child_element().compile_tree_asm(); // a now holds the element index
//    asm(text, push, a);
//    asm(text, call, "_array_checked_access");

    /*
     * unchecked version
     */
    // base addres of the array:
    // warning: this is stored as the value of the child
    // TODO: check in struct !

    // base address
		child_array().compile_tree_asm();
    asm(text, push, a); // push a aside

    // index of the element:
		child_element().compile_tree_asm(); // a now holds the element index
    
     // lower bound check:
     asm(text, cmp, "$0", a); 
     asm(text, jl, "_error_array_bounds");

     // upper bound check
     asm(text, pop, b);                  // b is now the base address
     asm(text, cmp, "(" + b + ")", a);
     asm(text, jge, "_error_array_bounds");

     asm(text, inc, a);                  // we add 1 because the first element is the size
     asm(text, mul, immediate("4"), a);  // and multiply by four, for 32-bits element size

		
		asm(text, add, b, a);               // a is now the element address
	}	
}
