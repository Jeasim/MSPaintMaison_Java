package Paint;

import java.awt.Color;
import java.awt.Point;

public class OutilsTriangle 
{
	private int clicX;
	private int clicY;

	private int[] x;
	private int[] y;
	private int largeur;
	private int hauteur;

	private Color couleur;
	private int tailleTrait;


	public OutilsTriangle(int clicX, int clicY, Color couleur, int tailleTrait)
	{
		this.clicX = clicX;
		this.clicY = clicY;
		this.couleur = couleur;
		this.tailleTrait = tailleTrait;
		x = new int[3];
		y = new int[3];
	}


	public void tracerTriangle(Point p)
	{
		if(p.y > clicY)
		{
			x[0] = clicX;
			y[0] = p.y;

			x[1] = (clicX + p.x) / 2;
			y[1] = clicY;

			x[2] = p.x;
			y[2] = p.y;
		}
		
		else if(p.y <= clicY)
		{
			x[0] = clicX;
			y[0] = clicY;

			x[1] = (clicX + p.x) / 2;
			y[1] = p.y;

			x[2] = p.x;
			y[2] = clicY;
		}


	}

	public int[] getX() 
	{
		return x;
	}

	public int[] getY() 
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
