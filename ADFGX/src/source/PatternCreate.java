package source;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class PatternCreate {

	public static void main(String[] args) {
		
		System.out.print("Input pattern: ");
		Scanner keyboard = new Scanner(System.in);
		String pattern = keyboard.next();
		
		String alphabet = "abcdefghijklmnopqrstuvwxyz";
		Map<Character, Integer> patternHolder = new HashMap<Character, Integer>();
		int counter = 0;
		String runningString = "";
		
		for(int i = 0; i < pattern.length(); ++i)
		{
			if(patternHolder.containsKey(pattern.charAt(i)))
			{
				runningString += alphabet.charAt(patternHolder.get(pattern.charAt(i)));
			}
			else
			{
				runningString += alphabet.charAt(counter);
				patternHolder.put(pattern.charAt(i), counter);
				++counter;
			}
		}
		
		System.out.println(runningString);

	}

}
