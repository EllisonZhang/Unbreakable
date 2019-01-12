import java.util.Collections;

public class Card {
	
	private String description;
//	private int[] attributes = new int[5];
	private int strength;
	private int agility;
	private int mastery;
	private int stamina;
	private int intelligence;

    public Card () {
    	
    }
    
    public void setDescription (String description) {
    	this.description = description;
    }
    public String getDescription () {
    	return description;
    }
    public void setAgility (int agility) {
    	this.agility = agility;
    }
    public int getAgility () {
    	return agility;
    }
    public void setMastery (int mastery) {
    	this.mastery = mastery;
    }
    public int getMastery () {
    	return mastery;
    }
    public void setStrength (int strength) {
    	this.strength = strength;
    }
    public int getStrength () {
    	return strength;
    }
    public void setStamina (int stamina) {
    	this.stamina = stamina;
    }
    public int getStamina () {
    	return stamina;
    }
    public void setIntelligence (int intelligence) {
    	this.intelligence = intelligence;
    }
    public int getIntelligence () {
    	return intelligence;
    }
    
    public void getAttributes() {
    	System.out.println("======Card " +":" + getDescription()+ "=======");
		System.out.println("1.Strength: " + getStrength());
		System.out.println("2.Intelligence: " + getIntelligence());
		System.out.println("3.Agility: " + getAgility());
		System.out.println("4.Mastery: " + getMastery());
		System.out.println("5.Stamina: " + getStamina());
    }
    
    public int[] getAttributesValues() {
    	int[] attributeValues = new int[5];
    	attributeValues[0] = getStrength();
    	attributeValues[1] = getIntelligence();
    	attributeValues[2] = getAgility();
    	attributeValues[3] = getMastery();
    	attributeValues[4] = getStamina();
    	return attributeValues;
    }
    public int chooseHighestAttribute() {
    	int[] attributeValues = getAttributesValues ();
    	int maxindex=0;
    	int maxValue=attributeValues[0];
    	for(int i =1;i<attributeValues.length;i++) {
    		if (attributeValues[i]>maxValue) {
    			maxValue = attributeValues[i];
    			maxindex = i;
    		}   		
    	}
    	return maxindex;
    }
}
