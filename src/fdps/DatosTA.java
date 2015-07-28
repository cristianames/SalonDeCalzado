package fdps;

public class DatosTA extends Datos {

	public DatosTA() {
		a = 15;
		b = 80;
		m = 0.42f;
	}
	
	@Override
	protected float calculateFy() {
		// (0.47444*((x-15)^-0.52556))/((80-15)^0.47444)
		float val = x - 15;
		val = (float) Math.pow(val, -0.52556f);
		val *= 0.47444f;
		val /= 7.24634f;
		return 0;
	}

}
