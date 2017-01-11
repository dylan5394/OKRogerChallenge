package app;

import java.util.ArrayList;
import java.util.List;

class TrieNode {
    TrieNode[] arr;
    boolean isEnd;
    int occurences = 0;
    // Initialize your data structure here.
    public TrieNode() {
        this.arr = new TrieNode[26];
    }
 
}
 
public class Trie {
    private TrieNode root;
 
    public Trie() {
        root = new TrieNode();
    }
 
    // Inserts a word into the trie.
    public int insert(String word) {
        TrieNode p = root;
        for(int i=0; i<word.length(); i++){
            char c = word.charAt(i);
            int index = c-'a';
            if(p.arr[index]==null){
                TrieNode temp = new TrieNode();
                p.arr[index]=temp;
                p = temp;
            }else{
                p=p.arr[index];
            }
        }
        p.isEnd=true;
        return ++p.occurences;
    }
 
    // Returns if the word is in the trie.
    public int search(String word) {
        TrieNode p = searchNode(word);
        if(p==null){
            return -1;
        }else{
            if(p.isEnd) {
                return ++p.occurences;
            }
        }
 
        return -1;
    }
 
    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
        TrieNode p = searchNode(prefix);
        if(p==null){
            return false;
        }else{
            return true;
        }
    }
    
    public List<String> findPrefixes(String prefix) {
    	
    	List<String> prefixes = new ArrayList<String>();
    	
    	TrieNode p = searchNode(prefix);
    	
    	if(p!=null) {
    		
    		if(p.isEnd) {
    			prefixes.add(prefix);
    		}
    		for(int i = 0; i < 26; i ++) {
    			
    			if(p.arr[i] != null) {
    				
    				char append = (char) (i + 'a');
    				prefixes.addAll(findPrefixes(prefix + append));
    			}
    		}
    	}
    	return prefixes;
    }
 
    public TrieNode searchNode(String s){
        TrieNode p = root;
        for(int i=0; i<s.length(); i++){
            char c= s.charAt(i);
            int index = c-'a';
            if(p.arr[index]!=null){
                p = p.arr[index];
            }else{
                return null;
            }
        }
 
        if(p==root)
            return null;
 
        return p;
    }
}