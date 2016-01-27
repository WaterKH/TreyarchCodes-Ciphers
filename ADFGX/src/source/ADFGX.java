/****************************************************************************
 * Author: @author peterclark - All Rights Reserved
 * Program: ADFGX Cipher Decryption Tool
 * 
 * The main program
 */
package source;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ADFGX {

	static Alphabet alphaClass = new Alphabet();
	static Map<Character, ArrayList<String>> columnMap = new HashMap<Character, ArrayList<String>>();
	static Map<String, Integer> freqTracker = new HashMap<String, Integer>();
	static ArrayList<String> listForFreqAna = new ArrayList<String>();
	public static String line = "";
	static int finalRowCount = 0;
	public static String wordToSearchFor = "";
	public static File currentFile;
	
	/************************************************************************
	 * 
	 * The main method - calls sort and frequency analysis as well as create permutations
	 * 
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException
	{
		Resources.startTimer();
		
		BufferedReader reader = Resources.openFile_Reader("CipherKeys");
		int counter = 1;
		
		finalRowCount = getLengthOfColumns("abcdef");
		System.out.print("What word would you like to look for? ");
		@SuppressWarnings("resource")
		Scanner keyboard = new Scanner(System.in);
		wordToSearchFor = keyboard.nextLine();
		
		while((line = reader.readLine()) != null)
		{
			System.out.println("****" + line.toUpperCase() + "****  -   " + counter);
			
			String inputKeyword = line;
				
			sortBasedOnKeyword(inputKeyword.toUpperCase());
			LetterFrequency.frequencyAnalysis(listForFreqAna);
		
			Alphabet.sortMap(ADFGX.freqTracker);
			
			if(currentFile.length() == 0)
			{
				System.out.println("EMPTY - DELETING");
				currentFile.delete();
			}
			
			++counter;
			clear();
		}
		
		Resources.endTimer();
		
	} /** public static void main(String[] args) throws IOException **/
	
	/************************************************************************
	 * Basic clear function
	 * 
	 * Clears holderForText, columnMap, freqTracker, and listForFreqAna
	 * 
	 */
	public static void clear()
	{
		columnMap.clear();
		freqTracker.clear();
		listForFreqAna.clear();
	} /** public static void clear() **/
	
	/************************************************************************
	 * Basic delete function
	 * 
	 * Deletes all unnecessary files
	 * 
	 */
	public static void deleteAllFiles()
	{
		Resources.deleteFile("adfgxMapped");
		Resources.deleteFile("adfgxSolved");
		Resources.deleteFile("adfgxPermutations");
		Resources.deleteFile("Freq");
		Resources.deleteFile("Infreq");
	} /** public static void deleteAllFiles() **/
	
	/************************************************************************
	 * 
	 * Accessor
	 * 
	 * Returns the index of where the letter we want should be. 
	 * 	!!NOTE!! This should be used with a loop over listForFreqAna size
	 *  
	 * 
	 * @param int index
	 * @returns tempHolder
	 */
	public static int[] getAlphabetIndexFromListIndex(int index)
	{
		int[] tempHolder = new int[2];
		
		for(int k = 0; k < listForFreqAna.get(index).length(); ++k)
		{
			tempHolder[k] = ADFGX_Subs(listForFreqAna.get(index).charAt(k));
		} // for(int k = 0; k < listForFreqAna.get(i).length(); ++k)
		
		return tempHolder;
	} /** public static int[] getAlphabetIndexFromListIndex(int index) **/
	
	/************************************************************************
	 * 
	 * Accessor
	 * 
	 * Returns the index of where the letter we want should be. 
	 * 	!!NOTE!! This should be used with a loop over listForFreqAna size
	 *  
	 * 
	 * @param String chars
	 * @returns tempHolder
	 */
	public static int[] getAlphabetIndexFromCharacters(String chars)
	{
		int[] tempHolder = new int[2];
		
		for(int k = 0; k < chars.length(); ++k)
		{
			tempHolder[k] = ADFGX_Subs(chars.charAt(k));
		} // for(int k = 0; k < chars.length(); ++k)
		
		return tempHolder;
	} /** public static int[] getAlphabetIndexFromCharacters(String chars) **/
	
	/************************************************************************
	 * 
	 * We take in an input key which we convert to a char array to sort, then
	 * 	convert it back to a string. We create a file named "adfgxMapped"
	 * 	and read from "adfgxCipherText" to the file we created - the
	 *  text wrap is dependent upon the inputKey's length. We then create a 
	 *  double array based on our column length and our row size and read in from
	 *  the "adfgxMapped" to create a matrix that we can then use to create
	 *  a map of our columns so that we can call our addToList method that will 
	 *  create the "adfgxSolved" file.
	 *  
	 * 
	 * @param String inputKey
	 * @throws IOException
	 */
	public static void sortBasedOnKeyword(String inputKey) throws IOException
	{	
		char[] individualChars = inputKey.toCharArray();
		Arrays.sort(individualChars);
		String columnDelim = new String(individualChars);
		//System.out.println("*SORTED ALPHABETICALLY: " + columnDelim + " ROW LENGTH: " + individualChars.length + "*");
		
		int rowLength = finalRowCount;
	
		ArrayList<ArrayList<String>> holderForText = new ArrayList<ArrayList<String>>();
		
		for(int i = 0; i < rowLength; i++)  
		{
	        holderForText.add(new ArrayList<String>());
	    } // for(int i = 0; i < individualRow; i++)  
		
		int col = 0;
		
		//System.out.println("*ORIGINAL INPUT CIPHERTEXT*:");
		
		BufferedReader readerMapped = Resources.openFile_Reader("adfgxMapped");
		String lineMapped = "";
		
		while((lineMapped = readerMapped.readLine()) != null)
		{
			for(String part : lineMapped.split("\\s+"))
			{	
				holderForText.get(col).add(part);
				++col;
			} // for(String part : line)

			col = 0;
		} // for(String line : Files.readAllLines)
		
		for(int tempCol = 0; tempCol < columnDelim.length(); ++tempCol)
		{
			columnMap.put(columnDelim.charAt(tempCol), holderForText.get(tempCol));
		} // for(int tempCol = 0; tempCol < colDelim.length(); ++tempCol)
		
		//System.out.println();
		addToListFreqAna(inputKey);
	} /** public static void sortBasedOnKeyword(String inputKey) throws IOException **/
	
	 
	/************************************************************************
	  * 
	  *  
	  *  
	  *  
	  *  @throws IOException 
	  */
	private static int getLengthOfColumns(String columnDelim) throws IOException
	{
		int trackOfRows = 0;
		int individualRow = 0;
		
		BufferedReader readerCipherText = Resources.openFile_Reader("adfgxCipherText");
		BufferedWriter writer = Resources.openFile_Writer("adfgxMapped");
		String lineCipherText = "";
		
		while((lineCipherText = readerCipherText.readLine()) != null)
		{
			for(String part : lineCipherText.split("\\s+"))
			{
				for(int i = 0; i < part.length(); ++i)
				{
					writer.write(part.charAt(i) + " ");
					++trackOfRows;
					if(trackOfRows == columnDelim.length())
					{
						writer.newLine();
						
						++individualRow;
						trackOfRows = 0;
					} // if(trackOfRows == colDelim.length())
					
				} // for(int i = 0; i < part.length(); ++i)
				
			} // for(String part : line)
			
		} // for(String line : Files.readAllLines)
		
		Resources.closeFile(writer, "adfgxMapped");
		Resources.closeFile(readerCipherText, "adfgxCipherText");
		
		return individualRow;
	}
	
	/************************************************************************
	 * !! This finalizes the sort called above, the method name is a bit misleading !!
	 * 
	 * We take in an inputKey and loop on how big our array is that we get
	 *  from our columnMap using our key. Then for how many columns we have
	 *  we write to file each row. Every time we get a pair of letters, we
	 *  add it to our list and reset our temporary string value.
	 *  
	 * 
	 * @param String inputKey
	 * @throws IOException 
	 */
	private static void addToListFreqAna(String inputKey) throws IOException
	{
		BufferedWriter writer = Resources.openFile_Writer("adfgxSolved");
		String str = "";
		
		//System.out.println("*SORTING BASED ON KEY...*");
		for(int j = 0; j < columnMap.get(inputKey.charAt(0)).size(); ++j)
		{
			for(int i = 0; i < columnMap.size(); ++i)
			{
				writer.write(columnMap.get(inputKey.charAt(i)).get(j) + " ");
				if(!columnMap.get(inputKey.charAt(i)).get(j).equals("-"))
				{
					str += columnMap.get(inputKey.charAt(i)).get(j);
				} // if(!columnMap.get(inputKey.charAt(i))[j].equals("-"))
				if(str.length() == 2)
				{
					listForFreqAna.add(str);
					//System.out.print(str + " ");
					str = "";
				} // if(str.length() == 2)
				
			} // for(int i = 0; i < columnMap.size(); ++i)
			writer.newLine();
			//System.out.println();
		} // for(int j = 0; j < columnMap.get(inputKey.charAt(0)).length; ++j)
		
		Resources.closeFile(writer, "adfgxSolved");
		
		//System.out.println("*SORTING COMPLETE.*");
	} /** private static void addToListFreqAna(String inputKey) throws FileNotFoundException **/
	
	/************************************************************************
	 * 
	 * !!NOTE!! This method has you decide if it does row or column first
	 * 
	 * For the size of our list of letter pairs, if the letter pair does not
	 * 	contain any dashes (Our null identifier), we cycle through our letter
	 *  pairs and save each letter and then use them to index into our 5x5 
	 *  mixed alphabet matrix. !!NOTE!! We use a method below for this -
	 *  ADFGX_Subs(char aChar). 
	 *  
	 * 
	 * @param String[][] mixedAlphabet, BufferedWriter writer, String rowOrColumnFirst 
	 * 	
	 * 	rowOrColumnFirst - Either enter "row" or "column" to do either row first
	 * 		or column first respectively
	 * @throws IOException 
	 */
	public static void constructPhrase(String[][] mixedAlphabet, BufferedWriter writer, String alphabetIndexes, String letterIndexes, String actualLetters) throws IOException
	{
		String holderForText = "";
		for(int i = 0; i < listForFreqAna.size(); ++i)
		{
			if(!listForFreqAna.get(i).contains("-"))
			{
				int[] tempHolder = getAlphabetIndexFromListIndex(i);
				if(!mixedAlphabet[tempHolder[0]][tempHolder[1]].equals("-"))
				{
					holderForText += mixedAlphabet[tempHolder[0]][tempHolder[1]];
				}
				else
				{
					holderForText += "[" + ADFGX_FROM_INDEX(tempHolder) + "]";
				}				
			} // if(!listForFreqAna.get(i).contains("-"))
			
		} // for(int i = 0; i < listForFreqAna.size(); ++i)

		if(holderForText.toLowerCase().contains(wordToSearchFor))
		{
			writer.write(holderForText + "  \n-  " + alphabetIndexes + "  -  " + letterIndexes + "  -  " + actualLetters);
			writer.newLine();
		}	
	} /** private static void constructPhrase(String[][] mixedAlphabet, PrintWriter writer, String rowOrColumnFirst) **/
	
	/************************************************************************
	 * 
	 * We switch on the value given by the parameter and return an integer
	 *  depending on the letter value we are given.
	 *  
	 * 
	 * @param char value
	 * @returns tempVal
	 */
	public static int ADFGX_Subs(char value)
	{
		int tempVal;
		switch(value)
		{
		case 'A':
			tempVal = 0;
			break;
		case 'D':
			tempVal = 1;
			break;
		case 'F':
			tempVal = 2;
			break;
		case 'G':
			tempVal = 3;
			break;
		case 'X':
			tempVal = 4;
			break;
		default:
			tempVal = -1;
			break;
		} // switch(value)
		return tempVal;
	} /** private static int ADFGX_Subs(char value) **/
	
	public static String ADFGX_FROM_INDEX(int[] index)
	{
		String tempStringVal = "";
		for(int i = 0; i < index.length; ++i)
		{
			switch(index[i])
			{
			case 0:
				tempStringVal += "A";
				break;
			case 1:
				tempStringVal += "D";
				break;
			case 2:
				tempStringVal += "F";
				break;
			case 3:
				tempStringVal += "G";
				break;
			case 4:
				tempStringVal += "X";
				break;
			default:
				tempStringVal += "P";
				break;
			} // switch(value)
		}
		return tempStringVal;
	} /** private static int ADFGX_Subs(char value) **/
	
}
