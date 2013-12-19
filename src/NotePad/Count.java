package NotePad;

import java.util.Scanner;


public class Count {

	private static String text;
	
	private int wordCount =0;
	private int charSpaceCount =0;
	private int lineCount=0;
	private int charCount=0;
	
  Count(String myText){
    	
		text=myText;
		charSpaceCount=text.length();
		
		for(char c: text.toCharArray()){
			
			if(c!=' ')
				charCount++;
					
		}
		
		
		
		
		Scanner s = new Scanner(text);
		 
		 while(s.hasNextLine())
		 {
			 s.nextLine();
			 lineCount++;
		 }
      s.close();
		
      Scanner s1 = new Scanner(text);
		  
		 while(s1.hasNext())
		 {
			 s1.next();
			 wordCount++;
		 }
      s1.close();
		     
      
      
      
	}
		
		
	public String getText(){
		return text;
	}
		
	public void setText(String myText){
		text=myText;
	}
		
    
  //This method returns the number of characters up till a certain line l.
 //This includes spaces and newlines characters.  It is useful for a goto line implementation.	
  public int getCharCountAtLine(int l){
		
		
		int myLength = 0;
		int counter =0;
		 Scanner s2 = new Scanner(text); 
		
		 while(s2.hasNextLine())
		 {
			String s =(s2.nextLine());
			myLength= myLength+s.length(); 
			counter++;
			if (counter==(l))
			{
				s2.close();
				return myLength;
			}
			else{
				myLength++;
			}
		 }
		 
		s2.close();
		return myLength;
		
  }
  
  
  public int getWordCount(){
		return wordCount;
	}
  
  
  public int getCharSpaceCount(){
		return charSpaceCount;
	}
 
  public int getCharCount(){
		return charCount;
	}
  
  
  public int getLineCount(){
	  return lineCount;
  }
 
}
