
public class Object
{
    // instance variables - vervang deze door jouw variabelen
    private String objectDescription;
    private String objectName;
    private boolean visible;
    private Command command;
    
    /**
     * Constructor voor objects van class Item
     * @param Command for the next command associated with this item
     */
    public Object (String objectName, String objectDescription, boolean value,Command command) {
        this.objectName = objectName;
        this.objectDescription = objectDescription;
        this.visible = value;
        this.command = command;
    }

    /**
     * @return object name
     */
    public String getObjectName(){return objectName;}

    /**
     * @return object description
     */
    public String getObjectDescription(){return objectDescription;}
    
    /**
     * @return command
     */
    public Command getCommand(){return command;}
    
    /**
     * @return boolean visibility
     */
    public boolean getVisible(){return visible;}
    
    /**
     * sets boolean visibility
     */
    public void setVisible(){this.visible = !this.visible;}
}
    

    
    

