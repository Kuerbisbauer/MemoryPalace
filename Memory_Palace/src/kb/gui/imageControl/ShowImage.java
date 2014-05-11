package kb.gui.imageControl;

import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;

public class ShowImage extends JDialog {
	
	private File file;
	/**
	 * <b>Konstruktor</b><br>
	 * Diese Klasse wird �ber den imageButton von NewIdea aufgerufen.
	 * 
	 * @param file - Das ausgew�hlte Bild
	 */
	public ShowImage(File file){
		setModal(true);
		this.file = file;
		generateImage();
	}

	/**
	 * Aus dem mitgelieferten File wird ein ImageIcon erstellt und dieses
	 * in einen JLabel gelegt. Die H�he und Breite des Bildes wird
	 * bestimmt und der JDialog wird dementsprechend angepasst.
	 */
	private void generateImage() {
		try {
			//Aus dem File wird ein Bild erstellt
			BufferedImage img = ImageIO.read(file);
			
			//Die Gr��e des Bildes wird erstellt
			int height = img.getHeight();
			int width  = img.getWidth();
			
			//Der JLabel wird gebraucht um das Bild anzuzeigen
			JLabel l = new JLabel("");
			
			//BufferedImage wird in ImageIcon umgesetzt, damit JLabel diesen anzeigen kann
			l.setIcon(new ImageIcon(img));
			this.add(l, BorderLayout.CENTER);
			
			//Die Gr��e des Fensters wird an die Gr��e des Bildes angepasst.
			this.setSize(width, height);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
