package fdps;

import java.util.Random;

public abstract class Datos implements IDatos {

	protected int x = 0;
	protected float y = 0;
//	protected Random q = new Random();
//	protected Random r = new Random();
	
	protected int a;
	protected int b;
	protected float m;
	
	protected void calculateValues(){
		x = a + (b - a) * new Random().nextInt();
		y = m * new Random().nextInt();
	}
	
	public int getNextValue(float t) {
		do {
			calculateValues();
		} while (calculateFy() >= y);
		return x;
	}
	
	protected abstract float calculateFy();
	
}
