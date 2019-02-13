package commandline;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class GameController {
	GameModel game;

	public GameController(GameModel model) {
		this.game = model;
		createCards();
		game.newGame();
		
		

	}

	public void createCards() {
		/* This method reads our file */
		String fn = "Hearthstone.txt";
		Scanner fileScanner = null;
		try {
			FileReader fileReader = new FileReader(fn);
			fileScanner = new Scanner(fileReader);
			String headerOutput = fileScanner.nextLine();
			game.attributes = headerOutput.split(" ");
			// we set the attributes to the values found in the file
			game.attributes = Arrays.copyOfRange(game.attributes, 1, 6);
			while (fileScanner.hasNext()) {
				String output = fileScanner.nextLine();
				String[] row = output.split(" ");
				// we get the name of each card
				String name = row[0];
				// this list is created to contain the attribute values for each card
				ArrayList<Integer> attributeValues = new ArrayList<Integer>();
				for (int i = 1; i < row.length; i++) {
					attributeValues.add(Integer.parseInt(row[i]));
				}
				// for each row of the file we create a new card 
				game.deck.add(new Card(name, game.attributes, attributeValues));

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			fileScanner.close();
		}

	}
	
	public void cliStart() {
		while(!game.gameOver) {
			game.round();
		}
	}
}
