/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fmaillet.orthocotation;

import java.awt.Color;
import java.awt.Graphics;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JLabel;
import javax.swing.border.MatteBorder;

/**
 *
 * @author Fred
 */
public class Panel_DEM extends javax.swing.JPanel {

    double error_M[]  = {14.9, 7.9, 4.0, 2.6, 2.0, 1.7, 1.1, 1.2, 0.6};
    double error_DS[] = {8.3, 7.6, 4.6, 3.8, 2.6, 2.0, 1.8, 1.9, 0.9} ;
    double ratio_M[]  = {1.53, 1.43, 1.31, 1.24, 1.18, 1.13, 1.12, 1.12, 1.12 } ;
    double ratio_DS[] = {0.29, 0.25, 0.20, 0.18, 0.12, 0.12, 0.09, 0.12, 0.07} ;
    double AHT_M[]    = {108.12, 75.01, 59.91, 52.04, 44.72, 39.49, 35.34, 33.16, 32.33} ;
    double AHT_DS[]   = {30.49, 19.33, 14.87, 12.78, 8.08, 8.44, 6.47, 6.57, 5.29} ;
    double VT_M[]     = {72.29, 52.74, 45.77, 41.98, 38.13, 35.06, 31.55, 29.71, 29.01} ;
    double VT_DS[]    = {20.99, 10.17, 9.68, 7.89, 6.35, 6.41, 5.74, 4.58, 4.91} ;
    
    int nsucoS_P[][]  = { {3, 3, 3, 3, 3, 3, 3, 3, 3, 4}, {3, 3, 3, 3, 3, 3, 3, 3, 3, 3} } ; //0: H 1: F
    int nsucoS_H[][]  = { {2, 2, 3, 3, 3, 3, 3, 3, 3, 3}, {2, 3, 3, 3, 3, 4, 4, 4, 4, 4} } ; //0: H 1: F
    int nsucoS_B[][]  = { {3, 3, 3, 4, 4, 4, 4, 4, 5, 5}, {4, 4, 4, 4, 4, 4, 5, 5, 5, 5} } ; //0: H 1: F
    
    int nsucoP_A[][]  = { {4, 4, 5, 5, 5, 5, 5, 5, 5, 5}, {5, 5, 5, 5, 5, 5, 5, 5, 5, 5} } ; //0: H 1: F
    int nsucoP_P[][]  = { {2, 2, 3, 3, 3, 4, 4, 4, 5, 5}, {3, 3, 3, 3, 4, 4, 4, 4, 4, 4} } ; //0: H 1: F
    int nsucoP_H[][]  = { {2, 2, 3, 3, 3, 4, 4, 4, 5, 5}, {3, 3, 3, 3, 3, 4, 4, 4, 4, 4} } ; //0: H 1: F
    int nsucoP_B[][]  = { {3, 3, 3, 4, 4, 4, 4, 5, 5, 5}, {4, 4, 4, 4, 4, 5, 5, 5, 5, 5} } ; //0: H 1: F
    
    /**
     * Creates new form Panel_DEM
     */
    public Panel_DEM() {
        initComponents();
        
        //On ajoute des listenrs sur les DS pour chger de couleur
        PropertyChangeListener l = new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                JLabel source = (JLabel) evt.getSource () ;
                double t  = Double.parseDouble(source.getText().replace(",",".") ) ;
                if (t> -1.0) source.setForeground(Color.GREEN);
                else source.setForeground(Color.RED);
            }
        };
        jH_Time_ds.addPropertyChangeListener("text", l);
        jV_Time_ds.addPropertyChangeListener("text", l);
        jRatio_ds.addPropertyChangeListener("text", l);
        jErrors_ds.addPropertyChangeListener("text", l);
        
        jGROFMAN_DS_A.addPropertyChangeListener("text", l);
        jGROFMAN_DS_B.addPropertyChangeListener("text", l);
        
        updateResults () ;
        updateNSUCO () ;
        updateGROFFMAN () ;
    }
    
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);  
        g.drawRect(jLabel2.getX()-10,jLabel2.getY()-10,250,60);
        g.drawRect(jLabel4.getX()-10,jLabel4.getY()-10,250,110);
        // Results
        g.drawRect(jLabel10.getX()-10,jLabel10.getY()-10,250,150);
        g.setColor(Color.GRAY);
        g.drawLine(jV_Time.getX()-10, jV_Time.getY()+30, jV_Time.getX()+120, jV_Time.getY()+30);
        /*g.setColor(Color.RED);  
        g.fillRect(230,80,10,10); */ 
    }
    
    public void updateResults () {
        //Temps vertical
        double testA = (double) jTest_A.getValue() ;
        double testB = (double) jTest_B.getValue() ;
        double time_V = testA + testB ;
        jV_Time.setText(String.format("%.2f", time_V));
        //Errors
        int err_o = (int) jError_O.getValue() ;
        int err_a = (int) jError_A.getValue() ;
        int err = (int) jError_S.getValue() + err_o + err_a + (int) jError_T.getValue() ;
        jErrors.setText(String.valueOf (err));
        //Temps horizontal corrigé
        double testC = (double) jTest_C.getValue() ;
        double time_H = testC * 80 / ( 80 - err_o + err_a) ;
        jH_Time.setText(String.format("%.2f", time_H));
        //ratio
        double ratio = time_H / time_V ;
        jRatio.setText(String.format("%.2f", ratio));
        //if connected and correct age range
        if (OrthoCotation.user.nom == null) return ;
        jMsgDEM.setText(null);
        int y = OrthoCotation.baseValues.patientAge.years - 6;
        if (y<0) {y = 0 ; jMsgDEM.setText("(Normes 6 ans par défaut)"); }
        else if (y>8) {y = 8 ; jMsgDEM.setText("(Normes 14 ans par défaut)"); }
        //DS errors
        double t = (error_M[y] - err) / error_DS[y] ;
        jErrors_ds.setText(String.format("%+.2f", t));
        //DS VT
        t = (VT_M[y] - time_V) / VT_DS[y] ;
        jV_Time_ds.setText(String.format("%+.2f", t));
        //DS VH
        t = (AHT_M[y] - time_H) / AHT_DS[y] ;
        jH_Time_ds.setText(String.format("%+.2f", t));
        //DS VT
        t = (ratio_M[y] - ratio) / ratio_DS[y] ;
        jRatio_ds.setText(String.format("%+.2f", t));
    }
    
    public void updateGROFFMAN () {
        
        //Connected ?
        if (OrthoCotation.user.nom == null) return ;
        //jMsgNSUCO.setText("");
        //Age ?
        if (OrthoCotation.baseValues.patientAge.years == 0) {
            jMsgGROFFMAN.setText("(Age ? Vous devez indiquer une date de naissance)");
            return ;
        }
        else jMsgGROFFMAN.setText(null);
        //Normes en fonction des limites d'âge
        if (OrthoCotation.baseValues.patientAge.years < 7) { jMsgGROFFMAN.setText("(Normes 7 ans par défaut)"); }
        else if (OrthoCotation.baseValues.patientAge.years > 12) {jMsgGROFFMAN.setText("(Normes 12 ans par défaut)"); }
        
        //Get all values
        int a[] = new int[5] ;
        int b[] = new int[5] ;
        a[0] = (Integer) jGROFFMAN_A_A.getValue() ;
        a[1] = (Integer) jGROFFMAN_A_B.getValue() ;
        a[2] = (Integer) jGROFFMAN_A_C.getValue() ;
        a[3] = (Integer) jGROFFMAN_A_D.getValue() ;
        a[4] = (Integer) jGROFFMAN_A_E.getValue() ;
        b[0] = (Integer) jGROFFMAN_B_A.getValue() ;
        b[1] = (Integer) jGROFFMAN_B_B.getValue() ;
        b[2] = (Integer) jGROFFMAN_B_C.getValue() ;
        b[3] = (Integer) jGROFFMAN_B_D.getValue() ;
        b[4] = (Integer) jGROFFMAN_B_E.getValue() ;
        //Compute score
        int scoreA = 0;
        int scoreB = 0;
        for (int i = 0; i<5; i++) {
            scoreA = scoreA + groffmanPoints (a[i]) ;
            scoreB = scoreB + groffmanPoints (b[i]) ;
        }
        jGROFMAN_scoreA.setText(String.valueOf(scoreA));
        jGROFMAN_scoreB.setText(String.valueOf(scoreB));
        jGROFMAN_DS_A.setText(String.format("%+.2f", groffmanDS(scoreA)));
        jGROFMAN_DS_B.setText(String.format("%+.2f", groffmanDS(scoreB)));
    }
    
    public int groffmanPoints (int t) {
        if (t==0) return 0 ;
        else if (t<16) return 10;
        else if (t>50 & t<=60) return 2 ;
        else if (t > 60) return 1 ;
        return 9 - ((t-16) / 5) ; 
    }
    
    private double groffmanDS (int score) {
        if (OrthoCotation.baseValues.patientAge.years <= 7)
            return ((double) score - 10.0 ) / 3.5 ;
        else if (OrthoCotation.baseValues.patientAge.years == 8)
            return ((double) score - 17.0 ) / 3.0 ;
        else if (OrthoCotation.baseValues.patientAge.years == 9)
            return ((double) score - 22.0 ) / 2.0 ;
        else if (OrthoCotation.baseValues.patientAge.years == 10)
            return ((double) score - 26.0 ) / 2.5 ;
        else if (OrthoCotation.baseValues.patientAge.years == 11)
            return ((double) score - 28.0 ) / 3.0 ;
        else 
            return ((double) score - 32.0 ) / 4.0 ;
    }
    
    public void updateNSUCO () {
        //Sexe coché ?
        if (!OrthoCotation.isGenreDefined ())
            jMsgGenre.setVisible(true);
        else jMsgGenre.setVisible(false);
        //Connected ?
        if (OrthoCotation.user.nom == null) return ;
        //jMsgNSUCO.setText("");
        //Age ?
        if (OrthoCotation.baseValues.patientAge.years == 0) {
            jMsgNSUCO.setText("(Age ? Vous devez indiquer une date de naissance)");
            return ;
        }
        else jMsgNSUCO.setText("");
        
        //Age -> index
        int y = OrthoCotation.baseValues.patientAge.years - 5;
        if (y<0) {y = 0 ; jMsgNSUCO.setText("(Normes 5 ans par défaut)"); jMsgNSUCO.setForeground(Color.BLUE);}
        if (y> 9) {y = 9 ; jMsgNSUCO.setText("(Normes 14 ans par défaut)"); jMsgNSUCO.setForeground(Color.BLUE);}
        //Aptitude
        if ((int) jNSUCO_S_A.getValue() < 5)
            jNSUCO_S_A.setBorder(new MatteBorder(2, 4, 2, 0, Color.RED));
        else
            jNSUCO_S_A.setBorder(new MatteBorder(2, 4, 2, 0, Color.GREEN));
        if ((int) jNSUCO_P_A.getValue() < nsucoP_A[OrthoCotation.baseValues.homme ? 0 : 1][y])
            jNSUCO_P_A.setBorder(new MatteBorder(2, 4, 2, 0, Color.RED));
        else
            jNSUCO_P_A.setBorder(new MatteBorder(2, 4, 2, 0, Color.GREEN));
        //Précision
        if ((int) jNSUCO_S_P.getValue() < nsucoS_P[OrthoCotation.baseValues.homme ? 0 : 1][y])
            jNSUCO_S_P.setBorder(new MatteBorder(2, 4, 2, 0, Color.RED));
        else
            jNSUCO_S_P.setBorder(new MatteBorder(2, 4, 2, 0, Color.GREEN));
        if ((int) jNSUCO_P_P.getValue() < nsucoP_P[OrthoCotation.baseValues.homme ? 0 : 1][y])
            jNSUCO_P_P.setBorder(new MatteBorder(2, 4, 2, 0, Color.RED));
        else
            jNSUCO_P_P.setBorder(new MatteBorder(2, 4, 2, 0, Color.GREEN));
        //Mvt de Tête
        if ((int) jNSUCO_S_H.getValue() < nsucoS_H[OrthoCotation.baseValues.homme ? 0 : 1][y])
            jNSUCO_S_H.setBorder(new MatteBorder(2, 4, 2, 0, Color.RED));
        else
            jNSUCO_S_H.setBorder(new MatteBorder(2, 4, 2, 0, Color.GREEN));
        if ((int) jNSUCO_P_H.getValue() < nsucoP_H[OrthoCotation.baseValues.homme ? 0 : 1][y])
            jNSUCO_P_H.setBorder(new MatteBorder(2, 4, 2, 0, Color.RED));
        else
            jNSUCO_P_H.setBorder(new MatteBorder(2, 4, 2, 0, Color.GREEN));
        //Mvt de Corps
        if ((int) jNSUCO_S_B.getValue() < nsucoS_B[OrthoCotation.baseValues.homme ? 0 : 1][y])
            jNSUCO_S_B.setBorder(new MatteBorder(2, 4, 2, 0, Color.RED));
        else
            jNSUCO_S_B.setBorder(new MatteBorder(2, 4, 2, 0, Color.GREEN));
        if ((int) jNSUCO_P_B.getValue() < nsucoP_B[OrthoCotation.baseValues.homme ? 0 : 1][y])
            jNSUCO_P_B.setBorder(new MatteBorder(2, 4, 2, 0, Color.RED));
        else
            jNSUCO_P_B.setBorder(new MatteBorder(2, 4, 2, 0, Color.GREEN));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTest_A = new javax.swing.JSpinner();
        jLabel2 = new javax.swing.JLabel();
        jUnit1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTest_B = new javax.swing.JSpinner();
        jUnit2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTest_C = new javax.swing.JSpinner();
        jUnit3 = new javax.swing.JLabel();
        jError_S = new javax.swing.JSpinner();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jError_O = new javax.swing.JSpinner();
        jLabel8 = new javax.swing.JLabel();
        jError_A = new javax.swing.JSpinner();
        jLabel9 = new javax.swing.JLabel();
        jError_T = new javax.swing.JSpinner();
        jLabel10 = new javax.swing.JLabel();
        jH_Time = new javax.swing.JTextField();
        jUnit4 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jRatio = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jV_Time = new javax.swing.JTextField();
        jUnit6 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jErrors = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jH_Time_ds = new javax.swing.JLabel();
        jV_Time_ds = new javax.swing.JLabel();
        jRatio_ds = new javax.swing.JLabel();
        jErrors_ds = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel16 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jMsgDEM = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jNSUCO_S_A = new javax.swing.JSpinner();
        jNSUCO_S_P = new javax.swing.JSpinner();
        jNSUCO_S_H = new javax.swing.JSpinner();
        jNSUCO_S_B = new javax.swing.JSpinner();
        jMsgNSUCO = new javax.swing.JLabel();
        jNSUCO_P_A = new javax.swing.JSpinner();
        jNSUCO_P_P = new javax.swing.JSpinner();
        jNSUCO_P_H = new javax.swing.JSpinner();
        jNSUCO_P_B = new javax.swing.JSpinner();
        jUnit5 = new javax.swing.JLabel();
        jUnit7 = new javax.swing.JLabel();
        jUnit8 = new javax.swing.JLabel();
        jUnit9 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jMsgGROFFMAN = new javax.swing.JLabel();
        jMsgGenre = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jUnit10 = new javax.swing.JLabel();
        jGROFFMAN_A_A = new javax.swing.JSpinner();
        jGROFFMAN_B_A = new javax.swing.JSpinner();
        jUnit11 = new javax.swing.JLabel();
        jGROFFMAN_A_B = new javax.swing.JSpinner();
        jGROFFMAN_B_B = new javax.swing.JSpinner();
        jUnit12 = new javax.swing.JLabel();
        jGROFFMAN_A_C = new javax.swing.JSpinner();
        jGROFFMAN_B_C = new javax.swing.JSpinner();
        jUnit13 = new javax.swing.JLabel();
        jGROFFMAN_A_D = new javax.swing.JSpinner();
        jGROFFMAN_B_D = new javax.swing.JSpinner();
        jUnit14 = new javax.swing.JLabel();
        jGROFFMAN_A_E = new javax.swing.JSpinner();
        jGROFFMAN_B_E = new javax.swing.JSpinner();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jGROFMAN_scoreA = new javax.swing.JTextField();
        jGROFMAN_scoreB = new javax.swing.JTextField();
        jGROFMAN_DS_A = new javax.swing.JLabel();
        jGROFMAN_DS_B = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(700, 550));

        jTest_A.setModel(new javax.swing.SpinnerNumberModel(15.0d, 1.0d, null, 0.1d));
        jTest_A.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTest_AStateChanged(evt);
            }
        });

        jLabel2.setText("Test A :");

        jUnit1.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jUnit1.setText("sec.");

        jLabel3.setText("Test B :");

        jTest_B.setModel(new javax.swing.SpinnerNumberModel(15.0d, 1.0d, null, 0.1d));
        jTest_B.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTest_BStateChanged(evt);
            }
        });

        jUnit2.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jUnit2.setText("sec.");

        jLabel4.setText("Test C :");

        jTest_C.setModel(new javax.swing.SpinnerNumberModel(20.0d, 1.0d, null, 0.1d));
        jTest_C.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTest_CStateChanged(evt);
            }
        });

        jUnit3.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jUnit3.setText("sec.");

        jError_S.setModel(new javax.swing.SpinnerNumberModel());
        jError_S.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jError_SStateChanged(evt);
            }
        });

        jLabel5.setText("Vertical");

        jLabel6.setText("Horizontal");

        jLabel1.setText("s :");

        jLabel7.setText("o :");

        jError_O.setModel(new javax.swing.SpinnerNumberModel());
        jError_O.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jError_OStateChanged(evt);
            }
        });

        jLabel8.setText("a :");

        jError_A.setModel(new javax.swing.SpinnerNumberModel());
        jError_A.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jError_AStateChanged(evt);
            }
        });

        jLabel9.setText("t :");

        jError_T.setModel(new javax.swing.SpinnerNumberModel());
        jError_T.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jError_TStateChanged(evt);
            }
        });

        jLabel10.setText("H Time (adj.):");

        jH_Time.setEditable(false);
        jH_Time.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jH_Time.setPreferredSize(new java.awt.Dimension(45, 20));

        jUnit4.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jUnit4.setText("sec.");

        jLabel11.setText("Ratio :");

        jRatio.setEditable(false);
        jRatio.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jRatio.setPreferredSize(new java.awt.Dimension(45, 20));

        jLabel12.setText("V Time :");

        jV_Time.setEditable(false);
        jV_Time.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jV_Time.setPreferredSize(new java.awt.Dimension(45, 20));

        jUnit6.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jUnit6.setText("sec.");

        jLabel13.setText("Errors :");

        jErrors.setEditable(false);
        jErrors.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jErrors.setPreferredSize(new java.awt.Dimension(45, 20));

        jLabel14.setText("Resultats [2012, Italian norms]");

        jH_Time_ds.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jH_Time_ds.setForeground(java.awt.Color.red);
        jH_Time_ds.setText("...");

        jV_Time_ds.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jV_Time_ds.setForeground(java.awt.Color.red);
        jV_Time_ds.setText("...");

        jRatio_ds.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jRatio_ds.setForeground(java.awt.Color.red);
        jRatio_ds.setText("...");

        jErrors_ds.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jErrors_ds.setForeground(java.awt.Color.red);
        jErrors_ds.setText("...");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setForeground(java.awt.Color.gray);
        jLabel15.setText("DEM");

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel16.setForeground(java.awt.Color.gray);
        jLabel16.setText("NSUCO");

        jMsgDEM.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jMsgDEM.setForeground(java.awt.Color.red);
        jMsgDEM.setText("(Les écarts à la norme ne sont pas calculés en mode démo)");

        jLabel17.setText("Saccades :");

        jLabel18.setText("Poursuite :");

        jNSUCO_S_A.setModel(new javax.swing.SpinnerNumberModel(5, 0, 5, 1));
        jNSUCO_S_A.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jNSUCO_S_AStateChanged(evt);
            }
        });

        jNSUCO_S_P.setModel(new javax.swing.SpinnerNumberModel(5, 0, 5, 1));
        jNSUCO_S_P.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jNSUCO_S_PStateChanged(evt);
            }
        });

        jNSUCO_S_H.setModel(new javax.swing.SpinnerNumberModel(5, 0, 5, 1));
        jNSUCO_S_H.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jNSUCO_S_HStateChanged(evt);
            }
        });

        jNSUCO_S_B.setModel(new javax.swing.SpinnerNumberModel(5, 0, 5, 1));
        jNSUCO_S_B.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jNSUCO_S_BStateChanged(evt);
            }
        });

        jMsgNSUCO.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jMsgNSUCO.setForeground(java.awt.Color.red);
        jMsgNSUCO.setText("(Ecarts à la norme non calculés en mode démo)");

        jNSUCO_P_A.setModel(new javax.swing.SpinnerNumberModel(5, 0, 5, 1));
        jNSUCO_P_A.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jNSUCO_P_AStateChanged(evt);
            }
        });

        jNSUCO_P_P.setModel(new javax.swing.SpinnerNumberModel(5, 0, 5, 1));
        jNSUCO_P_P.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jNSUCO_P_PStateChanged(evt);
            }
        });

        jNSUCO_P_H.setModel(new javax.swing.SpinnerNumberModel(5, 0, 5, 1));
        jNSUCO_P_H.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jNSUCO_P_HStateChanged(evt);
            }
        });

        jNSUCO_P_B.setModel(new javax.swing.SpinnerNumberModel(5, 0, 5, 1));
        jNSUCO_P_B.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jNSUCO_P_BStateChanged(evt);
            }
        });

        jUnit5.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jUnit5.setText("Apt.");

        jUnit7.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jUnit7.setText("Préc.");

        jUnit8.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jUnit8.setText("Tête");

        jUnit9.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jUnit9.setText("Corps");

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel19.setForeground(java.awt.Color.gray);
        jLabel19.setText("GROFFMAN Visual Tracing Test");

        jMsgGROFFMAN.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jMsgGROFFMAN.setForeground(java.awt.Color.red);
        jMsgGROFFMAN.setText("(Ecarts à la norme non calculés en mode démo)");

        jMsgGenre.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jMsgGenre.setForeground(java.awt.Color.red);
        jMsgGenre.setText("!! Genre non défini !");

        jLabel20.setText("Forme A :");

        jLabel21.setText("Forme B :");

        jUnit10.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jUnit10.setText("A(3)");

        jGROFFMAN_A_A.setModel(new javax.swing.SpinnerNumberModel(12, 0, null, 1));
        jGROFFMAN_A_A.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jGROFFMAN_A_AStateChanged(evt);
            }
        });

        jGROFFMAN_B_A.setModel(new javax.swing.SpinnerNumberModel(12, 0, null, 1));
        jGROFFMAN_B_A.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jGROFFMAN_B_AStateChanged(evt);
            }
        });

        jUnit11.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jUnit11.setText("B(4)");

        jGROFFMAN_A_B.setModel(new javax.swing.SpinnerNumberModel(12, 0, null, 1));
        jGROFFMAN_A_B.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jGROFFMAN_A_BStateChanged(evt);
            }
        });

        jGROFFMAN_B_B.setModel(new javax.swing.SpinnerNumberModel(12, 0, null, 1));
        jGROFFMAN_B_B.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jGROFFMAN_B_BStateChanged(evt);
            }
        });

        jUnit12.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jUnit12.setText("C(1)");

        jGROFFMAN_A_C.setModel(new javax.swing.SpinnerNumberModel(12, 0, null, 1));
        jGROFFMAN_A_C.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jGROFFMAN_A_CStateChanged(evt);
            }
        });

        jGROFFMAN_B_C.setModel(new javax.swing.SpinnerNumberModel(12, 0, null, 1));
        jGROFFMAN_B_C.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jGROFFMAN_B_CStateChanged(evt);
            }
        });

        jUnit13.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jUnit13.setText("D(5)");

        jGROFFMAN_A_D.setModel(new javax.swing.SpinnerNumberModel(12, 0, null, 1));
        jGROFFMAN_A_D.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jGROFFMAN_A_DStateChanged(evt);
            }
        });

        jGROFFMAN_B_D.setModel(new javax.swing.SpinnerNumberModel(12, 0, null, 1));
        jGROFFMAN_B_D.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jGROFFMAN_B_DStateChanged(evt);
            }
        });

        jUnit14.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jUnit14.setText("E(2)");

        jGROFFMAN_A_E.setModel(new javax.swing.SpinnerNumberModel(12, 0, null, 1));
        jGROFFMAN_A_E.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jGROFFMAN_A_EStateChanged(evt);
            }
        });

        jGROFFMAN_B_E.setModel(new javax.swing.SpinnerNumberModel(12, 0, null, 1));
        jGROFFMAN_B_E.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jGROFFMAN_B_EStateChanged(evt);
            }
        });

        jLabel22.setText("score :");

        jLabel23.setText("score :");

        jGROFMAN_scoreA.setEditable(false);
        jGROFMAN_scoreA.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jGROFMAN_scoreA.setPreferredSize(new java.awt.Dimension(45, 20));

        jGROFMAN_scoreB.setEditable(false);
        jGROFMAN_scoreB.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jGROFMAN_scoreB.setPreferredSize(new java.awt.Dimension(45, 20));

        jGROFMAN_DS_A.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jGROFMAN_DS_A.setForeground(java.awt.Color.red);
        jGROFMAN_DS_A.setText("...");

        jGROFMAN_DS_B.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jGROFMAN_DS_B.setForeground(java.awt.Color.red);
        jGROFMAN_DS_B.setText("...");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 552, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jUnit10, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(8, 8, 8))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel21)
                                .addGap(18, 18, 18)
                                .addComponent(jGROFFMAN_B_A, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel20)
                                .addGap(18, 18, 18)
                                .addComponent(jGROFFMAN_A_A, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jUnit11, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(8, 8, 8))
                            .addComponent(jGROFFMAN_B_B, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jGROFFMAN_A_B, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jUnit12, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(8, 8, 8))
                            .addComponent(jGROFFMAN_B_C, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jGROFFMAN_A_C, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jUnit13, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(8, 8, 8))
                            .addComponent(jGROFFMAN_B_D, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jGROFFMAN_A_D, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jUnit14, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(8, 8, 8))
                            .addComponent(jGROFFMAN_B_E, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jGROFFMAN_A_E, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel23)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jGROFMAN_scoreB, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jGROFMAN_DS_B, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel22)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jGROFMAN_scoreA, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jGROFMAN_DS_A, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jSeparator2)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addGap(29, 29, 29)
                                .addComponent(jMsgDEM))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(1, 1, 1)
                                            .addComponent(jLabel5))
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(11, 11, 11)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addComponent(jLabel3)
                                                    .addGap(18, 18, 18)
                                                    .addComponent(jTest_B, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addComponent(jUnit2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(layout.createSequentialGroup()
                                                    .addComponent(jLabel2)
                                                    .addGap(18, 18, 18)
                                                    .addComponent(jTest_A, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addComponent(jUnit1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel6)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(11, 11, 11)
                                                .addComponent(jLabel4)
                                                .addGap(18, 18, 18)
                                                .addComponent(jTest_C, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jUnit3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(28, 28, 28)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel1)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jError_S, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel7))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel8)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jError_A, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel9)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jError_T, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jError_O, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(85, 85, 85)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel13)
                                        .addComponent(jLabel11)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel10)
                                                .addGap(18, 18, 18)
                                                .addComponent(jH_Time, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jUnit4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel12)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jRatio, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addComponent(jV_Time, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addComponent(jUnit6, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addComponent(jErrors, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                    .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jH_Time_ds, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jV_Time_ds, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jRatio_ds, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jErrors_ds, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addGap(18, 18, 18)
                                .addComponent(jMsgNSUCO))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel18)
                            .addComponent(jLabel17))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jNSUCO_S_A, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jUnit5, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jUnit7)
                                    .addComponent(jNSUCO_S_P, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jNSUCO_S_H, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jUnit8))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jUnit9)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jNSUCO_S_B, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(36, 36, 36)
                                        .addComponent(jMsgGenre))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jNSUCO_P_A, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jNSUCO_P_P, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jNSUCO_P_H, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jMsgGROFFMAN)
                                    .addComponent(jNSUCO_P_B, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addComponent(jLabel19))
                .addContainerGap(138, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jMsgDEM))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(jH_Time, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jUnit4)
                            .addComponent(jH_Time_ds))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(jV_Time, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jUnit6)
                            .addComponent(jV_Time_ds))
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(jRatio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jRatio_ds))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(jErrors, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jErrors_ds)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTest_A, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(jUnit1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTest_B, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(jUnit2))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTest_C, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(jUnit3))
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jError_S, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(jError_O, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jError_A, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addComponent(jError_T, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))))
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(jMsgNSUCO))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jUnit5)
                    .addComponent(jUnit7)
                    .addComponent(jUnit8)
                    .addComponent(jUnit9))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jMsgGenre))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(jNSUCO_S_A, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jNSUCO_S_P, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jNSUCO_S_H, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jNSUCO_S_B, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18)
                            .addComponent(jNSUCO_P_A, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jNSUCO_P_P, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jNSUCO_P_H, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jNSUCO_P_B, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel19)
                            .addComponent(jMsgGROFFMAN))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jUnit10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel20)
                                    .addComponent(jGROFFMAN_A_A, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel21)
                                    .addComponent(jGROFFMAN_B_A, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jUnit13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jGROFFMAN_A_D, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jGROFFMAN_B_D, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jUnit11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jGROFFMAN_A_B, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jGROFFMAN_B_B, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jUnit12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jGROFFMAN_A_C, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jGROFFMAN_B_C, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jUnit14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jGROFFMAN_A_E, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel22)
                            .addComponent(jGROFMAN_scoreA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jGROFMAN_DS_A))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jGROFFMAN_B_E, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel23)
                            .addComponent(jGROFMAN_scoreB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jGROFMAN_DS_B))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTest_AStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTest_AStateChanged
        updateResults () ;
    }//GEN-LAST:event_jTest_AStateChanged

    private void jTest_BStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTest_BStateChanged
        updateResults () ;
    }//GEN-LAST:event_jTest_BStateChanged

    private void jError_SStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jError_SStateChanged
        updateResults () ;
    }//GEN-LAST:event_jError_SStateChanged

    private void jError_OStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jError_OStateChanged
        updateResults () ;
    }//GEN-LAST:event_jError_OStateChanged

    private void jError_AStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jError_AStateChanged
        updateResults () ;
    }//GEN-LAST:event_jError_AStateChanged

    private void jError_TStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jError_TStateChanged
        updateResults () ;
    }//GEN-LAST:event_jError_TStateChanged

    private void jTest_CStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTest_CStateChanged
        updateResults () ;
    }//GEN-LAST:event_jTest_CStateChanged

    private void jNSUCO_S_AStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jNSUCO_S_AStateChanged
        updateNSUCO () ;
    }//GEN-LAST:event_jNSUCO_S_AStateChanged

    private void jNSUCO_S_PStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jNSUCO_S_PStateChanged
        updateNSUCO () ;
    }//GEN-LAST:event_jNSUCO_S_PStateChanged

    private void jNSUCO_S_HStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jNSUCO_S_HStateChanged
        updateNSUCO () ;
    }//GEN-LAST:event_jNSUCO_S_HStateChanged

    private void jNSUCO_S_BStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jNSUCO_S_BStateChanged
        updateNSUCO () ;
    }//GEN-LAST:event_jNSUCO_S_BStateChanged

    private void jNSUCO_P_AStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jNSUCO_P_AStateChanged
        updateNSUCO () ;
    }//GEN-LAST:event_jNSUCO_P_AStateChanged

    private void jNSUCO_P_PStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jNSUCO_P_PStateChanged
        updateNSUCO () ;
    }//GEN-LAST:event_jNSUCO_P_PStateChanged

    private void jNSUCO_P_HStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jNSUCO_P_HStateChanged
        updateNSUCO () ;
    }//GEN-LAST:event_jNSUCO_P_HStateChanged

    private void jNSUCO_P_BStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jNSUCO_P_BStateChanged
        updateNSUCO () ;
    }//GEN-LAST:event_jNSUCO_P_BStateChanged

    private void jGROFFMAN_A_AStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jGROFFMAN_A_AStateChanged
        updateGROFFMAN () ;
    }//GEN-LAST:event_jGROFFMAN_A_AStateChanged

    private void jGROFFMAN_B_AStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jGROFFMAN_B_AStateChanged
        updateGROFFMAN () ;
    }//GEN-LAST:event_jGROFFMAN_B_AStateChanged

    private void jGROFFMAN_A_BStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jGROFFMAN_A_BStateChanged
        updateGROFFMAN () ;
    }//GEN-LAST:event_jGROFFMAN_A_BStateChanged

    private void jGROFFMAN_B_BStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jGROFFMAN_B_BStateChanged
        updateGROFFMAN () ;
    }//GEN-LAST:event_jGROFFMAN_B_BStateChanged

    private void jGROFFMAN_A_CStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jGROFFMAN_A_CStateChanged
        updateGROFFMAN () ;
    }//GEN-LAST:event_jGROFFMAN_A_CStateChanged

    private void jGROFFMAN_B_CStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jGROFFMAN_B_CStateChanged
        updateGROFFMAN () ;
    }//GEN-LAST:event_jGROFFMAN_B_CStateChanged

    private void jGROFFMAN_A_DStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jGROFFMAN_A_DStateChanged
        updateGROFFMAN () ;
    }//GEN-LAST:event_jGROFFMAN_A_DStateChanged

    private void jGROFFMAN_B_DStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jGROFFMAN_B_DStateChanged
        updateGROFFMAN () ;
    }//GEN-LAST:event_jGROFFMAN_B_DStateChanged

    private void jGROFFMAN_A_EStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jGROFFMAN_A_EStateChanged
        updateGROFFMAN () ;
    }//GEN-LAST:event_jGROFFMAN_A_EStateChanged

    private void jGROFFMAN_B_EStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jGROFFMAN_B_EStateChanged
        updateGROFFMAN () ;
    }//GEN-LAST:event_jGROFFMAN_B_EStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSpinner jError_A;
    private javax.swing.JSpinner jError_O;
    private javax.swing.JSpinner jError_S;
    private javax.swing.JSpinner jError_T;
    private javax.swing.JTextField jErrors;
    private javax.swing.JLabel jErrors_ds;
    private javax.swing.JSpinner jGROFFMAN_A_A;
    private javax.swing.JSpinner jGROFFMAN_A_B;
    private javax.swing.JSpinner jGROFFMAN_A_C;
    private javax.swing.JSpinner jGROFFMAN_A_D;
    private javax.swing.JSpinner jGROFFMAN_A_E;
    private javax.swing.JSpinner jGROFFMAN_B_A;
    private javax.swing.JSpinner jGROFFMAN_B_B;
    private javax.swing.JSpinner jGROFFMAN_B_C;
    private javax.swing.JSpinner jGROFFMAN_B_D;
    private javax.swing.JSpinner jGROFFMAN_B_E;
    private javax.swing.JLabel jGROFMAN_DS_A;
    private javax.swing.JLabel jGROFMAN_DS_B;
    private javax.swing.JTextField jGROFMAN_scoreA;
    private javax.swing.JTextField jGROFMAN_scoreB;
    private javax.swing.JTextField jH_Time;
    private javax.swing.JLabel jH_Time_ds;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    public static javax.swing.JLabel jMsgDEM;
    public static javax.swing.JLabel jMsgGROFFMAN;
    private javax.swing.JLabel jMsgGenre;
    public static javax.swing.JLabel jMsgNSUCO;
    private javax.swing.JSpinner jNSUCO_P_A;
    private javax.swing.JSpinner jNSUCO_P_B;
    private javax.swing.JSpinner jNSUCO_P_H;
    private javax.swing.JSpinner jNSUCO_P_P;
    private javax.swing.JSpinner jNSUCO_S_A;
    private javax.swing.JSpinner jNSUCO_S_B;
    private javax.swing.JSpinner jNSUCO_S_H;
    private javax.swing.JSpinner jNSUCO_S_P;
    private javax.swing.JTextField jRatio;
    private javax.swing.JLabel jRatio_ds;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSpinner jTest_A;
    private javax.swing.JSpinner jTest_B;
    private javax.swing.JSpinner jTest_C;
    private javax.swing.JLabel jUnit1;
    private javax.swing.JLabel jUnit10;
    private javax.swing.JLabel jUnit11;
    private javax.swing.JLabel jUnit12;
    private javax.swing.JLabel jUnit13;
    private javax.swing.JLabel jUnit14;
    private javax.swing.JLabel jUnit2;
    private javax.swing.JLabel jUnit3;
    private javax.swing.JLabel jUnit4;
    private javax.swing.JLabel jUnit5;
    private javax.swing.JLabel jUnit6;
    private javax.swing.JLabel jUnit7;
    private javax.swing.JLabel jUnit8;
    private javax.swing.JLabel jUnit9;
    private javax.swing.JTextField jV_Time;
    private javax.swing.JLabel jV_Time_ds;
    // End of variables declaration//GEN-END:variables
}
