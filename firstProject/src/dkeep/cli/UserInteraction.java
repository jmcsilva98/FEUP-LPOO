package dkeep.cli;

import java.util.Scanner;

import dkeep.logic.*;

public class UserInteraction {

	String input;
	Gamestate game;
	Scanner s = new Scanner(System.in);
	UserInteraction(){

		this.game =new Gamestate();
		game.start();
	}
	public static void main(String[] args) {
		UserInteraction new_game=new UserInteraction();
		new_game.start();

	}
	void start() {
		game.start();
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
	void print_map(String[][]map,int n)
	{ 
		if (n==1)
			n=10;
		else 
			n=9; 
		{
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < n; j++) {
					System.out.print(map[i][j]);
				}
				System.out.print("\n");
			}
		}

	}

	void user_input() {
		System.out.println("Please input the character commands (U/D/L/R)");
		String move = s.nextLine();
		System.out.println(move);
		game.hero_movement(move);


	}

}
