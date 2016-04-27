/**
 * This program is designed to fix a dictionary if necessary and also fish out only 6 letter words for keys
 * 
 */
package source;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Find {
	
	//public static File file = new File("dictionary.txt");
	//public static File fileToWrite = new File("dictionary_keywords.txt");
	//public static File dictFile = new File("dictionary_Fixed.txt");
	public static File file = new File("words.txt");
	public static File fileToWrite = new File("final6Words.txt");
	
	public static void main(String[] args) throws IOException {
	
		BufferedReader dictReader = new BufferedReader(new FileReader(file));
		BufferedWriter dictWriter = new BufferedWriter(new FileWriter(fileToWrite));
		//BufferedWriter toNewDictWriter = new BufferedWriter(new FileWriter(dictFile));
		
		String line = "";
		
		while((line = dictReader.readLine()) != null)
		{
		//	System.out.println("LINE: " + line);
			
			String wordToWrite = "";

			Map<Character, Integer> multipleLetters = new HashMap<Character, Integer>();
			
			for(int i = 0; i < line.length(); ++i)
			{
				if(!multipleLetters.containsKey(line.charAt(i)))
				{
					wordToWrite += line.charAt(i);
					multipleLetters.put(line.charAt(i), 0);
				}
			}			
		
			//System.out.println(wordToWrite);
			if(wordToWrite.length() == 6)
			{
				if(line.charAt(line.length() - 2) == '\'')
				{
					System.out.println("DELETE " + line);
					continue;
				}
				
				Map<Character, Integer> contained = new HashMap<Character, Integer>();
				boolean areGreaterThan = true;
				boolean isContained = false;
				//String firstChar = Character.toString(line.charAt(0));
				//String secondChar = Character.toString(line.charAt(1));
				char firstChar = wordToWrite.charAt(0);
				char secondChar = wordToWrite.charAt(1);
				
				if(firstChar == secondChar)
				{
					continue;
				}
				
				for(int i = 0; i < 2; ++i)
				{
					if(!contained.containsKey(wordToWrite.charAt(i)))
					{
						contained.put(wordToWrite.charAt(i), 0);
					}
				}
				
				// !!NOTE!! Loop starts at 2
				for(int i = 2; i < wordToWrite.length(); ++i)
				{
					//System.out.println(wordToWrite);
					if(firstChar <= wordToWrite.charAt(i) || secondChar <= wordToWrite.charAt(i))
					{
						//System.out.println(firstChar + " " + secondChar  + " - " + line.charAt(i));
						areGreaterThan = false;
						
						break;
					}
					
					if(contained.containsKey(wordToWrite))
					{
						isContained = true;
						
						break;
					}
					
					contained.put(wordToWrite.charAt(i), 0);
				}
				
				if(areGreaterThan && !isContained)
				{
					//System.out.println("Wrote - " + line);
					dictWriter.write(wordToWrite);
					dictWriter.newLine();
				}
			}
		}
		
		
		dictReader.close();
		dictWriter.close();
		//toNewDictWriter.close();
		
	
		System.out.println("Completed");
	}
}
