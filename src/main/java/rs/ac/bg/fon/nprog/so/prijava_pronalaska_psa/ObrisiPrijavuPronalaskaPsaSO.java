/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.nprog.so.prijava_pronalaska_psa;

import rs.ac.bg.fon.nprog.db.DBBroker;
import rs.ac.bg.fon.nprog.domen.EnumPol;
import rs.ac.bg.fon.nprog.domen.IOpstiDomenskiObjekat;
import rs.ac.bg.fon.nprog.domen.PrijavaPronalaskaPsa;
import rs.ac.bg.fon.nprog.helper.ImageWriter;
import java.io.File;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import rs.ac.bg.fon.nprog.kontoler.Kontroler;
import rs.ac.bg.fon.nprog.so.OpstaSistemskaOperacija;
import rs.ac.bg.fon.nprog.transfer.ServerskiOdgovor;

/**
 *
 * @author tamara
 */
public class ObrisiPrijavuPronalaskaPsaSO extends OpstaSistemskaOperacija{

    @Override
    protected void proveriPreduslove(Object object) throws Exception {
       PrijavaPronalaskaPsa p=(PrijavaPronalaskaPsa) object;
       if(p.getPrijavaPronalaskaPsaId()>0 &&
          p.getPol()!=null && (p.getPol()==EnumPol.MUŠKI || p.getPol()==EnumPol.ŽENSKI) &&
          !(p.getBoja().isEmpty()) &&
          p.getLokacija()!=null &&
          p.getLokacija().getLokacijaId()>0 &&
          p.getVremePronalaska()!=null &&
          !(p.getKontaktNalazaca().isEmpty()) &&
          p.getRasa()!=null && 
          p.getRasa().getRasaId()>0){
           
           
       }else{
           throw new Exception("");
       }
    }
    @Override
    protected ServerskiOdgovor izvrsiKonkretnuOperaciju(Object object) throws Exception {
         try{
            ServerskiOdgovor so=new ServerskiOdgovor();
            PrijavaPronalaskaPsa prijavaPronalaskaPsa=(PrijavaPronalaskaPsa)object;
            ImageWriter.vratiInstancu().obrisiSlikuPrijave(prijavaPronalaskaPsa.getSlikaURL().getDescription());

            DBBroker.getInstance().delete(prijavaPronalaskaPsa);
            
            
            
            
            so.setPoruka("Sistem je obrisao prijavu pronalaska psa. ");
            so.setUspesno(true);
            return so;
        }catch(SQLException ex){
            Logger.getLogger(ObrisiPrijavuPronalaskaPsaSO.class.getName()).log(Level.SEVERE,null,ex);
            throw new Exception("Sistem ne može da obriše prijavu pronalaska psa.");
        }
    }
    
}
