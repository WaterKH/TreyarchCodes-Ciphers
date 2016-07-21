package source;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class NumberCounter {

	public static void main(String[] args) throws IOException {
		
		BufferedReader reader = new BufferedReader(new FileReader("NumberText.txt"));
		String line = "";
		Map<String, Integer> counter = new HashMap<String, Integer>();
		ArrayList<String> elements = new ArrayList<String>();
		
		while((line = reader.readLine()) != null)
		{
			for(String part : line.split(" "))
			{
				if(counter.containsKey(part))
				{
					counter.put(part, counter.get(part) + 1);
				}
				else
				{
					elements.add(part);
					counter.put(part, 1);
				}
			}
		}
		
		for(int i = 0; i < counter.size(); ++i)
		{
			System.out.println(elements.get(i) + "  " + counter.get(elements.get(i)));
		}
		
	}

}
