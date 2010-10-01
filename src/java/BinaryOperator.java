/**
 * A superclass for binary operators like +, *, -, ... 
 * Note: iteger division (Div.java) is a bit more difficult in assembly,
 * it is not derived from this class.
 * @author arne
 *
 */
public abstract class BinaryOperator extends Node {

	/**
	 * Must return the opcode of the operation, like "addl" or "imul"
	 */
	abstract String opcode();
	
	/**
	 * Uses opcode() to generate the assembly code.
	 */
	public void compile_tree_asm(){
		child(1).compile_tree_asm();
		asm(text, push, a);			// important, cannot 'mov a, b', since b may be destructed later
		child(0).compile_tree_asm();
		asm(text, pop, b);
		asm(text, opcode(), b, a);
	}
	
	public void compile_it_impl(){
		// Intentionally empty
	}
}
