import static org.junit.Assert.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;


public class GforthTest {

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
	
	/*
	@Test
	public void testT() throws IOException {
		String filename = "testT";
		String writeThis = "[5]";
		writeFile(filename,writeThis);
		Tokenizer tokenizer = new Tokenizer(filename);
		Token tokenAdd = tokenizer.getToken();
		ArrayList<Token> tokenArray = new ArrayList<Token>();
		while(tokenAdd.getType()!=Type.EOF){
			tokenArray.add(tokenAdd);
		}
		tokenArray.add()
				
	}*/
	
	@Test
	public void testGforthTest() throws IOException {
		/*String filename = "testGforthTest";
		String writeThis = "[[let [[x int][c int]]][:= x [-1]][:= c 10][while [> c 0][:= x [-x]][stdout x][:= c [- c 1]]]]";
		writeFile(filename,writeThis);*/
		Tokenizer tokenizer = new Tokenizer("proftest.in");
		ArrayList<Token> tokenList=fillTokenList(tokenizer);
		Node.tokenList = tokenList;
		T t = new T();
		String output = t.printNode();
		assertEquals("",output);
		assertEquals(true , t.success);
		//assertEquals(true, oper.rBracket.checkRBracket());
		//assertEquals(true, t.lBracket.checkLBracket());
		//assertEquals(false, t.oper1.isName);
		//assertEquals(true, oper.oper2.isConstant);
		//assertEquals(Type.tassign, t.ops.getType());
		//assertEquals(5, oper.tokenCount);
	}
	
	@Test
	public void testGforthAssign() throws IOException {
		String filename = "testGforthAssign";
		String writeThis = "[ [let [[x int]] ][:= x [-1]][stdout [+ x 5]] ]";
		writeFile(filename,writeThis);
		Tokenizer tokenizer = new Tokenizer(filename);
		ArrayList<Token> tokenList=fillTokenList(tokenizer);
		Node.tokenList = tokenList;
		T t = new T();
		String output = t.printNode();
		assertEquals("",output);
		assertEquals(true, t.success);
		//assertEquals(true, oper.rBracket.checkRBracket());
		//assertEquals(true, t.lBracket.checkLBracket());
		//assertEquals(false, t.oper1.isName);
		//assertEquals(true, oper.oper2.isConstant);
		//assertEquals(Type.tassign, t.ops.getType());
		//assertEquals(5, oper.tokenCount);
	}
	
	@Test
	public void testGforthAssign2() throws IOException {
		String filename = "testGforthAssign2";
		String writeThis = "[ [let [[x float]] ][:= x [-1]][stdout [+ x 5]] ]";
		writeFile(filename,writeThis);
		Tokenizer tokenizer = new Tokenizer(filename);
		ArrayList<Token> tokenList=fillTokenList(tokenizer);
		Node.tokenList = tokenList;
		T t = new T();
		String output = t.printNode();
		assertEquals("",output);
		assertEquals(true, t.success);
		//assertEquals(true, oper.rBracket.checkRBracket());
		//assertEquals(true, t.lBracket.checkLBracket());
		//assertEquals(false, t.oper1.isName);
		//assertEquals(true, oper.oper2.isConstant);
		//assertEquals(Type.tassign, t.ops.getType());
		//assertEquals(5, oper.tokenCount);
	}
	
	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
