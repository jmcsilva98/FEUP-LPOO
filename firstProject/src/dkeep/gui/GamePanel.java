package dkeep.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.imageio.ImageIO;
import javax.swing.*;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GamePanel extends JPanel  {

	private JFrame frame;
	private String[][] map;

	private static BufferedImage wall;
	private static BufferedImage floor;
	private static BufferedImage closedDoor;
	private static BufferedImage openDoor;
	private static BufferedImage key;
	private static BufferedImage guard;
	private static BufferedImage ogre;
	private static BufferedImage stunnedOgre;
	private static BufferedImage hero;
	private static BufferedImage heroArmed;
	private static BufferedImage club;

	public GamePanel() {
	}



	public static void loadImages() throws IOException {

		wall = ImageIO.read(new File("Images/bricks.png"));
		floor = ImageIO.read(new File("Images/floor.png"));
		closedDoor = ImageIO.read(new File("Images/closedDoor.png"));
		openDoor = ImageIO.read(new File("Images/openDoor.png"));
		key = ImageIO.read(new File("Images/key.png"));
		guard = ImageIO.read(new File("Images/guard.png"));
		ogre = ImageIO.read(new File("Images/ogre.png"));
		stunnedOgre =	ImageIO.read(new File("Images/ogreStunned.png"));
		hero = ImageIO.read(new File("Images/hero.png"));
		heroArmed = ImageIO.read(new File("Images/heroArmed.png"));
		club = ImageIO.read(new File("Images/club.png"));
		


	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);


		if (map==null) return;
		int xSize=getWidth()/map[0].length;
		int ySize= getHeight()/map.length;
		g.drawRect(0, 0, (map.length-1)*xSize,(map.length-1)*ySize);
		for (int i =0; i <map.length ;i++) {
			for (int j = 0; j<map[0].length;j++) {
				switchPaintComponent(g, xSize, ySize, i, j);
			}

		}
	}



	private void switchPaintComponent(Graphics g, int xSize, int ySize, int i, int j) {
		switch(map[i][j]) {
		case " ":
			g.drawImage(floor, j*xSize, i*ySize, null);
			break;
		case "H":
 
			g.drawImage(hero, j*xSize, i*ySize, null);

			break;
		case "S":
			g.drawImage(openDoor, j*xSize, i*ySize, null);

			break;				
		case "X": 
			g.drawImage(wall, j*xSize, i*ySize, null);
			
			break;
		case "k":
			g.drawImage(key, j*xSize, i*ySize, null);
			
			break; 
		case "I":
			g.drawImage(closedDoor, j*xSize, i*ySize, null);
			
			break;
		case "G":
			g.drawImage(guard, j*xSize, i*ySize, null);
			break;
		case "A":
			g.drawImage(heroArmed, j*xSize, i*ySize, null);
			break;
		case "K":
			g.drawImage(hero, j*xSize, i*ySize, null);
			break;
		case "O":
			g.drawImage(ogre, j*xSize, i*ySize, null);
			break;
		case "8":
			g.drawImage(stunnedOgre, j*xSize, i*ySize, null);
			break;
		default:
			g.drawImage(floor, j*xSize, i*ySize, null);

			break;

		}
	}



	public void setMaze(String[][] map) {

		this.map=map;
		repaint();
	} 


	public String[][] getMap() {
		return map;
	}
	public void setMap(String[][] map) {
		this.map = map;
	}
	public BufferedImage getWall() {
		return wall;
	}

	public BufferedImage getFloor() {
		return floor;
	}

	public BufferedImage getClosedDoor() {
		return closedDoor;
	}

	public BufferedImage getOpenDoor() {
		return openDoor;
	}

	public BufferedImage getKey() {
		return key;
	}

	public BufferedImage getGuard() {
		return guard;
	}

	public BufferedImage getOgre() {
		return ogre;
	}

	public BufferedImage getStunnedOgre() {
		return stunnedOgre;
	}

	public BufferedImage getHero() {
		return hero;
	}

	public BufferedImage getHeroArmed() {
		return heroArmed;
	}

	public BufferedImage getClub() {
		return club;
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GamePanel window = new GamePanel();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}



}





