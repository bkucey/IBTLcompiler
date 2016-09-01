
public class Oper extends Expr{
	Token lBracket; // the left bracket 
	Token rBracket; // the right bracket 
	Token ops; // the operation 
	Oper oper1; //first operand 
	Oper oper2; //second operand 
	boolean isConstant; //to see if it's a constant 
	boolean isName; //to see if it's a variable
	
	public void setOutput(){
		oper1.setOutput();
		if(oper2 != null){
			oper2.setOutput();
		}
		//look at operands at set an output 
		if(oper2 == null){//single operand 
			oper1.setOutput();
			//now check operation type and operand type 
			if(oper1.outputType == Type.tbool 
					&& this.ops.getValue().equals("not") ){ //checking for boolean not 
				this.outputType = Type.tbool;
				return;
			}else if(ops.getType() == Type.tminus 
					&& (oper1.outputType == Type.tint //checking for int or 
						|| oper1.outputType == Type.tfloat) //float negate
					){
				this.outputType = oper1.outputType;
				return;
			}else if( (oper1.outputType == Type.tfloat 
					     || oper1.outputType == Type.tint)//checking for float 
					&& (ops.getValue().equals("sin") //with appropriate unop 
						|| ops.getValue().equals("cos")
						|| ops.getValue().equals("tan") )
					) {
					this.outputType = Type.tfloat;
					return;
			}else{
				System.out.println("Error with operand:"+this.ops.getValue()
							+" and the operand:"+this.oper1.printNode());//error throw 
				return;
			}
			
		}
		//double operand
		if(ops.getType() == Type.tineq //if it's an inequality
				&&(oper1.outputType == Type.tint || oper1.outputType == Type.tfloat) //with number operands 
				&&(oper2.outputType == Type.tint || oper2.outputType == Type.tfloat)){ 
			outputType = Type.tbool;//output bool
			return;
		}
		if(  (ops.getType() == Type.tbinop || ops.getType() == Type.tminus ) //if it's a binop
				&&(oper1.outputType == Type.tint) //with int operands 
				&&(oper2.outputType == Type.tint)){ 
			outputType = Type.tint;//output bool
			return;
		}
		if(  (ops.getType() == Type.tbinop || ops.getType() == Type.tminus ) //if it's a binop
				&&(oper1.outputType == Type.tint || oper1.outputType == Type.tfloat) //with float operands 
				&&(oper2.outputType == Type.tint || oper2.outputType == Type.tfloat)){ 
			outputType = Type.tfloat;//output float
			return;
		}
		
		if( (ops.getValue().equals("and") || ops.getValue().equals("or")) //if it's a binary binop
				&&(oper1.outputType == Type.tbool) // with booleans operands 
				&&(oper2.outputType == Type.tbool)){ 
			outputType = Type.tbool;//output bool
			return;
		}
	
		/*if(ops.getType() == Type.tunop){//if there's only one operand 
			outputType = oper1.outputType;//set to that 
		}*/
		if( ops.getValue().equals("+") //appending strings 
		 &&(oper1.outputType == Type.tstring && oper2.outputType == Type.tstring) ){
			outputType = Type.tstring;
			return;
		/*}else if (oper1.outputType == Type.tfloat || oper2.outputType == Type.tfloat){
			outputType = Type.tfloat;
		}else if (oper1.outputType != oper2.outputType){
			outputType = Type.Error;*/
		}else{
			System.out.println("Error with ):"+this.ops.getValue()+".  Operation "
					+ "and operands don't match signatures.  ");//outputType = oper1.outputType;
		}
	}
	
	public String printNode() {
		oper1.setOutput();
		if(oper2 != null){
			oper2.setOutput();
		}
		//prints this node
		String output = "";
		if(oper2 == null){ //unops 
			//look at operation and oper1
			if(ops.getValue().equals("not")){
				if(oper1.outputType != Type.tbool){//check for oper1 as bool or throw error 
					output += "Warning:"+oper1.printNode()+" doesn't match boolean for NOT operation";
				}
				output += " "+ oper1.printNode();
				output += " invert";
				return output;
			}
			if(ops.getValue().equals( "minus")){ //will be a unops negate 
				if(oper1.outputType == Type.tint){
					output+= " "+oper1.printNode();
					output+= " negate";
					return output;
				}
				else if (oper1.outputType == Type.tfloat){
					output += " "+oper1.printNode();
					output += " fnegate";
					return output;
				}
				else{
					output = "ERROR: int or float expected with '-'";//is error
					return output;
				}
			}
			if( (ops.getValue().equals("sin") || ops.getValue().equals("cos") //check for float unops 
					|| ops.getValue().equals("tan"))
				&& oper1.outputType == Type.tfloat) {
				output += " "+oper1.printNode();
				output += " f"+ops.getValue();
				return output;
			}
			if( (ops.getValue().equals("sin") || ops.getValue().equals("cos") //check for int unops 
					|| ops.getValue().equals("tan"))
				&& oper1.outputType == Type.tint) {
				output += " "+oper1.printNode();
				output += " "+"s>f";
				output += " f"+ops.getValue();
				return output;
			}
			//error
			output = "error for unop";
			return output;
		}else{ //must be a binop 
			if (ops.getValue().equals( "and") || ops.getValue().equals( "or")){
				if (oper1.outputType != Type.tbool){
					output = "ERROR for operand 1";//error
					return output;
				}
				output += " "+oper1.printNode();
				if (oper2.outputType != Type.tbool){
					output = "ERROR for operand 2";//error
					return output;
				}
				output += " "+oper2.printNode();
				output += " "+ops.getValue();
				return output;
			}
			else if (oper1.outputType == Type.tstring //concatenating strings 
					&& oper2.outputType == Type.tstring 
					&& ops.getValue().equals( "+")){
				output += " "+oper1.printNode();
				//cast if needed later 
				output += " "+oper2.printNode();
				//cast if needed later
				output += " append";
				return output;
			}else if(ops.getType() == Type.tassign){//if it's an assignment 
				if(!oper1.isName){ // first check to see that it's a name 
					output = "ERROR can only assign to names";
					return output;
				}
				Name name = (Name)oper1; //get the name out 
				//check to see that the name and oper2 have the same type
				Type type1 = name.getType(); 
				if(type1 != oper2.outputType){//we can't assign the wrong type
					if(type1 == Type.tfloat && oper2.outputType == Type.tint){
						//but we can upconvert ints to assign to floats 
						output += oper2.printNode()+" s>f "+ name.printAssign();
					}else{
						output = "ERROR can't assign wrong type to variable";
						return output;
					}
				}
				//print out the string " oper2 idX !"
				output += oper2.printNode() + name.printAssign();
				return output; 
			
			}else{
				//must be doing some math 
				boolean needFloat = false;
				if(oper1.outputType == Type.tfloat || oper2.outputType == Type.tfloat){
					needFloat = true;
				}
				if(oper1.outputType == Type.tstring || oper1.outputType == Type.tbool){
					output += "Error, operand 1 isn't int or float";
					return output;
				}
				if(oper2.outputType == Type.tstring || oper2.outputType == Type.tbool){
					output += "Error, operand 2 isn't int or float";
					return output;
				}
				
				output += " "+oper1.printNode();
				if(needFloat && oper1.outputType == Type.tint){
					output += " "+"s>f";
				}
				output += " "+oper2.printNode();
				if(needFloat && oper2.outputType == Type.tint){
					output += " "+"s>f";
				}
				if (ops.getValue().equals("^")){
					ops = new Token(Type.tbinop,"**");
				}
				if(ops.getValue().equals("%")){
					ops = new Token(Type.tbinop,"mod");
				}
				if(ops.getValue().equals("!=")){
					ops = new Token(Type.tineq, "<>");
				}
				if(ops.getValue().equals("minus")){
					ops = new Token(Type.tminus, "-");
				}
				if(needFloat){
					output += " f"+ops.getValue();
				}else{
					output += " "+ops.getValue();
				}
				return output;
			}
		}
	}
	
	public boolean isOper(){
		return !(isConstant || isName);
	}
	
	public static Oper produce(int index) {
		Oper oper = new Oper();
		oper.oper2 = null; // set in case of unops
		oper.success = false;
		//check for [, constant, or name 
		Token tokenCheck = tokenList.get(index);
		if(!tokenCheck.checkLBracket()){
			//not bracket, could be name or constant 
			//check constant 
			if(tokenCheck.getType()==Type.tbool || 
					tokenCheck.getType() == Type.tint || 
					tokenCheck.getType() == Type.tfloat || 
					tokenCheck.getType() == Type.tstring){
				Constant constant = new Constant(tokenCheck);
				return constant;
			//check name
			}else if (tokenCheck.getType() == Type.tid){
				Name name = new Name(tokenCheck);
				return name;
			}else{
				oper.success = false;
				return oper;
			}
		}
		//we've got a left bracket 
		oper.lBracket = tokenCheck;
		oper.tokenCount = 1;
		
		//read operation
		tokenCheck = tokenList.get(index + oper.tokenCount);
		if(tokenCheck.getType() != Type.tbinop && 
				tokenCheck.getType() != Type.tineq && 
				tokenCheck.getType() != Type.tassign &&  
				tokenCheck.getType() != Type.tminus && 
				tokenCheck.getType() != Type.tunop){
			oper.success = false;
			return oper;
		}
		oper.ops = tokenCheck;
		oper.tokenCount++;
		
		//read operand1 
		//check and perform ASSIGNMENT HERE
		if(oper.ops.getType() == Type.tassign) {// need the assign to be in the format [:= name oper]
			//read name into oper1
			oper.oper1 = Oper.produce(index + oper.tokenCount);
			if(!oper.oper1.success || !oper.oper1.isName){
				return oper; //failed
			}
		}else{ //read oper1 as usual otherwise 
			oper.oper1 = Oper.produce(index + oper.tokenCount);
			if(!oper.oper1.success){
				oper.tokenCount += oper.oper1.tokenCount;
				return oper;
			}
		}
		oper.tokenCount += oper.oper1.tokenCount;
		
		//check operation for reading operand2
		if(oper.ops.getType() == Type.tbinop 
				|| oper.ops.getType() == Type.tineq 
				|| oper.ops.getType() == Type.tassign
				|| oper.ops.getType() == Type.tminus){ 
			//read operand2 
			oper.oper2 = Oper.produce(index + oper.tokenCount);
			if(oper.oper2.success){
				oper.tokenCount += oper.oper2.tokenCount;
			}else if (oper.ops.getType() == Type.tminus){
				oper.oper2 = null;//no oper2 then, that was a unops minus then
			}else{
				oper.oper2 = null; //drop it if it didn't work
				return oper; //failed for binops  
			}
		}
		//read right bracket 
		tokenCheck = tokenList.get(index + oper.tokenCount);
		if(!tokenCheck.checkRBracket()){
			return oper;
		}
		oper.rBracket = tokenCheck;
		oper.tokenCount++;
		oper.isConstant = false;
		oper.isName = false;
		oper.success = true;
		return oper;
		
	}
}
