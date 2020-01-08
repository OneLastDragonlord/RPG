
/**
 * class Item - geef hier een beschrijving van deze class
 *
 * @author (jouw naam)
 * @version (versie nummer of datum)
 */
import java.util.Arrays;


public class ObjectInRoom
{
    // instance variables - vervang deze door jouw variabelen
        private String objectDescription;
        private String objectName;
        private String roomName;

    /**
     * Constructor voor objects van class Item
     */
    public ObjectInRoom (String objectName, String objectDescription, String roomName) {
        this.objectName = objectName;
        this.objectDescription = objectDescription;
    }
    
    public String getObjectName()
    {
        return objectName;
    }
    
    public String getObjectDescription()
    {
        return objectDescription;
    }
    
    public String getRoomName()
    {
        return roomName; 
    }
    

    
    
}
