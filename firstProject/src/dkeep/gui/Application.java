package dkeep.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
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

import javax.swing.JTextArea;
import javax.swing.DefaultComboBoxModel;

public class Application {

	private JFrame frame;
	private JTextField numberOgres;
	private JTextArea gameArea;
	private GuiInteraction game;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Application window = new Application();
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
	public Application() {
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
		
		JLabel lblNumberOfOgres = new JLabel("Number of ogres");
		
		numberOgres = new JTextField();
		numberOgres.setColumns(10);
		
		JLabel lblGuardPersonality = new JLabel("Guard personality");
		
		JComboBox guardPersonality = new JComboBox();
		guardPersonality.setMaximumRowCount(10);
		guardPersonality.setEditable(true);
		guardPersonality.setModel(new DefaultComboBoxModel(new String[] {"Rookie", "Drunken", "Suspicious"}));
		guardPersonality.setSelectedIndex(2);
		GroupLayout gl_configurations = new GroupLayout(configurations);
		gl_configurations.setHorizontalGroup(
			gl_configurations.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_configurations.createSequentialGroup()
					.addGap(35)
					.addGroup(gl_configurations.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNumberOfOgres)
						.addComponent(lblGuardPersonality))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_configurations.createParallelGroup(Alignment.TRAILING)
						.addComponent(numberOgres, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
						.addComponent(guardPersonality, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(19, Short.MAX_VALUE))
		);
		gl_configurations.setVerticalGroup(
			gl_configurations.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_configurations.createSequentialGroup()
					.addGap(4)
					.addGroup(gl_configurations.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNumberOfOgres)
						.addComponent(numberOgres, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_configurations.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblGuardPersonality)
						.addComponent(guardPersonality, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18))
		);
		configurations.setLayout(gl_configurations);
		
		JPanel game_1 = new JPanel();
		
		JPanel newGame = new JPanel();
		
		JPanel moveButtons = new JPanel();
		
		JLabel lblYou = new JLabel("You can start a new game");
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(game_1, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(moveButtons, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(configurations, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(52)
							.addComponent(newGame, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblYou)))
					.addContainerGap(24, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(newGame, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(configurations, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(game_1, GroupLayout.PREFERRED_SIZE, 164, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(22)
							.addComponent(moveButtons, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
					.addPreferredGap(ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
					.addComponent(lblYou))
		);
		
		JButton btnUp = new JButton("Up");
		
		JButton btnNewButton = new JButton("Right");
		
		JButton btnLeft = new JButton("Left");
		
		JButton btnLeft_1 = new JButton("Down");
		btnLeft_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JButton btnExit = new JButton("Exit");
		GroupLayout gl_moveButtons = new GroupLayout(moveButtons);
		gl_moveButtons.setHorizontalGroup(
			gl_moveButtons.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_moveButtons.createSequentialGroup()
					.addGroup(gl_moveButtons.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_moveButtons.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnLeft)
							.addPreferredGap(ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
							.addComponent(btnNewButton))
						.addGroup(gl_moveButtons.createSequentialGroup()
							.addGap(52)
							.addComponent(btnUp))
						.addGroup(gl_moveButtons.createSequentialGroup()
							.addGap(46)
							.addComponent(btnLeft_1))
						.addGroup(Alignment.TRAILING, gl_moveButtons.createSequentialGroup()
							.addContainerGap(91, Short.MAX_VALUE)
							.addComponent(btnExit)))
					.addContainerGap())
		);
		gl_moveButtons.setVerticalGroup(
			gl_moveButtons.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_moveButtons.createSequentialGroup()
					.addGap(16)
					.addComponent(btnUp)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_moveButtons.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton)
						.addComponent(btnLeft))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnLeft_1)
					.addPreferredGap(ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
					.addComponent(btnExit))
		);
		moveButtons.setLayout(gl_moveButtons);
		
		JButton btnNewGame = new JButton("New Game");
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newGamePressed();
			}
		});
		newGame.add(btnNewGame);
		
		gameArea = new JTextArea();
		gameArea.setColumns(27);
		gameArea.setTabSize(10);
		gameArea.setRows(8);
		game_1.add(gameArea);
		frame.getContentPane().setLayout(groupLayout);
	}

	private void newGamePressed() {
		game=new GuiInteraction();
		game.start();
		int number=Integer.parseInt(numberOgres.getText());
		while(numberOgres.getText()==null) {
			number=Integer.parseInt(numberOgres.getText());
		}
				
		if ( number >5 || number <0)
			JOptionPane.showMessageDialog(frame, "You have to insert a positive number less than 5!");
		game.getGame().setOgres(number);
		gameArea.setText(game.getGame().toStr());
		}
}
