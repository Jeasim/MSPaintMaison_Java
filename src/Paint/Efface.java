package Paint;

import java.awt.Color;
import java.awt.Point;
import java.util.Vector;

public class Efface 
{
	private int tailleTrait;
	private Color couleur;
	private Vector<Point> points;
	
	public Efface(int tailleTrait, Color couleur)
	{
		this.tailleTrait = tailleTrait;
		this.couleur = couleur;
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
	
	public int getTailleTrait()
	{
		return tailleTrait;
	}
	
	public Color getCouleur()
	{
		return couleur;
	}
	
	public void setCouleur(Color couleur)
	{
		this.couleur = couleur;
	}
}
