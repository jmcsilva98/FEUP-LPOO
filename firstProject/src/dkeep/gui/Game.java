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

import javax.swing.JTextArea;
import javax.swing.DefaultComboBoxModel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import net.miginfocom.swing.MigLayout;
import java.awt.Font;
import javax.swing.JLayeredPane;

public class Game {

	public JFrame frame;
	private JTextField numberOgres;
	private GamePanel gameArea;
	public GuiInteraction game;
	private JComboBox guardPersonality;
	private JButton btnNewGame;
	private JLabel lblYou;
	private JButton btnLeft;
	private JButton btnUp;
	private JButton btnRight;
	private JButton btnDown;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {

				try {
					Game window = new Game();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Game() {

		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel configurations = new JPanel();

		JPanel game_1 = new JPanel();

		JPanel newGame = new JPanel();

		JPanel moveButtons = new JPanel();

		lblYou = new JLabel("You can start a new game");
		GridBagLayout gbl_configurations = new GridBagLayout();
		gbl_configurations.columnWidths = new int[]{35, 85, 74, 0};
		gbl_configurations.rowHeights = new int[]{20, 20, 0};
		gbl_configurations.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_configurations.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		configurations.setLayout(gbl_configurations);

		JLabel lblNumberOfOgres = new JLabel("Number of ogres");
		GridBagConstraints gbc_lblNumberOfOgres = new GridBagConstraints();
		gbc_lblNumberOfOgres.anchor = GridBagConstraints.WEST;
		gbc_lblNumberOfOgres.insets = new Insets(0, 0, 5, 5);
		gbc_lblNumberOfOgres.gridx = 1;
		gbc_lblNumberOfOgres.gridy = 0;
		configurations.add(lblNumberOfOgres, gbc_lblNumberOfOgres);

		numberOgres = new JTextField();
		numberOgres.setColumns(10);
		GridBagConstraints gbc_numberOgres = new GridBagConstraints();
		gbc_numberOgres.anchor = GridBagConstraints.NORTH;
		gbc_numberOgres.fill = GridBagConstraints.HORIZONTAL;
		gbc_numberOgres.insets = new Insets(0, 0, 5, 0);
		gbc_numberOgres.gridx = 2;
		gbc_numberOgres.gridy = 0;
		configurations.add(numberOgres, gbc_numberOgres);

		JLabel lblGuardPersonality = new JLabel("Guard personality");
		GridBagConstraints gbc_lblGuardPersonality = new GridBagConstraints();
		gbc_lblGuardPersonality.anchor = GridBagConstraints.WEST;
		gbc_lblGuardPersonality.insets = new Insets(0, 0, 0, 5);
		gbc_lblGuardPersonality.gridx = 1;
		gbc_lblGuardPersonality.gridy = 1;
		configurations.add(lblGuardPersonality, gbc_lblGuardPersonality);

		guardPersonality = new JComboBox();
		guardPersonality.setMaximumRowCount(10);
		guardPersonality.setEditable(true);
		guardPersonality.setModel(new DefaultComboBoxModel(new String[] {"Rookie", "Drunken", "Suspicious"}));
		guardPersonality.setSelectedIndex(-1);
		GridBagConstraints gbc_guardPersonality = new GridBagConstraints();
		gbc_guardPersonality.anchor = GridBagConstraints.NORTH;
		gbc_guardPersonality.fill = GridBagConstraints.HORIZONTAL;
		gbc_guardPersonality.gridx = 2;
		gbc_guardPersonality.gridy = 1;
		configurations.add(guardPersonality, gbc_guardPersonality);

		btnUp = new JButton("Up");
		btnUp.setEnabled(false);
		btnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				buttonPressed("U");
			}
		});

		btnLeft = new JButton("Left");
		btnLeft.setEnabled(false);
		btnLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonPressed("L");
			}
		});
		
		btnDown = new JButton("Down");
		btnDown.setEnabled(false);
		btnDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonPressed("D");
			}
		});
		GridBagLayout gbl_newGame = new GridBagLayout();
		gbl_newGame.columnWidths = new int[]{83, 0};
		gbl_newGame.rowHeights = new int[]{23, 0};
		gbl_newGame.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_newGame.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		newGame.setLayout(gbl_newGame);
		frame.getContentPane().setLayout(new MigLayout("", "[240px][152px]", "[65px][160px][14px]"));
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
		game_1.add(gameArea, "cell 0 0,grow");
		frame.getContentPane().add(game_1, "cell 0 1,grow");

		JLayeredPane layeredPane = new JLayeredPane();
		frame.getContentPane().add(layeredPane, "flowx,cell 1 1");
		frame.getContentPane().add(moveButtons, "cell 1 1");
		moveButtons.setLayout(new MigLayout("", "[51px][][][24px][57px]", "[23px][23px][23px][23px]"));
		moveButtons.add(btnLeft, "cell 0 1");

		JLayeredPane layeredPane_1 = new JLayeredPane();
		moveButtons.add(layeredPane_1, "flowx,cell 3 0");
		moveButtons.add(btnUp, "cell 0 0 5 1,alignx center,aligny top");
		
				btnRight = new JButton("Right");
				btnRight.setEnabled(false);
				btnRight.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {

						buttonPressed("R");
					}
				});
				moveButtons.add(btnRight, "cell 1 1,alignx left,aligny top");
		moveButtons.add(btnDown, "cell 0 2 5 1,alignx center,aligny top");
						
								JButton btnMainMenu = new JButton("Main Menu");
								btnMainMenu.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent arg0) {
										int exitPressed = JOptionPane.showConfirmDialog(null, "Are you sure that you want to go to the main menu?", "Exit", JOptionPane.YES_NO_OPTION);
										 MenuPanel window = new MenuPanel();
										if (exitPressed==JOptionPane.YES_OPTION)
											window.frame.setVisible(true);
											frame.setVisible(false);
											
									}
								});
								moveButtons.add(btnMainMenu, "cell 0 3");
		frame.getContentPane().add(configurations, "cell 0 0");
		frame.getContentPane().add(newGame, "cell 1 0,alignx center,aligny bottom");

		btnNewGame = new JButton("New Game");
		//btnNewGame.setEnabled(false);
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				newGamePressed();
				gameArea.requestFocusInWindow();
			}
		});
		GridBagConstraints gbc_btnNewGame = new GridBagConstraints();
		gbc_btnNewGame.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnNewGame.gridx = 0;
		gbc_btnNewGame.gridy = 0;
		newGame.add(btnNewGame, gbc_btnNewGame);
		frame.getContentPane().add(lblYou, "cell 0 2,alignx left,aligny top");
	}

	private void newGamePressed() {

		game=new GuiInteraction();
		String guard;
		int number=0;
		try{
			number=Integer.parseInt(numberOgres.getText());

		} catch(NumberFormatException nfe) {

			JOptionPane.showMessageDialog(frame, "You have to insert the number of ogres!");
			return;
		}
		if ( number >5 || number <0) {
			JOptionPane.showMessageDialog(frame, "You have to insert a positive number less than 5!");
			return;
		}
		try {
			guard = guardPersonality.getSelectedItem().toString();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(frame, "You have to select the personality of guard!");
		}
		
		game.start(guardPersonality.getSelectedItem().toString(),Integer.parseInt(numberOgres.getText()));
		numberOgres.setEnabled(false);
		guardPersonality.setEnabled(false);
		btnNewGame.setEnabled(false);
		btnUp.setEnabled(true);
		btnDown.setEnabled(true);
		btnRight.setEnabled(true);
		btnLeft.setEnabled(true);
		game.getGame().setOgres(number);
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

}