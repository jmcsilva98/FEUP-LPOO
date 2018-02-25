package dkeep.logic;

public class Gamestate {
	String[][] current_map;
	int level;
	Ogre ogre;
	Guard rookie_guard;
	Guard drunken_guard;
	Guard suspicious_guard;
	Hero hero;
	public Gamestate() {
		this.current_map=Map.getMap(1);
		level=1;

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
	public  Guard get_rookie_guard() {
		return rookie_guard;
	}
	public Hero get_hero() {
		return hero;
	}
	public void set_hero(Hero hero) {
		this.hero=hero;
	}
	public void set_ogre(Ogre ogre) {
		this.ogre=ogre;
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
		Ogre ogre =new Ogre();
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
			ogre.set_x(1);
			ogre.set_y(4);
			hero.set_x(7);
			hero.set_y(1); 
			set_ogre(ogre);
		}
		set_hero(hero);


	}
	public void game_state(int n) {
		if (n==0) System.out.println("Game Over!");
		else {
			System.out.println("Game won!!");
			System.exit(0);
		}

	}
	public void hero_movement(String move) {
		current_map[hero.xn][hero.yn]=" ";
		hero.movement(move);
		switch (current_map[hero.x][hero.y]) {
		case " ":
			current_map[hero.x][hero.y]="H";
			break;
		case "k":
			if (level==1) {
				current_map[5][0] = "S";
				current_map[6][0] = "S";
				hero.x=hero.xn;
				hero.y=hero.yn;
				current_map[hero.xn][hero.yn]="H";
			}
			else {
				current_map[hero.x][hero.y]="K";
				hero.hasKey=true;
			}
			break;
		case "K":
			if (current_map[hero.x][hero.y]=="I" && hero.hasKey) {
				current_map[hero.x][hero.y]="S";
				game_state(1);	
			}
			break;
		case "S":
			set_level(2);		
			break;
		default:
			hero.x=hero.xn;
			hero.y=hero.yn;
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
			current_map[drunken_guard.x][drunken_guard.y]="G";
		drunken_guard.update_position();
		current_map[suspicious_guard.xn][suspicious_guard.yn]=" ";
		current_map[suspicious_guard.x][suspicious_guard.y]="G";
		suspicious_guard.update_position();
	}

	public void ogre_movement() {
		ogre.movement();
		current_map[ogre.xn][ogre.yn]=" ";
		if (current_map[ogre.x][ogre.y]!=" ") {
			ogre.x=ogre.xn;
			ogre.y=ogre.yn;
		}
		current_map[ogre.x][ogre.y]="O";
		ogre.update_position();
	}
	public boolean isFree() {

		if (level==1) {
			if (current_map[hero.x-1][hero.y] =="G")  return false;
			if (current_map[hero.x][hero.y+1] =="G")return false;
			if (current_map[hero.x+1][hero.y] =="G")return false;
			if (current_map[hero.x][hero.y-1] =="G")return false;
			if (current_map[hero.x-1][hero.y] =="g")  return false;
			if (current_map[hero.x][hero.y+1] =="g")return false;
			if (current_map[hero.x+1][hero.y] =="g")return false;
			if (current_map[hero.x][hero.y-1] =="g")return false;

		}
		else if (level==2) {
			if (current_map[hero.x-1][hero.y] =="O")  return false;
			if (current_map[hero.x][hero.y+1] =="O")return false;
			if (current_map[hero.x+1][hero.y] =="O")return false;
			if (current_map[hero.x][hero.y-1] =="O")return false;
		}
		return true;
	}
}

