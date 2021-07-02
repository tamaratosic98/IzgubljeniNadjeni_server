package rs.ac.bg.fon.nprog.so.zahtev_za_trazenje_psa;

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
import rs.ac.bg.fon.nprog.domen.ZahtevZaTrazenjePsa;
import rs.ac.bg.fon.nprog.so.prijava_pronalaska_psa.ZapamtiPrijavuPronalaskaPsaSO;
import rs.ac.bg.fon.nprog.transfer.ServerskiOdgovor;

class ZapamtiZahtevZaTrazenjePsaSOTest {
	ZapamtiZahtevZaTrazenjePsaSO operacija;
	@BeforeEach
	void setUp() throws Exception {
		operacija=new ZapamtiZahtevZaTrazenjePsaSO();
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
		ZahtevZaTrazenjePsa zztp= new ZahtevZaTrazenjePsa(1,EnumPol.MUŠKI,"bela", new Date(), "kontakt",slika, 10,"ime",r,l,1.0,1.0,"opis",k);
		ServerskiOdgovor so=operacija.izvrsiKonkretnuOperaciju(zztp);
		assertNotNull(so);
		assertEquals("Sistem je zapamtio zahtev za traženje psa.",so.getPoruka());
		assertTrue(so.isUspesno());
	}

}
