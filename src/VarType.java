
public class VarType extends Node{
	Token vartype;
	public VarType(Token tokenCheck) {
		if(tokenCheck.getType() == Type.ttype){
			tokenCount= 1;
			success = true;
			vartype = tokenCheck;
			return;
		}
		success = false;
		tokenCount = 0;
		vartype = null;
	}

}
