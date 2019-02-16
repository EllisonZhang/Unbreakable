package online.dwResources;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import online.configuration.TopTrumpsJSONConfiguration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import commandline.Card;
import commandline.GameController;
import commandline.GameModel;
import commandline.GameStatistics;
import commandline.Player;

@Path("/toptrumps") // Resources specified here should be hosted at http://localhost:7777/toptrumps
@Produces(MediaType.APPLICATION_JSON) // This resource returns JSON content
@Consumes(MediaType.APPLICATION_JSON) // This resource can take JSON content as input
/**
 * This is a Dropwizard Resource that specifies what to provide when a user
 * requests a particular URL. In this case, the URLs are associated to the
 * different REST API methods that you will need to expose the game commands to
 * the Web page.
 * 
 * Below are provided some sample methods that illustrate how to create REST API
 * methods in Dropwizard. You will need to replace these with methods that allow
 * a TopTrumps game to be controled from a Web page.
 */
public class TopTrumpsRESTAPI {

	/*
	 * We decided to create all the games necessary in a hash map, our session id
	 * will be a number, starting from 10
	 */
	HashMap<Integer, GameModel> games = new HashMap<Integer, GameModel>();
	int sessionID = 10;
	GameStatistics statistics = new GameStatistics();
	/**
	 * A Jackson Object writer. It allows us to turn Java objects into JSON strings
	 * easily.
	 */
	ObjectWriter oWriter = new ObjectMapper().writerWithDefaultPrettyPrinter();

	/**
	 * Constructor method for the REST API. This is called first. It provides a
	 * TopTrumpsJSONConfiguration from which you can get the location of the deck
	 * file and the number of AI players.
	 * 
	 * @param conf
	 */
	public TopTrumpsRESTAPI(TopTrumpsJSONConfiguration conf) {
	}

	@GET
	@Path("/loadStatistics")
	/*
	 * Retrieves the statistics of previous game in a hash table format and returns
	 * them as a json string
	 */
	public String loadStatistics() throws IOException {
		// loading statistics from the database and sending them to the client(s)
		statistics.retrievingGameStatistics();
		HashMap<String, Integer> stats = statistics.getStatsValues();
		String listAsJSONString = oWriter.writeValueAsString(stats);
		return listAsJSONString;
	}

	@GET
	@Path("/game")
	public void startGame(@QueryParam("Players") String players) throws IOException {
		// creating a new game with the number of players obtained from the query
		// parameters each time this url is triggered
		int playerNumber = Integer.parseInt(players);
		games.put(sessionID, new GameModel(playerNumber));
		GameController controller = new GameController(games.get(sessionID));
		sessionID++;

	}

	@GET
	@Path("/sessionID")
	public String sessionID() throws IOException {
		// Mapping each new game with an id for multiple users to play game at the same
		// time
		HashMap<String, Integer> sessionMap = new HashMap<String, Integer>();
		sessionMap.put("encryptionKey", sessionID);
		String listAsJSONString = oWriter.writeValueAsString(sessionMap);
		return listAsJSONString;

	}

	@GET
	@Path("/initialCards")
	public String initialCards(@QueryParam("session") String session) throws IOException {
		// this url sends the information for the cards at the beginning of the game
		int sessionID = Integer.parseInt(session);
		ArrayList<Player> gamePlayers = games.get(sessionID).getPlayers();
		HashMap<String, String> initialCards = new HashMap<String, String>();
		for (Player player : gamePlayers) {
			initialCards.put(player.getName(), player.getDeck().get(0).getName());
		}
		String listAsJSONString = oWriter.writeValueAsString(initialCards);
		return listAsJSONString;
	}

	@GET
	@Path("/round")
	public String round(@QueryParam("session") String session) throws IOException {
		// this url allows us to update the player's cards after each round
		int sessionID = Integer.parseInt(session);
		ArrayList<Player> gamePlayers = games.get(sessionID).getPlayers();
		HashMap<String, String> Cards = new HashMap<String, String>();
		for (Player player : gamePlayers) {
			if (player.getDeck().size() != 0) {
				Cards.put(player.getName(), player.getDeck().get(0).getName());
			} else {
				Cards.put(player.getName(), "Lost");
			}

		}
		String listAsJSONString = oWriter.writeValueAsString(Cards);
		return listAsJSONString;
	}

	@GET
	@Path("/choosingPlayer")
	public String choosingPlayer(@QueryParam("session") String session) throws IOException {
		// this url allows us to determine who is the choosing player at each round
		int sessionID = Integer.parseInt(session);
		HashMap<String, String> choosingPlayer = new HashMap<String, String>();
		String player = games.get(sessionID).getChoosingPlayer();
		choosingPlayer.put("Choosing player: ", player);
		String listAsJSONString = oWriter.writeValueAsString(choosingPlayer);
		return listAsJSONString;
	}

	@GET
	@Path("/deckSizes")
	public String deckSizes(@QueryParam("session") String session) throws IOException {
		// This url allows us to update the size of each player's deck after a round
		int sessionID = Integer.parseInt(session);
		HashMap<String, String> playersDecks = new HashMap<String, String>();
		for (Player p : games.get(sessionID).getPlayers()) {
			if (p.getDeck().size() != 0) {
				if (p.getDeck().size() == 40) {
					playersDecks.put(p.getName(), p.getDeck().size() + "");
					statistics.storingGameStatistics(games.get(sessionID).getDraws(), p.getName(),
							games.get(sessionID).getRoundNumber(), games.get(sessionID).getPlayers());
				} else {
					playersDecks.put(p.getName(), ": Number of Cards: <span> " + p.getDeck().size() + " </span>");
				}

			} else {
				playersDecks.put(p.getName(), ": <span>Lost</span>");
			}

		}
		String listAsJSONString = oWriter.writeValueAsString(playersDecks);
		return listAsJSONString;
	}

	@GET
	@Path("/finishGame")
	public String finishGame(@QueryParam("session") String session) throws IOException {
		// this method allows us to simulate the end of the game when the human user
		// looses.
		int sessionID = Integer.parseInt(session);
		if (games.get(sessionID).getPlayers().get(0).getDeck().size() == 0) {
			while (!games.get(sessionID).getGameOver()) {
				games.get(sessionID).round();
			}
			System.out.println("Hello world");
			statistics.storingGameStatistics(games.get(sessionID).getDraws(), games.get(sessionID).getGameWinner(),
					games.get(sessionID).getRoundNumber(), games.get(sessionID).getPlayers());
			System.out.println("hello kev");
			HashMap<String, String> playersDecks = new HashMap<String, String>();
			for (Player p : games.get(sessionID).getPlayers()) {
				if (p.getDeck().size() != 0) {
					playersDecks.put(p.getName(), ": <span>Winner</span>");

				} else {
					playersDecks.put(p.getName(), ": <span>Lost</span>");
				}

			}
			String listAsJSONString = oWriter.writeValueAsString(playersDecks);
			return listAsJSONString;
		}
		return null;
	}

	@GET
	@Path("/roundResult")
	public String roundResult(@QueryParam("Attribute") String attribute, @QueryParam("session") String session)
			throws IOException {
		// This url is used to trigger a new round in the game the round method returns
		// a boolean that allows us to determine whether the round was a draw.
		int sessionID = Integer.parseInt(session);
		boolean draw;
		if (games.get(sessionID).getChoosingPlayer().equals("You")) {
			draw = games.get(sessionID).round(attribute);
		} else {
			draw = games.get(sessionID).round();
		}
		// We then return a text message to display in the web page the result of the
		// round
		HashMap<String, String> attributeAndWinner = new HashMap<String, String>();
		if (!games.get(sessionID).getGameOver()) {
			if (draw) {
				attributeAndWinner.put("Attribute chosen: " + games.get(sessionID).getAttribute(),
						". The round was a draw, the size of the communal pile is: "
								+ games.get(sessionID).getCommunalPileSize() + ".");
			} else {
				attributeAndWinner.put("Attribute chosen: " + games.get(sessionID).getAttribute(),
						". Round winner: " + games.get(sessionID).getChoosingPlayer() + ".");
			}

		} else {
			attributeAndWinner.put("Game winner: ", games.get(sessionID).getChoosingPlayer());
		}

		String listAsJSONString = oWriter.writeValueAsString(attributeAndWinner);
		return listAsJSONString;
	}

}
