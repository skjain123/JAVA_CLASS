/*
 * This program is an implementation of Conway's life simulation.
 * 
 * Inpsired by Jason Galbraith's youtube video tutorials on his youtube channel
 * 
 * Modified/Created By: Sunil Jain
 * 
 * */

package Life;

//imports
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Life implements MouseListener, ActionListener, Runnable //able for user to click the mouse and press buttons and run the step function multiple times
{
	//Variables and arrays
	int cellsLength = 10; //how many cells in the array
	
	boolean[][] cells = new boolean[cellsLength][cellsLength];
	boolean running = false;
	
	//initializing the buttons, containers, frames, etc...
	JFrame frame = new JFrame("Life Simulation");
	LifePanel panel = new LifePanel(cells);
	Container south = new Container();
	JButton step = new JButton("Step");
	JButton start = new JButton("Start");
	JButton stop = new JButton("Stop");

	public Life() 
	{
		//setting up the window and adding all of the buttons, containers, and frames, etc...
		frame.setSize(600,600); //default size of the window
		
		frame.setLayout(new BorderLayout());
		frame.add(panel, BorderLayout.CENTER);
		panel.addMouseListener(this);
		
		//south container
		south.setLayout(new GridLayout(1,3));
		south.add(step);
		step.addActionListener(this);
		south.add(start);
		start.addActionListener(this);
		south.add(stop);
		stop.addActionListener(this);
		
		//adding the containers and how to exit
		frame.add(south, BorderLayout.SOUTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public static void main(String[] args)
	{
		new Life(); //constructor
	}
	
	@Override
	public void actionPerformed(ActionEvent event) //did the player do something if so
	{
		if (event.getSource().equals(step) == true) //did the player click the step button if so...
		{
			step(); //run step
		} else if (event.getSource().equals(start) == true) //did the player click the start button if so...
		{
			running = true;
			Thread t = new Thread(this);
			t.start(); //run the start function
		} else if (event.getSource().equals(stop) == true) //did the player click the stop button if so...
		{
			running = false; //stop the start function
		}
	}
	
	@Override
	public void run() //comes from runnable
	{
		while (running == true) //while running is true do this...
		{
			try //try catch block for no errors
			{
				step(); //run step
				Thread.sleep(250); //then wait 1/4 of a second to run again
			} catch (Exception ex) //if there is a error then catch it and ignore
			{
				ex.printStackTrace();
			}
		}
	}
	
	public void step()
	{	
		boolean[][] nextCells = new boolean[cells.length][cells[0].length]; //future generation of cells
		
		//checks for every single cell
		for (int rows = 0; rows < cells.length; rows++)
		{
			for (int columns = 0; columns < cells[0].length; columns++)
			{
				int neighborCount = 0; //resets the neighborCount
				
				//check for a neighbor in every direction and if there is one then add 1 to neighborCount
				if (rows > 0 && columns > 0 && cells[rows-1][columns-1] == true) //top-left
				{
					neighborCount++;
				}
				if (columns > 0 && cells[rows][columns-1] == true) //left
				{
					neighborCount++;
				}
				if (rows < cells.length-1 && columns > 0 && cells[rows+1][columns-1] == true) //bottom-left
				{
					neighborCount++;
				}
				if (rows > 0 && cells[rows-1][columns] == true) //top
				{
					neighborCount++;
				}
				if (rows < cells.length-1 && columns > 0 && cells[rows+1][columns] == true) //bottom
				{
					neighborCount++;
				}
				if (rows > 0 && columns < cells.length-1 && cells[rows-1][columns+1] == true) //top-right
				{
					neighborCount++;
				}
				if (columns < cells.length-1 && cells[rows][columns+1] == true) //right
				{
					neighborCount++;
				}
				if (rows < cells.length-1 && columns < cells.length-1 && cells[rows+1][columns+1] == true) //bottom-right
				{
					neighborCount++;
				}
				
				if (cells[rows][columns] == true) //if the cell is alive then...
				{
					if (neighborCount == 2 || neighborCount == 3) //does the cell have either 2 or 3 neighbors if so then...
					{
						nextCells[rows][columns] = true; //this cell survives to the next generation
					} else //the cell does not have 2 or 3 neighbors
					{
						nextCells[rows][columns] = false; //this cell does not survive to the next generation
					}
				} else //if the cell is dead then...
				{
					if (neighborCount == 3) //does the cell have 3 neighbors if so...
					{
						nextCells[rows][columns] = true; //the cell is alive the next generation
					} else //if it does not have 3 neighbors then...
					{
						nextCells[rows][columns] = false; //the cell is not alive the next generation
					}
				}
			}

		}
		//making the future generation the present generation
		for (int row = 0; row < cells.length; row++)
		{
			for (int column = 0; column < cells[0].length; column++)
			{
				cells[row][column] = nextCells[row][column];
			}
		}
		frame.repaint(); //repainting the frame for visual
	}

	@Override
	public void mouseClicked(MouseEvent event)
	{
	}

	@Override
	public void mousePressed(MouseEvent event) //did the mouse get pressed if so then...
	{
		//define the width and height of each cell
		double width = (double)panel.getWidth() / cells[0].length; 
		double height = (double)panel.getHeight() / cells.length;
		
		//define which cell was clicked on
		int row = Math.min(cells.length - 1,(int)(event.getY() / height));
		int column = Math.min(cells[0].length - 1,(int)(event.getX() / width));
		
		//toggle that cell
		cells[row][column] = !cells[row][column];
		
		//update the frame for visual
		frame.repaint();
	}
	
	@Override
	public void mouseReleased(MouseEvent event) 
	{
	}

	@Override
	public void mouseEntered(MouseEvent event)
	{
	}

	@Override
	public void mouseExited(MouseEvent event)
	{
	}
}
