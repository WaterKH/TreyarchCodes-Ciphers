/****************************************************************************
 * Author: @author peterclark - All Rights Reserved
 * Program: ADFGX Cipher Decryption Tool
 * 
 * Performs all functions that are included with the ADFGX Cipher on MotD
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
	static ArrayList<String> letterPairs = new ArrayList<String>();
	public static String line = "";
	static int finalRowCount = 6;
	public static String wordToSearchFor = "";
	public static Map<Integer, String> words = new HashMap<Integer, String>();
	public static File currentFile;
	public static Queue<String> writeToFileQueue = new LinkedList<String>();
	
	public static int uniqueLetters = 0;
	public static Map<String, String> letterPair_Map = new HashMap<String, String>();
	static String alphabet = "abcdefghijklmnopqrstuvwxyz";
	
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
		boolean finished = false;
		
		while(!finished)
		{
			Resources.startTimer();
			preinitColumnarTransposition("abcdef");
			
			BufferedReader reader = Resources.openFile_Reader("CipherKeys2");
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
			
			BufferedWriter writer = Resources.openFile_Writer("adfgxPhrases");
			Map<Integer, Map<String, String>> holderForChars = new HashMap<Integer, Map<String, String>>();
			int holderCounter = 0;
			
			while((line = reader.readLine()) != null)
			{
				System.out.println("****" + line.toUpperCase() + "****  -   " + counter);
				
				String inputKeyword = line;
				
				initColumnarTransposition(inputKeyword.toUpperCase());
				
				Map<String, String> charsInCipher = new HashMap<String, String>();
				int charCounter = 0;
				
				for(int i = 0; i < letterPairs.size(); ++i)
				{
					System.out.print(letterPairs.get(i) + " ");
					if(!charsInCipher.containsKey(letterPairs.get(i)))
					{
						charsInCipher.put(letterPairs.get(i), Character.toString(alphabet.charAt(charCounter)));
						++charCounter;
					}
				}
				System.out.println();
				
				holderForChars.put(holderCounter, charsInCipher);
				++holderCounter;
				
				uniqueLetters = 0;
				//ADFGX_BruteForce.permutation("abcd");//efghijklmnopqrstuvwxyz");
				
				/**
				 * For the first n (4) letters in the ciphertext
				 */
				
				//String firstChars = "----";
				//char[] firstCharArr = new char[firstChars.length()];
				
				//ADFGX_BruteForce.createCombinations(ADFGX_BruteForce.alphabet, firstCharArr.length, 0, firstCharArr);
				
				/*/**
				 * For the sequence in the middle of the ciphertext
				 
				String seqChars = "abcd";
				char[] seqCharArr = new char[seqChars.length() - 1];
				
				ADFGX_BruteForce.createCombinations(ADFGX_BruteForce.alphabet, seqCharArr.length, 0, seqCharArr);
				*/
				//for(int i = 0; i < ADFGX_BruteForce.combinationsList.size(); ++i)
				//{
				//	ADFGX_BruteForce.createPermutations(ADFGX_BruteForce.combinationsList.get(i), writer, 0, false);
				//}
				
				/*String secondChars = "----";
				char[] secondCharArr = new char[secondChars.length()];
				
				ADFGX_BruteForce.createCombinations(ADFGX_BruteForce.alphabet, secondCharArr.length, 0, secondCharArr);
				
				for(int i = 0; i < ADFGX_BruteForce.combinationsList.size(); ++i)
				{
					ADFGX_BruteForce.createPermutations(ADFGX_BruteForce.combinationsList.get(i), writer, firstChars.length(), true);
				}*/
				
				//ADFGX_BruteForce.assignedLetters.clear();
				//ADFGX_BruteForce.currAlphabet = "";
				//ADFGX_BruteForce.combinationsList.clear();
				
				/*for(int i = 0; i < ADFGX_BruteForce.combinationsList.size(); ++i)
				{
					//ADFGX_BruteForce.currCombination = ADFGX_BruteForce.combinationsList.get(i);
					
					ADFGX_BruteForce.permutation(ADFGX_BruteForce.combinationsList.get(i));
				}/*
				
				
				//Alphabet alphabet = new Alphabet(line);
				
				//constructPhrase(alphabet.alphabet, writer, "", "", "");
			
				/*System.out.println("LIST " + letterPairs);
				LetterFrequency.frequencyAnalysis(letterPairs);
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
			
			for(int i = 0; i < holderForChars.size(); ++i)
			{
				for(int j = 0; j < holderForChars.size(); ++j)
				{
					for(int k = 0; k < letterPairs.size(); ++k)
					{
						String str1 = holderForChars.get(i).get(letterPairs.get(k));
						String str2 = holderForChars.get(j).get(letterPairs.get(k));
						
						System.out.print(str1 + " " + str2 + " ");
					}
					System.out.println();
				}
			}
		
			writer.close();
			
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
	 * Clears holderForText, columnMap, freqTracker, and letterPairs
	 * 
	 */
	public static void clear()
	{
		columnMap.clear();
		freqTracker.clear();
		letterPairs.clear();
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
	 * 	!!NOTE!! This should be used with a loop over letterPairs size
	 *  
	 * 
	 * @param int index
	 * @returns tempHolder
	 */
	public static int[] getAlphabetIndexFromLetterPair(int index)
	{
		int[] polybiusSquareIndex = new int[2];
		
		for(int k = 0; k < letterPairs.get(index).length(); ++k)
		{
			polybiusSquareIndex[k] = ADFGX_Subs(letterPairs.get(index).charAt(k));
		} // for(int k = 0; k < letterPairs.get(i).length(); ++k)
		
		return polybiusSquareIndex;
	} /** public static int[] getAlphabetIndexFromListIndex(int index) **/
	
	/************************************************************************
	 * 
	 * Accessor
	 * 
	 * Returns the index of where the letter we want should be. 
	 * 	!!NOTE!! This should be used with a loop over letterPairs size
	 *  
	 * 
	 * @param String chars
	 * @returns tempHolder
	 */
	public static int[] getAlphabetIndexFromCharacters(String chars)
	{
		int[] polybiusSquareIndex = new int[2];
		
		for(int k = 0; k < chars.length(); ++k)
		{
			polybiusSquareIndex[k] = ADFGX_Subs(chars.charAt(k));
		} // for(int k = 0; k < chars.length(); ++k)
		
		return polybiusSquareIndex;
	} /** public static int[] getAlphabetIndexFromCharacters(String chars) **/
	
	/************************************************************************
	  * 
	  * We create two integer variables - one that tracks rows and one that 
	  *	  we will return as our row length. We then open our ciphertext and 
	  *	  create a file "adfgxMapped." We create a string will be our line  
	  *	  in the ciphertext. We also create a pre-defined array since we
	  *   know what our ciphertext size is and fill it with dummy values.
	  *   
	  * While we still have text, we split each string by whitespace+ and then
	  *  run an array on that part's length. We then add to our pre-defined array
	  *  the character at the current index. This is down until we reach the end
	  *  of our row, then we go to the next row and repeat. Once our numberOfRows
	  *  is equal to 12, we break out of this loop.
	  * 
	  * Print out the results to a file/ console and close the files. 
	  *  
	  *  
	  *  @throws IOException 
	  */
	private static void preinitColumnarTransposition(String columnDelim) throws IOException
	{
		int numberOfRows = 0;
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
					mappedADFGX[numberOfRows][individualRow] = Character.toString(part.charAt(i));
					++individualRow;
					
					if(individualRow == columnDelim.length())
					{
						++numberOfRows;
						
						if(numberOfRows == 12)
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
	}
	
	/************************************************************************
	 * 
	 * We take in an input key which we convert to a char array to sort, then
	 * 	convert it back to a string. We open a file named "adfgxMapped"
	 *  The text wrap is dependent upon the inputKey's length. We then create a 
	 *  double array based on our column length and our row size and read in from
	 *  the "adfgxMapped" to create a matrix that we can then use to create
	 *  a map of our columns so that we can create the "adfgxSolved" file.
	 *  
	 * We then create "adfgxSolved" and an empty String. We run a double loop
	 *  on our row size(j) and column size(i) and write to file the correct
	 *  positioning of the columns.
	 * 
	 * @param String inputKey
	 * @throws IOException
	 */
	public static void initColumnarTransposition(String inputKey) throws IOException
	{	
		String lineMapped = "";
		int col = 0;
		BufferedReader readerMapped = Resources.openFile_Reader("adfgxMapped");
		
		ArrayList<ArrayList<String>> holderForText = new ArrayList<ArrayList<String>>();
		char[] individualChars = inputKey.toCharArray();
		
		for(int i = 0; i < finalRowCount; i++)  
		{
	        holderForText.add(new ArrayList<String>());
	    } // for(int i = 0; i < finalRowCount; i++)  
		
		while((lineMapped = readerMapped.readLine()) != null)
		{
			for(String part : lineMapped.split("\\s+"))
			{	
				holderForText.get(col).add(part);

				++col;
			} // for(String part : line)
			col = 0;
		} // while((lineMapped = readerMapped.readLine()) != null)
		
		Resources.closeFile(readerMapped, "adfgxMapped");
		
		Arrays.sort(individualChars);
		String columnDelim = new String(individualChars);
		
		for(int tempCol = 0; tempCol < columnDelim.length(); ++tempCol)
		{
			columnMap.put(columnDelim.charAt(tempCol), holderForText.get(tempCol));
		} // for(int tempCol = 0; tempCol < colDelim.length(); ++tempCol)
		
		
		BufferedWriter writer = Resources.openFile_Writer("adfgxSolved" + inputKey);
		BufferedWriter writer_Letters = Resources.openFile_Writer("letterPairs" + inputKey);
		String str = "";
		Map<String, Integer> contained = new HashMap<String, Integer>();
	
		for(int j = 0; j < columnMap.get(inputKey.charAt(0)).size(); ++j)
		{
			for(int i = 0; i < columnMap.size(); ++i)
			{
				if(columnMap.containsKey(inputKey.charAt(i)))
				{
					writer.write(columnMap.get(inputKey.charAt(i)).get(j) + " ");
					
					if(!columnMap.get(inputKey.charAt(i)).get(j).equals("-"))
					{
						str += columnMap.get(inputKey.charAt(i)).get(j);
					} // if(!columnMap.get(inputKey.charAt(i))[j].equals("-"))
					
					if(str.length() == 2)
					{
						letterPairs.add(str);
						writer_Letters.write(str + " ");
						if(!contained.containsKey(str))
						{
							contained.put(str, 0);
							++uniqueLetters;
						}
						str = "";
					} // if(str.length() == 2)
					
				} // if(columnMap.containsKey(inputKey.charAt(i)))
				
			} // for(int i = 0; i < columnMap.size(); ++i)
			writer.newLine();
		} // for(int j = 0; j < columnMap.get(inputKey.charAt(0)).length; ++j)
		Resources.closeFile(writer_Letters, "letterPairs");
		Resources.closeFile(writer, "adfgxSolved");
		//System.out.println(letterPairs.size());
		//currentFile.delete();
	} /** public static void sortColumnarTransposition(String inputKey) throws IOException **/
	
	
	/************************************************************************
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

		for(int i = 0; i < letterPairs.size(); ++i)
		{
			int[] tempHolder = getAlphabetIndexFromLetterPair(i);
			if(!mixedAlphabet[tempHolder[0]][tempHolder[1]].equals("-"))
			{
				holderForText += mixedAlphabet[tempHolder[0]][tempHolder[1]];
			}
			else
			{
				holderForText += "[" + ADFGX_FROM_INDEX(tempHolder) + "]";
			}				

			
		} // for(int i = 0; i < letterPairs.size(); ++i)
		
		//for(int i = 0; i < words.size(); ++i)
		//{
			//if(holderForText.toLowerCase().contains(words.get(i)))
			//{
				writeToFileQueue.add(holderForText);
				writer.write(holderForText);// + "  \n-  " + alphabetIndexes + "  -  " + letterIndexes + "  -  " + actualLetters);
				writer.newLine();
				//break;
			//}	
		//}
		
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
