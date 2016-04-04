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
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

public class ADFGX {

	static Alphabet alphaClass = new Alphabet();
	static Map<Character, ArrayList<String>> columnMap = new HashMap<Character, ArrayList<String>>();
	static Map<String, Integer> freqTracker = new HashMap<String, Integer>();
	static ArrayList<String> listForFreqAna = new ArrayList<String>();
	public static String line = "";
	static int finalRowCount = 0;
	public static String wordToSearchFor = "";
	public static Map<Integer, String> words = new HashMap<Integer, String>();
	public static File currentFile;
	public static Queue<String> writeToFileQueue = new LinkedList<String>();
	
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
		
		//AbcAbc.checkAbcAbc("ring", "ing");
		finalRowCount = getLengthOfColumns("abcdef");
		boolean finished = false;
		
		while(!finished)
		{
			Resources.startTimer();
			
			BufferedReader reader = Resources.openFile_Reader("CipherKeys");
			System.out.print("What words would you like to look for? ");
			@SuppressWarnings("resource")
			Scanner keyboard = new Scanner(System.in);
			int tempCounter = 0;
			while(true)
			{
				System.out.println("Type \'no\' to continue");
				wordToSearchFor = keyboard.nextLine();
				if(wordToSearchFor.toLowerCase().equals("no"))
				{
					break;
				}
				words.put(tempCounter, wordToSearchFor);
				System.out.println(wordToSearchFor + " Added At Index " + tempCounter);
				++tempCounter;
			}
			
			int filesKept = 0;
			int counter = 1;
			
			while((line = reader.readLine()) != null)
			{
				System.out.println("****" + line.toUpperCase() + "****  -   " + counter);
				
				String inputKeyword = line;
					
				sortBasedOnKeyword(inputKeyword.toUpperCase());
				/*System.out.println("LIST " + listForFreqAna);
				LetterFrequency.frequencyAnalysis(listForFreqAna);
				System.out.println("FREQ TRACK: " + freqTracker);
				Alphabet.sortMap(freqTracker);
				
				if(currentFile.length() == 0)
				{
					System.out.println("EMPTY - DELETING");
					currentFile.delete();
				}
				else
				{
					++filesKept;
				}*/
				
				++counter;
				clear();
			}
			
			reader.close();
			
			Resources.endTimer();
			System.out.println();
			System.out.println("Number of file(s) kept: " + filesKept); 
			System.out.println("Y - Another One; N - Stop");
			String decision = keyboard.nextLine();
			switch(decision.toLowerCase())
			{
			case "y":
				break;
			case "n":
				finished = true;
				break;
			default:
				finished = true;
				break;
			}
		}
		
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
	
		ArrayList<ArrayList<String>> holderForText = new ArrayList<ArrayList<String>>();
		//System.out.println("FINAL " + finalRowCount);
		for(int i = 0; i < finalRowCount; i++)  
		{
	        holderForText.add(new ArrayList<String>());
	    } // for(int i = 0; i < individualRow; i++)  
		
		int col = 0;
		
		BufferedReader readerMapped = Resources.openFile_Reader("adfgxMapped");
		String lineMapped = "";

		while((lineMapped = readerMapped.readLine()) != null)
		{
			//System.out.println(lineMapped);
			for(String part : lineMapped.split("\\s+"))
			{	
				holderForText.get(col).add(part);
				//System.out.println(part);
				++col;
			} // for(String part : line)
			col = 0;
		} // for(String line : Files.readAllLines)
		//Hey
		//int tempInt = columnDelim.length() - 1;
		
		for(int tempCol = 0; tempCol < columnDelim.length(); ++tempCol)
		{
			columnMap.put(columnDelim.charAt(tempCol), holderForText.get(tempCol));
			//if(tempInt > 0)
			//{
			//	--tempInt;
			//}
		} // for(int tempCol = 0; tempCol < colDelim.length(); ++tempCol)
		
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
		String[][] mappedADFGX = new String[12][6];
		
		for(int i = 0; i < mappedADFGX.length; ++i)
		{
			for(int j = 0; j < mappedADFGX[i].length; ++j)
			{
				mappedADFGX[i][j] = "-";
			}
		}
		
		// Gets Rows
		while((lineCipherText = readerCipherText.readLine()) != null)
		{
			for(String part : lineCipherText.split("\\s+"))
			{
				for(int i = 0; i < part.length(); ++i)
				{
					mappedADFGX[trackOfRows][individualRow] = Character.toString(part.charAt(i));
					++individualRow;
					if(individualRow == columnDelim.length())
					{
						++trackOfRows;
						if(trackOfRows == 12)
							break;
						individualRow = 0;
					} // if(trackOfRows == colDelim.length())
					
				} // for(int i = 0; i < part.length(); ++i)

			} // for(String part : line)
			
		} // for(String line : Files.readAllLines)
		
		for(int i = 0; i < mappedADFGX.length; ++i)
		{
			for(int j = 0; j < mappedADFGX[i].length; ++j)
			{
				writer.write(mappedADFGX[i][j] + " ");
				System.out.print(mappedADFGX[i][j] + " ");
			}
			writer.newLine();
			System.out.println();
		}
			
		Resources.closeFile(writer, "adfgxMapped");
		Resources.closeFile(readerCipherText, "adfgxCipherText");
		//System.out.println(individualRow);
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
	
		for(int j = 0; j < columnMap.get(inputKey.charAt(0)).size(); ++j)
		{
			for(int i = 0; i < columnMap.size(); ++i)
			{
				if(columnMap.containsKey(inputKey.charAt(i)))
				{
					//System.out.print(columnMap.get(inputKey.charAt(i)).get(j) + " ");
					writer.write(columnMap.get(inputKey.charAt(i)).get(j) + " ");
					if(!columnMap.get(inputKey.charAt(i)).get(j).equals("-"))
					{
						str += columnMap.get(inputKey.charAt(i)).get(j);
					} // if(!columnMap.get(inputKey.charAt(i))[j].equals("-"))
					if(str.length() == 2)
					{
						listForFreqAna.add(str);
						str = "";
					} // if(str.length() == 2)
				}
			} // for(int i = 0; i < columnMap.size(); ++i)
			//System.out.println();
			writer.newLine();
		} // for(int j = 0; j < columnMap.get(inputKey.charAt(0)).length; ++j)
		
		Resources.closeFile(writer, "adfgxSolved");
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
			int[] tempHolder = getAlphabetIndexFromListIndex(i);
			if(!mixedAlphabet[tempHolder[0]][tempHolder[1]].equals("-"))
			{
				holderForText += mixedAlphabet[tempHolder[0]][tempHolder[1]];
			}
			else
			{
				holderForText += "[" + ADFGX_FROM_INDEX(tempHolder) + "]";
			}				

			
		} // for(int i = 0; i < listForFreqAna.size(); ++i)
		//System.out.println(holderForText);
		boolean containsAny = false;
		
		for(int i = 0; i < words.size(); ++i)
		{
			if(holderForText.toLowerCase().contains(words.get(i)))
			{
				containsAny = true;
				//writer.write(holderForText + "  \n-  " + alphabetIndexes + "  -  " + letterIndexes + "  -  " + actualLetters);
				//writer.newLine();
				//break;
			}	
		}
		
		if(containsAny)
		{
			//writer.write(holderForText + "  \n-  " + alphabetIndexes + "  -  " + letterIndexes + "  -  " + actualLetters);
			//writer.newLine();
			//System.out.println(holderForText);
			writeToFileQueue.add(holderForText);
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
