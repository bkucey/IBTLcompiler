
public class Constant extends Oper{
	Token token;//the token this constant represents
	
	public void setOutput(){
		outputType = token.getType();
	}
	
	public String printNode(){
		if(token.getType() == Type.tstring){//need to reformat string to s" hi"
			String newValue = token.getValue();
			newValue = newValue.substring(1);//shave "
			newValue = " s\" "+newValue;//put s" in front
			return newValue;
		}
		if(token.getType() == Type.tbool){
			if(token.getValue().equals( "true")){
				return " -1";
			}
			return " 0";
		}
		if(token.getType() == Type.tfloat){
			if(this.token.getValue().lastIndexOf('e') == -1){ // if there is no 'e', add 'e'
				this.token = new Token(Type.tfloat,this.token.getValue().concat("e0"));
			}
		}
		return " "+token.getValue();
	}
	
	public Constant(Token tokenCheck) {
		token = tokenCheck;
		isConstant = true;
		isName = false;
		success = true;
		tokenCount = 1;
	}
}
