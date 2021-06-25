/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.nprog.helper;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tamara
 */
public class PropertyWriter {
    Properties prop;
    FileWriter fw;
    public PropertyWriter() {
        writeProperties();
    }
    private void writeProperties() {
        try {
            fw=new FileWriter("db.properties");
            prop = new Properties();
        } catch (IOException ex) {
            Logger.getLogger(PropertyReader.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    public void writeForKey(String user, String pass, String driver, String url) throws IOException{
        prop.setProperty("user", user);
        prop.setProperty("pass", pass);
        prop.setProperty("driver", driver);
        prop.setProperty("url", url);
        prop.store(fw, ""); 
    }
}
