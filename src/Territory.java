
public class Territory {
	
	public static int idIndex = 1;
	public static int maxDice = 8;
	
	private int id;
	private Player player;
	private int numberOfDice = 0;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	public int getNumberOfDice() {
		return numberOfDice;
	}
	public void setNumberOfDice(int numberOfDice) {
		this.numberOfDice = numberOfDice;
	}
	public Territory(Player player) {
		super();
		this.id = Territory.idIndex++;
		this.player = player;
		this.player.getTerritories().add(this);
	}
	
	public void addDice(int n) {
		this.numberOfDice += n;
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
		Territory other = (Territory) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
}
