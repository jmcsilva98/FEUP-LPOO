package dkeep.logic;

public abstract class Guard extends Character {
	 
	String[] rookieMovement= {"0", "L", "D", "D","D","D", "L",  "L", "L", "L", "L", "L", "D", "R", "R", "R", "R", "R", "R", "R", "U","U","U","U" };
	String[] suspicousMovement;
	boolean suspiciousBack=false;
	int suspPos;
	
	int suspLength=0;
	public abstract void movement();
	public Guard() {}
	
	
	
}