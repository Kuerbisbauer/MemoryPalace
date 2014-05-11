package kb.gui.main;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Control extends JPanel {
	
	/*
	 * ##############################
	 * Swing Komponenten
	 * ##############################
	 */
	
	private JButton newIdea 	= new JButton("Neue Idee");
	private JButton ideaList 	= new JButton("Ideenliste");
	private JButton settings	= new JButton("Einstellugnen");
	
	public Control(){
		buildPanel();
	}

	/**
	 * Komponenten werden zusammengesetzt.<br>
	 * Bestehend aus:
	 * <li>JButton
	 * 
	 * <p><br>
	 * Flowlayout wird verwendet, damit die Buttons zentriert und
	 * stets nebeneinander dargestellt werden.
	 */
	private void buildPanel() {
		
		setLayout(new FlowLayout());
		
		//Komponenten werden hinzugefügt
		this.add(newIdea);
		this.add(ideaList);
		this.add(settings);
		
		addActionlistener();
	}

	/**
	 * Actionlistener für die Buttons:
	 * <li>newIdea
	 * <li>ideaList
	 * <li>settings
	 */
	private void addActionlistener() {
		newIdea.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Neue Idee!");
			}
		});
		ideaList.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Ideenliste!");
			}
		});
		settings.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Einstellungen!");
			}
		});
	}
}
