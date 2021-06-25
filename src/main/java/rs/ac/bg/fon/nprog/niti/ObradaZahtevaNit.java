/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.nprog.niti;

import rs.ac.bg.fon.nprog.domen.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import rs.ac.bg.fon.nprog.konstante.Operacije;
import rs.ac.bg.fon.nprog.kontoler.Kontroler;
import rs.ac.bg.fon.nprog.transfer.KlijentskiZahtev;
import rs.ac.bg.fon.nprog.transfer.ServerskiOdgovor;

/**
 *
 * @author tamara
 */
public class ObradaZahtevaNit extends Thread {
    private final Socket s;
    boolean kraj = false;
    private int brojPozivaSO;
    private final Date datumKonektovanja;
    private Korisnik korisnik=new Korisnik(-1,"neopoznato","nepoznato","nepoznato","nepoznato","nepoznato");

    public ObradaZahtevaNit(Socket s) {
        this.s = s;
        datumKonektovanja = new Date();
    }

    @Override 
    public void run() {
       
        while (!kraj) {            
            KlijentskiZahtev kz = primiZahtev();
            if(kz == null || kraj)
                continue;
            ServerskiOdgovor so = new ServerskiOdgovor();
            System.out.println("Operacija: "+kz.getOperacija());
            brojPozivaSO++;
            Kontroler.vratiInstancu().osveziPrikaz();
            switch(kz.getOperacija()){
                case Operacije.UCITAJ_LISTU_LOKACIJA:
                    Lokacija l=(Lokacija) kz.getParametar();
                    so=Kontroler.vratiInstancu().ucitajListuLokacija(l);
                    break;
                case Operacije.UCITAJ_LISTU_RASA:
                    Rasa r=(Rasa) kz.getParametar();
                    so=Kontroler.vratiInstancu().ucitajListuRasa(r);
                    break;
                case Operacije.UCITAJ_LISTU_PRIJAVA_PRONALASKA_PSA:
                    PrijavaPronalaskaPsa ppucitajlistu=(PrijavaPronalaskaPsa) kz.getParametar();
                    so=Kontroler.vratiInstancu().ucitajListuPrijavaPronalaskaPsa(ppucitajlistu);
                    break;
                case Operacije.UCITAJ_LISTU_ZAHTEVA_ZA_TRAZENJE_PSA:
                    ZahtevZaTrazenjePsa zucitajlistu=(ZahtevZaTrazenjePsa) kz.getParametar();
                    so=Kontroler.vratiInstancu().ucitajListuZahtevaZaTrazenjePsa(zucitajlistu);
                    break;
                case Operacije.NADJI_PRIJAVE_PRONALASKA_PSA:
                    PrijavaPronalaskaPsa ppnadji=(PrijavaPronalaskaPsa) kz.getParametar();
                    so=Kontroler.vratiInstancu().nadjiPrijavePronalaskaPsa(ppnadji);
                    break;
                case Operacije.NADJI_PRONALASKE:
                    Pronalazak pnadji=(Pronalazak) kz.getParametar();
                    so=Kontroler.vratiInstancu().nadjiPronalaske(pnadji);
                    break;
                case Operacije.NADJI_ZAHTEVE_ZA_TRAZENJE_PSA:
                    ZahtevZaTrazenjePsa znadji=(ZahtevZaTrazenjePsa) kz.getParametar();
                    so=Kontroler.vratiInstancu().nadjiZahteveZaTrazenjePsa(znadji);
                    break;
                case Operacije.OBRISI_PRIJAVU_PRONALASKA_PSA:
                    PrijavaPronalaskaPsa ppobrisi=(PrijavaPronalaskaPsa) kz.getParametar();
                    so=Kontroler.vratiInstancu().obrisiPrijavuPronalaskaPsa(ppobrisi);
                    break;
                case Operacije.OBRISI_ZAHTEV_ZA_TRAZENJE_PSA:
                    ZahtevZaTrazenjePsa zobrisi=(ZahtevZaTrazenjePsa) kz.getParametar();
                    so=Kontroler.vratiInstancu().obrisiZahtevZaTrazenjePsa(zobrisi);
                    break;
                case Operacije.UCITAJ_PRIJAVU_PRONALASKA_PSA:
                    PrijavaPronalaskaPsa ppucitaj=(PrijavaPronalaskaPsa) kz.getParametar();
                    so=Kontroler.vratiInstancu().ucitajPrijavuPronalaskaPsa(ppucitaj);
                    break;
                case Operacije.UCITAJ_PRONALAZAK:
                    Pronalazak pucitaj=(Pronalazak) kz.getParametar();
                    so=Kontroler.vratiInstancu().ucitajPronalazak(pucitaj);
                    break;
                case Operacije.UCITAJ_ZAHTEV_ZA_TRAZENJE_PSA:
                    ZahtevZaTrazenjePsa zucitaj=(ZahtevZaTrazenjePsa) kz.getParametar();
                    so=Kontroler.vratiInstancu().ucitajZahtevZaTrazenjePsa(zucitaj);
                    break;
                case Operacije.ZAPAMTI_PRIJAVU_PRONALASKA_PSA:
                    PrijavaPronalaskaPsa ppzapamti=(PrijavaPronalaskaPsa) kz.getParametar();
                    so=Kontroler.vratiInstancu().zapamtiPrijavuPronalaskaPsa(ppzapamti);
                    break;
                case Operacije.ZAPAMTI_PRONALAZAK:
                    List<Pronalazak> pzapamti=(List<Pronalazak>) kz.getParametar();
                    so=Kontroler.vratiInstancu().zapamtiPronalazak(pzapamti);
                    break;
                case Operacije.ZAPAMTI_ZAHTEV_ZA_TRAZENJE_PSA:
                    ZahtevZaTrazenjePsa zzapamti=(ZahtevZaTrazenjePsa) kz.getParametar();
                    so=Kontroler.vratiInstancu().zapamtiZahtevZaTrazenjePsa(zzapamti);
                    break;
                case Operacije.PRIJAVA_KORISNIKA:
                    Korisnik korisnikNepotpun = (Korisnik)kz.getParametar();
                    so=Kontroler.vratiInstancu().ulogujKorisnika(korisnikNepotpun);
                    if(so.getOdgovor()!=null){
                        korisnik=(Korisnik) so.getOdgovor();
                    }
                    break;
                case Operacije.ODJAVA_KORISNIKA:
                    Korisnik korisnikZaOdjavu=(Korisnik) kz.getParametar();
                    so=Kontroler.vratiInstancu().odjaviKorisnika(korisnik);
                    korisnik=(Korisnik)so.getOdgovor();
                    break;
                case Operacije.REGISTRACIJA_KORISNIKA:
                    Korisnik korisnikReg=(Korisnik) kz.getParametar();
                    so=Kontroler.vratiInstancu().registracijaKorisnika(korisnikReg);
                    break;
                case Operacije.ZAPAMTI_LOKACIJU:
                    Lokacija lokacija=(Lokacija) kz.getParametar();
                    so=Kontroler.vratiInstancu().zapamtiLokaciju(lokacija);
                    break;
            }
            
            posaljiOdgovor(so);//ovde radi write object, salje klijentu i tamo gde klijent prima, bice readObject
        }
         try{
            posaljiOdgovor(new ServerskiOdgovor(null, "Server je prekinuo konekciju ili vise nije aktivan", false));
        }catch(Exception ex){            
        }
        Kontroler.vratiInstancu().izbaciKlijenta(this);
    }

    private KlijentskiZahtev primiZahtev() {
        KlijentskiZahtev kz = new KlijentskiZahtev();
        
        try {
            ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
            kz = (KlijentskiZahtev) ois.readObject();
        } catch (Exception ex) {
            kraj = true;
            return null;
            //Logger.getLogger(ObradaZahtevaNit.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        return kz;
    }

    private void posaljiOdgovor(ServerskiOdgovor so) {
        ObjectOutputStream oos;
        try {
            oos = new ObjectOutputStream(s.getOutputStream());
            oos.writeObject(so);
        } catch (IOException ex) {
              kraj = true;
              //Logger.getLogger(ObradaZahtevaNit.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public void zaustavi() {
        kraj = true;
        
    }
    /**
     * @return the s
     */
    public Socket getSocket() {
        return s;
    }

    /**
     * @return the brojPozivaSO
     */
    public int getBrojPozivaSO() {
        return brojPozivaSO;
    }

    /**
     * @return the datumKonektovanja
     */
    public Date getDatumKonektovanja() {
        return datumKonektovanja;
    }

    /**
     * @return the korisnik
     */
    public Korisnik getKorisnik() {
        return korisnik;
    }

    /**
     * @param korisnik the korisnik to set
     */
    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }


}
