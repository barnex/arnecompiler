/**
 * Temporary.
 * TODO: each class (once classes have been implemented)
 * should have an initializer, making NewArray, NewStruct, ...
 * redundant.
 *
 * @author arne
 */
public class NewArray extends Node {

	TypeDefinition type;
	
	public void compile_it_impl(){
		if(type == null && child_class() instanceof Type){
			type = ((Type)child_class()).declaration;
			changemade = true;
		}
	}

	
	void compile_tree_asm() {
    child_size().compile_tree_asm();
    asm(text, push, a);// a copy of the size

    asm(text, push, a);
    asm(text, call, "_check_array_size");
    asm(text, add, immediate("4"), sp, comment("pop argument back off"));

    asm(text, inc, a);
    asm(text, mul, immediate("4"), a);
	asm(text, push, a);
	asm(text, call, "malloc"); // TODO: calloc: auto-initialize to 0
    asm(text, add, immediate("4"), sp, comment("pop argument back off"));

    // store size as first element
    asm(text, pop, b); // retrieve the copy of the size
    asm(text, mov, b, "(" + a + ")");

	}
	
	Node child_class(){
		return child(0);
	}

  Node child_size(){
    return child(1);
  }
	
}
