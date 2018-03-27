package dkeep.logic;

public class Hero extends Character {
	public boolean hasKey=false;
	//public boolean isArmed = false;
	 
	public CellPosition position() {
		CellPosition pos= new CellPosition(x,y);
		return pos;
	}
	
}
