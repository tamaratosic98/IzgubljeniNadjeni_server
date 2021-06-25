/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.nprog.model;

import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import rs.ac.bg.fon.nprog.niti.ObradaZahtevaNit;

/**
 *
 * @author tamara
 */
public class ModelTabeleKlijent extends AbstractTableModel{
    private List<ObradaZahtevaNit> lista;
    private final String[] kolone;
    private final SimpleDateFormat sdf;
    public ModelTabeleKlijent(List<ObradaZahtevaNit> lista) {
        this.lista = lista;
        kolone = new String[]{"Ime","Prezime","IP adresa", "port", "vreme konektovanja", "broj poziva SO"};
        sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm");
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ObradaZahtevaNit ozn = lista.get(rowIndex);
        switch(columnIndex){
            case 0: //return ozn.getKorisnik()==null ? "NEPOZNATO":ozn.getKorisnik().getIme();
                return ozn.getKorisnik().getIme();
                    
            case 1://return ozn.getKorisnik()==null ? "NEPOZNATO":ozn.getKorisnik().getPrezime();
                return ozn.getKorisnik().getPrezime();
            case 2: return ozn.getSocket().getInetAddress();
            case 3: return ozn.getSocket().getPort();
            case 4: return sdf.format(ozn.getDatumKonektovanja());
            case 5: return ozn.getBrojPozivaSO();
            default: return "N/A";
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }
    public void dodaj(ObradaZahtevaNit ozn){
        lista.add(ozn);
        fireTableDataChanged();
    }
    public void izbaci(ObradaZahtevaNit ozn){
        lista.remove(ozn);
        fireTableDataChanged();
    }

    public void osvezi() {
        fireTableDataChanged();
    }
    
    
}
