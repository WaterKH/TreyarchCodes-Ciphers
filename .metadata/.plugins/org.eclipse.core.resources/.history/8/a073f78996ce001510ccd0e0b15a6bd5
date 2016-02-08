package source;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class LetterFreq {

	public static void main(String[] args) throws IOException {
		
		String LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String letters = "abcdefghijklmnopqrstuvwxyz";
		String numbers = "0123456789+/";
		
		BufferedReader reader = Resources.openFile_Reader("alphabetToCount");
		
		//@SuppressWarnings("resource")
		//Scanner keyboard = new Scanner(System.in);
		//System.out.print("Type in the letters you wish to count: ");
		String inputKeyword = reader.readLine();//keyboard.next();
		
		Resources.closeFile(reader, "alphabetToCount");
		
		ArrayList<String> listOfUpperCaseLetters = new ArrayList<String>();
		ArrayList<String> listOfLowerCaseLetters = new ArrayList<String>();
		ArrayList<String> listOfNumbers = new ArrayList<String>();
		Map<String, Integer> listOfItems = new HashMap<String, Integer>();
		
		for(int i = 0; i < inputKeyword.length(); ++i)
		{
			listOfItems.put(Character.toString(inputKeyword.charAt(i)), 0);
		}
		
		System.out.println("Doing things...");
		
		for(int i = 0; i < inputKeyword.length(); ++i)
		{
			String testString = Character.toString(inputKeyword.charAt(i));
			listOfItems.put(testString, listOfItems.get(testString) + 1);
			
			if(LETTERS.contains(testString))
			{
				if(!listOfUpperCaseLetters.contains(testString))
				{
					listOfUpperCaseLetters.add(testString);
				}
			}
			else if(letters.contains(testString))
			{
				if(!listOfLowerCaseLetters.contains(testString))
				{
					listOfLowerCaseLetters.add(testString);
				}
			}
			else if(numbers.contains(testString))
			{
				if(!listOfNumbers.contains(testString))
				{
					listOfNumbers.add(testString);
				}
			}
			else
			{
				System.out.println("This doesn't match anything: " + testString);
			}
		}
		
		System.out.println("Number of times lower case appears: " + listOfLowerCaseLetters.size());
		System.out.println(listOfLowerCaseLetters);
		System.out.println("Number of times upper case appears: " + listOfUpperCaseLetters.size());
		System.out.println(listOfUpperCaseLetters);
		System.out.println("Number of times numbers appears: " + listOfNumbers.size());
		System.out.println(listOfNumbers);
		System.out.println("Number of times each letter/number appears: ");
		sortMap(listOfItems);
		
		sequenceDetector();
	}
	
	public static void sortMap(Map<String, Integer> tempMap)
	{
		Set<String> tempStringSet = tempMap.keySet();
		
		Collection<Integer> tempIntCol = tempMap.values();
		
		ArrayList<Integer> tempIntArr = new ArrayList<Integer>(tempIntCol);
		ArrayList<String> tempStringArr = new ArrayList<String>();
		
		for(String str : tempStringSet)
		{
			tempStringArr.add(str);
		} // for(String str : tempStringSet)
		
		System.out.println("*SORTING...*");
		for(int i = 0; i < tempStringArr.size(); ++i)
		{
			for(int j = 0; j < tempStringArr.size(); ++j)
			{
				if(tempIntArr.get(j) > tempIntArr.get(i))
				{
					int tempInt = tempIntArr.get(j);
					tempIntArr.set(j, tempIntArr.get(i));
					tempIntArr.set(i, tempInt);
					
					String tempString = tempStringArr.get(j);
					tempStringArr.set(j, tempStringArr.get(i));
					tempStringArr.set(i, tempString);
				} // if(tempIntArr.get(j) > tempIntArr.get(i))
				
			} // for(int j = 0; j < tempStringArr.size(); ++j)
			
		} // for(int i = 0; i < tempStringArr.size(); ++i)
		
		
		for(int i = tempStringArr.size() - 1; i >= 0; --i)
		{
			System.out.println(tempStringArr.get(i) + " " + tempIntArr.get(i));
		} // for(int i = tempStringArr.size() - 1; i >= 0; --i)	
		System.out.println("*SORT SUCCESFUL*");
	} /** public void sortMap(Map<String, Integer> tempMap) 
	 * @throws IOException **/
	
	public static void sequenceDetector() throws IOException
	{
		BufferedReader reader = Resources.openFile_Reader("alphabetToCount");
		//String line = "";
		String holder = "";
		int upperCaseInt = 0;
		int lowerCaseInt = 0;
		int digitInt = 0;
		
		ArrayList<String> CapitalLowerCapital = new ArrayList<String>();
		ArrayList<String> CapitalLowerNumber = new ArrayList<String>();
		ArrayList<String> CapitalNumberCapital = new ArrayList<String>();
		ArrayList<String> CapitalNumberLower = new ArrayList<String>();
		
		ArrayList<String> LowerCapitalLower = new ArrayList<String>();
		ArrayList<String> LowerCapitalNumber = new ArrayList<String>();
		ArrayList<String> LowerNumberCapital = new ArrayList<String>();
		ArrayList<String> LowerNumberLower = new ArrayList<String>();
		
		ArrayList<String> NumberCapitalLower = new ArrayList<String>();
		ArrayList<String> NumberCapitalNumber = new ArrayList<String>();
		ArrayList<String> NumberLowerCapital = new ArrayList<String>();
		ArrayList<String> NumberLowerNumber = new ArrayList<String>();
		
		ArrayList<String> Lower = new ArrayList<String>();
		ArrayList<String> Capital = new ArrayList<String>();
		ArrayList<String> Number = new ArrayList<String>();
		String whole = reader.readLine();
		//System.out.println(whole);
		//String[] lines = new String[whole.split("").];
		
		String[] lines = whole.split("");
		
		System.out.println("LENGTH: " + lines.length);
		
		for(int i = 0; i < lines.length; ++i)
		{
			holder += lines[i];
			System.out.println(holder);
			if(holder.length() >= 3)
			{
				for(int j = 0; j < holder.length(); ++j)
				{
					if(Character.isUpperCase(holder.toCharArray()[0]))
					{
						if(Character.isLowerCase(holder.toCharArray()[1]))
						{
							if(Character.isDigit(holder.toCharArray()[2]))
							{
								CapitalLowerNumber.add(holder);
								holder = "";
								break;
							}
							else if(Character.isUpperCase(holder.toCharArray()[2]))
							{
								CapitalLowerCapital.add(holder);
								holder = "";
								break;
							}
							else if(Character.isLowerCase(holder.toCharArray()[2]))
							{
								--i;
								holder = "";
								break;
							}
						}
						else if(Character.isDigit(holder.toCharArray()[1]))
						{
							if(Character.isUpperCase(holder.toCharArray()[2]))
							{
								CapitalNumberCapital.add(holder);
								holder = "";
								break;
							}
							else if(Character.isLowerCase(holder.toCharArray()[2]))
							{
								CapitalNumberLower.add(holder);
								holder = "";
								break;
							}
							else if(Character.isDigit(holder.toCharArray()[2]))
							{
								holder = "";
								--i;
								break;
							}
						}
						else if(Character.isUpperCase(holder.toCharArray()[1]))
						{
							boolean finished = false;
							for(int k = 0; k < holder.toCharArray().length; ++k)
							{
								if(!Character.isUpperCase(holder.toCharArray()[k]))
								{
									holder = "";
									finished = true;
									break;
								}
							}
							if(finished)
							{
								--i;
								break;
							}
						}
					}
					else if(Character.isLowerCase(holder.toCharArray()[0]))
					{
						if(Character.isUpperCase(holder.toCharArray()[1]))
						{
							if(Character.isDigit(holder.toCharArray()[2]))
							{
								LowerCapitalNumber.add(holder);
								holder = "";
								break;
							}
							else if(Character.isLowerCase(holder.toCharArray()[2]))
							{
								LowerCapitalLower.add(holder);
								holder = "";
								break;
							}
							else if(Character.isUpperCase(holder.toCharArray()[2]))
							{
								holder = "";
								--i;
								break;
							}
						}
						else if(Character.isDigit(holder.toCharArray()[1]))
						{
							if(Character.isUpperCase(holder.toCharArray()[2]))
							{
								LowerNumberCapital.add(holder);
								holder = "";
								break;
							}
							else if(Character.isLowerCase(holder.toCharArray()[2]))
							{
								LowerNumberLower.add(holder);
								holder = "";
								break;
							}
							else if(Character.isDigit(holder.toCharArray()[2]))
							{
								holder = "";
								--i;
								break;
							}
						}
						else if(Character.isLowerCase(holder.toCharArray()[1]))
						{
							boolean finished = false;
							for(int k = 0; k < holder.toCharArray().length; ++k)
							{
								if(!Character.isLowerCase(holder.toCharArray()[k]))
								{
									holder = "";
									finished = true;
									break;
								}
							}
							if(finished)
							{
								--i;
								break;
							}
						}
					}
					else if(Character.isDigit(holder.toCharArray()[0]))
					{
						if(Character.isUpperCase(holder.toCharArray()[1]))
						{
							if(Character.isLowerCase(holder.toCharArray()[2]))
							{
								NumberCapitalLower.add(holder);
								holder = "";
								break;
							}
							else if(Character.isDigit(holder.toCharArray()[2]))
							{
								NumberCapitalNumber.add(holder);
								holder = "";
								break;
							}
							else if(Character.isUpperCase(holder.toCharArray()[2]))
							{
								holder = "";
								--i;
								break;
							}
							
						}
						else if(Character.isLowerCase(holder.toCharArray()[1]))
						{
							if(Character.isUpperCase(holder.toCharArray()[2]))
							{
								NumberLowerCapital.add(holder);
								holder = "";
								break;
							}
							else if(Character.isDigit(holder.toCharArray()[2]))
							{
								NumberLowerNumber.add(holder);
								holder = "";
								break;
							}
							else if(Character.isLowerCase(holder.toCharArray()[2]))
							{
								holder = "";
								--i;
								break;
							}
						}
						else if(Character.isDigit(holder.toCharArray()[1]))
						{
							boolean finished = false;
							for(int k = 0; k < holder.toCharArray().length; ++k)
							{
								if(!Character.isDigit(holder.toCharArray()[k]))
								{
									holder = "";
									finished = true;
									break;
								}
							}
							if(finished)
							{
								--i;
								break;
							}
						}
					}
					
					//System.out.println("CHAR: " + holder);
					if(Character.isUpperCase(holder.charAt(j)))
					{
						++upperCaseInt;
						if(lowerCaseInt >= 2)
						{
							Lower.add(holder);
						}
						else if(digitInt >= 2)
						{
							Number.add(holder);
						}
						lowerCaseInt = 0;
						digitInt = 0;
					}
					else if(Character.isLowerCase(holder.charAt(j)))
					{
						++lowerCaseInt;
						if(upperCaseInt >= 2)
						{
							Capital.add(holder);
						}
						else if(digitInt >= 2)
						{
							Number.add(holder);
						}
						upperCaseInt = 0;
						digitInt = 0;
					}
					else if(Character.isDigit(holder.charAt(j)))
					{
						++digitInt;
						if(upperCaseInt >= 2)
						{
							Capital.add(holder);
						}
						else if(lowerCaseInt >= 2)
						{
							Lower.add(holder);
						}
						upperCaseInt = 0;
						lowerCaseInt = 0;
					}
				}
			}
		}
		
		Resources.closeFile(reader, "alphabetToCount");
		
		System.out.println("SEQUENCES");
		System.out.println("Lower");
		for(int i = 0; i < Lower.size(); ++i)
		{
			System.out.print(Lower.get(i) + " ");
		}
		System.out.println("-- " + Lower.size());
		
		System.out.println("Capital");
		for(int i = 0; i < Capital.size(); ++i)
		{
			System.out.print(Capital.get(i) + " ");
		}
		System.out.println("-- " + Capital.size());
		
		System.out.println("Number");
		for(int i = 0; i < Number.size(); ++i)
		{
			System.out.print(Number.get(i) + " ");
		}
		System.out.println("-- " + Number.size());
		
		System.out.println("Capital-Lower-Number");
		for(int i = 0; i < CapitalLowerNumber.size(); ++i)
		{
			System.out.print(CapitalLowerNumber.get(i) + " ");
		}
		System.out.println("-- " + CapitalLowerNumber.size());
		
		System.out.println("Capital-Lower-Capital");
		for(int i = 0; i < CapitalLowerCapital.size(); ++i)
		{
			System.out.print(CapitalLowerCapital.get(i) + " ");
		}
		System.out.println("-- " + CapitalLowerCapital.size());
		
		System.out.println("Capital-Number-Capital");
		for(int i = 0; i < CapitalNumberCapital.size(); ++i)
		{
			System.out.print(CapitalNumberCapital.get(i) + " ");
		}
		System.out.println("-- " + CapitalNumberCapital.size());
		
		System.out.println("Capital-Number-Lower");
		for(int i = 0; i < CapitalNumberLower.size(); ++i)
		{
			System.out.print(CapitalNumberLower.get(i) + " ");
		}
		System.out.println("-- " + CapitalNumberLower.size());
		
		System.out.println("Lower-Capital-Lower");
		for(int i = 0; i < LowerCapitalLower.size(); ++i)
		{
			System.out.print(LowerCapitalLower.get(i) + " ");
		}
		System.out.println("-- " + LowerCapitalLower.size());
		
		System.out.println("Lower-Capital-Number");
		for(int i = 0; i < LowerCapitalNumber.size(); ++i)
		{
			System.out.print(LowerCapitalNumber.get(i) + " ");
		}
		System.out.println("-- " + LowerCapitalNumber.size());
		
		System.out.println("Lower-Number-Lower");
		for(int i = 0; i < LowerNumberLower.size(); ++i)
		{
			System.out.print(LowerNumberLower.get(i) + " ");
		}
		System.out.println("-- " + LowerNumberLower.size());
		
		System.out.println("Lower-Number-Capital");
		for(int i = 0; i < LowerNumberCapital.size(); ++i)
		{
			System.out.print(LowerNumberCapital.get(i) + " ");
		}
		System.out.println("-- " + LowerNumberCapital.size());
		
		System.out.println("Number-Capital-Lower");
		for(int i = 0; i < NumberCapitalLower.size(); ++i)
		{
			System.out.print(NumberCapitalLower.get(i) + " ");
		}
		System.out.println("-- " + NumberCapitalLower.size());
		
		System.out.println("Number-Lower-Capital");
		for(int i = 0; i < NumberLowerCapital.size(); ++i)
		{
			System.out.print(NumberLowerCapital.get(i) + " ");
		}
		System.out.println("-- " + NumberLowerCapital.size());
		
		System.out.println("Number-Capital-Number");
		for(int i = 0; i < NumberCapitalNumber.size(); ++i)
		{
			System.out.print(NumberCapitalNumber.get(i) + " ");
		}
		System.out.println("-- " + NumberCapitalNumber.size());
		
		System.out.println("Number-Lower-Number");
		for(int i = 0; i < NumberLowerNumber.size(); ++i)
		{
			System.out.print(NumberLowerNumber.get(i) + " ");
		}
		System.out.println("-- " + NumberLowerNumber.size());
	}
}
