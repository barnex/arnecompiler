/**
 * The Root block is a special Block that contains all the code.
 * Its context is static by default, but can be overridden in
 * e.g. function definitions.
 * @author arne
 *
 */
public class Root extends Block{

	Root(){
		context = Variable.STATIC;
	}
}