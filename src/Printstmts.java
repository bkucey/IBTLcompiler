
public class Printstmts extends Stmts{
	Oper oper;
	
	public void setOutput(){
		this.outputType = Type.Error;//there is no output type for this 
	}
	
	public String printNode(){
		//check for the type of operand
		//and print accordingly 
		if(oper!=null){
			oper.setOutput();
		}else{
			return "Printstmts without valid oper";
		}
		String output = "";
		output += " "+oper.printNode();
		if(oper.outputType == Type.tint){//if it's an int
			output += " .";
		}else if (oper.outputType == Type.tfloat){
			output += " f."; 
		}else if (oper.outputType == Type.tbool){
			output += " ."; //same as int 
		}else if (oper.outputType == Type.tstring){
			output += " type"; 
		}else{
			output += "error on Printstmts, not valid type:";
			if(oper!=null){
				if(oper.outputType != null){
					output+= oper.outputType.toString();
				}else{
					output += "<ERROR: no output type set for STDOUT operand>";
				}
			}else{
				output+= "no oper";
			}
		}
		return output;
	}
	
	public Printstmts (int index) {
		isIf = false;
		isWhile = false;
		isLet = false;
		isPrint = true;
		success = false;
		//first check for [
		Token tokenCheck = tokenList.get(index);
		if(!tokenCheck.checkLBracket()){
			return;
		}
		lBracket = tokenCheck;
		tokenCount=1;
		
		//check for stdout
		tokenCheck = tokenList.get(index+tokenCount);
		if (!(tokenCheck.getType() == Type.tstmts && 
				tokenCheck.getValue() == "stdout")){
			return;
		}
		stmts = tokenCheck;
		tokenCount++;
		
		//check for oper 
		oper = Oper.produce(index+tokenCount);
		tokenCount += oper.tokenCount;
		if (!oper.success){
			return;
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

}
