import java.util.ArrayList;
import java.util.Random;

public class Player {
	private int playerId;
	private int timesOfWin;
    private boolean isActivePlayer;
    private boolean human;
    private ArrayList<Card> playerCardDeck=new ArrayList<Card>();
    
    public Player () {
    	isActivePlayer=true;
    	timesOfWin=0;
    	human=false;
    	
    	
    }
    public boolean getHuman() {
    	return human;
    }
    public boolean checkAvailability(){
    	
    	if(playerCardDeck.size() == 0)
    		isActivePlayer=false;
    
    	return isActivePlayer;
    }
    
   public void setPlayerID() {
	   Random rand=new Random();
	   playerId=rand.nextInt(899999)+100000;
	      
   }
    
    public void addWin()
    {
    	timesOfWin++;
    }
    
    public ArrayList<Card> getCardDeck() {
    	return playerCardDeck;
    }
    
    public void setCard(Card card) {
    	playerCardDeck.add(card);
    
    }
    
    public void printCards() {
    	System.out.println(playerCardDeck);
    }
    
    public void setHuman() {
    	human=true;
    }
    
}