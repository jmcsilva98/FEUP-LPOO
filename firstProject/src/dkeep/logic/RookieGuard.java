package dkeep.logic;

public class RookieGuard extends Guard{
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
	public int getLength() {
		return length;
	}

}
