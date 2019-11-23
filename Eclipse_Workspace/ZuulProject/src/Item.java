/*
 * Made by: Sunil Jain
 * Purpose: set the items and manage the Items that go through the system
 * Date: 9/22/19
 * */

public class Item
{
	String description; //description of item
	
	public Item(String newDescription) //pass through item when called
	{
		description = newDescription; //set the description of the item to the passed through item
	}
	
	public String getDescription() 
	{
		return description; //return the updated description/item
	}
}
