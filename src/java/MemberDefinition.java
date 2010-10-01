/**
 * Members of a struct are usually written with the syntax of ordinary
 * variable definitions:
 * 
 * struct vector{
 * 	int x;
 *  ^^^^^^
 * }
 * 
 * but have a quite different meaning than, e.g., variable definitions inside
 * a function body, etc. This difference is made by explicit by defining members
 * with a MemberDefintion node. A user-friendly compiler would automatically
 * convert VariableDefinitions inside a StructDeclaration to MemberDeclarations.
 * 
 */
public class MemberDefinition extends VariableDefinition{

	// inherited: 
	//TypeDefinition declared_type;
	//int offset = 999; // not used
	// inherited:
	// name
	
	boolean fullyCompiled_node(){
		return declared_type != null && name != null;
	}
	
	void compile_it_impl(){
		
		// (0) Fetch the variable
		if(declared_type == null && !(child(0) instanceof Unresolved)){
			declared_type = ((Type) child(0)).declaration;
			changemade = true;
		}
		
		// (1) fetch the name of the declared variable
		if(name == null){
			 this.name = ((Unresolved)child(1)).name();
			 set(1, new Identifier(name));
			 changemade = true;
		}
	}
	
	
  
	void compile_tree_asm(){
		//intentionally empty
	}
}
