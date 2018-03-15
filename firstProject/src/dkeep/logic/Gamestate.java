package dkeep.logic;

import java.util.Random;
import java.util.Vector;

public class Gamestate {
	String[][] current_map;
	String map;
	int level;
	int stunCounter = 0;
	boolean gameWon=false;
	Vector<Ogre> ogres = new Vector<Ogre>(); //Using java colletion (vector) to store ogres
	boolean gameOver=false;
	Ogre ogre;
	Guard guard;
	Hero hero;

	public Gamestate() {
		this.current_map=Map.getMap(1);
		level=1;

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
		this.current_map=Map.getMap(level);
		this.level=level;
	}

	public String[][] get_map() {
		return current_map;
	}
	public String getMap() {
		return map;
		
		
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
	public Hero getHero() {
		return hero;
	}
	public void setHero(Hero hero) {
		this.hero=hero;
	}
	public void set_ogre(Ogre ogre) {
		this.ogre = ogre;
	}
	

	public void setOgres(int numberOgres) {

		ogres.setSize(numberOgres);

		for(int i = 0; i < ogres.size(); i++) {
			
			Ogre ogre = new Ogre();
			ogres.setElementAt(ogre,i);
		}
	}


	public void set_guard(Guard guard) {
		this.guard=guard;
	}

	public String toStr() {
		int n;
		String map;
		if (level==1)
			n=10;
		else 
			n=9; 
		{
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < n; j++) {
					this.map+=current_map[i][j];
				}
				this.map+="\n";
			}
	}
		return this.map;
	}
	public void start() {
		Hero hero = new Hero();
		Vector<Ogre> ogres = new Vector<Ogre>();
		int aux =Guard.randomGenerator(3);
		switch(aux) {
		case 0:
			RookieGuard rookie=new RookieGuard();
			System.out.println("Guard: rookie");
			set_guard(rookie);
			break;
		case 1:
			DrunkenGuard drunken=new DrunkenGuard();
			System.out.println("Guard: drunken");
			set_guard(drunken);
			break;
		case 2:
			SuspiciousGuard suspicious=new SuspiciousGuard();
			System.out.println("Guard: suspicious");
			set_guard(suspicious);
			break;
		
		}
		if (level==1) {
			hero.set_x(1);
			hero.set_y(1); 
			guard.set_x(1);
			guard.set_y(9);

		}
		else {
			
			//generate_ogres(ogres); //generates the vector of ogres
			this.ogre=new Ogre();
			ogre.set_x(1);
			ogre.set_y(2);
			/*for(int i = 0; i < ogres.size(); i++) {			
				ogres.elementAt(i).set_x(1);	//Assuming that the ogres all start at the same position
				ogres.elementAt(i).set_y(4);
				set_ogre(ogres.elementAt(i));
			}*/
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
		current_map[guard.xn][guard.yn]=" ";
		guard.rookie_movement();
		current_map[guard.x][guard.y]="G";
		guard.update_position();
	}

	public String ogre_movement() {
		int i;
		String ret="";
		ret=ogre.movement();
		current_map[ogre.x][ogre.y]=" ";
		current_map[ogre.x][ogre.y]= "O";
		ogre.update_position();
		/*for(i = 0; i < ogres.size(); i++) {
			ret=ogres.elementAt(i).movement();
			current_map[ogres.elementAt(i).xn][ogres.elementAt(i).yn]=" ";
			current_map[ogres.elementAt(i).x][ogres.elementAt(i).y]="O";
			ogres.elementAt(i).update_position();
		}*/
		return ret;

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

