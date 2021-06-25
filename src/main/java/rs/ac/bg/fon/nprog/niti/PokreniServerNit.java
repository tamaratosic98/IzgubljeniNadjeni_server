package rs.ac.bg.fon.nprog.niti;

import rs.ac.bg.fon.nprog.forma.GlavnaServerskaForma;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import rs.ac.bg.fon.nprog.kontoler.Kontroler;

/**
 *
 * @author tamara
 */
public class PokreniServerNit extends Thread {
    GlavnaServerskaForma sf;
    private boolean running;
    private ServerSocket ss;
    public PokreniServerNit(GlavnaServerskaForma sf) {
        this.sf = sf;
    }

    public PokreniServerNit() {
    }
    
    @Override
    public void run() {
        try {
            ss = new ServerSocket(9000);
            sf.serverJePokrenut();
            running = true;
            while (!isInterrupted()) {
                Socket s = ss.accept();
                System.out.println("Prihvatio klijenta");
                ObradaZahtevaNit ozn = new ObradaZahtevaNit(s);
                ozn.start();
                Kontroler.vratiInstancu().dodajKlijenta(ozn);
                sf.dodajKlijenta(ozn);
            }
        } catch (IOException ex) {
            //sf.serverNijePokrenut();
            //Logger.getLogger(PokreniServerNit.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Kraj izvrsavanja niti servera");
        }
    }
     public void zaustaviServer() {
        sf.serverNijePokrenut();
        Kontroler.vratiInstancu().odveziSveKlijente();
        try {
            ss.close();                        
            this.interrupt();            
        } catch (IOException ex) {
            Logger.getLogger(PokreniServerNit.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


}

