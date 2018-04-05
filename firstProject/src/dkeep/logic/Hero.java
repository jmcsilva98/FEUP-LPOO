package dkeep.logic;

public class Hero extends Character {
	public boolean hasKey=false;
	 /**
	  * Function to set hero position
	  * @return position setted
	  */
	public CellPosition position() {
		CellPosition pos= new CellPosition(x,y);
		return pos;
	}
	
}
