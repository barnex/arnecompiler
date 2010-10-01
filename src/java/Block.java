/**
 * A Block of code, typically written between curly braces {}.
 * A block can contain many children, each of which are just compiled sequentially.
 * @author arne
 *
 */
public class Block extends Node{

	/**
	 * Just generates assembly code for each of the children.
	 */
	void compile_tree_asm(){
		for(Node n: children)
			n.compile_tree_asm();
	}
	
	/* This has to check ONLY if this node is fully compiled, the children are automatically checked
	 * by fullyCompiled(). Since there is nothing to compile about a block, we can just return true
	 */
	boolean fullyCompiled_node(){
		return true;
	}
	
	public void compile_it_impl(){
		// Intentionally empty
	}

}