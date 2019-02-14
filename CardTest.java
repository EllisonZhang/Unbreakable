package commandline;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CardTest {
	// test data, the same as card Categories+Values
	private String[] testCategories= {"A","B","C","D","E"};
	private ArrayList<Integer> testNumbers = new ArrayList<Integer>();
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		
	}

	@Test
	void testValue() {
		/**
		 * Here we create to cards to test if the  highestAttribute() and
		 * compareTo() method will return the expected results. 
		 * */
		
		for(int i=1;i<=5;i++) {
			testNumbers.add(i);
		}
		
		Card testCard1 = new Card("Ellison",testCategories,testNumbers);
		testCard1.usedValue = 1;
		Card testCard2 = new Card("Ellison",testCategories,testNumbers);
		testCard2.usedValue = 2;
		
		assertSame("E",testCard1.highestAttribute());
		assertSame(-1,testCard1.compareTo(testCard2));
	}

}
