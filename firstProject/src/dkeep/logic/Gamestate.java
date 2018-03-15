package dkeep.logic;

import java.util.Random;
import java.util.Vector;

public class Gamestate {

	String[][] currentMap;
	int level;
	int stunCounter = 0;
	boolean gameWon=false;
	Vector<Ogre> ogres = new Vector<Ogre>(); //Using java colletion (vector) to store ogres
	boolean gameOver=false;
	Ogre ogre;
	Guard guard;
	Hero hero;

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

	public Gamestate(String [][] map) {
		this.currentMap = map;
	}


	public void setLevel(int level) {
		this.currentMap=Map.getMap(level);
		this.level=level;
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
	public Vector<Ogre> get_ogres() {
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


	public void setOgres(Vector<Ogre> ogres) {

		Random n = new Random();
		int numberOfOgres = n.nextInt(3) + 1;
		ogres.setSize(numberOfOgres);

		for(int i = 0; i < ogres.size(); i++) {

			Ogre ogre = new Ogre();
			ogres.setElementAt(ogre,i);
		}
	}
	public void setOgres(int numberOfOgres) {

		ogres.setSize(numberOfOgres);

		for(int i = 0; i < ogres.size(); i++) {

			Ogre ogre = new Ogre();
			ogres.setElementAt(ogre,i);
		}
	}


	public void setGuard(Guard guard) {
		this.guard=guard;
	}

	public String toStr() {
		int n;
		String map="";
		if (level==1)
			n=10;
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


	public void start() {
		Hero hero = new Hero();
		Vector<Ogre> ogres = new Vector<Ogre>();
		int aux =Guard.randomGenerator(3);
		switch(aux) {
		case 0:
			RookieGuard rookie=new RookieGuard();
			System.out.println("Guard: rookie");
			setGuard(rookie);
			break;
		case 1:
			DrunkenGuard drunken=new DrunkenGuard();
			System.out.println("Guard: drunken");
			setGuard(drunken);
			break;
		case 2:
			SuspiciousGuard suspicious=new SuspiciousGuard();
			System.out.println("Guard: suspicious");
			setGuard(suspicious);
			break;

		}
		if (level==1) {
			RookieGuard rookie=new RookieGuard();
			setGuard(rookie);
			hero.setX(1);
			hero.setY(1); 
			guard.setX(1);
			guard.setY(8);
		}
		else {

			setOgres(ogres); //generates the vector of ogres
			this.ogre=new Ogre();
			ogre.setX(1);
			ogre.setY(2);
			for(int i = 0; i < ogres.size(); i++) {			
				ogres.elementAt(i).setX(1);	//Assuming that the ogres all start at the same position
				ogres.elementAt(i).setY(4);
				setOgre(ogres.elementAt(i));
			}
			hero.setX(7);
			hero.setY(1); 
		}

		setHero(hero);


	}
	public void gameState(int n) {
		if (n==0) {
			System.out.println("Game Over!");
			gameOver=true;
		}
		else {
			System.out.println("Game won!!");
			gameWon=true;
			System.exit(0);
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
			else if(hero.isArmed) {
				currentMap[hero.x][hero.y]="A";
			}
			else {
				currentMap[hero.x][hero.y]="H";
			}
			break;
		case "k":
			currentMap[hero.x][hero.y]="K";
			hero.hasKey=true;
			break;

		case "K":
			if (level==1) {
				currentMap[5][0] = "S";
				currentMap[6][0] = "S";
				currentMap[hero.x][hero.y]="H";
			}
			break;
		case "I":
			if(hero.hasKey) {
				currentMap[hero.x][hero.y]="S";
				gameState(1);		
			}
			break;
		case "C":				//Club (hero's weapon)
			currentMap[hero.x][hero.y]="A";
			hero.isArmed = true;
			break;
		case "S":
			if(level == 1) {
				setLevel(2);	
			}
			break;
		default:
			hero.x=hero.xn;
			hero.y=hero.yn;
			currentMap[hero.x][hero.y]="H";
		}
		hero.updatePosition();
	}


	public void guardMovement() {
		currentMap[guard.xn][guard.yn]=" ";
		guard.rookie_movement();
		currentMap[guard.x][guard.y]="G";
		guard.updatePosition();
	}

	public String ogreMovement() {
		int i;
		String ret="";
		ret=ogre.movement();
		currentMap[ogre.x][ogre.y]=" ";
		currentMap[ogre.x][ogre.y]= "O";
		ogre.updatePosition();
		for(i = 0; i < ogres.size(); i++) {
			ret=ogres.elementAt(i).movement();
			currentMap[ogres.elementAt(i).xn][ogres.elementAt(i).yn]=" ";
			currentMap[ogres.elementAt(i).x][ogres.elementAt(i).y]="O";
			ogres.elementAt(i).updatePosition();
		}
		return ret;

	}

	public boolean isFree() {

		if (level==1) {
			if (currentMap[hero.x-1][hero.y] =="G")return false;
			if (currentMap[hero.x][hero.y+1] =="G")return false;
			if (currentMap[hero.x+1][hero.y] =="G")return false;
			if (currentMap[hero.x][hero.y-1] =="G")return false;
			if (currentMap[hero.x-1][hero.y] =="g")return false;
			if (currentMap[hero.x][hero.y+1] =="g")return false;
			if (currentMap[hero.x+1][hero.y] =="g")return false;
			if (currentMap[hero.x][hero.y-1] =="g")return false;

		}
		else if (level==2) {
			if (currentMap[hero.x-1][hero.y] =="O")  
			{
				if(hero.isArmed) {
					currentMap[ogre.x-1][ogre.y] = "8";
					ogre.isStunned = true;
					if(stunCounter < 2) {
						stunCounter++;
					}
					else {
						stunCounter = 0;
						currentMap[ogre.x][ogre.y+1] = "O";
						ogre.isStunned = false;
					}
					return true;
				}
				else return false;
			}

			if (currentMap[hero.x][hero.y+1] =="O")
			{
				if(hero.isArmed) {
					currentMap[ogre.x][ogre.y+1] = "8";
					ogre.isStunned = true;
					if(stunCounter < 2) {
						stunCounter++;
					}
					else {
						stunCounter = 0;
						currentMap[ogre.x][ogre.y+1] = "O";
						ogre.isStunned = false;
					}
					return true;
				}
				else return false;
			}

			if (currentMap[hero.x+1][hero.y] =="O")
			{
				if(hero.isArmed) {
					currentMap[ogre.x+1][ogre.y] = "8";
					stunCounter++;
					ogre.isStunned = true;
					if(stunCounter < 2) {
						stunCounter++;
					}
					else {
						stunCounter = 0;
						currentMap[ogre.x][ogre.y+1] = "O";
						ogre.isStunned = false;
					}

					return true;
				}
				else return false;

			}

			if (currentMap[hero.x][hero.y-1] =="O")
			{
				if(hero.isArmed) {
					currentMap[ogre.x][ogre.y-1] = "8";
					ogre.isStunned = true;
					if(stunCounter < 2) {
						stunCounter++;
					}
					else {
						stunCounter = 0;
						currentMap[ogre.x][ogre.y+1] = "O";
						ogre.isStunned = false;
					}

					return true;
				}
				else return false;
			}		


		}
		return true;
	}

}

