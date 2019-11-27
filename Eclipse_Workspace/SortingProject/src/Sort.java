import java.awt.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Sort
{

	Scanner console = new Scanner(System.in);
	Scanner fileInput;
	
	String input;
	
	int[] inputArray;
	int[] outputArray;
	
	public Sort() 
	{
		//prompt user
		System.out.println("Enter a number for the input file.");
		System.out.println("1: input1.txt 2: input2.txt 3: input3.txt 4: input4.txt ");
		
		//get input
		input = console.nextLine();
		
		//check for errors in user input
		if (input.length() != 1 && input.charAt(0) != '1' && input.charAt(0) != '2' && input.charAt(0) != '3' && input.charAt(0) != '4') 
		{
			System.out.println("Enter 1, 2, 3, 4.");
			
			while (input.length() != 1 && input.charAt(0) != '1' && input.charAt(0) != '2' && input.charAt(0) != '3' && input.charAt(0) != '4') 
			{
				input = console.nextLine();
			}
		}
		//check if the .txt file exists if there is then supress the error
		try
		{
			fileInput = new Scanner(new File("input" + input.charAt(0) + ".txt"));
		} catch (FileNotFoundException ex)
		{
			ex.printStackTrace(); //no error message
			System.exit(0); //close program
		}
		
		//get the file
		String infile = fileInput.nextLine();
		String[] inputStringArray = infile.split(","); //remove the commas and only get the numbers
		
		//set inputArray's length to inputStringArray's length
		inputArray = new int[inputStringArray.length];
		
		//set inputArray to inputStringArray
		for (int i = 0; i < inputStringArray.length; i++)
		{
			inputArray[i] = Integer.parseInt(inputStringArray[i]);
		}
		
		//prompt user for sorting method
		System.out.println("How do you want to sort these numbers?");
		System.out.println("1: Bubble Sort 2: Selection Sort 3: Table (counting) Sort 4: Quick Sort");
		
		//get the user input
		input = console.nextLine();
		
		//check for user input errors
		if (input.length() != 1 && input.charAt(0) != '1' && input.charAt(0) != '2' && input.charAt(0) != '3' && input.charAt(0) != '4') 
		{
			System.out.println("Enter 1, 2, 3, 4.");
			
			while (input.length() != 1 && input.charAt(0) != '1' && input.charAt(0) != '2' && input.charAt(0) != '3' && input.charAt(0) != '4') 
			{
				input = console.nextLine();
			}
		}
		
		//start timer 
		long startTime = System.currentTimeMillis();
		
		//check for inputs
		if (input.equals("1")) 
		{
			inputArray = BubbleSort(inputArray);
		} else if (input.equals("2")) 
		{
			inputArray = SelectionSort(inputArray);
		} else if (input.equals("3")) 
		{
			inputArray = TableSort(inputArray);
		} else if (input.equals("4")) 
		{
			inputArray = QuickSort(inputArray, 0, inputArray.length - 1);
		}
		
		//end timer
		long totalTime = System.currentTimeMillis() - startTime;
		
		//initialize the printwriter
		PrintWriter pw;
		
		try //if there is an error to writing to a file then suppress it if not then continue
		{
			pw = new PrintWriter(new FileWriter(new File("SortOutput.txt"))); //make the file
			
			String output = ""; //reset output
			
			for (int i = 0; i < inputArray.length; i++)
			{
				output += inputArray[i] + ",";
			}
			
			output += "\nTotal Time: " + totalTime + "ms";
			pw.write(output);
			pw.close();
		} catch (IOException ex)
		{
			ex.printStackTrace();
			System.exit(0);
		}
		
		
		for (int i = 0; i < inputArray.length; i++)
		{
			//System.out.println(inputArray[i]);
		}
		
		System.out.println("Total Time: " + totalTime + "ms");
	}
	
	//inspired by programcreek.com 
	//I understand the logic
	private int[] QuickSort(int[] array, int low, int high) //input the array, lowest index, and the array length
	{
		int partition = partition(array, low, high); //gets new low number that "separates" the array
		
		if (partition-1 > low) // if the new low (partition) minus one is less than low then re-call the function with the new values and the partition number
	    {
			QuickSort(array, low, partition - 1); //recursion
	    }
		if (partition + 1  < high) //if the partition plus one is greater than the array length / high then re-call with partition+1 and the new values
		{
			QuickSort(array, partition + 1, high); //recursion
		}
		return array; //return the sorted array
	}

	public static int partition(int[] array, int low, int high)
	{
		int pivot = array[high]; //set pivot of the quicksort to the arrays last number and keep track of it with updating the high variable
		
		for(int i = low; i < high; i++) //set starting value to low and loop until i is greater than high value
		{
            if(array[i] < pivot) //if the array index i is less than the pivot number (last number)
            {
            	//switching the values of index low and index i 
                int temp = array[low]; //set temp number to the array index low
                array[low] = array[i]; //set index low to index i
                array[i] = temp; //set index i to previous index low
                low++; //increment low to go through array and to stop infinite loop
            }
        }
		
		//right after this loop low is just under high so the index of low is the second to last number at this time, and index high is the last number
		//switch the second to last number and the last number
		int temp = array[low];
        array[low] = pivot; 
        array[high] = temp;
        
		return low; //return new low number
	}

	private int[] TableSort(int[] array) //table sort
	{
		int[] tally = new int[1001]; //keep track of how many times a number repeats within the array
		for (int i = 0; i < array.length; i++)
		{
			tally[array[i]]++; //keeps track of the different numbers 
		}
		
		int count = 0; //reset count
		
		for (int i = 0; i < tally.length; i++) //go through tally array length
		{
			System.out.println("i: "+ i);
			for (int j = 0; j < tally[i]; j++) //go until j is greater than all of tally's numbers
			{
				array[count] = i; //switching numbers
				count++; //increment count
				
			}
		}
		return array;
	}

	private int[] SelectionSort(int[] array) //selection sort
	{
		for (int i = 0; i < array.length; i++) //go through array
		{
			int minNum = array[i];
			int minIndex = i;
			for (int j = i; j < array.length; j++) //go through array
			{
				if (array[j] < minNum) //if the number is lower than the "pivot" then:
				{
					minNum = array[j]; //set new lowest number
					minIndex = j; //set new index
				}
			}
			//switch numbers
			int temp = array[minIndex];
			array[minIndex] = array[i];
			array[i] = temp;
		}
		return array; //give sorted array
	}

	int[] BubbleSort(int[] array) //bubble sort
	{	
		for (int i = 0; i < array.length; i++) //go through array
		{
			for (int j = 0; j < array.length - 1; j++) //go through array
			{
				if (array[j] > array[j+1]) //if the current number is lower than the next number then:
				{
					//switch the numbers
					int temp = array[j];
					array[j] = array[j+1];
					array[j+1] = temp;
				}
			}
		}
		
		return array; //return sorted array
	}

	public static void main(String[] args)
	{
		new Sort();
	}
}
