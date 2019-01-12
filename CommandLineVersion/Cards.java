import java.util.HashMap;

public class Cards {
	public String name;
	public String description;
	public HashMap<String, Integer> attributes = new HashMap<String, Integer>();
	public Cards(String[] attributeNames, int[] attributeValues) {
		for (int i=0; i < attributeNames.length; i++) {
			attributes.put(attributeNames[i], attributeValues[i]);
		}
	}
}
