package dkeep.logic;

public class DrunkenGuard extends Guard{
	public void movement() {
		int x_move =randomGenerator(4);
		int y_move=randomGenerator(4);
		x+=x_move;
		y+=y_move;
		
		if (x<0 || x>9) x=xn;
		else
		if (y<0 || y>9) y=yn;
		return;
		
	}
}
