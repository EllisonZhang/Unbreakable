package commandline;

import java.util.ArrayList;
import java.util.Random;

public class Player {
	//private int playerId;
	// number of round a certain player won in a game
	int roundsWon = 0;
	// name of the player
	String name;
	boolean human;
	// deck of a player
	private ArrayList<Card> deck = new ArrayList<Card>();

	public Player(String name) {
		human = false;
		this.name = name;

	}

//	public void setPlayerID() {
//		Random rand = new Random();
//		playerId = rand.nextInt(899999) + 100000;
//
//	}

	public void addWin() {
		roundsWon++;
	}

	public ArrayList<Card> getDeck() {
		return deck;
	}

	public void addCard(Card card) {
		deck.add(card);
	}

//	public void printCards() {
//		System.out.println(deck);
//	}

	public void setHuman() {
		// name is set to you for console purposes
		human = true;
		name = "You";
	}
	
	public String toString() {
		return name;
	}
	public String getName() {
		return name;
	}

}