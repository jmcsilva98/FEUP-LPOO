package dkeep.cli;
 
import dkeep.logic.*;

public class GuiInteraction {

	private Gamestate game;
	private String guard;
	private int numberOgres;

	public GuiInteraction() {
		this.game =new Gamestate();
		
	}
	public void start(String guard, int numberOgres) {
		
		this.guard=guard;
		game.start(true,guard, numberOgres);
		
	}
	public Gamestate getGame() {
		return game;
	}

	public boolean checkGameStatus(String move) {
		
		if (game.getLevel()==1 &&game.isFree()) {
			game.heroMovement(move);
			game.guardMovement();
			return true;
		}
		
	
		if (game.getLevel()==2 && game.isFree()) {
			game.heroMovement(move);
			game.ogreMovement();
			return true;
		}
		else if (!game.isFree()) 		return false;
		return true;
		
	}
	public static void main(String[] args) {
		GuiInteraction new_game=new GuiInteraction();
	}
	public void setNumberOgres(int numberOgres) {
		this.numberOgres=numberOgres;
		
	}
	public void setGuard(String guard) {
		this.guard=guard;
	}

}
