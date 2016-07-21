package source;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class CaesarCipher {
	
	public static void main(String[] args) throws IOException
	{
		BufferedReader reader_De = new BufferedReader(new FileReader("CipherText_Caesar.txt"));
		BufferedWriter writer_De = new BufferedWriter(new FileWriter("Enciphered.txt"));
		String line_De = "";
	
		String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ,.";
		int modNumber = alphabet.length();
		
		Random rand = new Random();
		int rotateBy = rand.nextInt(modNumber);
		int power = rand.nextInt(20);
		
		writer_De.write(rotateBy + " " + power);
		writer_De.newLine();
		
		while((line_De = reader_De.readLine()) != null)
		{
			for(String character : line_De.split(""))
			{
				int currNumb = alphabet.indexOf(character.toCharArray()[0]);
				int newNumb = (currNumb + rotateBy) % modNumber;
				int finalNumb = performOperation(newNumb, power, "+") % modNumber;
				
				writer_De.write(alphabet.charAt(finalNumb));
			}
		}
		
		reader_De.close();
		writer_De.close();
		
		BufferedReader reader_En = new BufferedReader(new FileReader("Enciphered.txt"));
		BufferedWriter writer_En = new BufferedWriter(new FileWriter("Deciphered.txt"));
		String line_En = "";
		
		while((line_En = reader_En.readLine()) != null)
		{
			for(String character : line_En.split(""))
			{
				int currNumb = alphabet.indexOf(character.toCharArray()[0]);
				int newNumb = (currNumb - rotateBy) & modNumber;
				int finalNumb = performOperation(newNumb, power, "-") % modNumber;
				
				if(finalNumb < 0)
				{
					finalNumb += modNumber;
				}
				writer_En.write(alphabet.charAt(finalNumb));
			}
		}
		
		reader_En.close();
		writer_En.close();
		//TODO NOT WORKING YET - Do some examples so you know what you want the mod to do.
	}
	
	public static int performOperation(int onNumber, int power, String operation)
	{
		int toReturn = 0;
		switch(operation)
		{
		case "+":
			for(int i = 0; i < power; ++i)
			{
				toReturn += onNumber;
			}
			break;
		case "-":
			for(int i = 0; i < power; ++i)
			{
				toReturn -= onNumber;
			}
			break;
		}
	
		return toReturn;
	}
}
