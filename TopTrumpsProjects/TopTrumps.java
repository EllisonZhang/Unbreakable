import java.util.Scanner;

public class TopTrumps {

	public static void main(String[] args) {

		GameModel model = null;
		GameController controller = null;
		GameStatistics statistics = new GameStatistics();

//		if (args[0].equalsIgnoreCase("-c")) {
		int value = cliIntroduction();
		if (value == 1) {
			statistics.retrievingGameStatistics();
		}
		if (value == 2) {
			int numberOfPlayers = numberOfPlayersSelection();
			numberOfPlayers++;
			model = new GameModelTest(numberOfPlayers);
			controller = new GameController(model);
			statistics.storingGameStatistics(model.draws, model.gameWinner, model.round, model.players);
		}
//		};

	}

	public static int cliIntroduction() {
		// Checking if the user wants to see statistics or play a game
		String cliSentence = "Do you want to see past results or play a game?\r\n\t1: "
				+ "Print Game Staticstics\r\n\t2: Play game\r\n" + "Enter the number for your selection:";
		System.out.println("--------------------");
		System.out.println("---- Top Trumps ----");
		System.out.println("--------------------");
		Scanner s = new Scanner(System.in);
		System.out.println(cliSentence);
		int value = s.nextInt();
		return value;

	}

	public static int numberOfPlayersSelection() {
		// Checking if the user wants to see statistics or play a game
		String cliSentence = "How many AI would you like to play with (Select a number between 1 and 4):";
		Scanner s = new Scanner(System.in);
		System.out.println(cliSentence);
		int value = s.nextInt();
		return value;

	}
}
