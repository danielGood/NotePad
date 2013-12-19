package NotePad;
import java.util.Iterator;
import java.util.List;

import javax.swing.text.DefaultCaret;
import javax.swing.text.Document;
import javax.swing.text.Position;



//data structure of positions for outline
public class PositionList  {

	private List<Position> pos;
	
	
	
	PositionList(){
		
	}
	
	public void init(int i, int j, MDocument d, Caret c){
		//DefaultCaret dc = (DefaultCaret) (NoteFrame.editorPane.getCaret());
		
		String s= d.getText();
		Count myCount = new Count(s);
		
		//this fetches the current line the caret is on
		//and the number of characters up to that line
		int h= myCount.getCharCountAtLine(c.getLineNumber(s));
		
	  d.insertString(h, "\n");
	  pos.add(d.createPositon(h+2));  
	  pos.add(d.createPositon(h+7));
	  d.insertString(h+6, "1.");
	   
	}
	
	void forward(MDocument d, Caret c){
		
		String s= d.getText();
		Count myCount = new Count(s);
		
		//this fetches the current line the caret is on
		//and the number of characters up to that line
		 int myLine=c.getLineNumber(s);
			int start = myCount.getCharCountAtLine(myLine-1) + 1 ;
	  	    int end= myCount.getCharCountAtLine(myLine);
	  	    
	  	    
		
		
	}
	
	void backwards(){
		
	}
	
	void newLine(int i, int j, MDocument d){
		
		pos.add(d.createPositon(i));  
		pos.add(d.createPositon(j));
		
	}
	
	
	
	
	public String toString(){
		
		Iterator<Position> it =pos.iterator();
		
		while (it.hasNext()){
			System.out.println(it.next().getOffset());
		}
		
		return null;
		
	}

}
