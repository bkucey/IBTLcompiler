import static org.junit.Assert.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;


public class ConvertTest {

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
	
	@Test
	public void testIntFloatSubtraction() throws IOException{
		String filename = "testIntFloatSubtraction.txt";
		String writeThis = "[[- 5 5e0]]";
		writeFile(filename,writeThis);
		Tokenizer tokenizer = new Tokenizer(filename);
		ArrayList<Token> tokenList = fillTokenList(tokenizer);
		Node.tokenList = tokenList;
		Oper oper = Oper.produce(1);
		String got = oper.printNode();
		System.out.println("Got:"+got);
		assertEquals("5 5e0 +",got);
	} 
	
	@Test
	public void testFloatIntModulus() throws IOException{
		String filename = "testFloatIntModulus.txt";
		String writeThis = "[[% 6.0 5]]";
		writeFile(filename,writeThis);
		Tokenizer tokenizer = new Tokenizer(filename);
		ArrayList<Token> tokenList = fillTokenList(tokenizer);
		Node.tokenList = tokenList;
		Oper oper = Oper.produce(1);
		String got = oper.printNode();
		System.out.println("testFloatIntModulus.txt");
		System.out.println("Got:"+got);
		assertEquals("5 5e0 +",got);
	} 
	
	@Test
	public void testFloatFloatAddition() throws IOException{
		String filename = "testFloatFloatAddition.txt";
		String writeThis = "[[+ 5e0 5e0]]";
		writeFile(filename,writeThis);
		Tokenizer tokenizer = new Tokenizer(filename);
		ArrayList<Token> tokenList = fillTokenList(tokenizer);
		Node.tokenList = tokenList;
		Oper oper = Oper.produce(1);
		String got = oper.printNode();
		System.out.println("Got:"+got);
		assertEquals("5 5e0 +",got);
	} 
	
	@Test
	public void testIntIntPower() throws IOException{
		String filename = "testIntIntPower.txt";
		String writeThis = "[[^ 5 2]]";
		writeFile(filename,writeThis);
		Tokenizer tokenizer = new Tokenizer(filename);
		ArrayList<Token> tokenList = fillTokenList(tokenizer);
		Node.tokenList = tokenList;
		Oper oper = Oper.produce(1);
		String got = oper.printNode();
		System.out.println("Got:"+got);
		assertEquals("5 5e0 +",got);
	} 
	
	@Test
	public void testFloatFloatPower() throws IOException{
		String filename = "testFloatFloatPower.txt";
		String writeThis = "[[^ 5e0 2e0]]";
		writeFile(filename,writeThis);
		Tokenizer tokenizer = new Tokenizer(filename);
		ArrayList<Token> tokenList = fillTokenList(tokenizer);
		Node.tokenList = tokenList;
		Oper oper = Oper.produce(1);
		String got = oper.printNode();
		System.out.println("Got:"+got);
		assertEquals("5 5e0 +",got);
	} 
	
	@Test
	public void testBooleanBooleanAnd() throws IOException{
		String filename = "testBooleanBooleanAnd.txt";
		String writeThis = "[[and true false]]";
		writeFile(filename,writeThis);
		Tokenizer tokenizer = new Tokenizer(filename);
		ArrayList<Token> tokenList = fillTokenList(tokenizer);
		Node.tokenList = tokenList;
		Oper oper = Oper.produce(1);
		String got = oper.printNode();
		System.out.println("Got:"+got);
		assertEquals("5 5e0 +",got);
	} 
	
	@Test
	public void testBooleanBooleanOr() throws IOException{
		String filename = "testBooleanBooleanOr.txt";
		String writeThis = "[[or false true]]";
		writeFile(filename,writeThis);
		Tokenizer tokenizer = new Tokenizer(filename);
		ArrayList<Token> tokenList = fillTokenList(tokenizer);
		Node.tokenList = tokenList;
		Oper oper = Oper.produce(1);
		String got = oper.printNode();
		System.out.println("Got:"+got);
		assertEquals("5 5e0 +",got);
	} 
	
	@Test
	public void testBooleanIntAnd() throws IOException{
		String filename = "testBooleanIntAnd.txt";
		String writeThis = "[[and 5 true]]";
		writeFile(filename,writeThis);
		Tokenizer tokenizer = new Tokenizer(filename);
		ArrayList<Token> tokenList = fillTokenList(tokenizer);
		Node.tokenList = tokenList;
		Oper oper = Oper.produce(1);
		String got = oper.printNode();
		System.out.println("Got:"+got);
		assertEquals("5 5e0 +",got);
	} 
	
	@Test
	public void testBooleanStringAnd() throws IOException{
		String filename = "testBooleanStringAnd.txt";
		String writeThis = "[[and true \"fake\"]]";
		writeFile(filename,writeThis);
		Tokenizer tokenizer = new Tokenizer(filename);
		ArrayList<Token> tokenList = fillTokenList(tokenizer);
		Node.tokenList = tokenList;
		Oper oper = Oper.produce(1);
		String got = oper.printNode();
		System.out.println("Got:"+got);
		assertEquals("5 5e0 +",got);
	} 
	
	@Test
	public void testFloatNegate() throws IOException{
		String filename = "testFloatNegate.txt";
		String writeThis = "[[- 5e0]]";
		writeFile(filename,writeThis);
		Tokenizer tokenizer = new Tokenizer(filename);
		ArrayList<Token> tokenList = fillTokenList(tokenizer);
		Node.tokenList = tokenList;
		Oper oper = Oper.produce(1);
		String got = oper.printNode();
		System.out.println("Got:"+got);
		assertEquals("5 5e0 +",got);
	} 
	
	@Test
	public void testIntNegate() throws IOException{
		String filename = "testIntNegate.txt";
		String writeThis = "[[- 5]]";
		writeFile(filename,writeThis);
		Tokenizer tokenizer = new Tokenizer(filename);
		ArrayList<Token> tokenList = fillTokenList(tokenizer);
		Node.tokenList = tokenList;
		Oper oper = Oper.produce(1);
		String got = oper.printNode();
		System.out.println("Got:"+got);
		assertEquals("5 5e0 +",got);
	} 
	
	@Test
	public void testBooleanNegate () throws IOException{
		String filename = "testBooleanNegate.txt";
		String writeThis = "[[not true]]";
		writeFile(filename,writeThis);
		Tokenizer tokenizer = new Tokenizer(filename);
		ArrayList<Token> tokenList = fillTokenList(tokenizer);
		Node.tokenList = tokenList;
		Oper oper = Oper.produce(1);
		String got = oper.printNode();
		System.out.println("Got:"+got);
		assertEquals("5 5e0 +",got);
	} 
	
	@Test
	public void testStringNegate() throws IOException{
		String filename = "testStringNegate.txt";
		String writeThis = "[[- \"hi\"]]";
		writeFile(filename,writeThis);
		Tokenizer tokenizer = new Tokenizer(filename);
		ArrayList<Token> tokenList = fillTokenList(tokenizer);
		Node.tokenList = tokenList;
		Oper oper = Oper.produce(1);
		String got = oper.printNode();
		System.out.println("Got:"+got);
		assertEquals("5 5e0 +",got);
	} 
	
	@Test
	public void testIntCompare() throws IOException{
		String filename = "testIntCompare.txt";
		String writeThis = "[[!= 5 5]]";
		writeFile(filename,writeThis);
		Tokenizer tokenizer = new Tokenizer(filename);
		ArrayList<Token> tokenList = fillTokenList(tokenizer);
		Node.tokenList = tokenList;
		Oper oper = Oper.produce(1);
		String got = oper.printNode();
		System.out.println("Got:"+got);
		assertEquals("5 5e0 +",got);
	} 
	
	@Test
	public void testFloatCompare() throws IOException{
		String filename = "testFloatCompare.txt";
		String writeThis = "[[!= 5e0 5e0]]";
		writeFile(filename,writeThis);
		Tokenizer tokenizer = new Tokenizer(filename);
		ArrayList<Token> tokenList = fillTokenList(tokenizer);
		Node.tokenList = tokenList;
		Oper oper = Oper.produce(1);
		String got = oper.printNode();
		System.out.println("Got:"+got);
		assertEquals("5 5e0 +",got);
	} 
	
	@Test
	public void testPostOrderTraversal() throws IOException{
		String filename = "testT.txt";
		String writeThis = "[[+ 5 5e0]]";
		writeFile(filename,writeThis);
		Tokenizer tokenizer = new Tokenizer(filename);
		ArrayList<Token> tokenList = fillTokenList(tokenizer);
		Node.tokenList = tokenList;
		Oper oper = Oper.produce(1);
		String got = oper.printNode();
		System.out.println("Got:"+got);
		assertEquals("5 5e0 +",got);
	} 
	
	@Test
	public void testStringConcat() throws IOException{
		String filename = "testConcat.txt";
		String writeThis = "[[+ \"howdy\" \" there\"]]";
		writeFile(filename,writeThis);
		Tokenizer tokenizer = new Tokenizer(filename);
		ArrayList<Token> tokenList = fillTokenList(tokenizer);
		Node.tokenList = tokenList;
		Oper oper = Oper.produce(1);
		String got = oper.printNode();
		System.out.println("Got:"+got);
		assertEquals("5 5e0 +",got);
	} 
	
	@Test
	public void testT() throws IOException{
		String filename = "testT.txt";
		String writeThis = "[[+ \"howdy\" \" there\"]]";
		writeFile(filename,writeThis);
		Tokenizer tokenizer = new Tokenizer(filename);
		ArrayList<Token> tokenList = fillTokenList(tokenizer);
		Node.tokenList = tokenList;
		T t = new T();
		String got = t.printNode();
		System.out.println("Got:"+got);
		assertEquals("5 5e0 +",got);
	}
	
	@Test
	public void testIfThenElse() throws IOException{
		String filename = "testIfThenElse.txt";
		String writeThis = "[[if [> -1234 -2] [+ 5 15] [- 5 4]]]";
		writeFile(filename,writeThis);
		Tokenizer tokenizer = new Tokenizer(filename);
		ArrayList<Token> tokenList = fillTokenList(tokenizer);
		Node.tokenList = tokenList;
		T t = new T();
		String got = t.printNode();
		System.out.println("Got:"+got);
		assertEquals("5 5e0 +",got);
	}
	
	@Test
	public void testIfThen() throws IOException{
		String filename = "testIfThen.txt";
		String writeThis = "[[if [>= 5. -2] [^ 2. 6]]]";
		writeFile(filename,writeThis);
		Tokenizer tokenizer = new Tokenizer(filename);
		ArrayList<Token> tokenList = fillTokenList(tokenizer);
		Node.tokenList = tokenList;
		T t = new T();
		String got = t.printNode();
		System.out.println("Got:"+got);
		assertEquals("5 5e0 +",got);
	}
	
	@Test
	public void testIfThenTrue() throws IOException{
		String filename = "testIfThen.txt";
		String writeThis = "[[if true [^ 2. 6]]]";
		writeFile(filename,writeThis);
		Tokenizer tokenizer = new Tokenizer(filename);
		ArrayList<Token> tokenList = fillTokenList(tokenizer);
		Node.tokenList = tokenList;
		T t = new T();
		String got = t.printNode();
		System.out.println("Got:"+got);
		assertEquals("5 5e0 +",got);
	}
	
	@Test
	public void testIfThenFail() throws IOException{
		String filename = "testIfThen.txt";
		String writeThis = "[[if [+ 5. -2] [^ 2. 6]]]";
		writeFile(filename,writeThis);
		Tokenizer tokenizer = new Tokenizer(filename);
		ArrayList<Token> tokenList = fillTokenList(tokenizer);
		Node.tokenList = tokenList;
		T t = new T();
		String got = t.printNode();
		System.out.println("Got:"+got);
		assertEquals("5 5e0 +",got);
	}
	
	@Test
	public void testBig() throws IOException{
		String filename = "testIfThen.txt";
		String writeThis = "[[% [+ 5. -2] [^ 2. 6]]]";
		writeFile(filename,writeThis);
		Tokenizer tokenizer = new Tokenizer(filename);
		ArrayList<Token> tokenList = fillTokenList(tokenizer);
		Node.tokenList = tokenList;
		T t = new T();
		String got = t.printNode();
		System.out.println("Got:"+got);
		assertEquals("5 5e0 +",got);
	}
	
	@Test
	public void testBig2() throws IOException{
		String filename = "testIfThen.txt";
		String writeThis = "[[and [> 5. -2] true]]";
		writeFile(filename,writeThis);
		Tokenizer tokenizer = new Tokenizer(filename);
		ArrayList<Token> tokenList = fillTokenList(tokenizer);
		Node.tokenList = tokenList;
		T t = new T();
		String got = t.printNode();
		System.out.println("Got:"+got);
		assertEquals("5 5e0 +",got);
	}
	
	@Test
	public void testBig3() throws IOException{
		String filename = "testIfThen.txt";
		String writeThis = "[[- [% [+ 5 -2] [tan 5.]]]]";
		writeFile(filename,writeThis);
		Tokenizer tokenizer = new Tokenizer(filename);
		ArrayList<Token> tokenList = fillTokenList(tokenizer);
		Node.tokenList = tokenList;
		T t = new T();
		String got = t.printNode();
		System.out.println("Got:"+got);
		assertEquals("5 5e0 +",got);
	}
	
	@Test
	public void testBig4() throws IOException{
		String filename = "testIfThen.txt";
		String writeThis = "[[or[>= [- -7] 8.][not false]][- [% [^ 2 4] [* 7. 2]]]]";
		writeFile(filename,writeThis);
		Tokenizer tokenizer = new Tokenizer(filename);
		ArrayList<Token> tokenList = fillTokenList(tokenizer);
		Node.tokenList = tokenList;
		T t = new T();
		String got = t.printNode();
		System.out.println("Got:"+got);
		assertEquals("5 5e0 +",got);
	}
	
	@Test
	public void testBig5() throws IOException{
		String filename = "testIfThen.txt";
		String writeThis = "[[- [% [+ 5 -2] [tan 5]]]]";
		writeFile(filename,writeThis);
		Tokenizer tokenizer = new Tokenizer(filename);
		ArrayList<Token> tokenList = fillTokenList(tokenizer);
		Node.tokenList = tokenList;
		T t = new T();
		String got = t.printNode();
		System.out.println("Got:"+got);
		assertEquals("5 5e0 +",got);
	}
	
	@Test
	public void testE() throws IOException{
		String filename = "testT.txt";
		String writeThis = "[[%[> 5 6]5e0]]";
		writeFile(filename,writeThis);
		Tokenizer tokenizer = new Tokenizer(filename);
		ArrayList<Token> tokenList = fillTokenList(tokenizer);
		Node.tokenList = tokenList;
		T t = new T();
		String got = t.printNode();
		System.out.println("Got:"+got);
		assertEquals("5 5e0 +",got);
	}
	
	@Test
	public void testPrintString() throws IOException{
		String filename = "testIfThen.txt";
		String writeThis = "[[stdout \"hello world\"]]";
		writeFile(filename,writeThis);
		Tokenizer tokenizer = new Tokenizer(filename);
		ArrayList<Token> tokenList = fillTokenList(tokenizer);
		Node.tokenList = tokenList;
		T t = new T();
		String got = t.printNode();
		System.out.println("Got:"+got);
		assertEquals("5 5e0 +",got);
	}
	
	@Test
	public void testPrintStringConcat() throws IOException{
		String filename = "testIfThen.txt";
		String writeThis = "[[stdout [+\"hello\"\" world\"]]";
		writeFile(filename,writeThis);
		Tokenizer tokenizer = new Tokenizer(filename);
		ArrayList<Token> tokenList = fillTokenList(tokenizer);
		Node.tokenList = tokenList;
		T t = new T();
		String got = t.printNode();
		System.out.println("Got:"+got);
		assertEquals("5 5e0 +",got);
	}
	
	@Test
	public void testPrintInt() throws IOException{
		String filename = "testIfThen.txt";
		String writeThis = "[[stdout -5]]";
		writeFile(filename,writeThis);
		Tokenizer tokenizer = new Tokenizer(filename);
		ArrayList<Token> tokenList = fillTokenList(tokenizer);
		Node.tokenList = tokenList;
		T t = new T();
		String got = t.printNode();
		System.out.println("Got:"+got);
		assertEquals("5 5e0 +",got);
	}
	
	@Test
	public void testPrintFloat() throws IOException{
		String filename = "testIfThen.txt";
		String writeThis = "[[stdout -5.]]";
		writeFile(filename,writeThis);
		Tokenizer tokenizer = new Tokenizer(filename);
		ArrayList<Token> tokenList = fillTokenList(tokenizer);
		Node.tokenList = tokenList;
		T t = new T();
		String got = t.printNode();
		System.out.println("Got:"+got);
		assertEquals("5 5e0 +",got);
	}
	
	@Test
	public void testPrintBoolean() throws IOException{
		String filename = "testIfThen.txt";
		String writeThis = "[[stdout false]]";
		writeFile(filename,writeThis);
		Tokenizer tokenizer = new Tokenizer(filename);
		ArrayList<Token> tokenList = fillTokenList(tokenizer);
		Node.tokenList = tokenList;
		T t = new T();
		String got = t.printNode();
		System.out.println("Got:"+got);
		assertEquals("5 5e0 +",got);
	}

}
