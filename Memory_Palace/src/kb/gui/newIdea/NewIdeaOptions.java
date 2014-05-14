package kb.gui.newIdea;

import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;

import kb.entities.Idea;
import kb.entities.Image;
import kb.gui.components.MPTextfield;
import kb.gui.imageControl.ShowImage;
import kb.gui.main.ErrorDialog;
import kb.queries.NewIdeaQueries;
import kb.queries.NewImageQueries;

public class NewIdeaOptions {

	private File imgFile = null;
	private List<String> errorArray = new ArrayList<String>();
	
	public NewIdeaOptions(){
		
	}
	
	public void saveIdea(JEditorPane textField, MPTextfield titleField){
		
		NewImageQueries newImageQueries = new NewImageQueries();
		NewIdeaQueries newIdeaQueries = new NewIdeaQueries();
		
		Image img = createImage(getImgFile());
		Idea idea = createIdea(textField, titleField, img);
		
		newImageQueries.newImage(img);
		newIdeaQueries.saveIdea(idea);
		
		System.out.println("Gespeichert");
	}
	
	/**
	 * Erstellt ein neues Image Objekt und speichert diese in die DB
	 * 
	 * @param file	- Bilddatei
	 * @return 		- Erstellte Image Objekt
	 */
	private Image createImage(File file){
		Image img = new Image();
		
		img.setImg_data(file);
		img.setImg_md5(null);
		
		return img;
	}
	
	private Idea createIdea(JEditorPane textField, MPTextfield titleField, Image img){
		Idea idea = new Idea();
		
		idea.setFk_img(img);
		idea.setIdea_title(textField.toString());
		idea.setIdeaText(textField.toString());
		
		return idea;
	}
	
	/**
	 * Der JDialog ShowImage wird aufgerufen.<br>
	 * Falls kein Bild ausgewählt wurde, so wird eine Fehlermeldung angezeigt.
	 */
	public void showChosenImage() {
		if(imgFile != null){
			ShowImage si = new ShowImage(imgFile);
			si.setVisible(true);
		}else{
			System.out.println("Es wurde noch kein Bild ausgewählt!");
		}
	}
	
	/**
	 * Ein Bild wird ausgewählt und der Text des "imageButton" Buttons wird dementsprechend geändert.<br>
	 * Falls der Dateiname zu lang ist, so wird dieser gekürzt (max 15 Zeichen).
	 */
	public void executeImgSelection(JButton imageButton) {
		
		setImgFile(selectImgFile());
		
		if(imgFile != null){
			String fileName = imgFile.getName();
			
			//Ist der String zu groß wird dieser gekürzt
			if(fileName.length() > 15)
				//maximal 15 Zeichen
				fileName = fileName.substring(0, 15);
			
			imageButton.setText(fileName);
		}else{
			System.out.println("Abgebrochen!");
		}
	}
	
	/**
	 * Mittels FileChooser wird ein Bild ausgewählt.
	 * 
	 * @return - Ausgewähltes Bild | null
	 */
	public File selectImgFile() {
		JFileChooser jfc = new JFileChooser();
		int returnValue = jfc.showOpenDialog(null);
		
		if(returnValue == JFileChooser.APPROVE_OPTION){
			return jfc.getSelectedFile();
		}else{
			return null;
		}
	}

	/**
	 * Mögliche Mauseingaben werden überprüft. In modifierInt steckt ein Integerwert
	 * welcher die Maus- und Tastatureingabe angibt. Mittels getMouseModifiersText()
	 * kann diese als String angezeigt werden. 
	 * 
	 * 16 - Linksklick
	 * 18 - Ctrl + Linksklick
	 * 
	 * @param arg0			- Übergebener Mausklick und mögliche Zuatztasten
	 * @param imageButton	
	 */
	public void imageButtonMouseClickBehavior(MouseEvent arg0, JButton imageButton) {

		//Gibt einen Int Wert zurück als Indentikator für die gerdrückte(n) Taste(n)
		int modifierInt = arg0.getModifiers();
		
		//Der Integerwert wird in einen lesbaren String umgewandelt
		//String modifier = MouseEvent.getMouseModifiersText(modifierInt);
		
		//Regex: Es wird nach einem + gesucht 
		//String[] modifierKey = modifier.split("\\+");
		
		//Fälle von Tastenkombinationen werden behandelt
		switch (modifierInt){
			//Linksklick
			case 16:
				executeImgSelection(/*imgFile, */imageButton);
				break;
			//Ctrl+Linksklick
			case 18:
				showChosenImage(/*imgFile*/);
				break;
		}
	}
	
	/**
	 * Überprüft ob das Titelfeld nicht leer ist oder "Titel" enthält.<br>
	 * Überprüft ob im Textfeld etwas drin steht.<br>
	 * Überprüft ob ein File vorhanden ist.<br>
	 * 
	 * @param textField 	- Texteditorbereich
	 * @param titleField 	- Titeltextfeld
	 * 
	 * @return - Wenn alles vorhanden ist -> TRUE
	 */
	public boolean checkData(MPTextfield titleField, JEditorPane textField) {
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
	 * Fehlende Daten werden in die "errorArray" Arraylist hinzugefügt für
	 * weitere Fehlerbehandlungen.
	 * 
	 * @param textField 	- Texteditorbereich
	 * @param titleField 	- Titeltextfeld
	 */
	public void checkMissingData(MPTextfield titleField, JEditorPane textField) {
		if(titleField.getText().trim().equals("") || titleField.getText().equals("Titel"))
			errorArray.add("Titelfeld ist leer oder wurde nicht geändert");
		if(textField.getText().equals(""))
			errorArray.add("Der Textbereich ist leer");
		if(imgFile == null)
			errorArray.add("Es wurde kein Bild eingefügt");
		
		if(!errorArray.isEmpty())
			showError();
	}
	
	/**
	 * Holt sich das Array von Error Strings und erstellt einen neuen
	 * JDialog um die Fehlermeldung auszugeben.
	 */
	private void showError(){
		ErrorDialog ed = new ErrorDialog(getErrorArray());
		ed.setVisible(true);
	}
	
	
	
	public List<String> getErrorArray() {
		return errorArray;
	}

	public void setErrorArray(List<String> errorStrings) {
		this.errorArray = errorStrings;
	}
	
	public File getImgFile() {
		return imgFile;
	}

	public void setImgFile(File imgFile) {
		this.imgFile = imgFile;
	}
}
