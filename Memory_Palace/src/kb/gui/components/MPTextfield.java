package kb.gui.components;

import javax.swing.JTextField;

public class MPTextfield extends JTextField {

	/**
	 * Wenn das Textfeld zum ersten Mal angeklickt wurde,
	 * so wird dieser Wert auf true geändert.
	 */
	private boolean clickedState = false;
	
	public MPTextfield(String string){
		super();
		setText(string);
	}

	public boolean isClickedState() {
		return clickedState;
	}

	public void setClickedState(boolean clickedState) {
		this.clickedState = clickedState;
	}
}
