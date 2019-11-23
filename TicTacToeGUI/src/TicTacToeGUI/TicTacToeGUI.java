package TicTacToeGUI;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class TicTacToeGUI implements ActionListener
{
	
	JFrame frame = new JFrame();
	
	JButton[][] button = new JButton[3][3];
	int[][] board = new int[3][3];
	
	final int BLANK = 0;
	final int X_MOVE = 1;
	final int O_MOVE = 2;
	final int X_TURN = 0;
	final int O_TURN = 1;
	int turn = X_TURN;

	String winner = "";
	String xName = "X";
	String oName = "O";
	
	int xScore = 0;
	int oScore = 0;
	
	boolean stillplaying = true;
	boolean computer = false;
	public boolean isXBlank()
	{
		return false;
	}
	
	Container center = new Container();
	
	JLabel WinnerLabel = new JLabel("Last Winner: " + winner);
	JLabel TurnLabel = new JLabel("Turn: "+ xName);
	JLabel xNameLabel = new JLabel(xName + "'s Wins: " + xScore);
	JLabel oNameLabel = new JLabel(oName + "'s Wins: " + oScore);
	
	JButton xChangeName = new JButton("Change X's Name");
	JButton oChangeName = new JButton("Change O's Name");
	
	JTextField xChangeField = new JTextField();
	JTextField oChangeField = new JTextField();
	
	Container north = new Container();
	Container south = new Container();
	
	JCheckBox compPlayer = new JCheckBox("1 Player");

	public TicTacToeGUI() 
	{
		frame.setSize(400,400);
		//center grid container
		frame.setLayout(new BorderLayout());
		center.setLayout(new GridLayout(3,3));
		for (int i = 0; i < button.length; i++)
		{
			for (int j = 0; j < button[0].length; j++)
			{
				button[j][i] = new JButton(j+","+i);
				center.add(button[j][i]);
				button[j][i].addActionListener(this);
			}
		}
		frame.add(center, BorderLayout.CENTER);
		//north container
		north.setLayout(new GridLayout(3,2));
		north.add(xNameLabel);
		north.add(oNameLabel);
		north.add(xChangeField);
		north.add(oChangeField);
		north.add(xChangeName);
		xChangeName.addActionListener(this);
		north.add(oChangeName);
		oChangeName.addActionListener(this);
		frame.add(north, BorderLayout.NORTH);
		
		//south container
		south.setLayout(new GridLayout(1,5));
		south.add(TurnLabel);
		south.add(WinnerLabel);
		south.add(compPlayer);
		compPlayer.addActionListener(this);
		frame.add(south, BorderLayout.SOUTH);
		
		//exiting
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public static void main(String[] args)
	{
		new TicTacToeGUI();
	}

	public void computerTurn() 
	{
		JButton current;
		
		//if (board[i][j] == true)
		
	}
	
	@Override
	public void actionPerformed(ActionEvent event)
	{
		if (event.getSource().equals(compPlayer)) 
		{
			computer = !computer;
			if (computer == true) 
			{
				oChangeField.setEnabled(false);
				oChangeName.setEnabled(false);
				oName = "Computer";
			} else 
			{
				oChangeField.setEnabled(true);
				oChangeName.setEnabled(true);
				oName = "O";
			}
			resetBoard();
		}
		
		JButton current;
		boolean gridButton = false;
		for (int i = 0; i < button.length; i++)
		{
			for (int j = 0; j < button[0].length; j++)
			{
				if (event.getSource().equals(button[j][i])) 
				{
					gridButton = false;
					current = button[j][i];
					if (board[j][i] == BLANK) 
					{
						if (turn == X_TURN) 
						{
							current.setText("X");
							current.setEnabled(false);
							board[j][i] = X_MOVE;
							turn = O_MOVE;
							TurnLabel.setText("Turn: " + oName);
							if (computer == true) 
							{
								computerTurn();
							}
						} else 
						{
							current.setText("O");
							current.setEnabled(false);
							board[j][i] = O_MOVE;
							turn = X_TURN;
							TurnLabel.setText("Turn: " + xName);
						}
						if (checkWin(X_MOVE) == true) 
						{
							xScore++;
							winner = xName;
							WinnerLabel.setText("Last Winner: " + winner);
							stillplaying = false;
							resetBoard();
						} else if (checkWin(O_MOVE) == true) 
						{
							oScore++;
							winner = oName;
							WinnerLabel.setText("Last Winner: " + winner);
							stillplaying = false;
							resetBoard();
						} else if (checkTie() == true) 
						{
							winner = "none";
							WinnerLabel.setText("Last Winner: " + winner);
							stillplaying = false;
							resetBoard();
						}
					}
				}
			}
		}
		if (gridButton == false) 
		{
			if (event.getSource().equals(xChangeName) == true) 
			{
				xName = xChangeField.getText();
				oName = oChangeField.getText();
				if (xName.isEmpty() == false && xName.contains("  ") == false) 
				{
					xNameLabel.setText(xName + "'s Wins: " + xScore);
				}
			} else if (event.getSource().equals(oChangeName) == true) 
			{
				xName = xChangeField.getText();
				oName = oChangeField.getText();
				if (oName.isEmpty() == false && xName.contains("  ") == false) 
				{
					oNameLabel.setText(oName + "'s Wins: " + oScore);
				}
			}
		}
	}
	
	public void resetBoard() 
	{
		turn = X_TURN; //reset turn
		for (int row = 0; row < board.length; row ++) //reset board
		{
			for (int column = 0; column < board.length; column++)
			{
				board[row][column] = BLANK;
				button[column][row].setText(column+","+row);
				button[column][row].setEnabled(true);
			}
		}
		TurnLabel.setText("Turn: " + xName);
		xNameLabel.setText(xName + "'s Wins: " + xScore);
		oNameLabel.setText(oName + "'s Wins: " + oScore);
	}
	
	public boolean checkWin(int player) //did the player win
	{
		if (board[0][0] == player && board[0][1] == player && board[0][2] == player) //rows
		{
			return true;
		} else if (board[1][0] == player && board[1][1] == player && board[1][2] == player) 
		{
			return true;
		} else if (board[2][0] == player && board[2][1] == player && board[2][2] == player) 
		{
			return true;
		} else if (board[0][0] == player && board[1][0] == player && board[2][0] == player) //columns
		{
			return true;
		} else if (board[0][1] == player && board[1][1] == player && board[2][1] == player) 
		{
			return true;
		} else if (board[0][2] == player && board[1][2] == player && board[2][2] == player) 
		{
			return true;
		} else if (board[0][0] == player && board[1][1] == player && board[2][2] == player) //diagonals
		{
			return true;
		} else if (board[0][2] == player && board[1][1] == player && board[2][0] == player) 
		{
			return true;
		}
		
		return false; //no one won
	}
	
	public boolean checkTie() //did the players tie?
	{
		for (int row = 0; row < board.length; row++) //check every space for a blank if there is no blanks then the game is tied
		{
			for (int column = 0; column < board.length; column++)
			{
				if (board[row][column] == BLANK) {
					return false;
				}
			}
		}
		return true;
	}
	
	
	
	
	
	
	
	
	
	
	
}