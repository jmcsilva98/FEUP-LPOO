package dkeep.logic;

import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

public class Gamestate {


	public String[][] currentMap;

	int level;

	public boolean gameWon=false;
	ArrayList<Ogre> ogres = new ArrayList<Ogre>(); //Using java colletion (vector) to store ogres 
	boolean gameOver=false;
	Ogre ogre;
	Guard guard; 
	Hero hero;
	public boolean testCase = false, testCase0 = false, testCase1 = false, testCase2 = false;

	public Gamestate() {
		this.currentMap=Map.getMap(2);
		level=2;
	}

	public Gamestate(Map map) {
		this.currentMap=map.getMap();	

	}

	public boolean gameWon() {
		return gameWon;
	}

	public boolean heroIsArmed() {
		if(level == 2) return true;

		return false; 
	}
	public Gamestate(String [][] map) {
		this.currentMap = map;
	}


	public void setLevelManualy(int level) {
		this.level=level;
	}

	public void setLevel(int level) {
		this.currentMap=Map.getMap(level);
		this.level=level;
	}
	public void setMap(String[][]map) {
		this.currentMap=map;
	}

	public String[][] getMap() {
		return currentMap;
	}
	public int getLevel() {
		return level;
	}

	public Ogre getOgre() {
		return ogre;
	}
	public ArrayList<Ogre> getOgres() {
		return ogres;
	}
	public Hero getHero() {
		return hero;
	}
	public void setHero(Hero hero) {
		this.hero=hero;
	}
	public void setOgre(Ogre ogre) {
		this.ogre = ogre;
	}


	public void setOgres(ArrayList<Ogre> ogres) {

		Random n = new Random();
		Character club= new Character();
		club.setX(1);
		club.setY(3);
		int numberOfOgres = n.nextInt(3) + 1;

		for(int i = 0; i <numberOfOgres; ++i) {
			Ogre ogre = new Ogre();
			ogre.setX(1);
			ogre.setY(4);
			ogre.setClub(club);
			ogres.add(ogre);
		}
		this.ogres=ogres;
	}
	public void setOgres(int numberOfOgres) {
		ArrayList<Ogre> ogres= new ArrayList<Ogre>();
		for(int i = 0; i < numberOfOgres; i++) {

			Ogre ogre = new Ogre();
			ogres.add(ogre);
		}
		
		this.ogres=ogres;
		System.out.println("size::::"+this.ogres.size());
	}


	public void setGuard(Guard guard) {
		this.guard=guard;
	}

	public String toString() {
		int n;
		String map="";
		if (level==1) {
			n=10;
			testCase = true;
		}
		else 
			n=9; 
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				map+=currentMap[i][j];
			} 
			map+="\n";
		}
		return map;
	}

	public void startConsole() {
		ArrayList <Ogre> ogres= new ArrayList<Ogre>();
		int aux =Guard.randomGenerator(3);
		Character club= new Character();
		club.setX(2);
		club.setY(4);
		if (level==1) {
			switch(aux) {
			case 0:
				RookieGuard rookie=new RookieGuard();
				setGuard(rookie);
				testCase0 = true;
				break;
			case 1:
				DrunkenGuard drunken=new DrunkenGuard();
				setGuard(drunken);
				testCase1 = true;
				break;
			case -1:
				SuspiciousGuard suspicious=new SuspiciousGuard();
				setGuard(suspicious);
				testCase2 = true;
				break;
			}
		}

		else {
			setOgres(ogres); //generates the array of ogres
		}
	}
	public void startApplication(String guard, int numberOgres) {
		ArrayList <Ogre> ogres= new ArrayList<Ogre>();
		Vector<Ogre> ogresVector = new Vector<Ogre>();
		Ogre auxOgre= new Ogre();
		Character club= new Character();
		if (level==1) {
		switch(guard) {
		case "Rookie":
			RookieGuard rookie=new RookieGuard();
			setGuard(rookie);
			break;
		case "Drunken":
			DrunkenGuard drunken=new DrunkenGuard();
			setGuard(drunken);
			break;
		case "Suspicious":
			SuspiciousGuard suspicious=new SuspiciousGuard();
			setGuard(suspicious);
			break;
		default:
			RookieGuard rookie2=new RookieGuard();
			setGuard(rookie2);

		}
		}
		for (int i =0;i< numberOgres; i++) {
			ogresVector.add(auxOgre);
		}
		setOgres(numberOgres);
	}
	public void start(boolean application,String gua, int numberOgres) {
		Hero hero = new Hero();

		if (!application) {
			startConsole();
		}
		else {
			startApplication(gua,numberOgres);

		}	
		if (level==1) {
			hero.setX(1);
			hero.setY(1); 
			guard.setX(1);
			guard.setY(8);
			setHero(hero);
		}
		
		else if (level==2) {
			hero.setX(7);
			hero.setY(1); 
			setHero(hero);
		}




	}
	public void gameState(int n) {
		if (n==0) {
			System.out.println("Game Over!");
			gameOver=true;
		}
		else {
			System.out.println("Game won!!");
			gameWon=true;
			return;
		}


	}

	public boolean isGameOver() {
		return gameOver;
	}

	public void heroMovement(String move) {
		currentMap[hero.xn][hero.yn]=" ";
		currentMap[hero.x][hero.y]=" ";
		hero.movement(move);

		switch (currentMap[hero.x][hero.y]) {
		case " ":
			heroHasKey();
			break;
		case "k":
			heroChangeK();
			break;

		case "I":
			changeS();
			break;

		case "S":
			heroChangeLevel();
			break;
		default:
			heroDefaultMovement();
		}
		hero.updatePosition();
	}

	public void heroChangeK() {
		if (level==1) {
			currentMap[5][0] = "S";
			currentMap[6][0] = "S";
		}
		currentMap[hero.x][hero.y]="K";
		hero.hasKey=true;
	}

	public void changeS() {
		if(hero.hasKey) {
			currentMap[hero.x][hero.y]="S";
			gameState(1);
		}
		else {
			heroDefaultMovement();
		}
	}

	public void heroChangeLevel() {
		System.out.print("hero change level");
		if(level == 1) {
			
			setLevel(2);
			hero.setX(7);
			hero.setY(1);
		} 
		else 
			gameWon=true;
	}

	public void heroHasKey() {
		if(hero.hasKey) {
			currentMap[hero.x][hero.y]="K";
		}
		else if(level == 2) {
			currentMap[hero.x][hero.y]="A";

		}

		else {
			currentMap[hero.x][hero.y]="H";
		}
	}


	public void guardMovement() {
		currentMap[guard.xn][guard.yn]=" ";
		guard.movement();
		if (currentMap[guard.x][guard.y]!=" ") {
			guard.x=guard.xn;
			guard.y=guard.yn;
			testCase = true;
		}
		currentMap[guard.x][guard.y]="G";
		guard.updatePosition();
	}



	public String ogreMovement() {
		String ret="";

		for (int i =0;i< ogres.size();i++) {
			
			currentMap[ogre.xn][ogre.yn]=" ";
			ret=ogre.movement();
			if (currentMap[ogre.x][ogre.y]==" ")
			currentMap[ogre.x][ogre.y]="O";
			
			ogre.updatePosition();
			i++;
			if (ogres.get(i).isStunned) {
				ogres.get(i).symbol="8";
				ogres.get(i).stunCounter++;
				if (ogres.get(i).stunCounter==2) {
					ogres.get(i).isStunned=false;
					ogres.get(i).stunCounter=0;
				}
			}
			else {

				ret=ogres.get(i).movement();
				if (currentMap[ogres.get(i).x][ogres.get(i).y]!=" ") {
					ogres.get(i).x=ogres.get(i).xn;
					ogres.get(i).y=ogres.get(i).yn;
				}
				if (currentMap[ogres.get(i).x][ogres.get(i).y]=="k") {
					ogres.get(i).symbol="$";
				}

			}
			currentMap[ogres.get(i).getClub().xn][ogres.get(i).getClub().yn]=" ";
			newPositionClub(ogres.get(i));
			currentMap[ogres.get(i).getClub().x][ogres.get(i).getClub().y]="*";
			ogres.get(i).getClub().updatePosition();
			currentMap[ogres.get(i).x][ogres.get(i).y]=" ";
			currentMap[ogres.get(i).x][ogres.get(i).y]=ogres.get(i).symbol;
			ogres.get(i).updatePosition();
		}
		return ret;

	}

	public boolean isFreeGuard() {

		if (hero.y==0) {
			if (currentMap[hero.x-1][hero.y] =="G")return false;
			if (currentMap[hero.x][hero.y+1] =="G")return false;
			if (currentMap[hero.x+1][hero.y] =="G")return false;
			if (currentMap[hero.x-1][hero.y] =="g")return false;
			if (currentMap[hero.x][hero.y+1] =="g")return false;
			if (currentMap[hero.x+1][hero.y] =="g")return false;
		}

		if (!isFreeDefault("G","g"))return false;

		return true;
	}
	public boolean isFreeOgre() {

		if (!isFreeOgreX()) return false;

		else if (!isFreeOgreY()) return false;

		else if (!isFreeDefault("O","*"))return false;
		return true;
	}
	public boolean isFreeDefault(String type,String type2) {
		if (currentMap[hero.x-1][hero.y]==type)return false;
		if (currentMap[hero.x][hero.y+1] ==type)return false;
		if (currentMap[hero.x+1][hero.y] ==type)return false;
		if (currentMap[hero.x][hero.y-1]==type)return false;
		if (currentMap[hero.x-1][hero.y] ==type2)return false;
		if (currentMap[hero.x][hero.y+1] ==type2)return false;
		if (currentMap[hero.x+1][hero.y] ==type2)return false;
		if (currentMap[hero.x][hero.y-1] ==type2)return false;
		return true;
	}
	public boolean isFreeOgreX() {
		if (!isFreeOgreX0()) return false;
		
		if (! isFreeOgreLength()) return false;
		return true;
	}
	public boolean isFreeOgreX0() {
		if (hero.x==0) {
			if (currentMap[hero.x][hero.y+1] =="O")return false;
			if (currentMap[hero.x+1][hero.y] =="O")return false;
			if (currentMap[hero.x][hero.y-1] =="O")return false;
			if (currentMap[hero.x][hero.y+1] =="*")return false;
			if (currentMap[hero.x+1][hero.y] =="*")return false;
			if (currentMap[hero.x][hero.y-1] =="*")return false;
		}
		return true;
	}
	public boolean isFreeOgreLength() {
		if (hero.x == currentMap.length-1) {
		if (currentMap[hero.x-1][hero.y] =="O")return false;
		if (currentMap[hero.x][hero.y+1] =="O")return false;
		if (currentMap[hero.x][hero.y-1] =="O")return false;
		if (currentMap[hero.x-1][hero.y] =="*")return false;
		if (currentMap[hero.x][hero.y+1] =="*")return false;
		if (currentMap[hero.x][hero.y-1] =="*")return false;
	}
		return true;
	}
	public boolean isFreeOgreY() {
		
		if (!isFreeOgreY0()) return false;
		else 
		return true;
	}
	public boolean isFreeOgreY0() {
		if (hero.y==0) {
			if (currentMap[hero.x-1][hero.y] =="O")return false;
			if (currentMap[hero.x][hero.y+1] =="O")return false;
			if (currentMap[hero.x+1][hero.y] =="O")return false;
			if (currentMap[hero.x-1][hero.y] =="*")return false;
			if (currentMap[hero.x][hero.y+1] =="*")return false;
			if (currentMap[hero.x+1][hero.y] =="*")return false;
		}
		return true;
		
	}
	public boolean isFreeOgreYLength() {
		if (hero.y==currentMap.length-1) {
			if (currentMap[hero.x-1][hero.y] =="O")return false;
			if (currentMap[hero.x+1][hero.y] =="O")return false;
			if (currentMap[hero.x][hero.y-1] =="O")return false;
			if (currentMap[hero.x-1][hero.y] =="*")return false;
			if (currentMap[hero.x+1][hero.y] =="*")return false;
			if (currentMap[hero.x][hero.y-1] =="*")return false;
		}
		return true;
	}
	public void newPositionClub(Ogre ogre) {
		if ( ogre.x>0 && currentMap[ogre.x-1][ogre.y]==" ") {
			ogre.getClub().x=ogre.x;
			ogre.getClub().y=ogre.y-1;
		}
		else if ( ogre.x<currentMap.length && currentMap[ogre.x+1][ogre.y]==" ") {
			ogre.getClub().x=ogre.x+1;
			ogre.getClub().y=ogre.y;
		}
		else if (ogre.y>0 && currentMap[ogre.x][ogre.y-1]==" ") {
			ogre.getClub().x=ogre.x;
			ogre.getClub().y=ogre.y-1;
		}
		else if (ogre.y<currentMap.length &&currentMap[ogre.x][ogre.y+1]==" ") {
			ogre.getClub().x=ogre.x;
			ogre.getClub().y=ogre.y+1;
		}

	}
	public void heroDefaultMovement() {
		hero.x=hero.xn;
		hero.y=hero.yn;
		if(level ==1)
			currentMap[hero.x][hero.y]="H";
		else
			currentMap[hero.x][hero.y]="A";
	}

}



