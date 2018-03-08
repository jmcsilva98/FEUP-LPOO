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
	if (x>=8 || x<=0) x=xn;
	if (y>= 8 || y<=0) y=yn;
	}
	public int get_x() {
		return x;
	}
	public int get_y() {
		return y;
	}
	public int get_xn() {
		return xn;
	}

	public int get_yn() {
		return yn;
	}
	public void set_x(int x) {
		this.x=x;
	}
	public void set_y(int y) {
		this.y=y;
	}

	void update_position() {

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
