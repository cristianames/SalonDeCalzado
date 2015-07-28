package fdps;

public class DatosPPc extends Datos {
	
	public DatosPPc() {
		a = 100;
		b = 450;
		m = 0.17f;
	}

	@Override
	protected float calculateFy() {
		// (1/121.6)*exp(-(x-106.63)/121.6)
		float val = (106.63f - x) / 121.6f;
		val = (float) Math.exp(val);
		val /= 121.6f;
		return val;
	}

}
