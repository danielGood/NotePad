package NotePad;


import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;



public class FileChooser {
	
	String myFile = "";
	//JFileChooser chooser = new JFileChooser("C:\\Users\\Dan Good\\workspace\\WriterToolKit");
	JFileChooser chooser ;
	
	FileChooser(){
		chooser = new JFileChooser("C:\\Users\\Dan Good\\workspace\\WriterToolKit");
	}
	FileChooser(String dir){
		chooser = new JFileChooser(dir);
	}

	
	
	public void resetName(){
		myFile="";
	}
	
	
	public void open(JTextComponent myC){
		
		Document doc = myC.getDocument();
		
		FileReader inputStream = null;
		try{
			
			
			 FileNameExtensionFilter filter = new FileNameExtensionFilter(
				        "Text Documnet","txt");
				    chooser.setFileFilter(filter);
			 
			 int returnVal = chooser.showOpenDialog(null);
			    if(returnVal == JFileChooser.APPROVE_OPTION) {
			       myFile= chooser.getSelectedFile().getPath();
			    }
			

			inputStream = new FileReader(myFile);
			myC.read(inputStream, doc);
			
			inputStream.close();
		}
		catch(IOException exception){
			
			System.out.println("Io exception");
			
		}
		
	}
	
	public void save(JTextComponent myC){

		
		FileWriter outputStream = null;
		try{
			FileNameExtensionFilter filter = new FileNameExtensionFilter(
			        "Text Documnet","txt");
			    chooser.setFileFilter(filter);
			if(myFile==""){    
					int returnVal = chooser.showSaveDialog(null);
					  if(returnVal == JFileChooser.APPROVE_OPTION) {
					       myFile=chooser.getSelectedFile().getPath();
					       myFile = myFile + ".txt";
					    }
			}
			outputStream = new FileWriter(myFile);
			myC.write(outputStream);
			
			outputStream.close();
		}
		catch(IOException exception){
			
			System.out.println("Io exception");
			
		}
		
	}
	
	public void saveAs(JTextComponent myC){
		

		
		FileWriter outputStream = null;
		try{
			FileNameExtensionFilter filter = new FileNameExtensionFilter(
			        "Text Documnet","txt");
			    chooser.setFileFilter(filter);
			int returnVal = chooser.showSaveDialog(null);
			  if(returnVal == JFileChooser.APPROVE_OPTION) {
			       myFile=chooser.getSelectedFile().getPath();
			       myFile = myFile + ".txt";
			    }
			outputStream = new FileWriter(myFile);
			myC.write(outputStream);
			
			outputStream.close();
		}
		catch(IOException exception){
			
			System.out.println("Io exception");
			
		}
		
	}
	
	

}
