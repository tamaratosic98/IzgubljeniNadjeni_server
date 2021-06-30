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
 * Kontroler koji instancira i poziva sve sistemske operacije.
 *
 * @author tamara
 * @version 1.0
 */
public class Kontroler {
	/**
	 * Instanca kontrolera kao Kontroler potrebna za Singlton patern.
	 */
    private static Kontroler instanca;
    /**
     * Lista klijenata kao List
     */
    private final List<ObradaZahtevaNit> klijenti;
    /**
     * Glavna serverska forma kao GlavnaServerskaForma
     */
    private GlavnaServerskaForma sf;

    /**
     * Konstruktor koji incijalizuje objekat i nista vise.
     */
    private Kontroler(){  
        klijenti = new ArrayList<>();
    }
    /**
     * Vraca jedinstvenu instancu klase Kontroler.
     * 
     * @return Instanca kao Kontroler.
     */
    public static Kontroler vratiInstancu(){
        if(instanca==null){
            instanca=new Kontroler();
        }
        return instanca;
    }  
    /**
     * Ucitava zahtev za trazenje psa, koristi se kada se zahtevaju detalji.
     * 
     * @param z Zahtev za trazenje psa kao ZahtevZaTrazenjePsa
     * @return Odgovor od servera kao ServerskiOdgovor
     */
    public ServerskiOdgovor ucitajZahtevZaTrazenjePsa(ZahtevZaTrazenjePsa z) {
        OpstaSistemskaOperacija oso=new UcitajZahtevZaTrazenjePsaSO();
        return oso.izvrsiOperaciju(z);//da ne moze da se poziva izvrsi konkretnu, jer to odma krece sa dbbbroker
        //nije ucitan drajver itd zato mora ovo da se pozove
    }
    /**
     * Ucitava pronalazak, koristi se kada se zahtevaju detalji.
     * 
     * @param p Pronalazak kao Pronalazak
     * @return Odgovor od servera kao ServerskiOdgovor
     */

    public ServerskiOdgovor ucitajPronalazak(Pronalazak p) {
        OpstaSistemskaOperacija oso=new UcitajPronalazakSO();
        return oso.izvrsiOperaciju(p);    
    }
    /**
     * Ucitava prijavu pronalaska psa, koristi se kada se zahtevaju detalji.
     * 
     * @param pp Prijava pronalaska psa kao PrijavaPronalaskaPsa
     * @return Odgovor od servera kao ServerskiOdgovor
     */ 
    public ServerskiOdgovor ucitajPrijavuPronalaskaPsa(PrijavaPronalaskaPsa pp) {
        OpstaSistemskaOperacija oso=new UcitajPrijavuPronalaskaPsaSO();
        return oso.izvrsiOperaciju(pp);
    }
    /**
     * Cuva nov pronalazak.
     * 
     * @param p Lista pronalazaka kao List
     * @return Odgovor od servera kao ServerskiOdgovor
     */

    public ServerskiOdgovor zapamtiPronalazak(List<Pronalazak> p) {
        OpstaSistemskaOperacija oso=new ZapamtiPronalazakSO();
        return oso.izvrsiOperaciju(p);
    }
    /**
     * Cuva nov zahtev za trazenje psa.
     * 
     * @param z Zahtev za trazenje psa kao ZahtevZaTrazenjePsa
     * @return Odgovor od servera kao ServerskiOdgovor
     */ 
    public ServerskiOdgovor zapamtiZahtevZaTrazenjePsa(ZahtevZaTrazenjePsa z) {
        OpstaSistemskaOperacija oso=new ZapamtiZahtevZaTrazenjePsaSO();
        return oso.izvrsiOperaciju(z);
    }
    /**
     * Cuva novu prijavu pronalaska psa.
     * 
     * @param pp Prijava pronalaska psa kao PrijavaPronalaskaPsa
     * @return Odgovor od servera kao ServerskiOdgovor
     */
    public ServerskiOdgovor zapamtiPrijavuPronalaskaPsa(PrijavaPronalaskaPsa pp) {
        OpstaSistemskaOperacija oso=new ZapamtiPrijavuPronalaskaPsaSO();
        return oso.izvrsiOperaciju(pp);
    }
    /**
     * Brise prijavu pronalaska psa.
     * 
     * @param pp Prijava pronalaska psa kao PrijavaPronalaskaPsa
     * @return Odgovor od servera kao ServerskiOdgovor
     */

    public ServerskiOdgovor obrisiPrijavuPronalaskaPsa(PrijavaPronalaskaPsa pp) {
        OpstaSistemskaOperacija oso=new ObrisiPrijavuPronalaskaPsaSO();
        return oso.izvrsiOperaciju(pp);
    }
    /**
     * Brise zahtev za trazenje psa.
     * 
     * @param z Zahtev za trazenje psa kao ZahtevZaTrazenjePsa
     * @return Odgovor od servera kao ServerskiOdgovor
     */

    public ServerskiOdgovor obrisiZahtevZaTrazenjePsa(ZahtevZaTrazenjePsa z) {
        OpstaSistemskaOperacija oso=new ObrisiZahtevZaTrazenjePsaSO();
        return oso.izvrsiOperaciju(z);
    }
    /**
     * Ucitava listu svih prijava pronalaska psa koji postoje u bazi. Koristi se prilikom prikaza zahteva u tabeli.
     * 
     * @param pp Prijava pronalaska psa kao PrijavaPronalaskaPsa
     * @return Odgovor od servera kao ServerskiOdgovor
     */
    public ServerskiOdgovor ucitajListuPrijavaPronalaskaPsa(PrijavaPronalaskaPsa pp) {
        OpstaSistemskaOperacija oso=new UcitajListuPrijavaPronalaskaPsaSO();
        return oso.izvrsiOperaciju(pp);
    }
    /**
     * Trazi prijave po odredjenom filteru pretrage.
     * 
     * @param pp Prijava pronalaska psa kao PrijavaPronalaskaPsa
     * @return Odgovor od servera kao ServerskiOdgovor
     */
    public ServerskiOdgovor nadjiPrijavePronalaskaPsa(PrijavaPronalaskaPsa pp) {
        OpstaSistemskaOperacija oso=new NadjiPrijavePronalaskaPsaSO();
        return oso.izvrsiOperaciju(pp);
    }
    /**
     * Ucitava listu svih zahteva za trazenje psa koji postoje u bazi. Koristi se prilikom prikaza zahteva u tabeli.
     * 
     * @param z Zahtev za trazenje psa kao ZahtevZaTrazenjePsa
     * @return Odgovor od servera kao ServerskiOdgovor
     */

    public ServerskiOdgovor ucitajListuZahtevaZaTrazenjePsa(ZahtevZaTrazenjePsa z) {
        OpstaSistemskaOperacija oso=new UcitajListuZahtevaZaTrazenjePsaSO();
        return oso.izvrsiOperaciju(z);
    }
    /**
     * Trazi zahtev na osnovu filtera pretrage.
     * 
     * @param z Zahtev za trazenje psa kao ZahtevZaTrazenjePsa
     * @return Odgovor od servera kao ServerskiOdgovor
     */
    public ServerskiOdgovor nadjiZahteveZaTrazenjePsa(ZahtevZaTrazenjePsa z) {
        OpstaSistemskaOperacija oso=new NadjiZahteveZaTrazenjePsaSO();
        return oso.izvrsiOperaciju(z);
    }
    /**
     * Trazi pronalaske po odredjenim filterima pretrage.
     * 
     * @param p Pronalazak kao Pronalazak
     * @return Odgovor od servera kao ServerskiOdgovor
     */ 
    public ServerskiOdgovor nadjiPronalaske(Pronalazak p) {
        OpstaSistemskaOperacija oso=new NadjiPronalaskeSO();
        return oso.izvrsiOperaciju(p); 
    }
    /**
     * Ucitava listu lokacija koja se kasnije koristi za popunjavanje combo box-a.
     * 
     * @param l Lokacija kao Lokacija
     * @return Odgovor od servera kao ServerskiOdgovor
     */

    public ServerskiOdgovor ucitajListuLokacija(Lokacija l) {
        OpstaSistemskaOperacija oso=new UcitajListuLokacijaSO();
        return oso.izvrsiOperaciju(l);
    }
    /**
     * Ucitava listu rasa koja se kasnije koristi za popunjavanje combo box-a.
     * 
     * @param r Rasa kao Rasa
     * @return Odgovor od servera kao ServerskiOdgovor
     */

    public ServerskiOdgovor ucitajListuRasa(Rasa r) {
        OpstaSistemskaOperacija oso=new UcitajListuRasaSO();
        return oso.izvrsiOperaciju(r);
    }
    /**
     * Dodaje klijenta u listu aktivnih.
     * 
     * @param ozn Nit za obradu zahteva kao ObradaZahtevaNit
     */
    public void dodajKlijenta(ObradaZahtevaNit ozn) {
        klijenti.add(ozn);
    }
    /**
     * Vraca listu svih aktivnih klijenata.
     * 
     * @return Lista obrada zahteva niti kao List
     */

    public List<ObradaZahtevaNit> vratiListuKlijenata() {
        return klijenti;
    }
    /**
     * Izbacuje sve povezane klijente sa servera.
     */
    public void odveziSveKlijente() {
        for (ObradaZahtevaNit ozn : klijenti) {
            sf.izbaciKlijenta(ozn);
            ozn.zaustavi();
        }
    }
    /**
     * Izbacuje klijenta sa servera.
     * 
     * @param ozn Nit za obradu zahteva kao ObradaZahtevaNit
     */

    public void izbaciKlijenta(ObradaZahtevaNit ozn) {
        klijenti.remove(ozn);
        sf.izbaciKlijenta(ozn);

    }
    /**
     * Postavlja serversku formu na novu vrednost.
     * 
     * @param sf Serverska forma kao GlavnaServerskaForma
     */

    public void setServerskaForma(GlavnaServerskaForma sf) {
        this.sf = sf;
    }
    /**
     * Poziva metodu za refresh tabela sa forme.
     */

    public void osveziPrikaz() {
        sf.osveziPrikaz();
    }
    /**
     * Prijavljuje korisnika na sistem.
     * 
     * @param korisnik Korisnik kao Korisnik
     * @return Odgovor od servera kao ServerskiOdgovor
     */
    

    public ServerskiOdgovor ulogujKorisnika(Korisnik korisnik) {
        OpstaSistemskaOperacija oso=new PrijavaKorisnikaSO();
        return oso.izvrsiOperaciju(korisnik);
    }
    /**
     * Pretvara putanju u sliku.
     * 
     * @param path Putanja slike kao Strijg
     * @return Slika kao ImageIcon
     */
    
    public ImageIcon pathUImage(String path){
         ImageIcon imageIcon=new ImageIcon(path);
         return imageIcon;
    }
    /**
     * 
     * @param korisnik  Korisnik kao Korisnik
     * @return Odgovor od servera kao ServerskiOdgovor
     */

    public ServerskiOdgovor odjaviKorisnika(Korisnik korisnik) {
        OpstaSistemskaOperacija oso=new OdjavaKorisnikaSO();
        return oso.izvrsiOperaciju(korisnik);
    }
    /**
     * 
     * @param korisnikReg  Registrovani korisnik kao Korisnik
     * @return Odgovor od servera kao ServerskiOdgovor
     */

    public ServerskiOdgovor registracijaKorisnika(Korisnik korisnikReg) {
         OpstaSistemskaOperacija oso=new RegistracijaKorisnikaSO();
        return oso.izvrsiOperaciju(korisnikReg);
    }
    /**
     * 
     * @param lokacija Lokacija kao Lokacija
     * @return Odgovor od servera kao ServerskiOdgovor
     */
    public ServerskiOdgovor zapamtiLokaciju(Lokacija lokacija) {
        OpstaSistemskaOperacija oso=new ZapamtiLokacijuSO();
        return oso.izvrsiOperaciju(lokacija);
    }
    
}
