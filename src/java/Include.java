import java.io.IOException;

/**
 * Includes the contents of a file. 
 * This is not a "dumb" preprocessor-style directive: the syntax of the 
 * included file on its own must be correct.
 * @author arne
 *
 */
public class Include extends Node{

	protected final String STANDARD_SUFFIX = ".arne";

	void compile_it_impl(){

		try{
			// parse included file, is wrapped in a Root block
			Node root = new Parser(((Stringnode)child(0)).data + STANDARD_SUFFIX).parse();
			
			// replace the wrapper block by a non-hiding wrapper block
			NonHidingBlock block = new NonHidingBlock();
			block.set_children(root);
			
			// replace the include statement with the contents of the file
			replaceme(block);
			block.changemade = true; // this.changemade is futile, this is to be replaced
		}
		catch(IOException e){
			throw new Error(e);
		}
	}


	void compile_tree_asm() {
		// Intentionally empty
	}

}