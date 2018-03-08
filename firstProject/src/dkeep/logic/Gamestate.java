package dkeep.logic;

import java.util.Random;
import java.util.Vector;

public class Gamestate {
	String[][] current_map;
	int level;
	int stunCounter = 0;
	boolean gameWon=false;
	Vector<Ogre> ogres = new Vector<Ogre>(); //Using java colletion (vector) to store ogres
	boolean gameOver=false;
	Ogre ogre;
	Guard rookie_guard;
	Guard drunken_guard;
	Guard suspicious_guard;
	Hero hero;

	public Gamestate() {
		this.current_map=Map.getMap(2);
		level=2;

	}
	public Gamestate(Map map) {
		this.current_map=map.getMap();	

	}
	
	public boolean gameWon() {
		return gameWon;
	}

	public Gamestate(String [][] map) {
		this.current_map = map;
	}


	public void set_level(int level) {
		this.current_map=Map.getMap(2);
		this.level=level;
	}

	public String[][] get_map() {
		return current_map;
	}
	public int get_level() {
		return level;
	}
	public Ogre get_ogre() {
		return ogre;
	}
	public Vector<Ogre> get_ogres() {
		return ogres;
	}
	public  Guard get_rookie_guard() {
		return rookie_guard;
	}
	public Hero getHero() {
		return hero;
	}
	public void setHero(Hero hero) {
		this.hero=hero;
	}
	public void set_ogre(Ogre ogre) {
		this.ogre = ogre;
	}


	public void generate_ogres(Vector<Ogre> ogres) {

		Random n = new Random();
		int numberOfOgres = n.nextInt(3) + 1; //generates a random number to see how many ogres do we have (maximum 3 ogres)

		ogres.setSize(numberOfOgres);

		for(int i = 0; i < ogres.size(); i++) {
			
			Ogre ogre = new Ogre();
			ogre.isMoving=true;
			ogres.setElementAt(ogre,i);
		}

		this.ogres=ogres;
	}


	public void set_rookie_guard(Guard guard) {
		this.rookie_guard=guard;
	}
	public void set_drunken_guard(Guard guard) {
		this.drunken_guard=guard;
	}
	public void set_suspicious_guard(Guard guard) {
		this.suspicious_guard=guard;
	}


	public void start() {
		Hero hero = new Hero();
		Guard rookie_guard=new Guard();
		Guard drunken_guard=new Guard();
		Guard suspicious_guard=new Guard();
		Vector<Ogre> ogres = new Vector<Ogre>();

		if (level==1) {
			hero.set_x(1);
			hero.set_y(1); 
			rookie_guard.set_x(1);
			rookie_guard.set_y(8);
			set_rookie_guard(rookie_guard);
			drunken_guard.set_x(3);
			drunken_guard.set_y(3);
			set_drunken_guard(drunken_guard);
			suspicious_guard.set_x(4);
			suspicious_guard.set_y(3);
			set_suspicious_guard(suspicious_guard);

		}
		else {
			generate_ogres(ogres); //generates the vector of ogres
		
			for(int i = 0; i < ogres.size(); i++) {			
				ogres.elementAt(i).set_x(1);	//Assuming that the ogres all start at the same position
				ogres.elementAt(i).set_y(4);
				set_ogre(ogres.elementAt(i));
			}
			hero.set_x(7);
			hero.set_y(1); 
		}

		setHero(hero);


	}
	public void game_state(int n) {
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

	public void hero_movement(String move) {
		current_map[hero.xn][hero.yn]=" ";
		current_map[hero.x][hero.y]=" ";
		hero.movement(move);

		switch (current_map[hero.x][hero.y]) {
		case " ":
			if(hero.hasKey) {
				current_map[hero.x][hero.y]="K";
			}
			else if(hero.isArmed) {
				current_map[hero.x][hero.y]="A";
			}
			else {
				current_map[hero.x][hero.y]="H";
			}
			break;
		case "k":
			current_map[hero.x][hero.y]="K";
			hero.hasKey=true;
			break;

		case "K":
			if (level==1) {
				current_map[5][0] = "S";
				current_map[6][0] = "S";
				current_map[hero.x][hero.y]="H";
			}
			break;
		case "I":
			if(hero.hasKey) {
				current_map[hero.x][hero.y]="S";
				game_state(1);		
			}
			break;
		case "C":				//Club (hero's weapon)
			current_map[hero.x][hero.y]="A";
			hero.isArmed = true;
			break;
		case "S":
			if(level == 1) {
				set_level(2);	
			}
			break;
		default:
			current_map[hero.x][hero.y]="H";
		}
		hero.update_position();
	}


	public void guard_movement() {
		current_map[rookie_guard.xn][rookie_guard.yn]=" ";
		rookie_guard.rookie_movement();
		current_map[rookie_guard.x][rookie_guard.y]="G";
		rookie_guard.update_position();
		current_map[drunken_guard.xn][drunken_guard.yn]=" ";

		if (drunken_guard.drunken_movement()==2) {
			current_map[drunken_guard.x][drunken_guard.y]="g";
		}
		else 

			drunken_guard.update_position();
		current_map[suspicious_guard.xn][suspicious_guard.yn]=" ";
		current_map[suspicious_guard.x][suspicious_guard.y]="G";
		suspicious_guard.update_position();
	}

	public void ogre_movement() {
		int i;
		for(i = 0; i < ogres.size(); i++) {
			ogres.elementAt(i).movement();
			current_map[ogres.elementAt(i).xn][ogres.elementAt(i).yn]=" ";
			current_map[ogres.elementAt(i).x][ogres.elementAt(i).y]="O";
			ogres.elementAt(i).update_position();
		}

	}

	public boolean isFree() {

		if (level==1) {
			if (current_map[hero.x-1][hero.y] =="G")return false;
			if (current_map[hero.x][hero.y+1] =="G")return false;
			if (current_map[hero.x+1][hero.y] =="G")return false;
			if (current_map[hero.x][hero.y-1] =="G")return false;
			if (current_map[hero.x-1][hero.y] =="g")return false;
			if (current_map[hero.x][hero.y+1] =="g")return false;
			if (current_map[hero.x+1][hero.y] =="g")return false;
			if (current_map[hero.x][hero.y-1] =="g")return false;

		}
		else if (level==2) {
			if (current_map[hero.x-1][hero.y] =="O")  
			{
				if(hero.isArmed) {
					current_map[ogre.x-1][ogre.y] = "8";
					ogre.isStunned = true;
					if(stunCounter < 2) {
						stunCounter++;
					}
					else {
						stunCounter = 0;
						current_map[ogre.x][ogre.y+1] = "O";
						ogre.isStunned = false;
					}
					return true;
				}
				else return false;
			}

			if (current_map[hero.x][hero.y+1] =="O")
			{
				if(hero.isArmed) {
					current_map[ogre.x][ogre.y+1] = "8";
					ogre.isStunned = true;
					if(stunCounter < 2) {
						stunCounter++;
					}
					else {
						stunCounter = 0;
						current_map[ogre.x][ogre.y+1] = "O";
						ogre.isStunned = false;
					}
					return true;
				}
				else return false;
			}

			if (current_map[hero.x+1][hero.y] =="O")
			{
				if(hero.isArmed) {
					current_map[ogre.x+1][ogre.y] = "8";
					stunCounter++;
					ogre.isStunned = true;
					if(stunCounter < 2) {
						stunCounter++;
					}
					else {
						stunCounter = 0;
						current_map[ogre.x][ogre.y+1] = "O";
						ogre.isStunned = false;
					}

					return true;
				}
				else return false;

			}

			if (current_map[hero.x][hero.y-1] =="O")
			{
				if(hero.isArmed) {
					current_map[ogre.x][ogre.y-1] = "8";
					ogre.isStunned = true;
					if(stunCounter < 2) {
						stunCounter++;
					}
					else {
						stunCounter = 0;
						current_map[ogre.x][ogre.y+1] = "O";
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

