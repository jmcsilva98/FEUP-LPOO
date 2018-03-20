package dkeep.cli;

import dkeep.logic.*;

public class GuiInteraction {

	private Gamestate game;
	private String guard;

	public GuiInteraction() {
		this.game =new Gamestate();
		
	}
	public void start(String guard) {
		
		this.guard=guard;
		game.start(true,guard);
		
	}
	public Gamestate getGame() {
		return game;
	}

	public boolean checkGame(String move) {
		if (game.getLevel()==1 &&game.isFree()) {
			game.heroMovement(move);
			game.guardMovement();
			return true;
		}
		else
		return false;
		
	}
	public static void main(String[] args) {
		GuiInteraction new_game=new GuiInteraction();
	}

}
