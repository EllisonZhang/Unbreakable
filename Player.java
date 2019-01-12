import java.util.ArrayList;
import java.util.Random;



public class Player {
	private int playerId;
	private int timesOfWin;
    private boolean isActivePlayer;
    private ArrayList<Card> playerCardDeck;
    
    public Player () {
    	isActivePlayer=true;
    	timesOfWin=0;
    	
    	
    }
    
    public void checkAvailability(){
    	
    	if(playerCardDeck.size() == 0)
    		isActivePlayer=false;
    
    }
    
   public void setPlayerID() {
	   Random rand=new Random();
	   playerId=rand.nextInt(899999)+100000;
	   
	   
   }
    
    public void addWin()
    {
    	timesOfWin++;
    }
    
    public void setCard(Card card) {
    	playerCardDeck.add(card);
    
    }
    
    
    
}
