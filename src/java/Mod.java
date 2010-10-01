/**
 * Modulo
 * @author arne
 */
public class Mod extends Math {

	void compile_tree_asm(){
		child(1).compile_tree_asm();
		asm(text, push, a);
		child(0).compile_tree_asm();
		asm(text, pop, b);
		asm(text, cdq);
		asm(text, div, b);
		asm(text, mov, d, a);
	}
}
