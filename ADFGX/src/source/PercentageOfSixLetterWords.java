package source;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class PercentageOfSixLetterWords {
	
	public static File file = new File("final6Words.txt");
	public static File fileToWrite = new File("percentages.txt");
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader dictReader = new BufferedReader(new FileReader(file));
		BufferedWriter dictWriter = new BufferedWriter(new FileWriter(fileToWrite));
		
		String line = "";
		double counter = 0;
		Map<String, Double> letterCount = new HashMap<String, Double>();
		String lettersUsed = "";
		
		while((line = dictReader.readLine()) != null)
		{
			counter += 6.0;
			
			for(String part : line.split(""))
			{
				if(letterCount.containsKey(part))
				{
					letterCount.put(part, letterCount.get(part) + 1.0);
				}
				else
				{
					lettersUsed += part;
					letterCount.put(part, 0.0);
				}
			}
		}
		
		DecimalFormat df = new DecimalFormat("#.####");
		df.setRoundingMode(RoundingMode.CEILING);

		for(int i = 0; i < lettersUsed.length(); ++i)
		{
			double charCount = letterCount.get(Character.toString(lettersUsed.charAt(i)));
			double percentage = (charCount / counter) * 100;
			dictWriter.write(lettersUsed.charAt(i) + " " + df.format(percentage));
			dictWriter.newLine();
		}
		
		dictWriter.close();
		dictReader.close();
	}
		
}
