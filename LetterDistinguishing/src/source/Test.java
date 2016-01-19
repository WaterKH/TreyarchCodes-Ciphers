package source;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class Test {

	//static String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789/";
	//static String[][] alphabetArr = new String[alphabet.length()][alphabet.length()];
	
	public static void main(String[] args) throws IOException 
	{	
		//Scanner keyboard = new Scanner(System.in);
		//System.out.println("Please input an alphabet: ");
		BufferedReader reader = Resources.openFile_Reader("alphabet");
		//String line = "";
		//String alphabet = "";		
		//while((line = reader.readLine()) != null)
		//{
		String alphabet = reader.readLine();
		//}
		
		Resources.closeFile(reader, "alphabet");
		
		String[][] alphabetArr = new String[alphabet.length()][alphabet.length()];
		Queue<String> tempString = new LinkedList<String>();
		
		for(int i = 0; i < alphabet.length(); ++i)
		{
			tempString.add(Character.toString(alphabet.charAt(i)));
		}
		
		int delim = 0;
		
		for(int i = 0; i < alphabetArr.length; ++i)
		{
			for(int j = 0; j < alphabetArr[i].length; ++j)
			{	
				alphabetArr[i][j] = tempString.poll();
				
				++delim;
				for(int k = delim; k < alphabet.length(); ++k)
				{
					tempString.add(Character.toString(alphabet.charAt(k)));
				}
				for(int k = 0; k < delim; ++k)
				{
					if(k < 63)
						tempString.add(Character.toString(alphabet.charAt(k)));
				}	
			}
			delim = 0;
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
