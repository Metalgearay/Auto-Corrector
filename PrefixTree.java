
public class PrefixTree {
	int count;
	Node root;
	public PrefixTree()
	{
	root = new Node(' '); //creates blank node	
	}
	public void addWord(String word)
	{
		Node current = root;
		for(char cr:word.toCharArray())
		{  
			Node child = current.childList[cr-'a']; // maps the ascii value to the index of the array
			if(child!=null)
			{
				current = child;
			
			}
			else
			{ 
				current.childList[cr-'a'] = new Node(cr); //if character node does not exist make one
				current = current.childList[cr-'a'];
			}
			
		}
		current.isEnd = true;
	}
	public PrefixTree findPrefix(String Prefix)
	{
		Node current = root; //goes through the tree and checks to see if the characters exist if it does return the subtree
		for(char cr:Prefix.toCharArray())
		{
			Node child = current.childList[cr-'a'];
			if(child==null){
				return null;
			}
			else{
				current = current.childList[cr-'a']; 
			}
		}
			PrefixTree subtree = new PrefixTree();
			subtree.root = current;
			return subtree;
		}
	public boolean contains(String word) //checks to contain if the word exists in the tree
	{  count =0;
		Node current = root;
		for(char cr:word.toCharArray())
		{
			Node child = current.childList[cr-'a'];
			if(child==null){
				return false;
			}
			else{
				current = current.childList[cr-'a'];
				count++; //keeps count of how far down the word until the child goes to null uses to make substring
			}
		}
		if(current.isEnd==true){
			return true;}
		return false;
	}
	public static int editDistance(String s, String t) // more or less an impletation of what appears on the slides 
	{
		int[][] distance = new int[s.length()+1][t.length()+1];
		for(int i=0;i<=s.length();i++)
			distance[i][0] = i;
		for(int j=1;j<=t.length();j++)
			distance[0][j]=j;
		for(int i =1;i<= s.length();i++)
			for(int j=1;j<=t.length();j++)
				distance[i][j] = Math.min(Math.min(
						distance[i-1][j]+1,
						distance[i][j-1]+1),
						distance[i-1][j-1]+((s.charAt(i-1)==t.charAt(j-1))?0:1)); // uses obscure wildcard type because I am too lazy to make two if statments
		return distance[s.length()][t.length()];
	}
	

}

