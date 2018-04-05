package dkeep.logic;

import java.util.Random;

public class Character{
	 int x,y;
	int xn,yn; 
	/*
	 * Function to move the character
	 * @param String that represent character direction
	 */
	public void movement(String move) {
		
		switch(move) {
		case "U" : x--;
		break;
		case "D" : x++;
		break;
		case "R" : y++;
		break;
		case "L" : y--;
		break;
		default : break;
		}
	}
	/**
	 * Function to set previous x
	 * @param xn
	 */
	public void setXn(int xn) {
		this.xn = xn;
	}
	/**
	 * Function to set previous y
	 * @param yn
	 */
	public void setYn(int yn) {
		this.yn = yn;
	}
	/**
	 * Function to get actual x
	 * @return actual x
	 */
	public int getX() {
		return x;
	}
	/**
	 * Function to get actual y
	 * @return actual y
	 */
	public int getY() {
		return y;
	}
	/**
	 * Function to get previous x
	 * @return previous x
	 */
	public int getXn() {
		return xn;
	}
	/**
	 * Function to get previous y
	 * @return previous y
  	*/
	public int getYn() {
		return yn;
	} 
	/**
	 * Function to set actual x
	 * @param x actual x
	 */
	public void setX(int x) {
		this.x=x;
	}
	/**
	 * Function to set actual y
	 * @param y actual  y
	 */
	public void setY(int y) {
		this.y=y;
	}
	/**
	 * Function to update previous position
	 */
	public void updatePosition() {
		
		xn=x;
		yn=y;
	}
	/**
	 * Function to generate random n values
	 * @param n number of values to be generated
	 * @return random value
	 */
	public static int randomGenerator(int n) {
		Random randomGenerator = new Random();
		int randomInt = randomGenerator.nextInt(n);
		if (randomInt==2) {
			randomInt=-1;
		}
		return randomInt;
}
}
