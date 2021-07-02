package rs.ac.bg.fon.nprog.so.korisnik;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import rs.ac.bg.fon.nprog.domen.IOpstiDomenskiObjekat;
import rs.ac.bg.fon.nprog.domen.Korisnik;
import rs.ac.bg.fon.nprog.transfer.ServerskiOdgovor;
import rs.ac.bg.fon.nprog.db.DBBroker;


class PrijavaKorisnikaSOTest {
	private PrijavaKorisnikaSO operacija;


	@BeforeEach
	void setUp() throws Exception {
		operacija = new PrijavaKorisnikaSO();
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
		Korisnik k = new Korisnik(5,"x","x","x","x","9dd4e461268c8034f5c8564e155c67a6");
		ServerskiOdgovor so  = operacija.izvrsiKonkretnuOperaciju(k);
		assertNotNull(so);
		assertEquals(k,so.getOdgovor());
		assertEquals("Sistem je uspešno izvršio prijavu korisnika.", so.getPoruka());
		assertTrue(so.isUspesno());
		
	}

}
