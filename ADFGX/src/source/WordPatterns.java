package source;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class WordPatterns {

	public static void main(String[] args) throws IOException {
		
		BufferedReader reader = new BufferedReader(new FileReader("words_850.txt"));
		String line = "";
		
		int limit = 7;
		int counter = 0;
		
		Map<String, Integer> patternHolder = new HashMap<String, Integer>();
		Map<Integer, Integer> patternFreqHolder = new HashMap<Integer, Integer>();
		
		ArrayList<String> patterns = new ArrayList<String>();
		
		while((line = reader.readLine()) != null)
		{
			if(line.length() <= limit)
			{
				String pattern = "";
				String lineFormatted = line.toLowerCase();
				
				/*for(int i = 0; i < line.length(); ++i)
				{
					
					if(ContactLetter_CVPatterns.consonantCheck(lineFormatted, i))
					{
						pattern += "C";
					}
					else if(ContactLetter_CVPatterns.vowelCheck(lineFormatted, i))
					{
						pattern += "V";
					}
				}*/
				
				if(patternHolder.containsKey(pattern))
				{
					patternHolder.put(pattern, patternHolder.get(pattern) + 1);
				}
				else
				{
					patterns.add(pattern);
					patternHolder.put(pattern, 1);
				}
				
				if(patternFreqHolder.containsKey(line.length()))
				{
					patternFreqHolder.put(line.length(), patternFreqHolder.get(line.length()) + 1);
				}
				else
				{
					patternFreqHolder.put(line.length(), 1);
				}
				
				++counter;
			}
		}
		
		reader.close();
		
		DecimalFormat df = new DecimalFormat("#.###");
		df.setRoundingMode(RoundingMode.CEILING);
		
		BufferedWriter writer = new BufferedWriter(new FileWriter("patterns.txt"));
		
		for(int i = 1; i < limit + 1; ++i)
		{
			for(int j = 0; j < patterns.size(); ++j)
			{
				if(patterns.get(j).length() == i)
				{
					writer.write(patterns.get(j) + " " + df.format((double)(patternHolder.get(patterns.get(j)) * 100) / 
							patternFreqHolder.get(patterns.get(j).length())));
					
					writer.newLine();
				}
			}
		}
		
		writer.close();
	}

}
