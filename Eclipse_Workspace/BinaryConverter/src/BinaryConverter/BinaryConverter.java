////inspired by Jason Galbraith's videos on his Youtube channel
//Modified by Sunil Jain
//This is program that lets you convert from binary to a decimal and vice versa it can accept manual inputs or file uploads
//Date: 9/9/19

package BinaryConverter;

import java.io.File; //reading files
import java.io.IOException; //Exceptions
import java.util.Scanner; //scanners

public class BinaryConverter {

	public BinaryConverter() 
	{
		System.out.println("Please enter 'File' to enter a file or 'input' to use the console."); //instructions
		Scanner scanner = new Scanner(System.in); //instantiating Scanner scanner
		String input = scanner.nextLine(); //giving user choice for previous instructions
		String numberInput = ""; //creating numberInput
		if (input.equals("file") || input.equals("File")) //did the user choose file?
		{
			try 
			{
				System.out.println("Enter your file name."); //user instructions
				input = scanner.nextLine();
				Scanner fileScanner = new Scanner(new File(input)); //creating Scanner fileScanner to check the user's file
				numberInput = fileScanner.nextLine(); //give computer the file name
			} catch (IOException ex)  //if the computer cannot find the File then say file not found
			{
				System.out.println("File not Found.");
				scanner.close();
				System.exit(1);
			}
		} else //did the user choose input method?
		{
			System.out.println("Please Enter your number:"); //user instructions
			numberInput = scanner.nextLine(); //user gives the computer the decimal or binary
		}
		System.out.println("If you are translating from Decimal to Binary, type 'dtb'."); //which way does the user want to convert?
		System.out.println("If you are translating from Binary to Decimal, type 'btd'."); //which way does the user want to convert?
		input = scanner.nextLine();
		if (input.equals("dtb")) //decimal to binary
		{
			String answer = ""; //creating the answer variable
			int number = Integer.parseInt(numberInput); //transform the numberInput to an integer and store it in number
			while (number > 0)  //while the number is greater than 0
			{
				int remainder = number % 2; //get the remainder of the number / 2
				answer = remainder + answer; //put the remainder at the front of the answer
				
				number = number / 2; //divide number by 2 to continue the conversion
			}
			System.out.println("The Binary is: " + answer); //give user the conversion
		} else //binary to decimal
		{
			int answer = 0; //create answer variable
			int b = numberInput.length() - 1; //create b variable
			for (int a = 0; a < numberInput.length(); a++)  //while a is less than the length of numberInput loop
			{
				int pwr = (char) Math.pow(2, b); //set pwr to 2^b
				int character = ((int)(numberInput.charAt(a)) - 48); //set character to the charAt(a) of number input
				int digit = (int)(character * pwr); //multiply character and pwr together and store in digit
				answer+=digit; //add digit to the end of the answer for conversion
				b--; //minus b every time the loop loops through
				System.out.println(character +"  "+pwr);
			}
			System.out.println("The Decimal is: " + answer); //give the answer to user
		}
		scanner.close(); //close the scanner
	}
	
	public static void main(String[] args) 
	{
		new BinaryConverter(); //run the code
	}
}
