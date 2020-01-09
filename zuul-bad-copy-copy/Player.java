
/**
 * class Player - geef hier een beschrijving van deze class
 *
 * @author (jouw naam)
 * @version (versie nummer of datum)
 */
public class Player
{
    private String playerName;
    private int carryWeight;
    private int i;

   
    public Player(String playerName, int carryWeight)
    {
        this.playerName = playerName;
        this.carryWeight = carryWeight; 
    }

    public String getPlayerName(){ return playerName;}
    
    public int getPlayerWeight(){ return carryWeight;}
    
}
