package kb.gui.newIdea;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JPanel;

import kb.gui.components.MPTextfield;
import kb.gui.imageControl.ShowImage;
import kb.gui.main.MainWindow;
import kb.interfaces.NewIdeaCreated;

public class NewIdea extends JDialog {
	
	/* 
	 * ###############################
	 * Konstante
	 * ###############################
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Gr��e des Fensters
	 * wird im Konstruker verwendet
	 */
	private static final int WIDTH 		= 400;
	private static final int HEIGTH 	= 400;
	
	/**
	 * Position des Fensters auf der X und Y Achse
	 */
	private static final int POSX 	= 100;
	private static final int POSY	= 20;
	
	/*
	 * ##############################
	 * Swing Komponenten
	 * ##############################
	 */
	
	//Eigenes Textfeld. Beinhaltet einen Statuswert ob in das Textfeld geklickt wurde oder nicht
	private MPTextfield titleField 	= new MPTextfield("Titel");
	
	private JButton	imageButton		= new JButton("Image");
	private JButton linkButton		= new JButton("Verbinden");
	private JButton	saveButton		= new JButton("Speichern");
	private JButton	cancelButton	= new JButton("Abbrechen");
	
	private JEditorPane	textField	= new JEditorPane();
	
	/*
	 * ##############################
	 * Zus�tzliche JPanels
	 * ##############################
	 */
	
	private JPanel	topPanel 	= new JPanel();
	private JPanel  bottomPanel	= new JPanel();
	
	/*
	 * ##############################
	 * Sonstige Attribute
	 * ##############################
	 */
	
	//Bilddatei
	private File imgFile;
	
	/**
	 * <b>Konstruktor</b><p>
	 * Richtet die Gr��e, Position des Fensters ein und setzt dieses in den Vordergrund (modal).
	 */
	public NewIdea(){
		this.setModal(true);
		//Position und Gr��e des Fensters
		setBounds(POSX, POSY, WIDTH, HEIGTH);
		
		buildJDialog();
	}

	/**
	 * Komponenten werden hinzugef�gt und mit Actionlistener versehen:<br>
	 * <li>JTextField
	 * <li>JButtons
	 * <li>EditorPane
	 */
	private void buildJDialog() {
		getContentPane().setLayout(new BorderLayout());
		
		//Erstellt ein JPanel um Titeltextfeld und Button unterzukriegen
		topPanel();
		
		//Erstellt ein JPanel um die Buttons Speichern und Abbrechen unterzukriegen
		bottomPanel();
		
		this.add(topPanel, BorderLayout.NORTH);
		this.add(textField, BorderLayout.CENTER);
		this.add(bottomPanel, BorderLayout.SOUTH);
		
		//Actionlistener
		addActionlistener();
	}

	/**
	 * Actionlistener f�r die Komponenten:
	 * <li>titleField
	 * <li>imageButton
	 * <li>saveButton
	 * <li>cancelButton
	 */
	private void addActionlistener() {
		titleField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				//Bedingung: Zum ersten Mal geklickt?
				if(!titleField.isClickedState()){
					titleField.setText("");
					titleField.setClickedState(true);
				}
			}
		});
		
		imageButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				//Gibt einen Int Wert zur�ck als Indentikator f�r die gerdr�ckte(n) Taste(n)
				int modifierInt = arg0.getModifiers();
				
				//Der Integerwert wird in einen lesbaren String umgewandelt
				String modifier = MouseEvent.getMouseModifiersText(modifierInt);
				
				//Regex: Es wird nach einem + gesucht 
				//String[] modifierKey = modifier.split("\\+");
				
				//F�lle von Tastenkombinationen werden behandelt
				switch (modifierInt){
					//Linksklick
					case 16:
						executeImgSelection();
					//Ctrl/Strg
					case 18:
						showChosenImage();
				}
			}
		});
		
		linkButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Verlinken");
			}
		});
		
		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(checkData()){
					NewIdeaOptions nio = new NewIdeaOptions();
					nio.saveIdea(imgFile, textField, titleField);
					dispose();
				}else{
					checkMissingData();
				}
			}
		});
		
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}
	
	/**
	 * Fehlender Daten werden in der Konsole gemeldet.
	 */
	protected void checkMissingData() {
		if(titleField.equals(" ") || titleField.equals("Titel"))
			System.out.println("Titelfeld leer!");
		if(textField.getText().equals(""))
			System.out.println("Textfeld leer!");
		if(imgFile == null)
			System.out.println("Kein Image eingef�gt!");
	}

	/**
	 * �berpr�ft ob das Titelfeld nicht leer ist oder "Titel" enth�lt.<br>
	 * �berpr�ft ob im Textfeld etwas drin steht.<br>
	 * �berpr�ft ob ein File vorhanden ist.<br>
	 * 
	 * @return - Wenn alles vorhanden ist TRUE
	 */
	protected boolean checkData() {
		boolean bool = false;
		
		if(!titleField.equals("") || !titleField.equals("Titel")){
			if(!textField.getText().equals("")){
				if(imgFile != null)
					bool = true;
			}
		}
		return bool;
	}

	/**
	 * Der JDialog ShowImage wird aufgerufen.<br>
	 * Falls kein Bild ausgew�hlt wurde, so wird eine Fehlermeldung angezeigt.
	 */
	protected void showChosenImage() {
		if(getImgFile() != null){
			ShowImage si = new ShowImage(getImgFile());
			si.setVisible(true);
		}else{
			System.out.println("Es wurde noch kein Bild ausgew�hlt!");
		}
	}

	/**
	 * Ein Bild wird ausgew�hlt und der Text des "imageButton" Buttons wird dementsprechend ge�ndert.<br>
	 * Falls der Dateiname zu lang ist, so wird dieser gek�rzt (max 15 Zeichen).
	 */
	protected void executeImgSelection() {
		selectImgFile();	
		String fileName = getImgFile().getName();
		
		//Ist der String zu gro� wird dieser gek�rzt
		if(fileName.length() > 15)
			//maximal 15 Zeichen
			fileName = fileName.substring(0, 15);
		
		imageButton.setText(fileName);
	}

	/**
	 * Mittels FileChooser wird ein Bild ausgew�hlt
	 */
	protected void selectImgFile() {
		JFileChooser jfc = new JFileChooser();
		int returnValue = jfc.showOpenDialog(null);
		
		if(returnValue == JFileChooser.APPROVE_OPTION){
			setImgFile(jfc.getSelectedFile());
		}	
	}

	/**
	 * Ein neues JPanel wird erstellt und mit einem FlowLayout versehen.<br><br>
	 * Beinhaltet:
	 * <li>saveButton
	 * <li>cancelButton
	 */
	private void bottomPanel() {
		bottomPanel.setLayout(new FlowLayout());
		bottomPanel.add(saveButton);
		bottomPanel.add(cancelButton);	
	}

	/**
	 * Ein neues JPanel wird erstellt und mit einem FlowLayout versehen.<br><br>
	 * Beinhaltet:
	 * <li>titleField
	 * <li>imageButton
	 */
	private void topPanel() {
		topPanel.setLayout(new FlowLayout());
		
		titleField.setColumns(25);
		
		topPanel.add(titleField);
		topPanel.add(imageButton);
		topPanel.add(linkButton);
	}
	
	public File getImgFile() {
		return imgFile;
	}

	public void setImgFile(File imgFile) {
		this.imgFile = imgFile;
	}
}
