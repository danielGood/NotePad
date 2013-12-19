package NotePad;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import javax.swing.JMenuBar;
import javax.swing.JMenu;

import java.awt.Insets;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.text.AbstractDocument;
import javax.swing.text.DefaultCaret;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NoteFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected JEditorPane editorPane = new JEditorPane();
	private JScrollPane scrollPane ;
	private FileChooser file = new FileChooser();
	protected  Caret myCaret=new Caret((DefaultCaret) editorPane.getCaret());
	MDocument myDoc = new MDocument(editorPane.getDocument());
	AbstractDocument adoc;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NoteFrame frame = new NoteFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public NoteFrame() {
		
	  
		
		if (editorPane.getDocument() instanceof AbstractDocument) {
		    adoc = (AbstractDocument)editorPane.getDocument();
		    adoc.setDocumentFilter(new DocumentListFilter());
		} 
		
		
		scrollPane = new JScrollPane(editorPane);
		
		setBounds(100, 100, 706, 408);
		getContentPane().setLayout(new BorderLayout());
		//contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		//getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
	//	contentPanel.setLayout(gbl_contentPanel);
	
		
		
		GridBagConstraints gbc_textPane = new GridBagConstraints();
		gbc_textPane.insets = new Insets(0, 0, 5, 0);
		gbc_textPane.fill = GridBagConstraints.BOTH;
		gbc_textPane.gridx = 0;
		gbc_textPane.gridy = 0;
		//contentPanel.add(textPane, gbc_textPane);
		
		//contentPanel.add(scrollPane, gbc_textPane);
		getContentPane().add(scrollPane, BorderLayout.CENTER);
	
		


		//////////////////////////////////////////////////menubar begins
		{
			JMenuBar menuBar = new JMenuBar();
			setJMenuBar(menuBar);

			////////////////////////////////////////////////////File menu
			{
				JMenu mnFile = new JMenu("File");
				menuBar.add(mnFile);
				{
					JMenuItem mntmNew = new JMenuItem("New");
					mntmNew.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							editorPane.setText("");
							file.resetName();
						}
					});
					mnFile.add(mntmNew);
				}


				{
					JMenuItem mntmWordCount = new JMenuItem("Word Count");


					mntmWordCount.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {

							//System.out.println(myCaret.getDot());
							//myDoc.createPositon(4);
							//System.out.println();

							//System.out.println(unEscapeString(editorPane.getText()));

							WordCount dialog = new WordCount(editorPane.getText());
							dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
							dialog.setVisible(true);

						}
					});


					mnFile.add(mntmWordCount);
				}


				{
					JMenuItem mntmOpen = new JMenuItem("Open");
					mntmOpen.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {

							file.open(editorPane);

						}
					});
					mnFile.add(mntmOpen);
				}


				{
					JMenuItem mntmSave = new JMenuItem("Save");
					mntmSave.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {

							file.save(editorPane);
						}
					});
					mnFile.add(mntmSave);
				}


				{
					JMenuItem mntmSaveAs = new JMenuItem("Save As...");
					mntmSaveAs.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {

							file.saveAs(editorPane);

						}
					});
					mnFile.add(mntmSaveAs);
				}
				{
					JMenuItem mntmExit = new JMenuItem("Exit");
					mntmExit.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							System.exit(0);
						}
					});
					mnFile.add(mntmExit);
				}
			}/////////////////////////////////////////File menu Ends
			////////////////////////////////////////////Edit Menu
			{
				JMenu mnEdit = new JMenu("Edit");
				menuBar.add(mnEdit);
				{
					JMenuItem mntmCopy = new JMenuItem("Copy");
					mntmCopy.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							editorPane.copy();;

						}
					});
					mnEdit.add(mntmCopy);
				}


				{
					JMenuItem mntmPaste = new JMenuItem("Paste");
					mntmPaste.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							editorPane.paste();
						}
					});
					mnEdit.add(mntmPaste);
				}
			}//////////////////////////////////////////////Edit Menu ends
			{
				JMenu mnView = new JMenu("View");
				menuBar.add(mnView);
				{
					JMenuItem mntmGotoLine = new JMenuItem("Goto Line...");
					mntmGotoLine.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {


							String myString =JOptionPane.showInputDialog(null, "Enter line Number");
							int myLine = Integer.parseInt(myString);
							Count myCount = new Count(editorPane.getText());
							int dot = myCount.getCharCountAtLine(myLine);

							myCaret.setDot(dot+1);

						}
					});
					mnView.add(mntmGotoLine);
				}
			}
		}//menubar ends


	}

	MDocument getDocument(){
		return myDoc;
	}

}



