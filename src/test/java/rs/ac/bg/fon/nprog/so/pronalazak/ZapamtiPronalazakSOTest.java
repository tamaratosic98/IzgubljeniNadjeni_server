package rs.ac.bg.fon.nprog.so.pronalazak;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rs.ac.bg.fon.nprog.db.DBBroker;
import rs.ac.bg.fon.nprog.domen.PrijavaPronalaskaPsa;
import rs.ac.bg.fon.nprog.domen.Pronalazak;
import rs.ac.bg.fon.nprog.domen.ZahtevZaTrazenjePsa;
import rs.ac.bg.fon.nprog.so.prijava_pronalaska_psa.NadjiPrijavePronalaskaPsaSO;
import rs.ac.bg.fon.nprog.transfer.ServerskiOdgovor;

class ZapamtiPronalazakSOTest {
	ZapamtiPronalazakSO operacija;
	@BeforeEach
	void setUp() throws Exception {
		operacija = new ZapamtiPronalazakSO();
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
		ppp.setPrijavaPronalaskaPsaId(4);
		ZahtevZaTrazenjePsa zztp = new ZahtevZaTrazenjePsa();
		zztp.setZahtevZaTrazenjePsaId(14);
		Pronalazak p = new Pronalazak();
		p.setPrijavaPronalaskaPsa(ppp);
		p.setZahtevZaTrazenjePsa(zztp);
		p.setDatumResavanjaSlucaja(new Date());
		p.setNapomena("napomena");
		List<Pronalazak> lista = new ArrayList<>();
		lista.add(p);
		ServerskiOdgovor so = operacija.izvrsiKonkretnuOperaciju(lista);
		assertNotNull(so);
		assertTrue(so.isUspesno());
		assertEquals("Sistem je zapamtio pronalazak.",so.getPoruka());
		
		
	}

}
