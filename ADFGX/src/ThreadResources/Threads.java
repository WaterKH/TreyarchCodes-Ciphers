package ThreadResources;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import source.ADFGX;
import source.Alphabet;
import source.LetterFrequency;

public class Threads extends Thread {
	
	public int[] alphabet;
	public String[] letterPairs;
	public BufferedWriter writer;
	
	public Threads(int[] alphabetFromPerms, String[] letterPairsFromPerms, BufferedWriter writerFromPerms)
	{
		alphabet = alphabetFromPerms;
		letterPairs = letterPairsFromPerms;
		writer = writerFromPerms;
		//for(int i = 0; i < alphabetSomething.length; ++i)
			//System.out.print(alphabetSomething[i]+ " ");
			// !Semaphores and mutexs - Look this up!
	}
	
	public void run() 
	{
		boolean done = false;
		
		/*for(int i = 0; i < alphabet.length; ++i)
		{
			if(alphabet[i] > maxNumber[i])
			{
				if(i - 1 > 0)
				{
					++alphabet[i - 1];
					
					for(int j = i; j < alphabet.length; ++j)
					{
						alphabet[j] = 0;
					}
				}
				i = 0;
			}
			if(alphabet[i] < minNumber[i])
			{
				
				++alphabet[i];
				i = 0;
			}
		}*/
		int[] permsInner = new int[alphabet.length];
		int[] radicesInner = new int[alphabet.length];
		
		if(!done)
		{
			for(int i = 0; i < alphabet.length; ++i)
			{
				//if(alphabet[i] >= 5)
				//{
				//	alphabet[i] = 4;
				//}
				radicesInner[i] = LetterFrequency.frequencyStrings[alphabet[i]].length();
			}
		}
		
		//Permutates over the letters of the alphabet
		while(!done)
		{	
			Map<Integer, String> contained = new HashMap<Integer, String>();
			boolean passed = true;
			
			for(int i = 0; i < radicesInner.length; ++i)
			{
				if(!contained.containsValue(Character.toString(LetterFrequency.frequencyStrings[alphabet[i]].charAt(permsInner[i]))))
				{
					//EMPTY - TODO FIND A WAY TO SPEED UP PERMUTATION TIME -- THOUGH THIS MAY BE THE FASTEST IT GETS....
				}
				else
				{
					passed = false;
					break;
				}
				contained.put(i, Character.toString(LetterFrequency.frequencyStrings[alphabet[i]].charAt(permsInner[i])));
			}
			
			if(passed == true)
			{
				String tempStr = "";
				for(int i = 0; i < contained.size(); ++i)
				{
					tempStr += contained.get(i);
				}
				
				if(!LetterFrequency.alphabetContained.containsValue(tempStr))
				{
					Alphabet newAlphabet = new Alphabet();
					String tempStringLetters = "L -> ";
					String actualLettersUsed = "";
					
					for(int i = 0; i < permsInner.length; ++i)
					{
						tempStringLetters += permsInner[i] + "-";
						
						int[] indexOfAlphabet = ADFGX.getAlphabetIndexFromCharacters(letterPairs[i]);
						newAlphabet.alphabet[indexOfAlphabet[0]][indexOfAlphabet[1]] = Character.toString(LetterFrequency.frequencyStrings[alphabet[i]].charAt(permsInner[i]));
						actualLettersUsed += newAlphabet.alphabet[indexOfAlphabet[0]][indexOfAlphabet[1]];
					}
					
					String tempStringAlphabet = "A -> "; 
					for(int i = 0; i < alphabet.length; ++i)
					{
						tempStringAlphabet += alphabet[i] + "-";
					}
					try {
						ADFGX.constructPhrase(newAlphabet.alphabet, writer, tempStringAlphabet, tempStringLetters, actualLettersUsed);
					} catch (IOException e) {
						e.printStackTrace();
					}
					
					LetterFrequency.alphabetContained.put(LetterFrequency.counter, tempStr);
					++LetterFrequency.counter;
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
		        } // if (radixsub >= alphabet.size())
		        permsInner[radixsub] += 1;
		      } // while (alphabet.at(radixsub) == limitOfPermutations)
		      
		    } // if (alphabet.at(0) == limitOfPermutations)
		    
		}
	}
}
