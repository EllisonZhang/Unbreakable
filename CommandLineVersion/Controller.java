import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class Controller {

	private int position = 0;
	private ArrayList<String> tuples = new ArrayList<String>();
	private Scanner s;

	private Game game;

	public Controller() {
		game = new Game();
		getFileContent();
		assignData();
		shuffleCards();
		sendCards();
		
		
		
		
		while(!game.getendGame()) {
			game.newRound();
		}
		
		
		
	}

// first read file and put the content of every line into "tuples" (ArrayList)
// * called in constructor
	public void getFileContent() {
		String fn = "Hearthstone.csv";
		try {
			FileReader readFile = new FileReader(fn);
			s = new Scanner(readFile);
			while (s.hasNextLine()) {
				if (position > 0) {
					// here we put the content of every line into "tuples" (ArrayList)
					tuples.add(s.nextLine());
					// for every line we have, we add an empty "Card" object into cardDeck
					// and the "cardDeck" is occupied by 40 empty card now.
					game.cardDeck.add(new Card());
				} else {
					s.nextLine();
				}
				position++;
			}
		} catch (FileNotFoundException e) {
			System.out.println("wrong address!! file not found.");
		} finally {

		}
	}

// Use this method to pass the data from "tuples" to "cardDeck"
// now the objects in "cardDeck" are now empty anymore, they all have values inside.
// * called in constructor
	public void assignData() {
		for (int i = 0; i < game.cardDeck.size(); i++) {
			game.cardDeck.get(i).setDescription(tuples.get(i).split(",")[0]);
			game.cardDeck.get(i).setStrength(Integer.parseInt(tuples.get(i).split(",")[1]));
			game.cardDeck.get(i).setIntelligence(Integer.parseInt(tuples.get(i).split(",")[2]));
			game.cardDeck.get(i).setAgility(Integer.parseInt(tuples.get(i).split(",")[3]));
			game.cardDeck.get(i).setMastery(Integer.parseInt(tuples.get(i).split(",")[4]));
			game.cardDeck.get(i).setStamina(Integer.parseInt(tuples.get(i).split(",")[5]));
		}
	}

	public void shuffleCards() {
		Collections.shuffle(game.cardDeck);

	}

	public void quitGame() {
	}

	public void chooseMode() {
	}

	public void sendCards() {
		int currentPlayer = 0;
		for (int i = 0; i < game.cardDeck.size(); i++) {
			game.players.get(currentPlayer).setCard(game.cardDeck.get(i));
			currentPlayer++;
			if (currentPlayer == game.numberOfPlayers) {
				currentPlayer = 0;
			}

		}

	}

	public void beginNewRound() {
		
		
		
	}

	public void choosePlayer() {
	}

}