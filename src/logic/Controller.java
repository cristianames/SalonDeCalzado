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
	}
		
	public Object[][] obtainSimuDetails(){	
		Object[][] data2 = simulator.obtainSimuDetails();
		return data2;
	}

	public Object[][] obtainEmployeeDetails(){
		Object[][] data2 = simulator.obtainEmployeeDetails();
		return data2;
	}
	
	public float[] getResultVars(){
		return simulator.getResultVars();
	}
	
	public float[] getControlVars(){
		return simulator.getControlVars();
	}
}
