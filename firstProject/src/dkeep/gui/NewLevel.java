package dkeep.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.CardLayout; 
import java.awt.FlowLayout; 
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import java.awt.Color;

public class NewLevel {

	private JFrame frame;
	private String[][] map;
	private GamePanel gameArea;
	JTextField x = new JTextField();
	JTextField y = new JTextField();
	Object[] position = {
	    "X:", x,
	    "Y:",y
	};
	Object[] heroPosition;

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
		gameArea = new GamePanel();
		gameArea.setBounds(10, 66, 164, 167);
		frame.getContentPane().add(gameArea);
		frame.getContentPane().setLayout(null);
		this.createMap(10);
		gameArea.setMaze(map);
		
		JButton btnHero = new JButton("Hero");
		btnHero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showConfirmDialog(null, position, "Hero position", JOptionPane.OK_CANCEL_OPTION);
				chooseHeroPosition(Integer.parseInt(x.getText()),Integer.parseInt(y.getText()));
				gameArea.setMaze(map);
			}
		});
		btnHero.setBounds(285, 66, 89, 23);
		frame.getContentPane().add(btnHero);
		
		JButton btnDoor = new JButton("Exit Door");
		btnDoor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showConfirmDialog(null, position, "Guard position", JOptionPane.OK_CANCEL_OPTION);
				chooseGuardPosition(Integer.parseInt(x.getText()),Integer.parseInt(y.getText()));
				gameArea.setMaze(map);
			}
		});
		btnDoor.setBounds(285, 96, 89, 23);
		frame.getContentPane().add(btnDoor);
		
		JButton btnWalls = new JButton("Walls");
		btnWalls.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showConfirmDialog(null, position, "Walls position", JOptionPane.OK_CANCEL_OPTION);
				chooseWallPosition(Integer.parseInt(x.getText()),Integer.parseInt(y.getText()));
				gameArea.setMaze(map);
			}
		});
		btnWalls.setBounds(285, 130, 89, 23);
		frame.getContentPane().add(btnWalls);
		
		JButton btnOgre = new JButton("Ogre");
		btnOgre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showConfirmDialog(null, position, "Ogre position", JOptionPane.OK_CANCEL_OPTION);
				chooseOgrePosition(Integer.parseInt(x.getText()),Integer.parseInt(y.getText()));
				gameArea.setMaze(map);
			}
		});
		btnOgre.setBounds(285, 32, 89, 23);
		frame.getContentPane().add(btnOgre);
		
		JButton btnSave = new JButton("Save");
		btnSave.setBounds(184, 199, 89, 23);
		frame.getContentPane().add(btnSave);
		
		JButton btnMainMenu = new JButton("Main Menu");
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
		btnMainMenu.setBounds(324, 199, 100, 23);
		frame.getContentPane().add(btnMainMenu);
		
		
	}

	public void createMap(int length) {
		this.map=new String[length][length];
		if (length==0)return;
		for (int i =0; i < length;i++) {
			for (int j=0;j<length;j++) {
				if (i == 0 || i== length-1 || j==0 || j== length-1)
					this.map[i][j]="X";
				else this.map[i][j]=" ";
			}
		}
	}
	public void chooseHeroPosition(int x, int y) {
		if (x>=map[0].length|| y>=map.length) return;
		if (x<0 || y<0) return;
		
		if (map[x][y].equals(" ")) {
			clearLastPosition("H");
			map[x][y]="H";
		}
		else
			return;
	}
	public void chooseGuardPosition(int x, int y) {
		if (x>=map[0].length|| y>=map.length) return ;
		if (x<0 || y<0) return;
		if (map[x][y].equals(" ")) {
			clearLastPosition("G");
			map[x][y]="G";
		}
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
	public boolean chooseExitDoorPosition(int x, int y) {
		if (x>=map[0].length|| y>=map.length) return false;
		if (x<0 || y<0) return false;
		if (map[x][y].equals(" ")) {
			clearLastPosition("I");
			map[x][y]="I";
			return true;
		}
		else if (map[x][y].equals("X")) {
			map[x][y]="I";
			return true;
		}
			return false;
	}
	public boolean chooseWallPosition(int x, int y) {
		if (x>=map[0].length|| y>=map.length) return false;
		if (x<0 || y<0) return false;
		if (map[x][y].equals(" ")) {
			map[x][y]="X";
			return true;
		}
		return false;
	}
	public void clearLastPosition(String character) {
		for (int i =0;i<map.length;i++) {
			for (int j =0;j< map[0].length;j++)
				if (map[i][j].equals(character))map[i][j]=" ";
		}
	}

}
