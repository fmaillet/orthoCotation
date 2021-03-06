package com.fmaillet.orthocotation;


import java.awt.Cursor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Fred
 */
public class ConnectDialog extends java.awt.Dialog {

    /**
     * Creates new form ConnectDialog
     */
    public ConnectDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        jAdeli.setText("");
        jCode.setText ("") ;
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        label1 = new java.awt.Label();
        label2 = new java.awt.Label();
        jConnexion = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        jMessages = new javax.swing.JTextArea();
        jFermer = new javax.swing.JButton();
        jAdeli = new javax.swing.JFormattedTextField();
        jCode = new javax.swing.JFormattedTextField();

        setAlwaysOnTop(true);
        setBackground(java.awt.Color.cyan);
        setLocationRelativeTo(getOwner());
        setResizable(false);
        setTitle("orthoCotation : connexion au serveur...");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Connexion au serveur :");

        label1.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        label1.setText("Identifiant (Adeli) :");

        label2.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        label2.setText("Votre code :");

        jConnexion.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jConnexion.setText("Connexion");
        jConnexion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jConnexionActionPerformed(evt);
            }
        });

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/fmaillet/orthocotation/connect.png"))); // NOI18N

        jMessages.setColumns(20);
        jMessages.setRows(5);
        jScrollPane1.setViewportView(jMessages);

        jFermer.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jFermer.setText("Fermer");
        jFermer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFermerActionPerformed(evt);
            }
        });

        jAdeli.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        jAdeli.setToolTipText("Identifiant à 9 chiffres");
        jAdeli.setFocusLostBehavior(javax.swing.JFormattedTextField.PERSIST);
        jAdeli.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jAdeliFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jAdeliFocusLost(evt);
            }
        });

        jCode.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        jCode.setToolTipText("Code secret à 5 chiffres");
        jCode.setFocusLostBehavior(javax.swing.JFormattedTextField.PERSIST);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSeparator1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jFermer, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jAdeli)
                                    .addComponent(jConnexion, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
                                    .addComponent(jCode))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jAdeli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jConnexion, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jFermer)
                .addGap(18, 18, 18))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Closes the dialog
     */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        setVisible(false);
        dispose();
    }//GEN-LAST:event_closeDialog

    private void jConnexionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jConnexionActionPerformed
        String adeli = jAdeli.getText().trim() ;
        if (adeli.length() != 9) {
            jMessages.append("-> Format Adeli incorrect (doit contenir 9 chiffres)\n");
            return ;
        }
        String code = jCode.getText().trim() ;
        if (code.length() != 5) {
            jMessages.append("-> Format code incorrect (doit contenir 5 chiffres)\n");
            return ;
        }
        //On essaye de se connecter
        jMessages.append ("----- Connexion en cours :\n");
        if (connection (adeli, code)) {
            jConnexion.setEnabled(false);
        }
            
    }//GEN-LAST:event_jConnexionActionPerformed

    private void jAdeliFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jAdeliFocusGained
        jAdeli.setText(jAdeli.getText().trim());
    }//GEN-LAST:event_jAdeliFocusGained

    private void jAdeliFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jAdeliFocusLost
        jAdeli.setText(jAdeli.getText().trim());
    }//GEN-LAST:event_jAdeliFocusLost

    private void jFermerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFermerActionPerformed
        setVisible(false);
        dispose();
    }//GEN-LAST:event_jFermerActionPerformed

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        
    }//GEN-LAST:event_formKeyPressed

    
    Connection laConnection = null ;
    Statement transmission ;
    ResultSet leResultat ;
    String nom, prenom, titre, message, version, activite, dateLimite ;
    
     public boolean connection (String a, String c) {
        //On démarre
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        //Variables
        java.util.Date dt = new java.util.Date();
        java.text.SimpleDateFormat sdf ;
        //Connection
        try {
            //On ouvre la transmission avec le serveur
            //DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            laConnection = DriverManager.getConnection ("jdbc:mysql://fredericmaillet.fr/fmaillet_professionnels?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "fmaillet_fredo", "mastercog");
        } catch (Exception e) {
            jMessages.append ("-> Connection error : \n"+e.toString()) ;
        }
        try {
            transmission = laConnection.createStatement (ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE) ;
            //On cherche dans la table Pro
            leResultat = transmission.executeQuery ("select * from Pro where ADELI = " + a) ;
            //On checke le premier retour (obligé c le seul car Adeli est unique)
            if (leResultat.next()) {
                boolean ok = false ;
                //C'est ok l'Adeli existe
                jMessages.append("-> Adeli ok dans la BdD\n");
                //Jeton libre ?
                byte jeton = leResultat.getByte("JETON") ;
                if ( (jeton & 0x01) != 0) { // le jeton est pris !!
                    jMessages.append("-> Utilisateur déjà connecté !?\n"); ; // Le jeton est déjà pris
                    //Check Mac Address
                    String chk = leResultat.getString ("MACADR") ;
                    if ( chk.equals(OrthoCotation.user.macaddress)) {
                        jMessages.append ("-> Ordinateur reconnu : ok.\n");
                        ok = true ;
                    }
                    else {
                        jMessages.append ("-> Ordinateurs différents: connexion différée une heure...\n");
                        dateLimite = leResultat.getString("LASTCONNECTION") ;
                        sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        Date d = sdf.parse(dateLimite);
                        //System.out.println (d) ; System.out.println (dt) ;
                        long delay = ((long) (dt.getTime() - d.getTime()) / (60 * 60 * 1000) ) ;
                        if (delay >= 1.0) //nbre d'heures
                        {
                            ok = true ;
                            jMessages.append ("-> Ordinateurs différents: connexion acceptée...\n");
                        }
                    }
                }
                else { // le jeton est libre
                    jeton = (byte) (jeton | 0x01) ;
                    leResultat.updateByte("JETON", jeton);
                    ok = true ;
                }
                //Connexion autorisée ?
                if (ok) {
                    //On met à jour la mac address sur la base
                    leResultat.updateString("MACADR", OrthoCotation.user.macaddress); // 3ème colonne
                    //Et la date/heure de connexion
                    sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String currentTime = sdf.format(dt);
                    leResultat.updateString("LASTCONNECTION", currentTime);    // 4ème colonne
                    leResultat.updateRow () ;
                
                    //On vérifie la date limite...
                    dateLimite = leResultat.getString("LIMITE") ;
                    sdf = new SimpleDateFormat("yyyy-MM-dd");
                    Date d = sdf.parse(dateLimite);
                    if (d.before(dt)) {
                        
                        jMessages.append ("-> Abonnement terminé au " + dateLimite + " : connexion impossible.\n");
                        ok = false ;
                    }
                }
                //System.out.println ("check") ;
                //String pwd = JOptionPane.showInputDialog (null, "Mot de passe :", "Identification au serveur :", JOptionPane.QUESTION_MESSAGE) ;
                if (ok && c.equals(leResultat.getString("PWD"))) {
                    jMessages.append("-> Code ok dans la BdD\n-> Récupération des données...\n");
                    jMessages.append("-> Période d'essai ok jusqu'au "+ dateLimite + "\n") ;
                    nom = leResultat.getString("NOM") ;
                    prenom = leResultat.getString("PRENOM") ;
                    titre = leResultat.getString("TITRE") ;
                    activite = leResultat.getString("ACTIVITE") ;
                    message = "" ;
                    message = leResultat.getString("MSG") ;
                    //Coordonnées
                    OrthoCotation.user.adr1 = leResultat.getString("ADR1") ;
                    OrthoCotation.user.adr2 = leResultat.getString("ADR2") ;
                    OrthoCotation.user.cp = leResultat.getString("CP") ;
                    OrthoCotation.user.ville = leResultat.getString("VILLE") ;
                    OrthoCotation.user.tel1 = leResultat.getString("TEL1") ;
                    OrthoCotation.user.tel2 = leResultat.getString("TEL2") ;
                    OrthoCotation.user.mail = leResultat.getString("MAIL") ;
                }
                else
                    jMessages.append("-> Erreur : Connection refusée...\n") ;
                
            } else
                jMessages.append ("-> Erreur : Adeli absent de la BdD !\n") ;
        } catch (Exception e) {
            jMessages.append ("-> Pb connection (identification) : \n"+e.toString()) ;
        }
        
        //On ferme
        try {
            laConnection.close();
            transmission.close () ;
            leResultat.close () ;
        } catch (SQLException e) {}
        //Retour
        setCursor(Cursor.getDefaultCursor());
        if (nom == null) return false ;
        else {
            OrthoCotation.user.adeli = a ;
            OrthoCotation.user.code = c ;
            OrthoCotation.user.nom = nom ;
            OrthoCotation.user.prenom = prenom ;
            OrthoCotation.user.titre = titre ;
            OrthoCotation.user.activite = activite ;
            jMessages.append ("-> Connexion ok : "+OrthoCotation.user.titre+" "+OrthoCotation.user.nom+" "+OrthoCotation.user.prenom+" ("+OrthoCotation.user.activite+")\n") ;
            if (message.length() > 0) jMessages.append ("-> Message du serveur :\n\n"+message) ;
            else jMessages.append ("-> Pas de message du serveur.\n") ;
            //On met à jour la fenêtre :
            OrthoCotation.connected () ;
            
            //On a fini
            return true ;
        }
    }

     
     
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFormattedTextField jAdeli;
    private javax.swing.JFormattedTextField jCode;
    private javax.swing.JButton jConnexion;
    private javax.swing.JButton jFermer;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextArea jMessages;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private java.awt.Label label1;
    private java.awt.Label label2;
    // End of variables declaration//GEN-END:variables
}
