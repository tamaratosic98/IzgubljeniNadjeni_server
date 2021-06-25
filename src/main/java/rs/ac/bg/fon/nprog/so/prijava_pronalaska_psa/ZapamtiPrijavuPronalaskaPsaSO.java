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
public class ZapamtiPrijavuPronalaskaPsaSO extends OpstaSistemskaOperacija{

    @Override
    protected void proveriPreduslove(Object object) throws Exception {
       PrijavaPronalaskaPsa p=(PrijavaPronalaskaPsa) object;
       if(!(p!=null && p.getPrijavaPronalaskaPsaId()>0 &&
          p.getPol()!=null && (p.getPol()==EnumPol.MUŠKI || p.getPol()==EnumPol.ŽENSKI) &&
          !(p.getBoja().isEmpty()) &&
          p.getLokacija()!=null &&
          p.getLokacija().getLokacijaId()>0 &&
          p.getVremePronalaska()!=null &&
          !(p.getKontaktNalazaca().isEmpty()) &&
          p.getRasa()!=null && 
          p.getRasa().getRasaId()>0)){
            throw new Exception("");
       }
    }

    @Override
    protected ServerskiOdgovor izvrsiKonkretnuOperaciju(Object object) throws Exception {
          try{
            ServerskiOdgovor so=new ServerskiOdgovor();
            PrijavaPronalaskaPsa prijavaPronalaskaPsa=(PrijavaPronalaskaPsa)object;
            if(prijavaPronalaskaPsa.getPrijavaPronalaskaPsaId()==-1){
                 long maxId=DBBroker.getInstance().max((PrijavaPronalaskaPsa)object);
                 prijavaPronalaskaPsa.setPrijavaPronalaskaPsaId(maxId+1);
                 ImageWriter.vratiInstancu().sacuvajSlikuPronadjenog(prijavaPronalaskaPsa);
                 DBBroker.getInstance().insert((PrijavaPronalaskaPsa)object);
                 //System.out.println(prijavaPronalaskaPsa.getSlikaURL().getDescription());
            }else{
                //Kontroler.vratiInstancu().sacuvajSlikuPronadjenog(prijavaPronalaskaPsa);
                //ovde treba da proverim da li je slika promenjena i treba da se cuva
                //da izbegnem duplo cuvanje slika 
                Boolean duplikat = ImageWriter.vratiInstancu().daLiPostojiPrijava(prijavaPronalaskaPsa.getSlikaURL());
                if(duplikat==false){
                    ImageWriter.vratiInstancu().sacuvajSlikuPronadjenog(prijavaPronalaskaPsa);
                }
                DBBroker.getInstance().update((PrijavaPronalaskaPsa)object);
            }
            so.setPoruka("Sistem je zapamtio prijavu pronalaska psa. ");
            so.setUspesno(true);
            return so;
        }catch(SQLException ex){
            Logger.getLogger(ZapamtiPrijavuPronalaskaPsaSO.class.getName()).log(Level.SEVERE,null,ex);
            throw new Exception("Sistem ne može da zapamti prijavu pronalaska psa. ");
        }
    }
    
}
