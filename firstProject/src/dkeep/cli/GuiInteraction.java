package dkeep.cli;

import dkeep.logic.*;

public class GuiInteraction {

	private Gamestate game;
	private String guard;
	public int numberOgres;

	public GuiInteraction() {
		this.game =new Gamestate();

	}
	
	public void start(String guard, int numberOgres) {

		this.guard=guard;
		this.numberOgres=numberOgres;
		game.start(true,guard, numberOgres);

	}
	public Gamestate getGame() {
		return game;
	}

	public boolean checkGameStatus(String move) {

		if (game.getLevel()==1 &&game.isFreeGuard()) {
			game.heroMovement(move);
			game.guardMovement();
			return true;
		}
		if (game.getLevel()==2 && game.isFreeOgre()) {
			game.heroMovement(move);
			game.ogreMovement();
			return true;
		}
		else if (game.isFreeOgre()) 		return true;
		return false;

	}
	public void setNumberOgres(int numberOgres) {
		this.numberOgres=numberOgres;

	}
	public void setGuard(String guard) {
		this.guard=guard;
	}
public int getNumberOgres() {
	return numberOgres;
}
	public static void main(String[] args) {
		GuiInteraction new_game=new GuiInteraction();
	}

}
