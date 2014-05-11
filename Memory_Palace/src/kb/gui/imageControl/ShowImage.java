package kb.gui.imageControl;

import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JScrollPane;

public class ShowImage extends JDialog {
	
	private static final long serialVersionUID = 1L;
	
	private File file;
	/**
	 * <b>Konstruktor</b><br>
	 * Diese Klasse wird über den imageButton von NewIdea aufgerufen.
	 * 
	 * @param file - Das ausgewählte Bild
	 */
	public ShowImage(File file){
		setModal(true);
		this.file = file;
		generateImage();
	}

	/**
	 * Aus dem mitgelieferten File wird ein ImageIcon erstellt und dieses
	 * in einen JLabel gelegt. Die Höhe und Breite des Bildes wird
	 * bestimmt und der JDialog wird dementsprechend angepasst.
	 */
	private void generateImage() {
		try {
			//Aus dem File wird ein Bild erstellt
			BufferedImage img = ImageIO.read(file);
			
			//Die Größe des Bildes wird erstellt
			int height = img.getHeight();
			int width  = img.getWidth();
			
			//ImageIcon wird aus der Datei gebaut
			ImageIcon ii = new ImageIcon(file.toString());
			
			//In die ScrollPane wird ein JLabel eingebunden (GrabAndScrollLabel extends JLabel)
			JScrollPane jsp = new JScrollPane(new GrabAndScrollLabel(ii));

			this.add(jsp, BorderLayout.CENTER);
			
			//Die Größe des Fensters wird an die Größe des Bildes angepasst.
			this.setSize(width, height);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
