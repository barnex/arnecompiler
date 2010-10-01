/**
 * This abstract superclass contains the shared part of the code for all integer comparisons:
 * eq, neq, ...
 * 
 * Similar to BinaryOperator which contains the shared code for +, -, *, ...
 */
abstract class Compare extends Node{
	
	/**
	 * We implement comparisons in assembly by a conditional jump 
	 * (e.g., jump if equal, jump if not equal, ...).
	 * Subclasses only need to define an asm opcode used for jumping.
	 * E.g., to test for equality, the opcode "je" is returned.
	 */
	abstract String jump();
	
	void compile_tree_asm(){
		child(1).compile_tree_asm();
		asm(text, push, a);			// important, cannot 'mov a, b', since b may be destructed later
		child(0).compile_tree_asm();
		asm(text, pop, b);
		asm(text, cmp, b, a);
		// this could probably be done faster with eflags stuff...
		asm(text, jump(), label_("true"));
		asmlabel(text, label_("false") + ":");
		asm(text, mov, immediate("0"), a);
		asm(text, jmp, label_("end"));
		asmlabel(text, label_("true") + ":");
		asm(text, mov, immediate("1"), a);
		asmlabel(text, label_("end") + ":");
	}
	
	void compile_it_impl(){
		
	}
}
