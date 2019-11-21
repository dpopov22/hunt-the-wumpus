/**
 * File: WordCounter.java
 * Author: Domnika Popov
 * Date: 04/08/2019
 */
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.Object; 
import java.io.Writer; 
import java.io.FileWriter; 
import java.io.PrintWriter; 
import java.util.ArrayList; 
import java.util.Scanner; 
import java.util.Collections; 


public class WordCounter
{
	private MapSet<String, Integer> map; 
	private int wordCount; 
	private static String[] commonWordsList = {"time", "person", "year", "way", "day","thing","man", 
	"world","life", "hand", "part", "child", "eye", "woman", "place", "work", "week", "case", 
	"point", "government", "company", "number", "group", "problem", "fact", "be", "have","do", 
	"say", "get", "make","go", "know","take","see", "come","think","look", "want","give","use", 
	"find","tell","ask","work","seem","feel","try","leave","call","good","new","first","last", "long", 
	"great", "little", "own", "other", "old", "right", "big", "high", "different", "small", 
	"large", "next", "early", "young", "important", "few", "public", "bad", "same", "able", 
	"to", "of", "in", "for", "on", "with", "at", "by", "from", "up", "about", "into", "over", 
	"after", "the", "and", "a", "that", "i", "it", "not", "he", "as", "you", "this", "but", 
	"his", "they", "her", "she", "or", "an", "will", "my", "one", "all", "would", "there", "their"}; 
	
	private ArrayList<String> cw;  
	
	//constructor that makes an empty BSTMap and sets the total word count to zero
	//Also creates an array list of strings and loops through a string Array and adds each 
	//element to //that array list
	public WordCounter(boolean storeMethod)
	{
		//determines whether a BSTMap or Hashmap is being used 
		if (storeMethod == true)
		{
			map = new BSTMap<String, Integer>(new StringAscending()); 
		}
		if(storeMethod == false)
		{
			map = new Hashmap<String, Integer> (10, new StringAscending());
		}
		wordCount = 0; 
		cw = new ArrayList<String>(); 
		for (String c: commonWordsList)
		{
			cw.add(c); 
		}
	}
	public MapSet<String, Integer> getMap()
	{
		return map; 
	}
	
	//analyzed each text file by reading it in as an argument through the command line
	public void analyze(String filename, boolean commonWords)
	{
		try 
		{
			FileReader read = new FileReader(filename); 
			BufferedReader buffer = new BufferedReader(read);
			String line = buffer.readLine();
			 
		
			while(line!= null)
			{
				// split line into words
					String[] words = line.split("[^a-zA-Z0-9']");
					for (int i = 0; i < words.length; i++) 
					{
						String word = words[i].trim().toLowerCase();
						if (word.length() != 0)
						{
							//allows for user to determine whether or not they want
							//to include common words in the analysis of the file or not
							if (commonWords)
							{  
								if(cw.contains(word)) 
								{ 
									continue; 
								}
								if(word.length() != 0)
								{
								wordCount++; 
								} 
								Integer value = map.get(word);
							
								if (value != null)
								{
									map.put(word, value+1); 		
								}
								else
								{
									map.put(word, 1); 
								}	
							}	
							else
							{ 
							if(word.length() != 0)
							{
								wordCount++; 
							}
								Integer value = map.get(word);
							
								if (value != null)
								{
									map.put(word, value+1); 		
								}
								else
								{
									map.put(word, 1); 
								}	
							}
						}
					}
					line = buffer.readLine(); 
			}
			buffer.close(); 
		}
    	catch(FileNotFoundException ex) 
   		{
      		System.out.println("unable to open file " + filename );
    	}
    	catch(IOException ex)
    	{
      		System.out.println("error reading file " + filename);
    	}
	}
	
	//returns the total word count
  	public int getTotalWordCount()
  	{
  		return wordCount; 
  	}
  	
  	//returns the number of unique words
  	public int getUniqueWordCount()
  	{
  		return map.size(); 
  	}
  	
 	//returns the frequency value associated with this word.
  	public int getCount( String word )
  	{
  		 return map.get(word); 
  	}
  	//returns the value associated with this word divided by the total word count. 
  	public double getFrequency( String word )
  	{
  		return (double)getCount(word)/(double)wordCount; 
  	}
  	//returns a string representation of the map
  	public String toString()
  	{
  		return " " + map; 
  	}
  	
  	//writes the word count file to a filename
  	public void writeWordCountFile( String filename )
  	{
		//creates a new PrintWriter object that passes in a FileWriter object with the
		//filename and writes each pair on a new line.
		//Creates an ArrayList of key value pairs through the use of the entrySet() method
		try(PrintWriter printWriter = new PrintWriter(new FileWriter(filename)))
		{
			ArrayList<KeyValuePair<String, Integer>> strArray = map.entrySet();
			printWriter.println("totalWordCount:" + this.wordCount);
			for(KeyValuePair<String, Integer> pairs: strArray)
			{
				printWriter.println(pairs); 
			}
			printWriter.close(); 
		}
		catch(FileNotFoundException ex) 
   		{
      		System.out.println("unable to open file " + filename );
    	}
    	catch(IOException ex)
    	{
      		System.out.println("error reading file " + filename);
    	}
	}
	
	//reads in a file and then recreates a tree from that file
	public void readWordCountFile( String filename )
	{ 
		try 
		{
			FileReader read = new FileReader(filename); 
			BufferedReader buffer = new BufferedReader(read);
			String line = buffer.readLine();
			int i = 0; 
				
			while (line!= null)
			{
				String[] stringArray = line.split("[^a-zA-Z0-9']"); 	
				if (i== 0)
				{
					this.wordCount = Integer.parseInt(stringArray[1]); 
					line = buffer.readLine(); 
				}
				else
				{
// 					// KeyValuePair<String, Integer> pair = new KeyValuePair<String, Integer>(stringArray[0], Integer.parseInt(stringArray[1])); 
					map.put(stringArray[0], Integer.parseInt(stringArray[1])); 
					line = buffer.readLine(); 
				}
				i++; 
				
			}
			
			buffer.close(); 	
		}
		catch(FileNotFoundException ex) 
   		{
      		System.out.println("unable to open file " + filename );
    	}
    	catch(IOException ex)
    	{
      		System.out.println("error reading file " + filename);
    	}
	}
	
	//main method that allows for user to control whether they want to use a hashmap or
	//a bst map and allows for the user to control whether they want to include common words or not 	
  	public static void main( String[] args ) 
  	{ 
			Scanner scn = new Scanner(System.in); 
			System.out.println("Enter true for a BST map and false for a Hashmap");  
			String storeMethod = scn.nextLine();
			System.out.println("Enter true for files without common words and false for regular files");  
			String commonWords = scn.nextLine(); 
 
		WordCounter wc = new WordCounter(Boolean.parseBoolean(storeMethod)) ; 
		//loops through the length of the amount of command line arguments
		for (int i = 0; i < args.length; i++)
		{
			ArrayList<Long> times = new ArrayList<Long>(); 
			//loops through the analyzing of the file five times and computes the time
			//it took for the code to run 
			for (int j = 0; j<5; j++)
			{
				System.out.println("File analyzing:" + args[i]); 
				wc = new WordCounter(Boolean.parseBoolean(storeMethod)); 
				long startTime = System.currentTimeMillis(); 
				wc.analyze(args[i], Boolean.parseBoolean(commonWords)); 
				long endTime = System.currentTimeMillis(); 
				times.add((endTime - startTime)/1000); 			
				
			}
				//drops the min and max 
				Long min = Collections.min(times); 
				Long max = Collections.max(times); 
				times.remove(min); 
				times.remove(max); 
				long sum = 0; 
				//sums up the time
				for (Long t: times)
				{
					sum += t; 
				}
				//gets the average of the times 
				Long average = sum/(long)times.size(); 
				System.out.println("Average time in seconds is:" + Long.toString(average)); 
				System.out.println("Unique words:" + wc.getUniqueWordCount()); 
				System.out.println("Total words:" + wc.getTotalWordCount()); 
				
				if (Boolean.parseBoolean(storeMethod) == false)
				{
					System.out.println("Number of collisions:" + ((Hashmap<String,Integer>)wc.map).numCollisions); 
				}
				else
				{
					System.out.println( "Depth:" + ((BSTMap<String,Integer>)wc.map).depth()); 	
				}
		}

		
  	}
  		

  	
  	
  	
  



}