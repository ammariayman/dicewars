import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.dicewars.Exceptions.InsufficientDiceException;
import com.dicewars.Exceptions.NotOwnedException;
import com.dicewars.Exceptions.SamePlayerException;

public class Player {
	
	public static int idIndex = 1;
	public static Player currentPlayer;
//	public static List<Player> players = new ArrayList<Player>();
	
	private int id;
	private List<Territory> territories = new ArrayList<Territory>();
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Territory> getTerritories() {
		return territories;
	}

	public void setTerritories(List<Territory> territories) {
		this.territories = territories;
	}

	public int getMaxAdjacentTerritoryCount() {
		int totalDice = 0;
		//TODO: modifier pour avoir le nombre max des territoires dans le meme groupement
		for(Territory territory : this.getTerritories()) {
			totalDice += territory.getNumberOfDice();
		}
		return totalDice;
	}

	public Player() {
		super();
		this.id = Player.idIndex++;
	}
	
	public void attackTerritory(Territory attacker, Territory attacked) throws NotOwnedException, 
													  InsufficientDiceException,
//													  NotAdjacentException,
													  SamePlayerException {
		int i;
		Random rand = new Random(); 
		int attackerScore = 0, attackedScore = 0;
		
		if(territories == null) {
			return;
		}
		if(!attacker.getPlayer().equals(this)) {
			throw new NotOwnedException("Le territoire Attaquant ne vous appartient pas.");
		}
		if(attacker.getNumberOfDice() < 2) {
			throw new InsufficientDiceException("Le nombre de dés est insuffisant.");
		}
//		if(Math.abs(attacker.getPositionX() - attacked.getPositionX()) != 1
//		   || Math.abs(attacker.getPositionY() - attacked.getPositionY()) != 1) {
//			throw new NotAdjacentException("Les territoires ne sont pas adjacents.");
//		}
		if(attacker.getPlayer().equals(attacked.getPlayer())) {
			throw new SamePlayerException("Les territoires appartenant au même joueur ne peuvent pas s'entre attaquer.");
		}
		
		for(i = 0 ; i < attacker.getNumberOfDice() ; i++) {
			attackerScore += rand.nextInt(6) + 1;
		}
		for(i = 0 ; i < attacked.getNumberOfDice() ; i++) {
			attackedScore += rand.nextInt(6) + 1;
		}
		
		if(attackerScore > attackedScore) {
			attacked.setPlayer(this);
			attacked.setNumberOfDice(attacker.getNumberOfDice() - 1);
			attacker.setNumberOfDice(1);
		}
		else {
			attacker.setNumberOfDice(1);
		}
	}
	
	public void endTurn(Game game) {
		if(this.id < idIndex) {
			int nextPlayerId = this.id + 1;
			for(Player player : game.getPlayers()) {
				if(player.getId() == nextPlayerId) {
					currentPlayer = player;
					break;
				}
			}
		}
		else {
			for(Player player : game.getPlayers()) {
				if(player.getId() == 1) {
					currentPlayer = player;
					break;
				}
			}
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Player other = (Player) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
