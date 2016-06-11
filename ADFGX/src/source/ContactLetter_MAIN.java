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

public class ContactLetter_MAIN {

	public static void main(String[] args) throws IOException 
	{
		String alphabet = "abcdefghijklmnopqrstuvwxyz ";
		Map<String, ContactLetters> listOfContacts = new HashMap<String, ContactLetters>();
		
		for(int i = 0; i < alphabet.length(); ++i)
		{
			String key = Character.toString(alphabet.charAt(i));
			
			listOfContacts.put(key, new ContactLetters(key));
		}
		
		BufferedReader reader = new BufferedReader(new FileReader("Moby_Dick_FORMAT.txt"));
		String line = "";
		int depth = 0;
		
		System.out.println("Started Reading");
		
		while((line = reader.readLine()) != null)
		{
			String previousLetter = "";
			String followingLetter = "";
			
			for(String letter : line.split(""))
			{
				letter = letter.toLowerCase();
				ContactLetters contactLetter = listOfContacts.get(letter);
				
				if(depth > 0)
				{
					previousLetter = Character.toString(line.charAt(depth - 1));
					contactLetter.addToBefore(previousLetter);
					contactLetter.increaseBeforeTotalContactLetters();
				}
				
				if(depth < line.length() - 1)
				{
					followingLetter = Character.toString(line.charAt(depth + 1));
					contactLetter.addToAfter(followingLetter);
					contactLetter.increaseAfterTotalContactLetters();
				}
				
				++depth;
			}
		}
		reader.close();

		System.out.println("Finished Reading");
	
		BufferedWriter writer = new BufferedWriter(new FileWriter("percentagesOfContactLetters_MOBYDICK(TOTAL).txt"));
		
		for(int i = 0; i < alphabet.length(); ++i)
		{
			String letter = Character.toString(alphabet.charAt(i));
			for(int j = 0; j < alphabet.length(); ++j)
			{
				String percLetter = Character.toString(alphabet.charAt(j));
				
				DecimalFormat df = new DecimalFormat("#.####");
				df.setRoundingMode(RoundingMode.CEILING);
				
				writer.write(letter + "," + percLetter + "," + df.format(listOfContacts.get(letter).getBeforePercentage(percLetter) / depth) 
						+ "," + df.format(listOfContacts.get(letter).getAfterPercentage(percLetter) / depth));
				writer.newLine();
			}
			writer.newLine();
		}
		
		writer.close();
		System.out.println("Finished Program");
		
		BufferedReader perc_reader = new BufferedReader(new FileReader("ListForPermutations.txt"));
		line = "";
		Map<String, ArrayList<String>> percHolder = new HashMap<String, ArrayList<String>>(); 
		
		while((line = perc_reader.readLine()) != null)
		{
			String key = line.split("-")[0];
			ArrayList<String> strings = new ArrayList<String>();
			System.out.println(line);
			for(String part: line.split("-")[1].split(","))
			{
				
				strings.add(part);
			}
			
			percHolder.put(key, strings);
		}
		perc_reader.close();
		
		BufferedWriter writer_recursive = new BufferedWriter(new FileWriter("finalStrings_ADFGX.txt"));
		
		startRecursive(percHolder, writer_recursive);
		
		writer_recursive.close();
	}
	// ABOVE WORKS PERFECTLY
	// TODO BELOW MAY WORK, BUT NOT GIVING DESIRED RESULTS
	
	/** TODO 
	 * 
	 * 	We get our spaces list to check each letter in order of how they would appear
	 * 	We then check this letter's letter in its list, check this letter's next letter, etc.
	 * 	We check to see if a letter is there, if it is, then we skip over this slot and
	 * 	  check that slot to see what its next letter should be and continue
	 * 
	 * 
	 */
	
	public static void startRecursive(Map<String, ArrayList<String>> percHolder, BufferedWriter writer_recursive) throws IOException
	{
		BufferedReader reader_ci = new BufferedReader(new FileReader("ADFGX_Cryptograms.txt"));
		ArrayList<String> spaceList = percHolder.get("_");
		String line = "";
		
		while((line = reader_ci.readLine()) != null)
		{
			String cipherText = line;
			System.out.println(cipherText);
			
			for(int i = 0; i < spaceList.size(); ++i)
			{	
				String runningString = spaceList.get(i);
				Map<Character, Character> cipherHolder = new HashMap<Character, Character>();
				recursive_FindPhrase(spaceList.get(i), runningString, percHolder, cipherText, cipherHolder, 0, writer_recursive);
			}
		}
		
		reader_ci.close();
		
	}

	public static void recursive_FindPhrase(String checkForNext, String runningString, Map<String, ArrayList<String>> percHolder, String cipherText, 
			Map<Character, Character> cipherHolder, int depth, BufferedWriter writer_recursive) throws IOException
	{
		// TODO WORKING! But super slow...
		
		//System.out.println("CURR DEPTH: " + depth);
		if(checkPattern(runningString, cipherText, writer_recursive))
		{
			System.out.println(runningString);
			// Get the size of our current arrayList and run a loop on it
			for(int i = 0; i < percHolder.get(checkForNext).size(); ++i)
			{
				if(runningString.length() < 34)
				{
					if(percHolder.get(checkForNext).get(i).equals("_"))
					{
						runningString += percHolder.get("_").get(i);
						cipherHolder.put(cipherText.charAt(depth), runningString.charAt(runningString.length() - 1)); // TODO This is broken
						++depth; // TODO This is broken
						recursive_FindPhrase(percHolder.get("_").get(i), runningString, percHolder, cipherText, cipherHolder, depth, writer_recursive);
					}
					else
					{
						runningString += percHolder.get(checkForNext).get(i);
						cipherHolder.put(cipherText.charAt(depth), runningString.charAt(runningString.length() - 1)); // TODO This is broken
						++depth; // TODO This is broken
						recursive_FindPhrase(percHolder.get(checkForNext).get(i), runningString, percHolder, cipherText, cipherHolder, depth, writer_recursive);
						
					}
					cipherHolder.remove(runningString.substring(runningString.length() - 1, runningString.length()));
					runningString = runningString.substring(0, runningString.length() - 1);
					--depth;
					continue;
				}
				
				//System.out.println("Check Pattern: " + runningString);
				//solvePattern(cipherHolder, cipherText);
				//checkPattern(runningString);
				break;
			}
		}
		
	}
	
	public static void solvePattern(Map<Character, Character> solver, String cipherText)
	{
		String running = "";
		
		for(int i = 0; i < cipherText.length(); ++i)
		{
			if(solver.containsKey(cipherText.charAt(i)))
			{
				running += solver.get(cipherText.charAt(i));
			}
			else
			{
				running += ".";
			}
		}
		
		System.out.println(running);
	}
	
	public static boolean checkPattern(String patternString, String cipherText, BufferedWriter writer) throws IOException
	{
		String alphabet = "abcdefghiklmnopqrstuvwxyz";
		Map<Character, Integer> patternHolder = new HashMap<Character, Integer>();
		int counter = 0;
		String runningString = "";
		
		for(int i = 0; i < patternString.length(); ++i)
		{
			if(patternHolder.containsKey(patternString.charAt(i)))
			{
				runningString += alphabet.charAt(patternHolder.get(patternString.charAt(i)));
			}
			else
			{
				runningString += alphabet.charAt(counter);
				patternHolder.put(patternString.charAt(i), counter);
				++counter;
			}
		}
		
		//System.out.println(runningString);
		
		String checkStr = cipherText.substring(0, runningString.length());
		
		if(checkStr.equals(runningString))
		{
			if(runningString.length() > 33)
			{
				System.out.println(runningString);
				writer.write(runningString);
				writer.newLine();
			}
			return true;
		}
		
		return false;
	}

}
