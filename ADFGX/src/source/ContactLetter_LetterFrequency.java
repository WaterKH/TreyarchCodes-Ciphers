package source;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class ContactLetter_LetterFrequency {
	
	public static Map<String, Integer> cipher_letterFrequencies = new HashMap<String, Integer>();
	public static Map<String, Map<String, Integer>> cipher_ContactLetterFrequencies_After = new HashMap<String, Map<String, Integer>>();
	public static Map<String, Map<String, Integer>> cipher_ContactLetterFrequencies_Before = new HashMap<String, Map<String, Integer>>();
	
	public static void testFrequency(String letter)
	{
		
	}
	
	public static void createTableOfFrequencies(String cipherText)
	{
		// LETTER FREQUENCIES HERE
		
		for(int i = 0; i < cipherText.length(); ++i)
		{
			String letter = Character.toString(cipherText.charAt(i));
			
			if(!cipher_letterFrequencies.containsKey(letter))
			{
				cipher_letterFrequencies.put(letter, 1);
			}
			else
			{
				cipher_letterFrequencies.put(letter, cipher_letterFrequencies.get(letter) + 1);
			}
		}
		
		double totalLetters = cipherText.length();
		DecimalFormat df = new DecimalFormat("#.####");
		df.setRoundingMode(RoundingMode.CEILING);
		
		for(int i = 0; i < cipherText.length(); ++i)
		{
			String letter = Character.toString(cipherText.charAt(i));
			
			//System.out.println(letter + " " + cipher_letterFrequencies.get(letter) + " out of " + totalLetters);
			//System.out.println(letter + " " + df.format(cipher_letterFrequencies.get(letter) / totalLetters * 100));
		}
		
		// CONTACT LETTERS HERE
		
		int depth = 0;
		
		//System.out.println("Started Reading");
		
		Map<String, Integer> tempMap_After = new HashMap<String, Integer>();
		Map<String, Integer> tempMap_Before = new HashMap<String, Integer>();
		
		for(int i = 0; i < cipherText.length(); ++i)
		{
			String letter = Character.toString(cipherText.charAt(i));
			
			String previousLetter = "";
			String followingLetter = "";
			
			if(depth > 0)
			{
				previousLetter = Character.toString(cipherText.charAt(depth - 1));
				
				if(tempMap_Before.containsKey(previousLetter))
				{
					tempMap_Before.put(previousLetter, tempMap_Before.get(previousLetter) + 1);
					
					cipher_ContactLetterFrequencies_Before.put(letter, tempMap_Before);
				}
				else
				{
					tempMap_Before.put(previousLetter, 1);
					
					cipher_ContactLetterFrequencies_Before.put(letter, tempMap_Before);
				}
			}
			
			if(depth < cipherText.length() - 1)
			{
				followingLetter = Character.toString(cipherText.charAt(depth + 1));
				
				if(tempMap_After.containsKey(followingLetter))
				{
					tempMap_After.put(followingLetter, tempMap_After.get(followingLetter) + 1);
					
					cipher_ContactLetterFrequencies_After.put(letter, tempMap_After);
				}
				else
				{
					tempMap_After.put(followingLetter, 1);
					
					cipher_ContactLetterFrequencies_After.put(letter, tempMap_After);
				}
				//System.out.println(tempMap_After);
			}
			
			//cipher_ContactLetterFrequencies_After.put(letter, tempMap_After);
			//cipher_ContactLetterFrequencies_Before.put(letter, tempMap_Before);
			
			++depth;
		}
		
		//System.out.println("Finished Reading");
		
		//System.out.println(cipher_ContactLetterFrequencies_After);
		//System.out.println(cipher_ContactLetterFrequencies_Before);
		//System.exit(0);
	}
	
	public static void mapFrequencies()
	{
		
	}

}
