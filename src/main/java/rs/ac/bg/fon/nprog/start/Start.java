/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.nprog.start;

import rs.ac.bg.fon.nprog.forma.GlavnaServerskaForma;
import rs.ac.bg.fon.nprog.niti.PokreniServerNit;

/**
 *
 * @author tamara
 */
public class Start {
    public static void main(String[] args) {
        //new PokreniServerNit().start();
        new GlavnaServerskaForma().setVisible(true);
    }
}
