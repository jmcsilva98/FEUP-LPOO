package dkeep.logic;

public class SuspiciousGuard extends Guard {
	public void suspicious_movement() {
		int move =randomGenerator(5);
		int x_aux,y_aux;
		if(move==3 ) {
			if (suspPos==suspLength) {
			x_aux=randomGenerator(2);
			x+=x_aux;
			y=yn;
			if (x_aux>0) suspicousMovement[suspLength]="D";
			else 
				suspicousMovement[suspLength]="U";
			suspLength++;
			suspPos++;
		}
			else
			{
				suspiciousBack=false;
				movement(suspicousMovement[suspPos]);
				suspPos++;
			}
		}
		else if (move==4 ) {
			if (suspPos==suspLength){
			y_aux=randomGenerator(2);
			y+=y_aux;
			x=xn;
			if (y_aux>0) 
				suspicousMovement[suspLength]="R";
			else 
				suspicousMovement[suspLength]="D";
			suspLength++;
			}
			else
			{	suspiciousBack=false;
				movement(suspicousMovement[suspPos]);
				suspPos++;
			}
		}
		else {
			if (!suspiciousBack) {
			suspiciousBack=true;
			}
			suspPos--;
			movement(suspicousMovement[suspPos]);
		}
	}

}
