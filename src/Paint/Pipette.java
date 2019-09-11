package Paint;

import java.awt.Component;
import java.awt.image.BufferedImage;

public class Pipette 
{

	private int x;
	private int y;

	public Pipette(int x, int y)
	{
		this.x = x;
		this.y = y;
	}

	public int trouverRGBPixel(BufferedImage image)
	{
		return image.getRGB(x, y);
	}



	public int getX()
	{
		return x;
	}

	public int getY()
	{
		return y;
	}


}
