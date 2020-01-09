
import java.util.List;
import java.util.ArrayList;
public class Player
{
    private String playerName;
    private int carryWeight;
    private ArrayList<Item> inventory;
    
   
    public Player(String playerName, int carryWeight)
    {
        this.playerName = playerName;
        this.carryWeight = carryWeight; 
        this.inventory = new ArrayList<>();
    }
    
    public void addItem(Item item){
        inventory.add(item);
    }
    
    public ArrayList<Item> returnInventory(){return inventory;}

    public String getPlayerName(){ return playerName;}
    
    public int getPlayerWeight(){ return carryWeight;}
    
}
