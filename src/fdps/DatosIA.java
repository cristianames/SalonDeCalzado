package fdps;

public class DatosIA extends Datos{

	private IDatos deadTime;
	private IDatos normalTimer;
	
	public DatosIA(IDatos dead, IDatos normal) {
		deadTime = dead;
		normalTimer = normal;
	}
	
	@Override
	public float getNextValue(float t) {
		if (t%480 <240) return deadTime.getNextValue(t);
		else return normalTimer.getNextValue(t);
	}
	
	@Override
	protected float calculateFy() {
		return 0;
	}

}
