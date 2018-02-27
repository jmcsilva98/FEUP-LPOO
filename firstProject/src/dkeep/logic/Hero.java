package dkeep.logic;

public class Hero extends Character {
	boolean hasKey=false;
	boolean isArmed = false;
	
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
	
	
}
