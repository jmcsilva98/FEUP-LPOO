package dkeep.logic;

import java.util.Random;

public class Ogre extends Character {
	public static int randomGenerator() {
		Random randomGenerator = new Random();
		int randomInt = randomGenerator.nextInt(3);
		if (randomInt==2) {
			randomInt=-1;
		}
		return randomInt;
		
	}
	public void movement() {
		
		x+=randomGenerator();
		y+=randomGenerator();
	}
}
