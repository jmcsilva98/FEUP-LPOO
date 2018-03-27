package dkeep.logic;

public abstract class Guard extends Character {
	
	
	public String[] rookieMovement= {"0", "L", "D", "D","D","D", "L",  "L", "L", "L", "L", "L", "D", "R", "R", "R", "R", "R", "R", "R", "U","U","U","U" };
	public String[] suspicousMovement;
	public boolean suspiciousBack=false;
	public int suspPos;
	 
	public int suspLength=0;
	public abstract void movement();
	public Guard() {}
	
	
	
}