package rs.ac.bg.fon.nprog.so.zahtev_za_trazenje_psa;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rs.ac.bg.fon.nprog.db.DBBroker;
import rs.ac.bg.fon.nprog.domen.PrijavaPronalaskaPsa;
import rs.ac.bg.fon.nprog.domen.ZahtevZaTrazenjePsa;
import rs.ac.bg.fon.nprog.so.prijava_pronalaska_psa.UcitajListuPrijavaPronalaskaPsaSO;
import rs.ac.bg.fon.nprog.transfer.ServerskiOdgovor;

class UcitajListuZahtevaZaTrazenjePsaSOTest {
	UcitajListuZahtevaZaTrazenjePsaSO operacija;

	@BeforeEach
	void setUp() throws Exception {
		operacija = new UcitajListuZahtevaZaTrazenjePsaSO();
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
		ServerskiOdgovor so = operacija.izvrsiKonkretnuOperaciju(new ZahtevZaTrazenjePsa());
		assertNotNull(so);
		assertNotNull(so.getOdgovor());
		assertTrue(so.isUspesno());
		assertEquals("",so.getPoruka());
	}

}
