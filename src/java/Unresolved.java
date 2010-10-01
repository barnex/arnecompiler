/**
 * Represents an unresolved symbol in the source code. compileit_impl() tries to
 * resolve it by looking up the symbol name in the scope's declaration tables.
 * @author arne
 */
public class Unresolved extends Stringnode{
  
	/**
	 * Unresolved trivially means that it has not yet been compiled.
	 */
	boolean fullyCompiled_node(){
		return false;
	}
	
	/*	Currently only checks the name, but should also test if the arguments match
	 *	(to allow overloading).
	 */ 	
	void compile_it_impl(){
		
		// try to resolve myself
		Node current_scope = parent;
		Definition answer = null;
		int matches = 0;
		
		while(current_scope != null && matches != 1){
			
			for(Definition declaration: current_scope.declarations()){		
				if(declaration.name.equals(this.name())){
					matches++;
					answer = declaration;
				}
			}
			if(matches > 1){
				throw new Error("Symbol is ambiguous: " + this.name());
			}
			else{
				current_scope = current_scope.parent;
			}
		} 
		
		if(matches == 1){
			Node resolved = answer.symbol.copy_node();
			resolved.set_children(this);
			replaceme(resolved);
			resolved.changemade = true; // I am insignificant now, replaced
		}
		
	}
	
	/*	important:
	 *	do not recursively compile the children
	 *	the Unresolved node will replace itself at give its children to someone else.
	 *	they will be compiled automatically later on.
	 *
	 *	note:
	 *	this may become a bit slow, perhaps asking the call to compileit is a good idea...
	 */
	void compiletree_it(){
		compile_it_node();
	}
	
	public Unresolved(String data){
		super(data);
	}
	
	String name(){
		return data;
	}
}