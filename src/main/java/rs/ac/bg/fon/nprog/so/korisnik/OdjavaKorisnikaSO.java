/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.nprog.so.korisnik;

import rs.ac.bg.fon.nprog.db.DBBroker;
import rs.ac.bg.fon.nprog.domen.IOpstiDomenskiObjekat;
import rs.ac.bg.fon.nprog.domen.Korisnik;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import rs.ac.bg.fon.nprog.so.OpstaSistemskaOperacija;
import rs.ac.bg.fon.nprog.transfer.ServerskiOdgovor;

/**
 *
 * @author tamara
 */
public class OdjavaKorisnikaSO extends OpstaSistemskaOperacija{

    @Override
    protected void proveriPreduslove(Object object) throws Exception {
        
    }

    @Override
    protected ServerskiOdgovor izvrsiKonkretnuOperaciju(Object object) throws Exception {
         ServerskiOdgovor so = new ServerskiOdgovor();
         so.setPoruka("Sistem je uspešno izvršio odjavu korsinika.");
         //throw new Exception("Neuspešna prijava. Pokušajte ponovo.");
         so.setOdgovor(new Korisnik(-1,"neopoznato","nepoznato","nepoznato","nepoznato","nepoznato"));
         so.setUspesno(true);        
        return so;
    }
    
}
