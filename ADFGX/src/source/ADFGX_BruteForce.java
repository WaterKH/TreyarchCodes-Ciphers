package source;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ADFGX_BruteForce {
	
	//public static ArrayList<String> letterPairs = new ArrayList<String>();
	//public static String[] lastPermutation;
	public static String alphabet = "abcdefghiklmnopqrstuvwxyz";
	public static String currAlphabet = "";
	public static ArrayList<String> combinationsList = new ArrayList<String>();
	//public static String currCombination = "";
	static Map<String, Integer> frequencies = new HashMap<String, Integer>();
	static ArrayList<String> topChars = new ArrayList<String>();
	static ArrayList<String> chars = new ArrayList<String>();
	public static int lineCount = 0;
	
	
	public static void main(String[] args) throws IOException
	{
		BufferedReader reader = Resources.openFile_Reader("CipherKeys2");
		BufferedWriter writer = Resources.openFile_Writer("adfgxPhrases_Updated");
		String line = "";
		
		while((line = reader.readLine()) != null)
		{
			BufferedReader reader_Letters = Resources.openFile_Reader("letterPairs" + line);
			
			String letterPairs = reader_Letters.readLine();
			
			System.out.println(letterPairs);
			
			findTopCharacters(letterPairs);
			
			/**
			 * For the next n (4) letters in the ciphertext
			 */
			char[] firstCharArr = new char[topChars.size()];
			
			ADFGX_BruteForce.createCombinations(firstCharArr.length, 0, firstCharArr);
			
			for(int i = 0; i < ADFGX_BruteForce.combinationsList.size(); ++i)
			{
				ADFGX_BruteForce.createPermutations(ADFGX_BruteForce.combinationsList.get(i), writer);
			}
		
			combinationsList.clear();
			frequencies.clear();
			topChars.clear();
			chars.clear();
		}
		System.out.println("Completed");
		reader.close();
		writer.close();
		
		System.out.println(lineCount);
	}
	
	public static void createCombinations(int length, int startPosition, char[] result)
	{
        if (length == 0)
        {
        	String tempString = "";
        	for(int i = 0; i < result.length; ++i)
        	{
        		tempString += result[i];
        	}
            //System.out.println(tempString);
        	combinationsList.add(tempString);
        	
        	//createPermutations(tempString);
            return;
        }       
        for (int i = startPosition; i <= alphabet.length() - length; i++)
        {
            result[result.length - length] = alphabet.charAt(i);
            createCombinations(length - 1, i + 1, result);
        }
	}
	
	/**
	 * Code implemented from: http://stackoverflow.com/questions/4240080/generating-all-permutations-of-a-given-string
	 * @param str
	 * @throws IOException 
	 */
	public static void createPermutations(String stringToPermutate, BufferedWriter writer) throws IOException 
	{ 
		/*lastPermutation = new String[ADFGX.uniqueLetters];
		for(int i = 0; i < lastPermutation.length; ++i)
		{
			lastPermutation[i] = "";
		}*/
	    permutation("", stringToPermutate, writer); 
	}
	
	public static Map<String, String> assignedLetters = new HashMap<String, String>();
	
	private static void permutation(String prefix, String str, BufferedWriter writer) throws IOException 
	{ 
		int stringLength = str.length();
		
	    if (stringLength == 0) 
	    {
	    	int tempCounter = 0;
	    	
	    	for(int i = 0; i < topChars.size(); ++i)
	    	{
	    		if(tempCounter == prefix.length())
	    			break;
	    	
	    		if(Character.isUpperCase(topChars.get(i).charAt(0)))
	    		{
	    			assignedLetters.put(topChars.get(i), Character.toString(prefix.charAt(tempCounter)));
	    			++tempCounter;
	    		}
	    	}
	    	
	    	String plainText = "";
	    	
	    	for(int i = 0; i < chars.size(); ++i)
	    	{
	    		if(assignedLetters.containsKey(chars.get(i)))
	    		{
	    			plainText += assignedLetters.get(chars.get(i));
	    		}
	    		else
	    		{
	    			//plainText += chars.get(i);
	    			plainText += ".";
	    		}
	    	}
	
	    	if(plainText.contains("the"))
	    	{
	    		writer.write(plainText);
	    		writer.newLine();
	    		++lineCount;
	    	}
	    }
	    else 
	    {
	        for (int i = 0; i < stringLength; i++)
	        {	
	    		permutation(prefix + str.charAt(i), str.substring(0, i) + str.substring(i+1, stringLength), writer);
	        }
	    }
	}
	/**
	 * End of code sample implementation
	 * 
	 */
	
	public static void findTopCharacters(String letterPairs)
	{
		for(String letters : letterPairs.split(" "))
		{
			chars.add(letters);
			if(!frequencies.containsKey(letters))
			{
				frequencies.put(letters, 1);
			}
			else
			{
				frequencies.put(letters, frequencies.get(letters) + 1);
			}
		}
		
		System.out.println(frequencies);
		
		int permSize = 5;
		ArrayList<String> contained = new ArrayList<String>();
		
		for(int i = 0; i < permSize; ++i)
		{
			int max = 0;
			String str = "";
			
			for(int j = 0; j < chars.size(); ++j)
			{
				if(frequencies.get(chars.get(j)) > max && !contained.contains(chars.get(j)))
				{
					max = frequencies.get(chars.get(j));
					str = chars.get(j);
					
					if(topChars.size() > i)
					{
						topChars.remove(i);
					}
					topChars.add(chars.get(j));
				}
			}
			contained.add(str);
		}
		
		for(int i = 0; i < topChars.size(); ++i)
		{
			System.out.println(topChars.get(i));
		}
	}
}
