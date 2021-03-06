package kb.gui.main;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import kb.gui.newIdea.NewIdea;
import kb.interfaces.NewIdeaCreated;

public class Control extends JPanel {
	
	/*	###############################
	 * 	Konstante
	 * 	###############################
	 */
	private static final long serialVersionUID = 1L;
	
	/*
	 * ##############################
	 * Swing Komponenten
	 * ##############################
	 */
	private JButton newIdea 	= new JButton("Neue Idee");
	private JButton ideaList 	= new JButton("Ideenliste");
	private JButton settings	= new JButton("Einstellugnen");
	
	/*
	 * ##############################
	 * Observerlisten
	 * ##############################
	 */
	private List<NewIdeaCreated> list = new ArrayList<NewIdeaCreated>();
	
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
		
		//Komponenten werden hinzugef�gt
		this.add(newIdea);
		this.add(ideaList);
		this.add(settings);
		
		addActionlistener();
	}

	/**
	 * Actionlistener f�r die Buttons:
	 * <li>newIdea
	 * <li>ideaList
	 * <li>settings
	 */
	private void addActionlistener() {
		newIdea.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent arg0) {
				NewIdea ni = new NewIdea();
				ni.setVisible(true);
				refreshGUI();
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

	/**
	 * Die GUI wird aktualisiert. D.h. RandomThoughts wird mit ersten Daten best�ckt.
	 */
	protected void refreshGUI() {
		for(NewIdeaCreated nic : list)
			nic.refreshGUI();
	}

	/**
	 * F�gt das MainWindow in die Liste der Observer
	 * 
	 * @param mainWindow - Das �bergebene MainWindow
	 */
	public void addNewIdeaOptionsListener(MainWindow mainWindow) {
		list.add(mainWindow);
	}
}
