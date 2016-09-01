
public class Token {
	private Type type; 
	private String value; 
	
	public Token(Type type, String value){
		this.type = type; 
		this.value = value;
	}
	
	public Type getType(){
		return this.type;
	}
	
	public String getValue(){
		return this.value;
	}
	
	public boolean checkLBracket(){
		boolean result = (type == Type.tsyntax && value.equals("["));
		return result;
	}
	public boolean checkRBracket(){
		boolean result = (type == Type.tsyntax && value.equals("]"));
		return result;
	}
	
}