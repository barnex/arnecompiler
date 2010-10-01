/**
 * If-(Else)-statement
 * @author arne
 */
public class If extends Loop {

	void compile_tree_asm(){
		asmlabel(text, label_("condition") + ":");
		child(0).compile_tree_asm();
		asm(text, cmp, immediate("0"), a);

		if(n_children() == 2){
			asm(text, je, label_("end"));
			//if-body
			child(1).compile_tree_asm();
		}
		else{
			asm(text ,je, label_("else"));
			//if-body
			child(1).compile_tree_asm();
			asm(text, jmp, label_("end"));
			asmlabel(text, label_("else") + ":");
			// else-body
			child(2).compile_tree_asm();
		}
		asmlabel(text, label_("end") + ":");
	}
}
