package rs.ac.bg.fon.nprog.so.pronalazak;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rs.ac.bg.fon.nprog.db.DBBroker;
import rs.ac.bg.fon.nprog.domen.PrijavaPronalaskaPsa;
import rs.ac.bg.fon.nprog.domen.Pronalazak;
import rs.ac.bg.fon.nprog.domen.ZahtevZaTrazenjePsa;
import rs.ac.bg.fon.nprog.so.prijava_pronalaska_psa.NadjiPrijavePronalaskaPsaSO;
import rs.ac.bg.fon.nprog.transfer.ServerskiOdgovor;

class UcitajPronalazakSOTest {
	UcitajPronalazakSO operacija;
	@BeforeEach
	void setUp() throws Exception {
		operacija = new UcitajPronalazakSO();
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
		PrijavaPronalaskaPsa ppp = new PrijavaPronalaskaPsa();
		ppp.setPrijavaPronalaskaPsaId(1);
		ZahtevZaTrazenjePsa zztp = new ZahtevZaTrazenjePsa();
		zztp.setZahtevZaTrazenjePsaId(11);
		Pronalazak p = new Pronalazak();
		p.setPrijavaPronalaskaPsa(ppp);
		p.setZahtevZaTrazenjePsa(zztp);
		ServerskiOdgovor so = operacija.izvrsiKonkretnuOperaciju(p);
		assertNotNull(so);
		Pronalazak odgovor = (Pronalazak)so.getOdgovor();
		assertEquals(1, odgovor.getPrijavaPronalaskaPsa().getPrijavaPronalaskaPsaId());
		assertEquals(11, odgovor.getZahtevZaTrazenjePsa().getZahtevZaTrazenjePsaId());
		assertTrue(so.isUspesno());
		assertEquals("Sistem je učitao pronalazak.",so.getPoruka());
	}

}
