package logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import com.ibm.icu.util.OverlayBundle;

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
		private final IDatos rej = new Reject();
		
		private float[] results;
		private Object[][] empReults;
		
		private int tmax;	
		private int numEmp;
		private int strep;
		
		private int hv;
		private int tec = 3;	//Tiempo en CalendarData_ja del cliente.
		private int sal = 8000;	//Salario mensual de cada empleado.
		
		private int tpllca = 0, tpllp = 0, t = 0, ns = 0, nt = 0, st = 0;
		private int sps = 0, sta = 0, ca = 0, tc = 0, spr = 0, sstv = 0, spv = 0;
		private int stv = 0, cr = 0, pv = 0;
		private boolean rejected = false;
		
//		  "Evento",
//        "T",
//        "St Actual",
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
			strep = stm;
			hv = time * 2;
			employees = new int[numEmp][3];
			for(int i=0;i<numEmp;i++) employees[i][0] = hv;
		}
		
		public void simulate() {
			
			int tpsi = 0;
			
			Object[] init = {"Init",t,st,0,0,0,rejected};	//Estado Inicial.
			registry.add(init);
			
			while(t < tmax){
				tpsi = getMinTps();
				if(tpllca<=employees[tpsi][0]){
					if(tpllca <= tpllp){
						tpllcaProcess();
						Object[] temp = {"LlegadaCli",t,st,0,0,0,rejected};
						registry.add(temp);
						rejected = false;
					}else{
						tpllpProcess();
						Object[] temp = {"LlegadaPv",t,st,0,0,0,rejected};
						registry.add(temp);
						rejected = false;
					}
				}else{
					if(tpllp<=employees[tpsi][0]){
						tpllpProcess();
						Object[] temp = {"LlegadaPv",t,st,0,0,0,rejected};
						registry.add(temp);
						rejected = false;
					}else{
						tpsProcess(tpsi);
						Object[] temp = {"Salida",t,st,stv,cr,pv,rejected};
						registry.add(temp);
						rejected = false;
					}
				}
				if(t >= tmax){
					if( ns > 0) tpllca = hv;
					else break;
				}					
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
				int ta = (int) tA.getNextValue(t);
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
			st += strep;			
		}

		private void tpllcaProcess() {
			sps += (tpllca - t) * ns;
			t = tpllca;
			tpllca = (int) (t + iA.getNextValue(t));
			if (calculateCL() > 6){
				ca++;	//A fines practicos se invirtio el orden del if.
				rejected = true;
			}
			else{
				ns++;
				nt++;
				if(ns <= numEmp){
					int index = findFreeEmp();
					employees[index][2] += t - employees[index][1];
					int ta = (int) tA.getNextValue(t);
					employees[index][0] = t + ta;
					sta += ta;
				}
			}			
		}

		private int findFreeEmp() {
			int i = 0;
			while(employees[i][0] != hv)i++;
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
			float val = r.nextFloat();
			if(val<=0.4020) stv = 1;
			else if(val<=0.7660) stv = 2;
				else if(val<=0.8967) stv = 3;
					else  stv = 4;			
			
			int vst = st; 	//Virtual stock;
			for (int i = stv; i > 0; i--) {
				if( r.nextFloat() >= rej.getNextValue(vst)){
					cr++;
					stv--;
				} else vst--;				
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
			if(spr > 0 || sstv > 0){
				float val = spr + sstv;
				results[2] = (spr / val) * 100;	//% Pares perdidos. (No vendidos por falta de stock)
			}
			else results[2] = 0;
			results[3] = (ca /(float)(ca + nt)) * 100;							//% Clientes arrepentidos.
			int meses = t / (22 * 480);
			results[4] = ((spv / 3) -  sal * meses) / meses;				//Ganancia neta mensual.
			results[5] = 0;
			
			empReults = new Object[numEmp][3];
			for(int i=0; i<numEmp; i++){
				empReults[i][0] = i+1;
				empReults[i][1] = ((float)employees[i][2] / t) * 100;
				empReults[i][2] = employees[i][2];
				
				results[5] += employees[i][2];
			}
			results[5] /= numEmp * t;
			results[5] *= 100;												//% Tiempo ocioso.
		}
		
		public Object[][] obtainSimuDetails(){
			int lim = registry.size();
			Object[][] output = new Object[lim][7];
			for (int i=0;i<lim;i++){
				Object[] temp = registry.get(i);
				for (int j=0;j<7;j++){
					output[i][j]= temp[j];
				}
			}
			
			return output;
		}
		public Object[][] obtainEmployeeDetails(){
			return empReults;
		}
		
		public float[] getResultVars(){
			return results;
		}
		
		public float[] getControlVars(){
			float[] vars = {numEmp, strep};
			return vars;
		}
		

}
