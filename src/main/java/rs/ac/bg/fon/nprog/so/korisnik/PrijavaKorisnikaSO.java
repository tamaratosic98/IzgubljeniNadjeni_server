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
public class PrijavaKorisnikaSO extends OpstaSistemskaOperacija{

    @Override
    protected void proveriPreduslove(Object object) throws Exception {
        
    }

    @Override
    protected ServerskiOdgovor izvrsiKonkretnuOperaciju(Object object) throws Exception {
        ServerskiOdgovor so = new ServerskiOdgovor();
        try {
            List<IOpstiDomenskiObjekat> lista=DBBroker.getInstance().select((Korisnik)object);
            if(lista.isEmpty()){
                so.setPoruka("");
                so.setUspesno(false);  
                //throw new Exception("Neuspešna prijava. Pokušajte ponovo.");
            }else{
                so.setOdgovor(lista.get(0));
                so.setPoruka("Sistem je uspešno izvršio prijavu korisnika.");
                so.setUspesno(true);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PrijavaKorisnikaSO.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception("Sistem ne može da izvrši prijavu korisnika.");
        }        
        return so;
        /*try{
            ServerskiOdgovor so=new ServerskiOdgovor();
            List<IOpstiDomenskiObjekat> lista=DBBroker.getInstance().select((Korisnik)object);//insert ako hocemo da se cuva
            if(lista.isEmpty()==false){
                //pakujemo parametre u serverski odgovor
                so.setOdgovor(lista.get(0));
                so.setPoruka("");
                so.setUspesno(true);     
            }else{
                //so.setOdgovor(null); to je default
                so.setPoruka("");
                so.setUspesno(false);     
            }
        return so;
        }catch(SQLException ex){
            Logger.getLogger(PrijavaKorisnikaSO.class.getName()).log(Level.SEVERE,null,ex);
            throw new Exception("");
        }*/
    }
    
}
