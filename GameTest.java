package commandline;

import static org.junit.Assert.assertSame;
import static org.junit.jupiter.api.Assertions.*;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

class GameTest {
	GameModel model = null;
	GameController controller = null;

	@BeforeEach
	void setup() {
		model = new GameModel(5);
		controller = new GameController(model);
	}

	@Test
	void numberOfPlayerTest() {
		assertSame(5, model.players.size());	
	}
	
	@Test
	void initialDeckSizeTest() {
		assertSame(8, model.players.get(0).getDeck().size());
	}
	
	@Test
	void shuffleMethodTest() {
		ArrayList<String> cardNames = new ArrayList<String>();
		for (Card c: model.deck) {
			cardNames.add(c.getName());
		}
		model.shuffleCards();
		for (int i = 0; i < model.deck.size(); i+=10) {
			assertNotEquals(cardNames.get(i), model.deck.get(i).getName());
		}
		
	}
	
	@Test
	void correctAttributesTest() {
		String[] correctAttributes = {"Strength", "Intelligence", "Agility", "Mastery", "Stamina"};
		for (int i = 0; i < correctAttributes.length; i++) {
			assertEquals(correctAttributes[i], model.attributes[i]);
		}
	}
	
	

}
