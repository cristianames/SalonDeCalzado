package fdps;

public class Reject extends Datos{
	
	public Reject() {
		a = 0;
		b = 250;
		m = 0.35f;
	}
	
	@Override
	public float getNextValue(float st){
//		float val = super.getNextValue(st);
//		val = val/b;
		x = (int) st;
		return calculateFy();
	}

	@Override
	protected float calculateFy() {
		float a1=0.72155f;
		float a2=2.0039f;
		
		if(x==0)return 0;
		
		float z=(float)x/b;
		
		float val1=(float) (1-Math.pow(z, a1));
		val1= (float) Math.pow(val1, (a2-1));
		
		float val2=(float) Math.pow(z, a1-1);
		val2= val2*a1*a2;
		val1=val1*val2/b;
		
		return val1;
	}
}
