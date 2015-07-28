package fdps;

public class Reject extends Datos{
	
	public Reject() {
		a = 0;
		b = 250;
		m = 0.35f;
	}
	
	@Override
	public float getNextValue(float st){
		x = (int) st;
		return calculateFy();
	}

	@Override
	protected float calculateFy() {
		//(500-x)/(15x+500)
		float val1 = 500-x;
		float val2 = (10 * x)+500;
		val1 /= val2;
		return val1;
	}
}
