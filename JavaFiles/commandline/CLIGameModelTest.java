package commandline;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class CLIGameModelTest extends CLIGameModel {
	/** Class used to write the history of the game to a .log file (combine all the
	 functionality of the CLIGameModel class plus a file writer) */
	public CLIGameModelTest(int numberOfPlayers) {
		super(numberOfPlayers);
		
	}

	private FileWriter log = null;

	{

		try {
			log = new FileWriter("toptrumps.log");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void newGame() {
		System.out.println("\n");
		System.out.println("Game Start");
		createPlayers();
		initialDeckContent();
		shuffleCards();
		distributeCards();
	}

	public void shuffleCards() {
		super.shuffleCards();
		shuffledDeckContent();
	}

	public void choosingValue(String option, HashMap<Player, Card> board) {
		// writing the chosen category of each round to the file
		lineDash();
		try {
			log.write("Category selected: " + option + "\nValues: ");
			log.flush();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		for (Card card : board.values()) {

			card.usedValue = card.attributes.get(option);
			try {
				log.write(card.name + ": ");
				log.write(card.usedValue + "| ");
				log.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public boolean comparison(HashMap<Player, Card> board) {
		// writing the content of the communal pile to the file
		boolean draw = super.comparison(board);
		if (!communalPile.isEmpty()) {
			try {
				log.write("\nCommunal Pile Content: \n");
				log.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for (Card card : communalPile) {
				try {
					log.write(card.name + ", ");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		playerDeckContent();
		return draw;
	}

	public boolean round() {
		// writing the winner of the game to the file
		boolean draw = super.round();
		lineDash();
		if (gameOver) {
			lineDash();
			try {
				log.write(choosingPlayer.name + " won the game.");
				log.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return draw;
	}

	public void shuffledDeckContent() {
		// Writing the content of the initial deck to the file
		lineDash();
		try {
			log.write("Shuffled deck content: \n");
			log.flush();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		for (Card card : deck) {
			try {
				log.write(card.name + " ");
				log.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void initialDeckContent() {
		// Writing the name of the card of each player to the file
		lineDash();
		try {
			log.write("Initial deck content: \n");
			log.flush();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		for (Card card : deck) {
			try {
				log.write(card.name + " ");
				log.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void playerDeckContent() {
		// Writing the name of the card of each player to the file
		lineDash();
		for (Player player : players) {
			try {
				if (player.human) {
					log.write("Human player deck content: ");
					log.flush();
				} else {
					log.write("\nPlayer " + player.name + " deck content: ");
					log.flush();
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for (Card card : player.getDeck()) {
				try {
					log.write(card.name + " ");
					log.flush();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public void lineDash() {
		// writes a line dash in the file for better readability
		String str = "\n------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n";
		try {
			log.write(str);
			log.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
