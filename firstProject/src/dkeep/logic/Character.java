package dkeep.logic;

import java.util.Random;

public class Character{
	 int x,y;
	int xn,yn; 
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
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public int getXn() {
		return xn;
	}
 
	public int getYn() {
		return yn;
	} 
	public void setX(int x) {
		this.x=x;
	}
	public void setY(int y) {
		this.y=y;
	}

	void updatePosition() {
		
		xn=x;
		yn=y;
	}
	public static int randomGenerator(int n) {
		Random randomGenerator = new Random();
		int randomInt = randomGenerator.nextInt(n);
		if (randomInt==2) {
			randomInt=-1;
		}
		return randomInt;
}
}
