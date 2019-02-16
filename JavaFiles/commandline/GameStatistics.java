package commandline;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class GameStatistics {
	// This class handles posting to and retrieving information from the database 
	private HashMap<String, Integer> statsValues = new HashMap<String, Integer>();
	Connection connection = null;

	public GameStatistics() {
		dbConnection();
	}
	
	public void storingGameStatistics(int draws, String winner, int roundNumbers, ArrayList<Player> players) {
		// storing the statistics of a game to the database
		int players_number = players.size();
		int[] roundsWon = new int[5];
		for(int i =0; i < roundsWon.length; i++) {
			if (i < players.size()) 
				roundsWon[i] = players.get(i).roundsWon;
			else
				roundsWon[i] = 0;
		}
		String query = "INSERT INTO games ("
			    + " rounds_number,"
			    + " draws,"
			    + " players_number,"
			    + " AI1_rounds_won,"
			    + " AI2_rounds_won,"
			    + " AI3_rounds_won,"
			    + " AI4_rounds_won,"
			    + " human_rounds_won,"
			    + " game_winner ) VALUES ("
			    + "  ?, ?, ?, ?, ?, ?, ?, ?, ?)"; 
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, roundNumbers);
			statement.setInt(2, draws);
			statement.setInt(3, players_number);
			statement.setInt(4, roundsWon[1]);
			statement.setInt(5, roundsWon[2]);
			statement.setInt(6, roundsWon[3]);
			statement.setInt(7, roundsWon[4]);
			statement.setInt(8, roundsWon[0]);
			statement.setString(9, winner);
			statement.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
 
	public void retrievingGameStatistics() {
		// retrieving the statistics of a game from the database
		Statement statement = null;
		String numberOfGames = "Number of games played overall: ";
		try {
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT count(g_id) as count FROM games");
			while (rs.next()) {
				int result = rs.getInt("count");
				System.out.println(numberOfGames + result);
				statsValues.put(numberOfGames, result);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String computerWins = "Number of times the computer has won: ";
		try {
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(
					"SELECT count(g_id) as count FROM games WHERE game_winner like 'AI%'");
			while (rs.next()) {
				int result = rs.getInt("count");
				System.out.println(computerWins + result);
				statsValues.put(computerWins, result);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String averageDraws = "Average number of draws per game: ";
		try {
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT AVG(draws) as average FROM games");
			while (rs.next()) {
				int result = rs.getInt("average");
				System.out.println(averageDraws + result);
				statsValues.put(averageDraws, result);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String largestNumberRounds = "The largest number of round played in a single game: ";
		try {
			statement = connection.createStatement();
			ResultSet rs = statement
					.executeQuery("SELECT rounds_number as rounds FROM games ORDER BY rounds_number DESC LIMIT 1");
			while (rs.next()) {
				int result = rs.getInt("rounds");
				System.out.println(largestNumberRounds + result);
				statsValues.put(largestNumberRounds, result);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void dbConnection() {
		
		// Creating a statement object for our query
		// load the JDBC driver
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Could not find JDBC driver");
			e.printStackTrace();
			return;
		}
		// the driver is loaded
		// proceed with a database connection
		connection = null;
		// connect to the yacata.dcs.gla.ac.uk server, on port:5432, with
		try {
			connection = DriverManager.getConnection("jdbc:postgresql://yacata.dcs.gla.ac.uk:5432/", "m_18_2416468k",
					"2416468k");
		} catch (SQLException e) {
			System.out.println("Connection Failed!");
			e.printStackTrace();
			return;
		}
	}
	
	public HashMap<String, Integer> getStatsValues() { 
		// allows us to get the stats from the database in a nice format
		return statsValues;
	}
}
