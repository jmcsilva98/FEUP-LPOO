package dkeep.logic;

public class DrunkenGuard extends Guard{
	//public int testx, testy;
	
	public void movement() {
		int x_move =randomGenerator(3);
		int y_move=randomGenerator(3);
		x+=x_move;
		y+=y_move;
		
		
		if (x<0 || x>9) x=xn;
		if (y<0 || y>9) y=yn;
		
	}
}
