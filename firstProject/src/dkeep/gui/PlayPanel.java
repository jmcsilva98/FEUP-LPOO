package dkeep.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.FlowLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import dkeep.cli.GuiInteraction;
import dkeep.cli.UserInteraction;
import dkeep.logic.Hero;
import dkeep.logic.Ogre;

import javax.swing.JTextArea;
import javax.swing.DefaultComboBoxModel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import net.miginfocom.swing.MigLayout;
import java.awt.Font;
import javax.swing.JLayeredPane;

public class PlayPanel {

	public JFrame frame;
	private String[][] map;
	private GamePanel gameArea;
	public GuiInteraction game;
	private JButton Restart;
	private JLabel lblYou;
	private JButton btnLeft;
	private JButton btnUp;
	private JButton btnRight;
	private JButton btnDown;
	private String guard="";
	private int ogresNumber=1;
	private JTextField numberOgres;
	private JComboBox guardPersonality;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {

				try {
					PlayPanel window = new PlayPanel();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws IOException 
	 */
	public PlayPanel() throws IOException {
		this.game=new GuiInteraction();
		initialize();
	}

	/** 
	 * Initialize the contents of the frame.
	 * @throws IOException 
	 */
	private void initialize() throws IOException {
		GamePanel.loadImages();
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 313);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel game_1 = new JPanel();
		game_1.setBounds(7, 62, 191, 140);
		game_1.setLayout(new MigLayout("", "[220px]", "[148px]"));

		gameArea = new GamePanel();
		gameArea.setFont(new Font("Courier New", Font.PLAIN, 13));
		gameArea.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				pressedKey(e);
			}
		});
		gameArea.requestFocusInWindow();
		frame.getContentPane().setLayout(null);

		Restart = new JButton("Restart");
		Restart.setBounds(307, 7, 73, 25);
		frame.getContentPane().add(Restart);
		//btnNewGame.setEnabled(false);
		Restart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				gameArea.requestFocusInWindow();
			}
		});
		game_1.add(gameArea, "cell 0 0,grow");
		frame.getContentPane().add(game_1);

		JPanel moveButtons = new JPanel();
		moveButtons.setBounds(200, 69, 232, 118);

		btnUp = new JButton("Up");
		btnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				buttonPressed("U");
			}
		});

		btnDown = new JButton("Down");
		btnDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonPressed("D");
			}
		});

		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(206, 132, 1, 1);
		frame.getContentPane().add(layeredPane);
		frame.getContentPane().add(moveButtons);
		moveButtons.setLayout(new MigLayout("", "[51px][][][83px][][][][][][57px][1px]", "[23px][23px][23px][23px]"));

		JLayeredPane layeredPane_1 = new JLayeredPane();
		moveButtons.add(layeredPane_1, "cell 10 0,alignx left,aligny center");
		moveButtons.add(btnUp, "cell 3 0,alignx center,aligny top");

		btnLeft = new JButton("Left");
		btnLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonPressed("L");
			}
		});
		moveButtons.add(btnLeft, "cell 2 1,alignx left,aligny top");

		btnRight = new JButton("Right");
		btnRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				buttonPressed("R");
			}
		});
		moveButtons.add(btnRight, "cell 4 1,alignx left,aligny top");
		moveButtons.add(btnDown, "cell 3 2,alignx center,aligny top");

		JButton btnMainMenu = new JButton("Main Menu");
		btnMainMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int exitPressed = JOptionPane.showConfirmDialog(null, "Are you sure that you want to go to the main menu?", "Exit", JOptionPane.YES_NO_OPTION);
				MenuPanel window = null;
				try {
					window = new MenuPanel();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (exitPressed==JOptionPane.YES_OPTION)
					window.frame.setVisible(true);
				frame.setVisible(false);

			}
		});
		moveButtons.add(btnMainMenu, "cell 3 3,alignx left,aligny top");

		lblYou = new JLabel("You can start a new game");
		lblYou.setBounds(7, 206, 191, 16);
		frame.getContentPane().add(lblYou);

		JLabel lblNumberOfOgres = new JLabel("Number of ogres");
		lblNumberOfOgres.setBounds(7, 12, 96, 16);
		frame.getContentPane().add(lblNumberOfOgres);

		JLabel lblGuardPersonality= new JLabel("Guard Personality");
		lblGuardPersonality.setBounds(7, 39, 100, 16);
		frame.getContentPane().add(lblGuardPersonality);

		guardPersonality = new JComboBox();
		guardPersonality.setBounds(111, 36, 87, 22);
		guardPersonality.setModel(new DefaultComboBoxModel(new String[] {"Rookie", "Drunken", "Suspicious"}));
		guardPersonality.setSelectedIndex(0);
		guardPersonality.setToolTipText("");
		frame.getContentPane().add(guardPersonality);
		switch (this.guard) {
		case "Rookie":
			guardPersonality.setSelectedIndex(0);
			setGuard("Rookie");
			break;
		case "Drunken":
			guardPersonality.setSelectedIndex(1);
			setGuard("Drunken");
			break;
		case "Suspicious":
			guardPersonality.setSelectedIndex(2);
			setGuard("Suspicious");
			break;
		default:
			guardPersonality.setSelectedIndex(0);
		}

		guardPersonality.setEnabled(false);

		numberOgres = new JTextField();
		numberOgres.setBounds(107, 7, 91, 25);
		frame.getContentPane().add(numberOgres);

		numberOgres.setColumns(10);

		guardPersonality.setEnabled(true);

		newGamePressed();
	}

	private void newGamePressed() {
		this.game.start(guard,1);
		guardPersonality.setEnabled(false);
		gameArea.setMaze(game.getGame().getMap());
		lblYou.setText("You can play now");
	}
	private void buttonPressed(String move) {
		if (!game.checkGameStatus(move)) {
			JOptionPane.showMessageDialog(frame, "GAME OVER!");
			System.exit(0);
		}
		if (game.getGame().gameWon) {
			JOptionPane.showMessageDialog(frame, "GAME WON!");
			System.exit(0);
		}

		gameArea.setMaze(game.getGame().getMap());
		gameArea.requestFocusInWindow();
	}

	public void pressedKey(KeyEvent e) {
		switch(e.getKeyCode()){

		case KeyEvent.VK_LEFT:
			buttonPressed("L");
			break;
		case KeyEvent.VK_RIGHT:  
			buttonPressed("R");
			break;
		case KeyEvent.VK_UP:  
			buttonPressed("U");
			break;
		case KeyEvent.VK_DOWN: 
			buttonPressed("D");
			break;
		} 
	}
	public void setGuard(String guard) {
		this.guard=guard;
	}

	public void setOgresNumber(int ogresNumber) {
		this.ogresNumber=ogresNumber;
	}
	public void editableLevel(String[][]map) {
		this.map=map;
		game.getGame().setMap(map);
		for (int i =0;i<map.length;i++) {
			for (int j = 0; j<map.length;j++)
				switch (map[i][j]) {
				case "H":
					Hero hero =new Hero();
					hero.setX(i);
					hero.setY(j);
					game.getGame().setHero(hero);
					break;
				case "O":
					Ogre ogre =new Ogre();
					ogre.setX(i);
					ogre.setY(j);
					game.getGame().setOgre(ogre);
					break;
				}
		}
		gameArea.setMaze(map);
	}
}
