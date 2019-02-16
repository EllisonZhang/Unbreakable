package commandline;

import java.util.Scanner;

public class CLIGameModel extends GameModel {
	/**
	 * Class used to run the CLI version of the game (identical to the online
	 * version besides an expected used input between each round for the user to let
	 * the program knows he wishes to move on to the next round)
	 */
	public CLIGameModel(int numberOfPlayers) {
		super(numberOfPlayers);

	}

	public boolean round() {
		boolean draw = super.round();
		if (!players.get(0).getDeck().isEmpty()) {
			int quit = quitGameScanner();
			if (quit == 78) {
				System.exit(0);
			}
		}
		return draw;
	}


	public int quitGameScanner() {
		Scanner scanner = new Scanner(System.in);
		char character = 0;
		while (character != 'Y' && character != 'N') {
			System.out.println("Next Round? (Yes/No(quit)):\nEnter Y or N: ");
			character = scanner.nextLine().charAt(0);

		}
		return (int) character;

	}
}
