import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Display implements ActionListener {
	
	
	JFrame frame;
	JPanel cards;
	MenuPanel menuPanel;
	GamePanel gamePanel;
	CardLayout cardLayout;
	
	private Game game;
	
	public Display() {
		
		frame = new JFrame("Dice Wars");	
		frame.setSize(750, 650);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.cardLayout = new CardLayout();
		cards = new JPanel(this.cardLayout);
		
		menuPanel = new MenuPanel(this);
		cards.add(menuPanel, "main");
		
//		gamePanel = new JPanel();
//		GridLayout gameLayout = new GridLayout(4, 4);
//		gamePanel.setLayout(gameLayout);
//		gamePanel.setPreferredSize(new Dimension(750, 650));
//		gamePanel.setMaximumSize(new Dimension(750, 650));
//		cards.add(gamePanel, "game");
		
		frame.getContentPane().add(cards);
		
//		//Scores
//		JPanel panneau3 = new JPanel();
//		GridLayout disposition3 = new GridLayout(1, 2); 
//		panneau3.setLayout(disposition3);
//		panneau3.setSize(850, 1000);
//		fenetre1.add("North",panneau3);
//				JButton score1 = new JButton("Joueur 1 : 230");
//		JButton score2 = new JButton("Joueur 2 : 145");
//		panneau3.add(score1);
//		panneau3.add(score2);
//				
//		tableauBoutons();
//		
////		plateau.remplirCases();
//		remplirCases(plateau.plateau);
	}
	
	public void startGame(int playerCount) {
		
//		this.cardLayout.next(cards);
		this.gamePanel = new GamePanel(playerCount, this);
		cards.add(gamePanel, "game");
		this.cardLayout.show(cards, "game");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}
}
