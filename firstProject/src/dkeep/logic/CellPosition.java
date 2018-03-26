package dkeep.logic;

import java.util.Objects;

public class CellPosition {

	int x,y;

	public CellPosition(int x,int y){

		this.x=x;
		this.y=y;
	} 
@Override
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
