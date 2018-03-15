package dkeep.cli;

import dkeep.logic.Gamestate;

public class GuiInteraction {

	private Gamestate game;

	public GuiInteraction() {
		this.game =new Gamestate();
		game.start();
	}
	public void start() {
		
		
	}
	public Gamestate getGame() {
		return game;
	}
	public static void main(String[] args) {
		GuiInteraction new_game=new GuiInteraction();
		new_game.start();
	}
}
