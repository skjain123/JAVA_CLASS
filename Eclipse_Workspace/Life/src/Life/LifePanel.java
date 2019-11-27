/**
 * Panel for Conway's Life simulation. This draws the Grid and Cells
 * 
 * Inspired by Jason Galbraith's youtube videos on his youtube channel
 * 
 * Modified By: Sunil Jain
 * 
 * Date: 9/19/19
 * 
 * */

package Life;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class LifePanel extends JPanel //get the JPanels
{
	boolean[][] cells; //create the cells boolean
	
	double width;
	double height;
	
	public LifePanel(boolean[][] in) 
	{
		cells = in; //get the cells from Life.java
	}
	
	public void paintComponent(Graphics g) //repaint the board called by Life.java with frame.repaint()
	{
		super.paintComponent(g);
		width = (double)this.getWidth() / cells[0].length; //get the width of one cell
		height = (double)this.getHeight() / cells[0].length; ///get the height of one cell
		
		g.setColor(Color.black); //set the color to black
		
		//go through all the cells and if its true then paint it black with the width and height above
		for (int row = 0; row < cells.length; row++)
		{
			for (int column = 0; column < cells[0].length; column++)
			{
				if (cells[row][column] == true) 
				{
					g.fillRect((int)Math.round(column*width), (int)Math.round(row*height), (int)width + 1, (int)height + 1);
				}
			}
		}
		
		g.setColor(Color.black); //set the color to black again
		
		//paint the horizontal lines black
		for (int x = 0; x < cells[0].length + 1; x++) 
		{
			g.drawLine((int)Math.round(x*width), 0, (int)Math.round(x*width), this.getHeight());
		}
		
		//paint the vertical lines black
		for (int y = 0; y < cells[0].length + 1; y++)
		{
			g.drawLine(0,(int)Math.round(y*height),this.getWidth(),(int)Math.round(y*height));
		}
		
	}
}