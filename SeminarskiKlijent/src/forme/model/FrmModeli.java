/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forme.model;

import domen.ModelGitare;
import domen.OpstiDomenskiObjekat;
import domen.Proizvodjac;
import helper.Helper;
import java.awt.BorderLayout;
import java.awt.Window;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import komunikacija.Komunikacija;
import konstante.Konstante;
import transfer.KlijentTransferObjekat;
import transfer.ServerTransferObjekat;

/**
 *
 * @author Jovan
 */
public class FrmModeli extends javax.swing.JPanel {

    /**
     * Creates new form FrmProizvodjaci
     */
    int brojac;

    public FrmModeli() {
        initComponents();
        brojac = 0;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTextFieldPretraga = new javax.swing.JTextField();
        jButtonTrazi = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButtonIzmeni = new javax.swing.JButton();

        jLabel1.setText("Pretraga:");

        jButtonTrazi.setText("Traži");
        jButtonTrazi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTraziActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jButtonIzmeni.setText("Izmeni");
        jButtonIzmeni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonIzmeniActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(jTextFieldPretraga, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButtonTrazi)
                            .addComponent(jButtonIzmeni))
                        .addGap(0, 385, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldPretraga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButtonTrazi)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButtonIzmeni)
                .addContainerGap(47, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonTraziActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTraziActionPerformed
        try {
            String kriterijum = jTextFieldPretraga.getText();
            KlijentTransferObjekat kto = new KlijentTransferObjekat();

            kto.setOperacija(Konstante.VRATI_MODELE_SA_PRETRAGOM);
            List<Object> parametri = new ArrayList<>();
            parametri.add(kriterijum);
            parametri.add(new ModelGitare());
            kto.setParametar(parametri);
            Komunikacija.getInstance().posaljiZahtev(kto);
            ServerTransferObjekat sto = Komunikacija.getInstance().procitajOdgovor();
            if (sto.getRezultat() == Konstante.REZULTAT_NOK) {
                throw new Exception(sto.getIzuzetak());
            }
            List<OpstiDomenskiObjekat> lm = (List<OpstiDomenskiObjekat>) sto.getOdgovor();
            if (!lm.isEmpty()) {
                srediFormu(lm);
                if (brojac == 0) {
                    JOptionPane.showMessageDialog(this, "Sistem je pronašao modele gitara.");
                }
                brojac++;
            } else {
                srediFormu(new ArrayList<>());
                JOptionPane.showMessageDialog(this, "Sistem nije mogao da pronađe modele gitara.");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Sistem nije mogao da pronađe modele gitara.");
        }
    }//GEN-LAST:event_jButtonTraziActionPerformed

    private void jButtonIzmeniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonIzmeniActionPerformed
        try {

            int red = jTable1.getSelectedRow();
            if (red == -1) {
                JOptionPane.showMessageDialog(this, "Odaberite red!");
            } else {
                ModelGitareTableModel model = (ModelGitareTableModel) jTable1.getModel();
                ModelGitare mg = (ModelGitare) model.vratiModelGitare(red);
                Helper.getInstance().put("mg", mg);
                FrmModelUnos f = new FrmModelUnos();
                Window w = SwingUtilities.getWindowAncestor(FrmModeli.this);
                JDialog dialog = new JDialog((JDialog) w, "Izmena modela", true);
                dialog.setLayout(new BorderLayout());
                dialog.add(f, BorderLayout.CENTER);
                dialog.pack();
                JOptionPane.showMessageDialog(this, "Uspešno učitani podaci o modelu gitare");
                dialog.setVisible(true);
                while (dialog.isVisible()) {

                }
                jButtonTraziActionPerformed(evt);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Sistem ne može da pronađe podatke o izabranom modelu");
        }
    }//GEN-LAST:event_jButtonIzmeniActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonIzmeni;
    private javax.swing.JButton jButtonTrazi;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextFieldPretraga;
    // End of variables declaration//GEN-END:variables
    private void srediFormu(List<OpstiDomenskiObjekat> lm) throws Exception {

        ModelGitareTableModel model = new ModelGitareTableModel(lm);
        jTable1.setModel(model);
    }

    public JTable vratiTabelu() {
        return jTable1;
    }

}