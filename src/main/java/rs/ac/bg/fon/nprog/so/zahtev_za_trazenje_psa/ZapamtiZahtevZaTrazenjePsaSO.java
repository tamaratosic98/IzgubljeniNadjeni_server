/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.nprog.so.zahtev_za_trazenje_psa;

import rs.ac.bg.fon.nprog.db.DBBroker;
import rs.ac.bg.fon.nprog.domen.EnumPol;
import rs.ac.bg.fon.nprog.domen.IOpstiDomenskiObjekat;
import rs.ac.bg.fon.nprog.domen.ZahtevZaTrazenjePsa;
import rs.ac.bg.fon.nprog.helper.ImageWriter;
import java.io.File;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import rs.ac.bg.fon.nprog.kontoler.Kontroler;
import rs.ac.bg.fon.nprog.so.OpstaSistemskaOperacija;
import rs.ac.bg.fon.nprog.transfer.ServerskiOdgovor;

/**
 *
 * @author tamara
 */
public class ZapamtiZahtevZaTrazenjePsaSO extends OpstaSistemskaOperacija{

    @Override
    protected void proveriPreduslove(Object object) throws Exception {
       ZahtevZaTrazenjePsa z=(ZahtevZaTrazenjePsa) object;
       if(z.getZahtevZaTrazenjePsaId()>0 && 
          z.getPol()!=null && (z.getPol()==EnumPol.MUŠKI || z.getPol()==EnumPol.ŽENSKI) &&
          !(z.getBoja().isEmpty()) &&
          z.getLokacija()!=null &&
          z.getLokacija().getLokacijaId()>0 &&
          z.getVremeNestanka()!=null &&
          !(z.getKontaktVlasnika().isEmpty()) &&
          z.getIme().isEmpty() &&
          z.getRasa() !=null &&
          z.getRasa().getRasaId()>0){
           
       }else{
           throw new Exception("");
       }
    }

    @Override
    protected ServerskiOdgovor izvrsiKonkretnuOperaciju(Object object) throws Exception {
            try{
            ServerskiOdgovor so=new ServerskiOdgovor();
            ZahtevZaTrazenjePsa zahtevZaTrazenjePsa=(ZahtevZaTrazenjePsa)object;
            if(zahtevZaTrazenjePsa.getZahtevZaTrazenjePsaId()==-1){
                 long maxId=DBBroker.getInstance().max((ZahtevZaTrazenjePsa)object);
                 zahtevZaTrazenjePsa.setZahtevZaTrazenjePsaId(maxId+1);
                 ImageWriter.vratiInstancu().sacuvajSlikuIzgubljenog(zahtevZaTrazenjePsa);
                 DBBroker.getInstance().insert((ZahtevZaTrazenjePsa)object);
               
            }else{
                //kako resiti problem duplikata?
                Boolean duplikat = ImageWriter.vratiInstancu().daLiPostojiZahtev(zahtevZaTrazenjePsa.getSlikaURL());
                if(!duplikat){
                    ImageWriter.vratiInstancu().sacuvajSlikuIzgubljenog(zahtevZaTrazenjePsa);
                }
                DBBroker.getInstance().update((ZahtevZaTrazenjePsa)object);
            }
            so.setPoruka("Sistem je zapamtio zahtev za traženje psa.");
            so.setUspesno(true);
            return so;
        }catch(SQLException ex){
            Logger.getLogger(ZapamtiZahtevZaTrazenjePsaSO.class.getName()).log(Level.SEVERE,null,ex);
            throw new Exception("Sistem ne može da zapamti zahtev za traženje psa.");
        }
    }
    
    
}
