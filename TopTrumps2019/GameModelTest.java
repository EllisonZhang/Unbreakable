import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class GameModelTest extends GameModel {
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
		lineDash();
		try {
			log.write("Category selected: " + option + "\nValues :");
			log.flush();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		for (Card card : board.values()) {

			card.usedValue = card.attributes.get(option);
			try {
				log.write(card.usedValue + ", ");
				log.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void comparison(HashMap<Player, Card> board) {
		super.comparison(board);
		if (!communalPile.isEmpty()) {
			try {
				log.write("\nCommunal Pile Content: \n");
				log.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for (Card card: communalPile) {
				try {
					log.write(card.name + ", ");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		playerDeckContent();

	}
	
	public void round() {
		super.round();
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
	}

	public void shuffledDeckContent() {
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
