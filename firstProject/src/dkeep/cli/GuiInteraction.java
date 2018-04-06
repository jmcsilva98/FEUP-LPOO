package dkeep.cli;

import dkeep.logic.*;

public class GuiInteraction {

	private Gamestate game;
	private String guard="Rookie";
	public int numberOgres=1;

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
		
		deleteClub();
		if (game.getLevel()==1 &&game.isFreeGuard()) {
			game.guardMovement();
			game.heroMovement(move);
			return true;
		}
		if (!game.isFreeGuard()) {
		return false;

		}
		else if (game.getLevel()==2 && game.isFreeOgre()) {
			deleteGuard();
			game.heroMovement(move);
			game.ogreMovement();
			return true;
		}
		
		else if (game.isFreeOgre()) {
			game.heroMovement(move);
			game.ogreMovement();
			return true;
		}
		return false;

	}
	public void deleteGuard() {
		for (int i =0;i<game.getMap().length;i++)
			for(int j=0;j<game.getMap().length;j++)
				if (game.getMap()[i][j]=="G")
					game.getMap()[i][j]=" ";
	}
	public void setNumberOgres(int numberOgres) {
		this.numberOgres=numberOgres;

	}
	public void setGuard(String guard) {
		this.guard=guard;
	}
	public void deleteClub() {
		for (int i =0;i<game.getMap().length;i++)
			for(int j=0;j<game.getMap().length;j++)
				if (game.getMap()[i][j]=="*")
					game.getMap()[i][j]=" ";
		
	}
public int getNumberOgres() {
	return numberOgres;
}
	public static void main(String[] args) {
		GuiInteraction new_game=new GuiInteraction();
	}

}
