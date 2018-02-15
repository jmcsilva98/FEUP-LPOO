package firstProject;

import java.util.*;

public class Task2 {

	public static void main(String[] args) {
		int x = 1, y = 1;
		while(true) {
		System.out.println("Please input the character commands (U/D/L/R)");
		
		Scanner s = new Scanner(System.in);
		String move = s.nextLine();		

		

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
			
		if(matrix[x][y] == " ") {
			matrix[x][y] = "H";

		}
		
		for(int i = 0; i < 10; i++)
		{
			for(int j = 0; j < 10; j++) {
				System.out.print(matrix[i][j]);
			}
			System.out.print("\n");
		}
	
					
		
		
	}
	}
}
