


public class Varlist extends Node{
	Token lBracket;
	Token rBracket;
	Name name;
	VarType type;
	Type actualType;
	
	public String printNode(){
		String output = " ";
		
		//print appropriate variable declaration 
		//append the appropriate char for the type 
		if(this.actualType == Type.tfloat){
			output+= "f";
		}else if(this.actualType == Type.tstring){
			output += "2";
		}
		output +="variable"; 
		
		//print the variable name
		output += " "+name.printName();
		
		return output;
	}
	
	public Varlist(int index) {
		success = false;
		tokenCount = 0;
		
		//first check for [
		Token tokenCheck = tokenList.get(index);
		if(!tokenCheck.checkLBracket()){
			return;
		}
		lBracket = tokenCheck;
		tokenCount=1;
		
		//check for name
		tokenCheck = tokenList.get(index+tokenCount);
		name = new Name(tokenCheck);
		if(!name.success){
			return;
		}
		tokenCount++;
		
		//check for type
		tokenCheck = tokenList.get(index+tokenCount);
		type = new VarType(tokenCheck);
		if(!type.success){
			return;
		}
		tokenCount++;
		
		//check for ]
		tokenCheck = tokenList.get(index+tokenCount);
		if (!tokenCheck.checkRBracket()){
			return;
		}
		rBracket = tokenCheck;
		tokenCount++;
		success = true;
		return;
		
		
	}

}
