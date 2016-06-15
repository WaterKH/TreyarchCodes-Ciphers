package source;

public class ContactLetter_CVPatterns {

	//public int cipherTextLength = 0;
	public int cipherTextStart = 0;
	public int cipherTextEnd = 0;
	
	public String vowel = "aeiou";
	public String consonant = "bcdfghjklmnpqrstvwxyz";
	
	public boolean testPattern(String runningString)
	{
		System.out.println("RUN: " + runningString + " " + cipherTextStart + " " + cipherTextEnd);
		String word = runningString.substring(cipherTextStart, cipherTextEnd);
		
		switch(word.length())
		{
		case 1: // V
			if(vowelCheck(word, 0))
			{
				return true;
			}
			break;
		case 2: // V C
			if(vowelCheck(word, 0) && consonantCheck(word, 1))
			{
				return true;
			}
			break;
		case 3: // C C _
			if(consonantCheck(word, 0) && consonantCheck(word, 1))
			{
				return true;
			}
			break;
		case 4: // C V _ C
			if(consonantCheck(word, 0) && vowelCheck(word, 1) && consonantCheck(word, 3))
			{
				return true;
			}
			break;
		case 5: // C C V C C
			if(consonantCheck(word, 0) && consonantCheck(word, 1) && vowelCheck(word, 2) && consonantCheck(word, 3) && consonantCheck(word, 4))
			{
				return true;
			}
			break;
		case 6: // C V C _ _ C
			if(consonantCheck(word, 0) && vowelCheck(word, 1) && consonantCheck(word, 2) && consonantCheck(word, word.length() - 1))
			{
				return true;
			}
			break;
		case 7: // C V C C _ _ C
			if(consonantCheck(word, 0) && vowelCheck(word, 1) && consonantCheck(word, 2) && consonantCheck(word, 3) && 
					consonantCheck(word, word.length() - 1))
			{
				return true;
			}
			break;
		default:
			return false;
		}
		
		return false;
	}
	
	private boolean vowelCheck(String letter, int begin)
	{
		if(vowel.contains(letter.substring(begin, begin + 1)))
		{
			return true;
		}
		
		return false;
	}
	
	private boolean consonantCheck(String letter, int begin)
	{
		if(consonant.contains(letter.substring(begin, begin + 1)))
		{
			return true;
		}
		
		return false;
	}
}