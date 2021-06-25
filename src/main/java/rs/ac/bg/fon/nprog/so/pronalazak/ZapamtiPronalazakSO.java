/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.nprog.so.pronalazak;

import rs.ac.bg.fon.nprog.db.DBBroker;
import rs.ac.bg.fon.nprog.domen.IOpstiDomenskiObjekat;
import rs.ac.bg.fon.nprog.domen.Pronalazak;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import rs.ac.bg.fon.nprog.so.OpstaSistemskaOperacija;
import rs.ac.bg.fon.nprog.so.zahtev_za_trazenje_psa.UcitajZahtevZaTrazenjePsaSO;
import rs.ac.bg.fon.nprog.transfer.ServerskiOdgovor;

/**
 *
 * @author tamara
 */
public class ZapamtiPronalazakSO extends OpstaSistemskaOperacija {

    @Override
    protected void proveriPreduslove(Object object) throws Exception {
        Pronalazak p=(Pronalazak) object;
        if(p.getZahtevZaTrazenjePsa() != null && 
           p.getZahtevZaTrazenjePsa().getZahtevZaTrazenjePsaId()>0 &&
           p.getPrijavaPronalaskaPsa()!=null && 
           p.getPrijavaPronalaskaPsa().getPrijavaPronalaskaPsaId()>0 &&
           p.getDatumResavanjaSlucaja()!=null && 
           p.getNapomena()!=null){
           
        }else{
            throw new Exception("");
        }
        
    }

    @Override
    protected ServerskiOdgovor izvrsiKonkretnuOperaciju(Object object) throws Exception {
       try{
            ServerskiOdgovor so=new ServerskiOdgovor();
          
                List<Pronalazak> pronalasci=(List<Pronalazak>)object;
                for(Pronalazak pronalazak:pronalasci){
                    try{
                        DBBroker.getInstance().insert(pronalazak);
                    }catch(SQLException ex){
                        DBBroker.getInstance().update(pronalazak);
                    }
                }
                //bacice dupli key exception ako vec postoji
                //udji u catch pa radi update
                //duplicate key
           
            //baci ce izuzetak ako ne uspe pa nam ne treba if
            so.setPoruka("Sistem je zapamtio pronalazak.");
            so.setUspesno(true);
            //ne treba nam so.odgovor da zato 
            return so;
            //ne moramo da namestamo set uspesno na false zbog polimorfizma jer je poziva u opstoj prosledjuje se ex
            //i tu se podesava
        }catch(SQLException ex){
            Logger.getLogger(ZapamtiPronalazakSO.class.getName()).log(Level.SEVERE,null,ex);
            throw new Exception("Sistem ne može da zapamti pronalazak. ");
        }
    }
    
}
