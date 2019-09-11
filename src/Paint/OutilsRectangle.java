package Paint;

import java.awt.Color;
import java.awt.Point;

public class OutilsRectangle 
{

	private int clicX;
	private int clicY;
	
	private int x;
	private int y;
	private int largeur;
	private int hauteur;
	
	private Color couleur;
	private int tailleTrait;


	public OutilsRectangle(int clicX, int clicY, Color couleur, int tailleTrait)
	{
		this.clicX = clicX;
		this.clicY = clicY;
		this.couleur = couleur;
		this.tailleTrait = tailleTrait;
	}

	public void tracerRectangle(Point p)
	{
		if(p.x >= clicX){

			largeur = p.x - clicX;
			x = clicX;

			if(p.y >= clicY) {

				hauteur = p.y - clicY;
				y = clicY;

			}else if( p.y < clicY) {
				
				hauteur = clicY - y;
				y = p.y;
				
			}
		}else if(p.x < clicX){
			
			largeur = clicX - p.x;
			x = p.x;
			
			if(p.y >= clicY) {
				
				hauteur = p.y - clicY;
				y = clicY;
				
			}else if(p.y < clicY) {
				
				hauteur = clicY - y;
				y = p.y;
				
			}
		}
	}

	public int getX() 
	{
		return x;
	}

	public int getY() 
	{
		return y;
	}

	public int getLargeur() 
	{
		return largeur;
	}

	public int getHauteur() 
	{
		return hauteur;
	}
	
	public Color getCouleur()
	{
		return couleur;
	}
	
	public int getTailleTrait() 
	{
		return tailleTrait;
	}


}
