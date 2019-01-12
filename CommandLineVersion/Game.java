import java.util.ArrayList;

public class Game {
	private int draws;
	private int rounds;
	private Player winner;
	private ArrayList<Player> players;
	
	public Game() {
		
	}
	
	public int getDraws() {
		return this.draws;
	}
	public void setDraws(int draws) {
		this.draws = draws;
	}
	public int getRounds() {
		return this.rounds;
	}
	public void setRounds(int rounds) {
		this.rounds = rounds;
	}

}
