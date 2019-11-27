import java.util.ArrayList;

/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 1.0 (February 2002)
 */

/*
 * Modified by: Sunil Jain
 * Date: 9/22/19
 * */

class Game 
{
    private Parser parser;
    private Room currentRoom;
    
    public Game() 
    {
        createRooms();
        parser = new Parser();
    }
    
    public static void main(String[] args) 
    {
    	Game mygame = new Game();
    	mygame.play();
    }

    /**
     * Create the game and initialize its internal map.
     */
    
    Room outside, three12, one20, D8, F7, E4, H3, A22, H17, cafe, pool, track, gym, parkingLot, bus, field, portables, road;
    ArrayList<Item> inventory = new ArrayList<Item>();
    
    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
      
        // create the rooms
        outside = new Room("outside the main entrance of Sunset High School");
        three12 = new Room("in the classroom 3-12");
        one20 = new Room("in the classroom 1-20");
        D8 = new Room("in the classroom D-8");
        F7 = new Room("in the classroom F-7");
        E4 = new Room("in the classroom E-4");
        H3 = new Room("in the classroom H-3");
        A22 = new Room("in the classroom A-22");
        H17 = new Room("in the classroom H-17");
        cafe = new Room("in the Cafeteria");
        pool = new Room("in the Pool Area");
        track = new Room("in the Track Area");
        gym = new Room("in the Gym Area");
        parkingLot = new Room("in the Parking Lot");
        bus = new Room("in the Bus Area");
        field = new Room("in the Sports Field");
        portables = new Room("in the portable commons");
        road = new Room("on the Cornell Road");
        
        // initialize room exits
        
        //outside
        outside.setExit("north", road);
        outside.setExit("west", parkingLot);
        outside.setExit("east", cafe);
        outside.setExit("south", three12);
        
        //3-12
        three12.setExit("north", outside);
        three12.setExit("east", gym);
        three12.setExit("south", track);
        
        //1-20
        one20.setExit("west", cafe);
        one20.setExit("east", E4);
        one20.setExit("south", F7);
        
        //D8
        D8.setExit("east", A22);
        D8.setExit("south", cafe);
        
        //F7
        F7.setExit("north", one20);
        F7.setExit("south", H17);
        F7.setExit("east", bus);
        F7.setExit("west", gym);
        
        //E4
        E4.setExit("west", one20);
        
        //H3
        H3.setExit("west", H17);
        
        //A22
        A22.setExit("west", D8);
        
        //H17
        H17.setExit("east", H3);
        H17.setExit("north", F7);
        
        //Cafe
        cafe.setExit("west", outside);
        cafe.setExit("north", D8);
        cafe.setExit("east", one20);
        cafe.setExit("south", gym);
        
        //Pool
        pool.setExit("south", road);
        
        //Track
        track.setExit("north", three12);
        track.setExit("east", portables);
        
        //Gym
        gym.setExit("south", portables);
        gym.setExit("west", three12);
        gym.setExit("east", F7);
        gym.setExit("north", cafe);
        
        //Parking Lot
        parkingLot.setExit("east", outside);
        parkingLot.setExit("south", field);
        
        //Bus
        bus.setExit("west", F7);
        
        //Field
        field.setExit("north", parkingLot);
        
        //Portables
        portables.setExit("west", track);
        portables.setExit("north", gym);
        
        //road
        road.setExit("north", pool);
        road.setExit("south", outside);

        // start game outside
        currentRoom = outside;
        
        //Add 5 items
        pool.setItem(new Item("Goggles"));
        one20.setItem(new Item("Keyboard"));
        track.setItem(new Item("Timer"));
        F7.setItem(new Item("Homework"));
        cafe.setItem(new Item("Lunch"));
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
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
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * If this command ends the game, true is returned, otherwise false is
     * returned.
     */
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
