package NotePad;



import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

public class List {

	
	private Deque<Integer> stack;//the dequeue represents the layers of the outline, the 1, A, I, i
	
	private Deque<Integer> depth;// this keeps track of the 1, 2, 3 ,4
	private boolean active;
	private Deque<Integer> offset;//this keeps track of the offsets
	List(){
		 stack = new ArrayDeque<Integer>();
		 offset = new ArrayDeque<Integer>();
		 depth = new ArrayDeque<Integer>();
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
	
	public void initializeList(String s, Caret c, MDocument d){
		
		int i = 1;
		
		int l= c.getLineNumber(s);
		Count myCount = new Count(s);
		int offs = myCount.getCharCountAtLine(l);
		
		stack.push(i);
		depth.push(i);
		offset.push(offs+1);
		active = true;
		String append = "\n1.";
		d.insertString(offs, append);
		
		
		
		//s = s + append;
		return ;
	}
	
	public void newLine(String s, Caret c, MDocument d){
		//increment the current numeral by 1
		if(active){
			//int l= c.getLineNumber(s)+1;//the next line
			
			int sp = stack.peek(); 
			int dp =depth.peek();
			stack.push(sp);
			
			dp++;
			depth.push(dp);
			
			String append = "\n";
			for(int i=1; i<sp; i++){
				
				append= append+"\t";
				
			}
			
			
			
			 append = append + dp + ".";
			
			int offs = c.getDot();
			
			d.insertString(offs, append);
			offset.push(offs+1+sp-1);
		}
		else{
			//int l = c.getLineNumber(s);//current line
			String append ="\n";
			int offs = c.getDot();
			d.insertString(offs, append);
			
		}
		
		
		return ;
	}
	
	public void forwards(String s, Caret c, MDocument d){
		
		if(active){
			
			int dp = depth.pop();
			dp=1;
			depth.push(dp);
			
			int tabCount = stack.pop();//
			int sp=tabCount+1;
			stack.push(sp);
			
			String append = "\t " +dp;
			//line dequeue stays the same
			int lp=offset.pop();
						
			d.remove(lp, 2);
			d.insertString(lp , append);
			offset.push(lp+1);
			
		}
		else{//when tab is tab
			
			String append ="\t";
			int offs = c.getDot();
			d.insertString(offs, append);
			
		}
		
		
		
		return ;
	}
	
	public void backwards(MDocument d){
		
		//
		int sp=stack.pop();
		if(sp==1){
			return;//bottom level 
		}
		sp=sp-1;
		stack.push(sp);
		//Now we find depth
		//we look back through the stack in reverse order and try to find something equal to  sp
	    //then add one to its depth
		//if we find nothing its depth equals one
		Iterator<Integer>  myIt= stack.descendingIterator();
		Iterator<Integer>  myDeIt= depth.descendingIterator();
		int dp=1;//default value
		while(myIt.hasNext()){
			int temp= myIt.next();
			int deTemp= myDeIt.next();
			if(temp==sp)
			{
			dp=deTemp;	
			
			}
		}
		depth.pop();
		depth.push(dp);
		////////////////////////////////////////////
		
		int offs = offset.pop();
		String append =dp+".";
		d.remove(offs-1, 3);
		d.insertString(offs-1, append);
		offset.push(offs-1);
	}
	
	public void delete(){
		
	}
	
	public void modWordWrap(){
		
	}
	
	
}
