import java.util.ArrayList;


public class Letstmts extends Stmts{
	Token innerLBracket;
	Token innerRBracket;
	Varlist var1;
	ArrayList<Varlist> varList;
	
	public String printNode(){
		String output = "";
		//print var1 
		Letstmts.enterVarIntoTable(var1);
		output += var1.printNode();
		
		//then print anything in varList
		for (Varlist var : varList){
			Letstmts.enterVarIntoTable(var);
			//Node.variableList.put(Integer.parseInt(var1.name.token.getValue()), var1.type.vartype.getType());//put these var's and types in the variableList
			output += var.printNode();
		}
		
		return output;
	}
	
	private static void enterVarIntoTable(Varlist var1){
		Integer id = Integer.parseInt(var1.name.token.getValue());
		//get the correct type based on the varlist->type->token->value 
		Token typeToken = var1.type.vartype;
		Type type = Type.Error; //will fill with type once we find it 
		if(typeToken.getValue().equals("bool") ){
			type = Type.tbool;
		}else if(typeToken.getValue().equals("float")){
			type = Type.tfloat;
		}else if(typeToken.getValue().equals("int")){
			type = Type.tint;
		}else if(typeToken.getValue().equals("string")){
			type = Type.tstring;
		}
		var1.actualType = type;
		Node.variableList.put(id, type);//put these var's and types in the variableList
		
	}
	
	public Letstmts(int index) {
		varList = new ArrayList<Varlist>();
		isLet = true;
		success = false;
		tokenCount = 0;
		
		//first check for [
		Token tokenCheck = tokenList.get(index);
		if(!tokenCheck.checkLBracket()){
			return;
		}
		lBracket = tokenCheck;
		tokenCount=1;
		
		//check for let
		tokenCheck = tokenList.get(index+tokenCount);
		if (!(tokenCheck.getType() == Type.tstmts && 
				tokenCheck.getValue() == "let")){
			return;
		}
		stmts = tokenCheck;
		tokenCount++;
		
		// check for inner [
		tokenCheck = tokenList.get(index);
		if(!tokenCheck.checkLBracket()){
			return;
		}
		innerLBracket = tokenCheck;
		tokenCount++;
		
		//check for varlist
		var1 = new Varlist(index+tokenCount);
		tokenCount+=var1.tokenCount;
		if(!var1.success){
			return;
		}
		
		//keep checking for varlists's 
		Varlist var = new Varlist(index+tokenCount);
		while(var.success){
			tokenCount+= var.tokenCount;
			varList.add(var);
			var = new Varlist(index+tokenCount);
		}
		//done getting expr's 
		
		//check for inner ] 
		tokenCheck = tokenList.get(index+tokenCount);
		if (!tokenCheck.checkRBracket()){
			return;
		}
		innerRBracket = tokenCheck;
		tokenCount++;
		
		//check for outer ] 
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
