
public class Ifstmts extends Stmts{
	static Integer nameCounter; 
	//because no 2 if's should have the same name
	
	//if
	Expr expr1;//condition
	//then
	Expr expr2;//code block 
	//else
	Expr expr3;//code block 
	
	public void setOutput(){
		if(expr3 == null){//if there's just expr2 
			this.outputType = expr2.outputType;//ouptputType = expr2.outputType
			return;
		}	
		else{//else if expr2
			if(expr2.outputType == expr3.outputType){//check for both outputting same type
				this.outputType = expr2.outputType;
				return;
			}else{//if they don't match 
				this.outputType = Type.Error;//we can't promise any type 
				return;
			}
		}
	}
	
	public Ifstmts(int index) {
		Ifstmts.nameCounter = 0;
		isIf = true;
		success = false;
		tokenCount = 0;
		
		//first check for [
		Token tokenCheck = tokenList.get(index);
		if(!tokenCheck.checkLBracket()){
			return;
		}
		lBracket = tokenCheck;
		tokenCount=1;
		
		//check for if
		tokenCheck = tokenList.get(index+tokenCount);
		if (!(tokenCheck.getType() == Type.tstmts && 
				tokenCheck.getValue() == "if")){
			return;
		}
		stmts = tokenCheck;
		tokenCount++;
		
		//check for expr1 
		expr1 = Expr.produce(index+tokenCount);
		tokenCount += expr1.tokenCount;
		if (!expr1.success){
			return;
		}
		
		//check for expr2 
		expr2 = Expr.produce(index+tokenCount);
		tokenCount += expr2.tokenCount;
		if (!expr2.success){
			return;
		}
	
		//check for expr3 
		expr3 = Expr.produce(index+tokenCount);
		if (expr3.success){
			tokenCount += expr3.tokenCount;
		}else{
			expr3 = null;
		}
		
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
	
	public String printNode() {
		String output = "";
		//first let's make a if function 
		//then immediately call it 
		//first get expr1 ready 
		Oper condition = null; 
		if(Oper.class.isInstance(expr1)){ // check for boolean 
			condition = (Oper)expr1;
			condition.setOutput();
		}else{
			output = "ERROR: need boolean for if statement";
			return output;
		}
		if(condition.outputType != Type.tbool){
			output = "ERROR: need boolean for if statement";
			return output;
		}
		output += ": myIfFunction"+Ifstmts.nameCounter;//keep counter here 
														//until function called
		output += " "+condition.printNode()+" if";//condition if
		output += " "+expr2.printNode();//statements 
		 
		if(expr3 != null){//statements
			output += " else";//else
			output += " "+expr3.printNode();
		}
		output += " endif ; ";//endif 
		output += " myIfFunction"+Ifstmts.nameCounter++;//call myIfFunction
		return output;
	}

}
