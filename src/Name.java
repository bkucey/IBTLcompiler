

public class Name extends Oper{
	
	Token token;//the token this Name represents
	
	public void setOutput(){
		outputType = this.getType();
	}
	
	public Type getType(){
		Integer id = Integer.parseInt(this.token.getValue());//read the id from the token 
		if(!Node.variableList.containsKey(id)){//check if that id is in the table 
			return Type.Error;
		}
		Type type = Node.variableList.get(id);//get the type
		return type;
	}
	
	public String printAssign(){
		Integer id = Integer.parseInt(this.token.getValue());//read the id from the token 
		if(!Node.variableList.containsKey(id)){//check if that id is in the table 
			return "id:"+id+" hasn't been initialized";
		}
		Type type = Node.variableList.get(id);//get the type 
		//put together the assign operator 
		String operator = " ";
		if(type == Type.tfloat){
			operator += "f";
		}else if (type == Type.tstring){
			operator += "2";
		}
		operator += "!";
		// implement this to output " id0 (2/f)! 
		return this.printName()+operator; 
	}
	
	public String printNode(){
		Integer id = Integer.parseInt(this.token.getValue());//read the id from the token 
		if(!Node.variableList.containsKey(id)){//check if that id is in the table 
			return "id:"+id+" hasn't been initialized";
		}
		Type type = Node.variableList.get(id);//get the type 
		//put together the fetch operator 
		String operator = " ";
		if(type == Type.tfloat){
			operator += "f";
		}else if (type == Type.tstring){
			operator += "2";
		}
		operator += "@";
		return this.printName()+operator; 
	}
	
	public String printName(){
		return " id"+token.getValue();
	}
	
	public Name(Token tokenCheck) {
		if(tokenCheck.getType() == Type.tid){
			token = tokenCheck;
			isName = true;
			isConstant = false;
			success = true;
			tokenCount = 1;
			return;
		}
		success = false;
		tokenCount = 0;
		token = null;
	}

	 
}