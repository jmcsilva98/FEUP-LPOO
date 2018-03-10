package dkeep.logic;

public class DrunkenGuard extends Guard{
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
}
