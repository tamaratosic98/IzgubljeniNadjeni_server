package rs.ac.bg.fon.nprog.so.pronalazak;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rs.ac.bg.fon.nprog.db.DBBroker;
import rs.ac.bg.fon.nprog.domen.Pronalazak;
import rs.ac.bg.fon.nprog.so.prijava_pronalaska_psa.NadjiPrijavePronalaskaPsaSO;
import rs.ac.bg.fon.nprog.transfer.ServerskiOdgovor;

class NadjiPronalaskeSOTest {
	NadjiPronalaskeSO operacija;
	@BeforeEach
	void setUp() throws Exception {
		operacija = new NadjiPronalaskeSO();
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
		Pronalazak p = new Pronalazak();
		p.setDatumResavanjaSlucaja(new Date());
		ServerskiOdgovor so = operacija.izvrsiKonkretnuOperaciju(p);
		assertNotNull(so);
		assertFalse(so.isUspesno());
		assertEquals("Sistem ne može da nađe pronalaske po zadatoj vrednosti.",so.getPoruka());
	}

}
