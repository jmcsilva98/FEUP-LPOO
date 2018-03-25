package dkeep.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import javax.swing.SpringLayout;

public class MenuPanel {

	public JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuPanel window = new MenuPanel();
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
	public MenuPanel() throws  IOException{
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btnNewGame = new JButton("New game");
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			PlayPanel other = null;
			try {
				other = new PlayPanel();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			other.frame.setVisible(true);
			frame.setVisible(false);
				
			}
		});
		SpringLayout springLayout = new SpringLayout();
		springLayout.putConstraint(SpringLayout.WEST, btnNewGame, 180, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().setLayout(springLayout);
		frame.getContentPane().add(btnNewGame);
		
		JButton btnSettings = new JButton("Settings");
		btnSettings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SettingsPanel other = null;
				try {
					other = new SettingsPanel();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				other.frame.setVisible(true);
				frame.setVisible(false);
			}
		});
		springLayout.putConstraint(SpringLayout.WEST, btnSettings, 180, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnSettings, -160, SpringLayout.EAST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, btnNewGame, -17, SpringLayout.NORTH, btnSettings);
		springLayout.putConstraint(SpringLayout.EAST, btnNewGame, 0, SpringLayout.EAST, btnSettings);
		frame.getContentPane().add(btnSettings);
		
		JButton btnExit = new JButton("Exit");
		springLayout.putConstraint(SpringLayout.WEST, btnExit, 180, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnExit, -160, SpringLayout.EAST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, btnSettings, -18, SpringLayout.NORTH, btnExit);
		springLayout.putConstraint(SpringLayout.NORTH, btnExit, 149, SpringLayout.NORTH, frame.getContentPane());
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int exitPressed = JOptionPane.showConfirmDialog(null, "Are you sure that you want to exit the game?", "Exit", JOptionPane.YES_NO_OPTION);
				if (exitPressed==JOptionPane.YES_OPTION)
					System.exit(0);
					
			}
		});
		frame.getContentPane().add(btnExit);
	}
 
}
