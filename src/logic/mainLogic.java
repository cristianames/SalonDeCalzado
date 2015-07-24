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
	
	private final IDatos iAn = new DatosIAn();
	private final IDatos iAp = new DatosIAp();
	private final IDatos tAn = new DatosTAn();
	private final IDatos tAi = new DatosTAi();
	private final IDatos ppc = new DatosPPc();
	
	private int cantEmpleados;
	private int stockMaximo;
	
	
	
	   
}
