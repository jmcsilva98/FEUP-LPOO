package dkeep.logic;

public class Ogre extends Character {
	public boolean isStunned = false;
	private Character club;
	int stunCounter = 0;
	public String movement() {

		int aux=randomGenerator(2);
		switch(aux) {
		case 0:
			aux =randomGenerator(3);
			x+=aux;
			break;
		case 1:
			aux = randomGenerator(3);
			y+=aux;
			break;
		default:
			break;
		}
		if (x >= 8 || x <=0)
			x =xn;
		if (y>=8 || y<= 0)
			y=yn;
		if (x>xn )return "D";
		if (x<xn) return "U";
		if (y>yn) return "R";
		if (y<yn) return "L";

		return null;
	}
}
