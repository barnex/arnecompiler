
public interface LValue {

	/**
	 * Returns the asm code that puts the addres in %eax
	 */
	void compile_address_asm();
}
