/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fmaillet.orthocotation;

import javax.swing.text.Document;

/**
 *
 * @author Fred
 */
public class Norms_JDialog extends javax.swing.JDialog {

    /**
     * Creates new form VB_JDialog
     */
    public Norms_JDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        
        
        String HTMLTEXT = "<html><u>Source :</u><br>"
                + "Scheiman, M., & Wick, B. (2014). Clinical Management of Binocular Vision: Heterophoric,<br> Accommodative, and Eye Movement Disorders (fourth edition). Lippincott Williams & Wilkins."
                + "<br><br><table border=\"1\" style=\"width:80%\">"
                + "<tr><th>Cover Test</th><th>Moyenne</th><th>DS</th></tr>"
                + "<tr><td>Vision de près</td><td align=\"center\">X'3</td><td align=\"center\">+/-3</td></tr>"
                + "<tr><td>Vision de loin</td><td align=\"center\">X1</td><td align=\"center\">+/-2</td></tr>"
                + "</table>"
                + "<br><br><table border=\"1\" style=\"width:80%\">"
                + "<tr><th>Fusion (Adultes)</th><th>Moyenne</th><th>DS</th></tr>"
                + "<tr><td>Divergence de loin (D)</td><td align=\"center\">D7</td><td align=\"center\">+/-3</td></tr>"
                + "<tr><td>Convergence de loin (C)</td><td align=\"center\">C11</td><td align=\"center\">+/-7</td></tr>"
                + "<tr><td>Divergence de près (D')</td><td align=\"center\">D'13</td><td align=\"center\">+/-6</td></tr>"
                + "<tr><td>Convergence de près (C')</td><td align=\"center\">C'19</td><td align=\"center\">+/-9</td></tr>"
                + "</table>"
                + "<br><br><table border=\"1\" style=\"width:80%\">"
                + "<tr><th>Fusion (7-12 ans)</th><th>Moyenne</th><th>DS</th></tr>"
                + "<tr><td>Divergence de près (D')</td><td align=\"center\">D'12</td><td align=\"center\">+/-5</td></tr>"
                + "<tr><td>Convergence de près (C')</td><td align=\"center\">C'23</td><td align=\"center\">+/-8</td></tr>"
                + "<tr><td colspan=\"3\" align=\"center\"><i><small>(pas de normes pour la vision de loin : on utilise les normes adultes)</small></i></td></tr>"
                + "</table>"
                + "<br>"
                + "<h3>Amplitude accommodative</h3><hr><blockquote>"
                + "<ul><li>Minimum attendu : 15 - (âge/4)<br>"
                + "Attention : c'est une valeur seuil, il n'y a donc pas d'écart-type associé et on ne peut pas calculer d'écart à la norme.</li>"
                + "<li>Moyenne attendue : 18 - (âge/3)<br>"
                + "C'est la moyenne attendue pour l'âge. Son écart-type est de 2. On peut donc calculer un écart à la norme.</li>"
                + "</ul></blockquote>"
                + "<br><br><i>(à suivre...)</i></html>";
        
        //Texte
        jTextPane.setContentType("text/html");
        jTextPane.setText(HTMLTEXT);
        jTextPane.setCaretPosition(0);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextPane = new javax.swing.JTextPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(600, 502));

        jButton1.setText("Fermer");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(java.awt.Color.gray);
        jLabel1.setText("Normes utilisées");

        jTextPane.setText("hello");
        jScrollPane2.setViewportView(jTextPane);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1)
                            .addComponent(jLabel1))
                        .addGap(0, 468, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 416, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        dispose () ;
    }//GEN-LAST:event_jButton1ActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextPane jTextPane;
    // End of variables declaration//GEN-END:variables
}
