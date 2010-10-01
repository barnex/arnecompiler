/**
 * One machine word of literal data.
 * 
 * Assembles to movl $data, %eax because "everything is a function" 
 * and everything uses the C calling convention: return your value in %eax
 * 
 * @author arne
 *
 */
public class Word extends Stringnode {

	/**
	 * Nothing to be done. 
	 */
	public boolean fullyCompiled_node(){
		return true;
	}
	
	void compile_tree_asm(){
		asm(text, mov, immediate(data()), a);
	}
	
	public Word(String data){
		super(data);
	}
		
	int intValue(){
		return Integer.parseInt(data);
	}
	
	String data(){
		return data;
	}
}
