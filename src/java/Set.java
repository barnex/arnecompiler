
public class Set extends Node {

	public Set(Variable address, Node value){
		set(0, address);
		set(1, value);
	}
	
	public Set(){
		
	}
	
	void compile_it_impl() {
		
	}

	void compile_tree_asm() {
    // value to be set:
		child(1).compile_tree_asm();
		asm(text, push, a);

    // address to be written to:
		((LValue)child(0)).compile_address_asm();
		asm(text, pop, b); // value now in b
		asm(text, mov, b, "(" + a + ")");
	}

}
