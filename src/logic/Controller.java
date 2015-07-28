package logic;

import java.awt.EventQueue;

import view.IntroFrame;

public class Controller {

//	---------Singleton----------------------
	private static Controller instance = null;
	   protected Controller() {
	      // Exists only to defeat instantiation.
	   }
	   public static Controller getInstance() {
	      if(instance == null) {
	         instance = new Controller();
	      }
	      return instance;
	   }
//	----------------------------------------
	   
	private Simulator simulator;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IntroFrame frame = new IntroFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void runSimulation(int iter, int emp, int stm){
		
		simulator = new Simulator(iter, emp, stm);
		simulator.simulate();
//		ppp = iter / emp;				//Valor de prueba.
//		gn = stm * iter - emp * 10;		//Valor de prueba.
	}
		
	public Object[][] obtainSimuDetails(){		
//		Object[][] data = {
//			    {"LLegada",0,0,0,0,0,false},
//			    {"Salida",2,5,1,0,245,false},
//			    {"LLegada",4,0,0,0,0,false},
//			    {"LLegada",10,0,0,0,0,false},
//			    {"Salida",11,14,2,2,490,false},
//			    {"LLegada",14,0,0,0,0,false},
//			    {"LLegada",17,0,0,0,0,true},
//			    {"Salida",19,14,0,1,0,false},
//			    {"LLegada",29,0,0,0,0,false},			    
//			};		
		Object[][] data2 = simulator.obtainSimuDetails();
		return data2;
	}

	public Object[][] obtainEmployeeDetails(){
//		Object[][] data = {
//		        {1, 15.9f,347},
//		        {2, 7.15f,121},
//		        {3, 9.1f,236},
//		        };
		Object[][] data2 = simulator.obtainEmployeeDetails();
		return data2;
	}
	
	public float[] getResultVars(){
//		float[] results = {5.9f,2.2f,5,7,3,2};
		return simulator.getResultVars();
	}
	
	public float[] getControlVars(){
//		float[] controls = {25,33};
		return simulator.getControlVars();
	}
}
