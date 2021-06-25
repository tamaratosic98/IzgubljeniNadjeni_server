/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.nprog.so.zahtev_za_trazenje_psa;

import rs.ac.bg.fon.nprog.db.DBBroker;
import rs.ac.bg.fon.nprog.domen.IOpstiDomenskiObjekat;
import rs.ac.bg.fon.nprog.domen.ZahtevZaTrazenjePsa;
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
public class NadjiZahteveZaTrazenjePsaSO extends OpstaSistemskaOperacija{
    @Override
    protected void proveriPreduslove(Object object) throws Exception {
       
    }

    @Override
    protected ServerskiOdgovor izvrsiKonkretnuOperaciju(Object object) throws Exception {
        try{
            ServerskiOdgovor so=new ServerskiOdgovor();
            List<IOpstiDomenskiObjekat> lista=DBBroker.getInstance().select((ZahtevZaTrazenjePsa)object);//insert ako hocemo da se cuva
            if(lista.isEmpty()==false){
                //pakujemo parametre u serverski odgovor
                so.setOdgovor(lista);
                so.setPoruka("Sistem je našao zahteve za traženje psa po zadatoj vrednosti.");
                so.setUspesno(true);     
            }else{
                //so.setOdgovor(null); to je default
                so.setPoruka("Sistem ne može da nađe zahteve za traženje psa po zadatoj vrednosti.");
                so.setUspesno(false);     
            }
        return so;
        }catch(SQLException ex){
            Logger.getLogger(NadjiZahteveZaTrazenjePsaSO.class.getName()).log(Level.SEVERE,null,ex);
            throw new Exception("Sistem ne može da nađe zahteve za traženje psa po zadatoj vrednosti.");
        }
        
    }
}
