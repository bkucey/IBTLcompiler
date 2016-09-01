
public class Expr extends Node{
	Type outputType; //holds the output type of this expression 
	
	
	public static Expr produce(int index){
		//check oper 
		Oper oper = Oper.produce(index);
		if(oper.success){
			return oper;
		}
		Stmts stmts = Stmts.produce(index);
		if(stmts.success){
			return stmts;
		}
		if(oper.tokenCount>stmts.tokenCount){
			return oper;
		}
		return stmts;
		
	}

	public String printNode(){
		return " not implemented in Expr";
	}

	public void setOutput(){
		System.out.println("setOutput not implemented for Expr");
	}
}
