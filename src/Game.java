import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import com.dicewars.Exceptions.NoPlayersException;
import com.dicewars.Exceptions.TooManyPlayersException;

public class Game {
	
	public static int DICE_COUNT = 45;
	
	private List<Player> players = new ArrayList<Player>();
	private Map map;
	
	public Game(int numPlayers) throws NoPlayersException, TooManyPlayersException {
		this.map = new Map();
		if(numPlayers < 2) {
			throw new NoPlayersException("Joueurs insuffisants.");
		}
		else if(numPlayers > 8) {
			throw new TooManyPlayersException("Le nombre maximum de joueurs est 8.");
		}
		int i;
		for(i = 0 ; i < numPlayers ; i++) {
			players.add(new Player());
		}
		
		assignTerritories();
		assignDice();
	}
	
	public void assignTerritories() {
		int i, j, playerId;
		Random rand = new Random();
		
		for(i = 0 ; i < map.getSize() ; i++) {
			for(j = 0 ; j < map.getSize() ; j++) {
				playerId = rand.nextInt(Player.idIndex - 1) + 1;
				for(Player player : players) {
					if(player.getId() == playerId) {
						map.getTerritories()[i][j] = new Territory(player);
						map.getTerritories()[i][j].setNumberOfDice(1);
						DICE_COUNT--;
						break;
					}
				}
			}
		}
	}
	
	public void assignDice() {
		int i, randomDiceCount, playerId;
		Random rand = new Random();
		java.util.Map<Player, Integer> playerDiceCount = new HashMap<Player, Integer>();
		int rest = DICE_COUNT % players.size();
		
		for(Player player : players) {
			playerDiceCount.put(player, DICE_COUNT/ (Player.idIndex - 1));
		}
		
		for(i = 0 ; i < rest ; i++) {
			playerId = rand.nextInt(Player.idIndex - 1) + 1;
			for(Player player : players) {
				if(player.getId() == playerId) {
					playerDiceCount.put(player, playerDiceCount.get(player) + 1);
				}
			}
		}
		
		for(Player player : players) {
			for(Territory territory : player.getTerritories()) {
				if(playerDiceCount.get(player) < 1) {
					break;
				}
				randomDiceCount = rand.nextInt(8);
				while(randomDiceCount > playerDiceCount.get(player)) {
					randomDiceCount = rand.nextInt(8);
				}
				territory.addDice(randomDiceCount);
			}
		}
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}
}
