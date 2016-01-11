

import java.util.ArrayList;

public class Node {
	char content;
	boolean isEnd;
	 Node[] childList; // children are stored in an array
	public Node(char c)
	{   
		isEnd = false;
		childList = new Node[26]; //creates an array of 26
		content =c;
		}

}
