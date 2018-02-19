package firstProject;

import java.util.Scanner;

public class Task5 {
	public static void main(String[] args) {

		String[][] firstlevel ={{"X", "X", "X", "X", "X", "X", "X", "X", "X", "X"},
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



		int x = 1, y = 1;
		int xG = 1, yG = 8;
		int xn = x, yn = y;
		int xnG = xG, ynG = yG;


		String[] pos = {"0", "L", "D", "D","D","D", "L",  "L", "L", "L", "L", "L", "D", "R", "R", "R", "R", "R", "R", "R", "U","U","U","U" };

		int length = 1;
		Scanner s = new Scanner(System.in);
		
		print1stLevel(firstlevel);

		while(firstlevel[x-1][y] != "G" && firstlevel[x][y+1] != "G" && firstlevel[x+1][y] != "G" && firstlevel[x][y-1] != "G") {
			System.out.println("Please input the character commands (U/D/L/R)");

			String move = s.nextLine();		




			switch(move) {
			case "U" : x--;
			break;
			case "D" : x++;
			break;
			case "R" : y++;
			break;
			case "L" : y--;
			break;
			default : break;
			}



			firstlevel[xn][yn] = " ";
			firstlevel[xnG][ynG] = " ";

			if(firstlevel[x][y] == " ") {
				firstlevel[x][y] = "H";
			}
			else if(firstlevel[x][y] == "K") {
				firstlevel[5][0] = "S";
				firstlevel[6][0] = "S";
				firstlevel[x][y] = "H";
			}
			else if(firstlevel[x][y] == "S") {
				Second_Level();
			}


			if(length < 24) {
				switch(pos[length]) {
				case "U" : xG--;
				break;
				case "D" : xG++;
				break;
				case "R" : yG++;
				break;
				case "L" : yG--;
				break;
				default : break;
				}

				firstlevel[xG][yG] = "G";
				length++;

			}


			print1stLevel(firstlevel);

			xn = x;
			yn = y;
			xnG = xG;
			ynG = yG;

			
		}
		s.close();
		System.out.println("Game over!!");
		
	}

	public static void Second_Level() {


		String[][] secondlevel = {{"X", "X", "X", "X", "X", "X", "X", "X", "X"},
				{"I", " ", " ", " ", "O", " ", " ", "k", "X"},
				{"X", " ", " ", " ", " ", " ", " ", " ", "X"},
				{"X", " ", " ", " ", " ", " ", " ", " ", "X"},
				{"X", " ", " ", " ", " ", " ", " ", " ", "X"},
				{"X", " ", " ", " ", " ", " ", " ", " ", "X"},
				{"X", " ", " ", " ", " ", " ", " ", " ", "X"},
				{"X", "H", " ", " ", " ", " ", " ", " ", "X"},
				{"X", "X", "X", "X", "X", "X", "X", "X", "X"}};


		int xO = 1, yO = 4;
		int x = 7, y = 1;
		int xOn = xO, yOn = yO;
		int xn = x, yn = y;
		boolean hasKey = false;

		print2ndLevel(secondlevel);

		Scanner s = new Scanner(System.in);
		
		while(secondlevel[x-1][y] != "O" && secondlevel[x][y+1] != "O" && secondlevel[x+1][y] != "O" && secondlevel[x][y-1] != "O") {
			System.out.println("Please input the character commands (U/D/L/R)");

			String move = s.nextLine();		


			switch(move) {
			case "U" : x--;
			break;
			case "D" : x++;
			break;
			case "R" : y++;
			break;
			case "L" : y--;
			break;
			default : break;
			}


			secondlevel[xn][yn] = " ";
			secondlevel[xOn][yOn] = " ";

			if(secondlevel[x][y] == " ") {
				if(hasKey) {
					secondlevel[x][y] = "K";
				}
				else {
					secondlevel[x][y] = "H";
				}
			}
			else if(secondlevel[x][y] == "k") {
				hasKey = true;
				secondlevel[x][y] = "K";
			}
			else if(secondlevel[x][y] == "I" && hasKey) {
				secondlevel[x][y] = "S";
				print2ndLevel(secondlevel);
				System.out.println("Game Won!!");
				System.exit(0);
			}

			print2ndLevel(secondlevel);

			xn = x;
			yn = y;
			xOn = xO;
			yOn = yO;
			
			

		}
		s.close();
		System.out.println("Game over!!");
		
	}

	public static void print1stLevel(String[][] firstlevel) {
		for(int i = 0; i < 10; i++)
		{
			for(int j = 0; j < 10; j++) {
				System.out.print(firstlevel[i][j]);
			}
			System.out.print("\n");
		}
	}

	public static void print2ndLevel(String[][] secondlevel) {
		for(int i = 0; i < 9; i++)
		{
			for(int j = 0; j < 9; j++) {
				System.out.print(secondlevel[i][j]);
			}
			System.out.print("\n");
		}
	}
	
}