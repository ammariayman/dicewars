
public class Map {
	
	private int size = 4;
	private Territory[][] territories = new Territory[size][size];
	
	public Map() {
		super();
	}

	public Map(int size) {
		super();
		this.size = size;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public Territory[][] getTerritories() {
		return territories;
	}

	public void setTerritories(Territory[][] territories) {
		this.territories = territories;
	}

}
