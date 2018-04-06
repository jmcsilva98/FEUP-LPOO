package dkeep.logic;

import java.util.ArrayList;
import java.util.Random;

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
	/**
	 * Gamestate constructor
	 */
	public Gamestate() {
		this.currentMap=Map.getMap(1);
		level=1;
	}
	/**
	 * Gamestate constructor
	 * @param map actual map
	 */

	public Gamestate(Map map) {
		this.currentMap=map.getMap();	
		this.level=0;

	}
	/**
	 * Gamestate constructor
	 * @param map actual map
	 */
	public Gamestate(String [][] map) {
		this.currentMap = map;
	}
	/**
	 * Function to verify if player won the game 
	 * @return true if game won or false if not 
	 */

	public boolean gameWon() {
		return gameWon;
	}
	/**
	 * Function to verify if hero is armed
	 * @return true if hero is armed
	 */

	public boolean heroIsArmed() {
		if(level == 2) return true;
		return false; 
	}
	/**
	 * Function to set level manualy
	 * @param level to be setted
	 */


	public void setLevelManualy(int level) {
		this.level=level;
	}
	/**
	 * Function to set level
	 * @param level to be setted
	 */
	public void setLevel(int level) {
		this.currentMap=Map.getMap(level);
		this.level=level;
	}
	/**
	 * Function to set map 
	 * @param map to be setted
	 */
	public void setMap(String[][]map) {
		this.currentMap=map;
	}
	/**
	 * Function to get map
	 * @return current map
	 */
	public String[][] getMap() {
		return currentMap;
	}
	/**
	 * Function to get level
	 * @return current level
	 */
	public int getLevel() {
		return level;
	}
	/**
	 * Function to get ogre
	 * @return ogre
	 */

	public Ogre getOgre() {
		return ogre;
	}
	/**
	 * Function to get array of ogres
	 * @return ogres array
	 */
	public ArrayList<Ogre> getOgres() {
		return ogres;
	}
	/**
	 * Function to get hero
	 * @return hero
	 */
	public Hero getHero() {
		return hero;
	}
	/**
	 * Function to set hero
	 * @param hero to be setted
	 */
	public void setHero(Hero hero) {
		this.hero=hero;
	}
	/**
	 * Function to set ogre
	 * @param ogre to be setted and added to the array
	 */
	public void setOgre(Ogre ogre) {
		this.ogre = ogre;
		ogres.add(ogre);
	}
	/**
	 * Function to set ogres 
	 * @param ogres Array of ogres 
	 */

	public void setOgres(ArrayList<Ogre> ogres) {

		Random n = new Random();

		int numberOfOgres = n.nextInt(3) + 1;
		for(int i = 0; i <numberOfOgres; ++i) {
			Ogre ogre = new Ogre();
			Character club= new Character();
			club.setX(2);
			club.setY(4);
			club.updatePosition();
			ogre.setX(1);
			ogre.setY(4);
			ogre.updatePosition();
			ogre.setClub(club);
			ogres.add(ogre);
		}
		this.ogres=ogres;
	}
	/**
	 * Function to create specific number of ogres
	 * @param numberOfOgres number of ogres to be created
	 */
	public void setOgres(int numberOfOgres) {
		for(int i = 0; i < numberOfOgres; i++) {
			Ogre ogre = new Ogre();
			Character club = new Character();
			club.setX(2);
			club.setY(4);
			club.updatePosition();
			ogre.setX(1);
			ogre.setY(4);
			ogre.updatePosition();
			ogre.setClub(club);
			ogres.add(ogre);
		}

		this.ogres=ogres;
	}
	/**
	 * Function to set guard
	 * @param guard guard to be setted
	 */

	public void setGuard(Guard guard) {
		this.guard=guard;
	}
	/**
	 * Function to parse map to string
	 * @return map's string
	 */
	public String toString() {
		int n;
		String map="";
		if (level==1) {
			n=10;
			testCase = true;
		}
		else 
			n=9; 
		n=currentMap.length;
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				map+=currentMap[i][j];
			} 
			map+="\n";
		}
		return map;
	}
	/**
	 * Function to start game in console
	 */

	public void startConsole() {
		ArrayList <Ogre> ogres= new ArrayList<Ogre>();
		int aux =Guard.randomGenerator(3);
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
	/**
	 * Function to start game in application
	 * @param guard personality choosed
	 * @param numberOgres number of ogres choosed
	 */
	public void startApplication(String guard, int numberOgres) {
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
				break;

			}
		}
		if (level!=0)
			setOgres(numberOgres);

	}
	/**
	 * Function to start game
	 * @param application true if game is in application
	 * @param gua guard personality
	 * @param numberOgres of ogres
	 */
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
	/**
	 * State of the game
	 * @param n is 0 if game over or 1 if game won
	 */
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
	/**
	 * Function to verify if game over
	 * @return true if game over
	 */
	public boolean isGameOver() {
		return gameOver;
	}
	/**
	 * Function to move hero
	 * @param move direction to move hero
	 */
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
	/**
	 * Function to open doors in map
	 */
	public void heroChangeK() {
		if (level==1) {
			currentMap[5][0] = "S";
			currentMap[6][0] = "S";
			hero.hasKey=true;
		}
		else if (level==2)
			currentMap[1][0]="S";
		else 
			whereIsDoor();
		currentMap[hero.x][hero.y]="A";
		hero.hasKey=true;
	}
	/**
	 * Function to verify if hero has key when he is on the door
	 */
	public void changeS() {
		if(hero.hasKey) {
			currentMap[hero.x][hero.y]="S";
			gameState(1);
		}
		else {
			heroDefaultMovement();
		}
	}
	/**
	 * Function to open doors when hero catches the key
	 */
	public void whereIsDoor() {
		for (int i =0;i<currentMap.length;i++)
			for (int j=0;j<currentMap.length;j++)
				if (currentMap[i][j]=="I")
					currentMap[i][j]="S";
	}
	/**
	 * Function to change level
	 */
	public void heroChangeLevel() {
		if(level == 1) {
			setLevel(2);

			hero.setX(7);
			hero.setY(1);
		} 
		else 
			gameWon=true;
	}
	/**
	 * Function to verify if hero is armed 
	 */
	public void heroHasKey() {
		if(hero.hasKey) {
			currentMap[hero.x][hero.y]="A";
		}
		else {
			currentMap[hero.x][hero.y]="H";
		}
	}
	/**
	 * Function to move guard
	 */

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

/**
 * Function to move ogre
 * @return ogre's direction
 */

	public String ogreMovement() {
		String ret="";
		if (ogres.size()==0) return ret;
		for (Ogre ogre : ogres) {
			currentMap[ogre.x][ogre.y]=" ";
			currentMap[ogre.xn][ogre.yn]=" ";
			if (ogre.isStunned) {
				ogre.symbol="8";
				ogre.stunCounter++;
				if (ogre.stunCounter==2) {
					ogre.isStunned=false;
					ogre.stunCounter=0;
				}
			}
			else {

				ret=ogre.movement();
				if (currentMap[ogre.x][ogre.y]!=" ") {
					ogre.x=ogre.xn;
					ogre.y=ogre.yn;
				}
				if (currentMap[ogre.x][ogre.y]=="k") {
					ogre.symbol="$";
				}

			}
			currentMap[ogre.getClub().xn][ogre.getClub().yn]=" ";
			newPositionClub(ogre);
			ogre.getClub().updatePosition();
			currentMap[ogre.getClub().x][ogre.getClub().y]="*";
			currentMap[ogre.x][ogre.y]=ogre.symbol;
			ogre.updatePosition();

		}
		return ret;

	}
	/**
	 * Function to verify if hero is in adjacent position of guard
	 * @return true if hero is in adjacent position of guard
	 */

	public boolean isFreeGuard() {
		if (hero.y==0) {
			if (!isFreeGuardY()) return false;
		}
		else if (!isFreeDefault("G"))return false;
		else if (!isFreeDefault("g")) return false;

		return true;
	}
	/**
	 * Function to verify if hero is in adjacent position of guard when y=0
	 * @return true if hero is in adjacent position of guard
	 */
	public boolean isFreeGuardY() {

		if (currentMap[hero.x-1][hero.y] =="G")return false;
		if (currentMap[hero.x][hero.y+1] =="G")return false;
		if (currentMap[hero.x+1][hero.y] =="G")return false;
		if (currentMap[hero.x-1][hero.y] =="g")return false;
		if (currentMap[hero.x][hero.y+1] =="g")return false;
		if (currentMap[hero.x+1][hero.y] =="g")return false;
		return true;
	}
	/**
	 * Function to verify if hero is in adjacent position of ogre
	 * @return true if hero is in adjacent position of ogre
	 */
	public boolean isFreeOgre() {

		if (!isFreeOgreX()) return false;

		else if (!isFreeOgreY()) return false;

		else if (!isFreeDefault("O"))return false;
		else if (!isFreeDefault("*")) return false;
		return true;
	}
	/**
	 * Function to verify if hero is in adjacent position of guard or ogre(common conditions)
	 * @return true if hero is in adjacent position of guard/ogre
	 */
	public boolean isFreeDefault(String type) {
		if (currentMap[hero.x-1][hero.y]==type)return false;
		if (currentMap[hero.x][hero.y+1] ==type)return false;
		if (currentMap[hero.x+1][hero.y] ==type)return false;
		if (currentMap[hero.x][hero.y-1]==type)return false;
		return true;
	}
	/**
	 * Function to verify if hero is in adjacent position of ogre when hero.x=0 or hero.x = map length-1
	 * @return true if hero is in adjacent position of guard
	 */
	public boolean isFreeOgreX() {
		if (!isFreeOgreX0()) return false;

		else if (! isFreeOgreLength()) return false;
		return true;
	}
	/**
	 * Function to verify if hero is in adjacent position of guard when hero.x=0
	 * @return true if hero is in adjacent position of guard
	 */
	public boolean isFreeOgreX0() {
		if (hero.x==0) {
			if (!isFreeOgreX0Default("O")) return false;
			if (!isFreeOgreX0Default("*")) return false;
		}
		return true;
	}
	/**
	 * Function to verify if hero is in adjacent position of guard/ogre when hero.x=0
	 * @return true if hero is in adjacent position of guard
	 */
	public boolean isFreeOgreX0Default(String type) {
		if (currentMap[hero.x][hero.y+1] ==type)return false;
		else if (currentMap[hero.x+1][hero.y] ==type)return false;
		else if (currentMap[hero.x][hero.y-1] ==type)return false;

		return true;
	}
	/**
	 * Function to verify if hero is in adjacent position of ogre when hero.y= map.length-1
	 * @return true if hero is in adjacent position of ogre
	 */
	public boolean isFreeOgreLength() {
		if (hero.x == currentMap.length-1) {
			if (!isFreeOgreLengthDefault("O")) return false;
			if (!isFreeOgreLengthDefault("*")) return false;
		}
		return true;
	}
	/**
	 * Function to verify if hero is in adjacent position of ogre
	 * @return true if hero is in adjacent position of ogre
	 */
	public boolean isFreeOgreLengthDefault(String type) {
		if (currentMap[hero.x-1][hero.y] ==type)return false;
		else if (currentMap[hero.x][hero.y+1] ==type)return false;
		else if (currentMap[hero.x][hero.y-1] ==type)return false;
		return true;
	}
	/**
	 * Function to verify if hero is in adjacent position of guard when y=0 or y =map.length
	 * @return true if hero is in adjacent position of ogre
	 */
	public boolean isFreeOgreY() {

		if (!isFreeOgreY0()) return false;
		if (!isFreeOgreYLength()) return false;
		return true;
	}
	/**
	 * Function to verify if hero is in adjacent position of ogre when y=0
	 * @return true if hero is in adjacent position of ogre
	 */
	public boolean isFreeOgreY0() {
		if (hero.y==0) {
			if (!isFreeOgreY0Default("O")) return false;
			if (!isFreeOgreY0Default("*")) return false;
		}
		return true;

	}
	/**
	 * Function to verify if hero is in adjacent position of ogre when y=0
	 * @return true if hero is in adjacent position of guard
	 */
	public boolean isFreeOgreY0Default(String type) {
		if (currentMap[hero.x-1][hero.y] ==type)return false;
		else if (currentMap[hero.x][hero.y+1] ==type)return false;
		else if (currentMap[hero.x+1][hero.y] ==type)return false;
		return true;
	}
	/**
	 * Function to verify if hero is in adjacent position of ogre when y= length of map -1
	 * @return true if hero is in adjacent position of ogre
	 */
	public boolean isFreeOgreYLength() {
		if (hero.y==currentMap.length-1) {
			if (!isFreeOgreYLengthDefault("O")) return false;
			if (!isFreeOgreYLengthDefault("*")) return false;

		}
		return true;
	}
	/**
	 * Function to verify if hero is in adjacent position of ogre when y= length of map -1
	 * @param type club or ogre
	 * @return true if hero is in adjacent position of ogre
	 */
	public boolean isFreeOgreYLengthDefault(String type) {
		if (currentMap[hero.x-1][hero.y] ==type)return false;
		else if (currentMap[hero.x+1][hero.y] ==type)return false;
		else if (currentMap[hero.x][hero.y-1] ==type)return false;
		return true;
	}
	/**
	 * Function to calculate new position of club 
	 * @param ogre 
	 */
	public void newPositionClub(Ogre ogre) {
		if ( ogre.x>0 && currentMap[ogre.x-1][ogre.y]==" ") {
			ogre.getClub().x=ogre.x-1;
			ogre.getClub().y=ogre.y;
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
	/**
	 * Function to aux hero movement
	 */
	public void heroDefaultMovement() {
		hero.x=hero.xn;
		hero.y=hero.yn;
		if(level ==1)
			currentMap[hero.x][hero.y]="H";
		else
			currentMap[hero.x][hero.y]="A";
	}
/**
 * Function to get guard
 * @return guard
 */
	public Guard getGuard() {
		return guard;
	}

}



