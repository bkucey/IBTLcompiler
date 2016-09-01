

public class S_ extends Node{
	S s;
	S_ s_; 
	public S_(int index) {
		success = false;
		tokenCount = 0;
		s=null; s_=null;
		
		//first try S production 
		s = S.produce(index+tokenCount);
		if (!s.success){
			//return empty string
			s=null;
			success = true;
			return; 
		}
		tokenCount += s.tokenCount;
		
		//now check Sprime
		s_ = new S_(index+tokenCount);
		if (!s_.success){
			//return empty string
			s=null;
			s_=null;
			tokenCount = 0;
			success = true;
			return;
		}
		tokenCount += s_.tokenCount;
		success = true;
		return;
	}
	public String printNode() {
		String output = "";
		if(s != null){
			output += " "+s.printNode();
		}
		if (s_ != null){
			output += " "+s_.printNode();
		}
		return output;
	}

}
