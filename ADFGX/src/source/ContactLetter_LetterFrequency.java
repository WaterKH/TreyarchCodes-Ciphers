package source;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class ContactLetter_LetterFrequency {
	
	public static Map<String, Integer> cipher_letterFrequencies = new HashMap<String, Integer>();
	
	public static void testFrequency(String letter)
	{
		
	}
	
	public static void createTableOfFrequencies(String cipherText)
	{
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
	}
	
	public static void mapFrequencies()
	{
		
	}

}
