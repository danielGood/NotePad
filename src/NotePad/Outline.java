package NotePad;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JDialog;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;

import java.awt.BorderLayout;

import javax.swing.JButton;



import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Outline extends NoteFrame  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static List myList;
    private static Action enterAction;
    private static Action tabAction;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Outline dialog = new Outline();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public Outline() {
		
		enterAction = new EnterAction();
		tabAction = new ForwardAction();
		editorPane.getInputMap().put(KeyStroke.getKeyStroke("ENTER"), "enter");
		editorPane.getActionMap().put("enter", enterAction);
		
		editorPane.getInputMap().put(KeyStroke.getKeyStroke("TAB"), "tab");
		editorPane.getActionMap().put("tab", tabAction);
		
		
		/*
		Action EnterAction = new AbstractAction() {
		    public void actionPerformed(ActionEvent e) {
		        //do nothing
		    }
		}
		*/
		
		JToolBar toolBar = new JToolBar();
		getContentPane().add(toolBar, BorderLayout.NORTH);
	    myList = new List();
		
		JButton list = new JButton("List");
		list.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				myList.initializeList(editorPane.getText(), myCaret, myDoc);
				//editorPane.setText(s);
			}
		});
		toolBar.add(list);
		
		JButton backwards = new JButton("Backwards");
		backwards.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myList.backwards(myDoc);
			}
		});
		toolBar.add(backwards);
		
		JButton forwards = new JButton("Forward");
		forwards.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myList.forwards(editorPane.getText(),myCaret, myDoc);
				
			}
		});
		toolBar.add(forwards);
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
		
		//missing 3 things backwards via escape, mod word wrap, and proper delete
		//
	}
	
	 
	
	    class EnterAction extends AbstractAction
	
	    {
	
	        
			private static final long serialVersionUID = 1L;

			public void actionPerformed( ActionEvent tf )
	
	        {
	        	myList.newLine(editorPane.getText(), myCaret, myDoc);
				
	            
	        } // end method actionPerformed()
	
	         
	    } 
	    
	     class ForwardAction extends AbstractAction
		
	    {
	
	        
			private static final long serialVersionUID = 1L;

			public void actionPerformed( ActionEvent tf )
	
	        {
				 myList.forwards(editorPane.getText(),myCaret, myDoc);
				
	            
	        } // end method actionPerformed()
	
	         
	    } 
	

}
