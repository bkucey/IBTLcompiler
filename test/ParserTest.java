import static org.junit.Assert.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import org.junit.Test;


public class ParserTest {

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
	public void testAssignFail() throws IOException {
		String filename = "testAssign";
		String writeThis = "[:= 5 asdf]";
		writeFile(filename,writeThis);
		Tokenizer tokenizer = new Tokenizer(filename);
		ArrayList<Token> tokenList=fillTokenList(tokenizer);
		Node.tokenList = tokenList;
		Oper oper = Oper.produce(0);
		assertEquals(false, oper.success);
		//assertEquals(true, oper.rBracket.checkRBracket());
		assertEquals(true, oper.lBracket.checkLBracket());
		assertEquals(false, oper.oper1.isName);
		//assertEquals(true, oper.oper2.isConstant);
		assertEquals(Type.tassign, oper.ops.getType());
		//assertEquals(5, oper.tokenCount);
	}
	
	@Test
	public void testAssign() throws IOException {
		String filename = "testAssign";
		String writeThis = "[:= asdf 5]";
		writeFile(filename,writeThis);
		Tokenizer tokenizer = new Tokenizer(filename);
		ArrayList<Token> tokenList=fillTokenList(tokenizer);
		Node.tokenList = tokenList;
		Oper oper = Oper.produce(0);
		assertEquals(true, oper.success);
		assertEquals(true, oper.rBracket.checkRBracket());
		assertEquals(true, oper.lBracket.checkLBracket());
		assertEquals(true, oper.oper1.isName);
		assertEquals(true, oper.oper2.isConstant);
		assertEquals(Type.tassign, oper.ops.getType());
		assertEquals(5, oper.tokenCount);
	}
	
	@Test
	public void testBinop() throws IOException {
		String filename = "testOper1";
		String writeThis = "[+ 5 asdf]";
		writeFile(filename,writeThis);
		Tokenizer tokenizer = new Tokenizer(filename);
		ArrayList<Token> tokenList=fillTokenList(tokenizer);
		Node.tokenList = tokenList;
		Oper oper = Oper.produce(0);
		assertEquals(true, oper.success);
		assertEquals(true, oper.rBracket.checkRBracket());
		assertEquals(true, oper.lBracket.checkLBracket());
		assertEquals(true, oper.oper1.isConstant);
		assertEquals(true, oper.oper2.isName);
		assertEquals(Type.tbinop, oper.ops.getType());
		assertEquals(5, oper.tokenCount);
	}
	
	@Test
	public void testUnop() throws IOException {
		String filename = "testOper1";
		String writeThis = "[sin 5]";
		writeFile(filename,writeThis);
		Tokenizer tokenizer = new Tokenizer(filename);
		ArrayList<Token> tokenList=fillTokenList(tokenizer);
		Node.tokenList = tokenList;
		Oper oper = Oper.produce(0);
		assertEquals(true, oper.success);
		assertEquals(true, oper.rBracket.checkRBracket());
		assertEquals(true, oper.lBracket.checkLBracket());
		assertEquals(true, oper.oper1.isConstant);
		assertEquals(null, oper.oper2);
		assertEquals(Type.tunop, oper.ops.getType());
		assertEquals(4, oper.tokenCount);
	}
	
	@Test
	public void testMinus1() throws IOException {
		String filename = "testOper1";
		String writeThis = "[- 5]";
		writeFile(filename,writeThis);
		Tokenizer tokenizer = new Tokenizer(filename);
		ArrayList<Token> tokenList=fillTokenList(tokenizer);
		Node.tokenList = tokenList;
		Oper oper = Oper.produce(0);
		assertEquals(true, oper.success);
		assertEquals(true, oper.rBracket.checkRBracket());
		assertEquals(true, oper.lBracket.checkLBracket());
		assertEquals(true, oper.oper1.isConstant);
		assertEquals(null, oper.oper2);
		assertEquals(Type.tminus, oper.ops.getType());
		assertEquals(4, oper.tokenCount);
	}
	
	@Test
	public void testMinus2() throws IOException {
		String filename = "testOper1";
		String writeThis = "[- 5 6]";
		writeFile(filename,writeThis);
		Tokenizer tokenizer = new Tokenizer(filename);
		ArrayList<Token> tokenList=fillTokenList(tokenizer);
		Node.tokenList = tokenList;
		Oper oper = Oper.produce(0);
		assertEquals(true, oper.success);
		assertEquals(true, oper.rBracket.checkRBracket());
		assertEquals(true, oper.lBracket.checkLBracket());
		assertEquals(true, oper.oper1.isConstant);
		assertEquals(true, oper.oper2.isConstant);
		assertEquals(Type.tminus, oper.ops.getType());
		assertEquals(5, oper.tokenCount);
	}
	
	@Test 
	public void testStdout1() throws IOException{
		String filename = "testStdout.txt";
		String writeThis = "[[stdout 5]";
		writeFile(filename,writeThis);
		Tokenizer tokenizer = new Tokenizer(filename);
		ArrayList<Token> tokenList = fillTokenList(tokenizer);
		Node.tokenList = tokenList;
		Printstmts stmts = new Printstmts(1);
		assertEquals(true, stmts.rBracket.checkRBracket());
		assertEquals(true, stmts.lBracket.checkLBracket());
		assertEquals(true, stmts.oper.isConstant);
		assertEquals(Type.tstmts, stmts.stmts.getType());
		assertEquals("stdout", stmts.stmts.getValue());
		assertEquals(4,stmts.tokenCount);
	}
	
	@Test
	public void testIfstmts() throws IOException{
		String filename = "testIfstmts.txt";
		String writeThis = "[[if 5 [stdout \"haha\"]]";
		writeFile(filename,writeThis);
		Tokenizer tokenizer = new Tokenizer(filename);
		ArrayList<Token> tokenList = fillTokenList(tokenizer);
		Node.tokenList = tokenList;
		Ifstmts stmts = new Ifstmts(1);
		assertEquals(true, stmts.rBracket.checkRBracket());
		assertEquals(true, stmts.lBracket.checkLBracket());
		assertEquals(true, Constant.class.isInstance(stmts.expr1));
		assertEquals(Type.tstmts, stmts.stmts.getType());
		assertEquals("if", stmts.stmts.getValue());
		assertEquals(8,stmts.tokenCount);
	}
	
	@Test
	public void testWhilestmts() throws IOException{
		String filename = "testWhilestmts.txt";
		String writeThis = "[[while 5 [stdout \"haha\"] asdf]";
		writeFile(filename,writeThis);
		Tokenizer tokenizer = new Tokenizer(filename);
		ArrayList<Token> tokenList = fillTokenList(tokenizer);
		Node.tokenList = tokenList;
		Whilestmts stmts = new Whilestmts(1);
		assertEquals(true, stmts.rBracket.checkRBracket());
		assertEquals(true, stmts.lBracket.checkLBracket());
		assertEquals(true, Constant.class.isInstance(stmts.expr1));
		assertEquals(Type.tstmts, stmts.stmts.getType());
		assertEquals("while", stmts.stmts.getValue());
		assertEquals(9,stmts.tokenCount);
	}
	
	@Test
	public void testVarlist() throws IOException{
		String filename = "testVarlist.txt";
		String writeThis = "[[asdf bool]";
		writeFile(filename,writeThis);
		Tokenizer tokenizer = new Tokenizer(filename);
		ArrayList<Token> tokenList = fillTokenList(tokenizer);
		Node.tokenList = tokenList;
		Varlist stmts = new Varlist(1);
		assertEquals(true, stmts.rBracket.checkRBracket());
		assertEquals(true, stmts.lBracket.checkLBracket());
		assertEquals(true, Name.class.isInstance(stmts.name));
		assertEquals(true, VarType.class.isInstance(stmts.type));
		assertEquals(4,stmts.tokenCount);
	}
	
	@Test
	public void testLetstmts() throws IOException{
		String filename = "testLetstmts.txt";
		String writeThis = "[[let[[asdf bool][zxcv string]]]";
		writeFile(filename,writeThis);
		Tokenizer tokenizer = new Tokenizer(filename);
		ArrayList<Token> tokenList = fillTokenList(tokenizer);
		Node.tokenList = tokenList;
		Letstmts stmts = new Letstmts(1);
		assertEquals(true, stmts.success);
		assertEquals(true, stmts.innerLBracket.checkLBracket());
		assertEquals(true, stmts.innerRBracket.checkRBracket());
		assertEquals(true, stmts.rBracket.checkRBracket());
		assertEquals(true, stmts.lBracket.checkLBracket());
		assertEquals(true, Varlist.class.isInstance(stmts.var1));
		assertEquals(Type.tstmts, stmts.stmts.getType());
		assertEquals(13,stmts.tokenCount);
	}
	
	@Test
	public void testSprod1() throws IOException{
		String filename = "testSprod1.txt";
		String writeThis = "haha[][stdout 5]";
		writeFile(filename,writeThis);
		Tokenizer tokenizer = new Tokenizer(filename);
		ArrayList<Token> tokenList = fillTokenList(tokenizer);
		Node.tokenList = tokenList;
		S s = S.produce(1);
		assertEquals(S.Sprod.prod1,s.myProd);
		assertEquals(true, s.success);
		assertEquals(true, s.rBracket.checkRBracket());
		assertEquals(true, s.lBracket.checkLBracket());
		assertEquals(null, s.s);
		assertEquals(true, s.s_.success);
		assertEquals(null, s.expr);
		assertEquals(6,s.tokenCount);
	}
	
	@Test
	public void testSprod2() throws IOException{
		String filename = "testSprod2.txt";
		String writeThis = "haha[ [5] [let [[asdf bool][zxcv string]] ] ]";
		writeFile(filename,writeThis);
		Tokenizer tokenizer = new Tokenizer(filename);
		ArrayList<Token> tokenList = fillTokenList(tokenizer);
		Node.tokenList = tokenList;
		S s = S.produce(2);
		assertEquals(true, s.success);
		assertEquals(true, s.rBracket.checkRBracket());
		assertEquals(true, s.lBracket.checkLBracket());
		assertEquals(true, s.s.success);
		assertEquals(true, Constant.class.isInstance(s.s.expr));//should hold the 5
		assertEquals(true, s.s_.success);
		assertEquals(true, Letstmts.class.isInstance(s.s_.s.expr));
		assertEquals(null, s.expr);
		assertEquals(S.Sprod.prod2,s.myProd);
		assertEquals(16,s.tokenCount);
	}
	
	@Test
	public void testSprod3() throws IOException{
		String filename = "testSprod3.txt";
		String writeThis = "haha[let[[asdf bool][zxcv string]]]";
		writeFile(filename,writeThis);
		Tokenizer tokenizer = new Tokenizer(filename);
		ArrayList<Token> tokenList = fillTokenList(tokenizer);
		Node.tokenList = tokenList;
		S s = S.produce(1);
		assertEquals(S.Sprod.prod3, s.myProd);
		assertEquals(true, s.success);
		assertEquals(null, s.rBracket);
		assertEquals(null, s.lBracket);
		assertEquals(null, s.s);
		assertEquals(true, s.s_.success);
		assertEquals(true, Letstmts.class.isInstance(s.expr));
		assertEquals(13,s.tokenCount);
	}
	/*
	@Test
	public void testS_prod1() throws IOException{
		String filename = "testS_prod1.txt";
		String writeThis = "haha[5][stdout 5]";
		writeFile(filename,writeThis);
		Tokenizer tokenizer = new Tokenizer(filename);
		ArrayList<Token> tokenList = fillTokenList(tokenizer);
		Node.tokenList = tokenList;
		S s = S.produce(1);
		assertEquals(S.Sprod.prod1,s.myProd);
		assertEquals(true, s.success);
		assertEquals(true, s.rBracket.checkRBracket());
		assertEquals(true, s.lBracket.checkLBracket());
		assertEquals(null, s.s);
		assertEquals(true, s.s_.success);
		assertEquals(null, s.expr);
		assertEquals(6,s.tokenCount);
	}
	*/
	
	@Test
	public void testT() throws IOException{
		String filename = "testT.txt";
		String writeThis = "[[stdout 5]]";
		writeFile(filename,writeThis);
		Tokenizer tokenizer = new Tokenizer(filename);
		ArrayList<Token> tokenList = fillTokenList(tokenizer);
		Node.tokenList = tokenList;
		T t = new T();
		assertEquals(true, t.success);
		assertEquals(true, t.rBracket.checkRBracket());
		assertEquals(true, t.lBracket.checkLBracket());
		assertNotEquals(null, t.s);
		assertEquals(Type.EOF,t.EOF.getType());
		assertEquals(true, t.s.success);
		assertEquals(S.Sprod.prod3,t.s.myProd);
		assertEquals(6,t.tokenCount);
	} 
	
	@Test
	public void testT2() throws IOException{
		String filename = "testT2.txt";
		String writeThis = "[[5]]";
		writeFile(filename,writeThis);
		Tokenizer tokenizer = new Tokenizer(filename);
		ArrayList<Token> tokenList = fillTokenList(tokenizer);
		Node.tokenList = tokenList;
		T t = new T();
		assertEquals(true, t.success);
		assertEquals(true, t.rBracket.checkRBracket());
		assertEquals(true, t.lBracket.checkLBracket());
		assertNotEquals(null, t.s);
		assertEquals(Type.EOF,t.EOF.getType());
		assertEquals(true, t.s.success);
		assertEquals(S.Sprod.prod2,t.s.myProd);
		assertEquals(5,t.tokenCount);
	} 
	
	@Test
	public void testTFail() throws IOException{
		String filename = "testTFail.txt";
		String writeThis = "[[stdout 5]";
		writeFile(filename,writeThis);
		Tokenizer tokenizer = new Tokenizer(filename);
		ArrayList<Token> tokenList = fillTokenList(tokenizer);
		Node.tokenList = tokenList;
		T t = new T();
		assertEquals(false, t.success);
		//assertEquals(null, t.rBracket.checkRBracket());
		assertEquals(true, t.lBracket.checkLBracket());
		assertNotEquals(null, t.s);
		assertEquals(null,t.EOF);
		assertEquals(true, t.s.success);
		//assertEquals(S.Sprod.prod3,t.s.myProd);
		//assertEquals(6,t.tokenCount);
	} 

}
