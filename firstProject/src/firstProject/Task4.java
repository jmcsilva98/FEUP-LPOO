package firstProject;

import java.util.Scanner;

public class Task4 {

	public static void main(String[] args) {

		String[][] matrix ={{"X", "X", "X", "X", "X", "X", "X", "X", "X", "X"},
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

		while(matrix[x-1][y] != "G" && matrix[x][y+1] != "G" && matrix[x+1][y] != "G" && matrix[x][y-1] != "G") {
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



			matrix[xn][yn] = " ";
			matrix[xnG][ynG] = " ";

			if(matrix[x][y] == " ") {
				matrix[x][y] = "H";
			}
			else {
				if(matrix[x][y] == "K"){
					matrix[5][0] = "S";
					matrix[6][0] = "S";
					matrix[x][y] = "H";
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

				matrix[xG][yG] = "G";
				length++;

			}




			for(int i = 0; i < 10; i++)
			{
				for(int j = 0; j < 10; j++) {
					System.out.print(matrix[i][j]);
				}
				System.out.print("\n");
			}


			xn = x;
			yn = y;
			xnG = xG;
			ynG = yG;
			
		}
		s.close();
		System.out.println("Game over!!");
	}


}
