
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class GameModel {
// Model containing the data and the method necessary to run a game

	// Different attribute names for our cards (Intelligence, Mastery, etc...)
	String[] attributes;
	// Game deck containing 40 cards
	ArrayList<Card> deck = new ArrayList<Card>();
	// Players that will play the game
	ArrayList<Player> players = new ArrayList<Player>();
	// Communal pile for tied rounds
	ArrayList<Card> communalPile = new ArrayList<Card>();
	// Number of players in each games
	int numberOfPlayers;
	// Number of draws throughout the game
	int draws;
	// Winner of each round (random player when the game start)
	Player choosingPlayer;
	// Boolean enabling use to close a game
	boolean gameOver = false;

	public void createPlayers() {
		/*
		 * Adding objects from our player class in our players List. The names of the
		 * players will help us distinguish who wins each round AI 1, AI 2, AI 3...
		 * Etc... One of the player will be a human and its name in our console output
		 * will be "You" (Defined by setHuman()).
		 */
		numberOfPlayers = 4;
		for (int i = 0; i < numberOfPlayers; i++) {
			players.add(new Player("AI " + i));
		}
		players.get(0).setHuman();
		// The first choosing player will be generated randomly.
		choosingPlayer = players.get((int) (Math.random() * 4));
	}

	public void newGame() {
		/*
		 * At the beginning of each game we first create the players, then we shuffle
		 * the cards and distribute them evenly.
		 */
		createPlayers();
		shuffleCards();
		distributeCards();
	}

	public void shuffleCards() {
		// Collections method that allow us to shuffle our deck of cards
		Collections.shuffle(deck);
	}

	public void round() {
		/*
		 * At the beginning of each round we create a board containing the players and
		 * their top cards. (Only for the players that have remaining cards in their
		 * deck).
		 */
		HashMap<Player, Card> board = new HashMap<Player, Card>();
		String option;
		Scanner s = new Scanner(System.in);
		for (Player player : players) {
			/*
			 * Printing the human's card to be able to pick desired attribute when its
			 * her/his turn to play.
			 */
			if (!player.getDeck().isEmpty()) {
				if (player.human) {
					player.getDeck().get(0).printCard();
					board.put(player, player.getDeck().remove(0));
				} else {
					board.put(player, player.getDeck().remove(0));
				}

			}

		}
		// The game will end when only one player attends the board
		if (board.values().size() == 1) {
			board.forEach((k, v) -> System.out.println(k + " won. Game over"));
			gameOver = true;
			s.close();
			return;
		}
		if (choosingPlayer.human) {
			// If the choosing player is human we allow her/him to pick an attribute
			System.out.println("Please select an attribute: ");
			option = s.nextLine().toLowerCase();
			option = option.substring(0, 1).toUpperCase() + option.substring(1);

		} else {
			/*
			 * Else the AI players use the highest value of their card as the selected
			 * attribute
			 */
			option = board.get(choosingPlayer).highestAttribute();
		}
		choosingValue(option, board);
		comparison(board);

	}

	public void choosingValue(String option, HashMap<Player, Card> board) {
		/*
		 * For each card on the board, the attribute used Value will be set to the
		 * attribute value selected by the winning player from the previous round
		 */
		System.out.println(option);
		for (Card card : board.values()) {

			card.usedValue = card.attributes.get(option);
		}
	}

	public void comparison(HashMap<Player, Card> board) {
		/*
		 * We get the best card from the board (card with the highest value). However if
		 * another player's card's share the same value, we then agree on a tie and add
		 * the cards from the board to the communal pile.
		 */
		int counter = 0;
		Card bestCard = Collections.max(board.values());
		for (Card card : board.values()) {
			if (card.compareTo(bestCard) == 0) {
				counter++;
			}

		}
		if (counter > 1) {
			System.out.println("Tie");
			communalPile.addAll(board.values());
		} else {
			/*
			 * If the round is not a tie, we change the winning player to the winner of the
			 * round, if the communal pile is not empty we add it to the winner's deck, then
			 * we add the cards from the board to the winner's deck
			 */
			choosingPlayer = Collections.max(board.entrySet(), Map.Entry.comparingByValue()).getKey();
			if (!communalPile.isEmpty()) {
				choosingPlayer.getDeck().addAll(communalPile);
				communalPile = new ArrayList<Card>();
			}
			choosingPlayer.getDeck().addAll(board.values());
			choosingPlayer.roundsWon++;
			System.out.println(choosingPlayer + " won the round.");
		}
	}

	public void distributeCards() {
		// distributing cards to players;
		int currentPlayer = 0;
		for (int i = 0; i < deck.size(); i++) {
			players.get(currentPlayer).addCard(deck.get(i));
			currentPlayer++;
			if (currentPlayer == numberOfPlayers) {
				currentPlayer = 0;
			}

		}

	}
}
