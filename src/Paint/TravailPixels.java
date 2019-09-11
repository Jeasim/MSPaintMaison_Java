package Paint;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class TravailPixels extends JFrame {
	private BufferedImage i ;
	private SurfDessin surface;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TravailPixels frame = new TravailPixels();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TravailPixels() throws IOException{
		setTitle ( "Travail sur les pixels");
		setBounds(100, 100, 850, 620);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		i = ImageIO.read(new File( "photos/un.jpg"));
		
		surface = new SurfDessin();
		surface.setSize(850,620);
		getContentPane().add(surface);

	}
	
	private class SurfDessin extends JPanel
	{
		protected void paintComponent ( Graphics g )
		{
			super.paintComponent(g);
			// va chercher un pixel
		     int couleur =  i.getRGB(10, 10);
		     // couleur int négative
		     System.out.println(couleur);
		     // transformer en hexadecimal, ff : alpha complètement opaque 
		     System.out.println(Integer.toHexString(couleur));
		    
		      //je change les pixels bleu-ciel pour verts 
		      for ( int j = 0; j < 800; j++)
		    	  for ( int k=0; k<600; k++)
		    	  {
		         
		    		int pixel= i.getRGB(j,k);
		    		//créer un objet Color avec la valeur int 
		    		Color couleurPixel = new Color ( pixel);
		    		//plus facile d'identifier bleu ciel avec schéma TSL ( teinte saturation luminosité, en anglais Hue Saturation Brightness )
		            float[]tsl =  Color.RGBtoHSB(couleurPixel.getRed(), couleurPixel.getGreen(), couleurPixel.getBlue(), null);
		             //bleu teinte entre 220 et 240 , la teinte est donnée en proportion du cercle chromatique /360 
		             System.out.println(tsl[0]); //teinte
		             System.out.println ( tsl[2]); //luminosité
		             //teinte bleue et grande luminosité
		             if ( tsl[0]>0.556 && tsl[0]< 0.666 && tsl[2] >0.7)
		            	 i.setRGB(j,k,0x50FF50); //ce qui suit 0x est en hexadecimal
		             
		    	  }
		  
		      g.drawImage(i, 0,0,800,600, this);
		     
		}
	}

}
