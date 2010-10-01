/**
 * Primitive Type declaration
 * For the bootstrapping compiler, they are all 32 bits.
 * @author arne
 */
public class PrimitiveDefinition extends TypeDefinition{
	
	public PrimitiveDefinition(){
		storagewords = 1;
	}
	
	Node child_name(){
		return child(0);
	}
	
	void compile_it_impl(){
		// (1) fetch the name of the declared class
		if(name == null){
			this.name = ((Unresolved)child_name()).name();
			 set(0, new Identifier(name));
			 changemade = true;
		}
		
		// (2) create a prototype of te symbol declared here, will be copied each time it's used
		if(symbol == null){
			Type clazz = new Type();
			this.symbol = clazz;
			clazz.parent = null;
			clazz.declaration = this;
			parent.declarations().add(this);
			changemade = true;
		}
		
//		if(storagebits == -1){
//			storagebits = ((Word)child_storagebits()).intValue();
//			changemade = true;
//		}
	}

	void compile_tree_asm(){
		// nothing to do
	}
}