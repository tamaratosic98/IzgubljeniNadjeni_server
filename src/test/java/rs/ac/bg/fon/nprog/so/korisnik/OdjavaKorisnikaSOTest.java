package rs.ac.bg.fon.nprog.so.korisnik;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rs.ac.bg.fon.nprog.db.DBBroker;
import rs.ac.bg.fon.nprog.domen.Korisnik;
import rs.ac.bg.fon.nprog.transfer.ServerskiOdgovor;

class OdjavaKorisnikaSOTest {
	private OdjavaKorisnikaSO operacija;
	@BeforeEach
	void setUp() throws Exception {
		operacija = new OdjavaKorisnikaSO();
		DBBroker.getInstance().ucitajDrajver();
    	DBBroker.getInstance().otvoriKonekciju(); 
	}

	@AfterEach
	void tearDown() throws Exception {
		operacija=null;
		DBBroker.getInstance().zatvoriKonekciju();
	}


	@Test
	void testIzvrsiKonkretnuOperaciju() throws Exception {
		Korisnik k = new Korisnik(-1,"neopoznato","nepoznato","nepoznato","nepoznato","nepoznato");
		ServerskiOdgovor so = operacija.izvrsiKonkretnuOperaciju(new Korisnik());
		assertNotNull(so);
		assertEquals(k,so.getOdgovor());
		assertEquals("Sistem je uspešno izvršio odjavu korsinika.", so.getPoruka());
		assertTrue(so.isUspesno());
	}

}
