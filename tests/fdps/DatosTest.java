package fdps;

import static org.junit.Assert.*;

import org.junit.Test;

public class DatosTest {
	
	private IDatos mock;
	
	@Test
	public void testDatosIAd(){
		mock = new DatosIA(new DatosIAd(),new DatosIAn());
		int val = mock.getNextValue(9760);
		assertTrue(val>=10 && val <= 40);
	}
	@Test
	public void testDatosIAn(){
		mock = new DatosIA(new DatosIAd(),new DatosIAn());
		int val = mock.getNextValue(5580);
		assertTrue(val>=60 && val <= 100);
	}
	@Test
	public void testDatosPPc(){
		mock = new DatosPPc();
		int val = mock.getNextValue(5580);
		assertTrue(val>=100 && val <= 450);
	}
	@Test
	public void testDatosTa(){
		mock = new DatosTA();
		int val = mock.getNextValue(5580);
		assertTrue(val>=15 && val <= 80);
	}

}
