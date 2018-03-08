package dkeep.logic;

public class Map {
	String[][] map;
	static String[][] map1={		
			{"X", "X", "X", "X", "X", "X", "X", "X", "X", "X"},
			{"X", "H", " ", " ", "I", " ", "X", " ", " ", "X"},
			{"X", "X", "X", " ", "X", "X", "X", " ", " ", "X"},
			{"X", "I", " ", " ", "I", " ", "X", " ", " ", "X"},
			{"X", "X", "X", " ", "X", "X", "X", " ", " ", "X"},
			{"I", " ", " ", " ", " ", " ", " ", " ", " ", "X"},
			{"I", " ", " ", " ", " ", " ", " ", " ", " ", "X"},
			{"X", "X", "X", " ", "X", "X", "X", "X", " ", "X"},
			{"X", " ", "I", " ", "I", " ", "X", "k", " ", "X"},
			{"X", "X", "X", "X", "X", "X", "X", "X", "X", "X"},
	};
	static  String[][] map2={{"X", "X", "X", "X", "X", "X", "X", "X", "X"},
			{"I", " ", " ", " ", " ", " ", " ", "k", "X"},
			{"X", " ", " ", " ", " ", " ", " ", " ", "X"},
			{"X", " ", " ", " ", " ", " ", " ", " ", "X"},
			{"X", " ", " ", " ", " ", " ", " ", " ", "X"},
			{"X", " ", "C", " ", " ", " ", " ", " ", "X"},
			{"X", " ", " ", " ", " ", " ", " ", " ", "X"},
			{"X", "H", " ", " ", " ", " ", " ", " ", "X"},
			{"X", "X", "X", "X", "X", "X", "X", "X", "X"}};

	public static String[][] getMap(int level){
		if (level==1)
			return map1;
		else
			return map2;
	}
	public Map(String[][]map){
		this.map=map;

	}
	
	public String[][]getMap() {
		
		return this.map;
	}
}
