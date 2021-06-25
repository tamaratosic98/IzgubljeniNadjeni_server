/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.nprog.so.lokacija;

import rs.ac.bg.fon.nprog.db.DBBroker;
import rs.ac.bg.fon.nprog.domen.IOpstiDomenskiObjekat;
import rs.ac.bg.fon.nprog.domen.Lokacija;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import rs.ac.bg.fon.nprog.so.OpstaSistemskaOperacija;
import rs.ac.bg.fon.nprog.so.pronalazak.NadjiPronalaskeSO;
import rs.ac.bg.fon.nprog.transfer.ServerskiOdgovor;

/**
 *
 * @author tamara
 */
public class ZapamtiLokacijuSO extends OpstaSistemskaOperacija{
     @Override
    protected void proveriPreduslove(Object object) throws Exception {
    }

    @Override
    protected ServerskiOdgovor izvrsiKonkretnuOperaciju(Object object) throws Exception {
         try{
            ServerskiOdgovor so=new ServerskiOdgovor();
            Lokacija lokacija = (Lokacija) object;
            if(lokacija.getLokacijaId()==-1){
                long maxId= DBBroker.getInstance().max(lokacija);
                lokacija.setLokacijaId(maxId+1);
                DBBroker.getInstance().insert(lokacija);
            }else{
                DBBroker.getInstance().update(lokacija);
            }
            so.setPoruka("Sistem je zapamtio lokaciju.");
            so.setUspesno(true);
            so.setOdgovor(lokacija);
            return so;
        }catch(SQLException ex){
            Logger.getLogger(NadjiPronalaskeSO.class.getName()).log(Level.SEVERE,null,ex);
            throw new Exception("");
        }
    }
}
