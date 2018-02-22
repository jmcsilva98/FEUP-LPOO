package dkeep.logic;

public class Character{
	int x,y;
	int xn,yn; 
	public int get_x() {
		return x;
	}
	public int get_y() {
		return y;
	}
	public int get_xn() {
		return xn;
	}

	public int get_yn() {
		return yn;
	}
	public void set_x(int x) {
		this.x=x;
	}
	public void set_y(int y) {
		this.y=y;
	}

	void update_position() {

		xn=x;
		yn=y;
	}

}
