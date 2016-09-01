
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class Tokenizer {
	FileReader reader;
	boolean isPeek;
	int readInt;
	//SortedMap<Integer,String> constantTable;
	HashMap<String,Integer> idTable;
	HashMap<String,Token> keyTable;
	HashSet<Character> digits,binops,alpha_,alphaNumeric_,compare;
	public Tokenizer(String filename) throws IOException{
		reader = new FileReader(filename);
		isPeek = false; //first time running, no peeks yet 
		readInt = 0;//nothing to hold yet
		//init the tables
		
		//constantTable = new TreeMap<Integer,String>();
		//constantTable.put(-1, "Move along");
		
		idTable = new HashMap<String, Integer>();
		
		digits = new HashSet<Character>();
		digits.add('0');
		digits.add('1');
		digits.add('2');
		digits.add('3');
		digits.add('4');
		digits.add('5');
		digits.add('6');
		digits.add('7');
		digits.add('8');
		digits.add('9');
		
		binops = new HashSet<Character>();
		binops.add('+');
		binops.add('*');
		binops.add('/');
		binops.add('%');
		binops.add('^');
		//binops.add('=');
		
		alpha_ = new HashSet<Character>();
		alpha_.add((char)95);
		for(int i=97;i<=122;i++){//add lower letters 
			alpha_.add((char)i);
		}
		for(int i=65;i<=90;i++){//add upper letters 
			alpha_.add((char)i);
		}
		
		alphaNumeric_ = new HashSet<Character>(alpha_);
		for(int i = 48; i<=57; i++){
			alphaNumeric_.add((char)i);
		}
		
		compare = new HashSet<Character>();
		compare.add('>');
		compare.add('<');
		
		keyTable = new HashMap<String,Token>();
		keyTable.put("or", new Token(Type.tbinop,"or"));//string binops 
		keyTable.put("and", new Token(Type.tbinop,"and"));
		keyTable.put("not", new Token(Type.tunop,"not"));// unops
		keyTable.put("sin", new Token(Type.tunop,"sin"));
		keyTable.put("cos", new Token(Type.tunop,"cos"));
		keyTable.put("tan", new Token(Type.tunop,"tan"));
		keyTable.put("stdout", new Token(Type.tstmts,"stdout"));//statements 
		keyTable.put("if", new Token(Type.tstmts,"if"));
		keyTable.put("while", new Token(Type.tstmts,"while"));
		keyTable.put("let", new Token(Type.tstmts,"let"));
		keyTable.put("bool", new Token(Type.ttype,"bool"));//types
		keyTable.put("int", new Token(Type.ttype,"int"));
		keyTable.put("float", new Token(Type.ttype,"float"));
		keyTable.put("string", new Token(Type.ttype,"string"));
		keyTable.put("true", new Token(Type.tbool,"true"));
		keyTable.put("false", new Token(Type.tbool,"false"));
	}
	
	//read the next char and update internal readInt in case of EOF
	private char getChar(){
		try {
			readInt = reader.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return (char)readInt;
	}
	
	private Token enterConstantToken(Type type, String readString){
		//int id = constantTable.lastKey() + 1; //make new id 
		//constantTable.put(id,readString);
		//return new Token(type,String.valueOf(id));
		return new Token(type, readString);
	}
	
	public Token getToken(){
		//go through and read a token
		String readString = ""; //the whole string that is read 
		char readChar; // the char that is read 
		//check to see if there is a current peek
		if(isPeek == false ){//if there is not peek
			try{
				readInt = reader.read();//read the next char 
			}catch(IOException err){
				System.out.println("!!!!!!!!!!!!Reading the file "
						+ "inside Tokenizer failed!!!!!!!!!!!!!!!");
			}
		}
		if (readInt == -1){// if this is the end of the file 
				return new Token(Type.EOF,"EOF"); //return no token
		}
		readChar = (char) readInt; //first char for our use
		readString += readChar; 
		
		//at this point we have the first char of whatever we're reading 
		
		if (alpha_.contains(readChar)){ //looking at keyword/identifier
			isPeek = true; //we will need a peek with this
			readChar = getChar();
			while(alphaNumeric_.contains(readChar)){ //while still a keyword/identifier
				readString = readString + readChar;//add it to the string 
				readChar = getChar();
			}
			//now we have a string for identifier/keyword
			//check for it in the table 
			Token token = keyTable.get(readString);
			if(token != null){//it was a keyword 
				return token;
			}
			//must be identifier 
			boolean isInTable = idTable.containsKey(readString);
			if (isInTable == true){//found identifier 
				int id = idTable.get(readString);
				return new Token(Type.tid,String.valueOf(id)); 
			}
			//new identifier 
			int id = idTable.size();
			idTable.put(readString,id);
			return new Token(Type.tid, String.valueOf(id));
		}
		
		if(binops.contains(readChar)){//looking for a binary operator 
			isPeek = false;//no peed was needed
			return new Token(Type.tbinop,readString); 
		}
		
		if(compare.contains(readChar)){//looking for comparators 
			//check to see if next is '='
			readChar = getChar();
			if(readChar == '='){
				readString += readChar; 
				isPeek = false;//no peek, char was used 
			}else{
				isPeek = true; //cannot use that char, was a peek
			}
			return new Token(Type.tineq,readString);
		}
		if(readChar == '='){//looking for = comparator
			isPeek = false; // used the char 
			return new Token(Type.tineq,readString);
		}
		if(readChar == '!'){//looking for != comparator 
			readChar = getChar();
			if(readChar == '='){
				readString += readChar;
				isPeek = false; // used the char 
				return new Token(Type.tineq, readString);
			}else{
				return new Token(Type.Error,"fail");
			}
		}
		
		if(readChar == ':'){//needs to be followed by '=' or fail 
			readChar = getChar();
			if(readChar == '='){
				isPeek = false; //used the char, was not a peek
				readString += readChar; 
				return new Token(Type.tassign, readString);
			}else{
				return new Token(Type.Error,"fail");
			}
		}
		
		if(readChar == '"'){//looking for a string
			isPeek = false; // terminated with '"', so no peek used 
			readChar = getChar();
			while(readInt != -1 && readChar != '"'){
				readString += readChar; //add to string 
				readChar = getChar(); //fetch next 
			}
			if(readInt == -1 || readChar != '"'){ //prematurely found end of file 
				return new Token(Type.Error,"fail");
			}
			if(readChar == '"'){
				readString += '"';
			}
			//at this point have a valid string
			return enterConstantToken(Type.tstring,readString);
		}
		
		if(readChar == '-'){//so the fun begins 
			/*readChar = getChar();
			if(digits.contains(readChar)){ 
				readString += readChar; 
				isPeek = false;//used the char
				return Ints(readString);//go down 
			}else{
				*/
				//isPeek = true; //not using char, was peek 
			
			
			
				//milestone 5 [-1] vs -1 controversy 
				isPeek = false;
				return new Token(Type.tminus,"minus");
			//}
		}
		
		if(readChar == '.'){//looking for float 
			readChar = getChar();
			if(digits.contains(readChar)){
				readString += readChar;
				isPeek = false; 
				return Floats(readString);
			}else{
				return new Token(Type.Error,"fail");
			}
		}
		
		if(digits.contains(readChar)){//looking for integer 
			isPeek = false;
			return Ints(readString);
		}
		if(readInt == -1){//end of file
			return new Token(Type.EOF,"EOF");
		}
		if(readChar == '[' || readChar == ']'){
			isPeek = false;
			return new Token(Type.tsyntax,readString);
		}
		//System.out.println("Passed all if's with:"+readChar);
		//System.out.println("Calling tokenizer for next char");
		isPeek = false;
		return this.getToken();
	}
	
	private Token Ints(String readString){
		//receiving valid integer, here we add more digits 
		char readChar = getChar();//read next char 
		while(digits.contains(readChar)){//if digit 
			readString += readChar; //append digit
			readChar = getChar();
		}
		if(readChar == 'E' || readChar == 'e'){
			readString += readChar;//append E
			isPeek = false;//char used, not peeked
			return FloatsE(readString);
		}
		if(readChar == '.'){
			readString += readChar; //append dot 
			isPeek = false;//char used 
			return Floats(readString);
		}
		//none matched, but started with valid integer anyways 
		isPeek = true;//didn't use last char 
		return enterConstantToken(Type.tint, readString);
	}
	
	private Token Floats(String readString){
		//receiving valid float, here we add more digits 
		char readChar = getChar();//read next char 
		while(digits.contains(readChar)){//if digit 
			readString += readChar; //append digit
			readChar = getChar();
		}
		if(readChar == 'E' || readChar == 'e'){
			readString += readChar;//append E
			isPeek = false;//char used, not peeked
			return FloatsE(readString);
		}
		//none matched, but started with valid integer anyways 
		isPeek = true;//didn't use last char 
		return enterConstantToken(Type.tfloat, readString);
	}
	
	private Token FloatsE(String readString){
		char readChar = getChar(); 
		if(readChar == '+' || readChar == '-'){
			readString += readChar;
			readChar = getChar();
			if(!digits.contains(readChar)){
				return new Token(Type.Error,"fail");
			}
		}else if(!digits.contains(readChar)){
			return new Token(Type.Error,"fail");
		}
		//char passes tests, add on 
		readString += readChar;
		
		//now valid, and just appending more digits 
		readChar = getChar();
		while (digits.contains(readChar)){
			readString+=readChar;
			readChar = getChar();
		}
		isPeek = true;//didn't use last char 
		return enterConstantToken(Type.tfloat, readString);
	}
}
