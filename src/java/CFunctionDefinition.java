
public class CFunctionDefinition extends FunctionDefinition{

	void compile_it_impl(){
		/* DO NOT FORGET THAT EVERY MODIFICATION SHOULD BE REPORTED BACK
		 * BY changemade=true !
		 */
		
		// Fetch the returntype
		if(returntype == null && !(child_returntype() instanceof Unresolved)){
			returntype = ((Type) child_returntype()).declaration;
			changemade = true;
		}
		
		// all variables declared in this block have to know they are arguments
		if(child_declarations().context ==0){
			child_declarations().context = Variable.ARGUMENT;
			changemade = true;
		}
		
		// (1) fetch the name of the declared function
		if(name == null){
			this.name = ((Unresolved)child_name()).name();
			//set(NAME, new Identifier(name));		// replace Unresolved by a String that does not need to be resolved;
			child_name().replaceme(new Identifier(name));
			changemade = true;
		}

		// (2) create a prototype of te symbol declared here, will be copied each time it's used
		if(symbol == null){
			CFunction f = new CFunction();
			this.symbol = f;
			f.parent = null;
			f.declaration = this;
			parent.declarations().add(this);
			changemade = true;
		}

		// (3) manage the arguments (count them, make their declarations available in the code body)
		// quite redundant actually
		if(symbol != null && !compiled_declarations){
			compiled_declarations = true; // make sure we don't do this twice
				
			// (1) process declarations in the declarations block
			// also calculate their offsets
			int count = 0;
			for(Node child_: ((Block)child_declarations()).children){
				VariableDefinition child = (VariableDefinition)child_;
				child.allocation_type = Variable.ARGUMENT;
				child.offset = count;
				count ++;
			}
			n_args = ((Block)child_declarations()).n_children();
			changemade = true;
		}
	}


	String label(){
		return name;
	}


	void compile_tree_asm(){
		// nothing to do
	}


//	Block declarations(){
//		return (Block)child(1);
//	}
	
}