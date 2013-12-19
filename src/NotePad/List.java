package NotePad;



import java.util.ArrayDeque;
import java.util.Deque;

public class List {

	private boolean[] active = new boolean[100];//parallel with line
	private Deque<Integer> stack;//the deque represents the layers of the outline
	private int curr = 1;//current deque
	private int[] line = new int[100];//a temporary implementation for line numbers
	
	List(){
		 stack = new ArrayDeque<Integer>();
		 
	}
	
	/*
	 * Outline has 6 crucial features
	 * 
	 * Start - button
	 * New Line - enter, /n
	 * Forward - tab or /t at letter; button
	 * Backwards - button
	 * Delete -
	 * Modified Word Wrap
	 */
	
	public String initializeList(String s){
		//active=true;
		int i = 1;
		stack.push(i);
		
		String append = "\n 1.";
		//String append = "<ol> <li> </li> </ol> ";
		//NotePad.htmlDoc.insertBeforeEnd(i, "<ol> <li> </li> </ol> ");
		s = s + append;
		return s;
	}
	
	public String newLine(String s){
		//increment the current numeral by 1
		
		int i = stack.pop();
		i++;
		String append ="\n " + i + ".";
		stack.push(i);
		s= s+ append;
		return s;
	}
	
	public String forwards(String s, Caret myCaret){
		
		
		
		curr++;
		int i=1;
		String append = "\t ";
		stack.push(i);
		s= s;
		return s;
	}
	
	public void backwards(){
		
	}
	
	public void delete(){
		
	}
	
	public void modWordWrap(){
		
	}
	
	
}
