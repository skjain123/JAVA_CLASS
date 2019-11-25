/*
 * This program is an implementation of TicTacToe using GUI.
 * 
 * Inpsired by Jason Galbraith's youtube video tutorials on his youtube channel
 * 
 * Modified/Created By: Sunil Jain
 * 
 * Date: 9/19/19
 * 
 * */

package TicTacToeGUI;

//awt imports
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//swing imports
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class TicTacToeGUI implements ActionListener
{
	//booleans
	boolean stillplaying = false;
	
	//frames
	JFrame frame = new JFrame();
	
	//buttons and board initialization
	JButton[][] button = new JButton[3][3];
	int[][] board = new int[3][3];
	
	//X and O moves and turns and blank values
	final int BLANK = 0;
	final int X_MOVE = 1;
	final int O_MOVE = 2;
	final int X_TURN = 0;
	final int O_TURN = 1;
	int turn = X_TURN;

	//names of players and winners
	String winner = "";
	String xName = "X";
	String oName = "O";
	
	//scores
	int xScore = 0;
	int oScore = 0;
	
	//containers in frame
	Container center = new Container();
	
	//labels
	JLabel WinnerLabel = new JLabel("Last Winner: " + winner);
	JLabel TurnLabel = new JLabel("Turn: "+ xName);
	JLabel xNameLabel = new JLabel(xName + "'s Wins: " + xScore);
	JLabel oNameLabel = new JLabel(oName + "'s Wins: " + oScore);
	
	//more buttons
	JButton xChangeName = new JButton("Change X's Name");
	JButton oChangeName = new JButton("Change O's Name");
	
	//text fields
	JTextField xChangeField = new JTextField();
	JTextField oChangeField = new JTextField();
	
	//containers
	Container north = new Container();
	Container south = new Container();

	public TicTacToeGUI() 
	{
		//set up frame and add buttons
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
		//what type of layout is this
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
		frame.add(south, BorderLayout.SOUTH);
		
		//exiting
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public static void main(String[] args)
	{
		new TicTacToeGUI(); //TicTacToeGUI constructor
	}
	
	@Override
	public void actionPerformed(ActionEvent event)
	{	
		JButton current;
		boolean gridButton = false;
		
		//did one of the board buttons get pressed?
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
						if (turn == X_TURN) //is it x's turn if so place the x marker to the button pressed and change to O's turn
						{
							current.setText(xName);
							current.setEnabled(false);
							board[j][i] = X_MOVE;
							turn = O_MOVE;
							TurnLabel.setText("Turn: " + oName);
						} else //is it o's turn if so place the o marker to the button pressed and change to X's turn
						{
							current.setText(oName);
							current.setEnabled(false);
							board[j][i] = O_MOVE;
							turn = X_TURN;
							TurnLabel.setText("Turn: " + xName);
						}
						if (checkWin(X_MOVE) == true) //did x win?
						{
							xScore++;
							winner = xName;
							WinnerLabel.setText("Last Winner: " + winner);
							stillplaying = false;
							resetBoard();
						} else if (checkWin(O_MOVE) == true) //did o win
						{
							oScore++;
							winner = oName;
							WinnerLabel.setText("Last Winner: " + winner);
							stillplaying = false;
							resetBoard();
						} else if (checkTie() == true) //is it a tie game?
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
		if (gridButton == false) //if the input did not come from the board buttons
		{
			if (event.getSource().equals(xChangeName) == true) //if the input came from xChangeName then change x's name
			{
				xName = xChangeField.getText();
				xName = xName.trim();
				if (xName.isEmpty() == false && !xName.contains(" ") && xName.length() >= 1) 
				{
					xNameLabel.setText(xName + "'s Wins: " + xScore);
					for (int k = 0; k < board.length; k++)
					{
						for (int k2 = 0; k2 < board.length; k2++)
						{
							if (board[k2][k] == X_MOVE) 
							{
								button[k2][k].setText(xName);
							}
						}
					}
				} else 
				{
					xName = "X";
				}
			} else if (event.getSource().equals(oChangeName) == true) //if the input came from oChangeName then change o's name
			{
				oName = oChangeField.getText();
				xName = xName.trim();
				if (oName.isEmpty() == false && !oName.contains(" ") && oName.length() >= 1) 
				{
					oNameLabel.setText(oName + "'s Wins: " + oScore);
					for (int k = 0; k < board.length; k++)
					{
						for (int k2 = 0; k2 < board.length; k2++)
						{
							if (board[k2][k] == O_MOVE) 
							{
								button[k2][k].setText(oName);
							}
						}
					}
				} else 
				{
					oName = "O";
				}
			}
		}
	}
	
	public void resetBoard() //reset board
	{
		turn = X_TURN; //reset turn to x's
		for (int row = 0; row < board.length; row ++) //empty all of the board and enable all of the buttons
		{
			for (int column = 0; column < board.length; column++)
			{
				board[row][column] = BLANK;
				button[column][row].setText(column+","+row);
				button[column][row].setEnabled(true);
			}
		}
	
		//reset labels
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