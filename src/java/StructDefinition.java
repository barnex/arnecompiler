/**
 * Declaration of a struct.
 * Similar to the declaration of a primitive, but with members:
 * 
 * struct(vector, block(
 * 	member(int x)
 * 	member(int y)
 * ))
 * 
 * After this definition: 'vector' will resolve to an instance of Type, whose
 * 'declaration' field points to the definition.
 * 
 * @author arne
 */

import java.util.Hashtable;

public class StructDefinition extends TypeDefinition{

	Hashtable<String, Integer> member_offsets = null;
	
	Node child_name(){
		return child(0);
	}
	
	Node child_members(){
		return child(1);
	}
	
	void compile_it_impl(){
		//System.out.println(children.size());
		// (1) fetch the name of the declared class
		if(name == null){
			this.name = ((Unresolved)child_name()).name();
			 set(0, new Identifier(name));
			 changemade = true;
		}
		
		// (2) create a prototype of the symbol declared here, will be copied each time it's used
		if(symbol == null){
			Type clazz = new Type();
			this.symbol = clazz;
			clazz.parent = null;
			clazz.declaration = this;
			parent.declarations().add(this);
			changemade = true;
		}
		
		if(member_offsets == null && child_members().fullyCompiled_tree()){
			/* add member variables to the members list,
			 * calculate their offsets and the total
			 * storage size for this struct.
			 */
			storagewords = 0;
			
			member_offsets = new Hashtable<String, Integer>();
			Block members = (Block)(child_members());
			for(Node n: members.children){
				MemberDefinition child = (MemberDefinition)n;
				member_offsets.put(child.name, new Integer(storagewords));
				storagewords += child.declared_type.storagewords;
			}
			changemade = true;
		}
	}
	
	boolean fullyCompiled_node(){
		return member_offsets != null && name != null;
	}

	void compile_tree_asm(){
		// nothing to do
	}
}