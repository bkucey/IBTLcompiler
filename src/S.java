


public class S extends Node{
	public enum Sprod{prod1,prod2,prod3}
	Token lBracket,rBracket;
	S s;
	S_ s_;
	Expr expr;
	Sprod myProd;
	public static S produce(int index) {
		S s = new S();
		s.success = false; 
		if(s.prod1(index)){			
			return s;
		}
		s.clear();
		if(s.prod2(index)){
			return s;
		}
		s.clear(); 
		if(s.prod3(index)){
			return s;
		}
		s.clear();
		return s;
	}
	
	
	/*
	 * checks for exprS'
	 */
	private boolean prod3(int index) {
		tokenCount = 0;
		//check for expr
		expr = Expr.produce(index + tokenCount);
		if(!expr.success){
			return false;
		}
		tokenCount += expr.tokenCount;
		
		//check for S_
		s_ = new S_(index + tokenCount);
		if(!s_.success){
			return false;
		}
		tokenCount += s_.tokenCount;
		success = true;
		myProd = Sprod.prod3;
		return true;
	}

	private void clear(){
		lBracket = null;
		rBracket = null;
		s = null;
		s_ = null;
		expr = null;
		tokenCount = 0;
		success = false;
	}
	
	/*
	 * checks for [S]S_
	 */
	private boolean prod2(int index) {
		tokenCount = 0;
		//check for [
		lBracket = tokenList.get(index+tokenCount);
		if(!lBracket.checkLBracket()){
			return false;
		}
		tokenCount++;
		
		//check for S
		s = S.produce(index+tokenCount);
		if(!s.success){
			return false;
		}
		tokenCount+= s.tokenCount;
		
		//check for ]
		rBracket = tokenList.get(index+tokenCount);
		if(!rBracket.checkRBracket()){
			return false;
		}
		tokenCount++;
		
		//check for s_
		s_ = new S_(index+tokenCount);
		if(!s_.success){
			return false;
		}
		tokenCount += s_.tokenCount;
		success = true;
		myProd = Sprod.prod2;
		return true;
	}

	/*
	 * checks for -> []S'
	 */
	private boolean prod1(int index){
		tokenCount = 0;
		//check for [
		lBracket = tokenList.get(index);
		if(!lBracket.checkLBracket()){
			return false;
		}
		tokenCount++;
		
		//check for ]
		rBracket = tokenList.get(index+tokenCount);
		if(!rBracket.checkRBracket()){
			return false;
		}
		tokenCount++;
		
		//check for S'
		s_ = new S_(index+tokenCount);
		if(!s_.success){
			return false;
		}
		tokenCount+=s_.tokenCount;
		
		success=true;
		myProd = Sprod.prod1;
		return true;
	}


	public String printNode() {
		String output = "";
		if(s != null){
			output += s.printNode();
		}
		if(expr != null){
			output += " "+expr.printNode();
		}
		if(s_ != null){
			output += " "+s_.printNode();
		}
		return output;
	}

}
