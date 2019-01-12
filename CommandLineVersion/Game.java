import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Game {
	public  ArrayList<Card> cardDeck = new ArrayList<Card>();
	private ArrayList<Card> comunalPile=new ArrayList<Card>();
	private int draws;
	private int rounds;
	private int choosingPlayer;
	
	int numberOfPlayers;
	ArrayList<Player> players;
	

	public Game() {
		setNumberOfPlayers();
		begin();
		
		
	}

	public int getDraws() {
		return this.draws;
	}

	public void setDraws(int draws) {
		this.draws = draws;
	}

	public int getRounds() {
		return this.rounds;
	}

	public void setRounds(int rounds) {
		this.rounds = rounds;
	}

	public void setNumberOfPlayers() {
		numberOfPlayers = 4;
		players = new ArrayList<Player>();
		for (int i = 0; i < numberOfPlayers; i++) {
			players.add(new Player());
		}
		players.get(0).setHuman();

	}

	public void begin() {
		Random random = new Random();
		int randomNumber = random.nextInt(numberOfPlayers);
		choosingPlayer = randomNumber;

	}

	public void cardTest() {
		for (int j=0;j<numberOfPlayers;j++) {
			for (int i=0;i<40/numberOfPlayers;i++) {
				System.out.print(players.get(j).getCardDeck().get(i).getDescription()+" ");
			}
			System.out.println("");
		}
	}
	
	public void newRound() {
		ArrayList<Card> board=new ArrayList<Card>();
	    
		Scanner s=new Scanner(System.in);
		int option;  // option stand for the category
		
		
		for (int i=0;i<numberOfPlayers;i++) {
			board.add(players.get(i).getCardDeck().remove(0)); //
		}
		
		if (players.get(0).getHuman()) {
			board.get(0).getAttributes();
			
			System.out.println("Please select an attribute: ");
			option=s.nextInt();
			option--;
			s.nextLine();
		}
		
		else{
			option= board.get(choosingPlayer).chooseHighestAttribute();
			board.get(choosingPlayer).getAttributes();
		}
		
		System.out.println(option);
		System.out.println(choosingPlayer);

		
		
	}
	
	
	public void Comparison(int option, ArrayList<Card> board) {
		int tempWinner=0;
		int maxValue=board.get(0).getAttributesValues()[option];
		boolean draw=false;
		for (int i=1;i<numberOfPlayers;i++) {
			if(board.get(i).getAttributesValues()[option]>maxValue) {
				tempWinner=i;
				maxValue=board.get(i).getAttributesValues()[option];
				draw=false;
			}
			else if(board.get(i).getAttributesValues()[option]==maxValue) {
				draw=true;
			}
		}
		//getting the board cards inside the communal pile
		//easy way and very understandable 
		//not radical way to do it
		
		for(int i=0;i<board.size();i++) {
			comunalPile.add(board.remove(i));
		}
		//If there is a not a draw then 
		//we give the cards to the player
		if (!draw) {
		choosingPlayer=tempWinner;
		for(int i=0;i<comunalPile.size();i++) {
			players.get(choosingPlayer).getCardDeck().add(comunalPile.get(i));
		}
		
		}
		//need to make a winner statement, loser statement
		
	}
	
	public void CheckPlayers() {
		int loses=0;
		for (int i=0;i<players.size();i++) {
			players.get(i).checkAvailability();
			if(!players.get(i).checkAvailability()) {
				players.remove(i);
				loses++;
				if(i<choosingPlayer) {
					choosingPlayer--;
				}
				
			}
			
		}
		numberOfPlayers=numberOfPlayers-loses;
	}
	
	
}
