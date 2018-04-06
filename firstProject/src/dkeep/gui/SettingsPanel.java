package dkeep.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class SettingsPanel {

	public JFrame frame;
	public JTextField numberOgres;
	private JLabel lblNumberOfOgres;
	private JComboBox guardPersonality;
	public String guard;
	public int ogresNumber;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SettingsPanel window = new SettingsPanel();
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
	public SettingsPanel() throws IOException{
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		initializeSettingsButtons();

		initializeMainButtons();

		initializeNewLevelButton();
	}

	private void initializeNewLevelButton() {
	}

	private void initializeMainButtons() {
		initializeMainMenuButton();

		initializePlayButton();
	}

	private void initializePlayButton() {
		JButton btnPlay = new JButton("PLAY");
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PlayPanel other = null;

				try {
					playPressed(other);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			private void playPressed(PlayPanel other) throws IOException {
				playPressedAction(other);

			}

			public void playPressedAction(PlayPanel other) throws IOException {
				int number=0;
				String guard;
				try{
					number=Integer.parseInt(numberOgres.getText());

				} catch(NumberFormatException nfe) {

					JOptionPane.showMessageDialog(frame, "You have to insert the number of ogres!");
					return;
				}

				
				try { 
					guard = (String) guardPersonality.getItemAt(guardPersonality.getSelectedIndex());

				} catch (Exception e) {
					JOptionPane.showMessageDialog(frame, "You have to select the personality of guard!");
				}

				if ( number >5 || number <0) {

					JOptionPane.showMessageDialog(frame, "You have to insert a positive number less than 5!");
					return;
				}
				guard=(String) guardPersonality.getItemAt(guardPersonality.getSelectedIndex());
				System.out.println(guard);
				int ogresNumber=Integer.parseInt(numberOgres.getText());
				try {
					other = new PlayPanel(guard,ogresNumber);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				other.frame.setVisible(true);
				frame.setVisible(false);
			}
		});
		btnPlay.setBounds(302, 157, 111, 23);
		frame.getContentPane().add(btnPlay);
	}

	private void initializeMainMenuButton() {
		JButton btnMainMenu = new JButton("Main menu");
		btnMainMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MenuPanel other = null;
				try {
					other = new MenuPanel();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				other.frame.setVisible(true);
				frame.setVisible(false);

			}
		});
		btnMainMenu.setBounds(302, 192, 111, 23);
		frame.getContentPane().add(btnMainMenu);
	}

	private void initializeSettingsButtons() {
		numberOgres = new JTextField();
		numberOgres.setText("1");
		numberOgres.setToolTipText("");
		numberOgres.setBounds(196, 63, 91, 20);
		frame.getContentPane().add(numberOgres);
		numberOgres.setColumns(10);

		lblNumberOfOgres = new JLabel("Number of ogres");
		lblNumberOfOgres.setBounds(95, 66, 101, 14);
		frame.getContentPane().add(lblNumberOfOgres);

		guardPersonality = new JComboBox();
		guardPersonality.setModel(new DefaultComboBoxModel(new String[] {"Rookie", "Drunken", "Suspicious"}));
		guardPersonality.setSelectedIndex(0);
		guardPersonality.setToolTipText("Rookie\r\n");
		guardPersonality.setBounds(196, 94, 91, 20);
		frame.getContentPane().add(guardPersonality);

		JLabel lblGuardPersonality = new JLabel("Guard personality");
		lblGuardPersonality.setBounds(95, 97, 91, 14);
		frame.getContentPane().add(lblGuardPersonality);
	}
}