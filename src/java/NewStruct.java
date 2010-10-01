
public class NewStruct extends Node {

	TypeDefinition type;
	
	public void compile_it_impl(){
		if(type == null && child_class() instanceof Type){
			type = ((Type)child_class()).declaration;
			changemade = true;
		}
	}

	
	void compile_tree_asm() {
		asm(text, push, immediate((4 * type.storagewords) + ""));
		// TODO: calloc: Auto-initialize all members to 0
		asm(text, call, "malloc");
		// IMPORTANT:
		asm(text, add, immediate("4"), sp, comment("pop argument back off"));
		
	}
	
	Node child_class(){
		return child(0);
	}
	
}
