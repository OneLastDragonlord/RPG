
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
    
<<<<<<< HEAD
    public void minusCurrentWeight(int itemWeight){this.currentWeight -= itemWeight;}
    
    public void updateCurrentWeight(int itemWeight){this.currentWeight += itemWeight;}
    
    public boolean correctWeight(){
        boolean correctWeight = true;
        if(this.currentWeight >= this.carryWeight){
        System.out.println(this.currentWeight);
        System.out.println(this.carryWeight);
        correctWeight = false;
    }
    return correctWeight;
}
    
=======
>>>>>>> 18cafcc9e333aa0c8910ac762f2e6ced10251a85
    public ArrayList<Item> returnInventory(){return inventory;}

    public String getPlayerName(){ return playerName;}
    
    public int getPlayerWeight(){ return carryWeight;}
    
    public int getCurrentWeight(){ return currentWeight;}
}
