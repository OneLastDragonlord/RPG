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
    private Room throneroom, dungeon, castlecourtyard, thelabyrinth, thebattlefield, avalon;

    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        createPlayer();
        parser = new Parser();
    }

    /**
     * Create all the rooms and link their exits together.
     * Create all items and objects
     */
    private void createRooms()
    {
        
        Command throneroomTableCommand, throneroomLockCommand, avalonHandCommand, avalonBoatCommand, dungeonKeyCommand;
        // create the rooms
        throneroom = new Room("standing in a large room with high ceilings", "Throne Room");
        castlecourtyard = new Room("standing in a courtyard in Camelot", "Castle Courtyard");
        dungeon = new Room("in a very dark and deep place underneath the castle", "Dungeon");
        thelabyrinth = new Room("standing at the beginning of the labyrinth", "The Labyrinth of Gedref");
        thebattlefield = new Room("are on a battlefield", "the battlefield of the mages");
        avalon = new Room("standing near the edge of water", "The Lake of Avalon");

        // initialise room exits
        throneroom.setExits("down" , castlecourtyard);
        throneroomTableCommand = new Command("drop","sword");
        throneroomLockCommand = new Command("drop","key");
        throneroom.addObject("table", "a round table in the middle of the room with crossed swords on it",false,throneroomTableCommand);
        throneroom.addObject("lock", "a lock in the middle of the room on the floor",false,throneroomLockCommand);
        throneroom.addItem("crown", "the crown of kings",0,false);

        castlecourtyard.setExits("up", throneroom);
        castlecourtyard.setExits("east", thebattlefield);
        castlecourtyard.setExits("down", dungeon);

        dungeon.setExits("up", castlecourtyard);
        dungeonKeyCommand = new Command("grab", "key");
        dungeon.addObject("corpse","a corpse with something shiny in its chest, but you need something sharp to get it out", false, dungeonKeyCommand);
        dungeon.addItem("key","a key with markings that look like a crown", 10 ,false);

        thelabyrinth.setExits("south", thebattlefield);
        thelabyrinth.addItem("cup" , "a cup depicting a battle between life and death",25 ,false);

        thebattlefield.setExits("north", thelabyrinth);
        thebattlefield.setExits("south", avalon);
        thebattlefield.setExits("west", castlecourtyard);

        avalon.setExits("north", thebattlefield);
        avalonHandCommand = new Command("grab", "sword");
        avalonBoatCommand = new Command("quit", "finish");
        avalon.addObject("hand" , "a hand reaching out from the water",true,avalonHandCommand);
        avalon.addObject("boat" , "a boat ready to set sail", false, avalonBoatCommand);
        avalon.addItem("sword","a sword, it has the name Excalibur engraved in it", 10,false);
        currentRoom = castlecourtyard;  // start game outside
        //oldRoom = null;
    }

    /**
     * function to print all objects in the current room
     */
    private void printObjects()
    {
        ArrayList<Object> objectList = currentRoom.returnObjectList();
        if(objectList.size() > 0){
            for (int i = 0; i <objectList.size();i++){
                if(objectList.get(i).getVisible()){
                    System.out.println("There is "+objectList.get(i).getObjectDescription()+ ".\n");
                }  
            }
        }
    }

    /**
     * function to print all items in the current room
     */
    private void printItems()
    {
        ArrayList<Item> itemList = currentRoom.returnItemList();
        if(itemList.size() >0){
            for (int i = 0; i <itemList.size();i++){
                if(itemList.get(i).getVisible()){
                    System.out.println("There is " + itemList.get(i).getItemDescription()+".\n");
                }
            }   
        }
    }

    /**
     * show inventory
     */
    private void showInventory()
    {
        for (int i = 0; i <player1.returnInventory().size();i++){
            System.out.println(player1.returnInventory().get(i).getItemName());
        }  
    }

    /**
     * create player with max weight limit
     */
    private void createPlayer()
    {
        Player player1;
        player1 = new Player("pieter", 20);
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
        else if(commandWord.equals("use")) {
            useObject(command);
        }
        else if(commandWord.equals("drop")) {
            dropItem(command);
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
        System.out.println("You are Arthur Pendragon.");
        System.out.println("You are in Camelot, searching for peace.");
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
        Room nextRoom;
        if(direction.equals("dungeon")){
            nextRoom = dungeon;
        } else {
            // Try to leave current room.
            nextRoom= currentRoom.getExit(direction);
        }
        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            stack.push(currentRoom);
            currentRoom = nextRoom;
            printLocationInfo();
        }

    }

    /**
     * checks for second word, if exists add item to player inventory and remove from room
     */
    private void grabItem(Command command){
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know what to grab...
            System.out.println("Grab what?");
            return;
        }
        String itemToGrab = command.getSecondWord();
        Item itemToRemove = currentRoom.grabItemInRoom(itemToGrab);
        if(itemToRemove != null){
            player1.addItem(itemToRemove);
            currentRoom.removeItem(itemToGrab);
        }else{
            System.out.println("You can't grab this");
        }
        //System.out.println(player1.returnInventory());
    }

    /**
     * certain items have special interactions
     */
    private void whatObjectIsIt(String object){
        switch(object){
            case "hand":
                avalon.setObjectVisible("hand");
                dungeon.setObjectVisible("corpse");
                break;
            case "corpse":
                dungeon.setObjectVisible("corpse");
                throneroom.setObjectVisible("lock");
            case "lock":
                throneroom.setObjectVisible("lock");
                throneroom.setObjectVisible("table");
            case "table":
                throneroom.setItemVisible("crown");
        }
    }

    /**
     * checks for second word, if exists add item to player inventory and remove from room
     */
    private void dropItem(Command command){
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know what to drop...
            System.out.println("Drop what?");
            return;
        }
        String itemToDrop = command.getSecondWord();
        Item itemToRemove = player1.getItem(itemToDrop);
        if(itemToRemove != null){
            currentRoom.addExistingItem(itemToRemove);
            player1.deleteItem(itemToRemove);
        }else{
            System.out.println("You can't drop this, you dont even have this.");
        }
        //System.out.println(player1.returnInventory());
    }

    /**
     * checks for second word, if exists process the command associated to it
     */private void useObject(Command command){
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know what to use...
            System.out.println("use what?");
            return;
        }
        String objectNameToUse = command.getSecondWord();
        
        Object objectToUse = currentRoom.useObjectInRoom(objectNameToUse);
        if(objectToUse != null){
            whatObjectIsIt(objectNameToUse);
            processCommand(objectToUse.getCommand());
        }else{
            System.out.println("You can't use this");
        }

    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            if(command.getSecondWord().equals("finish")){
                System.out.println("You have given Arthur Pendragon peace...");
                return true;
            } else {
                System.out.println("quit what?.." );
                return false;
            }
        }
        else {
            return true;  // signal that we want to quit
        }
    }

    /**
     * prints current location
     */
    private void printLocationInfo(){
        System.out.println(currentRoom.getLongDescription());
        System.out.println();
    }

    /**
     * player option to print everything inside the current area
     */
    private void look(){
        System.out.println(currentRoom.getLongDescription());
        printObjects();
        printItems();
        System.out.println(currentRoom.getExitString());
        // System.out.println(currentRoom.getItemDescription());
    }

    private void sleep(){
        System.out.println("You slept for a year and are really awake now");
    }

    /**
     * player option to go to the previous room
     */
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
