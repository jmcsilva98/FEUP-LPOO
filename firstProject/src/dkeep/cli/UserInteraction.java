package dkeep.cli;

import java.util.Scanner;

import dkeep.logic.*;

public class UserInteraction {

	String input;
	Gamestate game;
	Scanner s = new Scanner(System.in);
	UserInteraction(){
		
		this.game =new Gamestate();
	}
	public static void main(String[] args) {
		UserInteraction new_game=new UserInteraction();
		new_game.start("G");

	}
	private void start(String obstacle) {
		game.start();
		while(game.isFree()) {
			
			print_map(game.get_map(),game.get_level());
			user_input();
			if (obstacle=="G") {
				game.guard_movement();
			}

			else if (obstacle=="O") {
				game.ogre_movement();
			}
		}
		

	}
	void get_map(int level) {

		if (level==1) {
			String[][] firstlevel =
				{		{"X", "X", "X", "X", "X", "X", "X", "X", "X", "X"},
						{"X", " ", " ", " ", "I", " ", "X", " ", "G", "X"},
						{"X", "X", "X", " ", "X", "X", "X", " ", " ", "X"},
						{"X", "I", " ", " ", "I", " ", "X", " ", " ", "X"},
						{"X", "X", "X", " ", "X", "X", "X", " ", " ", "X"},
						{"I", " ", " ", " ", " ", " ", " ", " ", " ", "X"},
						{"I", " ", " ", " ", " ", " ", " ", " ", " ", "X"},
						{"X", "X", "X", " ", "X", "X", "X", "X", " ", "X"},
						{"X", " ", "I", " ", "I", " ", "X", "K", " ", "X"},
						{"X", "X", "X", "X", "X", "X", "X", "X", "X", "X"},
				};
		}
		else
		{
			String[][] secondlevel =
				{{"X", "X", "X", "X", "X", "X", "X", "X", "X"},
						{"I", " ", " ", " ", "O", " ", " ", "k", "X"},
						{"X", " ", " ", " ", " ", " ", " ", " ", "X"},
						{"X", " ", " ", " ", " ", " ", " ", " ", "X"},
						{"X", " ", " ", " ", " ", " ", " ", " ", "X"},
						{"X", " ", " ", " ", " ", " ", " ", " ", "X"},
						{"X", " ", " ", " ", " ", " ", " ", " ", "X"},
						{"X", "H", " ", " ", " ", " ", " ", " ", "X"},
						{"X", "X", "X", "X", "X", "X", "X", "X", "X"}};

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
		game.get_hero().movement(move);

	}

}
