package fdps;

public class DatosIAd extends Datos {

	public DatosIAd() {
		a = 10;
		b = 40;
		m = 0.145f;
	}
	
	@Override
	protected float calculateFy() {
		float i = -0.02527f;
		float j = 0.70523f;
		float k = 33.29f;
		float l = 7.9614f;
		
		float z = (x - l) / k;
		
		float val1 = (float) (j/(k*z*(z-1)*Math.sqrt(2 * Math.PI)));
		float val2 =  (float) (i + j * Math.log(z/(1-z)));
		val2 = (float) Math.pow(val2, 2) / -2f;
		val1 = (float) (val1 * Math.exp(val2));		
		return val1;
	}

}
