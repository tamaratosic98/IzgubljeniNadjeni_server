/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.nprog.so.korisnik;

import rs.ac.bg.fon.nprog.db.DBBroker;
import rs.ac.bg.fon.nprog.domen.Korisnik;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import rs.ac.bg.fon.nprog.so.OpstaSistemskaOperacija;
import rs.ac.bg.fon.nprog.transfer.ServerskiOdgovor;

/**
 *
 * @author tamara
 */
public class RegistracijaKorisnikaSO extends OpstaSistemskaOperacija {

    @Override
    protected void proveriPreduslove(Object object) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected ServerskiOdgovor izvrsiKonkretnuOperaciju(Object object) throws Exception {
         try{
            ServerskiOdgovor so=new ServerskiOdgovor();
            Korisnik korisnik=(Korisnik)object;
            long maxId=DBBroker.getInstance().max((Korisnik)object);
            long korisnikId=maxId+1;
            System.out.println(korisnikId);
            korisnik.setKorisnikId(korisnikId);
            
            DBBroker.getInstance().insert((Korisnik)object);
            so.setPoruka("Korisnik je uspešno registrovan na sistem. ");
            so.setUspesno(true);
            return so;
        }catch(SQLException ex){
            Logger.getLogger(RegistracijaKorisnikaSO.class.getName()).log(Level.SEVERE,null,ex);
            throw new Exception("Neuspešna registracija. ");
        }
    }
    
}
