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
		this.currentMap=Map.getMap(1);
		level=1;
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
		club.setX(2);
		club.setY(4);
		int numberOfOgres = n.nextInt(3) + 1;

		for(int i = 0; i <numberOfOgres; i++) {
			Ogre ogre = new Ogre();
			ogre.setX(1);
			ogre.setY(4);
			//ogres.elementAt(i).setClub(club);
			ogres.add(ogre);
		}
		this.ogres=ogres;
	}
	public void setOgres(int numberOfOgres) {

		for(int i = 0; i < numberOfOgres; i++) {

			Ogre ogre = new Ogre();
			ogres.add(ogre);
		}
		this.ogres=ogres;
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
		setOgres(ogres); //generates the vector of ogres
		}
	}
	public void startApplication(String guard, int numberOgres) {
		Ogre auxOgre= new Ogre();
		Character club= new Character();
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
		for(int i =0; i <numberOgres;i++) {
			auxOgre.setX(1);
			auxOgre.setY(4);
			club.setX(2);
			club.setY(4);
			auxOgre.setClub(club);
			ogres.add(auxOgre);
		}

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
			if(hero.hasKey) {
				currentMap[hero.x][hero.y]="K";
			}
			else if(level == 2) {
				currentMap[hero.x][hero.y]="A";
				
			}

			else {
				currentMap[hero.x][hero.y]="H";
			}
			break;
		case "k":
			if (level==1) {
				currentMap[5][0] = "S";
			currentMap[6][0] = "S";
			}
			currentMap[hero.x][hero.y]="K";
			hero.hasKey=true;
			break;

		case "I":
			if(hero.hasKey) {
				currentMap[hero.x][hero.y]="S";
				gameState(1);
			}
			else {
				hero.x=hero.xn;
				hero.y=hero.yn;
				if(level ==1)
					currentMap[hero.x][hero.y]="H";
				else
					currentMap[hero.x][hero.y]="A";
			}

			break;

		case "S":
			if(level == 1) {
				setLevel(2);	
			} 
			else 
				gameWon=true;
			break;
		default:
			hero.x=hero.xn;
			hero.y=hero.yn;
			if(level ==1)
				currentMap[hero.x][hero.y]="H";
			else
				currentMap[hero.x][hero.y]="A";
		}
		hero.updatePosition();
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
		int i;
		String ret="";
		System.out.println(ogres.size());
		for (Ogre ogre : ogres) {
			currentMap[ogre.x][ogre.y]="O";
			/*if(ogres.elementAt(i).isStunned) {
				ogres.elementAt(i).stunCounter++;

				if(ogres.elementAt(i).stunCounter == 2) {
					ogres.elementAt(i).isStunned = false;
					ogres.elementAt(i).stunCounter = 0;
					return ret;
				}

			else {

				ret=ogres.elementAt(i).movement();
				currentMap[ogres.elementAt(i).xn][ogres.elementAt(i).yn]=" ";
				currentMap[ogres.elementAt(i).x][ogres.elementAt(i).y]="O";
				ogres.elementAt(i).updatePosition();
			}*/
		}
		return ret;

	}
	
	public boolean isFreeGuard() {
		if (hero.x==0) {
			if (currentMap[hero.x][hero.y+1] =="G")return false;
			if (currentMap[hero.x+1][hero.y] =="G")return false;
			if (currentMap[hero.x][hero.y-1] =="G")return false;
			if (currentMap[hero.x][hero.y+1] =="g")return false;
			if (currentMap[hero.x+1][hero.y] =="g")return false;
			if (currentMap[hero.x][hero.y-1] =="g")return false;
		}
		else if (hero.y==0) {
			if (currentMap[hero.x-1][hero.y] =="G")return false;
			if (currentMap[hero.x][hero.y+1] =="G")return false;
			if (currentMap[hero.x+1][hero.y] =="G")return false;
			if (currentMap[hero.x-1][hero.y] =="g")return false;
			if (currentMap[hero.x][hero.y+1] =="g")return false;
			if (currentMap[hero.x+1][hero.y] =="g")return false;
		}
		else if (hero.x == currentMap.length) {
			if (currentMap[hero.x-1][hero.y] =="G")return false;
			if (currentMap[hero.x][hero.y+1] =="G")return false;
			if (currentMap[hero.x][hero.y-1] =="G")return false;
			if (currentMap[hero.x-1][hero.y] =="g")return false;
			if (currentMap[hero.x][hero.y+1] =="g")return false;
			if (currentMap[hero.x][hero.y-1] =="g")return false;
		}
		else if (hero.y==currentMap.length) {
			if (currentMap[hero.x-1][hero.y] =="G")return false;
			if (currentMap[hero.x+1][hero.y] =="G")return false;
			if (currentMap[hero.x][hero.y-1] =="G")return false;
			if (currentMap[hero.x-1][hero.y] =="g")return false;
			if (currentMap[hero.x+1][hero.y] =="g")return false;
			if (currentMap[hero.x][hero.y-1] =="g")return false;
		}
		else {
		if (currentMap[hero.x-1][hero.y] =="G")return false;
		if (currentMap[hero.x][hero.y+1] =="G")return false;
		if (currentMap[hero.x+1][hero.y] =="G")return false;
		if (currentMap[hero.x][hero.y-1] =="G")return false;
		if (currentMap[hero.x-1][hero.y] =="g")return false;
		if (currentMap[hero.x][hero.y+1] =="g")return false;
		if (currentMap[hero.x+1][hero.y] =="g")return false;
		if (currentMap[hero.x][hero.y-1] =="g")return false;
		}

		return true;
	}
	public boolean isFreeOgre() {
		if (hero.x==0) {
			if (currentMap[hero.x][hero.y+1] =="O")return false;
			if (currentMap[hero.x+1][hero.y] =="O")return false;
			if (currentMap[hero.x][hero.y-1] =="O")return false;
			if (currentMap[hero.x][hero.y+1] =="*")return false;
			if (currentMap[hero.x+1][hero.y] =="*")return false;
			if (currentMap[hero.x][hero.y-1] =="*")return false;
		}
		else if (hero.y==0) {
			if (currentMap[hero.x-1][hero.y] =="O")return false;
			if (currentMap[hero.x][hero.y+1] =="O")return false;
			if (currentMap[hero.x+1][hero.y] =="O")return false;
			if (currentMap[hero.x-1][hero.y] =="*")return false;
			if (currentMap[hero.x][hero.y+1] =="*")return false;
			if (currentMap[hero.x+1][hero.y] =="*")return false;
		}
		else if (hero.x == currentMap.length) {
			if (currentMap[hero.x-1][hero.y] =="O")return false;
			if (currentMap[hero.x][hero.y+1] =="O")return false;
			if (currentMap[hero.x][hero.y-1] =="O")return false;
			if (currentMap[hero.x-1][hero.y] =="*")return false;
			if (currentMap[hero.x][hero.y+1] =="*")return false;
			if (currentMap[hero.x][hero.y-1] =="*")return false;
		}
		else if (hero.y==currentMap.length) {
			if (currentMap[hero.x-1][hero.y] =="O")return false;
			if (currentMap[hero.x+1][hero.y] =="O")return false;
			if (currentMap[hero.x][hero.y-1] =="O")return false;
			if (currentMap[hero.x-1][hero.y] =="*")return false;
			if (currentMap[hero.x+1][hero.y] =="*")return false;
			if (currentMap[hero.x][hero.y-1] =="*")return false;
		}
		else {
		if (currentMap[hero.x-1][hero.y] =="O")return false;
		if (currentMap[hero.x][hero.y+1] =="O")return false;
		if (currentMap[hero.x+1][hero.y] =="O")return false;
		if (currentMap[hero.x][hero.y-1] =="O")return false;
		if (currentMap[hero.x-1][hero.y] =="*")return false;
		if (currentMap[hero.x][hero.y+1] =="*")return false;
		if (currentMap[hero.x+1][hero.y] =="*")return false;
		if (currentMap[hero.x][hero.y-1] =="*")return false;
		}
		return true;
	}
}

