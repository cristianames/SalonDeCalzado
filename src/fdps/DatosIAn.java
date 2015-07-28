package fdps;

public class DatosIAn extends Datos {

	public DatosIAn() {
		a = 60;
		b = 100;
		m = 0.185f;
	}
	
	@Override
	protected float calculateFy() {		
		float i = 0.15228f;
		float j = 0.64269f;
		float k = 43.826f;
		float l = 58.517f;
		
		float z = (x - l) / k;
		
		float val1 = (float) (j/(k*z*(z-1)*Math.sqrt(2 * Math.PI)));
		float val2 =  (float) (i + j * Math.log(z/(1-z)));
		val2 = (float) Math.pow(val2, 2) / -2f;
		val1 = (float) (val1 * Math.exp(val2));		
		return val1;
	}

}
