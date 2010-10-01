/**
 * Decrements an integer.
 */
public class Dec extends Math{
	
	void compile_tree_asm(){
		((LValue)child(0)).compile_address_asm();
		asm(text, dec, "(" + a + ")");
	}

}
