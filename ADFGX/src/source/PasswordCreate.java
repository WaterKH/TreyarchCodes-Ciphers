package source;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class PasswordCreate {

	public static void main(String[] args) throws IOException {
		String list1 = "LKGEAZDH";
		String list2 = "UROIEDAY";
		String list3 = "MEDWTSRO";
		String list4 = "IBAWTSNH";
		String list5 = "IELAUROS";
		String list6 = "SRLHGEDA";
		
		BufferedWriter writer = new BufferedWriter(new FileWriter("Solutions.txt"));
		BufferedReader reader = new BufferedReader(new FileReader("words_850.txt"));
		String line = "";
		ArrayList<String> holder = new ArrayList<String>();
		
		while((line = reader.readLine()) != null)
		{
			holder.add(line);
		}
		
		for(int i = 0; i < list1.length(); ++i)
		{
			for(int j = 0; j < list2.length(); ++j)
			{
				for(int k = 0; k < list3.length(); ++k)
				{
					for(int l = 0; l < list4.length(); ++l)
					{
						for(int m = 0; m < list5.length(); ++m)
						{
							for(int n = 0; n < list6.length(); ++n)
							{
								String word = list1.charAt(i) + "" + list2.charAt(j) + "" + list3.charAt(k)
										+ "" + list4.charAt(l) + "" + list5.charAt(m) + "" + list6.charAt(n);
								//System.out.println(list1.charAt(i) + list2.charAt(j) + list3.charAt(k) +
									//	list4.charAt(l) + list5.charAt(m) + list6.charAt(n));
								if(holder.contains(word))
								{
									writer.write(word);
									writer.newLine();
								}
							}
						}
					}
				}
			}
		}
		
		writer.close();
		System.out.println("CLOSE");

	}

}
