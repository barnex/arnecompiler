/**                                                          
 * The unique definition of a type:                          
 *                                                           
 * declareclass(int)                                         
 * struct(vector, ...)                                       
 *                                                           
 * TypeDefinitions are UNIQUE. Each type can be defined only once.
 * Many references to the same type may occur, however. e.g.:
 * int a, int b
 * ^^^    ^^^
 * both these Type instances point the same TypeDefintion (field: declaration)
 *
 * @see: PrimitiveDefinition, StructDefinition
 *
 * @author arne
 */

public class TypeDefinition extends Definition{

        /**
         * Number of 32-bit words needed to store an instance
         * This is 1 for a primitive type like int,
         * but more for a struct.
         */
        int storagewords = -1;  // debug: -1 means unset

  @Override
  void compile_it_impl() {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  void compile_tree_asm() {
    throw new UnsupportedOperationException("Not supported yet.");
  }
}