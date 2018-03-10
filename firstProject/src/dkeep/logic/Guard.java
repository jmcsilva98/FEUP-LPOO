package dkeep.logic;

public class Guard extends Character {
	 
	String[] rookie_movement= {"0", "L", "D", "D","D","D", "L",  "L", "L", "L", "L", "L", "D", "R", "R", "R", "R", "R", "R", "R", "U","U","U","U" };
	String[] suspicous_movement;
	boolean suspicious_back=false;
	int susp_pos;
	int length=1;
	int susp_length=0;
	public void rookie_movement() {
		if(length < 24) {
			switch(rookie_movement[length]) {
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