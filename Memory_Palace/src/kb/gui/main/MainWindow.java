package kb.gui.main;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class MainWindow extends JFrame {

	/*	###############################
	 * 	Konstante
	 * 	###############################
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Größe des Fensters
	 * wird im Konstruker verwendet
	 */
	private static final int WIDTH 		= 800;
	private static final int HEIGTH 	= 800;
	
	/**
	 * Position des Fensters auf der X und Y Achse
	 */
	private static final int POSX 	= 100;
	private static final int POSY	= 20;
	
	/* 
	 * ##############################
	 * Klassen
	 * ##############################
	 * 
	 */
	private Control 		control 		= new Control();
	private RandomThoughts 	randomThoughts	= new RandomThoughts();
	
	public MainWindow(){
		//Der Prozess wird beendet wenn das Programm über das geschlossen wird
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Aussehen wird an das OS angepasst
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
		buildGui();
	}

	/**
	 * GUI wird zusammengebaut.<p>
	 * Bestehend aus:
	 * <li>Control
	 * <li>RandomThoughts
	 */
	private void buildGui() {
		
		
		//Position und Größe des Fensters
		setBounds(POSX, POSY, WIDTH, HEIGTH);
		
		//Layoutmanager wird auf das BorderLayout festgelegt
		setLayout(new BorderLayout());
		
		//JPanels werden in das BorderLayout eingefügt
		this.add(control, BorderLayout.NORTH);
		this.add(randomThoughts, BorderLayout.CENTER);
	}
}
