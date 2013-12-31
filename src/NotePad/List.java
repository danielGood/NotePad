package NotePad;
/*
 * List Implementation
 * 
 * 
 * 
 * Ordering
 * Indentation
 * Rotating Icons
 * 
 * Node
 * 
 * 
 * Multiple Lists
 *    
 * Icons
 * 		I. A. 1. a. i.
 * 		I. II. III.   
 * 
 * Default Values
 * 		
 * Abstraction
 * 		especially for listeners
 * 
 * List Button
 *     create icon before text
 *     remove icon before text
 *     integrate with adjacent list
 * 
 * 
 * 
 * NewLine
 *      new list icon on enter
 *      new list icon has incremented by one
 *      
 * Forward
 * 		button 
 * 		tab at specific point
 * 
 * Backwards
 * 		button
 * 		backspace
 * 		
 * Backspace
 * 		string based list icon is deleted as a unit
 * 
 * Modified Word Wrap
 * 		indentation 
 * 
 * When you have a icon with no text at the bottom of the list and press enter twice that icon disappears
 */


import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.text.BadLocationException;
import javax.swing.text.Position;

public class List {

	
	private Vector<Integer> stack;//the dequeue represents the layers of the outline, the 1, A, I, i
	
	private Vector<Integer> depth;// this keeps track of the 1, 2, 3 ,4
	private Vector<Integer> nodeLength;
	private boolean active;
	
	private Vector<Position> offset;
	
	List(){
		 stack = new Vector<Integer>(0);
		 
		 depth = new Vector<Integer>(0);
		 offset = new Vector<Position>(0);
		 nodeLength = new Vector<Integer>(0);
		 
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
		
		
		//Count myCount = new Count(s);
		//int offs = myCount.getCharCountAtLine(l);
		int offs = c.getDot();
		stack.add(i);
		depth.add(i);
		//System.out.println(offs);
		nodeLength.add(3);
		
		active = true;
		String append = "\n 1.";
		
		d.insertString(offs, append);
		
		offset.add(d.createPositon(offs+1));
		
		//s = s + append;
		return ;
	}
	
	public void newLine(String s, Caret c, MDocument d){
		//increment the current numeral by 1
		if(active){
			
			int index = getIndex(c, s);
			//System.out.println(index);
			if(index==-1){
				return;
			}
			int sp = stack.get(index); 
			int dp =depth.get(index);
			int nL=nodeLength.get(index);
			//System.out.println(dp);
			
			stack.add(index+1, sp);
			nodeLength.add(index+1, nL);
			dp++;
			depth.add(index+1, dp);
			
			updateDepth();
			
			String append = "\n"+" ";
			for(int i=1; i<sp; i++){
				
				append= append+"\t";
				
			}
			
			
			
			 append = append + dp + ".  ";
			 //System.out.println(dp);
			int offs = c.getDot();
			
			d.insertString(offs, append);
			//System.out.println(offs +1 +sp -1);
			Position p =d.createPositon(offs+1+sp-1);
			
			//Position p =d.createPositon(offs+1+sp);
			offset.add(index+1, p);
			print(d);
			//debug(d);
			
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
			int index = getIndex(c, s);
			
			int dp=1;
			depth.set(index, dp);
			updateDepth();
			int nL=nodeLength.get(index);
			nodeLength.set(index, nL+1);
			int tabCount = stack.get(index);//
			int sp=tabCount+1;
			stack.set(index, sp);
			
			String append = "\t " +dp+".  ";
			//line dequeue stays the same
			//int lp=offset.get(index);
			Position p =offset.get(index);
			int lp= p.getOffset();
			//we added a buffer so lp +1
			d.remove(lp+1, 2);
			//System.out.println(d.getText(lp, 2));
			d.insertString(lp+1 , append);
			//System.out.println(append);
			Position p2 = d.createPositon(lp+2);
			offset.set(index, p2);
			//print(d);
			
		}
		else{//when tab is tab
			
			String append ="\t";
			int offs = c.getDot();
			d.insertString(offs, append);
			
		}
		
		
		
		return ;
	}
	
	public void backwards(String s, Caret c, MDocument d){
		
		
		int index = getIndex(c, s);
		//
		int sp=stack.get(index);
		if(sp==1){
			return;//bottom level 
		}
		sp=sp-1;
		int nL=nodeLength.get(index);
		nodeLength.set(index, nL-1);
		//Now we find depth
		//we look back through the stack in reverse order and try to find something equal to  sp
	    //then add one to its depth
		//if we find nothing its depth equals one
		Iterator<Integer>  myIt= stack.iterator();
		Iterator<Integer>  myDeIt= depth.iterator();
		int dp=1;//default value
		while(myIt.hasNext()){
			int temp= myIt.next();
			int deTemp= myDeIt.next();
			
			if(temp==sp)
			{
			dp=deTemp+1;	
			
			}
		}
		stack.set(index, sp);
		depth.get(index);
		depth.set(index, dp);
		////////////////////////////////////////////
		
		//int offs = offset.get(index);
		Position p = offset.get(index);
		int offs = p.getOffset();
		String append =dp+".";
		d.remove(offs-1, 3);
		d.insertString(offs-1, append);
		
		Position p2 = d.createPositon(offs-1);
		offset.set(index, p2);
	}
	
	public void delete(){
		
	}
	
	public void modWordWrap(){
		
	}
	
	
	int getIndex(Caret c, String s){
		int i=-1;
		int line=c.getLineNumber(s);//here is the problem
		int dot = c.getDot();
		Count myCount = new Count(s);
		//System.out.println(line);
		int be= myCount.getCharCountAtLine(line-1)+1;
		/*
		 * get line number for dot
		 * get beginning of line for dot
		 * cycle through offsets
		 * 
		 */
		
		
		Iterator<Position>  myIt=offset.iterator();
		int counter =0;
		while(myIt.hasNext()){
			//int offs = myIt.next();
			Position p= myIt.next();
			int offs=p.getOffset();
			//System.out.println(offs);
			//System.out.println(be+" "+offs+" "+ dot);
			if((offs>=be)&&(offs<=dot)){
				 i= counter;
			}
			counter++;
		}
		
		//System.out.println(i);
		
		return i;
	}
	
	
	/*The vector depth is a convenience variable and its value can be derived.
	 * This methods updates all of the depth vector values.
	 * stack dependent
	 */
	
	void updateDepth(){
		Deque<Integer> lastDepth = new ArrayDeque<Integer>();
		//lastDepth is a queue which contains the last depth for that layer of stack
		int index=0;
		
		
		//iterate through stack vector and reassign depth values
		Iterator<Integer>  s = stack.iterator();
		int dp=0;
		while(s.hasNext()){
			int newStack = s.next();
			if(index==0){
				dp=1;
				
			}
			if(index>0){
				int sp = lastDepth.size();
				int last = lastDepth.peek();
				if(newStack==sp){//enter scenario
					last++;
					dp=last;
					lastDepth.pop();
					
				}
				if(newStack>sp){//forwards scenario
					dp=1;
					
				}
				if(newStack<sp){//backwards scenario
					lastDepth.pop();
					last=lastDepth.pop();
					last++;
					dp=last;
				}
				
			}
			//System.out.println(index +" " +dp +" ");
			
			lastDepth.push(dp);
			depth.set(index, dp);
			index++;
		}
		
	}
	
	
	//updates numbers

	//dependent on depth and position
	void print(MDocument d){
		//iterate depth and position
		//remove
		//insert
		Iterator<Integer>  itDepth = depth.iterator();
		Iterator<Position>  p = offset.iterator();
		//System.out.println("length" +d.getLength());
		Iterator<Integer> myS = stack.iterator();
		Iterator<Integer> len = nodeLength.iterator();
		int index=0;
		/*
		 * A node should go something like this "\n\t\t 3.  "
		 * myS is the number of tabs
		 * For now everyone has a newline 
		 * Forward and Backwards manipulates an already existing node
		 * 
		 * depth is the number
		 * stack is the rotation icon - not there yet
		 * 
		 * the position is at the beginning of the line?
		 * 
		 */
		while(itDepth.hasNext()){
			int sp = myS.next();
			int dp =itDepth.next();
			Position myP =p.next();
			int myLen=len.next();
			
			
			int x = myP.getOffset();
			
			
			int currDepth= depth.get(index);
			String str=currDepth+".  ";
			
			
			//tabs in string here
			
			
			
			
				d.remove(x+1, myLen);
				
				
				
				
				d.insertString(x+1, str);
				int strLen =str.length();
				nodeLength.set(index, strLen);
				x=myP.getOffset();
				//System.out.println("x:" + x);
			
			
			index++;
			
		}
		
		
	}
	
	//positions can only be on the consecutive left edges of line
	//this method enforces that
	
	void enforcePositions(){
		
		
	}
	
	
	
	void debug(MDocument d){
		
		Iterator<Integer>  s = stack.iterator();
		Iterator<Integer>  di = depth.iterator();
		Iterator<Position>  p = offset.iterator();
		while(p.hasNext()){
			
			
			System.out.println(di.next()+" "+s.next()+" "+ p.next());
			
			
		}
		
		//now form a list of all of start of dot
		//Count myCount = new Count(d.getText());
		//int line= myCount.getLineCount();
		//System.out.println("done1");
		//for(int i=1;i<line;i++){
			
		//	System.out.println(myCount.getCharCountAtLine(i));
		//}
		//System.out.println("done1");
		//System.out.println("done1");
		//System.out.println("done1");
	}
	
	
	
	
	
}



/*Bug Report
 * 
 * 
 * 
 * List Button
 * 		does not work on first line
 * 
 * Forwards
 *     weird side effects
 * NewLine
 *     seems alright
 * Backwards
 *      weird side effects
 * 
 * Weird Side Effects are usually floating strings and numbers they come from improper order of remove and insert
 * I should unify string methods to get rid of these
 * 
 * 
 * 
 *
 *
 * 		
 * 
 * 
 * it effects the rest of the list
 * Also -1 error from getIndex
 * 
 * Exception while removing reference
 * first line bug: on first line press list then enter to get -1 exception
 */
