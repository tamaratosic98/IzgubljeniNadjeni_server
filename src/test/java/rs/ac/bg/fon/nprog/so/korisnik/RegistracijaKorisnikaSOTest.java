package rs.ac.bg.fon.nprog.so.korisnik;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rs.ac.bg.fon.nprog.db.DBBroker;
import rs.ac.bg.fon.nprog.domen.Korisnik;
import rs.ac.bg.fon.nprog.transfer.ServerskiOdgovor;

class RegistracijaKorisnikaSOTest {
	private RegistracijaKorisnikaSO operacija;
	@BeforeEach
	void setUp() throws Exception {
		operacija = new RegistracijaKorisnikaSO();
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
		Korisnik k = new Korisnik(-1,"nov","nov","nov","nov","9dd4e461268c8034f5c8564e155c67a6");
		ServerskiOdgovor so  = operacija.izvrsiKonkretnuOperaciju(k);
		assertNotNull(so);
		assertEquals("Korisnik je uspešno registrovan na sistem. ",so.getPoruka());
		assertTrue(so.isUspesno());
	}

}
