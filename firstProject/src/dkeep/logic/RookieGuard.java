package dkeep.logic;

public class RookieGuard extends Guard{
	int position=1;

	public void movement() {

		if(position < movement.length) {

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
			position++;
		}
		else
			position=1;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int length) {
		this.position = length;
	}

}
