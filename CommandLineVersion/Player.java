import java.util.ArrayList;
import java.util.Random;

public class Player {
	private int playerId;
	private int timesOfWin;
    private boolean isActivePlayer;
    private boolean human;
    private ArrayList<Card> playerCardDeck=new ArrayList<Card>();
    String name;
    
    public Player (String name) {
    	isActivePlayer=true;
    	timesOfWin=0;
    	human=false;
    	this.name = name;
    	
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
    
    public void addCards(ArrayList<Card> c) {
    	playerCardDeck.addAll(c);
    }
    
    public void setCard(Card card) {
    	playerCardDeck.add(card);
    
    }
    
    public void getNumberOfCards() {
    	System.out.println("The player has "+playerCardDeck.size()+" Cards");
    }
    
    public void printCards() {
    	System.out.println(playerCardDeck);
    }
    
    public void setHuman() {
    	human=true;
    	name = "You";
    }
    
}
