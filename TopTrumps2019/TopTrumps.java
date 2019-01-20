import java.util.Scanner;

public class TopTrumps {

	public static void main(String[] args) {

		GameModel model = new GameModel();
		GameController controller = null;
		
		
		if (args[0].equalsIgnoreCase("-c")) {
			int value = cliIntroduction();
			if (value == 2) {
				controller = new GameController(model);
			}
		};
			

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
}
