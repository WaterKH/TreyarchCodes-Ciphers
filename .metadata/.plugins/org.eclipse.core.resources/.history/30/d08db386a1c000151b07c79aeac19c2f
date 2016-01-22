/****************************************************************************
 * Author: @author peterclark - All Rights Reserved
 * Program: ADFGX Cipher Decryption Tool
 * 
 * Houses open/ close/ timer functions
 */
package source;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Resources {
	
	static long start;
	static long end;
	
	public static BufferedWriter openFile_Writer(String fileName) throws IOException
	{
		System.out.println();
		
		System.out.println("/// Opening /// " + fileName + " ...");
		File file = new File("Null");
		if(fileName.equals("CipherKeys"))
			file = new File("/Volumes/Jacob Clark/_PhrasesCOD/" + fileName + ".txt");
		else
			file = new File("/Volumes/Jacob Clark/_PhrasesCOD/" + fileName + ADFGX.line + ".txt");
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		//System.out.println(file.getName() + " /// Opened ///");
		
		System.out.println();
		
		return writer;
	} /** public static BufferedWriter openFile_Writer(String fileName) throws IOException **/
	
	public static BufferedReader openFile_Reader(String fileName) throws IOException
	{
		System.out.println();
		
		System.out.println("/// Opening /// " + fileName + " ...");
		File file = new File("Null");
		if(fileName.equals("adfgxCipherText"))
			file = new File("/Volumes/Jacob Clark/_PhrasesCOD/" + fileName + ".txt");
		else
			file = new File("/Volumes/Jacob Clark/_PhrasesCOD/" + fileName + ADFGX.line + ".txt");
		
		BufferedReader reader = new BufferedReader(new FileReader(file));//"/Volumes/Jacob Clark/_PhraseCOD/" +fileName+ ".txt"));
		System.out.println(file.getName() + " /// Opened ///");
		
		System.out.println();
		
		return reader;
	} /** public static BufferedReader openFile_Reader(String fileName) throws IOException **/
	
	public static void closeFile(BufferedWriter fileToClose, String file) throws IOException
	{
		System.out.println();
		
		fileToClose.flush();
		System.out.println("\\\\\\ Closing \\\\\\ " + file + " ...");
		fileToClose.close();
		System.out.println(file + " \\\\\\ Closed \\\\\\");
		
		System.out.println();
	} /** public static void closeFile(BufferedWriter fileToClose, String file) throws IOException **/
	
	public static void closeFile(BufferedReader fileToClose, String file) throws IOException
	{
		System.out.println();
		
		System.out.println("\\\\\\ Closing \\\\\\ " + file + " ...");
		fileToClose.close();
		System.out.println(file + " \\\\\\ Closed \\\\\\");
		
		System.out.println();
	} /** public static void closeFile(BufferedReader fileToClose, String file) throws IOException **/
	
	public static void deleteFile(String fileName)
	{
		File file = new File("/Volumes/Jacob Clark/_PhrasesCOD/" + fileName + ".txt");
		file.delete();
		System.out.println("!!! " + fileName + " !!! Has Been Deleted!");
	} /** public static void deleteFile(String fileName) **/
	
	public static void startTimer()
	{
		start = System.currentTimeMillis();
		System.out.println("--- Timer Started ---");
	} /** public static void startTimer() **/
	
	public static void endTimer()
	{
		end = System.currentTimeMillis();
		System.out.println("--- Time taken to write/read lines = "+ ((end-start)/1000) + " seconds. ---");	
	} /** public static void endTimer() **/
}
