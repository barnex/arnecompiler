import java.io.*;

public class Compiler{

	public static void main(String[] args) throws IOException{

//		if(args.length == 0)
//			args = new String[]{"/home/arne/dev/compiler/project/trunk/test.arne"};

		for(String filename: args){

			File asmfile = new File(filename + ".s");
			asmfile.delete();
			File origtree = new File(filename + "-orig.xml");
			origtree.delete();
			File compiledtree = new File(filename + "-compiled.xml");
			compiledtree.delete();

			Node root = null;
			try{
				Parser parser = new Parser(filename);
				root = parser.parse();

				root.print(new PrintStream(new FileOutputStream(origtree)));

				root.compiletreefully();
				root.print(new PrintStream(new FileOutputStream(compiledtree)));

				root.checkUnresolved_tree();
				root.typecheck_tree();

				root.compile_tree_asm();
				Node.dump_asm(new PrintStream(new FileOutputStream(asmfile)));
			}
			catch(Throwable error){
				error.printStackTrace();
				System.err.println("Tree: ");
				root.print(System.err);
				error.printStackTrace();
				System.exit(-2);
			}
		}
	}
}
