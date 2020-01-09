
public class Item
{
    // instance variables - vervang deze door jouw variabelen
    private String itemDescription;
    private String itemName;
   

    /**
     * Constructor voor items van class Item
     */
    public Item (String itemName, String itemDescription) {
        this.itemName = itemName;
        this.itemDescription = itemDescription;
    }

    public String getItemName(){return itemName;}

    public String getItemDescription(){return itemDescription;}
}