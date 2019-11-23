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
		System.out.println("Enter a number for the input file.");
		System.out.println("1: input1.txt 2: input2.txt 3: input3.txt 4: input4.txt ");
		
		input = console.nextLine();
		
		if (input.length() != 1 && input.charAt(0) != '1' && input.charAt(0) != '2' && input.charAt(0) != '3' && input.charAt(0) != '4') 
		{
			System.out.println("Enter 1, 2, 3, 4.");
			
			while (input.length() != 1 && input.charAt(0) != '1' && input.charAt(0) != '2' && input.charAt(0) != '3' && input.charAt(0) != '4') 
			{
				input = console.nextLine();
			}
		}
		try
		{
			fileInput = new Scanner(new File("input" + input.charAt(0) + ".txt"));
		} catch (FileNotFoundException ex)
		{
			ex.printStackTrace();
			System.exit(0);
		}
		
		String infile = fileInput.nextLine();
		String[] inputStringArray = infile.split(",");
		
		inputArray = new int[inputStringArray.length];
		
		for (int i = 0; i < inputStringArray.length; i++)
		{
			inputArray[i] = Integer.parseInt(inputStringArray[i]);
		}
		
		System.out.println("How do you want to sort these numbers?");
		System.out.println("1: Bubble Sort 2: Selection Sort 3: Table (counting) Sort 4: Quick Sort");
		
		input = console.nextLine();
		
		if (input.length() != 1 && input.charAt(0) != '1' && input.charAt(0) != '2' && input.charAt(0) != '3' && input.charAt(0) != '4') 
		{
			System.out.println("Enter 1, 2, 3, 4.");
			
			while (input.length() != 1 && input.charAt(0) != '1' && input.charAt(0) != '2' && input.charAt(0) != '3' && input.charAt(0) != '4') 
			{
				input = console.nextLine();
			}
		}
		
		long startTime = System.currentTimeMillis();
		
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
			//QuickSort();
		}
		
		long totalTime = System.currentTimeMillis() - startTime;
		
		PrintWriter pw;
		
		try
		{
			pw = new PrintWriter(new FileWriter(new File("SortOutput.txt")));
			
			String output = "";
			
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
			System.out.println(inputArray[i]);
		}
		
		System.out.println("Total Time: " + totalTime + "ms");
	}
	
	private int[] TableSort(int[] array)
	{
		int[] tally = new int[1001];
		for (int i = 0; i < array.length; i++)
		{
			tally[array[i]]++;
		}
		
		int count = 0;
		
		for (int i = 0; i < tally.length; i++)
		{
			for (int j = 0; j < tally[i]; j++)
			{
				array[count] = i;
				count++;
			}
		}
		
		return array;
	}

	private int[] SelectionSort(int[] array)
	{
		for (int i = 0; i < array.length; i++)
		{
			int minNum = array[i];
			int minIndex = i;
			for (int j = i; j < array.length; j++)
			{
				if (array[j] < minNum) 
				{
					minNum = array[j];
					minIndex = j;
				}
			}
			int temp = array[minIndex];
			array[minIndex] = array[i];
			array[i] = temp;
		}
		return array;
	}

	int[] BubbleSort(int[] array)
	{	
		for (int i = 0; i < array.length; i++)
		{
			for (int j = 0; j < array.length - 1; j++)
			{
				if (array[j] > array[j+1])
				{
					int temp = array[j];
					array[j] = array[j+1];
					array[j+1] = temp;
				}
			}
		}
		
		return array;
	}

	public static void main(String[] args)
	{
		new Sort();
	}
}
