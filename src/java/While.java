/**
 * While-loop
 * @author arne
 */
public class While extends Loop {

	void compile_tree_asm(){
	  asmlabel(text, label_("condition") + ":");
	  child(0).compile_tree_asm();
	  asm(text, cmp, immediate("0"), a);
	  asm(text, je, label_("exit"));
	  child(1).compile_tree_asm();
	  asm(text, jmp, label_("condition"));
	  asmlabel(text, label_("exit") + ":");
	}
}
