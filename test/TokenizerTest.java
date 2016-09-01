import static org.junit.Assert.*;

import java.io.*;
import java.util.*;

import org.junit.Test;


public class TokenizerTest {

	String file = "text.txt";
	public static boolean writeFile(String filename, String writeThis){
		try{
			FileWriter writer = new FileWriter(filename);
			writer.write(writeThis);
			writer.close();
			return true;
		}catch(IOException exc){
			System.out.println("Couldn't open(w) file:"+filename);
			return false;
		}
	}
	
	@Test
	public void testInit() {
		try {
			@SuppressWarnings("unused")
			Tokenizer tokenizer = new Tokenizer(file);
		} catch (IOException e) {
			System.out.println();
		}
	}
	
	@Test
	public void testString() {
		String filename = "testString";
		String writeThis = "\"H\"";
		
		//make a file with a string
		writeFile(filename, writeThis);
		//check for the token
		try{
		Tokenizer tokenizer = new Tokenizer(filename);
		Token stringToken = tokenizer.getToken();
		assertEquals(Type.tstring , stringToken.getType());
		assertEquals("\"H\"" , stringToken.getValue());
		}catch(IOException err){
			System.out.println("Could't open file");
		}
	}
	
	@Test
	public void testKeywordIdentifier() 
	{
		String testfile = "testKeyWordIdentifier.txt";
		writeFile(testfile,"while _hey true false");//make a file with a keyword 
		Tokenizer tokenizer = null;
		try {
			tokenizer = new Tokenizer(testfile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Token token1 = tokenizer.getToken();//check for the token
		Token token2 = tokenizer.getToken();
		assertEquals(Type.tstmts,token1.getType());
		assertEquals(Type.tid,token2.getType());
		assertEquals("while",token1.getValue());
		assertEquals("0",token2.getValue());
		Token token = tokenizer.getToken();
		assertEquals(Type.tbool,token.getType());
		assertEquals("true", token.getValue());
		token = tokenizer.getToken();
		assertEquals(Type.tbool,token.getType());
		assertEquals("false", token.getValue());
		
	}
	
	@Test
	public void testOperators() throws IOException
	{
		String testFile = "testOperators.txt";//make a file with a keyword 
		writeFile(testFile,":=+*/%^=>>=<<=!=or-and");
		Tokenizer tokenizer = new Tokenizer(testFile);
		//check for the token
		ArrayList<Token> tokenList = new ArrayList<Token>();
		Token token = tokenizer.getToken();
		while(token.getType()!=Type.EOF){
			tokenList.add(token);
			token = tokenizer.getToken();
		}
		assertEquals(Type.tassign, tokenList.get(0).getType());
		assertEquals(":=",tokenList.get(0).getValue());
		assertEquals(Type.tbinop, tokenList.get(1).getType());
		assertEquals("+",tokenList.get(1).getValue());
		assertEquals(Type.tbinop, tokenList.get(2).getType());
		assertEquals("*",tokenList.get(2).getValue());
		assertEquals(Type.tbinop, tokenList.get(3).getType());
		assertEquals("/",tokenList.get(3).getValue());
		assertEquals(Type.tbinop, tokenList.get(4).getType());
		assertEquals("%",tokenList.get(4).getValue());
		assertEquals(Type.tbinop, tokenList.get(5).getType());
		assertEquals("^",tokenList.get(5).getValue());
		assertEquals(Type.tineq, tokenList.get(6).getType());
		assertEquals("=",tokenList.get(6).getValue());
		assertEquals(Type.tineq, tokenList.get(7).getType());
		assertEquals(">",tokenList.get(7).getValue());
		assertEquals(Type.tineq, tokenList.get(8).getType());
		assertEquals(">=",tokenList.get(8).getValue());
		assertEquals(Type.tineq, tokenList.get(9).getType());
		assertEquals("<",tokenList.get(9).getValue());
		assertEquals(Type.tineq, tokenList.get(10).getType());
		assertEquals("<=",tokenList.get(10).getValue());
		assertEquals(Type.tineq, tokenList.get(11).getType());
		assertEquals("!=",tokenList.get(11).getValue());
		assertEquals(Type.tbinop, tokenList.get(12).getType());
		assertEquals("or",tokenList.get(12).getValue());
		assertEquals(Type.tminus, tokenList.get(13).getType());
		assertEquals("minus", tokenList.get(13).getValue());
		assertEquals(Type.tbinop, tokenList.get(14).getType());
		assertEquals("and",tokenList.get(14).getValue());
	}
	
	@Test
	public void testIntegers() throws IOException
	{
		String intfile = "testInteger.txt";//make a file with a keyword 
		writeFile(intfile, "6 1234");
		Tokenizer tokenizer = new Tokenizer(intfile);
		Token token1 = tokenizer.getToken();//check for the token
		Token token2 = tokenizer.getToken();
		assertEquals(Type.tint,token1.getType());
		assertEquals("6",token1.getValue());
		assertEquals(Type.tint, token2.getType());
		assertEquals("1234", token2.getValue());
	}
	
	@Test
	public void testFloats() throws IOException
	{
		//make a file with a keyword 
		String floatfile = "testFloats.txt";
		writeFile(floatfile,"1234.1234E-555.1 1234. .3e5 ");
		Tokenizer tokenizer = new Tokenizer(floatfile);
		//check for the token
		ArrayList<Token> tokenList = new ArrayList<Token>();
		Token tokenAdd = tokenizer.getToken();
		while(tokenAdd.getType()!=Type.EOF){
			tokenList.add(tokenAdd);
			tokenAdd = tokenizer.getToken();
		}
		Token token = tokenList.get(0);
		assertEquals(Type.tfloat, token.getType());
		assertEquals("1234.1234E-555",token.getValue());
		//assertEquals()
		token = tokenList.get(1);
		assertEquals(Type.tfloat, token.getType());
		assertEquals(".1",token.getValue());
		//assertEquals()
		token = tokenList.get(2);
		assertEquals(Type.tfloat, token.getType());
		assertEquals("1234.",token.getValue());
		//assertEquals()
		token = tokenList.get(3);
		assertEquals(Type.tfloat, token.getType());
		assertEquals(".3e5",token.getValue());
		//assertEquals()
		token = tokenAdd;
		assertEquals(Type.EOF, token.getType());
	}
	
	@Test
	public void testFloatsFail() throws IOException
	{
		//make a file with a keyword 
		String floatfile = "testFloatsFail.txt";
		writeFile(floatfile,"1234.1234E-.1 1234. .3e5 ");
		Tokenizer tokenizer = new Tokenizer(floatfile);
		//check for the token
		ArrayList<Token> tokenList = new ArrayList<Token>();
		Token tokenAdd = tokenizer.getToken();
		while(tokenAdd.getType()!=Type.EOF){
			tokenList.add(tokenAdd);
			tokenAdd = tokenizer.getToken();
		}
		Token token = tokenList.get(0);
		assertEquals(Type.Error, token.getType());
		//assertEquals()
	}

}
