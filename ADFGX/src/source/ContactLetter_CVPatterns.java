package source;

public class ContactLetter_CVPatterns {

	//public int cipherTextLength = 0;
	public int cipherTextPrevStart = 0;
	public int cipherTextStart = 0;
	public int cipherTextEnd = 0;
	
	public String cipherPattern = "";
	
	public static String vowel = "aeiouy";
	public static String consonant = "bcdfghjklmnpqrstvwxz";
	
	public boolean testPattern()//String runningString)
	{
		String word = cipherPattern.split("_")[cipherPattern.split("_").length - 1];
		
		switch(word.length())
		{
		case 1: // V
			if(vowelCheck(word, 0))
			{
				return true;
			}
			break;
		case 2: // V C || C V || C C
			if(vowelCheck(word, 0) && consonantCheck(word, 1))
			{
				return true;
			}
			else if(consonantCheck(word, 0) && vowelCheck(word, 1))
			{
				return true;
			}
			else if(consonantCheck(word, 0) && vowelCheck(word, 1))
			{
				return true;
			}
			break;
		case 3: // C V C || C V V || V C C || V C V TODO
			if(consonantCheck(word, 0) && vowelCheck(word, 1))
			{
				return true;
			}
			else if(consonantCheck(word, 0) && consonantCheck(word, 1))
			{
				return true;
			}
			else if(vowelCheck(word, 0) && consonantCheck(word, 1))
			{
				return true;
			}
			else if(vowelCheck(word, 0) && vowelCheck(word, 1) && consonantCheck(word, 2))
			{
				return true;
			}
			break;
		case 4: // C V C V || C V V C || C V C C || C C V C TODO
			if(consonantCheck(word, 0))
			{
				if(vowelCheck(word, 1))
				{
					if(consonantCheck(word, 2) && vowelCheck(word, 3))
					{
						return true;
					}
					else if(vowelCheck(word, 2) && consonantCheck(word, 3))
					{
						return true;
					}
					else if(consonantCheck(word, 2) && consonantCheck(word, 3))
					{
						return true;
					}
				}
				else if(consonantCheck(word, 1) && vowelCheck(word, 2) && consonantCheck(word, 3))
				{
					return true;
				}
			}
			break;
		case 5: // C C V C _ ..... TODO
			if(consonantCheck(word, 0))
			{
				if((consonantCheck(word, 1) && vowelCheck(word, 2)) || (vowelCheck(word, 1) && consonantCheck(word, 2)))
				{
					if(consonantCheck(word, 3))
					{
						return true;
					}
					else if(vowelCheck(word, 3) && consonantCheck(word, 4))
					{
						return true;
					}
				}
				else if(vowelCheck(word, 1) && vowelCheck(word, 2) && consonantCheck(word, 3) && consonantCheck(word, 4))
				{
					return true;
				}
			}
			break;
		case 6: // C V C _ _ C TODO 
			if(consonantCheck(word, 0) && vowelCheck(word, 1) && consonantCheck(word, 2))
			{
				if(consonantCheck(word, 3))
				{
					if((consonantCheck(word, 4) && vowelCheck(word, 5)) || (vowelCheck(word, 4) && consonantCheck(word, 5)))
					{
						return true;
					}
				}
				else if(vowelCheck(word, 3))
				{
					if(consonantCheck(word, 4))
					{
						return true;
					}
					else if(vowelCheck(word, 4) && consonantCheck(word, 5))
					{
						return true;
					}
				}
			}
			else if(consonantCheck(word, 1) && consonantCheck(word, 4) && consonantCheck(word, 5))
			{
				if(vowelCheck(word, 0) && consonantCheck(word, 2) && vowelCheck(word, 3))
				{
					return true;
				}
				else if(consonantCheck(word, 0) && vowelCheck(word, 2) && consonantCheck(word, 3))
				{
					return true;
				}
			}
			break;
		case 7: // C V C C _ _ C TODO
			if(consonantCheck(word, 0) && vowelCheck(word, 1) && consonantCheck(word, 2) && consonantCheck(word, 3) && 
					consonantCheck(word, word.length() - 1))
			{
				return true;
			}
			break;
		default:
			//System.out.println("ERROR");
			return false;
		}
		
		return false;
	}
	
	public static boolean vowelCheck(String letter, int begin)
	{
		if(vowel.contains(letter.substring(begin, begin + 1)))
		{
			return true;
		}
		
		return false;
	}
	
	public static boolean consonantCheck(String letter, int begin)
	{
		if(consonant.contains(letter.substring(begin, begin + 1)))
		{
			return true;
		}
		
		return false;
	}
}
