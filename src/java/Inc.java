/**
 * Increments an integer
 * @author arne
 */
public class Inc extends Math{
	
	void compile_tree_asm(){
		((LValue)child(0)).compile_address_asm();
		asm(text, inc, "(" + a + ")");
	}
}
