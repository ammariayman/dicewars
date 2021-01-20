import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.dicewars.Exceptions.NoPlayersException;
import com.dicewars.Exceptions.TooManyPlayersException;

public class GamePanel extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static Color[] COLORS = new Color[] {
		Color.GREEN, 
		Color.RED,
		Color.YELLOW,
		Color.CYAN, 
		Color.MAGENTA, 
		Color.PINK, 
		Color.BLUE, 
		Color.GRAY, 
	};
	
	private Display display;
	private Game game;
	private int mapSize = 4;
	
	
	private JPanel centerPanel;
	private JPanel northPanel;
	private JPanel southPanel;
	private JPanel westPanel;
	private JPanel eastPanel;
	private JButton mapDisplay[][];
	
	public GamePanel(int playerCount, Display display) {
		int i, j;
		
		this.display = display;
		try {
			this.game = new Game(playerCount);
		}
		catch(NoPlayersException e) {
			System.out.println(e.getMessage());
		}
		catch(TooManyPlayersException e) {
			System.out.println(e.getMessage());
		}
		
		northPanel = new JPanel();
		GridLayout northPanelLayout = new GridLayout(1, 7);
		northPanel.setLayout(northPanelLayout);
		this.add(northPanel, BorderLayout.NORTH);
		
		centerPanel = new JPanel();
		mapDisplay = new JButton [mapSize][mapSize];
		GridLayout gameLayout = new GridLayout(mapSize, mapSize);
		centerPanel.setLayout(gameLayout);
		centerPanel.setPreferredSize(new Dimension(650, 550));
		centerPanel.setMaximumSize(new Dimension(650, 550));
		this.add(centerPanel, BorderLayout.CENTER);
		
		for(i = 0 ; i < mapSize ; i++) {
			for(j = 0 ; j < mapSize ; j++) {
				this.mapDisplay[i][j] = new JButton(String.valueOf(this.game.getMap().getTerritories()[i][j].getNumberOfDice()));
				int k = 0;
				for(Player player : this.game.getPlayers()) {
					if(player.equals(this.game.getMap().getTerritories()[i][j].getPlayer())) {
						this.mapDisplay[i][j].setBackground(GamePanel.COLORS[k]);
						break;
					}
					k++;
				}
				this.mapDisplay[i][j].addActionListener(this);
				this.add(this.mapDisplay[i][j]);
				
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	public Display getDisplay() {
		return display;
	}

	public void setDisplay(Display display) {
		this.display = display;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public int getMapSize() {
		return mapSize;
	}

	public void setMapSize(int mapSize) {
		this.mapSize = mapSize;
	}

}
