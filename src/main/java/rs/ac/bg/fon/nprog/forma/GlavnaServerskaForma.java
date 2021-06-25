/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.nprog.forma;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import rs.ac.bg.fon.nprog.kontoler.Kontroler;
import rs.ac.bg.fon.nprog.model.ModelTabeleKlijent;
import rs.ac.bg.fon.nprog.niti.ObradaZahtevaNit;
import rs.ac.bg.fon.nprog.niti.PokreniServerNit;

/**
 *
 * @author tamara
 */
public class GlavnaServerskaForma extends javax.swing.JFrame {
    private PokreniServerNit ps;
    /**
     * Creates new form GlavnaServerskaForma
     */
    public GlavnaServerskaForma() {
        initComponents();
        srediFormu();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnServer = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        pnKlijenti = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblKlijenti = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        btnPokreni = new javax.swing.JButton();
        btnZaustavi = new javax.swing.JButton();
        txtStatus = new javax.swing.JTextField();
        lblStatus = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(153, 153, 153));

        pnServer.setBackground(new java.awt.Color(153, 153, 153));
        pnServer.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Server", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Grande", 1, 18), new java.awt.Color(255, 255, 255))); // NOI18N
        pnServer.setForeground(new java.awt.Color(102, 102, 102));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rs/ac/bg/fon/nprog/img/logo.png"))); // NOI18N

        pnKlijenti.setBackground(new java.awt.Color(153, 153, 153));
        pnKlijenti.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Aktivni klijenti", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Grande", 0, 18), new java.awt.Color(255, 255, 255))); // NOI18N

        tblKlijenti.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        tblKlijenti.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblKlijenti.setSelectionForeground(new java.awt.Color(204, 204, 204));
        jScrollPane1.setViewportView(tblKlijenti);

        javax.swing.GroupLayout pnKlijentiLayout = new javax.swing.GroupLayout(pnKlijenti);
        pnKlijenti.setLayout(pnKlijentiLayout);
        pnKlijentiLayout.setHorizontalGroup(
            pnKlijentiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnKlijentiLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnKlijentiLayout.setVerticalGroup(
            pnKlijentiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnKlijentiLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Status servera", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Grande", 0, 18), new java.awt.Color(255, 255, 255))); // NOI18N

        btnPokreni.setBackground(new java.awt.Color(204, 204, 204));
        btnPokreni.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        btnPokreni.setForeground(new java.awt.Color(102, 102, 102));
        btnPokreni.setText("Pokreni server");
        btnPokreni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPokreniActionPerformed(evt);
            }
        });

        btnZaustavi.setBackground(new java.awt.Color(204, 204, 204));
        btnZaustavi.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        btnZaustavi.setForeground(new java.awt.Color(102, 102, 102));
        btnZaustavi.setText("Zaustavi server");
        btnZaustavi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnZaustaviActionPerformed(evt);
            }
        });

        txtStatus.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        txtStatus.setForeground(new java.awt.Color(102, 102, 102));

        lblStatus.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        lblStatus.setForeground(new java.awt.Color(255, 255, 255));
        lblStatus.setText("Status:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnPokreni, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 80, Short.MAX_VALUE)
                        .addComponent(btnZaustavi, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtStatus)
                    .addComponent(lblStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addComponent(lblStatus)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPokreni, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnZaustavi, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout pnServerLayout = new javax.swing.GroupLayout(pnServer);
        pnServer.setLayout(pnServerLayout);
        pnServerLayout.setHorizontalGroup(
            pnServerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnServerLayout.createSequentialGroup()
                .addGap(153, 153, 153)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnServerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnServerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnKlijenti, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnServerLayout.setVerticalGroup(
            pnServerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnServerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pnKlijenti, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jMenuBar1.setForeground(new java.awt.Color(102, 102, 102));
        jMenuBar1.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N

        jMenu1.setBackground(new java.awt.Color(153, 153, 153));
        jMenu1.setForeground(new java.awt.Color(102, 102, 102));
        jMenu1.setText("Meni");

        jMenuItem1.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jMenuItem1.setText("Podešavanja");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnServer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnServer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnPokreniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPokreniActionPerformed
        ps = new PokreniServerNit(this);
        ps.start();
    }//GEN-LAST:event_btnPokreniActionPerformed

    private void btnZaustaviActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnZaustaviActionPerformed
        // TODO add your handling code here:
        ps.zaustaviServer();
    }//GEN-LAST:event_btnZaustaviActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        new FrmPodesavanja(this, true, EnumModForme.SHOW).setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPokreni;
    private javax.swing.JButton btnZaustavi;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JPanel pnKlijenti;
    private javax.swing.JPanel pnServer;
    private javax.swing.JTable tblKlijenti;
    private javax.swing.JTextField txtStatus;
    // End of variables declaration//GEN-END:variables



    public void serverJePokrenut() {
       txtStatus.setText("Server program je pokrenut.");
       btnPokreni.setEnabled(false);
       btnZaustavi.setEnabled(true);
    }
        

    public void serverNijePokrenut() {
       txtStatus.setText("Server program je ugašen.");
       btnPokreni.setEnabled(true);
       btnZaustavi.setEnabled(false);
    }

    public void dodajKlijenta(ObradaZahtevaNit ozn) {
        ((ModelTabeleKlijent)tblKlijenti.getModel()).dodaj(ozn);
    }

    public void izbaciKlijenta(ObradaZahtevaNit ozn) {
        ((ModelTabeleKlijent)tblKlijenti.getModel()).izbaci(ozn);
    }

    private void srediFormu() {
        txtStatus.setEnabled(false);
        serverNijePokrenut();
        tblKlijenti.setModel(new ModelTabeleKlijent(new ArrayList<ObradaZahtevaNit>()));
        Kontroler.vratiInstancu().setServerskaForma(this);
    }

    public void osveziPrikaz() {
        ((ModelTabeleKlijent)tblKlijenti.getModel()).osvezi();
    }
    
}
