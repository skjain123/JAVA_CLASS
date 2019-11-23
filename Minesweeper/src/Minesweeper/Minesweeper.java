package Minesweeper;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Minesweeper implements ActionListener
{
	JFrame frame = new JFrame("Minesweeper");
	JButton reset = new JButton("Reset");
	JButton[][] buttons = new JButton[20][20];
	int[][] counts = new int[20][20];
	Container grid = new Container();
	
	final int MINE = 10;
	
	public Minesweeper() 
	{
		frame.setSize(1000,1000);
		frame.setLayout(new BorderLayout());
		frame.add(reset, BorderLayout.NORTH);
		reset.addActionListener(this);
		
		//button grid
		grid.setLayout(new GridLayout(20,20));
		for (int a = 0; a < buttons.length; a++)
		{
			for (int b = 0; b < buttons[0].length; b++)
			{
				buttons[a][b] = new JButton();
				buttons[a][b].addActionListener(this);
				grid.add(buttons[a][b]);
			}
		}
		frame.add(grid, BorderLayout.CENTER);
		
		createMines();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public void createMines() 
	{
		int mineCount = 1;
		
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int x = 0; x < counts.length; x++)
		{
			for (int y = 0; y < counts[0].length; y++)
			{
				list.add(x*100+y);
			}
		}
		counts = new int[20][20];
		
		for (int a = 0; a < mineCount; a++)
		{
			int choice = (int)(Math.random() * list.size());
			counts[list.get(choice)/100][list.get(choice) % 100] = MINE;
			list.remove(choice);
		}
		
		for (int x = 0; x < counts.length; x++)
		{
			for (int y = 0; y < counts[0].length; y++)
			{
				if (counts[x][y] != MINE) 
				{
					int neighborCount = 0;
					if (x > 0 && y > 0 && counts[x-1][y-1] == MINE) 
					{
						neighborCount++;
					}
					if (y > 0 && counts[x][y-1] == MINE) 
					{
						neighborCount++;
					}
					if (x < counts.length - 1 && y > 0 && counts[x+1][y-1] == MINE) 
					{
						neighborCount++;
					}
					if (x > 0 && counts[x-1][y] == MINE) 
					{
						neighborCount++;
					}
					if (x < counts.length - 1 && counts[x+1][y] == MINE) 
					{
						neighborCount++;
					}
					if (x > 0 && y < counts[0].length - 1 && counts[x-1][y+1] == MINE) 
					{
						neighborCount++;
					}
					if (y < counts[0].length - 1 && counts[x][y+1] == MINE) 
					{
						neighborCount++;
					}
					if (x < counts.length - 1 && y < counts[0].length - 1 && counts[x+1][y+1] == MINE) 
					{
						neighborCount++;
					}
					
					counts[x][y] = neighborCount;
				}
			}
		}
	}
	
	public static void main(String[] args)
	{
		new Minesweeper();
	}
	
	public void lostGame() 
	{
		for (int x = 0; x < buttons.length; x++)
		{
			for (int y = 0; y < buttons[0].length; y++)
			{
				if (buttons[x][y].isEnabled()) 
				{
					if (counts[x][y] != MINE) 
					{
						buttons[x][y].setText(counts[x][y] + "");
						buttons[x][y].setEnabled(false);
					} else 
					{
						buttons[x][y].setText("X");
						buttons[x][y].setEnabled(false);
					}
				}
			}
		}
	}

	public void clearZeros(ArrayList<Integer> toClear) 
	{
		if (toClear.size() == 0) 
		{
			return;
		} else 
		{
			int x = toClear.get(0) / 100;
			int y = toClear.get(0) % 100;
			toClear.remove(0);
			if (x > 0 && y > 0)  //up-left
			{
				buttons[x-1][y-1].setText(counts[x-1][y-1] + "");
				buttons[x-1][y-1].setEnabled(false);
				if (counts[x-1][y-1] == 0) 
				{
					toClear.add((x-1) * 100 + (y-1));
				}
			}
			if (y > 0) //up
			{
				buttons[x][y-1].setText(counts[x][y-1] + "");
				buttons[x][y-1].setEnabled(false);
				if (counts[x][y-1] == 0) 
				{
					toClear.add((x) * 100 + (y-1));
				}
			}
			if (x < counts.length - 1 && y > 0) //up right
			{
				buttons[x+1][y-1].setText(counts[x+1][y-1] + "");
				buttons[x+1][y-1].setEnabled(false);
				if (counts[x+1][y-1] == 0) 
				{
					toClear.add((x+1) * 100 + (y-1));
				}
			}
			if (x > 0) //left
			{
				buttons[x-1][y].setText(counts[x-1][y] + "");
				buttons[x-1][y].setEnabled(false);
				if (counts[x-1][y] == 0) 
				{
					toClear.add((x-1) * 100 + (y));
				}
			}
			if (x < counts.length - 1) //right
			{
				buttons[x+1][y].setText(counts[x+1][y] + "");
				buttons[x+1][y].setEnabled(false);
				if (counts[x+1][y] == 0) 
				{
					toClear.add((x+1) * 100 + (y));
				}
			}
			if (x > 0 && y < counts[0].length - 1) //down left
			{
				buttons[x-1][y+1].setText(counts[x-1][y+1] + "");
				buttons[x-1][y+1].setEnabled(false);
				if (counts[x-1][y+1] == 0) 
				{
					toClear.add((x-1) * 100 + (y+1));
				}
			}
			if (y < counts[0].length - 1) //down
			{
				buttons[x][y+1].setText(counts[x][y+1] + "");
				buttons[x][y+1].setEnabled(false);
				if (counts[x][y+1] == 0) 
				{
					toClear.add((x) * 100 + (y+1));
				}
			}
			if (x < counts.length - 1 && y < counts[0].length - 1) //down right
			{
				buttons[x+1][y+1].setText(counts[x+1][y+1] + "");
				buttons[x+1][y+1].setEnabled(false);
				if (counts[x+1][y+1] == 0) 
				{
					toClear.add((x+1) * 100 + (y+1));
				}
			} 
			clearZeros(toClear);
		}
	}
	
	private void checkWin()
	{
		boolean won = false;
		for (int x = 0; x < buttons.length; x++)
		{
			for (int y = 0; y < buttons.length; y++)
			{ 
				if (counts[x][y] != MINE && buttons[x][y].isEnabled() == true) 
				{
					won = false;
				}
			}
		}
		if (won == false) 
		{
			JOptionPane.showMessageDialog(frame, "You Won!");
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent event)
	{
		if (event.getSource().equals(reset)) 
		{
			for (int x = 0; x < buttons.length; x++)
			{
				for (int y = 0; y < buttons[0].length; y++)
				{
					buttons[x][y].setEnabled(true);
					buttons[x][y].setText("");
					buttons[x][y].setEnabled(true);
					counts[x][y] = 0;
					createMines();
				}
			}
		} else 
		{
			for (int x = 0; x < buttons.length; x++)
			{
				for (int y = 0; y < buttons[0].length; y++)
				{
					if (event.getSource().equals(buttons[x][y])) 
					{
						if (counts[x][y] == MINE) 
						{
							buttons[x][y].setForeground(Color.red);
							buttons[x][y].setText("X");
							lostGame();
						} else if (counts[x][y] == 0)
						{
							buttons[x][y].setText(counts[x][y] + "");
							buttons[x][y].setEnabled(false);
							ArrayList<Integer> toClear = new ArrayList<Integer>();
							toClear.add(x*100 + y);
							clearZeros(toClear);
							checkWin();
						}
						else 
						{
							buttons[x][y].setText(counts[x][y] + "");
							buttons[x][y].setEnabled(false);
							checkWin();
						}
					}
				}
			}
		}
		
	}
}
