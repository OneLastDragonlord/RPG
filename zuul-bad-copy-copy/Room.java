/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  The exits are labelled north, 
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */
import java.util.HashMap;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;
public class Room 
{
    private String description;
    private HashMap<String, Room> exits;
    private String roomName;
    private ArrayList<Object> objects;
    private ArrayList<Item> items;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description, String roomName) 
    {
        this.description = description;
        this.roomName = roomName;
        exits = new HashMap<>();
        this.objects = new ArrayList<>();
        this.items = new ArrayList<>();
    }

    /**
     * @return Object that you use, if it doesnt exist returns null
     */
    public Object useObjectInRoom(String objectToUse){
        for(int i=0;i<objects.size();i++){
            if (objects.get(i).getObjectName().contains(objectToUse)){
                System.out.println("You used the " + objectToUse);
                return objects.get(i);
            }
        }
        return null;
    }

    //Object functions
    /**
     * adds Object to objects ArrayList
     */
    public void addObject(String objectName,String objectDescription,boolean visible,Command command){objects.add(new Object(objectName,objectDescription,visible,command));}

    /**
     * @return ArrayList objects
     */
    public ArrayList<Object> returnObjectList(){return objects;}

    /**
     * sets the object with the name objectName to the opposite value
     */
    public void setObjectVisible(String objectName){
        for(int i=0;i<objects.size();i++){
            if (objects.get(i).getObjectName().equals(objectName)){
                objects.get(i).setVisible();
            }
        }
    }

    /**
     * adds Item to ArrayList 
     */
    public void addItem(String itemName,String itemDescription, int itemWeight,boolean visible){items.add(new Item(itemName,itemDescription, itemWeight,visible));}

    /**
     * @return ArrayList items
     */
    public ArrayList<Item> returnItemList(){return items;}

    /**
     * @return grabbed Item if item doesnt exist return null
     */
    public Item grabItemInRoom(String itemToGrab){
        for(int i=0;i<items.size();i++){
            if (items.get(i).getItemName().contains(itemToGrab)){
                System.out.println("You grabbed the " + itemToGrab);
                return items.get(i);
            }
        }
        return null;
    }

    /**
     * sets the object with the name objectName to the opposite value
     */
    public void setItemVisible(String itemName){
        for(int i=0;i<items.size();i++){
            if (items.get(i).getItemName().equals(itemName)){
                items.get(i).setVisible();
            }
        }
    }
    
    /**
     * remove item from arrayList
     */
    public void removeItem(String itemToRemove)
    {
        for(int i=0;i<items.size();i++){
            if (items.get(i).getItemName().contains(itemToRemove)){
                items.remove(i);
            }
        }
    }
    
    /**
     * delete item from arrayList
     */
    public void deleteItem(String itemToRemove)
    {
        for(int i=0;i<items.size();i++){
            if (items.get(i).getItemName().contains(itemToRemove)){
                System.out.println("You dropped the " + itemToRemove);
                items.remove(i);
            }
        }
    }
    
    public void addExistingItem(Item itemToAdd){
        System.out.println("You dropped the " + itemToAdd.getItemName());
        items.add(itemToAdd);
    }

    
    /**
     * Define the exits of this room.  Every direction either leads
     * to another room or is null (no exit there).
     * @param north The north exit.
     * @param east The east east.
     * @param south The south exit.
     * @param west The west exit.
     */
    public void setExits(String direction,Room neighbor) 
    {
        exits.put(direction, neighbor);
    }

    /**
     * @return The description of the room.
     */
    public String getDescription(){return description;}

    /**
     * @return The name of the room.
     */
    public String getName(){return roomName;}

    /**
     * @return The long description of the room.
     */
    public String getLongDescription(){return "you are " + description + ".\nIt appears to be the " + roomName + ".";}

    /**
     * @return The exits of the room.
     */
    public Room getExit(String direction){return exits.get(direction);}

    //public String 
    /** 
     * Retourneer een string met daarin de uitgangen van de ruimte
     * @return Een omschrijving van de uitgangen
     */
    public String getExitString(){
        String returnString = "Exits: ";
        Set<String> keys = exits.keySet();
        for(String exit : keys){
            returnString += " " + exit;
        }
        return returnString;
    }
}
