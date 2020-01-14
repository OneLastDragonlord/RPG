
public class Item
{
    // instance variables - vervang deze door jouw variabelen
    private String itemDescription;
    private String itemName;
    private int itemWeight;

    /**
     * Constructor voor items van class Item
     */
    public Item (String itemName, String itemDescription, int itemWeight) {
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemWeight = itemWeight;
    }

    public String getItemName(){return itemName;}

    public String getItemDescription(){return itemDescription;}
    
    public int getItemWeight(){return itemWeight;}
}