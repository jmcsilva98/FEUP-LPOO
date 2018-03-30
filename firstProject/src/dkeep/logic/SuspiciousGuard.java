package dkeep.logic;

public class SuspiciousGuard extends Guard {
	int position=1;
	boolean backMovement;

	public void movement() {
		int aux =randomGenerator(2);
		if (aux==0)
			backMovement=true;
		else 
			backMovement=false;

		if (position==1)
			backMovement=false;

		if (position==24)
			backMovement=true;

		if (backMovement)
			position--;
		else
			position++;
		auxMovement();
	}

	public void auxMovement() {
		switch(movement[position]) {
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

