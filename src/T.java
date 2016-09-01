import java.util.TreeMap;



public class T extends Node{
	Token lBracket;
	Token rBracket;
	S s;
	Token EOF;
	
	public String printNode(){
		//first print out the definition for power
		String output = "";
		output += ": ** ( n1 u -- n ) ";
		//System.out.println("\\ n = the uth power of n1");
		output += "       1 swap 0 u+do ";
		output += "         over * ";
		output += "loop nip ; ";
		output += s.printNode();
		return output;
	}
	
	@SuppressWarnings("static-access")
	public T() {
		this.produce();
		this.variableList = new TreeMap<Integer,Type>();
		if(!this.success){
			System.out.println("Error with parsing!!!");
		}
	}
	
	private void produce(){
		success = false; //no success until gone through everything 
		tokenCount = 0;
		lBracket = tokenList.get(tokenCount);//grab first [
		if(!lBracket.checkLBracket()){
			return;
		}
		// token passes
		tokenCount = 1;
		
		//call to S
		s = S.produce(tokenCount);//start S at 2nd token 
		if(!s.success){
			tokenCount = s.tokenCount + tokenCount;
			return;
		}
		tokenCount += s.tokenCount;
		
		//grab last ]
		rBracket = tokenList.get(tokenCount);
		if(!rBracket.checkRBracket()){
			return;
		}
		tokenCount++;
		
		//check for EOF
		EOF = tokenList.get(tokenCount);
		if(EOF.getType() != Type.EOF){
			return;
		}
		//don't update tokenCount for EOF
		success=true;
		return;
	}
}
