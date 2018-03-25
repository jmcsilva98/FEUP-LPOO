package dkeep.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.*;

import javax.swing.*;


public class GamePanel extends JPanel  {

	String[][]map;
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);


		if (map==null) return;
		int xSize=getWidth()/map[0].length;
		int ySize= getHeight()/map.length;
		g.drawRect(0, 0, (map.length-1)*xSize,(map.length-1)*ySize);
		for (int i =0; i <map.length ;i++) {
			for (int j = 0; j<map[0].length;j++) {
				switch(map[i][j]) {
				case " ":
					g.setColor(Color.PINK);
					g.fillRect(j*xSize, i*ySize, xSize, ySize);
					break;
				case "H":
					g.setColor(Color.BLUE);
					g.fillRect(j*xSize, i*ySize, xSize, ySize);
					break;
				case "S":

					g.setColor(Color.ORANGE);
					g.fillRect(j*xSize, i*ySize, xSize, ySize);
					break;				
				case "X":
					g.setColor(Color.GREEN);
					g.fillRect(j*xSize, i*ySize, xSize, ySize);
					break;
				case "k":
					g.setColor(Color.BLACK);
					g.fillRect(j*xSize, i*ySize, xSize, ySize);
					break;
				case "I":
					g.setColor(Color.magenta);
					g.fillRect(j*xSize,i*ySize, xSize, ySize);
					break;
				default:
					g.setColor(Color.WHITE);
					g.fillRect(j*xSize, i*ySize, xSize, ySize);
					break;
				}
			}

		}

	}

	public void setMaze(String[][] map) {
		this.map=map;
		repaint();
	} 




}
