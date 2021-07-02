package rs.ac.bg.fon.nprog.so.zahtev_za_trazenje_psa;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rs.ac.bg.fon.nprog.db.DBBroker;
import rs.ac.bg.fon.nprog.domen.PrijavaPronalaskaPsa;
import rs.ac.bg.fon.nprog.domen.ZahtevZaTrazenjePsa;
import rs.ac.bg.fon.nprog.so.prijava_pronalaska_psa.UcitajPrijavuPronalaskaPsaSO;
import rs.ac.bg.fon.nprog.transfer.ServerskiOdgovor;

class UcitajZahtevZaTrazenjePsaSOTest {
	UcitajZahtevZaTrazenjePsaSO operacija;
	@BeforeEach
	void setUp() throws Exception {
		operacija = new UcitajZahtevZaTrazenjePsaSO();
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
		ZahtevZaTrazenjePsa odgovor = (ZahtevZaTrazenjePsa)so.getOdgovor();
		assertEquals(6, odgovor.getZahtevZaTrazenjePsaId());
		assertTrue(so.isUspesno());
		assertEquals("Sistem je učitao zahtev za traženje psa. ",so.getPoruka());
	}

}
