/**
 * Array is a buit-in type.
 * @author arne
 */
public class Array extends Type{

  static final TypeDefinition ARRAY_TYPE = new TypeDefinition();
  static{
    ARRAY_TYPE.storagewords = 1;
  }
  public Array(){
    declaration = ARRAY_TYPE;
  }

  // only used for type-checking and allocation
  @Override
  public void compile_it_impl(){
  
  }
}