package source;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SequenceFinder_Solver {

	public static int startIndex = 0;
	
	public static boolean oneWordSequence_Finder(String seqToFind)
	{
		boolean isSeq = false;
		
		for(int i = 0; i < seqToFind.length() - 5; ++i)
		{
			Map<Integer, Character> sequenceHolder = new HashMap<Integer, Character>();
			int counter = 0;
			
			for(int j = i; j < i + 6; ++j)
			{
				if(j < i + 3)
				{
					sequenceHolder.put(j, seqToFind.charAt(j));	
				}
				else
				{
					if(seqToFind.charAt(j) != sequenceHolder.get(j - 3))
					{
						break;
					}
					else
					{
						++counter;
					}
				}
			}
			
			if(counter == 3)
			{
				startIndex = i;
				System.out.println(startIndex);
				isSeq = true;
				break;
			}
		}
		
		return isSeq;
	}
	
	public static void sequenceSolver(String seqToUse, BufferedWriter writer) throws IOException
	{
		int actualStart = ADFGX.startIndex - startIndex;
		
		if(actualStart < 0)
			return;
		
		Map<String, Character> container = new HashMap<String, Character>();
		int index = 0;
		
		for(int i = actualStart; i < seqToUse.length(); ++i)
		{
			if(!container.containsKey(ADFGX.letterPairs.get(i)))
			{
				container.put(ADFGX.letterPairs.get(i), seqToUse.charAt(index));
			}
			else
			{
				break;
			}
			
			++index;
		}
		System.out.println(index + " " + (seqToUse.length() - 1));
		if(index == (seqToUse.length() - 1))
		{
			String phraseToWrite = "";
			
			for(int i = 0; i < ADFGX.letterPairs.size(); ++i)
			{
				if(container.containsKey(ADFGX.letterPairs.get(i)))
				{
					phraseToWrite += ADFGX.letterPairs.get(i);
				}
				else
				{
					phraseToWrite += "[" + ADFGX.letterPairs.get(i) + "]";
				}
			}
			
			writer.write(phraseToWrite);
			writer.newLine();
		}
	}
	
	public static void startSeqFinder(BufferedReader reader, BufferedWriter writer) throws IOException
	{
		String line = "";
	
		while((line = reader.readLine()) != null)
		{
			if(oneWordSequence_Finder(line))
			{
				sequenceSolver(line, writer);
			}
		}
		
		
		System.out.println("Finished");
	}
	/*
	public static void main(String[] args) throws IOException
	{
		BufferedReader reader = Resources.openFile_Reader("words");
		BufferedWriter writer = Resources.openFile_Writer("ABCABC_Sequences");
		String line = "";
		
		//oneWordSequence_Finder("anisnis");
		//System.exit(0);
		
		while((line = reader.readLine()) != null)
		{
			if(oneWordSequence_Finder(line))
			{
				sequenceSolver(line);
			}
		}
		
		reader.close();
		writer.close();
		System.out.println("Finished");
	}*/
}
