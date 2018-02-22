package dkeep.logic;

public class Guard extends Character {
	
	String[] movement= {"0", "L", "D", "D","D","D", "L",  "L", "L", "L", "L", "L", "D", "R", "R", "R", "R", "R", "R", "R", "U","U","U","U" };;
	int length=1;
	public void movement() {
		if(length < 24) {
			switch(movement[length]) {
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