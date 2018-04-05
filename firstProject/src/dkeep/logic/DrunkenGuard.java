package dkeep.logic;

public class DrunkenGuard extends Guard{
	public int xMove, yMove;
	public boolean functionWasCalled = false;
	/**
	 * Function to move drunken guard
	 */
	public void movement() {
		randomMovement();	

		if (x<0 || x>9) x=xn;
		if (y<0 || y>9) y=yn;

	}
	/**
	 * Function to generate random movement
	 */
	public void randomMovement() {
		functionWasCalled = true;
		xMove =randomGenerator(3);
		yMove=randomGenerator(3);
		x+=xMove;
		y+=yMove;
	}
}
