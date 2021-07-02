package rs.ac.bg.fon.nprog.so.lokacija;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rs.ac.bg.fon.nprog.db.DBBroker;
import rs.ac.bg.fon.nprog.domen.Lokacija;
import rs.ac.bg.fon.nprog.transfer.ServerskiOdgovor;

class ZapamtiLokacijuSOTest {
	ZapamtiLokacijuSO operacija;

	@BeforeEach
	void setUp() throws Exception {
		operacija = new ZapamtiLokacijuSO();
		DBBroker.getInstance().ucitajDrajver();
    	DBBroker.getInstance().otvoriKonekciju(); 
	}

	@AfterEach
	void tearDown() throws Exception {
		operacija = null;
		DBBroker.getInstance().zatvoriKonekciju();
	}

	@Test
	void testIzvrsiKonkretnuOperaciju() throws Exception {
		ServerskiOdgovor so  = operacija.izvrsiKonkretnuOperaciju(new Lokacija(-1,"Nova"));
		assertNotNull(so);
		assertEquals("Sistem je zapamtio lokaciju.", so.getPoruka());
		assertTrue(so.isUspesno());
	}

}
