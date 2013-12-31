package symbols;

public class RomanNumerals {

	private int[] value= new int[]{1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
	private String[] num= new String[]{"m", "cm", "d", "cd", "c", "xc", "l", "xl", "x", "ix", "v", "iv", "i"};
			
	public static void main(String[] args) {
		
           int i =8;
           RomanNumerals num = new RomanNumerals(i);
	}
	
	RomanNumerals(int i){
		String str="";
		int index=0;
		for(int myValue :value){
			while(myValue<=i){
				i=i-myValue;
				str=str+num[index];
				
			}
			index++;
		}
		
		System.out.println(str);
	}

}
