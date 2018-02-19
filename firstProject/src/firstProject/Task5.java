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

		String[][] secondlevel = {{"X", "X", "X", "X", "X", "X", "X", "X", "X"},
							   {"I", " ", " ", " ", "O", " ", " ", "K", "X"},
							   {"X", " ", " ", " ", " ", " ", " ", " ", "X"},
							   {"X", " ", " ", " ", " ", " ", " ", " ", "X"},
							   {"X", " ", " ", " ", " ", " ", " ", " ", "X"},
							   {"X", " ", " ", " ", " ", " ", " ", " ", "X"},
							   {"X", " ", " ", " ", " ", " ", " ", " ", "X"},
							   {"X", "H", " ", " ", " ", " ", " ", " ", "X"},
							   {"X", "X", "X", "X", "X", "X", "X", "X", "X"}};

		int x = 1, y = 1;
		int xG = 1, yG = 8;
		int xn = x, yn = y;
		int xnG = xG, ynG = yG;


		String[] pos = {"0", "L", "D", "D","D","D", "L",  "L", "L", "L", "L", "L", "D", "R", "R", "R", "R", "R", "R", "R", "U","U","U","U" };

		int length = 1;



		while(firstlevel[x-1][y] != "G" && firstlevel[x][y+1] != "G" && firstlevel[x+1][y] != "G" && firstlevel[x][y-1] != "G") {
			System.out.println("Please input the character commands (U/D/L/R)");

			Scanner s = new Scanner(System.in);
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
			else {
				if(firstlevel[x][y] == "K"){
					firstlevel[5][0] = "S";
					firstlevel[6][0] = "S";
					firstlevel[x][y] = "H";
				}
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




			for(int i = 0; i < 10; i++)
			{
				for(int j = 0; j < 10; j++) {
					System.out.print(firstlevel[i][j]);
				}
				System.out.print("\n");
			}


			xn = x;
			yn = y;
			xnG = xG;
			ynG = yG;

		}

		System.out.println("Game over!!");
	}

}
