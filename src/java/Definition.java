/**
 * Superclass for all declarations.
 */
public abstract class Definition extends Statement{

	// Name (identifier) of the symbol that is declared
	String name;
	
	// Prototype of the declared symbol (e.g., a function). Each time the symbol
	// occurs in the source code, it is replaced by a COPY of this prototype.
	Symbol symbol;
	
}