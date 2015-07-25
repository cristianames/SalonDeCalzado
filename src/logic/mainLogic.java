package logic;

public class mainLogic {

	private static mainLogic instance = null;
	   protected mainLogic() {
	      // Exists only to defeat instantiation.
	   }
	   public static mainLogic getInstance() {
	      if(instance == null) {
	         instance = new mainLogic();
	      }
	      return instance;
	   }
	private int iteraciones;
	   
	private final IDatos iAn = new DatosIAn();
	private final IDatos iAp = new DatosIAp();
	private final IDatos tAn = new DatosTAn();
	private final IDatos tAi = new DatosTAi();
	private final IDatos ppc = new DatosPPc();
	
	private int cantEmpleados;
	private int stockMaximo;
	
	private int pcr;
	public int getPCR(){return pcr;}
	
	private int gn;
	public int getGN(){return gn;}
	
	
	public void ejecutarSimulacion(int emp, int stm, int iter){
		cantEmpleados = emp;
		stockMaximo = stm;
		iteraciones = iter;
		//TODO CARGAR LA LOGICA DE LA SIMULACION.
		pcr = iter / emp;				//Valor de prueba.
		gn = stm * iter - emp * 10;		//Valor de prueba.
	}	   
}
