import java.io.IOException;

/**
 * Import is similar to include, except that the file is automatically
 * protected against duplicate including. (As if there were a typical
 * C-style #IFNDEF ... #DEFINE ... #ENDIF around the included file.)
 */
public class Import extends Include{

	//
	// APPROACH SEEMS FAULTY: RECURSIVE COMPILATION MAY ALREADY HAVE ADDED 
	// DECLARATIONS TO SOME DECLARATION LIST BEFORE REDUNDANT IMPORTEDBLOCK
	// GETS REMOVED
	//

	void compile_it_impl(){

		//
		// Copied from Include.java, replaced NonHidingBlock by IncludedBlock
		//
		try{
			String file = ((Stringnode)child(0)).data + STANDARD_SUFFIX;
			// parse included file, is wrapped in a Root block
			Node root = new Parser(file).parse();
			
			// replace the wrapper block by a non-hiding wrapper block
			ImportedBlock block = new ImportedBlock();
			block.file = file;
			for(Node child: root.children){
				block.add(child);
			}
			
			if(findDuplicateUpstream(file) == null){
				// replace the include statement with the contents of the file
				// TODO: quite some room for optimization here: only parse block if used.
				replaceme(block);
				
				while(findDuplicateDownstream(file) != null){
					findDuplicateDownstream(file).removeme();
				}
			}
			else{
				this.removeme();
			}
		}
		catch(IOException e){
			throw new Error(e);
		}
		parent.changemade = true; // this.changemade is futile, this is to be replaced
	}
	
	/**
	 * Looks upstream (towards root) and searches if an ImportedBlock for the given
	 * file that already exists. In that case, it will not be necessary to include
	 * it again.
	 */
	ImportedBlock findDuplicateUpstream(String file){
		Node current = parent;
		while(current != null){
			for(Node child: current.children){
				if(child instanceof ImportedBlock && ((ImportedBlock)child).file == file)
					return (ImportedBlock)child;
			}
			current = current.parent;
		}
		return null;
	}
	
	ImportedBlock findDuplicateDownstream(String file){
		for(Node child: listDeepChildren()){
			if(child instanceof ImportedBlock && ((ImportedBlock)child).file == file)
				return (ImportedBlock)child;
		}
		return null;
	}

}