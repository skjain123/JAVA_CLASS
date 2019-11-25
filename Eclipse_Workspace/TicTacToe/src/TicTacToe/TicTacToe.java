//inspired by Jason Galbraith's videos on his youtube channel
//Modified by Sunil Jain
//This is a version of tic-tac-toe that can be played in the console by inputting coordinates in the console
//Date: 9/10/19

package TicTacToe;

import java.util.Scanner;

public class TicTacToe 
{
	int[][] board = new int[3][3];
	
	final int BLANK = 0;
	final int X_MOVE = 1;
	final int O_MOVE = 2;
	final int X_TURN = 0;
	final int O_TURN = 1;
	
	int turn = X_TURN;
	
	boolean stillPlaying = true;
	
	Scanner scanner = new Scanner(System.in);
	String input = "";
	
	
	public TicTacToe() 
	{
		getMoves();
	}
	
	public void getMoves() 
	{
			while (stillPlaying == true) {
				System.out.println("Turn: X");
			while (checkWin(X_MOVE) == false && checkWin(O_MOVE) == false) {
				printBoard();
				input = scanner.nextLine();
				
				if (input.length() != 2) 
				{
					System.out.println("Enter a letter followed by a letter.");
				} else if (input.charAt(0) != 'a' && input.charAt(0) != 'b' && input.charAt(0) != 'c') 
				{
					System.out.println("Row must be a,b or, c.");
				} else if (input.charAt(1) != '1' && input.charAt(1) != '2' && input.charAt(1) != '3') 
				{
					System.out.println("Column must be 1,2 or, 3.");
				} else 
				{
					int row = input.charAt(0) - 'a';
					int column = input.charAt(1) - '1';
					if (board[row][column] == BLANK) 
					{
						if (turn == X_TURN) 
						{
							board[row][column] = X_MOVE;
							turn = O_TURN;
							System.out.println("Turn: O");
						} else if (turn == O_TURN) 
						{
							board[row][column] = O_MOVE;
							turn = X_TURN;
							System.out.println("Turn: X");
						}
					} else 
					{
						System.out.println("There is a piece there!");
					}
				}
			}
			if (checkWin(X_MOVE) == true) 
			{
				printBoard();
				System.out.println("X Wins!");
				playAgain();
			} else if (checkWin(O_MOVE) == true) 
			{
				printBoard();
				System.out.println("O Wins!");
				playAgain();
			} else if (checkTie() == true) 
			{
				printBoard();
				System.out.println("Tie Game!");
				playAgain();
			}
		}
	}
	
	public void playAgain() 
	{
		System.out.println("Do you want to play again? (y/n)");
		
		input = scanner.nextLine();
		
		if (input.equals("yes") || input.equals("y")) 
		{
			stillPlaying = true;
			System.out.println("------Restarting-----");
			resetBoard();
		} else 
		{
			stillPlaying = false;
			System.out.println("-----End-----");
		}
	}
	
	public void resetBoard() 
	{
		for (int row = 0; row < board.length; row++) //check every space for a blank if there is no blanks then the game is tied
		{
			for (int column = 0; column < board.length; column++)
			{
				board[row][column] = BLANK;
			}
		}
		turn = X_TURN;
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
	
	
	public void printBoard() 
	{
		System.out.println(" \t1\t2\t3");
		for (int row = 0; row < board.length; row++)
		{
			String output = (char)('a'+row) + "\t";
			for (int column = 0; column < board[0].length; column++)
			{
				if (board[row][column] == BLANK) 
				{
					output += " \t";
				} else if (board[row][column] == X_MOVE) 
				{
					output += "X\t";
				} else if (board[row][column] == O_MOVE) 
				{
					output += "O\t";
				}
			}
			System.out.println(output);
		}
	}
	
	public static void main(String[] args) {
		new TicTacToe();
	}
}