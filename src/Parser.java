import java.util.*;


/*class Node{
	Node right;
	Node left; 
	int num;
}*/

public class Parser {

	public static ArrayList<Token> fillTokenList(Tokenizer tokenizer){
		ArrayList<Token> arrayList = new ArrayList<Token>();
		Token tokenAdd = tokenizer.getToken();
		while(tokenAdd.getType()!=Type.EOF){
			arrayList.add(tokenAdd);
			tokenAdd = tokenizer.getToken();
		}
		arrayList.add(tokenAdd);
		return arrayList;
	}
	
	public static void main(String[] args) {
		for(int i=0;i<args.length;i++){
			try{
				Tokenizer tokenizer;
				tokenizer = new Tokenizer(args[i]);
				
				ArrayList<Token> tokenList = fillTokenList(tokenizer);
				Node.tokenList = tokenList;
				T t = new T();
				System.out.println(t.printNode());
			}
			catch(Exception err){
				System.out.println("Error:"+err.getMessage());
			}
		}
		//reads in all the file names 
		/*for each file name read{
		 	call parser
		 	output tree
		 }
		 */
	}
	/*
	public static T parse(String filename){
		Tokenizer tokenizer = new Tokenizer(filename);//call tokenizer 
		Token token = tokenizer.getToken();//fill the ArrayList with the tokens 
		ArrayList<Token> tokenList = new ArrayList<Token>();
		while(token.getType()!=Type.EOF){
			tokenList.add(token);//add the token
			token = tokenizer.getToken();//get the next one
		}
		tokenList.add(token);//add the EOF
		Node.tokenList = tokenList;//tokenList now has full list of tokens 
		
		T result = T.produce();//now start productions
		if(result.success){
			System.out.println("Hooray, success!");
		}else{
			System.out.println("Failed");
		}
		printTree(result);
	}
	
	
	private static void read1(){
		System.out.println("I'm cool");
		FileInputStream fin=null; // holds our file stream 
		try{
			fin = new FileInputStream("Text");
			
		}catch(FileNotFoundException exc){
			System.out.println("Couldn't open file");
		}
		
		int readThis;
		
		try{
			readThis = fin.read();
			while(readThis != -1){ //not EOF 
				System.out.print(readThis);
				readThis = fin.read();
			}
		}catch(IOException exc){
			System.out.println("Couldn't read file:"+exc.getMessage());
		}catch(Exception exc){
			System.out.println("Err:"+exc.getMessage());
		}finally{
			try{
				fin.close(); // close the file
			}catch(Exception exc){
				System.out.println("Couldn't close file:"+exc.getMessage());
			}
		}
		System.out.println();
	}
	
	private static void readLines(){
		FileInputStream fin = null;
		InputStreamReader sReader = null;
		BufferedReader reader = null;
		try{
			fin = new FileInputStream("Text");
			sReader = new InputStreamReader(fin);
			reader = new BufferedReader(sReader);
			String line = reader.readLine();
			while (line != null){
				System.out.println(line);
				line = reader.readLine();
			}
		}catch(Exception exc){
			System.out.println("err:"+exc.getMessage());
		}
		finally{
			try {
				reader.close();
				sReader.close();
				fin.close();
			}catch(Exception err){
				System.out.println(err.getMessage());
			}
		}
	}*/

}
