package dkeep.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NewLevel {

	private JFrame frame;
	private String[][] map;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewLevel window = new NewLevel();
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
	public NewLevel() {
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
		
		JButton addHero = new JButton("Hero");
		addHero.setBounds(295, 36, 89, 23);
		frame.getContentPane().add(addHero);
		
		JButton btnNewButton_1 = new JButton("Guard");
		btnNewButton_1.setBounds(295, 70, 89, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Ogre");
		btnNewButton_2.setBounds(295, 104, 89, 23);
		frame.getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("");
		btnNewButton_3.setBounds(295, 135, 89, 23);
		frame.getContentPane().add(btnNewButton_3);
	}

	public void createMap(int length) {
		
		for (int i =0; i < length;i++) {
			for (int j=0;j<length;j++)
				this.map[i][j]=" ";
		}
	}
	public void chooseHeroPosition(int x, int y) {
		if (x>=map[0].length|| y>=map.length) return ;
		if (x<0 || y<0) return;
		if (map[x][y].equals(" ")) map[x][y]="H";
		else
			return;
	}
	public void chooseGuardPosition(int x, int y) {
		if (x>=map[0].length|| y>=map.length) return ;
		if (x<0 || y<0) return;
		if (map[x][y].equals(" ")) map[x][y]="G";
		else
			return;
	}
	public void chooseOgrePosition(int x, int y) {
		if (x>=map[0].length|| y>=map.length) return ;
		if (x<0 || y<0) return;
		if (map[x][y].equals(" ")) map[x][y]="O";
		else
			return;
	}
}
