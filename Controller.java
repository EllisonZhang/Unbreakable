
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class Controller {

	private int position = 0;
	public  ArrayList<Card> cardDeck = new ArrayList<Card>();
	private ArrayList<String> tuples = new ArrayList<String>(); 
	private Scanner s;
	
	public Controller(){
		getFileContent ();
		assignData();
	}

// first read file and put the content of every line into "tuples" (ArrayList)
// * called in constructor
	public void getFileContent () {
		String fn = "Hearthstone.csv";	
		try {	
			FileReader readFile = new FileReader(fn);
			s = new Scanner (readFile);
			while(s.hasNextLine()) {				
				if(position>0) { 		
					//here we put the content of every line into "tuples" (ArrayList)
					tuples.add(s.nextLine());  
					//for every line we have, we add an empty "Card" object into cardDeck
					//and the "cardDeck" is occupied by 40 empty card now.
					cardDeck.add(new Card());				
				}else {
					s.nextLine();
				}
				position++;
			}      
		}catch (FileNotFoundException e) {
			System.out.println("wrong address!! file not found.");
		} finally{

		}			
	} 
	
// Use this method to pass the data from "tuples" to "cardDeck"
// now the objects in "cardDeck" are now empty anymore, they all have values inside.
// * called in constructor
	public void assignData() {
		for (int i=0; i< cardDeck.size();i++) {
			cardDeck.get(i).setDescription(tuples.get(i).split(",")[0]);
			cardDeck.get(i).setStrength(Integer.parseInt(tuples.get(i).split(",")[1]));
			cardDeck.get(i).setIntelligence(Integer.parseInt(tuples.get(i).split(",")[2]));
			cardDeck.get(i).setAgility(Integer.parseInt(tuples.get(i).split(",")[3]));
			cardDeck.get(i).setMastery(Integer.parseInt(tuples.get(i).split(",")[4]));
			cardDeck.get(i).setStamina(Integer.parseInt(tuples.get(i).split(",")[5]));
		}	
	}
	
	public void shuffleCards() {}
	public void quitGame() {}
	public void chooseMode() {}
	public void sendCards() {}
	public void beginNewRound() {}
	public void choosePlayer() {}
	
}
