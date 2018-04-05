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
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
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
import dkeep.logic.*;
import dkeep.logic.Character;

import javax.swing.JTextArea;
import javax.swing.DefaultComboBoxModel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import net.miginfocom.swing.MigLayout;
import java.awt.Font;
import javax.swing.JLayeredPane;
import javax.swing.JSplitPane;

public class PlayPanel implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JFrame frame;
	private String[][] map;
	private GamePanel gameArea;
	public GuiInteraction game;
	private JButton mainMenu;
	private JLabel lblYou;
	private JButton btnLeft;
	private JButton btnUp;
	private JButton btnRight;
	private JButton btnDown;
	static String guard="Rookie";
	private JPanel settings;
	public  int ogresNumber=1;
	private JTextField numberOgres;
	private JComboBox guardPersonality;
	private JPanel exitButtons;
	private JPanel moveButtons;
	/**
	 * Launch the application.
	 * @throws IOException 
	 */
	public PlayPanel(String guard,int ogresNumber,GuiInteraction game) throws IOException {
		this.guard=guard;
		this.ogresNumber=ogresNumber;
		this.game=game;
		initialize();

	}

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
	public PlayPanel(String guard,int ogres) throws IOException {

		this.guard=guard;
		this.ogresNumber=ogres;
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
		frame.getContentPane().setLayout(new GridLayout(2, 2));

		initializeSettings();
		initializeMoveButtons();
		initializeExitButton();

		getFocus();
		gameArea.requestFocusInWindow();
		JPanel game_1 = new JPanel();

		newGamePressed();
	}
	public void getFocus() {
		lblYou = new JLabel("You can start a new game");
		GridBagConstraints gbc_lblYou = new GridBagConstraints();
		gbc_lblYou.insets = new Insets(0, 0, 5, 0);
		gbc_lblYou.gridx = 0;
		gbc_lblYou.gridy = 5;
		exitButtons.add(lblYou, gbc_lblYou);
		gameArea.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				pressedKey(e);
			}
		});
	}
	private void initializeExitButton() {
		exitButtons= new JPanel();
		frame.getContentPane().add(exitButtons);
		GridBagLayout gbl_exitButtons = new GridBagLayout();
		gbl_exitButtons.columnWidths = new int[]{217, 0};
		gbl_exitButtons.rowHeights = new int[]{30, 0, 0, 22, 0, 23, 0, 0};
		gbl_exitButtons.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_exitButtons.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		exitButtons.setLayout(gbl_exitButtons);
		gameArea = new GamePanel();
		gameArea.setFont(new Font("Courier New", Font.PLAIN, 13));
		gameArea.setLayout(new GridLayout(1, 0, 0, 0));
		frame.getContentPane().add(gameArea);
		initializeMainMenuButton();
	}
	public void initializeMainMenuButton() {
		mainMenu = new JButton("Main Menu");
		GridBagConstraints gbc_Restart = new GridBagConstraints();
		gbc_Restart.insets = new Insets(0, 0, 5, 0);
		gbc_Restart.gridx = 0;
		gbc_Restart.gridy = 2;
		exitButtons.add(mainMenu, gbc_Restart);
		mainMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuPanel other=null;
				try {
					other = new MenuPanel();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				other.frame.setVisible(true);
				frame.setVisible(false);
				gameArea.requestFocusInWindow();
			}
		});
	}
	private void initializeMoveButtons() {
		moveButtons = new JPanel();
		GridBagLayout gbl_moveButtons = new GridBagLayout();
		gbl_moveButtons.columnWidths = new int[]{101, 0, 0, 0, 106, 0};
		gbl_moveButtons.rowHeights = new int[]{38, 0, 0, 0, 0, 0, 0, 43, 0};
		gbl_moveButtons.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_moveButtons.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		moveButtons.setLayout(gbl_moveButtons);
		frame.getContentPane().add(moveButtons);

		initializeUpDownButtons();
		initializeLeftRightButtons();



	}
	private void initializeLeftRightButtons() {
		initializeLeftButton();
		initializeRightButton();
	}
	public void initializeLeftButton() {
		btnLeft = new JButton("Left");
		GridBagConstraints gbc_btnLeft = new GridBagConstraints();
		gbc_btnLeft.fill = GridBagConstraints.BOTH;
		gbc_btnLeft.insets = new Insets(0, 0, 5, 5);
		gbc_btnLeft.gridx = 1;
		gbc_btnLeft.gridy = 4;
		moveButtons.add(btnLeft, gbc_btnLeft);
		btnLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonPressed("L");
			}
		});
	}
	public void initializeRightButton() {
		btnRight = new JButton("Right");
		GridBagConstraints gbc_btnRight = new GridBagConstraints();
		gbc_btnRight.insets = new Insets(0, 0, 5, 5);
		gbc_btnRight.fill = GridBagConstraints.BOTH;
		gbc_btnRight.gridx = 3;
		gbc_btnRight.gridy = 4;
		moveButtons.add(btnRight, gbc_btnRight);
		btnRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				buttonPressed("R");
			}
		});
	}
	private void initializeUpDownButtons() {
		initializeUpButton();

		initializeDownButton();
	}
	public void initializeDownButton() {
		btnDown = new JButton("Down");
		GridBagConstraints gbc_btnDown = new GridBagConstraints();
		gbc_btnDown.insets = new Insets(0, 0, 5, 5);
		gbc_btnDown.fill = GridBagConstraints.BOTH;
		gbc_btnDown.gridx = 2;
		gbc_btnDown.gridy = 5;
		moveButtons.add(btnDown, gbc_btnDown);
		btnDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonPressed("D");
			}
		});
	}
	public void initializeUpButton() {
		btnUp = new JButton("Up");
		GridBagConstraints gbc_btnUp = new GridBagConstraints();
		gbc_btnUp.fill = GridBagConstraints.BOTH;
		gbc_btnUp.insets = new Insets(0, 0, 5, 5);
		gbc_btnUp.gridx = 2;
		gbc_btnUp.gridy = 3;
		moveButtons.add(btnUp, gbc_btnUp);
		btnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				buttonPressed("U");
			}
		});
	}
	private void initializeSettings() {
		settings = new JPanel();
		GridBagLayout gbl_settings = new GridBagLayout();
		gbl_settings.columnWidths = new int[]{108, 0, 108, 0};
		gbl_settings.rowHeights = new int[]{68, 0, 0, 0, 0, 68, 0};
		gbl_settings.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_settings.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		settings.setLayout(gbl_settings);
		initializeGuardPersonality();
		initializeNumberOgresTextField();

	}
	private void initializeGuardPersonality() {
		JLabel lblGuardPersonality = new JLabel("Guard Personality");
		GridBagConstraints gbc_lblGuardPersonality = new GridBagConstraints();
		gbc_lblGuardPersonality.fill = GridBagConstraints.BOTH;
		gbc_lblGuardPersonality.insets = new Insets(0, 0, 5, 5);
		gbc_lblGuardPersonality.gridx = 0;
		gbc_lblGuardPersonality.gridy = 2;
		settings.add(lblGuardPersonality, gbc_lblGuardPersonality);
		guardPersonality = new JComboBox();
		guardPersonality.setModel(new DefaultComboBoxModel(new String[] {"Rookie", "Drunken", "Suspicious",""}));

		selectGuardPersonality();
		GridBagConstraints gbc_guardPersonality = new GridBagConstraints();
		gbc_guardPersonality.gridwidth = 2;
		gbc_guardPersonality.insets = new Insets(0, 0, 5, 0);
		gbc_guardPersonality.fill = GridBagConstraints.HORIZONTAL;
		gbc_guardPersonality.gridx = 2;
		gbc_guardPersonality.gridy = 2;
		settings.add(guardPersonality, gbc_guardPersonality);

		guardPersonality.setEnabled(false);
		frame.getContentPane().add(settings);
	}
	public void selectGuardPersonality() {
		if (guard==null) {
			guardPersonality.setSelectedIndex(3);
			return;
		}
		switch(guard) {
		case "Rookie":
			guardPersonality.setSelectedIndex(0);
			break;
		case "Drunken":
			guardPersonality.setSelectedIndex(1);
			break;
		case "Suspicious":
			guardPersonality.setSelectedIndex(2);
			break;
		default:
			guardPersonality.setSelectedIndex(0);
			break;

		}
	}
	private void initializeNumberOgresTextField() {
		JLabel lblNumberOfOgres = new JLabel("Number of ogres");
		GridBagConstraints gbc_lblNumberOfOgres = new GridBagConstraints();
		gbc_lblNumberOfOgres.fill = GridBagConstraints.BOTH;
		gbc_lblNumberOfOgres.insets = new Insets(0, 0, 5, 5);
		gbc_lblNumberOfOgres.gridx = 0;
		gbc_lblNumberOfOgres.gridy = 1;
		settings.add(lblNumberOfOgres, gbc_lblNumberOfOgres);

		numberOgres = new JTextField();

		numberOgres.setEnabled(false);
		GridBagConstraints gbc_numberOgres = new GridBagConstraints();
		gbc_numberOgres.gridwidth = 2;
		gbc_numberOgres.fill = GridBagConstraints.BOTH;
		gbc_numberOgres.insets = new Insets(0, 0, 5, 0);
		gbc_numberOgres.gridx = 2;
		gbc_numberOgres.gridy = 1;
		settings.add(numberOgres, gbc_numberOgres);
		numberOgres.setColumns(10);
		numberOgres.setText(""+ogresNumber);
	}

	private void newGamePressed() {
		this.game.start(guard,ogresNumber);
		guardPersonality.setEnabled(false);
		gameArea.setMaze(game.getGame().getMap());
		lblYou.setText("You can play now");
	}
	private void buttonPressed(String move) {

		if (!game.checkGameStatus(move)) {
			System.out.println("GAME OVER !!!");
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
		int ogresNumber=0;
		game.getGame().setLevel(0);
		game.getGame().setMap(map);
		System.out.println(game.getGame().getLevel());
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
					Character club= new Character();
					ogre.setX(i);
					ogre.setY(j);
					ogre.updatePosition();

					club.setX(i);
					club.setY(j-1);
					club.updatePosition();

					ogre.setClub(club);

					game.getGame().setOgre(ogre);
					ogresNumber++;
					break;
				default:
					break;
				}
		}
		gameArea.setMaze(map);
	}
	public void saveGameDataToFile(File file) {   

		try {   
			FileOutputStream fileStream = new FileOutputStream(file);   
			ObjectOutputStream objectStream = new ObjectOutputStream(fileStream);   

			objectStream.writeObject(game.getGame().getHero());   
			objectStream.writeObject(game.getGame().getMap());   
			objectStream.writeObject(game.getGame().getGuard());   
			objectStream.writeObject(game.getGame().getOgres());   
			objectStream.writeObject(game.getGame().getLevel()); 

			objectStream.close();   
			fileStream.close();   

			JOptionPane.showConfirmDialog(frame, 
					"Save game state successfully.", 
					"Game",   
					JOptionPane.DEFAULT_OPTION);   
		} catch (Exception e) {   
			JOptionPane.showConfirmDialog(frame, 
					e.toString() + "\nFail to save game state.",   
					"Snake Game", 
					JOptionPane.DEFAULT_OPTION);   
		}   
	}
	public void loadGameDataFromFile(File file) throws ClassNotFoundException, IOException{   

		FileInputStream fileStream = new FileInputStream(file);   
		ObjectInputStream objectStream = new ObjectInputStream(fileStream);   
		Hero savedHero = (Hero) objectStream.readObject();   
		String[][] savedMap = (String[][]) objectStream.readObject();
		Guard savedGuard = (Guard) objectStream.readObject();     
		ArrayList<Ogre> savedOgres = (ArrayList<Ogre>) objectStream.readObject();   
		int savedLevel = (int) objectStream.readObject(); 

		objectStream.close();   
		fileStream.close(); 

	}
}