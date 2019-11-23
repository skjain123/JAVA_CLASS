package MyZuul;

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
