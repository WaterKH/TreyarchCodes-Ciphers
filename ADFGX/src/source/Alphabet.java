/****************************************************************************
 * Author: @author peterclark - All Rights Reserved
 * Program: ADFGX Cipher Decryption Tool
 *
 * Stores everything to do with manipulating the alphabet
 */
package source;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Alphabet {

	public String[][] alphabet;
	public static int counter = 0;
	public String alphabetStr = "abcdefghijklmnopqrstuvwxyz";
	
	/************************************************************************
	 * Basic Constructor
	 * 
	 * Sets the String[][] alphabet to null values. (ie - '-')
	 * 
	 */
	public Alphabet()
	{
		alphabet = new String[5][5];
		for(int i = 0; i < 5; ++i)
		{
			for(int j = 0; j < 5; ++j)
			{
				alphabet[i][j] = "-";
			} // for(int j = 0; j < 5; ++j)
			
		} // for(int i = 0; i < 5; ++i)
	} /** public Alphabet() **/
	
	/************************************************************************
	 * Parameterized Constructor
	 * 
	 * Sets the String[][] alphabet to another String[][]
	 * 
	 */
	public Alphabet(String[][] tempAlpha)
	{
		alphabet = tempAlpha;
	} /** public Alphabet(String[][] tempAlpha) **/
	
	/************************************************************************
	 * Parameterized Constructor
	 * 
	 * 
	 * 
	 */
	public Alphabet(String keyword)
	{
		int count = 0;
		int alphaCount = 0;
		Map<String, Integer> container = new HashMap<String, Integer>();
		
		alphabet = new String[5][5];
		for(int i = 0; i < alphabet.length; ++i)
		{
			for(int j = 0; j < alphabet[i].length; ++j)
			{
				if(count < keyword.length())
				{
					alphabet[i][j] = Character.toString(keyword.charAt(count));
					container.put(Character.toString(keyword.charAt(count)), 0);
					++count;
				}
				else 
				{
					if(alphaCount < alphabetStr.length())
					{
						while(true)
						{
							if(container.containsKey(Character.toString(alphabetStr.charAt(alphaCount))))
							{
								++alphaCount;
								continue;
							}
							alphabet[i][j] = Character.toString(alphabetStr.charAt(alphaCount));
							++alphaCount;
							break;
						}
					}
				}
			}
		}
		//displayAlphabet();
	}
	
	/************************************************************************
	 * Basic clear function
	 * 
	 * Clears freqLettArr, infreqLettArr, and alreadyUsed
	 * 
	 */
	public void clear()
	{
		alphabet = new String[5][5];
		for(int i = 0; i < 5; ++i)
		{
			for(int j = 0; j < 5; ++j)
			{
				alphabet[i][j] = "-";
			} // for(int j = 0; j < 5; ++j)
			
		} // for(int i = 0; i < 5; ++i)
	} /** public static void clear() **/
	
	/************************************************************************
	 * !! Not used as originally intended - Instead just lists the frequencies in order !!
	 * 
	 * Prints out the Map in order of Integer values
	 * 
	 * @param Map<String, Integer> tempMap
	 * @throws IOException 
	 */
	public static void sortMap(Map<String, Integer> tempMap) throws IOException
	{
		System.out.println("SORT MAP: " + tempMap);
		Set<String> tempStringSet = tempMap.keySet();
		
		Collection<Integer> tempIntCol = tempMap.values();
		
		ArrayList<Integer> tempIntArr = new ArrayList<Integer>(tempIntCol);
		ArrayList<String> tempStringArr = new ArrayList<String>();
		
		for(String str : tempStringSet)
		{
			tempStringArr.add(str);
		} // for(String str : tempStringSet)
		
		//System.out.println("*SORTING...*");
		for(int i = 0; i < tempStringArr.size(); ++i)
		{
			for(int j = 0; j < tempStringArr.size(); ++j)
			{
				if(tempIntArr.get(j) > tempIntArr.get(i))
				{
					int tempInt = tempIntArr.get(j);
					tempIntArr.set(j, tempIntArr.get(i));
					tempIntArr.set(i, tempInt);
					
					String tempString = tempStringArr.get(j);
					tempStringArr.set(j, tempStringArr.get(i));
					tempStringArr.set(i, tempString);
				} // if(tempIntArr.get(j) > tempIntArr.get(i))
				
			} // for(int j = 0; j < tempStringArr.size(); ++j)
			
		} // for(int i = 0; i < tempStringArr.size(); ++i)
		
		Map<String, Integer> paramMap = new HashMap<String, Integer>();
		for(int i = tempStringArr.size() - 1; i >= 0; --i)
		{
			//System.out.println(tempStringArr.get(i) + " " + tempIntArr.get(i));
			//if(!(tempIntArr.get(i) < 2))
			//{
				////System.out.println(tempStringArr.get(i) + " " + tempIntArr.get(i));
				paramMap.put(tempStringArr.get(i), tempIntArr.get(i));
			//}
		} // for(int i = tempStringArr.size() - 1; i >= 0; --i)	
		//System.out.println(paramMap);
		LetterFrequency.distributeLetters(paramMap);
		//System.out.println("*SORT SUCCESFUL*");
	} /** public void sortMap(Map<String, Integer> tempMap) **/
	
	/************************************************************************
	 * 
	 * Simple toString - Just named differently
	 * 
	 */
	public void displayAlphabet()
	{	
		System.out.println();
		System.out.println("*ADFGX ALPHABET POLYBIUS SQUARE*:");
		System.out.println();
		
		String adfgx = "ADFGX";
		System.out.println("     A D F G X");
		System.out.println("     0 1 2 3 4");
		System.out.println("     | | | | |");
		
		for(int i = 0; i < 5; ++i)
		{
			System.out.print(adfgx.charAt(i) + " " + i + "> ");
			for(int j = 0; j < 5; ++j)
			{
				System.out.print(alphabet[i][j].toLowerCase() + " ");
			} // for(int j = 0; j < 5; ++j)
			
			System.out.println();
		} // for(int i = 0; i < 5; ++i)
		
		System.out.println();
	} /** public void displayAlphabet() **/
}
