import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Panel extends JPanel
{
	
	double width;
	double height;
	
	double x = 0;
	double y = 0;
	
	public Panel(double X, double Y) 
	{
		x = X;
		y = Y;
	}
	
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		width = (double)this.getWidth() / 100;
		height = (double)this.getHeight() / 100;
		
		g.setColor(Color.black);
		
		g.fillRect((int)x, (int)y, 50, 50);
		
		for (int x = 0; x < 100 + 1; x++)
		{
			g.drawLine((int)Math.round(x*width), 0, (int)Math.round(x*width), this.getHeight());
		}
		for (int y = 0; y < 100 + 1; y++)
		{
			g.drawLine(0,(int)Math.round(y*height),this.getWidth(),(int)Math.round(y*height));
		}
	}
}