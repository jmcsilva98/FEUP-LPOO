package dkeep.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class NewLevel {
	public JFrame frame;
	private String[][] map;
	private GamePanel gameArea;
	private boolean hasHero=false;
	private boolean hasOgre=false;
	private boolean hasExitDoor=true;
	private boolean hasKey=true;
	private boolean hasWall=true;
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
	 * @throws IOException 
	 */
	public NewLevel() throws IOException {

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
				JOptionPane.showConfirmDialog(null, position, "Exit Door position", JOptionPane.OK_CANCEL_OPTION);
				if (!chooseExitDoorPosition(Integer.parseInt(x.getText()),Integer.parseInt(y.getText()))) {
					JOptionPane.showMessageDialog(null,"Exit Door can only be placed in empty spaces or in walls");
				}
				gameArea.setMaze(map);
			}
		});

		btnDoor.setBounds(285, 96, 89, 23);
		frame.getContentPane().add(btnDoor);
		JButton btnKey = new JButton("Key");
		btnKey.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showConfirmDialog(null, position, "Key position", JOptionPane.OK_CANCEL_OPTION);
				if (!chooseKeyPosition(Integer.parseInt(x.getText()),Integer.parseInt(y.getText()))) {
					JOptionPane.showMessageDialog(null,"Key can only be placed in empty spaces or in walls");
				}
				gameArea.setMaze(map);
			}
		});
		btnKey.setBounds(285, 126, 89, 23);
		frame.getContentPane().add(btnKey);

		JButton btnWalls = new JButton("Walls");
		btnWalls.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showConfirmDialog(null, position, "Wall's position", JOptionPane.OK_CANCEL_OPTION);
				if (!chooseWallPosition(Integer.parseInt(x.getText()),Integer.parseInt(y.getText()))) {
					JOptionPane.showMessageDialog(null,"Walls can only be placed in empty spaces");
				}
				gameArea.setMaze(map);
			}
		});
		btnWalls.setBounds(285, 156, 89, 23);
		frame.getContentPane().add(btnWalls);

		JButton btnOgre = new JButton("Ogre");
		btnOgre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showConfirmDialog(null, position, "Ogre position", JOptionPane.OK_CANCEL_OPTION);
				if (!chooseOgrePosition(Integer.parseInt(x.getText()),Integer.parseInt(y.getText()))) {
					JOptionPane.showMessageDialog(null,"Ogre can only be placed in empty spaces");
				}
				gameArea.setMaze(map);
			}
		});
		btnOgre.setBounds(285, 32, 89, 23);
		frame.getContentPane().add(btnOgre);

		JButton btnSave = new JButton("Save and Play");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int selectedOption=JOptionPane.showConfirmDialog(null,  "Are you sure that you want to save the map and play?", "Play with new map", JOptionPane.YES_NO_OPTION); 
				if (selectedOption==JOptionPane.YES_OPTION) {
					if (!canSaveMap());
					JOptionPane.showMessageDialog(null, "To play with your own map, you need to place all the game elements(walls, exit door, key, Ogres, hero) ");
				}
				else return;
				gameArea.setMaze(map);
			}
		});
		btnSave.setBounds(184, 199, 89, 23);
		frame.getContentPane().add(btnSave);

		JButton btnMainMenu = new JButton("Main Menu");
		btnMainMenu.addActionListener(new ActionListener() {
			MenuPanel other=null;
			public void actionPerformed(ActionEvent arg0) {
				int selectedOption=JOptionPane.showConfirmDialog(null, "Are you sure that you want to the main menu?", "New menu", JOptionPane.YES_NO_OPTION); 
				if (selectedOption==JOptionPane.NO_OPTION) {
					return;
				}
				try {
					 other= new MenuPanel();
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
	public boolean chooseHeroPosition(int x, int y) {
		if (x>=map[0].length|| y>=map.length) return false;
		if (x<0 || y<0) return false;

		if (map[x][y].equals(" ")) {
			clearLastPosition("H");
			map[x][y]="H";
			return true;
		}
		else
			return false;
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
	public boolean chooseOgrePosition(int x, int y) {
		if (x>=map[0].length|| y>=map.length) return false;
		if (x<0 || y<0) return false;
		if (map[x][y].equals(" ")) {
			map[x][y]="O";
			return true;
		}
		else
			return false;
	}
	public boolean chooseExitDoorPosition(int x, int y) {
		if (x>=map[0].length|| y>=map.length) return false;
		if (x<0 || y<0) return false;
		if (map[x][y].equals(" ") || map[x][y].equals("X")) {
			clearLastPosition("I");
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
	public boolean chooseKeyPosition(int x, int y) {
		if (x>=map[0].length|| y>=map.length) return false;
		if (x<0 || y<0) return false;
		if (map[x][y].equals(" ")) {
			map[x][y]="I";
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
	public boolean canSaveMap() {
		int ogresNumber=0;
		for (int i =0;i<map.length;i++)
			for (int j = 0;  j< map.length;j++) {
				switch (map[i][j]) {
				case "H":
					hasHero=true;
					break;
				case "K":
					hasKey=true;
					break;
				case "O":
					hasOgre=true;
					ogresNumber++;
					break;
				case "X":
					hasWall=true;
					break;
				case "I":
					hasExitDoor=true;
					break;
				}
			}
		if (hasHero && hasKey && hasOgre && hasWall && hasExitDoor && ogresNumber<5) {
			PlayPanel other=null;
			try {
				other = new PlayPanel();
			} catch (IOException e) {
				e.printStackTrace();
			}
			other.frame.setVisible(true);
			other.editableLevel(map);
			return true;
		}
		return false;
	}
}
