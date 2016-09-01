import java.util.*;
public class Whilestmts extends Stmts{
	static Integer nameCounter; 
	//because no 2 while functions should have the same name
	Expr expr1;
	ArrayList<Expr> exprList;
	
	public String printNode(){
		String output;
		Oper condition = null; 
		if(Oper.class.isInstance(expr1)){ // check for boolean 
			condition = (Oper)expr1;
			condition.setOutput();
		}else{
			output = "ERROR: need boolean for while statement";
			return output;
		}
		if(condition.outputType != Type.tbool){
			output = "ERROR: need boolean for while statement";
			return output;
		}
		output = " : myWhileFunction"+Whilestmts.nameCounter;
		output += " begin";
		//add the expr 
		output += " "+expr1.printNode();
		
		output += " while";
		
		//add the exprList
		for (Expr expr : exprList){
			output += " "+expr.printNode();
		}
		
		output += " repeat ;";
		output += " myWhileFunction"+Whilestmts.nameCounter++;
		return output;
	}
	
	public Whilestmts(int index) {
		Whilestmts.nameCounter = 0;
		exprList = new ArrayList<Expr>();
		isWhile = true;
		success = false;
		tokenCount = 0;
		
		//first check for [
		Token tokenCheck = tokenList.get(index);
		if(!tokenCheck.checkLBracket()){
			return;
		}
		lBracket = tokenCheck;
		tokenCount=1;
		
		//check for while
		tokenCheck = tokenList.get(index+tokenCount);
		if (!(tokenCheck.getType() == Type.tstmts && 
				tokenCheck.getValue() == "while")){
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
		
		//keep checking for expr's 
		Expr expr = Expr.produce(index+tokenCount);
		while(expr.success){
			tokenCount+= expr.tokenCount;
			exprList.add(expr);
			expr = Expr.produce(index+tokenCount);
		}
		//done getting expr's 
		
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
