package dkeep.logic;

public class RookieGuard extends Guard{
	int position=1;
	/**
	 * Function to move rookie guard
	 */

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

	/**
	 * Function to get  actual position in the array
	 * @return actual position
	 */

	public int getPosition() {
		return position;
	}

	/**
	 * Function to set actual position in the array
	 * @param position Actual position
	 */
	public void setPosition(int position) {
		this.position = position;

	}

}
