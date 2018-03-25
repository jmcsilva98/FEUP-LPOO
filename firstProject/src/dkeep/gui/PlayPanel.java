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

		JPanel moveButtons = new JPanel();

		lblYou = new JLabel("You can start a new game");

		btnUp = new JButton("Up");
		btnUp.setEnabled(false);
		btnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				buttonPressed("U");
			}
		});
		
		btnDown = new JButton("Down");
		btnDown.setEnabled(false);
		btnDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonPressed("D");
			}
		});
		frame.getContentPane().setLayout(new MigLayout("", "[240px][152px]", "[65px][][160px][14px]"));
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
		
				Restart = new JButton("Restart");
				frame.getContentPane().add(Restart, "cell 1 0");
				//btnNewGame.setEnabled(false);
				Restart.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						newGamePressed();
						gameArea.requestFocusInWindow();
					}
				});
		game_1.add(gameArea, "cell 0 0,grow");
		frame.getContentPane().add(game_1, "cell 0 2,grow");

		JLayeredPane layeredPane = new JLayeredPane();
		frame.getContentPane().add(layeredPane, "flowx,cell 1 2");
		frame.getContentPane().add(moveButtons, "cell 1 2");
		moveButtons.setLayout(new MigLayout("", "[51px][83px][57px][1px]", "[23px][23px][23px][23px]"));

		JLayeredPane layeredPane_1 = new JLayeredPane();
		moveButtons.add(layeredPane_1, "cell 3 0,alignx left,aligny center");
		moveButtons.add(btnUp, "cell 1 0,alignx center,aligny top");
		
				btnRight = new JButton("Right");
				btnRight.setEnabled(false);
				btnRight.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {

						buttonPressed("R");
					}
				});
				
						btnLeft = new JButton("Left");
						btnLeft.setEnabled(false);
						btnLeft.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								buttonPressed("L");
							}
						});
						moveButtons.add(btnLeft, "cell 0 1,alignx left,aligny top");
				moveButtons.add(btnRight, "cell 2 1,alignx left,aligny top");
		moveButtons.add(btnDown, "cell 1 2,alignx center,aligny top");
										
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
												moveButtons.add(btnMainMenu, "cell 1 3,alignx left,aligny top");
		frame.getContentPane().add(lblYou, "cell 0 3,alignx left,aligny top");
	}

	private void newGamePressed() {
		
		/*game=new GuiInteraction();
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
		Restart.setEnabled(false);
		btnUp.setEnabled(true);
		btnDown.setEnabled(true);
		btnRight.setEnabled(true);
		btnLeft.setEnabled(true);
		game.getGame().setOgres(number);
		gameArea.setMaze(game.getGame().getMap());
		lblYou.setText("You can play now");*/
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
