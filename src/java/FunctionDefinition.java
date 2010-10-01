public class FunctionDefinition extends Definition{

	int n_local_variables = 0;
	int n_args = 0;
	
	boolean compiled_declarations = false;
	TypeDefinition returntype = null; // TODO: group in FeatureDefinition
	
	public FunctionDefinition(){
		context = Variable.LOCAL;
	}
	
	// child indices
	//public static final int RETURNTYPE = 0, NAME = 1, DECLARATIONS = 2, CODE = 3;
	
	Node child_returntype(){
		return child(0);
	}
	
	Node child_name(){
		return child(1);
	}
	
	Node child_declarations(){
		return child(2);
	}
	
	Node child_code(){
		return child(3);
	}
	
	void compile_it_impl(){
		/* DO NOT FORGET THAT EVERY MODIFICATION SHOULD BE REPORTED BACK
		 * BY changemade=true !
		 */
		
		// Fetch the returntype
		if(returntype == null && !(child_returntype() instanceof Unresolved)){
			returntype = ((Type) child_returntype()).declaration;
			changemade = true;
		}
		
		// Fetch the name of the declared function
		if(name == null){
			this.name = ((Unresolved)child_name()).name();
			//set(NAME, new Identifier(name));		// replace Unresolved by a String that does not need to be resolved;
			child_name().replaceme(new Identifier(name));
			changemade = true;
		}
		
		// All variables declared in this block have to know they are arguments
		if(child_declarations().context ==0){
			child_declarations().context = Variable.ARGUMENT;
			changemade = true;
		}
		
		// All variables declared in the code body are local
		if(child_code().context == 0){ 
			child_code().context = Variable.LOCAL;
			changemade = true;
		}

		// Create a prototype of te symbol declared here, will be copied each time it's used
		if(symbol == null){
			Function f = new Function();
			this.symbol = f;
			f.parent = null;
			f.declaration = this;
			parent.declarations().add(this);
			changemade = true;
		}

		// Manage the arguments (count them, make their declarations available in the code body)
		if(symbol != null && !compiled_declarations){
			compiled_declarations = true; // make sure we don't do this twice
			
			// (1) process declarations in the declarations block, make them available in the code block
			// also calculate their offsets
			int count = 0;
			for(Node child_: child_declarations().children){
				VariableDefinition child = (VariableDefinition)child_;
				child.allocation_type = Variable.ARGUMENT;
				child_code().declarations().add(child);
				child.offset = count;
				count ++;
			}
			n_args = child_declarations().n_children();
			changemade = true;
		}
	}

	/**
	 * An assembly label for the address of the function
	 */
	String label(){
		String label = "_function_" + id;
		if(name != null)
			label += "_" + name;
		return label;
	}


	void compile_tree_asm(){

		asmlabel(text0, ".type " +  label() + " @function");
		asmlabel(text0, label() + ":");
		asm(text0, push, bp);//		 # save stack frame
		asm(text0, mov, sp, bp); 
		asm(text0, sub, immediate("" + (WORDSIZE * n_local_variables)), sp, "reserve " + n_local_variables + " local variables");
		//asm_indent();

		StringBuffer old_text = text;	// dirty, but hey...
		text = text0;					// children will compile to text0 section without knowing.
		for(Node c:child_code().children){
			c.compile_tree_asm();
		}
		text = old_text;

		asm(text0, add, immediate("" + (WORDSIZE * n_local_variables)), sp, "free " + n_local_variables + " local variables");
		asm(text0, leave);
		asm(text0, ret);
	}

}