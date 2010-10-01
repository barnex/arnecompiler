import java.io.IOException;
import java.io.InputStream;

public final class Lexer {

	int nextchar;
	int currentchar;
	
	int[] type = new int[256];
	
	int c = 0;
	int UNKNOWN = c++;
	int WHITESPACE = c++;
	int ENDL = c++;
	int LETTER = c++;
	//int DIGIT = c++;
	int OPERATOR = c++;
	int PAREN = c++;
	int COMMA = c++;
	int EOF = c++;
	
	boolean[] concat = new boolean[c];
	boolean[] discard = new boolean[c];
	
	InputStream in;
	
	StringBuffer buffer = new StringBuffer();
	
	
	public Lexer(InputStream in) throws IOException{
		this.in = in;
		
		concat[WHITESPACE] = true;
		concat[ENDL] = true;
		concat[LETTER] = true;
		//concat[DIGIT] = true;
		concat[OPERATOR] = true;
	
		discard[WHITESPACE] = true;
		discard[ENDL] = true;
		
		type(' ', WHITESPACE);
		type('\t', WHITESPACE);
		type('a', 'z', LETTER);
		type('A', 'Z', LETTER);
		type('0', '9', LETTER);
		type('-', LETTER);
		type('_', LETTER);
		type('+', LETTER);
		type('=', LETTER);
		type('*', LETTER);
		type('/', LETTER);
		type('%', LETTER);
		type('>', LETTER);
		type('<', LETTER);
		type('!', LETTER);
		type('[', LETTER);
		type(']', LETTER);
		type('{', LETTER);	// TODO: likely to become parens.
		type('}', LETTER);
		type('.', LETTER);
		type('\n', ENDL);
		type('(', PAREN);
		type(')', PAREN);
		type(',', COMMA);
		
		peeknext();
	}
	
	void peeknext() throws IOException{
		currentchar = nextchar;
		nextchar = in.read();
	}
	
	
	String readToken() throws IOException{
		String token = readToken_nodiscard();
		while(discard(currentchar))
			token = readToken_nodiscard();
		return token;
	}
	
	String readToken_nodiscard() throws IOException{

		buffer = new StringBuffer();
		peeknext();
		
		if(currentchar < 0){
			return null;
		}
		else if(type(currentchar) == UNKNOWN){
			throw new Error("Forbidden character: " + (char)currentchar);
		}
		else{
			
			
			buffer.append((char) currentchar);
			while(type(currentchar) == type(nextchar) 
			      && concat[type(currentchar)]){
				buffer.append((char) nextchar);
				peeknext();
			}
			
			return buffer.toString();
			
		}
	}
	
	int type(int chr){
		if(chr < 0)
			return EOF;
		else
			return type[chr];
	}
	
	void type(char chr, int chartype){
		type[chr] = chartype;
	}
	
	boolean discard(int chr){
		if(chr < 0)
			return false;
		else
			return discard[type(chr)];
	}
	void type(char first, char last_inclusive, int chartype){
		for(int i = first; i <= last_inclusive; i++){
			type[i] = chartype;
		}
	}
}
