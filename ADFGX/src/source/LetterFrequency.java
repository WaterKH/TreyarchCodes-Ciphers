/****************************************************************************
 * Author: @author peterclark - All Rights Reserved
 * Program: ADFGX Cipher Decryption Tool
 * 
 * !! Not sure why this is in a separate class - Maybe I was going to do more with it !!
 */
package source;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LetterFrequency {

	public static final String MOST_FREQ_LETTER = "E"; // Used for top letter
	public static final String VERY_FREQ_LETTERS = "TAOI"; // Mix this in if there are multiple top letters - otherwise this will be second
	public static final String FREQ_LETTERS = "NSHR"; // Used for Mid range 3s
	public static final String INFREQ_LETTERS = "DL"; // Used for 2s
	public static final String VERY_INFREQ_LETTERS = "CUMWF"; // 2s as well?
	//public static final String VERY_VERY_INFREQ_LETTERS = "GYPBVK";
	//public static final String MOST_INFREQ_LETTERS = "JXQZ";
	static int counter = 0;
	static Map<String, String> charsBasedOnFrequency = new HashMap<String, String>();
	static ArrayList<String> freqPairs = new ArrayList<String>();
	
	public static final String[] frequencyStrings = {MOST_FREQ_LETTER, VERY_FREQ_LETTERS, FREQ_LETTERS, 
													 INFREQ_LETTERS, VERY_INFREQ_LETTERS};//,VERY_VERY_INFREQ_LETTERS, MOST_INFREQ_LETTERS};
	public static Map<Integer, String> alphabetContained = new HashMap<Integer, String>();
	
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
		//System.out.println("*** FREQUENCY ANALYSIS ***");
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

		//System.out.println("Entering distributeLetters...");
		
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
		
		assignLettersToFrequencyNumbers(maxIntHolder, stringHolder);		
	}
	
	public static void assignLettersToFrequencyNumbers(int[] frequencyNumbers, String[] letterPairs) throws IOException
	{

		//System.out.println("ENTERING ASSIGN METHOD");
		Map<String, ArrayList<Integer>> distributedAlphabet = new HashMap<String, ArrayList<Integer>>();
		
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
			//System.out.println(distributedAlphabet);
		}
		permutateOverLetters(distributedAlphabet, letterPairs, frequencyNumbers);
		//System.out.println("LEAVING ASSIGN METHOD");
	}
	
	public static void permutateOverLetters(Map<String, ArrayList<Integer>> availableLetters, String[] letterPairs, int[] frequencyNumbers) throws IOException
	{
		BufferedWriter writer = Resources.openFile_Writer("adfgxPermutations", "JUST TO ACCESS ANOTHER METHOD");
		ArrayList<ArrayList<Integer>> tempIndexes = new ArrayList<ArrayList<Integer>>();
		ArrayList<ArrayList<Integer>> indexes = new ArrayList<ArrayList<Integer>>();
		
		for(int i = 0; i < availableLetters.size(); ++i)
		{
			tempIndexes.add(new ArrayList<Integer>());
			indexes.add(new ArrayList<Integer>());
		}
		
		int maxNumb = 0;
		
		//Changes the frequency numbers to indexes in order to correctly find the right part of the alphabet
		for(int i = 0; i < frequencyNumbers.length; ++i)
		{
			for(int j = 0; j < availableLetters.get(letterPairs[i]).size(); ++j)
			{
				tempIndexes.get(i).add(availableLetters.get(letterPairs[i]).get(j)); 
				
				if(maxNumb < tempIndexes.get(i).get(j))
				{
					maxNumb = tempIndexes.get(i).get(j);
				}
			}
		}
		
		for(int i = 0; i < frequencyNumbers.length; ++i)
		{
			for(int j = 0; j < availableLetters.get(letterPairs[i]).size(); ++j)
			{
				indexes.get(i).add(maxNumb - tempIndexes.get(i).get(j));
			}
		}
	
		int[] perms = new int[indexes.size()];
		int[] radices = new int[indexes.size()];
		for(int i = 0; i < indexes.size(); ++i)
		{
			radices[i] = indexes.get(i).get(indexes.get(i).size() - 1) + 1;
			while(radices[i] > 4)
				radices[i] = radices[i] - 1;
		}
		
		int[] maxNumber = new int[perms.length];
		int[] minNumber = new int[perms.length];
		//Gets max and min numbers
		for(int i = 0; i < indexes.size(); ++i)
		{
			minNumber[i] = indexes.get(i).get(0);
			for(int j = 0; j < indexes.get(i).size(); ++j)
			{
				if(maxNumber[i] < indexes.get(i).get(j))
				{
					maxNumber[i] = indexes.get(i).get(j);
				}
				
			}
		}
	
		boolean finished = false;
		//Permutates over which alphabet to use
		while(!finished)
		{	
			// First number sequence that will register a hit
			//(perms[6] == 3 && perms[5] == 3 && perms[4] == 2 && perms[3] == 2)
			boolean done = false;
			
			for(int i = 0; i < indexes.size(); ++i)
			{
				if(!(perms[i] <= maxNumber[i]))// && perms[i] >= minNumber[i])
				{
					if(i - 1 > 0)
					{
						++perms[i - 1];
						System.out.print(i + " - ");
						//for(int j = 0; j < indexes.size(); ++j)
							//System.out.print(perms[j] + " ");
						//System.out.println();
						for(int j = i; j < indexes.size(); ++j)
						{
							perms[j] = 0;
						}
						//for(int j = 0; j < indexes.size(); ++j)
							//System.out.print(perms[j] + " ");
						//System.out.println();
						
					}
					i = 0;
				}
				if(!(perms[i] >= minNumber[i]))
				{
					
					++perms[i];
					//for(int j = 0; j < indexes.size(); ++j)
						//System.out.print(perms[j] + " ");
					//System.out.println();
					
					i = 0;
					//for(int j = 0; j < indexes.size(); ++j)
						//System.out.print(perms[j] + " ");
					//System.out.println();
				
				}
				/*else
				{
					done = true;
					break;
				}*/
			}
			int[] permsInner = new int[perms.length];
			int[] radicesInner = new int[radices.length];
			
			if(!done)
			{
				for(int i = 0; i < perms.length; ++i)
				{
					if(perms[i] >= 5)
					{
						perms[i] = 4;
					}
					radicesInner[i] = frequencyStrings[perms[i]].length();
				}
			}
			
			//Permutates over the letters of the alphabet
			while(!done)
			{	
				Map<Integer, String> contained = new HashMap<Integer, String>();
				boolean passed = true;
				
				for(int i = 0; i < radicesInner.length; ++i)
				{
					if(/*frequencyStrings[perms[i]].length() > permsInner[i] && */!contained.containsValue(Character.toString(frequencyStrings[perms[i]].charAt(permsInner[i]))))
					{
						//EMPTY - TODO FIND A WAY TO SPEED UP PERMUTATION TIME -- THOUGH THIS MAY BE THE FASTEST IT GETS....
					}
					else
					{
						passed = false;
						break;
					}
					contained.put(i, Character.toString(frequencyStrings[perms[i]].charAt(permsInner[i])));
				}
				
				if(passed == true)
				{
					String tempStr = "";
					for(int i = 0; i < contained.size(); ++i)
					{
						tempStr += contained.get(i);
					}
					
					if(!alphabetContained.containsValue(tempStr))
					{
						Alphabet alphabet = new Alphabet();
						String tempStringLetters = "L -> ";
						String actualLettersUsed = "";
						
						for(int i = 0; i < permsInner.length; ++i)
						{
							tempStringLetters += permsInner[i] + "-";
							
							int[] indexOfAlphabet = ADFGX.getAlphabetIndexFromCharacters(letterPairs[i]);
							alphabet.alphabet[indexOfAlphabet[0]][indexOfAlphabet[1]] = Character.toString(frequencyStrings[perms[i]].charAt(permsInner[i]));
							actualLettersUsed += alphabet.alphabet[indexOfAlphabet[0]][indexOfAlphabet[1]];
						}
						
						String tempStringAlphabet = "A -> "; 
						for(int i = 0; i < perms.length; ++i)
						{
							tempStringAlphabet += perms[i] + "-";
						}
						
						ADFGX.constructPhrase(alphabet.alphabet, writer, tempStringAlphabet, tempStringLetters, actualLettersUsed);
						
						alphabetContained.put(counter, tempStr);
						++counter;
					}
				}
				
				permsInner[0] += 1;
			    if (permsInner[0] >= radicesInner[0])
			    {
			      int radixsub = 0;
			      while (radicesInner[radixsub] <= permsInner[radixsub])
			      {
			    	permsInner[radixsub] = 0;
			        radixsub += 1;
			        if (radixsub >= radicesInner.length)
			        {
			        	done = true;
			            break;
			        } // if (radixsub >= perms.size())
			        permsInner[radixsub] += 1;
			      } // while (perms.at(radixsub) == limitOfPermutations)
			      
			    } // if (perms.at(0) == limitOfPermutations)
			    
			}
			
			perms[0] += 1;
			
		    if (perms[0] >= radices[0])
		    {
		      int radixsub = 0;
		      while (radices[radixsub] <= perms[radixsub])
		      {
		        perms[radixsub] = 0;
		        radixsub += 1;
		        if (radixsub >= radices.length)
		        {
		        	finished = true;
		            break;
		        } // if (radixsub >= perms.size())
		        perms[radixsub] += 1;
		      } // while (perms.at(radixsub) == limitOfPermutations)
		      
		    } // if (perms.at(0) == limitOfPermutations)
		    
		}
		
		Resources.closeFile(writer, "adfgxPermutations");
	}
}
