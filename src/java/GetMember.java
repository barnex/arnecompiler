

/**
 * Gets a member from a struct, equivalent to struct.member in C.
 * Returns the address of the member, so it can be get or set.
 */
public class GetMember extends Node implements LValue{

	StructDefinition struct;
	int offset = -1; // -1 means unset
	String member_name = null;
	
	void compile_it_impl(){
		//System.out.println("child0:" + child_name());
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
		}
	}	
	
	Node child_struct(){
		return child(0);
	}
	
	Node child_name(){
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
    // Bugfix: took address of child_struct() instead of contents
		child_struct().compile_tree_asm();
		asm(text, lea, offset + "(" + a + ")", a);
	}	
}
