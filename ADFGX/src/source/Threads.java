package source;

import java.io.BufferedWriter;

public class Threads extends Thread {
	
	public int[] alphabet;
	public String[] letterPairs;
	public BufferedWriter writer;
	
	public Threads(int[] alphabetFromPerms, String[] letterPairsFromPerms, BufferedWriter writerFromPerms)
	{
		alphabet = alphabetFromPerms;
		letterPairs = letterPairsFromPerms;
		writer = writerFromPerms;
	}
	
	public void run() 
	{
		
	}
}
