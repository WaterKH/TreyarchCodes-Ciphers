package source;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileWriterThread extends Thread {

	static File currFile;
	
	public FileWriterThread(File file)
	{
		currFile = file;
	}
	
	public void run()
	{
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(currFile));
			while(!ADFGX.writeToFileQueue.isEmpty() || ADFGX.line.toLowerCase().equals("fedcba"))
			{
				if(!ADFGX.writeToFileQueue.isEmpty())
				{
					System.out.println(ADFGX.writeToFileQueue.peek());
					writer.write(ADFGX.writeToFileQueue.poll());
					writer.newLine();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}