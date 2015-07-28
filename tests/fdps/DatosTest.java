package fdps;

import static org.junit.Assert.*;

import org.junit.Test;

public class DatosTest {
	
	private IDatos mock;
	
	@Test
	public void testDatosIAd(){
		mock = new DatosIA(new DatosIAd(),new DatosIAn());
		float val = mock.getNextValue(9760);
		assertTrue(val>=10 && val <= 40);
	}
	@Test
	public void testDatosIAn(){
		mock = new DatosIA(new DatosIAd(),new DatosIAn());
		float val = mock.getNextValue(5580);
		assertTrue(val>=60 && val <= 100);
	}
	@Test
	public void testDatosPPc(){
		mock = new DatosPPc();
		float val = mock.getNextValue(5580);
		assertTrue(val>=100 && val <= 450);
	}
	@Test
	public void testDatosTa(){
		mock = new DatosTA();
		float val = mock.getNextValue(5580);
		assertTrue(val>=15 && val <= 80);
	}
	@Test
	public void testRejected(){
		mock = new Reject();
		float prob0 = mock.getNextValue(1);
		float prob1 = mock.getNextValue(20);
		float prob2 = mock.getNextValue(249);
		assertTrue(prob1 > prob2);
	}
}
