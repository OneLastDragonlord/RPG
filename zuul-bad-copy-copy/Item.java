
public class Item
{
    // instance variables - vervang deze door jouw variabelen
    private String itemDescription;
    private String itemName;
    private int itemWeight;
    private boolean visible;

    /**
     * Constructor for items of class Item
     */
    public Item (String itemName, String itemDescription, int itemWeight,boolean visible) {
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemWeight = itemWeight;
        this.visible = visible;
    }

    /**
     * @return item name
     */
    public String getItemName(){return itemName;}

    /**
     * @return item description
     */
    public String getItemDescription(){return itemDescription;}
    
    /**
     * @return item weight in int
     */
    public int getItemWeight(){return itemWeight;}
    
    /**
     * @return boolean visibility
     */
    public boolean getVisible(){return visible;}
    
    /**
     * sets boolean visibility
     */
    public void setVisible(){this.visible = !this.visible;}
}