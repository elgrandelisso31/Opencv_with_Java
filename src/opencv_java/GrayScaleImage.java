package opencv_java;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class GrayScaleImage extends JFrame implements ChangeListener {

	private static final long serialVersionUID = 1L;
	private JLabel imageLabel;
    private JSlider graySlider;
    private Mat image;
    private Mat grayImage;
    
    public GrayScaleImage() {
        // Chargement de l'image
    	String imgFile= "images/multi.png";
        Mat image = Imgcodecs.imread(imgFile);
        
        // Conversion de l'image en niveaux de gris
        Imgproc.cvtColor(image, grayImage, Imgproc.COLOR_BGR2GRAY);
        
        // Initialisation de la fenêtre
        setTitle("Image en niveaux de gris");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(image.width(), image.height() + 50);
        
        // Ajout de la JLabel contenant l'image
        imageLabel = new JLabel(new ImageIcon());
        add(imageLabel);
        
        // Ajout du JSlider pour ajuster les niveaux de gris
        graySlider = new JSlider(0, 255, 128);
        graySlider.addChangeListener(this);
        add(graySlider, BorderLayout.SOUTH);
        
        setVisible(true);
    }
    
    // Méthode appelée lorsque la valeur du JSlider change
    public void stateChanged(ChangeEvent e) {
        // Modification de la quantité de gris en fonction de la valeur du JSlider
        Imgproc.threshold(grayImage, grayImage, graySlider.getValue(), 255, Imgproc.THRESH_TOZERO_INV);
        Imgproc.threshold(grayImage, grayImage, graySlider.getValue(), 255, Imgproc.THRESH_BINARY);
        
        // Affichage de l'image modifiée
        imageLabel.setIcon(new ImageIcon(HighGui.toBufferedImage(grayImage)));
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        
        // Création de la fenêtre
        new GrayScaleImage();

	}

}
