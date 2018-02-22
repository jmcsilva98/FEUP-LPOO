package dkeep.logic;

public class Gamestate {
	 String[][] current_map;
	 int level;
	Ogre ogre;
	Guard guard;
    Hero hero;
    public Gamestate() {
    	this.current_map=Map.getMap(1);
    	level=1;
 
    }
    public void set_level(int level) {
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
   public  Guard get_guard() {
    	return guard;
    }
   public Hero get_hero() {
    	return hero;
    }
	public void start() {
		
		if (level==1) {
			hero.x=1;
			hero.y=1;
			guard.x=1;
			guard.y=8;
		}
		else {
			ogre.x=1;
			ogre.y=4;
			hero.x=7;
			hero.y=1;
		}
			
		
	}
	public void game_state(int n) {
		if (n==0) System.out.println("Game Over!");
		else {
			System.out.println("Game won!!");
			System.exit(0);
		}
		
	}
	public void hero_movement(String move) {
		hero.movement(move);
		final String aux = " ";
		current_map[hero.xn][hero.yn]=" ";
		switch (current_map[hero.x][hero.y]) {
		case aux:
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
			level=2;
			break;
		default:
			hero.x=hero.xn;
			hero.y=hero.yn;
	}
		hero.update_position();
	}
	
	public void guard_movement() {
		
		guard.movement();
		current_map[guard.x][guard.y]="G";
		current_map[guard.xn][guard.yn]=" ";
		guard.update_position();

		
	}
	
	public void ogre_movement() {
		ogre.movement();
		if (current_map[ogre.x][ogre.y]==" ")
			current_map[ogre.x][ogre.y]="O";
		else
		{
			ogre.x=ogre.xn;
			ogre.y=ogre.yn;
		}
		ogre.update_position();

		
	}
	public boolean isFree() {
		
	if (level==1) {
		if (current_map[hero.x-1][hero.y] =="G")  return false;
		if (current_map[hero.x][hero.y+1] =="G")return false;
		if (current_map[hero.x+1][hero.y] =="G")return false;
		if (current_map[hero.x][hero.y-1] =="G")return false;
	}
	else {
		if (level==2) {
			if (current_map[hero.x-1][hero.y] =="O")  return false;
			if (current_map[hero.x][hero.y+1] =="O")return false;
			if (current_map[hero.x+1][hero.y] =="O")return false;
			if (current_map[hero.x][hero.y-1] =="O")return false;
		}
	}
	return true;
	}
}

