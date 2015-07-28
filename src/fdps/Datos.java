package fdps;

import java.util.Random;

public abstract class Datos implements IDatos {

	protected int x = 0;
	protected float y = 0;
	
	protected int a;
	protected int b;
	protected float m;
	
	protected void calculateValues(){
		x = (int) (a + (b - a) * new Random().nextFloat());
		y = m * new Random().nextFloat();
	}
	
	public float getNextValue(float t) {
		do {
			calculateValues();
		} while (calculateFy() >= y);
		return x;
	}
	
	protected abstract float calculateFy();
	
}
