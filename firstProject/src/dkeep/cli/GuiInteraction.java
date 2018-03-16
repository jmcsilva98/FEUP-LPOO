package dkeep.cli;

import dkeep.logic.*;

public class GuiInteraction {

	private Gamestate game;

	public GuiInteraction() {
		this.game =new Gamestate();
		game.start();
	}
	public void start(String guard) {
		
		switch (guard) {
		case "rookie":
			this.game.setGuard(new RookieGuard());
			break;
		case "suspicious":
			this.game.setGuard(new SuspiciousGuard());
			break;
		case "drunken":
			this.game.setGuard(new DrunkenGuard());
			break;
			
		}
		
	}
	public Gamestate getGame() {
		return game;
	}
	public static void main(String[] args) {
		GuiInteraction new_game=new GuiInteraction();
		new_game.start("drunken3");
	}
}
