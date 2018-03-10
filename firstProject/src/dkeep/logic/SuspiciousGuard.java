package dkeep.logic;

public class SuspiciousGuard extends Guard {
	public void suspicious_movement() {
		int move =randomGenerator(5);
		int x_aux,y_aux;
		if(move==3 ) {
			if (susp_pos==susp_length) {
			x_aux=randomGenerator(2);
			x+=x_aux;
			y=yn;
			if (x_aux>0) suspicous_movement[susp_length]="D";
			else 
				suspicous_movement[susp_length]="U";
			susp_length++;
			susp_pos++;
		}
			else
			{
				suspicious_back=false;
				movement(suspicous_movement[susp_pos]);
				susp_pos++;
			}
		}
		else if (move==4 ) {
			if (susp_pos==susp_length){
			y_aux=randomGenerator(2);
			y+=y_aux;
			x=xn;
			if (y_aux>0) 
				suspicous_movement[susp_length]="R";
			else 
				suspicous_movement[susp_length]="D";
			susp_length++;
			}
			else
			{	suspicious_back=false;
				movement(suspicous_movement[susp_pos]);
				susp_pos++;
			}
		}
		else {
			if (!suspicious_back) {
			suspicious_back=true;
			}
			susp_pos--;
			movement(suspicous_movement[susp_pos]);
		}
	}

}
