package NotePad;

import java.util.Scanner;

import javax.swing.text.DefaultCaret;

//defaultcaret
//rectangle, rectangle 2d, rectangleshape, 
//object

public class Caret {

	private DefaultCaret caret;
	
	
	Caret(DefaultCaret myCaret){
		
		caret=myCaret;
	}
	
	
	void setDot(int dot){
		caret.setDot(dot);
	}
	
	
	int getDot(){
		
		return caret.getDot();
	}
	
	int getLineNumber(String text){
		
		int dot = getDot();
	   
		Scanner s2 = new Scanner(text); 
		
		int sum =0;
		
		int counter =0;
		while(s2.hasNextLine()){
			String line=s2.nextLine();
			
			
			sum=sum+line.length();//the sum is the end of line marker
			//System.out.println(sum +" " + dot);
			if(dot>sum){
				counter++;
				
			}
			
		}
		s2.close();
		return counter;
	}
	
	
	
	
	
	
	

	
	
}
