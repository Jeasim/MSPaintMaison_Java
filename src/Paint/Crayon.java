package Paint;

import java.awt.Color;
import java.awt.Point;
import java.util.Vector;

public class Crayon 
{
	private Vector<Point> points;
	private Color couleur;
	private int tailleTrait;

	public Crayon(Color couleur, int tailleTrait) 
	{
		this.couleur = couleur;
		this.tailleTrait = tailleTrait;
		points = new Vector<Point>();
	}


	public void ajouterPointsVecteur(Point point)
	{
		points.add(point);
	}

	public Vector<Point> getVecteurPoints()
	{
		return points;
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
