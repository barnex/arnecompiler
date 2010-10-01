/**
 * Example:
 * 
 * int a = 0;
 * ^^^
 * resolves to an instance of Type.
 * 
 * int b = 0;
 * ^^^
 * resolves to an OTHER INSTANCE of Type
 * 
 * but both examples' "declaration" fields point to the same TypeDefinition:
 * declareclass(int), which is unique.
 * ^^^^^^^^^^^^^^^^^
 * 
 * Struct, Array, and Primitive types all use this wrapper class for their definition.
 * 
 * @author arne
 */

public class Type extends Symbol{

	TypeDefinition declaration;
	
	void compile_tree_asm(){
		//	Intentionally empty
	}
	
	public void compile_it_impl(){
		// Intentionally empty
	}
}