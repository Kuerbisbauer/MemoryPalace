package kb.gui.main;

import java.awt.BorderLayout;
import java.util.logging.Level;

import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

public class RandomThoughts extends JPanel {
	
	/*
	 * ##############################
	 * Swing Komponenten
	 * ##############################
	 */
	
	private JLayeredPane randomThoughts = new JLayeredPane();
	
	public RandomThoughts(){
		buildPanel();
	}

	/**
	 * Komponenten werden zusammengesetzt.<br>
	 * Bestehend aus:
	 * <li>JLabel
	 */
	private void buildPanel() {
		setLayout(new BorderLayout());
		
		//Das JPanel wird erhöht
		Border border = new BevelBorder(BevelBorder.RAISED);
		this.setBorder(border);
		
		//Komponenten werden hinzugefügt
		this.add(randomThoughts, BorderLayout.CENTER);
	}
}
