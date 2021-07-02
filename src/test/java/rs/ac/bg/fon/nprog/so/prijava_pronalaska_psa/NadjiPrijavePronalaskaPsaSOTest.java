package rs.ac.bg.fon.nprog.so.prijava_pronalaska_psa;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import javax.swing.ImageIcon;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rs.ac.bg.fon.nprog.db.DBBroker;
import rs.ac.bg.fon.nprog.domen.EnumPol;
import rs.ac.bg.fon.nprog.domen.Korisnik;
import rs.ac.bg.fon.nprog.domen.Lokacija;
import rs.ac.bg.fon.nprog.domen.PrijavaPronalaskaPsa;
import rs.ac.bg.fon.nprog.domen.Rasa;
import rs.ac.bg.fon.nprog.transfer.ServerskiOdgovor;

class NadjiPrijavePronalaskaPsaSOTest {
	NadjiPrijavePronalaskaPsaSO operacija;
	@BeforeEach
	void setUp() throws Exception {
		operacija = new NadjiPrijavePronalaskaPsaSO();
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
		PrijavaPronalaskaPsa ppp = new PrijavaPronalaskaPsa();
		ppp.setLokacija(l);
		ppp.setRasa(r);
		ServerskiOdgovor so  = operacija.izvrsiKonkretnuOperaciju(ppp);
		assertNotNull(so);
		assertNotNull(so.getOdgovor());
		assertEquals("Sistem je našao prijave pronalaska psa po zadatoj vrednosti. ",so.getPoruka());
		assertTrue(so.isUspesno());
	}

}
