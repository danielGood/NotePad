package NotePad;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;


public class DocumentListFilter extends DocumentFilter {
	
	
	boolean list = false;
	
	DocumentListFilter(){
		
		
	}
	
	
	public void insertString(FilterBypass fb, int offs,
            String str, AttributeSet a) throws BadLocationException{
		//System.out.println("a");
		super.insertString(fb, offs, str, a);
		
	}
	
	
	public void remove(FilterBypass fb, int offs,
            int l) throws BadLocationException{
		//System.out.println("b");
		
		super.remove(fb, offs, l);
	}
	
	public void replace(FilterBypass fb, int offs,int l,
            String str, AttributeSet a) throws BadLocationException{
		//System.out.println("c");
		super.replace(fb, offs, l, str, a);
	}
	
	
	void setList(boolean l){
		list=l;
		
	}

}
