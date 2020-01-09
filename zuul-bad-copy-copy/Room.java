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
    
    //ObjectInRoom Object= new ObjectInRoom();
    
   public void addObject(String objectName,String objectDescription){objects.add(new Object(objectName,objectDescription));}
    
 
    public ArrayList<Object> returnObjectList(){return objects;}
    
    public void addItem(String itemName,String itemDescription){items.add(new Item(itemName,itemDescription));}

    public ArrayList<Item> returnItemList(){return items;}
    
    public Item grabItemInRoom(String itemToGrab){
        System.out.println(itemToGrab);
        for(int i=0;i<items.size();i++){
            System.out.println(items.get(i));
            if (items.get(i).getItemName().contains(itemToGrab)){
                return items.get(i);
                
            }
            
        }
        return null;
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

   // public void getAllAtributes()
    //{
        
   // }
    /**
     * @return The description of the room.
     */
    public String getDescription(){return description;}
    
    public String getName(){return roomName;}
    
    public String getLongDescription(){return "you are " + description + ".\n" + getExitString();}
    
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
