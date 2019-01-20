import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Game {
	public  ArrayList<Card> cardDeck = new ArrayList<Card>();
	private ArrayList<Card> comunalPile=new ArrayList<Card>();
	private int draws;
	private int rounds;
	private int choosingPlayer;
	private boolean endGame=false;
	
	int numberOfPlayers;
	ArrayList<Player> players;
	
	public int getChoosingPlayer() {
		return choosingPlayer;
	}

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
			players.add(new Player("AI " + i));
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
			
			board.add(players.get(i).getCardDeck().get(0)); 
			players.get(i).getCardDeck().remove(0); 
			System.out.println("Number of cards of "+i+" are "+players.get(i).getCardDeck().size()+" "+players.size());
		}
		
		
		if (false/*players.get(0).getHuman()&& choosingPlayer==0*/) {
			board.get(0).getAttributes();
			
			System.out.println("Please select an attribute: ");
			option=s.nextInt();
			option--;
			s.nextLine();
		}
		
		else{
			option= board.get(choosingPlayer).chooseHighestAttribute();
			//board.get(choosingPlayer).getAttributes();
		}
		
		System.out.println("The atribute of choise is "+option);
		System.out.println("The choosing player is " + players.get(choosingPlayer).name);
		System.out.println();
		Comparison(option,board);
		
		
	}
	
	
	public void Comparison(int option, ArrayList<Card> board) {
		
		//To print the Cards in each board
		
		//for(int i=0;i<board.size();i++) {
			//board.get(i).getAttributes();
		//}
		
		
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
		
	
			comunalPile.addAll (board);
		
		System.out.println(comunalPile);
		//If there is a not a draw then 
		//we give the cards to the player
		if (!draw) {
		choosingPlayer=tempWinner;
			players.get(choosingPlayer).addCards(comunalPile);
			comunalPile = new ArrayList<Card>();
		
		
		}
		
		
		CheckPlayers();
		//need to make a winner statement, loser statement
				System.out.println("The winner of the round is "+players.get(choosingPlayer).name);
	}
	
	public void CheckPlayers() {
		int loses=0;
		int j=0;
		int i=numberOfPlayers;
		while(i>0) {
			
			players.get(j).checkAvailability();
			if(players.get(j).getCardDeck().size()==0) {
				players.remove(j);
				loses++;
				if(j<choosingPlayer) {
					choosingPlayer--;
				}
				System.out.println(11111);
			}
			else {
				j++;
			}
			i--;
		}
		numberOfPlayers=numberOfPlayers-loses;
		if (numberOfPlayers==1) {
			//need to make a winner statement, loser statement
			System.out.println("The winner of the game is "+players.get(choosingPlayer).name);
			System.out.println("Number of cards of "+" are "+players.get(choosingPlayer).getCardDeck().size());
			endGame=true;
		}
	}
	
	public boolean getendGame() {
		return endGame;
	}
	
}