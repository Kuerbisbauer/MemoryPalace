package kb.gui.main;

import java.util.List;

import javax.swing.JDialog;

public class ErrorDialog extends JDialog {

	private List<String> errorArray;
	
	public ErrorDialog(List<String> list){
		this.setErrorArray(list);
		generateErrorMessage();
		buildGui();
	}

	private void buildGui() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Erstellt für die FehlermeldungsGUI eine Liste aller Fehler
	 */
	private void generateErrorMessage() {
		for(String error : getErrorArray()){
			
		}
	}

	public List<String> getErrorArray() {
		return errorArray;
	}

	public void setErrorArray(List<String> list) {
		this.errorArray = list;
	}
}
