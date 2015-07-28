package fdps;

public class DatosTA extends Datos {

	public DatosTA() {
		a = 25;
		b = 80;
		m = 0.42f;
	}
	
	@Override
	protected float calculateFy() {
		// (0.47444*((x-a)^-0.52556))/((b-a)^0.47444)
		float val = x - a;		
		val = (float) Math.pow(val, -0.52556f);
		val *= 0.47444f;
		float val2 = (float) Math.pow((b-a), 0.47444);
		val /= val2;
		return 0;
	}

}
