
import java.util.List;
import java.util.ArrayList;
public class Player
{
    private String playerName;
    private int carryWeight;
    private ArrayList<Item> inventory;
    private int currentWeight;
    
   
    public Player(String playerName, int carryWeight)
    {
        this.playerName = playerName;
        this.carryWeight = carryWeight; 
        this.currentWeight = 0;
        this.inventory = new ArrayList<>();
    }
    
    public void addItem(Item item){
        inventory.add(item);
    }
    
    public Item getItem(String itemName){
        for(Item item : inventory){
            if(item.getItemName().equals(itemName)){
                return item;
            }
        }
        return null;
    }
    
    public void deleteItem(Item item){
        inventory.remove(item);
    }
    
    public ArrayList<Item> returnInventory(){return inventory;}

    public String getPlayerName(){ return playerName;}
    
    public int getPlayerWeight(){ return carryWeight;}
    
    public int getCurrentWeight(){ return currentWeight;}
}
