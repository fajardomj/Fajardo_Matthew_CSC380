package server;

import java.io.Serializable;
import java.util.Random;

public class WordsLogic implements Serializable, ReflectionParent {

	private String[] words = {"Hi","hello","orange","computer"};
	private static WordsLogic INSTANCE = null;
	public WordsLogic() {
		
	}
	
	public String getRandomWord(String blah){
		return "Haha this was your word " + blah;
	}

}
