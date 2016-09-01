import java.util.*;
public class Node {
	public static ArrayList<Token> tokenList;//holds the array list for all productions to use
	public static SortedMap<Integer,Type> variableList;
	public boolean success; //shows pass/fail of production
	public int tokenCount; //shows the number of tokens this production has taken
}
