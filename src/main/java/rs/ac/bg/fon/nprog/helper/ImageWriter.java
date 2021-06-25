/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.nprog.helper;

import rs.ac.bg.fon.nprog.domen.PrijavaPronalaskaPsa;
import rs.ac.bg.fon.nprog.domen.ZahtevZaTrazenjePsa;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import rs.ac.bg.fon.nprog.konstante.Konstante;
import rs.ac.bg.fon.nprog.kontoler.Kontroler;

/**
 *
 * @author tamara
 */
public class ImageWriter {
    public static ImageWriter instanca;
    private PropertyReader pr;

    private ImageWriter() {
        pr = new PropertyReader();
    }
    public static ImageWriter vratiInstancu(){
        if(instanca==null){
            instanca=new ImageWriter();
        }
        return instanca;
    }  
    public void sacuvajSlikuPronadjenog(PrijavaPronalaskaPsa prijavaPronalaskaPsa) throws Exception{
        try {
            ImageIcon slika = prijavaPronalaskaPsa.getSlikaURL();
            String path = slika.getDescription();
            
            int indeksZagrade=path.lastIndexOf("/");
            String ime=path.substring(indeksZagrade+1,path.length()-4);
            Timestamp tmp=new Timestamp(new Date().getTime());
            String konacna=pr.readForKey(Konstante.PRIJAVE_PATH)+""+ime+"_"+tmp+".jpg";
            
            BufferedImage bi = new BufferedImage(
                                slika.getIconWidth(),
                                slika.getIconHeight(),
                                BufferedImage.TYPE_INT_RGB);
            Graphics g = bi.createGraphics();
                            // paint the Icon to the BufferedImage.
            slika.paintIcon(null, g, 0,0);
            g.dispose();
            ImageIO.write(bi, "jpg", new File (konacna));
            ImageIcon zaSetovanje=new ImageIcon(konacna);
            prijavaPronalaskaPsa.setSlikaURL(zaSetovanje);
            
        } catch (IOException ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception();
        }
    }
    public ImageIcon sacuvajSlikuIzgubljenog(ZahtevZaTrazenjePsa zahtevZaTrazenjePsa) throws Exception{
        try {
            ImageIcon slika = zahtevZaTrazenjePsa.getSlikaURL();
            String path = slika.getDescription();
            int indeksZagrade=path.lastIndexOf("/");
            String ime=path.substring(indeksZagrade+1,path.length()-4);
            Timestamp tmp=new Timestamp(new Date().getTime());
            String konacna=pr.readForKey(Konstante.ZAHTEVI_PATH)+""+ime+"_"+tmp+".jpg";
            
            BufferedImage bi = new BufferedImage(
                                slika.getIconWidth(),
                                slika.getIconHeight(),
                                BufferedImage.TYPE_INT_RGB);
            Graphics g = bi.createGraphics();
                            // paint the Icon to the BufferedImage.
            slika.paintIcon(null, g, 0,0);
            g.dispose();
            
            
            
            ImageIO.write(bi, "jpg", new File (konacna));
            
            ImageIcon zaSetovanje=new ImageIcon(konacna);
            zahtevZaTrazenjePsa.setSlikaURL(zaSetovanje);
            
            return slika;
        } catch (IOException ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception();
        }
    }

    public void obrisiSlikuPrijave(String description) {  
        System.out.println(description);
        File file = new File(description); 
        file.delete();
    }
    public void obrisiSlikuZahteva(String description) {  
        System.out.println(description);
        File file = new File(description); 
        file.delete();
    }


    public Boolean daLiPostojiPrijava(ImageIcon slikaURL) {
        File folder = new File(pr.readForKey(Konstante.PRIJAVE_PATH));
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
          if (listOfFiles[i].isFile()) {
              //System.out.println(listOfFiles[i].getName());
              //System.out.println(slikaURL.getDescription());
              String path = slikaURL.getDescription();
              int indeksZagrade=path.lastIndexOf("/");
              String ime=path.substring(indeksZagrade+1,path.length());
              
            //System.out.println("File " + listOfFiles[i].getName());
            if(ime.equals(listOfFiles[i].getName())){
                return true;
            }
          }
        }
        return false;
    }

    public Boolean daLiPostojiZahtev(ImageIcon slikaURL) {
        File folder = new File(pr.readForKey(Konstante.ZAHTEVI_PATH));
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
          if (listOfFiles[i].isFile()) {
              //System.out.println(listOfFiles[i].getName());
              //System.out.println(slikaURL.getDescription());
              String path = slikaURL.getDescription();
              int indeksZagrade=path.lastIndexOf("/");
              String ime=path.substring(indeksZagrade+1,path.length());
              
            //System.out.println("File " + listOfFiles[i].getName());
            if(ime.equals(listOfFiles[i].getName())){
                return true;
            }
          }
        }
        return false;
    }
    
}
