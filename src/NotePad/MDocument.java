package NotePad;
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
	
	
	
	
}
