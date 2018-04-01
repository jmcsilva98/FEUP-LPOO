package dkeep.logic;

public class SuspiciousGuard extends Guard {
	public int position=1;
	public boolean backMovement;
	public boolean functionWasCalled;

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
		functionWasCalled = true;
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

