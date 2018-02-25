package dkeep.logic;


public class Ogre extends Character {
	public void movement() {
		
		x+=randomGenerator(3);
		y+=randomGenerator(3);
	}
}
