import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Card implements Comparable<Card> {
	// used in each round to compare cards
	int usedValue;
	// card name/description
	public String name;
	// table containing each attribute name and its value for a card
	public HashMap<String, Integer> attributes = new HashMap<String, Integer>();

	public Card(String name, String[] attributeNames, ArrayList<Integer> attributeValues) {
		// at initialization we create the attributes table for each card
		this.name = name;
		for (int i = 0; i < attributeValues.size(); i++) {
			attributes.put(attributeNames[i], attributeValues.get(i));
		}
	}

	public void printCard() {
		// Allows us to print in the console a card's attributes
		attributes.forEach((k, v) -> System.out.println("  > " + k + ":" + v));
	}

	public String getName() {
		return name;
	}

	public String highestAttribute() {
		// retrieve the attribute name of the highest attribute in one card
		return Collections.max(attributes.entrySet(), Map.Entry.comparingByValue()).getKey();
	}

	public int compareTo(Card other) {
		/*
		 * Allows us to compare two cards with one another and determine which one is
		 * better according to the used value at a particular one
		 */
		return Integer.compare(this.usedValue, other.usedValue);

	}

}
