package dkeep.gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import dkeep.cli.GuiInteraction;


public class MapCreator extends JFrame {

	public JFrame frame;
	private int xPressed, yPressed;
	private String[][] map;
	int dimension;
	private int numberOgres;
	private boolean hasHero=false;
	private boolean hasOgre=false;
	private boolean hasExitDoor=false;
	private boolean hasKey=false;
	private boolean hasWall=true;
	private GamePanel gameArea;
	private String element;
	private JLabel label;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MapCreator window = new MapCreator();
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
	public MapCreator() throws IOException {
		initialize();
	}
	/**
	 * Initialize the contents of the frame.
	 * @throws IOException 
	 */
	private void initialize() throws IOException {
		GamePanel.loadImages();
		initializeFrame();

		initializeHeroButton();

		initalizeOgreButton();

		initializeWallButton();

		initializeKeyWordButton();

		initializeExitDoorButton();

		initializeMainMenuButton();

		intializePlayButton();  
	}

	private void intializePlayButton() {
		JButton btnPlay = new JButton("Play");
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					canSaveMap();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnPlay.setBounds(335, 206, 89, 23);
		frame.getContentPane().add(btnPlay);

		JLabel lblNowYouCan = new JLabel("Now, you can create your own map.");
		lblNowYouCan.setBounds(24, 236, 200, 14);
		frame.getContentPane().add(lblNowYouCan);

		gameArea.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e){
				xPressed=e.getX();
				yPressed=e.getY();
				whichElement();
			}

		});
	}

	private void initializeMainMenuButton() {
		JButton btnNewButton = new JButton("Main Menu");
		btnNewButton.addActionListener(new ActionListener() {
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
		btnNewButton.setBounds(243, 206, 89, 23);
		frame.getContentPane().add(btnNewButton);
	}

	private void initializeExitDoorButton() {
		JButton btnExitDoor = new JButton("Exit door");
		btnExitDoor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				element="I";
				gameArea.requestFocus();
			}
		});
		btnExitDoor.setBounds(282, 172, 89, 23);
		frame.getContentPane().add(btnExitDoor);
	}

	private void initializeKeyWordButton() {
		JButton btnKey = new JButton("Key");
		btnKey.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				element="k";
				gameArea.requestFocus();
			}
		});
		btnKey.setBounds(282, 138, 89, 23);
		frame.getContentPane().add(btnKey);
	}

	private void initializeWallButton() {
		JButton btnWall = new JButton("Wall");
		btnWall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				element="X";
				gameArea.requestFocus();
			}
		});
		btnWall.setBounds(282, 104, 89, 23);
		frame.getContentPane().add(btnWall);
	}

	private void initalizeOgreButton() {
		JButton btnOgre = new JButton("Ogre");
		btnOgre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				element="O";
				gameArea.requestFocus();
			}

		});
		btnOgre.setBounds(282, 70, 89, 23);
		frame.getContentPane().add(btnOgre);
	}

	private void initializeHeroButton() {
		JButton btnHero = new JButton("Hero");
		btnHero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				element="H";
				gameArea.requestFocus();
			}
		});
		btnHero.setBounds(282, 36, 89, 23);
		frame.getContentPane().add(btnHero);
	}

	private void initializeFrame() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		String length = JOptionPane.showInputDialog(frame, "Map dimensions");
		dimension=Integer.parseInt(length);
		gameArea = new GamePanel();
		this.createMap(dimension);
		gameArea.setBounds(24, 36, 200,200);
		gameArea.setMaze(map);
		frame.getContentPane().add(gameArea);
	}
	private void initializeHero() {
		chooseHeroPosition(xPressed/(200/dimension),yPressed/(200/dimension));
		gameArea.setMaze(map);
	}
	private void initializeOgre() {
		chooseOgrePosition(xPressed/(200/dimension),yPressed/(200/dimension));
		gameArea.setMaze(map);
	}
	private void initializeWalls() {
		chooseWallPosition(xPressed/(200/dimension),yPressed/(200/dimension));
		gameArea.setMaze(map);
	}
	private void initializeKey() {
		chooseKeyPosition(xPressed/(200/dimension),yPressed/(200/dimension));
		gameArea.setMaze(map);
	}
	private void initializeExitDoor() {
		chooseExitDoorPosition(xPressed/(200/dimension),yPressed/(200/dimension));
		gameArea.setMaze(map);
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

	public void paint() {
		int x,y;
		for (int i =0;i<dimension;i++)
			for(int j =0;j<dimension;j++) {
				x=i*20;
				y=j*20;

				if (xPressed > x && xPressed < i+20)
					if (yPressed > y && yPressed < j+20)
						if (map[xPressed][yPressed]==" ")
							return;

			}
	}
	public void clearLastPosition(String character) {
		for (int i =0;i<map.length;i++) {
			for (int j =0;j< map[0].length;j++)
				if (map[i][j].equals(character))map[i][j]=" ";
		}
	}

	public boolean canSaveMap() throws IOException {
		int ogresNumber=verifyAllElements();
		if (hasHero && hasKey && hasOgre && hasWall && hasExitDoor && ogresNumber<5) {
			return generatePlayPanel();
		}
		else {
			JOptionPane.showMessageDialog(frame, "You have to insert a hero, ogres, exit door and the key!");
			return false;
		}
	}

	public boolean generatePlayPanel() throws IOException {
		PlayPanel other=null;
		GuiInteraction game= new GuiInteraction();
		game.getGame().setLevel(0);
		other = new PlayPanel(null,numberOgres,game);
		other.editableLevel(map);
		other.frame.setVisible(true);

		return true;
	}
	public boolean chooseHeroPosition(int x, int y) {
		if (x>=map[0].length|| y>=map.length) return false;
		if (x<0 || y<0) return false;

		if (map[y][x].equals(" ")) {
			clearLastPosition("H");
			map[y][x]="H";
			return true;
		}
		else
			return false;
	}

	public boolean chooseOgrePosition(int x, int y) {
		if (x>=map[0].length|| y>=map.length) return false;
		if (x<0 || y<0) return false;
		if (map[y][x].equals(" ")) {
			map[y][x]="O";
			map[y-1][x]="*";
			return true;
		}
		if (map[y][x].equals("O"))
		{
			map[y][x]=" ";
			return true;
		}
		else
			return false;
	}
	public boolean chooseExitDoorPosition(int x, int y) {
		if (x>=map[0].length|| y>=map.length) return false;
		if (x<0 || y<0) return false;
		if (map[y][x].equals(" ") || map[x][y].equals("X")) {
			clearLastPosition("I");
			map[y][x]="I";
			return true;
		}
		return false;
	}
	public boolean chooseWallPosition(int x, int y) {
		if (x>=map[0].length|| y>=map.length) return false;
		if (x<0 || y<0) return false;
		if (map[y][x].equals(" ")) {
			map[y][x]="X";
			return true;
		}
		else if (map[y][x].equals("X")) {
			map[y][x]=" ";
		}
		return false;
	}
	public boolean chooseKeyPosition(int x, int y) {
		if (x>=map[0].length|| y>=map.length) return false;
		if (x<0 || y<0) return false;
		if (map[y][x].equals(" ")) {
			clearLastPosition("k");
			map[y][x]="k";
			return true;
		}
		return false;
	}

	public int verifyAllElements() {
		int ogresNumber=0;
		for (int i =0;i<map.length;i++)
			for (int j = 0;  j< map.length;j++) {
				ogresNumber = switchElements(ogresNumber, i, j);
			}
		this.numberOgres=ogresNumber;
		if (numberOgres >5) {
			JOptionPane.showMessageDialog(frame, "You have to insert a number less than 5 to ogres!");
			return 0;
		}
		return ogresNumber;
	}

	private int switchElements(int ogresNumber, int i, int j) {
		switch (map[i][j]) {
		case "H":
			hasHero=true;
			break;
		case "k":
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
		return ogresNumber;
	}

	public void whichElement() {
		switch (element) {
		case "H":
			initializeHero();
			break;
		case "k":
			initializeKey();
			break;
		case "O":
			initializeOgre();
			break;
		case "X":
			initializeWalls();
			break;
		case "I":
			initializeExitDoor();
			break;
		default:
			break;
		}
	}
	
}
