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
		while(game.getLevel()==1 &&game.isFree()) {
			print_map(game.getMap(),game.getLevel());
			user_input();
			game.guardMovement();
		}
		if (!game.isFree()) {
			System.out.println("GAME OVER!");
			return;

		}
		game.start();
		while(game.getLevel()==2&&game.isFree()) {
			print_map(game.getMap(),game.getLevel());
			user_input();
			game.ogreMovement();
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
		game.heroMovement(move);


	}

}
