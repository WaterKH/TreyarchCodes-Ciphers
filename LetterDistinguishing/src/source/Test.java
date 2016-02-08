package source;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

public class Test {

	static String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789/";
	static String[][] alphabetArr = new String[alphabet.length()][alphabet.length()];
	
	public static void main(String[] args) throws IOException 
	{	
		System.out.print("Select Alphabet you which to use: ");
		Scanner keyboard = new Scanner(System.in);
		String alphabetToUse = keyboard.nextLine();
		
		System.out.print("Key: ");
		String key = keyboard.nextLine();
		int lengthOfKey = key.length();
		//System.out.println("Please input an alphabet: ");
		BufferedReader reader = Resources.openFile_Reader(alphabetToUse);
		String line = "";
		
		String lettersFromCipherText = "";		
		while((line = reader.readLine()) != null)
		{
			lettersFromCipherText += line;
			//System.out.println(lettersFromCipherText);
		}
		
		int row = 0;
		int counter = 0;
		
		for(int i = 0; i < lettersFromCipherText.length(); ++i)
		{
			++counter;
			if(counter == lengthOfKey)
			{
				counter = 0;
				++row;
			}
		}
		
		Resources.closeFile(reader, alphabetToUse);
		
		String[][] cipherTextArr = new String[row][lengthOfKey];
		int delim = 0;
		
		for(int i = 0; i < cipherTextArr.length; ++i)
		{
			for(int j = 0; j < cipherTextArr[i].length; ++j)
			{
				cipherTextArr[i][j] = Character.toString(lettersFromCipherText.charAt(delim + j));
			}
			delim += cipherTextArr[i].length;
		}
		
		for(int i = 0; i < cipherTextArr.length; ++i)
		{
			for(int j = 0; j < cipherTextArr[i].length; ++j)
			{
				System.out.print(cipherTextArr[i][j] + " ");
			}
			System.out.println();
		}
	
		Map<String, Integer> freqCounter = new HashMap<String, Integer>();
		for(int j = 0; j < cipherTextArr[0].length; ++j)
		{
			for(int i = 0; i < cipherTextArr.length; ++i)
			{
				int freq = 0;
				for(int k = 0; k < cipherTextArr.length; ++k)
				{
					int checkIndex = i;
					
					if(cipherTextArr[checkIndex][j].equals(cipherTextArr[k][j]))
					{
						++freq;
					}
				}
				
				
				System.out.print(cipherTextArr[i][j] + " " + i + " = " + freq + "; ");
				
				freq = 0;
				//freqCounter.put(cipherTextArr[i][j] + " " + i, freq);
			}
			System.out.println();
		}
		
		System.out.println(freqCounter);
		
		String[][] alphabetArr = new String[alphabet.length()][alphabet.length()];
		Queue<String> tempString = new LinkedList<String>();
		
		for(int i = 0; i < alphabet.length(); ++i)
		{
			tempString.add(Character.toString(alphabet.charAt(i)));
		}
		
		int delim1 = 0;
		

		
		for(int i = 1; i < alphabetArr.length; ++i)
		{
			for(int j = 0; j < alphabetArr[i].length; ++j)
			{	
				alphabetArr[i][j] = tempString.poll();
				
				++delim1;
				for(int k = delim1; k < alphabet.length(); ++k)
				{
					tempString.add(Character.toString(alphabet.charAt(k)));
				}
				for(int k = 0; k < delim1; ++k)
				{
					if(k < 63)
						tempString.add(Character.toString(alphabet.charAt(k)));
				}	
			}
			delim1 = 0;
		}
		displayAlphabet(alphabet, alphabetArr);
	}
	
	public static void displayAlphabet(String alphabet, String[][] alphabetArr)
	{
		System.out.print("     ");
		
		for(int i = 0; i < alphabet.length(); ++i)
		{
			System.out.print(alphabet.charAt(i) + " ");
		}
		
		System.out.println();
		System.out.print("     ");
		
		for(int i = 0; i < alphabet.length(); ++i)
		{
			System.out.print(i % 10 + " ");
		}
		
		System.out.println();
		System.out.print("     ");
		
		for(int i = 0; i < alphabet.length(); ++i)
		{
			System.out.print("| ");
		}
		
		System.out.println();
		boolean firstPass = true;
		for(int i = 0; i < alphabetArr.length; ++i)
		{
			
			System.out.print(alphabet.charAt(i) + " " + i % 10 + "> ");
			for(int j = 0; j < alphabetArr[i].length; ++j)
			{
				System.out.print(alphabetArr[i][j] + " ");
			}
			System.out.println();
		}
	}
}
