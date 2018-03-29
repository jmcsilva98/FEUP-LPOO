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
public void clubMovement() {
		int x= this.x;
		int y =this.y;
		int auxR= randomGenerator(2);
		int aux = randomGenerator(3);
		switch(auxR) {
		case 0:
			x+=aux;
			break;
		case 1:
			y+=aux;
			break;
		}
		if (x>=8 || x<=0)
			club.x= this.x+1;
		if (y>=8 || y<=0)
			club.y=this.y+1;
	}

public Character getClub() {
	return club;
}
public void setClub(Character club) {
	this.club=club;
}
}
