import java.util.ArrayList;

// controller of each round

public class Round {
	
	public  ArrayList<Card> communal  = new ArrayList<Card>();
	public  ArrayList<Player> playerInRound  = new ArrayList<Player>();
	private Player activePlayer;
    private Player winner;
    private String category;
	private boolean isDraw;
	
//	public void begin(int numberOfPlayers) {
//		int randomNumber = Random.netxInt(numberOfPlayers);
//		activePlayer = player.inRound[randomNumber];
//		for(int i=0;i<numberOfPlayers;i++) {
//			playerInRound.add(new Player());
//		}	    
//	}
	
	public void chooseCategory() {
		
	}

}
