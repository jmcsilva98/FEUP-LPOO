package dkeep.logic;

public class Guard extends Character {
	 
	String[] rookieMovement= {"0", "L", "D", "D","D","D", "L",  "L", "L", "L", "L", "L", "D", "R", "R", "R", "R", "R", "R", "R", "U","U","U","U" };
	String[] suspicousMovement;
	boolean suspiciousBack=false;
	int suspPos;
	int length=1;
	int suspLength=0;
	public void rookie_movement() {
		if(length < 24) {
			switch(rookieMovement[length]) {
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
			length++;
		}
			else
				length=1;
}
	
	
	
}