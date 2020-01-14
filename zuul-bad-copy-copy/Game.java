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
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */

import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.Stack;
import java.util.ArrayList;

public class Game 
{
    private Parser parser;
    private Room currentRoom;
    private Room oldRoom;
    private Player player1 = new Player("Henk" ,7) ;
    Stack<Room> stack = new Stack<Room>();

    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        createPlayer();
        printObjects();
        //  createObjects();
        parser = new Parser();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room throneroom, castlecourtyard, dungeon, thelabyrinth, thebattlefield, avalon;
        // create the rooms
        throneroom = new Room("standing in the Throne room", "throneroom");
        castlecourtyard = new Room("standing in the courtyard of the castle", "castlecourtyard");
        dungeon = new Room("standing in the Dungeon", "dungeon");
        thelabyrinth = new Room("standing at the beginning of the labyrinth", "thelabyrinth");
        thebattlefield = new Room("are entering the battlefield", "thebattlefield");
        avalon = new Room("standing in Avalon!", "avalon");

        // initialise room exits
        throneroom.setExits("down" , castlecourtyard);
        throneroom.setExits("down", dungeon);
        throneroom.addObject("The Round Table", "A round table in the middle of the room with crossed swords on it.");
        //throneroom.addItem

        castlecourtyard.setExits("up", throneroom);
        castlecourtyard.setExits("east", thebattlefield);
        castlecourtyard.setExits("down", dungeon);
        castlecourtyard.addItem("Throne Room key","A key with markings that look like a crown", 10 );

        dungeon.setExits("up", castlecourtyard);
        
        thelabyrinth.setExits("south", thebattlefield);
        thelabyrinth.addItem("The Cup of Life" , "A cup depicting a battle between life and death",25 );

        thebattlefield.setExits("north", thelabyrinth);
        thebattlefield.setExits("south", avalon);
        thebattlefield.setExits("west", castlecourtyard);

        avalon.setExits("north", thebattlefield);
        avalon.addObject("Hand" , "A hand reaching out from the water");
        currentRoom = throneroom;  // start game outside
        //oldRoom = null;
    }

    private void printObjects()
    {
        ArrayList<Object> objectList = currentRoom.returnObjectList();
        for (int i = 0; i <objectList.size();i++){
            System.out.println(objectList.get(i).getObjectDescription());
        }   
    }

    private void printItems()
    {
        ArrayList<Item> itemList = currentRoom.returnItemList();
        for (int i = 0; i <itemList.size();i++){
            System.out.println(itemList.get(i).getItemDescription());
        }   
    }

    private void showInventory()
    {
        for (int i = 0; i <player1.returnInventory().size();i++){
            System.out.println(player1.returnInventory().get(i).getItemName());
        }  
    }

    private void createPlayer()
    {
        Player player1, player2;
        player1 = new Player("pieter", 20);
        player2 = new Player("bram" , 40);

    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.

        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        printLocationInfo();
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        }else if (commandWord.equals("look")) {
            look();
        }
        else if (commandWord.equals("go")) {
            goRoom(command);
        }
        else if (commandWord.equals("sleep")) {
            sleep();
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }
        else if(commandWord.equals("back")) {
            goBack();
        }
        else if(commandWord.equals("grab")) {
            grabItem(command);
        }

        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        System.out.println(parser.showCommands());
    }

    /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
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

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            //oldRoom = currentRoom;
            System.out.println(currentRoom.getName());
            stack.push(currentRoom);
            currentRoom = nextRoom;
            printLocationInfo();
        }

    }

    private void grabItem(Command command){
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Grab what?");
            return;
        }
        String itemToGrab = command.getSecondWord();
        Item itemToRemove = currentRoom.grabItemInRoom(itemToGrab);
        if(itemToRemove != null){
            player1.addItem(itemToRemove);
            currentRoom.deleteItem(itemToGrab);
        }else{
            System.out.println("You can't grab this");
        }
        System.out.println(player1.returnInventory());
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }

    private void printLocationInfo(){
        System.out.println(currentRoom.getLongDescription());
        System.out.println();
    }

    private void look(){
        System.out.println(currentRoom.getLongDescription());
        printObjects();
        printItems();
        // System.out.println(currentRoom.getItemDescription());
    }

    private void sleep(){
        System.out.println("You slept for a year and are really awake now");
    }

    private void goBack(){
        //currentRoom = oldRoom;
        if(! stack.empty()){
            currentRoom = stack.peek();
            //System.out.println(stack.peek());
            stack.pop();
            printLocationInfo();
        }else{
            System.out.println("You can't go back any further");
        }
    }

}
