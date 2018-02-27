package firstProject;

import java.util.Scanner;

public class Task3 {

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
		int xn = x, yn = y;
		Scanner s = new Scanner(System.in);
		while(matrix[x-1][y] != "G" && matrix[x][y+1] != "G") {
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




			for(int i = 0; i < 10; i++)
			{
				for(int j = 0; j < 10; j++) {
					System.out.print(matrix[i][j]);
				}
				System.out.print("\n");
			}


			xn = x;
			yn = y;
			
			
		}
		s.close();
		System.out.println("Game over!!");
	}

}
