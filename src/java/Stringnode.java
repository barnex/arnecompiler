import java.io.PrintStream;

/**
 * String literal in the source code, like 1 or x
 * @author arne
 */
public class Stringnode extends Node{
	
    //leaf node has data
    String data = null;
	  
	Stringnode(String data){
		this.data = data;
	}
	
	void printNode(PrintStream out){
	    super.printNode(out);
    	if(data != null)
    		out.print(":" + data);
	}
	
	String string(){
		return data;
	}

	void compile_tree_asm() {
		// Intentionally empty
	}	
	
	void compile_it_impl(){
		// Intentionally empty
	}
}