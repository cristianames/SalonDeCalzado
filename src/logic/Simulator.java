package logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import fdps.DatosIA;
import fdps.DatosIAd;
import fdps.DatosIAn;
import fdps.DatosPPc;
import fdps.DatosTA;
import fdps.IDatos;
import fdps.Reject;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import sun.util.resources.ja.CalendarData_ja;

public class Simulator {	
	 
		private final IDatos iA = new DatosIA(new DatosIAd(),new DatosIAn());
		private final IDatos tA = new DatosTA();
		private final IDatos ppc = new DatosPPc();
		private final Reject rej = new Reject();
		
		private float[] results;
		private float[][] empReults;
		
		private int tmax;	
		private int numEmp;
		private int stmax;
		
		private int hv;
		private int tec = 3;	//Tiempo en CalendarData_ja del cliente.
		private int sal = 4000;	//Salario mensual de cada empleado.
		
		private int tpllca = 0, tpllp = 0, t = 0, ns = 0, nt = 0, st = 0;
		private int sps = 0, sta = 0, ca = 0, tc = 0, spr = 0, sstv = 0, spv = 0;
		private int stv = 0, cr = 0, pv = 0;
		private boolean rejected = false;
		
//		  "Evento",
//        "T",
//        "TC",
//        "St vendido",
//        "St rechazado",
//        "Precio venta",
//        "Arrepentido?"
		private ArrayList<Object[]> registry = new ArrayList<Object[]>();
		
//		"tps",
//		"ito",
//		"sto",		
		private int[][] employees;
		
		public Simulator(int time, int cEmp, int stm) {
			tmax = time;
			numEmp = cEmp;
			stmax = stm;
			hv = time * 2;
			employees = new int[numEmp][3];
			for(int i=0;i<numEmp;i++) employees[i][0] = hv;
		}
		
		public void simulate() {
			
			int tpsi = 0;
			
			Object[] init = {"Init",t,0,0,0,rejected};	//Estado Inicial.
			registry.add(init);
			
			while(t < tmax || ns > 0){
				tpsi = getMinTps();
				if(tpllca <= employees[tpsi][0] && tpllca <= tpllp){
					tpllcaProcess();
					Object[] temp = {"LlegadaCli",t,0,0,0,rejected};
					registry.add(temp);
					rejected = false;
				}else if(tpllca > employees[tpsi][0] && tpllp > employees[tpsi][0]){
					tpllpProcess();
					Object[] temp = {"LlegadaPv",t,0,0,0,rejected};
					registry.add(temp);
					rejected = false;
				}else{
					tpsProcess(tpsi);
					Object[] temp = {"Salida",t,stv,cr,pv,rejected};
					registry.add(temp);
					rejected = false;
				}
				if(t >= tmax && ns > 0) tpllca = hv;
			}
			resultCalculation();			
		}
		
		private void tpsProcess(int tpsi) {
			sps += (employees[tpsi][0] - t) * ns;
			t = employees[tpsi][0];
			calculateOperation();
			spr += cr;
			if(stv != 0){
				sstv += stv;
				st -= stv;
				pv = generatePV(stv);
				spv += pv;
				if(tc < t) tc = t + tec;
				else tc = tc + tec;
			}
			ns--;
			if(ns >= numEmp){
				int ta = tA.getNextValue(t);
				employees[tpsi][0] = t + ta;
				sta += ta;
			}else{
				employees[tpsi][1] = t;
				employees[tpsi][0] = hv;
			}
		}
		
		private void tpllpProcess() {
			t = tpllp;
			tpllp += 5 * 480;	//Cada 5 dias, con jornadas diarias de 8 horas o 480 minutos.
			st = stmax;			
		}

		private void tpllcaProcess() {
			sps += (tpllca - t) * ns;
			t = tpllca;
			tpllca = t + iA.getNextValue(t);
			if (calculateCL() > 6) ca++;	//A fines practicos se invirtio el orden del if.
			else{
				ns++;
				nt++;
				if(ns <= numEmp){
					int index = findFreeEmp();
					employees[index][2] += t - employees[index][1];
					int ta = tA.getNextValue(t);
					employees[index][0] = t + ta;
					sta += ta;
				}
			}			
		}

		private int findFreeEmp() {
			int i = 0;
			while(employees[i][0] != hv)i++;	//TODO revisar si es suficiente o hay que agregar un if.
			return i;
		}

		private int calculateCL() {
			int cl, ctc;
			if(tc>t)cl=ns;
			else {
				ctc = ( (tc - t) / tec ) + 1;
				cl = ns + ctc;
			}
			return cl;
		}

		private int getMinTps(){
			int index = 0;
			int val = employees[index][0];
			for(int i=1;i<numEmp;i++){
				if (employees[i][0] < val){
					index = i;
					val = employees[i][0];
				}
			}			
			return index;
		}

		private void calculateOperation() {
			cr = 0;
			Random r = new Random();
			switch (r.nextInt(10000)) {
			case 0 - 4020: 
				stv = 1;
				break;
			case 4020 - 7660: 
				stv = 2;
				break;
			case 7660 - 8967: 
				stv = 3;
				break;
			case 8967 - 10000: 
				stv = 4;
				break;
			default:
				break;
			}
			int vst = st; 	//Virtual stock;
			for (int i = stv; i > 0; i--){
				if( r.nextFloat() <= rej.getNextValue(vst)){
					cr++;
					stv--;
				}else vst--;				
			}
		}
		
		private int generatePV(int cant) {
			int result = 0;
			for(int i = 0; i < cant; i++) result += ppc.getNextValue(t);
			return result;
		}

		private void resultCalculation() {
			results = new float[6];
			results[0] = sps / nt;											//Permanencia en el sistema.
			results[1] = (sps - sta) / nt;									//Tiempo de espera. 
			if(spr!=0 || sstv!=0)results[2] = (spr / (spr + sstv)) * 100;	//% Pares perdidos. (No vendidos por falta de stock)
			else results[2] = 0;
			results[3] = (ca / (ca + nt)) * 100;							//% Clientes arrepentidos.
			int meses = t / 22 * 480;
			results[4] = ((spv / 3) -  sal * meses) / meses;				//Ganancia neta mensual.
			results[5] = 0;
			
			empReults = new float[numEmp][3];
			for(int i=0; i<numEmp; i++){
				empReults[i][1] = i;
				empReults[i][1] = employees[0][2] / t * 100;
				empReults[i][1] = employees[0][2];
				
				results[5] += employees[0][2];
			}
			results[5] /= numEmp * t;
			results[5] *= 100;												//% Tiempo ocioso.
		}
		
		public Object[][] obtainEmployeeDetails(){
			return (Object[][]) registry.toArray();
		}
		
		public float[] getResultVars(){
			return results;
		}
		
		public float[] getControlVars(){
			float[] vars = {numEmp, stmax};
			return vars;
		}
		

}
