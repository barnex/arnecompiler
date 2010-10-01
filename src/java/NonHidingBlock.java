import java.util.Vector;

/**
 * In contrast to a "normal" block of code, a NonHidingBlock does not introduce
 * a scope which hides declarations from the outer world.
 * @author arne
 *
 */
public class NonHidingBlock extends Block{

	Vector<Definition> declarations(){
		// this causes declarations inside the block to added to the parent
		// so that they are not hidden from the outside world.
		return parent.declarations();
	}
}