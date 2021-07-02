package rs.ac.bg.fon.nprog.so.prijava_pronalaska_psa;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rs.ac.bg.fon.nprog.db.DBBroker;
import rs.ac.bg.fon.nprog.domen.PrijavaPronalaskaPsa;
import rs.ac.bg.fon.nprog.transfer.ServerskiOdgovor;

class UcitajPrijavuPronalaskaPsaSOTest {
	UcitajPrijavuPronalaskaPsaSO operacija;
	@BeforeEach
	void setUp() throws Exception {
		operacija = new UcitajPrijavuPronalaskaPsaSO();
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
		ServerskiOdgovor so = operacija.izvrsiKonkretnuOperaciju(ppp);
		assertNotNull(so);
		PrijavaPronalaskaPsa odgovor = (PrijavaPronalaskaPsa)so.getOdgovor();
		assertEquals(1, odgovor.getPrijavaPronalaskaPsaId());
		assertTrue(so.isUspesno());
		assertEquals("Sistem je učitao prijavu pronalaska psa. ",so.getPoruka());
	}

}
