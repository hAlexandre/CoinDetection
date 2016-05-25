package pacote2892028989.CONTROL;    

import java.awt.AWTException;
import java.awt.Frame;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.image.PixelGrabber;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Vector;

public class Jpeg {
  
	String salvar, string = "";	

	int i, Quality = 80;
	Image image = null;
    FileOutputStream dataOut = null;
    File file, outFile;
    JpegEncoder jpg;
    
    public Jpeg(String s){
    	salvar = s+".jpg";
    	
      string = "C:\\Users\\Alexandre A\\Pictures\\37500erroteste.png";
      
//      Arquivo de saída
      
    outFile = new File(salvar+".jpg");
    i = 1;    
    while (outFile.exists()) {
      outFile = new File(salvar.substring(0, string.lastIndexOf(".")) + (i++)+".jpg" );
      if (i > 100)
        System.exit(0);
    }
    file = new File("C:\\Users\\Alexandre A\\Pictures\\37500erro.png");
    if (file.exists()) {
    	System.out.println("HU");
      try {
        dataOut = new FileOutputStream(outFile);
      } catch (IOException e) {
      }
      try {
        Quality = Integer.parseInt("4");
      } catch (NumberFormatException e) {
        
      }
      image = Toolkit.getDefaultToolkit().getImage(salvar);
      jpg = new JpegEncoder(image, Quality, dataOut);
      jpg.Compress();
      try {
    	  
        dataOut.close();
      } catch (IOException e) {
      }
    }
    }
}
  
  
