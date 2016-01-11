

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class AutoCorrector {
	int level;
	String prefix;
	ArrayList<String> test;
	PrefixTree tree;
	public PrefixTree filetoTree(String filename) throws FileNotFoundException
	{   File file = new File(filename);
	    Scanner reader = new Scanner(file); //reads the files and add each lines to the prefixtree
	    tree = new PrefixTree();
	    while(reader.hasNextLine())
	    {
	    	String word = reader.nextLine();
	    	tree.addWord(word);
	    }
		return tree;
		}
	public String correct(String str)
	{   
		if(tree.contains(str)==true){ //returns the word if it exists
		 return str;
		}
		if(tree.contains(str)!=true) // 
		{   
			 prefix = str.substring(0, tree.count);
			if(prefix.equals(""))
				return str+" cannot be found are you sure its even a real word?";//returns this statment if the word does not exist at all
			else
				{PrefixTree subtree = tree.findPrefix(prefix);
				  test = new ArrayList<String>(); // creates an array list to hold the words
				  findCompleteWords(prefix,subtree.root);
				  for(int i=0; i<test.size();i++)
				  {   int number = 100; //sets it with a high value to make less than possible
				      int index =0;
					  if(PrefixTree.editDistance(str,test.get(i))<number)
						  number= PrefixTree.editDistance(str,test.get(i)); // gets the edit distance number
					      index =i; //records index number
					  return test.get(i); //returns word at i 
					  
				  }
				  
				}
			
		}
		return null;
	}
	public void findCompleteWords(String prefix, Node node) { 

	    if(node == null) return; 

	    if(node.isEnd) 
	        test.add(prefix);
	    
	    for(int i = 0; i < node.childList.length; i++) {  
	        if(node.childList[i] != null) {
	            findCompleteWords(prefix + (char)(i+'a'), node.childList[i]);  //recursively builds a word with prefix and each node character
	        }  
	    }  
	}

	public static void main(String[] args) throws FileNotFoundException {
		AutoCorrector corrector = new AutoCorrector(); //main tester
		corrector.filetoTree(args[0]);
		Scanner input = new Scanner(System.in);
		while(1==1){ // simple infinte loop to make the enter process repeat
		String   word = input.nextLine();
		System.out.println(corrector.correct(word));
		}
		
	}

}
