
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
	// number of rounds
	int round;

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
		System.out.println("\n");
		System.out.println("Game Start");
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

		System.out.println("Round " + ++round);
		System.out.println("Current choosing player " + choosingPlayer.name);
		System.out.println("Round " + round + ": Players have drawn their cards");
		HashMap<Player, Card> board = new HashMap<Player, Card>();
		String option;
		for (Player player : players) {
			/*
			 * Printing the human's card to be able to pick desired attribute when its
			 * her/his turn to play.
			 */
			if (!player.getDeck().isEmpty()) {
				if (player.human) {
					
					System.out.println("There are " + (player.getDeck().size() - 1) + " cards in your deck");
					System.out.println("You drew '" + player.getDeck().get(0).name + "':");
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
			return;
		}
		if (choosingPlayer.human) {
			// If the choosing player is human we allow her/him to pick an attribute
			int value = categoryScanner();
			option = attributes[value];

		} else {
			/*
			 * Else the AI players use the highest value of their card as the selected
			 * attribute
			 */
			option = board.get(choosingPlayer).highestAttribute();
		}
		choosingValue(option, board);
		comparison(board);
		int quit = quitGameScanner();
		if (quit == 89 || quit == 121) {
			
		} else if (quit == 78 || quit == 110) {
			System.exit(0);
		}

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

			communalPile.addAll(board.values());
			System.out.println("Round " + round + ": This round was a Draw, common pile now has " + communalPile.size()
					+ " cards.");
		} else {
			/*
			 * If the round is not a tie, we change the winning player to the winner of the
			 * round, if the communal pile is not empty we add it to the winner's deck, then
			 * we add the cards from the board to the winner's deck
			 */
			System.out.println("The winning card was " + bestCard.name);
			bestCard.printCard();
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

	public int categoryScanner() {
		// Each time the choosing player is a human we let him choose his category
		// thanks to a scanner
		Scanner scanner = new Scanner(System.in);
		System.out.println("It is your turn to select a category, the categories are: \r\n   1: Strength\r\n"
				+ "   2: Intelligence\r\n" + "   3: Agility\r\n" + "   4: Mastery\r\n" + "   5: Stamina\r\n"
				+ "Enter the number for your attribute:");
		int value = scanner.nextInt();
		// scanner.close();
		return (value - 1);

	}
	
	public int quitGameScanner() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Next Round ? (Y/N)");
		String value = scanner.nextLine();
		char character = value.charAt(0);
		return (int) character;
	}
}
