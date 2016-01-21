/****************************************************************************
 * Author: @author peterclark - All Rights Reserved
 * Program: ADFGX Cipher Decryption Tool
 * 
 * !! Not sure why this is in a separate class - Maybe I was going to do more with it !!
 */
package source;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LetterFrequency {

	public static final String MOST_FREQ_LETTER = "E"; // Used for top letter
	public static final String VERY_FREQ_LETTERS = "TAOI"; // Mix this in if there are multiple top letters - otherwise this will be second
	public static final String FREQ_LETTERS = "NSHR"; // Used for Mid range 3s
	public static final String INFREQ_LETTERS = "DL"; // Used for 2s
	public static final String VERY_INFREQ_LETTERS = "CUMWFGYPBVK"; // Used for 2s
	//public static final String MOST_INFREQ_LETTERS = "JXQZ";
	static int counter = 0;
	static Map<String, String> charsBasedOnFrequency = new HashMap<String, String>();
	static ArrayList<String> freqPairs = new ArrayList<String>();
	
	public static final String[] frequencyStrings = {MOST_FREQ_LETTER, VERY_FREQ_LETTERS, FREQ_LETTERS, 
													 INFREQ_LETTERS, VERY_INFREQ_LETTERS};//, MOST_INFREQ_LETTERS};
	
	/************************************************************************
	 * 
	 * We first create an array from our ArrayList - minor speed difference.
	 * 	We then create an array of integers to store the number of times
	 *  an item appears. We also create another ArrayList to store our 
	 *  already used letter pairs. We loop through our array of letter
	 *  pairs and check if we have that letter pair in our ArrayList.
	 *  If it is not, then we cycle through the array again and count
	 *  how many times we have it in our array. We then add our current
	 *  pair and the number of times it appears to a frequency tracker
	 *  and then add the letter pair to our ArrayList of used letter pairs.
	 *  
	 * 
	 * @param freqAnalysisList
	 */
	public static void frequencyAnalysis(ArrayList<String> freqAnalysisList)
	{
		System.out.println("*** FREQUENCY ANALYSIS ***");
		// Create an array for faster access
		String[] arrFreqA = new String[freqAnalysisList.size()];
		arrFreqA = freqAnalysisList.toArray(arrFreqA);
		//for(int i = 0; i < arrFreqA.length; ++i)
			//System.out.print(arrFreqA[i] + " ");
		//System.out.println();
		int[] freqCounter = new int[arrFreqA.length];
		
		ArrayList<String> alreadyUsed = new ArrayList<String>();
		
		for(int currPair = 0; currPair < arrFreqA.length; ++currPair)
		{
			boolean findNextPair = true;
			
			for(String str : alreadyUsed)
			{
				if(arrFreqA[currPair].equals(str))
				{
					findNextPair = false;
					break;
				} // if(arrFreqA[currPair].equals(str))
				
			} // for(String str : alreadyUsed)
			
			if(findNextPair == true)
			{
				for(int nextPair = 0; nextPair < arrFreqA.length; ++nextPair)
				{
					if(arrFreqA[currPair].equals(arrFreqA[nextPair]))
					{
						++freqCounter[currPair];
					} // if(arrFreqA[currPair].equals(arrFreqA[nextPair]))
					
				} // for(int nextPair = 0; nextPair < arrFreqA.length; ++nextPair)
				ADFGX.freqTracker.put(arrFreqA[currPair], freqCounter[currPair]);
				freqPairs.add(arrFreqA[currPair]);
				alreadyUsed.add(arrFreqA[currPair]);
			} // if(findNextPair == true)
				
		} // for(int currPair = 0; currPair < arrFreqA.length; ++currPair)
		
		//System.out.println();
		//System.out.println(ADFGX.freqTracker);
		//System.out.println();
	} /** private static void frequencyAnalysis(ArrayList<String> freqAnalysisList) **/
	
	public static void distributeLetters(Map<String, Integer> paramMap) throws IOException
	{

		System.out.println("Entering distributeLetters...");
		
		int[] maxIntHolder = new int[paramMap.size()];
		String[] stringHolder = new String[paramMap.size()];
		ArrayList<String> containedStrings = new ArrayList<String>();
		
		//Move frequency numbers to front (ie - [6,5,4,3,2,2])
		for(int i = 0; i < maxIntHolder.length; ++i)
		{
			for(int j = 0; j < freqPairs.size(); ++j)
			{
				if(paramMap.get(freqPairs.get(j)) != null)
				{
					if(maxIntHolder[i] < paramMap.get(freqPairs.get(j)) && !containedStrings.contains(freqPairs.get(j)))
					{
						maxIntHolder[i] = paramMap.get(freqPairs.get(j));
						stringHolder[i] = freqPairs.get(j);
						//System.out.println("FINAL: " + paramMap.get(freqPairs.get(j)) + " " + maxIntHolder[i]);
						containedStrings.add(freqPairs.get(j));
						break;
					}
				}
			}
		}

		//Bubble sort
		for(int i = 0; i < maxIntHolder.length; ++i)
		{
			for(int j = 0; j < maxIntHolder.length; ++j)
			{
				if(maxIntHolder[i] > maxIntHolder[j])
				{
					int swapInt = maxIntHolder[i];
					maxIntHolder[i] = maxIntHolder[j];
					maxIntHolder[j] = swapInt;
					
					String swapString = stringHolder[i];
					stringHolder[i] = stringHolder[j];
					stringHolder[j] = swapString;
				}
			}
		}
		
		Map<String, ArrayList<Integer>> distributedAlphabet = assignLettersToFrequencyNumbers(maxIntHolder, stringHolder);
		
		int tempInt = 0;
		int stringTracker = 0;
		int[] index = new int[frequencyStrings.length];
		
		for(int i = 0; i < maxIntHolder.length; ++i)
		{
			//System.out.println(frequencyStrings[stringTracker]);
			//permuteString(frequencyStrings[stringTracker], stringTracker, i, index, stringHolder);
			
			if(!(maxIntHolder[i] == tempInt) && stringTracker + 1 < frequencyStrings.length)
			{
				++stringTracker;
				System.out.println("Curr:" + frequencyStrings[stringTracker]);
			}
			else if(index[stringTracker] + 1 < frequencyStrings[stringTracker].length())
			{
				++index[stringTracker];
			}
			
			tempInt = maxIntHolder[i];
		}
		
		//System.out.println("CHARS: " + charsBasedOnFrequency);
		
	}
	
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
	public static void permuteString(String stringToUse, int stringTracker, int indexOfStringTracker, int[] currIndex, String[] stringHolder) throws IOException
	{
		System.out.println("ENTERING PERMUTATIONS");
		System.out.println(counter);
		permuteString("", stringToUse, stringTracker, indexOfStringTracker, currIndex, stringHolder);
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
	public static void permuteString(String beginningString, String endingString, int stringTracker, 
									   int indexOfStringTracker, int[] currIndex, String[] stringHolder) throws IOException 
	{
		if (endingString.length() <= 1)
	    {
			charsBasedOnFrequency.put(stringHolder[indexOfStringTracker], Character.toString(frequencyStrings[stringTracker].charAt(currIndex[stringTracker])));
			//System.exit(0);
	    } // if (endingString.length() <= 1)
	    else
	    {
	    	for (int i = 0; i < endingString.length(); i++) 
	    	{
	    		try 
	    		{
	    			String newString = endingString.substring(0, i) + endingString.substring(i + 1);
	    			++counter;
	    			permuteString(beginningString + endingString.charAt(i), newString, stringTracker, indexOfStringTracker, currIndex, stringHolder);
		        } // try
	    		catch (StringIndexOutOfBoundsException exception) 
	    		{
		        	exception.printStackTrace();
		        } // catch (StringIndexOutOfBoundsException exception)
	    		
	    	} // for (int i = 0; i < endingString.length(); i++) 
	    	
	    } // else
	} /** public static void permuteString(String beginningString, String endingString, BufferedWriter writer) throws IOException  **/
	
	public static Map<String, ArrayList<Integer>> assignLettersToFrequencyNumbers(int[] frequencyNumbers, String[] letterPairs)
	{

		System.out.println("ENTERING ASSIGN METHOD");
		int lastFreqNumber = 0;
		int lastIndex = 0;
		Map<String, ArrayList<Integer>> distributedAlphabet = new HashMap<String, ArrayList<Integer>>();
		int stringIndex = 0;
		int counter = 0;
		
		//TODO Write a recursive method for finding each letter of the alphabet
		for(int i = 0; i < letterPairs.length; ++i)
		{
			ArrayList<Integer> counterHolder = new ArrayList<Integer>();
			
			for(int j = 0; j < frequencyNumbers.length; ++j)
			{
				if(ADFGX.freqTracker.get(letterPairs[i]) == frequencyNumbers[j] || 
				   ADFGX.freqTracker.get(letterPairs[i]) == frequencyNumbers[j] + 1 || 
				   ADFGX.freqTracker.get(letterPairs[i]) == frequencyNumbers[j] - 1)
				{
					if(!counterHolder.contains(frequencyNumbers[j]))
						counterHolder.add(frequencyNumbers[j]);
					distributedAlphabet.put(letterPairs[i], counterHolder);
				}
			}
			System.out.println(distributedAlphabet);
		}
		permutateOverLetters(distributedAlphabet, letterPairs);
		System.out.println("LEAVING ASSIGN METHOD");
		return distributedAlphabet;
	}
	
	public static void permutateOverLetters(Map<String, ArrayList<Integer>> availableLetters, String[] letterPairs)
	{
		
 		int[] perms = new int[availableLetters.size()];
		int[] radices = new int[availableLetters.size()];
		
		System.out.println(perms.length + " " + radices.length);
		System.out.println();
		System.out.println(availableLetters.size());
		System.out.println();
		System.out.println("RADIXES");
		for(int i = 0; i < availableLetters.size(); ++i)
		{		
			radices[i] = availableLetters.get(letterPairs[i]).size(); // Use a for loop to access(?)
			System.out.print(radices[i] + " ");
		}
		System.out.println();
		//int limitOfPermutations = frequencyStrings.length;
		
		boolean finished = false;
		//Permutates over which alphabet to use
		while(!finished)
		{	
			int[] permsInner = new int[availableLetters.size()];
			int[] radicesInner = new int[availableLetters.size()];
			boolean done = false;
			for(int i = 0; i < frequencyStrings.length; ++i)
			{
				radicesInner[i] = frequencyStrings[i].length();
			}
			
			//Permutates over the letters of the alphabet
			while(!done)
			{	
				System.out.println("CURR LETTERS");
				for(int i = 0; i < radicesInner.length; ++i)
				{
					if(frequencyStrings[perms[i]].length() > permsInner[i])
						System.out.print(frequencyStrings[perms[i]].charAt(permsInner[i]) + " " + permsInner[i] + " ");
					else
					{
						System.out.print("N/A ");
						done = true; 
					}
				}
				System.out.println();
				
				permsInner[0] += 1;
			    if (permsInner[0] == radicesInner[0])
			    {
			      int radixsub = 0;
			      while (radicesInner[radixsub] == permsInner[radixsub])
			      {
			    	permsInner[radixsub] = 0;
			        radixsub += 1;
			        if (radixsub >= radicesInner.length)
			        {
			        	System.out.println("FINISHED");
			        	done = true;
			            break;
			        } // if (radixsub >= perms.size())
			        permsInner[radixsub] += 1;
			      } // while (perms.at(radixsub) == limitOfPermutations)
			    } // if (perms.at(0) == limitOfPermutations)
			}
			
			perms[0] += 1;
		    if (perms[0] == radices[0])
		    {
		      int radixsub = 0;
		      while (radices[radixsub] == perms[radixsub])
		      {
		        perms[radixsub] = 0;
		        radixsub += 1;
		        if (radixsub >= radices.length)
		        {
		        	System.out.println("FINISHED");
		        	finished = true;
		            break;
		        } // if (radixsub >= perms.size())
		        perms[radixsub] += 1;
		      } // while (perms.at(radixsub) == limitOfPermutations)
		    } // if (perms.at(0) == limitOfPermutations)
		}
	}
	
	public void permutateOverLetters()
	{
		
	}
}
