package commandline;

import java.util.Scanner;

/**
 * Top Trumps command line application
 */
public class TopTrumpsCLIApplication {

	/**
	 * This main method is called by TopTrumps.java when the user specifies that they want to run in
	 * command line mode. The contents of args[0] is whether we should write game logs to a file.
 	 * @param args
	 */
	public static void main(String[] args) {

		boolean writeGameLogsToFile = false; // Should we write game logs to file?
		GameModel model = null;
		GameController controller = null;
		if (args[0].equalsIgnoreCase("true")) writeGameLogsToFile=true; // Command line selection
			
		GameStatistics statistics = new GameStatistics();
		
		boolean userWantsToQuit = false; // flag to check whether the user wants to quit the application#
		
		// Loop until the user wants to exit the game
		while (!userWantsToQuit) {
			if (writeGameLogsToFile)
				model = new GameModelTest(5);
			else 
				model = new GameModel(5);
			int value = cliIntroduction();
			// ----------------------------------------------------
			// Add your game logic here based on the requirements
			// ----------------------------------------------------
			if (value == 1) {
				statistics.retrievingGameStatistics();
			}
			if (value == 2) {
				controller = new GameController(model);
				controller.cliStart();
				statistics.storingGameStatistics(model.draws, model.gameWinner, model.round, model.players);
			}
			 // use this when the user wants to exit the game
			int quitValue = quitCLIGame();
			if (quitValue == 1) {
				continue;
			} if (quitValue == 2) {
				userWantsToQuit=true;
			}
		}
		
	}
	
	public static int quitCLIGame() {
		// Checking if the user wants to quit
		String cliSentence = "What else would you like to do ?\r\n\t1: "
				+ "Play a game\r\n\t2: Quit\r\n" + "Enter the number for your selection:";
		Scanner s = new Scanner(System.in);
		System.out.println(cliSentence);
		int value = s.nextInt();
		return value;
	}
	
	public static int cliIntroduction() {
		// Checking if the user wants to see statistics or play a game
		String cliSentence = "Do you want to see past results or play a game?\r\n\t1: "
				+ "Print Game Staticstics\r\n\t2: Play game\r\n" + "Enter the number for your selection:";
		Scanner s = new Scanner(System.in);
		System.out.println(cliSentence);
		int value = s.nextInt();
		return value;

	}
}
