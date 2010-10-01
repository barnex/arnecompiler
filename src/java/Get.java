
public class Get extends Node {

	public Get(Variable v){
		set(0, v);
	}
	
	public Get(){
		
	}
	
	void compile_it_impl() {
		// store variable and replace by identifier
	}

	void compile_tree_asm() {
		child(0).compile_tree_asm();
		asm(text, mov, "(" + a + ")", a);
	}

}
