/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.nprog.kontoler;

import rs.ac.bg.fon.nprog.domen.Korisnik;
import rs.ac.bg.fon.nprog.domen.Lokacija;
import rs.ac.bg.fon.nprog.domen.PrijavaPronalaskaPsa;
import rs.ac.bg.fon.nprog.domen.Pronalazak;
import rs.ac.bg.fon.nprog.domen.Rasa;
import rs.ac.bg.fon.nprog.domen.ZahtevZaTrazenjePsa;
import rs.ac.bg.fon.nprog.forma.GlavnaServerskaForma;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import rs.ac.bg.fon.nprog.niti.ObradaZahtevaNit;
import rs.ac.bg.fon.nprog.so.OpstaSistemskaOperacija;
import rs.ac.bg.fon.nprog.so.korisnik.OdjavaKorisnikaSO;
import rs.ac.bg.fon.nprog.so.korisnik.PrijavaKorisnikaSO;
import rs.ac.bg.fon.nprog.so.korisnik.RegistracijaKorisnikaSO;
import rs.ac.bg.fon.nprog.so.lokacija.UcitajListuLokacijaSO;
import rs.ac.bg.fon.nprog.so.lokacija.ZapamtiLokacijuSO;
import rs.ac.bg.fon.nprog.so.prijava_pronalaska_psa.NadjiPrijavePronalaskaPsaSO;
import rs.ac.bg.fon.nprog.so.prijava_pronalaska_psa.ObrisiPrijavuPronalaskaPsaSO;
import rs.ac.bg.fon.nprog.so.prijava_pronalaska_psa.UcitajListuPrijavaPronalaskaPsaSO;
import rs.ac.bg.fon.nprog.so.prijava_pronalaska_psa.UcitajPrijavuPronalaskaPsaSO;
import rs.ac.bg.fon.nprog.so.prijava_pronalaska_psa.ZapamtiPrijavuPronalaskaPsaSO;
import rs.ac.bg.fon.nprog.so.pronalazak.NadjiPronalaskeSO;
import rs.ac.bg.fon.nprog.so.pronalazak.UcitajPronalazakSO;
import rs.ac.bg.fon.nprog.so.pronalazak.ZapamtiPronalazakSO;
import rs.ac.bg.fon.nprog.so.rasa.UcitajListuRasaSO;
import rs.ac.bg.fon.nprog.so.zahtev_za_trazenje_psa.NadjiZahteveZaTrazenjePsaSO;
import rs.ac.bg.fon.nprog.so.zahtev_za_trazenje_psa.ObrisiZahtevZaTrazenjePsaSO;
import rs.ac.bg.fon.nprog.so.zahtev_za_trazenje_psa.UcitajListuZahtevaZaTrazenjePsaSO;
import rs.ac.bg.fon.nprog.so.zahtev_za_trazenje_psa.UcitajZahtevZaTrazenjePsaSO;
import rs.ac.bg.fon.nprog.so.zahtev_za_trazenje_psa.ZapamtiZahtevZaTrazenjePsaSO;
import rs.ac.bg.fon.nprog.transfer.ServerskiOdgovor;

/**
 *
 * @author tamara
 */
public class Kontroler {
    private static Kontroler instanca;
    private final List<ObradaZahtevaNit> klijenti;
    private GlavnaServerskaForma sf;


    private Kontroler(){  
        klijenti = new ArrayList<>();
    }
    public static Kontroler vratiInstancu(){
        if(instanca==null){
            instanca=new Kontroler();
        }
        return instanca;
    }  
    public ServerskiOdgovor ucitajZahtevZaTrazenjePsa(ZahtevZaTrazenjePsa z) {
        OpstaSistemskaOperacija oso=new UcitajZahtevZaTrazenjePsaSO();
        return oso.izvrsiOperaciju(z);//da ne moze da se poziva izvrsi konkretnu, jer to odma krece sa dbbbroker
        //nije ucitan drajver itd zato mora ovo da se pozove
    }

    public ServerskiOdgovor ucitajPronalazak(Pronalazak p) {
        OpstaSistemskaOperacija oso=new UcitajPronalazakSO();
        return oso.izvrsiOperaciju(p);    
    }
    public ServerskiOdgovor ucitajPrijavuPronalaskaPsa(PrijavaPronalaskaPsa pp) {
        OpstaSistemskaOperacija oso=new UcitajPrijavuPronalaskaPsaSO();
        return oso.izvrsiOperaciju(pp);
    }

    public ServerskiOdgovor zapamtiPronalazak(List<Pronalazak> p) {
        OpstaSistemskaOperacija oso=new ZapamtiPronalazakSO();
        return oso.izvrsiOperaciju(p);
    }

    public ServerskiOdgovor zapamtiZahtevZaTrazenjePsa(ZahtevZaTrazenjePsa z) {
        OpstaSistemskaOperacija oso=new ZapamtiZahtevZaTrazenjePsaSO();
        return oso.izvrsiOperaciju(z);
    }

    public ServerskiOdgovor zapamtiPrijavuPronalaskaPsa(PrijavaPronalaskaPsa pp) {
        OpstaSistemskaOperacija oso=new ZapamtiPrijavuPronalaskaPsaSO();
        return oso.izvrsiOperaciju(pp);
    }

    public ServerskiOdgovor obrisiPrijavuPronalaskaPsa(PrijavaPronalaskaPsa pp) {
        OpstaSistemskaOperacija oso=new ObrisiPrijavuPronalaskaPsaSO();
        return oso.izvrsiOperaciju(pp);
    }

    public ServerskiOdgovor obrisiZahtevZaTrazenjePsa(ZahtevZaTrazenjePsa z) {
        OpstaSistemskaOperacija oso=new ObrisiZahtevZaTrazenjePsaSO();
        return oso.izvrsiOperaciju(z);
    }
    public ServerskiOdgovor ucitajListuPrijavaPronalaskaPsa(PrijavaPronalaskaPsa pp) {
        OpstaSistemskaOperacija oso=new UcitajListuPrijavaPronalaskaPsaSO();
        return oso.izvrsiOperaciju(pp);
    }

    public ServerskiOdgovor nadjiPrijavePronalaskaPsa(PrijavaPronalaskaPsa pp) {
        OpstaSistemskaOperacija oso=new NadjiPrijavePronalaskaPsaSO();
        return oso.izvrsiOperaciju(pp);
    }

    public ServerskiOdgovor ucitajListuZahtevaZaTrazenjePsa(ZahtevZaTrazenjePsa z) {
        OpstaSistemskaOperacija oso=new UcitajListuZahtevaZaTrazenjePsaSO();
        return oso.izvrsiOperaciju(z);
    }
    public ServerskiOdgovor nadjiZahteveZaTrazenjePsa(ZahtevZaTrazenjePsa z) {
        OpstaSistemskaOperacija oso=new NadjiZahteveZaTrazenjePsaSO();
        return oso.izvrsiOperaciju(z);
    }

    public ServerskiOdgovor nadjiPronalaske(Pronalazak p) {
        OpstaSistemskaOperacija oso=new NadjiPronalaskeSO();
        return oso.izvrsiOperaciju(p); 
    }

    public ServerskiOdgovor ucitajListuLokacija(Lokacija l) {
        OpstaSistemskaOperacija oso=new UcitajListuLokacijaSO();
        return oso.izvrsiOperaciju(l);
    }

    public ServerskiOdgovor ucitajListuRasa(Rasa r) {
        OpstaSistemskaOperacija oso=new UcitajListuRasaSO();
        return oso.izvrsiOperaciju(r);
    }
    
    public void dodajKlijenta(ObradaZahtevaNit ozn) {
        klijenti.add(ozn);
    }

    public List<ObradaZahtevaNit> vratiListuKlijenata() {
        return klijenti;
    }

    public void odveziSveKlijente() {
        for (ObradaZahtevaNit ozn : klijenti) {
            sf.izbaciKlijenta(ozn);
            ozn.zaustavi();
        }
    }

    public void izbaciKlijenta(ObradaZahtevaNit ozn) {
        klijenti.remove(ozn);
        sf.izbaciKlijenta(ozn);

    }

    public void setServerskaForma(GlavnaServerskaForma sf) {
        this.sf = sf;
    }

    public void osveziPrikaz() {
        sf.osveziPrikaz();
    }

    public ServerskiOdgovor ulogujKorisnika(Korisnik korisnik) {
        OpstaSistemskaOperacija oso=new PrijavaKorisnikaSO();
        return oso.izvrsiOperaciju(korisnik);
    }
    
    public ImageIcon pathUImage(String path){
         ImageIcon imageIcon=new ImageIcon(path);
         return imageIcon;
    }

    public ServerskiOdgovor odjaviKorisnika(Korisnik korisnik) {
        OpstaSistemskaOperacija oso=new OdjavaKorisnikaSO();
        return oso.izvrsiOperaciju(korisnik);
    }

    public ServerskiOdgovor registracijaKorisnika(Korisnik korisnikReg) {
         OpstaSistemskaOperacija oso=new RegistracijaKorisnikaSO();
        return oso.izvrsiOperaciju(korisnikReg);
    }
    public ServerskiOdgovor zapamtiLokaciju(Lokacija lokacija) {
        OpstaSistemskaOperacija oso=new ZapamtiLokacijuSO();
        return oso.izvrsiOperaciju(lokacija);
    }
    
}
