package dkeep.cli;

import java.util.Scanner;

import dkeep.logic.*;

public class UserInteraction {

	String input;
	Gamestate game;
	Scanner s = new Scanner(System.in);
	public UserInteraction(){

		this.game =new Gamestate();
		game.start();
	}
	public static void main(String[] args) {
		UserInteraction new_game=new UserInteraction();
		new_game.start();

	}
	public void start() {
		while(game.get_level()==1 &&game.isFree()) {
			print_map(game.get_map(),game.get_level());
			user_input();
			game.guard_movement();
		}
		if (!game.isFree()) {
			System.out.println("GAME OVER!");
			return;

		}
		game.start();
		while(game.get_level()==2&&game.isFree()) {
			print_map(game.get_map(),game.get_level());
			user_input();
			game.ogre_movement();
		}
		if (!game.isFree()) {
			System.out.println("GAME OVER!");
			return;

		}
	}
	public 	void print_map(String[][]map,int n)
	{ 
		//String print= game.toString(map);
		//System.out.print(print);

	}
	public Gamestate getGame() {
		return game;
	}
	public 	void user_input() {
		System.out.println("Please input the character commands (U/D/L/R)");
		String move = s.nextLine();
		System.out.println(move);
		game.hero_movement(move);


	}

}
