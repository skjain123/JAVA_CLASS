package MyZuul;

import java.util.ArrayList;

public class MyGame
{
	Room currentRoom;
	Room AHall, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16;
	Room BHall, B1, B2,B3, B4;
	Room CHall, C6,C7,C8,C9;
	Room DHall, D6, D8, D10;
	Room EHall, E1, E2, E3, E4, E5, E6;
	Room FHall, F1, F2, F3, F4, F5, F6, F7;
	Room GHall, G1, G2, G3, G4, G5, G6, G8, G9, G10;
	Room HHall, H1, H2, H3, H4, H5, H6, H7, H9, H11, H13, H15, H17;
	Room Khall, K1, K2, K3;
	Room MainHall, M1, M2, CounselingOffice, BusinessOffice, AttendanceOffice, MainOffice, StaffOffice, AthleticOffice; //offices
	Room oneHall, one2, one6, one9, one12, one20, one21, one22, one23;
	Room twoHall; //ADD everything around 2 hall
	Room threeHall, three3, three4, three9, three11, three12, three13, three16, three17;
	ArrayList<Item> inventory = new ArrayList<Item>();
	
	private parser parser;
	
	public void createRooms() 
	{
		Room("AHall", "");
		for (int halls = 0; halls < 11; halls++)
		{
			for (int i= 0; i < 22; i++)
			{
				Room("A"+i, "");
			}
			
			Room("BHall", "");
			Room("C1", "");
			Room("C2", "");
			Room("C3", "");
			Room("C4", "");
			
			Room("CHall", "");
			Room("C6", "");
			Room("C7", "");
			Room("C8", "");
			Room("C9", "");
			
			Room("DHall", "");
			Room("C6", "");
			Room("C8", "");
			Room("C10", "");
			
			Room("EHall", "");
			for (int i= 0; i < 6; i++)
			{
				Room("E"+i, "");
			}
			
			Room("FHall", "");
			for (int i= 0; i < 7; i++)
			{
				Room("F"+i, "");
			}
			
			Room("GHall", "");
			for (int i= 0; i < 10; i++)
			{
				Room("G"+i, "");
			}
			
			Room("HHall", "");
			for (int i= 0; i < 17; i++)
			{
				Room("H"+i, "");
			}
			
			Room("KHall", "");
			Room("K1", "");
			Room("K2", "");
			Room("K3", "");
			
			Room("MainHall", "");
			Room("M1", "");
			Room("M2", "");
			
			Room("CounselingOffice", "");
			Room("BusinessOffice", "");
			Room("AttendanceOffice", "");
			Room("MainOffice", "");
			Room("StaffOffice", "");
			Room("AthleticOffice", "");
			
			Room("threeHall", "");
			for (int i= 0; i < 17; i++)
			{
				Room("three"+i, "");
			}
			Room("twoHall", "");
			for (int i= 0; i < 17; i++)
			{
				Room("two"+i, "");
			}
		}
		//currentRoom = oneHall;
		//
	}
	
	ArrayList<String> rooms = new ArrayList<String>();
	ArrayList<String> items = new ArrayList<String>();
	
	private void Room(String newRoom, String item)
	{
		rooms.add(newRoom);
		String roomDesc = "";
		items.add(item);
	}
	
	private String getItem(String item) 
	{
		for (int x = 0; x < rooms.size(); x++)
		{
			if (rooms.get(x).equals(room)) 
			{
				if (items.get(x).equals("item"))
				return items.get(x);
			}
		}
		System.out.println("This Room Does Not Have that Item!");
		return null;
	}

	public MyGame() 
	{
		createRooms();
		parser = new parser();
	}
	
	public static void main(String[] args)
	{
		MyGame mygame = new MyGame();
		mygame.play();
	}
	
	public void play() 
    {            
        printWelcome(); //get welcome message

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
            
            if (inventory.size() == 5) //does the player have 5 items? if so end the game and display end message
            {
            	finished = true;
            	System.out.println("You Win!");
            	System.out.println("You gathered all of the Items in the School!");
            }
            
        }
        System.out.println("Thank you for playing.");
    }
	
	private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) { //if the player inputed an unknown command then error
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord(); //check if the command is valid
        if (commandWord.equals("help")) { //help command
            printHelp();
        } else if (commandWord.equals("go")) //go command
        {
            goRoom(command);
        } else if (commandWord.equals("quit")) //quit command
        {
            wantToQuit = quit(command);
        } else if (commandWord.equals("inventory")) //inventory command
        {
            printInventory();
        } else if (commandWord.equals("get")) //pickup/get commmand
        {
            getItem(command);
        } else if (commandWord.equals("drop")) //drop command
        {
            dropItem(command);
        }
        return wantToQuit;
    }

	/**
     * Print out the opening message for the player.
     */
    private void printWelcome() //welcome message
    {
        System.out.println();
        System.out.println("Welcome to Adventure!");
        System.out.println("Adventure is a new, incredibly boring adventure game.");
        System.out.println("Your goal is to go around the school and find all of the items");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        //System.out.println(currentRoom.getLongDescription());
    }
    
    private void dropItem(Command command) //drop item
	{
    	if(!command.hasSecondWord()) {
            // if there is no second word, we don't know what to drop...
            System.out.println("Drop what?");
            return;
        }

        String item = command.getSecondWord(); //get the second word of the command

        // Try to leave current room.
        Item newItem = null; //reset the item
        int index = 0; //reset the index
        for (int i = 0; i < inventory.size(); i++) //check if the player has the item to be dropped
		{
			if (inventory.get(i).getDescription().equals(item))
			{
				newItem = inventory.get(i); //get the item
				index = i; //set the index to incrementor
			}
		}

        if (newItem == null) //if the item that the user wants to drop is not in the inventory then displlay error message
            System.out.println("That Item is not in your inventory!");
        else { //if the item that is to be dropped is in the inventory then remove it from the inventory and add it to the room and continue
            inventory.remove(index);
            currentRoom.setItem(new Item(item));
            System.out.println("Dropped: " + item);
            System.out.println();
            System.out.println(currentRoom.getLongDescription());
        }
	}

	private void getItem(Command command) //pickup/get item
	{
    	if(!command.hasSecondWord()) {
            // if there is no second word, we don't know what to pickup/get...
            System.out.println("Get What?");
            return;
        }

        String item = command.getSecondWord(); //get the item that the user wants to pickup/get

        // Try to leave current room.
        Item newItem = currentRoom.getItem(item); //check if the room has the item

        if (newItem == null) //if the room does not have the item then display error message
            System.out.println("That Item is not here!");
        else { //if it does have the item then remove it from room and put in the inventory and continue
            inventory.add(newItem);
            currentRoom.removeItem(item);
            System.out.println("Picked up: " + item);
            System.out.println();
            System.out.println(currentRoom.getLongDescription());
        }
	}

	private void printInventory()
	{
		String output = ""; //reset the output
		if (inventory.size() == 0)  //if the player has no items then display message
		{
			System.out.println("You Have No Items!");
		} else //if the player has item/items then
		{
			for (int i = 0; i < inventory.size(); i++) //go through the users inventory
			{
				output += inventory.get(i).getDescription() + " "; //add the item to the output list
			}
			System.out.println("You are carrying: "); //display beginning message
			System.out.println(output); //display the users inventory
		}
	}

	// implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() //remind the user of the commands and goal
    {
        //System.out.println("You are lost. You are alone. You wander");
        //System.out.println("around at the university.");
    	System.out.println("Wander around the school to find all of the Items.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /** 
     * Try to go to one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null)
            System.out.println("There is no door!");
        else {
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
        }
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game. Return true, if this command
     * quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else
            return true;  // signal that we want to quit
    }
}
