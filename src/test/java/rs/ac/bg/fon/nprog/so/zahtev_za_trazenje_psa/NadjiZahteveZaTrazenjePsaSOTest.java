package rs.ac.bg.fon.nprog.so.zahtev_za_trazenje_psa;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rs.ac.bg.fon.nprog.db.DBBroker;
import rs.ac.bg.fon.nprog.domen.Lokacija;
import rs.ac.bg.fon.nprog.domen.PrijavaPronalaskaPsa;
import rs.ac.bg.fon.nprog.domen.Rasa;
import rs.ac.bg.fon.nprog.domen.ZahtevZaTrazenjePsa;
import rs.ac.bg.fon.nprog.so.prijava_pronalaska_psa.NadjiPrijavePronalaskaPsaSO;
import rs.ac.bg.fon.nprog.transfer.ServerskiOdgovor;

class NadjiZahteveZaTrazenjePsaSOTest {
	NadjiZahteveZaTrazenjePsaSO operacija;
	@BeforeEach
	void setUp() throws Exception {
		operacija = new NadjiZahteveZaTrazenjePsaSO();
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
		Rasa r = new Rasa(1,"Škotski terijer","/");
		Lokacija l = new Lokacija(1,"Beograd");
		ZahtevZaTrazenjePsa zztp = new ZahtevZaTrazenjePsa();
		zztp.setLokacija(l);
		zztp.setRasa(r);
		ServerskiOdgovor so  = operacija.izvrsiKonkretnuOperaciju(zztp);
		assertNotNull(so);
		assertNotNull(so.getOdgovor());
		assertEquals("Sistem je našao zahteve za traženje psa po zadatoj vrednosti.",so.getPoruka());
		assertTrue(so.isUspesno());
	}

}
