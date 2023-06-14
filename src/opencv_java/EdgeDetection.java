package opencv_java;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class EdgeDetection {

	public static void main(String[] args) {
		
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		Mat color = Imgcodecs.imread("images/messi.jpeg");
		Mat gray = new Mat();
		Mat draw = new Mat();
		Mat wide = new Mat();
		//cette methode prends 3 paramettre, l'image d'entrée, l'image de sortie et la fonction
		Imgproc.cvtColor(color, gray, Imgproc.COLOR_BGR2GRAY);
		//détection de contours à l'aide de l'algorithme de Canny 
		//img d'entrée, img de sortie, 50 tt les pixel<50 seront pas detecter comme contour,seuil sup , 3 le type de filtre 
		Imgproc.Canny(gray, wide, 50, 150, 3, false);
		
		//POURQUOI??
		wide.convertTo(draw, CvType.CV_8U);
		if(Imgcodecs.imwrite("images/messi_Canny.jpg",draw)) {
			System.out.println("edge detected");
			
		}
		
	}

}
