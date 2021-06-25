/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.nprog.so;

import rs.ac.bg.fon.nprog.db.DBBroker;
import rs.ac.bg.fon.nprog.domen.IOpstiDomenskiObjekat;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import rs.ac.bg.fon.nprog.transfer.ServerskiOdgovor;

/**
 *
 * @author tamara
 */
public abstract class OpstaSistemskaOperacija {
     
    public ServerskiOdgovor izvrsiOperaciju(Object object){
        ServerskiOdgovor so = new ServerskiOdgovor();
        try {
            DBBroker.getInstance().ucitajDrajver();
            DBBroker.getInstance().otvoriKonekciju(); 
            so = izvrsiKonkretnuOperaciju(object);
            DBBroker.getInstance().commit();
        } catch (Exception ex) {
            Logger.getLogger(OpstaSistemskaOperacija.class.getName()).log(Level.SEVERE, null, ex);
            try {
                DBBroker.getInstance().rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(OpstaSistemskaOperacija.class.getName()).log(Level.SEVERE, null, ex1);
            }
            so.setUspesno(false);
            so.setPoruka(ex.getMessage());
        }finally{
            try {
                DBBroker.getInstance().zatvoriKonekciju();
            } catch (SQLException ex) {
                Logger.getLogger(OpstaSistemskaOperacija.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return so;
    }

    protected abstract void proveriPreduslove(Object object) throws Exception;

    protected abstract ServerskiOdgovor izvrsiKonkretnuOperaciju(Object object) throws Exception;

}
