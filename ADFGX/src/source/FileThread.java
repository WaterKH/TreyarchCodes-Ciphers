package source;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class FileThread extends Thread {
	
	static File currFile;
	public static Queue<String> abcQueue = new LinkedList<String>();
	
	public FileThread(File file)
	{
		currFile = file;
	}
	
	public void run()
	{
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(currFile));
			
			while(true)
			{
				//System.out.println(AbcAbc.abcQueue.peek());
				if(!(abcQueue.isEmpty()))
				{
					String temp = abcQueue.peek();
					writer.write(abcQueue.poll());
					writer.newLine();
					System.out.println("Write successful");
					if(temp.equals("zyzzyvaszyzzyvas"))
					{
						break;
					}
				}
			}
			
			writer.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
