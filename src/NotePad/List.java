package NotePad;



import java.util.Iterator;
import java.util.Vector;

import javax.swing.text.Position;

public class List {

	
	private Vector<Integer> stack;//the dequeue represents the layers of the outline, the 1, A, I, i
	
	private Vector<Integer> depth;// this keeps track of the 1, 2, 3 ,4
	private boolean active;
	
	private Vector<Position> offset;
	List(){
		 stack = new Vector<Integer>(0);
		 
		 depth = new Vector<Integer>(0);
		 offset = new Vector<Position>(0);
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
		int offs = myCount.getCharCountAtLine(l)+1;
		
		stack.add(i);
		depth.add(i);
		
		offset.add(d.createPositon(offs));
		
		active = true;
		String append = "\n1.";
		d.insertString(offs, append);
		
		
		
		//s = s + append;
		return ;
	}
	
	public void newLine(String s, Caret c, MDocument d){
		//increment the current numeral by 1
		if(active){
			
			int index = getIndex(c, s);
			int sp = stack.get(index); 
			int dp =depth.get(index);
			
			
			stack.add(index+1, sp);
			
			dp++;
			depth.add(index+1, dp);
			
			String append = "\n";
			for(int i=1; i<sp; i++){
				
				append= append+"\t";
				
			}
			
			
			
			 append = append + dp + ".";
			
			int offs = c.getDot();
			
			d.insertString(offs, append);
			Position p =d.createPositon(offs+1+sp-1);
			offset.add(p);
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
			
			int tabCount = stack.get(index);//
			int sp=tabCount+1;
			stack.set(index, sp);
			
			String append = "\t " +dp;
			//line dequeue stays the same
			//int lp=offset.get(index);
			Position p =offset.get(index);
			int lp= p.getOffset();
			
			d.remove(lp, 2);
			d.insertString(lp , append);
			
			Position p2 = d.createPositon(lp+1);
			offset.set(index, p2);
			
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
		stack.set(index, sp);
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
			dp=deTemp;	
			
			}
		}
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
		int line=c.getLineNumber(s);
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
			//System.out.println(be+" "+offs+" "+ dot);
			if((offs>=be)&&(offs<=dot)){
				 i= counter;
			}
			counter++;
		}
		
		
		
		return i;
	}
	
	
	
	
	
	
}



/*Bug Report
 * 
 * 
 * NewLine in the middle of the list doesn't work
 * Backwards stack doesn't work
 * 
 * 
 * 
 * 
 */
