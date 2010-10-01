/**
 * Anonymous function
 * @author arne
 *
 */
public class LambdaDefinition extends FunctionDefinition{

	// inherited:
	//int n_local_variables = 0;
	//int n_args = 0;
	//boolean compiled_declarations = false;
	//ClassDefinition returntype = null; // TODO: group in FeatureDefinition
	
	public LambdaDefinition(){
		context = Variable.LOCAL;
		name = "lambda"; // placeholder against nullpointerexceptions. todo: remove me
	}
	
	// child indices
	public static final int RETURNTYPE = 0, DECLARATIONS = 1, CODE = 2;
	
	void compile_it_impl(){
		/* DO NOT FORGET THAT EVERY MODIFICATION SHOULD BE REPORTED BACK
		 * BY changemade=true !
		 */
		
		// Fetch the returntype
		if(returntype == null && !(child(RETURNTYPE) instanceof Unresolved)){
			returntype = ((Type) child(RETURNTYPE)).declaration;
			changemade = true;
		}
		
		// All variables declared in this block have to know they are arguments
		if(child(DECLARATIONS).context ==0){
			child(DECLARATIONS).context = Variable.ARGUMENT;
			changemade = true;
		}
		
		// All variables declared in the code body are local
		if(child(CODE).context == 0){ 
			child(CODE).context = Variable.LOCAL;
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
			for(Node child_: child(DECLARATIONS).children){
				VariableDefinition child = (VariableDefinition)child_;
				child.allocation_type = Variable.ARGUMENT;
				child(CODE).declarations().add(child);
				child.offset = count;
				count ++;
			}
			n_args = child(DECLARATIONS).n_children();
			changemade = true;
		}
	}
	
	boolean fullyCompiled_node(){
		// should be it, I guess...
		return symbol != null && compiled_declarations;
	}

	/**
	 * An assembly label for the address of the function
	 */
//	String label(){
//		String label = "_function_" + id;
//		if(name != null)
//			label += "_" + name;
//		return label;
//	}


	void compile_tree_asm(){
		
		//super:
		
		asmlabel(text0, ".type " +  label() + " @function");
		asmlabel(text0, label() + ":");
		asm(text0, push, bp);//		 # save stack frame
		asm(text0, mov, sp, bp); 
		asm(text0, sub, immediate("" + (WORDSIZE * n_local_variables)), sp, "reserve " + n_local_variables + " local variables");
		
		StringBuffer old_text = text;	// dirty, but hey...
		text = text0;					// children will compile to text0 section without knowing.
		for(Node c:child(CODE).children){
			c.compile_tree_asm();
		}
		text = old_text;

		asm(text0, add, immediate("" + (WORDSIZE * n_local_variables)), sp, "free " + n_local_variables + " local variables");
		asm(text0, leave);
		asm(text0, ret);
		
		// me:
		
		// note: we need to "mov $label %eax", immediate addressing mode.
		asm(text, mov, immediate("(" + label() + ")"), a);	// lambda constructs return their address so they can be called/stored.
	}
}