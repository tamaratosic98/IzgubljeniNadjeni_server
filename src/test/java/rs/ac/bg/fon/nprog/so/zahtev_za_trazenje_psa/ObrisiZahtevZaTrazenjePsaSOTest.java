package rs.ac.bg.fon.nprog.so.zahtev_za_trazenje_psa;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rs.ac.bg.fon.nprog.db.DBBroker;
import rs.ac.bg.fon.nprog.domen.ZahtevZaTrazenjePsa;
import rs.ac.bg.fon.nprog.so.prijava_pronalaska_psa.ObrisiPrijavuPronalaskaPsaSO;
import rs.ac.bg.fon.nprog.transfer.ServerskiOdgovor;

class ObrisiZahtevZaTrazenjePsaSOTest {
	ObrisiZahtevZaTrazenjePsaSO operacija;
	@BeforeEach
	void setUp() throws Exception {
		operacija = new ObrisiZahtevZaTrazenjePsaSO();
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
		ZahtevZaTrazenjePsa zztp = new ZahtevZaTrazenjePsa();
		zztp.setZahtevZaTrazenjePsaId(6);
		ServerskiOdgovor so = operacija.izvrsiKonkretnuOperaciju(zztp);
		assertNotNull(so);
		assertEquals("Sistem je obrisao zahtev za traženje pronalaska psa.",so.getPoruka());
		assertTrue(so.isUspesno());
		
	}

}
