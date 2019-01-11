
public class Card {
	
	private String description;
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
}
