package dkeep.cli;

import java.util.Scanner;

import dkeep.logic.*;

public class UserInteraction {

	String input;
	Gamestate game;
	Scanner s = new Scanner(System.in);
	public UserInteraction(){
 
		this.game =new Gamestate();
		game.start(false,null,0);
	}
	public static void main(String[] args) {
		UserInteraction new_game=new UserInteraction();
		new_game.start();
		
	
	}
	public void start() {
		while(game.getLevel()==1 &&game.isFreeGuard()) {
			print_map(game.getMap(),game.getLevel());
			user_input();
			game.guardMovement();
		}
		if (!game.isFreeGuard()) {
			System.out.println("GAME OVER!");
			return;

		}
		//game.start(false,null,0);
		for (int i =0;i<game.getMap().length;i++) {
			for (int j =0;j<game.getMap().length;j++)
				if (game.getMap()[i][j]=="G")
					game.getMap()[i][j]=" ";
		}
		
		while(game.getLevel()==2&&game.isFreeOgre()&& !game.gameWon) {
			print_map(game.getMap(),game.getLevel());
			user_input();
			game.ogreMovement();
		}
		if (!game.isFreeOgre()) {
			System.out.println("GAME OVER!");
			return;

		}
		else 
			System.out.println("GAME WON!");
	}
	public 	void print_map(String[][]map,int n)
	{ 
		if (n==1) n=10;
		else n=9;
		map[0][0]="X";
		for (int i = 0 ; i<n; i++) {
			for (int j =0 ; j<n;j++) {
				System.out.print(map[i][j]);
			}
			System.out.print("\n");
		}
	

	}
	public String toString() {
		return game.toString();
	}
	public Gamestate getGame() {
		return game;
	}
	public 	void user_input() {
		System.out.println("Please input the character commands (U/D/L/R)");
		String move = s.nextLine();
		game.heroMovement(move);
	}
}
