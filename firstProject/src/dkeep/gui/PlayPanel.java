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
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
				JPanel game_1 = new JPanel();
				game_1.setBounds(0, 102, 166, 140);
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
								Restart.setBounds(313, 30, 69, 20);
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
								moveButtons.setBounds(194, 113, 230, 118);
								
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
												layeredPane.setBounds(171, 171, 1, 1);
												frame.getContentPane().add(layeredPane);
										frame.getContentPane().add(moveButtons);
										moveButtons.setLayout(new MigLayout("", "[51px][][83px][][][57px][1px]", "[23px][23px][23px][23px]"));
										
												JLayeredPane layeredPane_1 = new JLayeredPane();
												moveButtons.add(layeredPane_1, "cell 6 0,alignx left,aligny center");
												moveButtons.add(btnUp, "cell 2 0,alignx center,aligny top");
																
																		btnRight = new JButton("Right");
																		btnRight.addActionListener(new ActionListener() {
																			public void actionPerformed(ActionEvent arg0) {

																				buttonPressed("R");
																			}
																		});
																		
																				btnLeft = new JButton("Left");
																				btnLeft.addActionListener(new ActionListener() {
																					public void actionPerformed(ActionEvent e) {
																						buttonPressed("L");
																					}
																				});
																				moveButtons.add(btnLeft, "cell 1 1,alignx left,aligny top");
																		moveButtons.add(btnRight, "cell 3 1,alignx left,aligny top");
																moveButtons.add(btnDown, "cell 2 2,alignx center,aligny top");
																
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
																		moveButtons.add(btnMainMenu, "cell 2 3,alignx left,aligny top");
				
						lblYou = new JLabel("You can start a new game");
						lblYou.setBounds(0, 247, 166, 14);
						frame.getContentPane().add(lblYou);
						
						numberOgres = new JTextField();
						numberOgres.setBounds(99, 30, 73, 20);
						frame.getContentPane().add(numberOgres);
						
						numberOgres.setColumns(10);
						
						JLabel lblNumberOfOgres = new JLabel("Number of ogres");
						lblNumberOfOgres.setBounds(10, 33, 90, 14);
						frame.getContentPane().add(lblNumberOfOgres);
						
						 JLabel lblGuardPersonality= new JLabel("Guard Personality");
						lblGuardPersonality.setBounds(10, 58, 90, 14);
						frame.getContentPane().add(lblGuardPersonality);
						
						guardPersonality = new JComboBox();
						guardPersonality.setModel(new DefaultComboBoxModel(new String[] {"Rookie", "Drunken", "Suspicious"}));
						guardPersonality.addItem("Rookie");
						guardPersonality.addItem("Drunken");
						guardPersonality.addItem("Suspicious");
						guardPersonality.setSelectedIndex(0);
						guardPersonality.setToolTipText("");
						guardPersonality.setBounds(99, 55, 73, 20);
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
		System.out.println(1);
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
}
