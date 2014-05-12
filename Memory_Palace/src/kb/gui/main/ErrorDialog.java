package kb.gui.main;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;

import net.miginfocom.swing.MigLayout;

public class ErrorDialog extends JDialog {

	//TODO ActionListener + DOKU
	/*	###############################
	 * 	Konstante
	 * 	###############################
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Größe des Fensters
	 * wird im Konstruker verwendet
	 */
	private static final int WIDTH 		= 400;
	private static final int HEIGTH 	= 200;
	
	/**
	 * Position des Fensters auf der X und Y Achse
	 */
	private static final int POSX 	= 100;
	private static final int POSY	= 20;
	
	private List<String> errorArray;
	
	public ErrorDialog(List<String> list){
		this.setErrorArray(list);
		buildGui();
	}

	private void buildGui() {
		setLayout(new MigLayout("", "[]", "[]"));
		//Position und Größe des Fensters
		setBounds(POSX, POSY, WIDTH, HEIGTH);
		
		setModal(true);
		
		JLabel errorLabel 	= new JLabel(generateErrorMessage());
		JButton ok			= new JButton("OK");
		
		this.add(errorLabel, "cell 0 0, wrap");
		this.add(ok, "span");
	}

	/**
	 * Erstellt für die FehlermeldungsGUI eine Liste aller Fehler
	 * @return 
	 */
	private String generateErrorMessage() {
		String errorMessage = "<html><body>Folgende Felder wurden noch nicht ausgefüllt:<br>";
		for(String error : getErrorArray()){
			errorMessage = errorMessage + " - <b>" + error + "</b><br>"; 
		}
		errorMessage = errorMessage + "</body></html>";
		return errorMessage;
	}

	public List<String> getErrorArray() {
		return errorArray;
	}

	public void setErrorArray(List<String> list) {
		this.errorArray = list;
	}
}
