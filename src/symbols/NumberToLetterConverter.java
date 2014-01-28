package symbols;

import java.util.Iterator;
import java.util.Vector;

public class NumberToLetterConverter {

	private Vector<Integer> base26;
	private String letters ="";
	
	
	NumberToLetterConverter(int i){
		
		
		base26 = new Vector<Integer>(0);
		boolean loop=true;
		
		while(loop){
			int x=0;
			int r=0;
			if(i>26){
				x=i/26;
				r=i%26;
			}
			else{
				x=i;
				
			}
			
			if (x>26){
				base26.add(r);
				System.out.println(r);
			}
			else{
				base26.add(r);
				System.out.println(r);
				base26.add(x);
				System.out.println(x);
				loop=false;
			}
				
			i=x;
			
		}
		
		Iterator<Integer> myNum =base26.iterator();
		String str="";
		while(myNum.hasNext()){
			int thisNum=myNum.next();
			str=toLetter(thisNum)+str;
			
		}
		letters=str;
		//System.out.println(str);
	}
	
	String toLetter(int i){
		String str="";
		
		switch(i){
		case 1:str="a";
		break;
		case 2:str="b";
		break;
		case 3:str="c";
		break;
		case 4:str="d";
		break;
		case 5:str="e";
		break;
		case 6:str="f";
		break;
		case 7:str="g";
		break;
		case 8:str="h";
		break;
		case 9:str="i";
		break;
		case 10:str="j";
		break;
		case 11:str="k";
		break;
		case 12:str="l";
		break;
		case 13:str="m";
		break;
		case 14:str="n";
		break;
		case 15:str="o";
		break;
		case 16:str="p";
		break;
		case 17:str="q";
		break;
		case 18:str="r";
		break;
		case 19:str="s";
		break;
		case 20:str="t";
		break;
		case 21:str="u";
		break;
		case 22:str="v";
		break;
		case 23:str="w";
		break;
		case 24:str="x";
		break;
		case 25:str="y";
		break;
		case 26:str="z";
		break;
		}
		
		return str;
	}
	
	
	String getLetters(){
		return letters;
	}

}
