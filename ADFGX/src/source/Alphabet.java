/****************************************************************************
 * Author: @author peterclark - All Rights Reserved
 * Program: ADFGX Cipher Decryption Tool
 *
 * Stores everything to do with manipulating the alphabet
 */
package source;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Alphabet {

	public static String[][] alphabet;
	//final static String frequentLetters = "ETAOINSH";
	//final static String infrequentLetters = "RDLCUMWFGYPBVKJXQZ";
	//public static ArrayList<String> freqLettArr = new ArrayList<String>();
	//public static ArrayList<String> infreqLettArr = new ArrayList<String>();
	//public static ArrayList<String> alreadyUsed = new ArrayList<String>();
	public static int counter = 0;
	
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
	 * Basic clear function
	 * 
	 * Clears freqLettArr, infreqLettArr, and alreadyUsed
	 * 
	 */
	public static void clear()
	{
		//freqLettArr.clear();
		//infreqLettArr.clear();
		//alreadyUsed.clear();
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
	 * 
	 * We construct an alphabet based on a frequency tracker.
	 * 
	 * FOR the size of the listForFreqAna
	 * 	  IF the letterPairs does not contain our letter pair - meaning 
	 *    we don't already have that letter of the alphabet
	 * 		IF the frequency of this letter pair is above our frequency delimiter
	 *      and our frequency size is below the amount of letters we have
	 * 			We change our alphabet to the letter at the current index
	 * 			  of our frequent letters and increment our frequency size
	 * 		ELSE IF our infrequent size is below the amount of letters we have
	 * 			We change our alphabet to the letter at the current index
	 * 			  of our infrequent letters and increment our infrequency size
	 * 		Then we add the letter pair to our letter pairs.
	 * We then call constructListsOfFrequencies()
	 * @throws IOException 
	 * 
	 */
	
	// REWORK THIS METHOD TO USE
	public void constructAlphabet() throws IOException
	{
		this.sortMap(ADFGX.freqTracker);
		
		/*final int frequencyDelim = 3;
		
		int fSize = 0;
		int ifSize = 0;
		ArrayList<String> letterPairs = new ArrayList<String>();
		
		for(int i = 0; i < ADFGX.listForFreqAna.size(); ++i)
		{
			int[] alphaIndex = ADFGX.getAlphabetIndexFromListIndex(i);
			int frequencyInt = ADFGX.freqTracker.get(ADFGX.listForFreqAna.get(i));
			
			if(!letterPairs.contains(ADFGX.listForFreqAna.get(i)))
			{
				if(frequencyInt > frequencyDelim && fSize < frequentLetters.length())
				{
					alphabet[alphaIndex[0]][alphaIndex[1]] = Character.toString(frequentLetters.charAt(fSize));
					++fSize;
				} // if(ADFGX.freqTracker.get(ADFGX.listForFreqAna.get(i)) > 4 && tempAdderF < frequentLetters.length())
				else if(frequencyInt == 1)
				{
					continue;
				}
				else if(ifSize < infrequentLetters.length())
				{
					alphabet[alphaIndex[0]][alphaIndex[1]] = Character.toString(infrequentLetters.charAt(ifSize));
					++ifSize;
				} // else if(tempAdderIF < infrequentLetters.length())
				
				letterPairs.add(ADFGX.listForFreqAna.get(i));
			} // if(!tempHolderLetterPairs.contains(ADFGX.listForFreqAna.get(i)))
			
		} // for(int i = 0; i < ADFGX.listForFreqAna.size(); ++i)*/
		
		this.constructListsOfFrequencies();
	} /** public void constructAlphabet() **/
	
	/************************************************************************
	 *
	 * We construct two lists - freqLettArr & infreqLettArr - that house the letters
	 * 	that are frequent and infrequent
	 * 
	 * FOR the size of the alphabet's two arrays
	 *   (!! APPLIES TO BOTH FREQUENT AND INFREQUENT !!)
	 *   
	 *   FOR the size of the (in)frequent letters String
	 *     IF we are still in bounds
	 *       IF the alphabet letter equals the letter in our (in)frequent letters String
	 *         We add this to our (in)freqLettArr
	 * 
	 */
	public void constructListsOfFrequencies()
	{
		//boolean found = false;
		
		for(int i = 0; i < alphabet.length; ++i)
		{
			for(int j = 0; j < alphabet[i].length; ++j)
			{
				System.out.print(alphabet[i][j] + " ");
			}
			System.out.println();
		}
		
		/*for(int i = 0; i < alphabet.length; ++i)
		{
			for(int j = 0; j < alphabet[i].length; ++j)
			{
				for(int k = 0; k < frequentLetters.length(); ++k)
				{
					if(found == true)
						break;
					if(alphabet[i][j].equals(Character.toString(frequentLetters.charAt(k))))
					{
						found = true;
						freqLettArr.add(alphabet[i][j]);
						System.out.println(freqLettArr);
					} // if(alphabet[i][j].equals(Character.toString(frequentLetters.charAt(k))))
					
				} // for(int k = 0; k < frequentLetters.length(); ++k)
				
				for(int k = 0; k < infrequentLetters.length(); ++k)
				{
					if(found == true)
						break;	
					if(alphabet[i][j].equals(Character.toString(infrequentLetters.charAt(k))))
					{
						found = true;
						infreqLettArr.add(alphabet[i][j]);
						System.out.println(infreqLettArr);
					} // if(alphabet[i][j].equals(Character.toString(infrequentLetters.charAt(k))))
					
				
				} // for(int k = 0; k < infrequentLetters.length(); ++k)
				
				found = false;
			} // for(int j = 0; j < alphabet[i].length; ++j)
			
		} // for(int i = 0; i < alphabet.length; ++i)*/
		
	} /** public void constructListsOfFrequencies() **/
	
	/************************************************************************
	 *
	 * This is the main method that creates the text files of the different alphabets
	 * 
	 * We set timers to see how long opening Freq and Infreq txt files will take. We
	 *  pass the BufferedWriters as well as the ArrayLists of the letters being used
	 *  in to permuteString to get every permutation of the letters.
	 * We then write every combination of the frequent and infrequent letter strings
	 *  to a file (FinalWrite) delimited by a dash.
	 * Once our file is created, we read from that to obtain a different alphabet which
	 *  we use to construct a phrase from.
	 * 
	 * @param BufferedWriter constructWriter, String choice
	 * @throws IOException
	 */
	public static void permutateLists(BufferedWriter constructWriter, String choice) throws IOException
	{
		Resources.startTimer();
		
		BufferedWriter Fwriter = Resources.openFile_Writer("Freq");
		//permuteString(freqLettArr, Fwriter);
		
		Resources.closeFile(Fwriter, "Freq");
		Resources.endTimer();
		
		Resources.startTimer();
		
		BufferedWriter IFwriter = Resources.openFile_Writer("Infreq");
		//permuteString(infreqLettArr, IFwriter);
		
		Resources.closeFile(IFwriter, "Infreq");
		Resources.endTimer();
		
		System.out.println("** PERMUTATIONS COMPLETED **");

		//Resources.startTimer();
		
		/*BufferedReader fReader = Resources.openFile_Reader("Freq");
		BufferedReader ifReader = Resources.openFile_Reader("Infreq");
		//final int FInteger = 0;
		//final int IFInteger = 1;
		//String freqLine = "";
		//String infreqLine = "";
		
		while((freqLine = fReader.readLine()) != null)
		{
			while((infreqLine = ifReader.readLine()) != null)
			{
				//String[] line = {freqLine, infreqLine};
				//ArrayList<String> alphabetF = new ArrayList<String>();
				//ArrayList<String> alphabetIF = new ArrayList<String>();
				
				//rearrangeAlphabet(alphabetF, line, FInteger, frequentLetters);
				
				//rearrangeAlphabet(alphabetIF, line, IFInteger, infrequentLetters);
				
				ADFGX.constructPhrase(alphabet, constructWriter, choice);
			} // for(String infreqLine : infreqList)
			
		} // for(String freqLine : freqList)
		
		//displayAlphabet();
		
		Resources.closeFile(ifReader, "Infreq");
		Resources.closeFile(fReader, "Freq");*/
		
		//Resources.endTimer();
		
	} /** public static void permutateLists(BufferedWriter constructWriter, String choice) throws IOException **/

	/************************************************************************
	 * 
	 * Rearranges the letters in our alphabet depending on our string of letters.
	 * 
	 * FOR the length of the alphabet's two arrays
	 *   IF the alphabet letter is contained within the letters (Freq or infreq letters)
	 *     Add it to the alphabetPos ArrayList
	 * FOR the size of the alphabetPos ArrayList
	 *  IF we are within bounds
	 *    IF our position does not equal the alphabet's position of the letter (ie - E != F)
	 *      We swap the letters
	 * FOR the length of the alphabet's two arrays   
	 *   We change the letter of the alphabet according to the ArrayList we created
	 * 
	 * @param ArrayList<String> alphabetPos, String[] line, int index, String letters
	 */
	public static void rearrangeAlphabet(ArrayList<String> alphabetPos, String[] line, int index, String letters)
	{
		for(int i = 0; i < alphabet.length; ++i)
		{
			for(int j = 0; j < alphabet[i].length; ++j)
			{
				if(letters.contains(alphabet[i][j]))
				{
					alphabetPos.add(alphabet[i][j]);
				} // if(letters.contains(alphabet[i][j]))
				
			} // for(int j = 0; j < alphabet[i].length; ++j)
			
		} // for(int i = 0; i < alphabet.length; ++i)
		
		for(int i = 0; i < alphabetPos.size(); ++i)
		{
			if(!alphabetPos.get(i).equals(Character.toString(line[index].charAt(i))))
			{
				int tempInt = i;
				boolean found = false;
				while(!found)
				{
					++i;
					if(i + 1 < alphabetPos.size())
					{
						if(alphabetPos.get(tempInt).equals(Character.toString(line[index].charAt(i))))
						{	
							String tempString = Character.toString(line[index].charAt(i));
							String toSwap = alphabetPos.get(i);
							alphabetPos.set(i, tempString);
							i = tempInt;
							tempString = Character.toString(line[index].charAt(i));
							alphabetPos.set(i, toSwap);
						} // if(alphabetPos.get(tempInt).equals(Character.toString(line[index].charAt(i))))
						
					} // if(i + 1 < alphabetPos.size())
					else
					{
						found = true;
					} // else
					
				} // while(!found)
				
			} // if(!alphabetPos.get(i).equals(Character.toString(line[index].charAt(i))))
		
		} // for(int i = 0; i < alphabetPos.size(); ++i)
		
		int tempIndex = 0;
		
		for(int i = 0; i < alphabet.length; ++i)
		{
			for(int j = 0; j < alphabet[i].length; ++j)
			{
				if(!alphabet[i][j].equals("-"))
				{
					if(letters.contains(alphabet[i][j]))
					{
						alphabet[i][j] = alphabetPos.get(tempIndex);
						++tempIndex;
					} // if(letters.contains(alphabet[i][j]))
					
				} // if(!alphabet[i][j].equals("-"))
				
			} // for(int j = 0; j < alphabet[i].length; ++j)
			
		} // for(int i = 0; i < alphabet.length; ++i)
	} /** public static void rearrangeAlphabet(ArrayList<String> alphabetPos, String[] line, int index, String letters) **/
	
	/************************************************************************
	 * 
	 * Set up method for the actual permutation method
	 * 
	 * We create a string from the ArrayList<String> and pass it in as parameter
	 *  for the main permutation method.
	 * 
	 * @param ArrayList<String> stringToUse, BufferedWriter writer
	 * @throws IOException
	 */
	public static void permuteString(ArrayList<String> stringToUse, BufferedWriter writer) throws IOException
	{
		String tempString = "";
		
		for(String character : stringToUse)
		{
			tempString += character;
		} // for(String character : stringToUse)
		
		permuteString("", tempString, writer);
		System.out.println(counter);
		counter = 0;
	} /** public static void permuteString(ArrayList<String> stringToUse, BufferedWriter writer) throws IOException **/
	
	/************************************************************************
	 * ORIGINAL: http://www.java2s.com/Tutorial/Java/0100__Class-Definition/RecursivemethodtofindallpermutationsofaString.htm
	 * IMPLEMENTED BY: @author peterclark
	 * 
	 * Works like next_permutations() from C++ - Gets every permutation of the 
	 *  String and writes it to a file when finished
	 * 
	 * @param String beginningString, String endingString, BufferedWriter writer
	 * @throws IOException
	 */
	public static void permuteString(String beginningString, String endingString, BufferedWriter writer) throws IOException 
	{
		if (endingString.length() <= 1)
	    {
	    	writer.write(beginningString + endingString);
	    	writer.newLine();
	    } // if (endingString.length() <= 1)
	    else
	    {
	    	for (int i = 0; i < endingString.length(); i++) 
	    	{
	    		try 
	    		{
	    			String newString = endingString.substring(0, i) + endingString.substring(i + 1);
	    			++counter;
	    			permuteString(beginningString + endingString.charAt(i), newString, writer);
		        } // try
	    		catch (StringIndexOutOfBoundsException exception) 
	    		{
		        	exception.printStackTrace();
		        } // catch (StringIndexOutOfBoundsException exception)
	    		
	    	} // for (int i = 0; i < endingString.length(); i++) 
	    	
	    } // else
	} /** public static void permuteString(String beginningString, String endingString, BufferedWriter writer) throws IOException  **/
	
	/************************************************************************
	 * !! Not used as originally intended - Instead just lists the frequencies in order !!
	 * 
	 * Prints out the Map in order of Integer values
	 * 
	 * @param Map<String, Integer> tempMap
	 * @throws IOException 
	 */
	public void sortMap(Map<String, Integer> tempMap) throws IOException
	{
		Set<String> tempStringSet = tempMap.keySet();
		
		Collection<Integer> tempIntCol = tempMap.values();
		
		ArrayList<Integer> tempIntArr = new ArrayList<Integer>(tempIntCol);
		ArrayList<String> tempStringArr = new ArrayList<String>();
		
		for(String str : tempStringSet)
		{
			tempStringArr.add(str);
		} // for(String str : tempStringSet)
		
		System.out.println("*SORTING...*");
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
			if(!(tempIntArr.get(i) < 2))
			{
				//System.out.println(tempStringArr.get(i) + " " + tempIntArr.get(i));
				paramMap.put(tempStringArr.get(i), tempIntArr.get(i));
			}
		} // for(int i = tempStringArr.size() - 1; i >= 0; --i)	
		System.out.println(paramMap);
		LetterFrequency.distributeLetters(paramMap);
		System.out.println("*SORT SUCCESFUL*");
	} /** public void sortMap(Map<String, Integer> tempMap) **/
	
	/************************************************************************
	 * 
	 * Simple toString - Just named differently
	 * 
	 */
	public static void displayAlphabet()
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