
//inspired by Jason Galbraith's videos on his youtube channel
//Modified by Sunil Jain
//This is a Guessing Game where the player guesses a number between 0 and 50 with hints from the computer
//Date: 9/7/19

import java.util.Scanner; //import scanners for user input

//System.out.println(); print something

public class GuessingGame
{
	public GuessingGame()
	{
		boolean playing = true;
		while (playing == true) 
		{
			System.out.println("Guess a number between 0 and 50"); //prints instructions
			int randomNumber = (int)(Math.random() * 51); //gets a random number between 0 and 50
			@SuppressWarnings("resource")
			Scanner userInput = new Scanner(System.in); //starts the user inputs
			int guess = -1; //creates guess variable 
			int guesses = 0; //creates the guess counter
			while (guess != randomNumber)  //loops the game when player wants to play the game again
			{
				guess = Integer.parseInt(userInput.nextLine()); //converts the user input to an integer into guess
				if (guess < randomNumber)  //checks if the guess is less than the computer number
				{
					System.out.println("Guess Higher!"); //tells you to guess higher
					guesses++; //adds the amount of guesses you have taken
				} else if (guess > randomNumber) //checks if the guess is more than the computer number
				{
					System.out.println("Guess Lower!"); //tells you to guess lower 
					guesses++; //adds the amount of guesses you have taken
				} else //if the player guesses the number then:
				{
					System.out.println("You Guessed Correctly!"); //congratulate player
					System.out.println("You took " + guesses + " Guesses!"); //display how many guesses it took the player to guess correctly
					System.out.println("Play Again? (y/n)"); //display play again option
					String yesno = userInput.nextLine(); //user can input yes or no
					if (yesno.equals("yes") || yesno.equals("y"))  //if yes then restart game
					{
						System.out.println("---Restarting---");
						playing = true;
					} else //if no then end the program and exit loop
					{
						System.out.println("---End---");
						playing = false;
					}
				}
			}
		}
	}
	
	public static void main(String[] args)
	{
		new GuessingGame(); //runs the game
	}
}