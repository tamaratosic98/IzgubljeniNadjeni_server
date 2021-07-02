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

class ZapamtiPrijavuPronalaskaPsaSOTest {
	ZapamtiPrijavuPronalaskaPsaSO operacija;
	@BeforeEach
	void setUp() throws Exception {
		operacija=new ZapamtiPrijavuPronalaskaPsaSO();
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
		Korisnik k = new Korisnik(5, "x", "x", "x","x","9dd4e461268c8034f5c8564e155c67a6");
		ImageIcon slika=new ImageIcon("/Users/tamara/Desktop/test.png");
		PrijavaPronalaskaPsa ppp= new PrijavaPronalaskaPsa(-1,EnumPol.MUŠKI,"BlaBla",new Date(),"Blabla",slika,r, l, 1.0,1.0,"Blabla",k);
		
		ServerskiOdgovor so=operacija.izvrsiKonkretnuOperaciju(ppp);
		assertNotNull(so);
		assertEquals("Sistem je zapamtio prijavu pronalaska psa. ",so.getPoruka());
		assertTrue(so.isUspesno());
	}

}
