package NotePad;
import java.util.Scanner;

import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Position;





public class MDocument {
   
	private static Document myDoc;
	
	MDocument(Document d){
		myDoc=d;
	   
   }
	
	Document getDocument(){
		return myDoc;
	}
	
	
	int getLength(){
		
		
		return myDoc.getLength();
	}
	
	
	
	 public String getText(){
		try{
		return myDoc.getText(0, myDoc.getLength());
		}
		catch(BadLocationException e){
			
			return "";
		}
	}
	
	
	
	 public String getText(int i, int l){
		try{
		return myDoc.getText(i, l);
		}
		catch(BadLocationException e){
			
			return "null";
		}
	}

	void insertString(int offset, String str){
		try {
			myDoc.insertString(offset, str, null);
			
		} catch (BadLocationException e) {
			
			e.printStackTrace();
		}
	}
	
	public  Position createPositon(int offs){
		
		
		try {
			return myDoc.createPosition(offs);
		} catch (BadLocationException e) {
			
			e.printStackTrace();
			return null;
		}
	}
	
	void remove(int offs, int len){
		
		try {
			myDoc.remove(offs, len);
		} catch (BadLocationException e) {
			
			e.printStackTrace();
		}
		
	}
	
	
	String getLine(int l){
		
		String line = "";
		int counter = 0;
		Scanner s = new Scanner(this.getText());
		while(s.hasNextLine()){
			counter++;
			if(l==counter)
			   line=s.nextLine();
			else{
				s.nextLine();
			}
		   
		}
		s.close();
		return line;
	}
	
	
	
}
