package app;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class Counter {
	
	private Map<String, Integer> occurenceMappings;
	private Trie prefixTrie;

	public void countWords() throws FileNotFoundException {
		
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("test.txt").getFile());
		
		
		Map <String, Integer> hm = new HashMap<String, Integer>();
		Trie trie = new Trie();
		
		Scanner scan = new Scanner(file);
		
		while(scan.hasNext()) {
			
			String [] words = scan.nextLine().split(" ");
			for(String word : words) {
				
				
				word = word.replaceFirst("^[^a-zA-Z]+", "");
				word = word.replaceAll("[^a-zA-Z]+$", "");
				
				String [] filteredWords = word.split("--");
				for(String filteredWord : filteredWords) {
					
					//insert into trie
					String trieWord = filteredWord.replaceAll("[^a-zA-Z]", "");
					trieWord = trieWord.toLowerCase();
					
					int occurences = trie.search(trieWord);

					if(trieWord.compareTo("") != 0) {
						
						if(occurences==-1) {
						
							occurences = trie.insert(trieWord);
						
						}
						
						hm.put(trieWord, occurences);
					}
				}
			}
		}
		
		Map<String, Integer> sorted = sortMap(hm);
		
		this.prefixTrie = trie;
		this.occurenceMappings = sorted;
		
		scan.close();
	
	}
	
	public void findWordsWithPrefix(String prefix) {
		
		List<String> words = this.prefixTrie.findPrefixes(prefix);
		
		for(String word : words) {
			
			System.out.println(word + ": " + this.occurenceMappings.get(word));
		}
	}
	
	public void printToFile() {
		
		try{
		    PrintWriter writer = new PrintWriter("output.txt", "UTF-8");
		    
		    Object [] arr = this.occurenceMappings.entrySet().toArray();
		    for(int i = arr.length-1; i >= 0; i--) {
		    	
		    	Entry<String, Integer> entry = (Entry<String, Integer>) arr[i];
		    	writer.println(entry.getKey() + ": " + entry.getValue());
		    }
		    
		    writer.close();
		} catch (IOException e) {
		   // do something
		}
	}
	
	public HashMap<String, Integer> sortMap(Map<String, Integer> map) {
		
		List<Map.Entry<String, Integer>> list = new LinkedList<Map.Entry<String, Integer>>(map.entrySet());
	      
			
		Collections.sort(list, new Comparator<Entry<String, Integer>>() {
			public int compare(Entry<String, Integer> entry1, Entry<String, Integer> entry2) {
	               return ((Comparable<Integer>) entry1.getValue()).compareTo(entry2.getValue());
			}
		});
	       
	      
		HashMap<String, Integer> sortedHashMap = new LinkedHashMap<String, Integer>();
		for(Entry<String, Integer> entry : list) {
			
			sortedHashMap.put(entry.getKey(), entry.getValue());
		}
		
		return sortedHashMap;
	}
	
}
