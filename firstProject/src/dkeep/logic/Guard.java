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
	public int drunken_movement() {
		int x_move =randomGenerator(4);
		int y_move=randomGenerator(4);
		if(x_move==3|| x_move==4) {
			x=xn;
			y=yn;
			return 2;
		}
		
		if (y_move==3 || y_move==4) {
			x=xn;
			y=yn;
			return 2;
		}
		x+=x_move;
		y+=y_move;
		
		if (x<0 || x>9) x=xn;
		else
			if (y<0 || y>9) y=yn;
		return 0;
		
	}
	
	public void suspicious_movement() {
		int move =randomGenerator(5);
		int x_aux,y_aux;
		if(move==3 ) {
			if (susp_pos==susp_length) {
			x_aux=randomGenerator(2);
			x+=x_aux;
			y=yn;
			if (x_aux>0) suspicous_movement[susp_length]="D";
			else 
				suspicous_movement[susp_length]="U";
			susp_length++;
			susp_pos++;
		}
			else
			{
				suspicious_back=false;
				movement(suspicous_movement[susp_pos]);
				susp_pos++;
			}
		}
		else if (move==4 ) {
			if (susp_pos==susp_length){
			y_aux=randomGenerator(2);
			y+=y_aux;
			x=xn;
			if (y_aux>0) 
				suspicous_movement[susp_length]="R";
			else 
				suspicous_movement[susp_length]="D";
			susp_length++;
			}
			else
			{	suspicious_back=false;
				movement(suspicous_movement[susp_pos]);
				susp_pos++;
			}
		}
		else {
			if (!suspicious_back) {
			suspicious_back=true;
			}
			susp_pos--;
			movement(suspicous_movement[susp_pos]);
		}
	}
}