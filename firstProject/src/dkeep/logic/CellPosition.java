package dkeep.logic;

import java.util.Objects;

public class CellPosition {

	public int x,y;
/**
 * Cell position constructor
 * @param x value
 * @param y value
 */
	public CellPosition(int x,int y){

		this.x=x;
		this.y=y;
	} 
@Override
/**
 * Function to compare two cell positions 
 * @param Object to compare
 * @return True if objects are equal or not
 */
public boolean equals(Object obj) {
		if (this==obj) return true;
		
		if (obj==null)
			return false;
		if(getClass() != obj.getClass())
				return false;
		CellPosition cellposition=(CellPosition) obj;
		return Objects.equals(this.x,cellposition.x) &&
				Objects.equals(this.y, cellposition.y);
	}


}
