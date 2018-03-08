package dkeep.logic;


public class Ogre extends Character {
	public boolean isStunned = false;
	public boolean isMoving=false;
	public void movement() {
		
		x+=randomGenerator(3);
		y+=randomGenerator(3);
	}
	

}
