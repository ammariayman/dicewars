import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class MenuPanel extends JPanel implements ActionListener {
	
	private Display display;
	
	private JButton selectedPlayerNumber;
	private JPanel playersPanel;
	private JPanel playPanel;
	private JPanel northPanel;
	private JPanel westPanel;
	private JPanel eastPanel;
	private JButton playButton;
	private JButton players[];
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public MenuPanel(Display display) {
		this.display = display;
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(750, 650));
		this.setMaximumSize(new Dimension(750, 650));
		this.setBackground(java.awt.Color.cyan);
		
		playersPanel = new JPanel();
		GridLayout playersGridLayout = new GridLayout(1, 7);
		playersPanel.setLayout(playersGridLayout);
		this.add(playersPanel, BorderLayout.SOUTH);
		
		fillPlayersPanel();
						
		playPanel = new JPanel();
		playButton = new JButton();
		playButton.setPreferredSize(new Dimension(200, 100));
		playButton.setMaximumSize(new Dimension(200, 100));
		playButton.setText("Jouer");
		playButton.addActionListener(this);
		playPanel.add(playButton);
		this.add(playPanel, BorderLayout.CENTER);
		
		northPanel = new JPanel();
		northPanel.setPreferredSize(new Dimension(200, 200));
		northPanel.setMaximumSize(new Dimension(200, 200));
		westPanel = new JPanel();
		westPanel.setPreferredSize(new Dimension(200, 200));
		westPanel.setMaximumSize(new Dimension(200, 200));
		eastPanel = new JPanel();
		eastPanel.setPreferredSize(new Dimension(200, 200));
		eastPanel.setMaximumSize(new Dimension(200, 200));
		this.add(northPanel, BorderLayout.NORTH);
		this.add(westPanel, BorderLayout.WEST);
		this.add(eastPanel, BorderLayout.EAST);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton) e.getSource();
		boolean playButtonSelected = button == this.playButton;
		if(playButtonSelected) {
			int numberOfPlayers = Integer.valueOf(selectedPlayerNumber.getText());
			if(numberOfPlayers > 1 && numberOfPlayers < 9) {
				this.getDisplay().startGame(numberOfPlayers);
			}
		}
		else {
			int i, n = 7;
			for(i = 0 ; i < n ; i++) {
				if(button == players[i]) {
					this.selectedPlayerNumber = players[i];
					refreshPlayerButtons();
					break;
				}
			}
		}
	}
	
	private void fillPlayersPanel() {
		int i, n = 7;
		players = new JButton[n];
		for (i = 0  ; i < n ; i++) {
			players[i] = new JButton();
			players[i].setText(String.valueOf(i + 2));
			players[i].setPreferredSize(new Dimension(50, 50));
			players[i].setMaximumSize(new Dimension(50, 50));
			players[i].setBackground(java.awt.Color.gray);
			playersPanel.add(players[i]);
			players[i].addActionListener(this);
		}
		selectedPlayerNumber = players[0];
		
		refreshPlayerButtons();
	}
	
	public void refreshPlayerButtons() {
		int i, n = 7;
		for(i = 0 ; i < n ; i++) {
			if(this.players[i] == selectedPlayerNumber) {
//				int numberOfPlayers = Integer.valueOf(selectedPlayerNumber.getText());
				players[i].setBackground(java.awt.Color.red);
			}
			else {
				players[i].setBackground(java.awt.Color.gray);
			}
		}
	}

	public Display getDisplay() {
		return display;
	}

	public void setDisplay(Display display) {
		this.display = display;
	}

}
