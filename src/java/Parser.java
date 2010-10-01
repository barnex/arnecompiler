import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Hashtable;


public class Parser {

	Lexer lexer;
	
	public Parser(String filename) throws IOException{
		this(new File(filename));
	}
	
	public Parser(File file) throws IOException{
		this(new FileInputStream(file));
	}
	
	public Parser(InputStream in) throws IOException{
		lexer = new Lexer(in);
		
		/*
		 * Here we put the 'keywords', and which classes they are translated to.
		 * This can be an identity like "block" -> Block, or an entry to
		 * save some keystrokes like "struct" -> StructDefinition.
		 */
		// ease-of-use:
		lookup.put("[]", ArrayGet.class);
		lookup.put(".", GetMember.class);
		lookup.put("{}", Block.class);
		lookup.put("=", Set.class);
		lookup.put("++", Inc.class);
		lookup.put("--", Dec.class);
		lookup.put("+", Add.class);
		lookup.put("-", Sub.class);		
		lookup.put("*", Mul.class);
		lookup.put("/", Div.class);
		lookup.put("%", Mod.class);
		lookup.put("==", Eq.class);
		lookup.put("!=", Neq.class);
		lookup.put("<", Less.class);
		lookup.put(">", Less.class);
		lookup.put("//", Comment.class);
		lookup.put("let", VariableDefinition.class);
		lookup.put("new", NewStruct.class);
		
		
		// literal: 
		lookup.put("newarray", NewArray.class);
		lookup.put("arrayget", ArrayGet.class);
		lookup.put("array", Array.class);
		lookup.put("newstruct", NewStruct.class);
		lookup.put("getmember", GetMember.class);
		lookup.put("member", MemberDefinition.class);
		lookup.put("struct", StructDefinition.class);
		lookup.put("call", Call.class);
		lookup.put("lambda", LambdaDefinition.class);
		lookup.put("block", Block.class);
		lookup.put("word", Word.class);
		lookup.put("function", FunctionDefinition.class);
		lookup.put("cfunction", CFunctionDefinition.class);
		lookup.put("class", StructDefinition.class);
		lookup.put("set", Set.class);
		lookup.put("get", Get.class);
		lookup.put("_incl", Inc.class);
		lookup.put("_decl", Dec.class);
		lookup.put("_addl", Add.class);
		lookup.put("_subl", Sub.class);
		lookup.put("_mull", Mul.class);
		lookup.put("_divl", Div.class);
		lookup.put("_modl", Mod.class);
		lookup.put("_eql", Eq.class);
		lookup.put("_neql", Neq.class);
		lookup.put("_lessl", Less.class);
		lookup.put("_greaterl", Greater.class);
		lookup.put("if", If.class);
		lookup.put("while", While.class);
		lookup.put("for", For.class);
		lookup.put("declareclass", PrimitiveDefinition.class);
		lookup.put("variable", VariableDefinition.class);
		lookup.put("include", Include.class);
		lookup.put("import", Include.class);
		lookup.put("nonhidingblock", NonHidingBlock.class);
		lookup.put("comment", Comment.class);
	}
	
	
	
	public Node parse() throws IOException{
		Root root = new Root();
		Node current = root;
		Node lastadded = null;
		String token = lexer.readToken();
		while(token != null){
			
			if(lexer.currentchar >= '0' && lexer.currentchar <= '9'){
				Node node = new Word(token);
				current.add(node);
				lastadded = node;
			}
			else if(lexer.type(lexer.currentchar) == lexer.LETTER){
				Node node = lookup(token);
				current.add(node);
				lastadded = node;
			}
			else if(lexer.currentchar == '('){
				current = lastadded;
				lastadded = null;
			}
			else if(lexer.currentchar == ')'){
				current = current.parent;
				if(current == null){
					throw new Error("unbalanced )");
				}
			}
			else if(lexer.currentchar == ','){
				
			}
			
			token = lexer.readToken();
		}
		if(current != root)
			throw new Error("unbalanced (");
		return root;
	}
	
	final java.lang.Class[] EMPTY_CLASS = new java.lang.Class[0];
	final java.lang.Object[] EMPTY_OBJ = new java.lang.Object[0];
	
	
	@SuppressWarnings("unchecked")
	Node lookup(String token){
		java.lang.Class clazz = lookup.get(token);
		if(clazz == null)
			return new Unresolved(token);
		else{
			Node node = null;
			try {
				node = (Node)(clazz.getConstructor(EMPTY_CLASS).newInstance(EMPTY_OBJ));
			} catch (Exception e) {
				System.err.println("In class: " + clazz.getName());
				e.printStackTrace();
				System.exit(-1);
			}
			return node;
		}
	}
	
	Hashtable<String, java.lang.Class> lookup = new Hashtable<String, java.lang.Class>();
	
}
