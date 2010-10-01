/**
 * When an unresolved symbol is not meant to be resolved,
 * like the a in declare(a, 0), we simply replace it by
 * an Identifier. This is about the same as Unresolved,
 * but will not try to resolve itself.
 */
public class Identifier extends Stringnode {
	
	/**
	 * Nothing to be done. 
	 */
	public boolean fullyCompiled_node(){
		return true;
	}
	
	Identifier(String data) {
		super(data);
	}

	String name(){
		return data;
	}
}
