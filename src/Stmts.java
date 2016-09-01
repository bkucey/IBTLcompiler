
public class Stmts extends Expr{

	boolean isIf = false;
	boolean isWhile = false;
	boolean isLet = false;
	boolean isPrint = false;
	
	Token lBracket;
	Token rBracket;
	Token stmts;
	public static Stmts produce(int index) {
		Stmts stmts = new Stmts();
		stmts.success = false;
		stmts.tokenCount = 0; 
		
		//check for printstmts
		Printstmts print = new Printstmts(index);
		if(print.success){
			return print;
		}
		
		//check for ifstmts
		Ifstmts ifstmts = new Ifstmts(index);
		if(print.success){
			return ifstmts;
		}
		
		Whilestmts whilestmts = new Whilestmts(index);
		if(whilestmts.success){
			return whilestmts;
		}
		
		Letstmts letstmts = new Letstmts(index);
		if(letstmts.success){
			return letstmts;
		}
		
		return ifstmts;
		
	}

}
