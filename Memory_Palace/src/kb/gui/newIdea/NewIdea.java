package kb.gui.newIdea;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import kb.gui.components.MPTextfield;

public class NewIdea extends JDialog {
	
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
	
	private MPTextfield titleField 	= new MPTextfield("Titel");
	
	private JButton	imageButton		= new JButton("Image");
	private JButton	saveButton		= new JButton("Speichern");
	private JButton	cancelButton	= new JButton("Abbrechen");
	
	private JEditorPane	textField	= new JEditorPane();
	
	/*
	 * ##############################
	 * Zusätzliche JPanels
	 * ##############################
	 */
	
	private JPanel	topPanel 	= new JPanel();
	private JPanel  bottomPanel	= new JPanel();
	
	public NewIdea(){
		this.setModal(true);
		//Position und Größe des Fensters
		setBounds(POSX, POSY, WIDTH, HEIGTH);
		
		buildJDialog();
	}

	/**
	 * Komponenten werden hinzugefügt und mit Actionlistener versehen:<br>
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
		
		getContentPane().add(topPanel, BorderLayout.NORTH);
		getContentPane().add(textField, BorderLayout.CENTER);
		getContentPane().add(bottomPanel, BorderLayout.SOUTH);
		
		//Actionlistener
		addActionlistener();
	}

	/**
	 * Actionlistener für die Komponenten:
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
	}
}
