/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fmaillet.orthocotation;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JLabel;

/**
 *
 * @author Fred
 */
public class Panel_VB extends javax.swing.JPanel {

    double sheardCriteriumVP, sheardCriteriumVL ;
    
    /**
     * Creates new form BaseDatas
     */
    public Panel_VB() {
        initComponents();
               
        //placement
        
        //jLabel12.setLocation(jLabel12.getX()+50, jPhorieLds.getY());
        
        //Unités
        jUnit1.setText("\u0394");jUnit2.setText("\u0394");jUnit3.setText("\u0394");jUnit4.setText("\u0394");
        jUnit5.setText("\u0394");jUnit6.setText("\u0394");jUnit7.setText("cm");jUnit8.setText("cm");
        jUnit10.setText("\u0394/\u03B4");
        //jUnit8.setLocation(jUnit7.getX(), jUnit8.getY());
        
        jSphere_1.addMouseListener(new MyMouseListener()) ;
        jSphere_2.addMouseListener(new MyMouseListener()) ;
        jSphere_3.addMouseListener(new MyMouseListener()) ;
        
        //Couleur des DS
        PropertyChangeListener l = new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                JLabel source = (JLabel) evt.getSource () ;
                try {
                    String s  = source.getText() ;
                    double t  = Double.parseDouble(s.substring(0, s.length()-2).replace(",",".") ) ;
                    if (t> -1.0) source.setForeground(Color.GREEN);
                     else source.setForeground(Color.RED);
                } catch (Exception e) {}
            }
        };
        
        jPhorieLds.addPropertyChangeListener("text", l);
        jPhoriePds.addPropertyChangeListener("text", l);
        jFusionDPds.addPropertyChangeListener("text", l);
        jFusionCPds.addPropertyChangeListener("text", l);
        jFusionDLds.addPropertyChangeListener("text", l);
        jFusionCLds.addPropertyChangeListener("text", l);
        jPPCds.addPropertyChangeListener("text", l);
        jPPAds.addPropertyChangeListener("text", l);
        jACAds.addPropertyChangeListener("text", l);
        jFDVds.addPropertyChangeListener("text", l);
        jFDAds.addPropertyChangeListener("text", l);
        
        //Update all
        jPhoriePStateChanged (null) ;
        jPhorieLStateChanged (null) ;
        jFusionDPStateChanged (null) ;
        jFusionCPStateChanged (null) ;
        jFusionDLStateChanged (null) ;
        jFusionCLStateChanged (null) ;
        jPPAStateChanged (null) ;
        jPPCStateChanged (null) ;
        jFDVStateChanged (null) ;
        jFDAStateChanged (null) ;
    }
    
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);  
        //Phories
        g.drawRect(jCheckPhorieL.getX()-5,jCheckPhorieL.getY()-5,260,65); 
        //Fusion
        g.drawRect(jCheckFusionDP.getX()-5,jCheckFusionDP.getY()-5,260,128); 
        //PPC
        g.drawRect(jCheckPPC.getX()-5,jCheckPPC.getY()-5,330,70);
        //ACA
        g.drawRect(jLabel1.getX()-5,jLabel1.getY()-12,530,78);  
        
    }
    
    double gradientACA, calculACA, meanACA ;
    boolean gradientACA_isValid = false ;
    boolean calculACA_isValid = false ;
    
    private void updateSheardVL () {
        sheardCriteriumVL = 0 ;
        double requis = 0 ;
        if (OrthoCotation.baseValues.phorieL.raw < 0) {
            requis = - 2 * OrthoCotation.baseValues.phorieL.raw ;
            if (OrthoCotation.baseValues.fusionCL.raw < requis) {
                jSheardVL.setText("nok (requis: C" + String.valueOf((int) requis) + ")") ;
                jSheardVL.setForeground(Color.RED);
                
                double d = 2 * OrthoCotation.baseValues.phorieL.raw + OrthoCotation.baseValues.fusionCL.raw ;
                sheardCriteriumVL = d / 7.0 ;
            }
            else {
                jSheardVL.setText("ok  (requis: C" + String.valueOf((int) requis) + ")") ;
                jSheardVL.setForeground(Color.GREEN);
            }
        }
        else if (OrthoCotation.baseValues.phorieL.raw > 0) {
            requis = 2 * OrthoCotation.baseValues.phorieL.raw ;
            if (OrthoCotation.baseValues.fusionDL.raw < requis) {
                jSheardVL.setText("nok (requis: D" + String.valueOf((int) requis) + ")") ;
                jSheardVL.setForeground(Color.RED);
                
                double d = OrthoCotation.baseValues.fusionDL.raw - 2 * OrthoCotation.baseValues.phorieL.raw;
                sheardCriteriumVL = d / 3.0 ;
            }
            else {
                jSheardVL.setText("ok  (requis: D" + String.valueOf((int) requis) + ")") ;
                jSheardVL.setForeground(Color.GREEN);
            }
        }
        else {
            jSheardVL.setText("ok") ;
            jSheardVL.setForeground(Color.GREEN);
        }
    }
    
    private void updateSheardVP () {
        if (OrthoCotation.baseValues.patientAge.years == 0) {
            jSheardVP.setText("[age ?]") ;
            jSheardVP.setToolTipText("Précisez la date de naissance...");
            jSheardVP.setForeground(Color.BLUE);
            return ;
        }
        sheardCriteriumVP = 0 ;
        double requis = 0 ;
        if (OrthoCotation.baseValues.phorieP.raw < 0) {
            requis = - 2 * OrthoCotation.baseValues.phorieP.raw ;
            jSheardVP.setToolTipText(null);
            if (OrthoCotation.baseValues.fusionCP.raw < requis) {
                jSheardVP.setText("nok (requis: C'" + String.valueOf((int) requis) + ")") ;
                jSheardVP.setForeground(Color.RED);
                
                double d = 2 * OrthoCotation.baseValues.phorieP.raw + OrthoCotation.baseValues.fusionCP.raw ;
                if (OrthoCotation.baseValues.patientAge.years <= 12)
                    sheardCriteriumVP = d / 8.0 ;
                else
                    sheardCriteriumVP = d / 9.0 ;
            
            }
            else {
                jSheardVP.setText("ok  (requis: C'" + String.valueOf((int) requis) + ")") ;
                jSheardVP.setForeground(Color.GREEN);
            }
            
        }
        else if (OrthoCotation.baseValues.phorieP.raw > 0) {
            requis = 2 * OrthoCotation.baseValues.phorieP.raw ;
            if ( OrthoCotation.baseValues.fusionDP.raw < requis ) {
                jSheardVP.setText("nok (requis: D'" + String.valueOf((int) requis) + ")") ;
                jSheardVP.setForeground(Color.RED);
                
                double d =  OrthoCotation.baseValues.fusionDP.raw - 2 * OrthoCotation.baseValues.phorieP.raw;
                if (OrthoCotation.baseValues.patientAge.years <= 12)
                    sheardCriteriumVP = d / 5.0 ;
                else
                    sheardCriteriumVP = d / 6.0 ;
            }
            else {
                jSheardVP.setText("ok (requis D'" + String.valueOf((int) requis) + ")") ;
                jSheardVP.setForeground(Color.GREEN);
            }
            
        }
        else {
            jSheardVP.setText("ok") ;
            jSheardVP.setForeground(Color.GREEN);
        }
    }
    
    private void gradientACA () {
        int p0 = (int) jACA_0.getValue() ;
        int p1 = (int) jACA_1.getValue() ;
        int p2 = (int) jACA_2.getValue() ;
        int p3 = (int) jACA_3.getValue() ;
        
        double s = 0 ; double i = 0 ;
        if (jACA_1.isEnabled()) {s = s + (double)(p1-p0) / 1.0 ; i++ ;}
        if (jACA_2.isEnabled()) {s = s + (double)(p2-p0) / 2.0 ; i++ ;}
        if (jACA_3.isEnabled()) {s = s + (double)(p3-p0) / 3.0 ; i++ ;}
        
        gradientACA = s / i ;
        
        //gradientACA = (double)( (double)(p3-p0)/3.0 + (double)(p2-p0)/2.0 + (double)(p1-p0) ) / 3.0 ;
        
        jGradient.setText(String.format("%+.2f", gradientACA));
        gradientACA_isValid = true ;
        jCheckACA.setSelected(true);
        
        if (calculACA_isValid) meanACA = (gradientACA + calculACA) / 2 ;
        else meanACA = gradientACA ;
        meanACA = Math.round(meanACA * 100.0) / 100.0 ;
        jACA.setValue(Double.valueOf(meanACA)) ;
        //Déviation standard
        double ds = OrthoCotation.baseValues.updateACA ( meanACA, jCheckACA.isSelected() ) ;
        jACAds.setText(String.format("%+.2f", ds) + " DS");
    }
    
    public void calculACA () {
        double e = (double) jEIP.getValue() ;
        int p = (int) jACA_PP.getValue() ;
        int l = (int) jACA_PL.getValue() ;
        
        calculACA = e + .40 * (double) (p - l) ;
        jACA_Calcul.setText(String.format("%+.2f", calculACA));
        calculACA_isValid = true ;
        jCheckACA.setSelected(true);
        
        if (gradientACA_isValid) meanACA = (gradientACA + calculACA) / 2 ;
        else meanACA = calculACA ;
        meanACA = Math.round(meanACA * 100.0) / 100.0 ;
        jACA.setValue(Double.valueOf(meanACA)) ;
        //Déviation standard
        double ds = OrthoCotation.baseValues.updateACA ( meanACA, jCheckACA.isSelected() ) ;
        jACAds.setText(String.format("%+.2f", ds) + " DS");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPhorieL = new javax.swing.JSpinner();
        jPhorieP = new javax.swing.JSpinner();
        jPhorieLds = new javax.swing.JLabel();
        jPhoriePds = new javax.swing.JLabel();
        jCheckPhorieL = new javax.swing.JCheckBox();
        jCheckPhorieP = new javax.swing.JCheckBox();
        jCheckFusionDP = new javax.swing.JCheckBox();
        jFusionDP = new javax.swing.JSpinner();
        jFusionDPds = new javax.swing.JLabel();
        jCheckFusionCP = new javax.swing.JCheckBox();
        jFusionCP = new javax.swing.JSpinner();
        jFusionCPds = new javax.swing.JLabel();
        jCheckFusionDL = new javax.swing.JCheckBox();
        jFusionDL = new javax.swing.JSpinner();
        jFusionDLds = new javax.swing.JLabel();
        jCheckFusionCL = new javax.swing.JCheckBox();
        jFusionCL = new javax.swing.JSpinner();
        jFusionCLds = new javax.swing.JLabel();
        jPPC = new javax.swing.JSpinner();
        jPPA = new javax.swing.JSpinner();
        jPPCds = new javax.swing.JLabel();
        jPPAds = new javax.swing.JLabel();
        jCheckPPC = new javax.swing.JCheckBox();
        jCheckPPA = new javax.swing.JCheckBox();
        jUnit1 = new javax.swing.JLabel();
        jUnit2 = new javax.swing.JLabel();
        jUnit3 = new javax.swing.JLabel();
        jUnit4 = new javax.swing.JLabel();
        jUnit5 = new javax.swing.JLabel();
        jUnit6 = new javax.swing.JLabel();
        jUnit7 = new javax.swing.JLabel();
        jUnit8 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jCheckACA = new javax.swing.JCheckBox();
        jACA_0 = new javax.swing.JSpinner();
        jSphere_1 = new javax.swing.JLabel();
        jACA_1 = new javax.swing.JSpinner();
        jSphere_2 = new javax.swing.JLabel();
        jACA_2 = new javax.swing.JSpinner();
        jSphere_3 = new javax.swing.JLabel();
        jACA_3 = new javax.swing.JSpinner();
        jLabel5 = new javax.swing.JLabel();
        jGradient = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jEIP = new javax.swing.JSpinner();
        jLabel7 = new javax.swing.JLabel();
        jUnit9 = new javax.swing.JLabel();
        jACA_PL = new javax.swing.JSpinner();
        jACA_PP = new javax.swing.JSpinner();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jACA_Calcul = new javax.swing.JTextField();
        jACA = new javax.swing.JSpinner();
        jUnit10 = new javax.swing.JLabel();
        jACAds = new javax.swing.JLabel();
        jSheardVL = new javax.swing.JLabel();
        jSheardVP = new javax.swing.JLabel();
        jCheckFDV = new javax.swing.JCheckBox();
        jFDV = new javax.swing.JSpinner();
        jUnit11 = new javax.swing.JLabel();
        jFDVds = new javax.swing.JLabel();
        jCheckFDA = new javax.swing.JCheckBox();
        jFDA = new javax.swing.JSpinner();
        jUnit12 = new javax.swing.JLabel();
        jFDAds = new javax.swing.JLabel();
        jChkSheardVP = new javax.swing.JCheckBox();
        jChkSheardVL = new javax.swing.JCheckBox();

        setMinimumSize(new java.awt.Dimension(700, 550));
        setPreferredSize(new java.awt.Dimension(700, 550));

        jPhorieL.setModel(new javax.swing.SpinnerNumberModel(-1, null, null, 1));
        jPhorieL.setToolTipText("<html>Phorie de loin<br>Exo en négatif, éso en positif</html>");
        jPhorieL.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jPhorieLStateChanged(evt);
            }
        });

        jPhorieP.setModel(new javax.swing.SpinnerNumberModel(-3, null, null, 1));
        jPhorieP.setToolTipText("<html>Phorie de près<br>Exo en négatif, éso en positif</html>");
        jPhorieP.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jPhoriePStateChanged(evt);
            }
        });

        jPhorieLds.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jPhorieLds.setForeground(java.awt.Color.red);
        jPhorieLds.setText("...");

        jPhoriePds.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jPhoriePds.setForeground(java.awt.Color.red);
        jPhoriePds.setText("...");

        jCheckPhorieL.setSelected(true);
        jCheckPhorieL.setText("Phorie L :");
        jCheckPhorieL.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jCheckPhorieLStateChanged(evt);
            }
        });

        jCheckPhorieP.setSelected(true);
        jCheckPhorieP.setText("Phorie P :");
        jCheckPhorieP.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jCheckPhoriePStateChanged(evt);
            }
        });

        jCheckFusionDP.setText("D' :");
        jCheckFusionDP.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jCheckFusionDPStateChanged(evt);
            }
        });

        jFusionDP.setModel(new javax.swing.SpinnerNumberModel(12, null, null, 1));
        jFusionDP.setToolTipText("<html>Fusion en divergence de près<br>(sans signe)</html>");
        jFusionDP.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jFusionDPStateChanged(evt);
            }
        });

        jFusionDPds.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jFusionDPds.setForeground(java.awt.Color.red);
        jFusionDPds.setText("...");

        jCheckFusionCP.setText("C' :");
        jCheckFusionCP.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jCheckFusionCPStateChanged(evt);
            }
        });

        jFusionCP.setModel(new javax.swing.SpinnerNumberModel(23, null, null, 1));
        jFusionCP.setToolTipText("<html>Fusion en convergence de près<br>(sans signe)</html>");
        jFusionCP.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jFusionCPStateChanged(evt);
            }
        });

        jFusionCPds.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jFusionCPds.setForeground(java.awt.Color.red);
        jFusionCPds.setText("...");

        jCheckFusionDL.setSelected(true);
        jCheckFusionDL.setText("D :");
        jCheckFusionDL.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jCheckFusionDLStateChanged(evt);
            }
        });

        jFusionDL.setModel(new javax.swing.SpinnerNumberModel(7, null, null, 1));
        jFusionDL.setToolTipText("<html>Fusion en divergence de loin<br>(sans signe)</html>");
        jFusionDL.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jFusionDLStateChanged(evt);
            }
        });

        jFusionDLds.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jFusionDLds.setForeground(java.awt.Color.red);
        jFusionDLds.setText("...");

        jCheckFusionCL.setSelected(true);
        jCheckFusionCL.setText("C :");
        jCheckFusionCL.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jCheckFusionCLStateChanged(evt);
            }
        });
        jCheckFusionCL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckFusionCLActionPerformed(evt);
            }
        });

        jFusionCL.setModel(new javax.swing.SpinnerNumberModel(11, null, null, 1));
        jFusionCL.setToolTipText("<html>Fusion en convergence de loin<br>(sans signe)</html>");
        jFusionCL.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jFusionCLStateChanged(evt);
            }
        });

        jFusionCLds.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jFusionCLds.setForeground(java.awt.Color.red);
        jFusionCLds.setText("...");

        jPPC.setModel(new javax.swing.SpinnerNumberModel(2.5d, null, null, 0.5d));
        jPPC.setToolTipText("PPC (en cm) sur cible acc.");
        jPPC.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jPPCStateChanged(evt);
            }
        });
        jPPC.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jPPCPropertyChange(evt);
            }
        });

        jPPA.setModel(new javax.swing.SpinnerNumberModel(10.0d, 1.0d, null, 0.5d));
        jPPA.setToolTipText("Amplitude accommodative (en cm)");
        jPPA.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jPPAStateChanged(evt);
            }
        });

        jPPCds.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jPPCds.setForeground(java.awt.Color.red);
        jPPCds.setText("...");

        jPPAds.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jPPAds.setForeground(java.awt.Color.red);
        jPPAds.setText("[age ?]");

        jCheckPPC.setSelected(true);
        jCheckPPC.setText("PPC :");
        jCheckPPC.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jCheckPPCStateChanged(evt);
            }
        });

        jCheckPPA.setSelected(true);
        jCheckPPA.setText("PPA :");
        jCheckPPA.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jCheckPPAStateChanged(evt);
            }
        });

        jUnit1.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jUnit1.setText("d");

        jUnit2.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jUnit2.setText("d");

        jUnit3.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jUnit3.setText("d");

        jUnit4.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jUnit4.setText("d");

        jUnit5.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jUnit5.setText("d");

        jUnit6.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jUnit6.setText("d");

        jUnit7.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jUnit7.setText("d");

        jUnit8.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jUnit8.setText("d");

        jLabel1.setText("Gradient :");

        jCheckACA.setText("AC/A :");
        jCheckACA.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jCheckACAStateChanged(evt);
            }
        });

        jACA_0.setModel(new javax.swing.SpinnerNumberModel(-3, null, null, 1));
        jACA_0.setToolTipText("Exo en négatif, éso en positif");
        jACA_0.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jACA_0StateChanged(evt);
            }
        });
        jACA_0.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jACA_0PropertyChange(evt);
            }
        });

        jSphere_1.setText("(-1)");

        jACA_1.setModel(new javax.swing.SpinnerNumberModel());
        jACA_1.setToolTipText("Exo en négatif, éso en positif");
        jACA_1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jACA_1StateChanged(evt);
            }
        });
        jACA_1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jACA_1PropertyChange(evt);
            }
        });

        jSphere_2.setText("(-2)");

        jACA_2.setModel(new javax.swing.SpinnerNumberModel(6, null, null, 1));
        jACA_2.setToolTipText("Exo en négatif, éso en positif");
        jACA_2.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jACA_2StateChanged(evt);
            }
        });
        jACA_2.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jACA_2PropertyChange(evt);
            }
        });

        jSphere_3.setText("(-3)");

        jACA_3.setModel(new javax.swing.SpinnerNumberModel(9, null, null, 1));
        jACA_3.setToolTipText("Exo en négatif, éso en positif");
        jACA_3.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jACA_3StateChanged(evt);
            }
        });
        jACA_3.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jACA_3PropertyChange(evt);
            }
        });

        jLabel5.setText("->");

        jGradient.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jGradient.setText("...");
        jGradient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jGradientActionPerformed(evt);
            }
        });

        jLabel6.setText("Calculé :");

        jEIP.setModel(new javax.swing.SpinnerNumberModel(5.5d, 0.0d, null, 0.1d));
        jEIP.setToolTipText("Ecart Inter-pupillaire");
        jEIP.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jEIPStateChanged(evt);
            }
        });
        jEIP.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jEIPPropertyChange(evt);
            }
        });

        jLabel7.setText("EIP :");
        jLabel7.setToolTipText("Ecart Inter-pupillaire");

        jUnit9.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jUnit9.setText("cm");

        jACA_PL.setModel(new javax.swing.SpinnerNumberModel(-1, null, null, 1));
        jACA_PL.setToolTipText("<html>Phorie de loin<br>Exo en négatif, éso en positif</html>");
        jACA_PL.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jACA_PLStateChanged(evt);
            }
        });
        jACA_PL.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jACA_PLPropertyChange(evt);
            }
        });

        jACA_PP.setModel(new javax.swing.SpinnerNumberModel(-3, null, null, 1));
        jACA_PP.setToolTipText("<html>Phorie de près<br>Exo en négatif, éso en positif</html>");
        jACA_PP.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jACA_PPStateChanged(evt);
            }
        });
        jACA_PP.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jACA_PPPropertyChange(evt);
            }
        });

        jLabel8.setText("P(L):");

        jLabel9.setText("P(P):");

        jLabel10.setText("->");

        jACA_Calcul.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jACA_Calcul.setText("...");
        jACA_Calcul.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jACA_CalculActionPerformed(evt);
            }
        });

        jACA.setModel(new javax.swing.SpinnerNumberModel(3.0d, 1.0d, null, 0.5d));
        jACA.setToolTipText("Rapport Accommodation-Convergence");
        jACA.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jACAStateChanged(evt);
            }
        });

        jUnit10.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jUnit10.setText("d");

        jACAds.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jACAds.setForeground(java.awt.Color.red);
        jACAds.setText("...");

        jSheardVL.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jSheardVL.setForeground(java.awt.Color.red);
        jSheardVL.setText("...");

        jSheardVP.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jSheardVP.setForeground(java.awt.Color.red);
        jSheardVP.setText("...");

        jCheckFDV.setSelected(true);
        jCheckFDV.setText("Fluidité des vergences :");
        jCheckFDV.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jCheckFDVStateChanged(evt);
            }
        });

        jFDV.setModel(new javax.swing.SpinnerNumberModel(12, 0, null, 1));
        jFDV.setToolTipText("Fluidité des Vergences (cycles par mn).");
        jFDV.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jFDVStateChanged(evt);
            }
        });
        jFDV.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jFDVPropertyChange(evt);
            }
        });

        jUnit11.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jUnit11.setText("cpm");

        jFDVds.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jFDVds.setForeground(java.awt.Color.red);
        jFDVds.setText("...");

        jCheckFDA.setSelected(true);
        jCheckFDA.setText("Fluidité accommodative :");
        jCheckFDA.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jCheckFDAStateChanged(evt);
            }
        });
        jCheckFDA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckFDAActionPerformed(evt);
            }
        });

        jFDA.setModel(new javax.swing.SpinnerNumberModel(10, 0, null, 1));
        jFDA.setToolTipText("<html>Fluidité accommodative binoculaire<br>(cycles par mn)</html>");
        jFDA.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jFDAStateChanged(evt);
            }
        });
        jFDA.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jFDAPropertyChange(evt);
            }
        });

        jUnit12.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jUnit12.setText("cpm");

        jFDAds.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jFDAds.setForeground(java.awt.Color.red);
        jFDAds.setText("...");

        jChkSheardVP.setText("Critère de Sheard (VP) :");
        jChkSheardVP.setEnabled(false);
        jChkSheardVP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jChkSheardVPActionPerformed(evt);
            }
        });

        jChkSheardVL.setSelected(true);
        jChkSheardVL.setText("Critère de Sheard (VL) :");
        jChkSheardVL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jChkSheardVLActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jCheckPhorieL, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(5, 5, 5)
                            .addComponent(jPhorieL, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(10, 10, 10)
                            .addComponent(jUnit1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0)
                            .addComponent(jPhorieLds, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jCheckPPC, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(25, 25, 25)
                            .addComponent(jPPC, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(10, 10, 10)
                            .addComponent(jUnit7, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0)
                            .addComponent(jPPCds, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jCheckPPA, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(25, 25, 25)
                            .addComponent(jPPA, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(10, 10, 10)
                            .addComponent(jUnit8, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jPPAds, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jCheckACA, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(15, 15, 15)
                            .addComponent(jACA, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(10, 10, 10)
                            .addComponent(jUnit10, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(6, 6, 6)
                            .addComponent(jACAds, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(6, 6, 6)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(3, 3, 3)
                            .addComponent(jEIP, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(10, 10, 10)
                            .addComponent(jUnit9, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(19, 19, 19)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(3, 3, 3)
                            .addComponent(jACA_PL, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(10, 10, 10)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(4, 4, 4)
                            .addComponent(jACA_PP, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(10, 10, 10)
                            .addComponent(jLabel10)
                            .addGap(10, 10, 10)
                            .addComponent(jACA_Calcul, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jCheckPhorieP, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(5, 5, 5)
                                .addComponent(jPhorieP, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(jUnit3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jPhoriePds, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 263, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(jACA_0, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(jSphere_1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(8, 8, 8)
                                .addComponent(jACA_1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(jSphere_2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(8, 8, 8)
                                .addComponent(jACA_2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(jSphere_3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(4, 4, 4)
                                .addComponent(jACA_3, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(jLabel5)
                                .addGap(10, 10, 10)
                                .addComponent(jGradient, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jCheckFusionCP, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(35, 35, 35)
                                            .addComponent(jFusionCP, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(10, 10, 10)
                                            .addComponent(jUnit4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(0, 0, 0)
                                            .addComponent(jFusionCPds, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jCheckFusionDP, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(35, 35, 35)
                                            .addComponent(jFusionDP, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(10, 10, 10)
                                            .addComponent(jUnit2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(0, 0, 0)
                                            .addComponent(jFusionDPds, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jChkSheardVP)
                                    .addGap(18, 18, 18))
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jCheckFusionCL, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(35, 35, 35)
                                            .addComponent(jFusionCL, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(10, 10, 10)
                                            .addComponent(jUnit5, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(0, 0, 0)
                                            .addComponent(jFusionCLds, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jCheckFusionDL, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(35, 35, 35)
                                            .addComponent(jFusionDL, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(10, 10, 10)
                                            .addComponent(jUnit6, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(0, 0, 0)
                                            .addComponent(jFusionDLds, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jChkSheardVL)
                                    .addGap(20, 20, 20)))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jSheardVP, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                                .addComponent(jSheardVL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jCheckFDA)
                            .addComponent(jCheckFDV, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jFDV, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(jUnit11, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(jFDVds, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(jFDA, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(jUnit12, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(jFDAds, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(123, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCheckPhorieL)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jPhorieL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jUnit1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jPhorieLds)))
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jCheckPhorieP))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jPhorieP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jUnit3))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jPhoriePds)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCheckFusionDP)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(jFusionDP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addComponent(jUnit2))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jFusionDPds)))
                        .addGap(8, 8, 8)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCheckFusionCP)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(jFusionCP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addComponent(jUnit4))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jFusionCPds))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jChkSheardVP)
                            .addComponent(jSheardVP))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCheckFusionCL)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(jFusionCL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addComponent(jUnit5))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jFusionCLds)))
                        .addGap(8, 8, 8)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCheckFusionDL)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(jFusionDL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addComponent(jUnit6))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jFusionDLds))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jChkSheardVL)
                            .addComponent(jSheardVL))))
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCheckPPC)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jPPC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jUnit7))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jPPCds)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCheckPPA)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(jPPA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jPPAds))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(jUnit8)))
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCheckACA)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jACA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jUnit10))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jACAds)))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jACA_0, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jSphere_1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jACA_1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jSphere_2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jACA_2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jSphere_3))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jACA_3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel5))
                    .addComponent(jGradient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel6))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel7))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jEIP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jUnit9))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel8))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jACA_PL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel9))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jACA_PP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel10))
                    .addComponent(jACA_Calcul, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCheckFDV)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jUnit11)
                            .addComponent(jFDV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jFDVds)))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCheckFDA)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jFDA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jUnit12))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jFDAds))))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jPhorieLStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jPhorieLStateChanged
        int p = (int) jPhorieL.getValue() ;

        double ds = OrthoCotation.baseValues.updatePhorieL ( p, jCheckPhorieL.isSelected() ) ;
        jPhorieLds.setText(String.format("%+.2f", ds) + " DS");
        jFusionDLStateChanged (null) ;
        jFusionCLStateChanged (null) ;
    }//GEN-LAST:event_jPhorieLStateChanged

    private void jPhoriePStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jPhoriePStateChanged
        int p = (int) jPhorieP.getValue() ;
        double ds = OrthoCotation.baseValues.updatePhorieP ( p, jCheckPhorieP.isSelected() ) ;
        jPhoriePds.setText(String.format("%+.2f", ds) + " DS");
        jFusionDPStateChanged (null) ;
        jFusionCPStateChanged (null) ;
    }//GEN-LAST:event_jPhoriePStateChanged

    private void jCheckPhorieLStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jCheckPhorieLStateChanged
        jPhorieLStateChanged (null) ;
    }//GEN-LAST:event_jCheckPhorieLStateChanged

    private void jCheckPhoriePStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jCheckPhoriePStateChanged
        jPhoriePStateChanged (null) ;
    }//GEN-LAST:event_jCheckPhoriePStateChanged

    private void jCheckFusionDPStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jCheckFusionDPStateChanged
        jFusionDPStateChanged (null) ;
    }//GEN-LAST:event_jCheckFusionDPStateChanged

    private void jFusionDPStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jFusionDPStateChanged
        int p = (int) jFusionDP.getValue() ;
        if (OrthoCotation.baseValues.patientAge.years != 0) {
            double ds = OrthoCotation.baseValues.updateFusionDP ( p, jCheckFusionDP.isSelected() ) ;
            updateSheardVP () ;
            //Si éso & ! sheard
            if (OrthoCotation.baseValues.phorieP.raw > 0 & jChkSheardVP.isSelected() & this.sheardCriteriumVP < ds) {
                    OrthoCotation.baseValues.fusionDP.ds = ds = this.sheardCriteriumVP ;
                    OrthoCotation.polarChart.updateDataset();
            }        
            jFusionDPds.setText(String.format("%+.2f", ds) + " DS");
            jFusionDPds.setToolTipText(null);
            //jFusionDPds.setForeground(Color.RED);
        }
        else {
            jFusionDPds.setText("[age ?]") ;
            jFusionDPds.setToolTipText("Précisez la date de naissance...");
            jCheckFusionDP.setSelected(false) ;
            jFusionDPds.setForeground(Color.BLUE);
        }
        
    }//GEN-LAST:event_jFusionDPStateChanged

    private void jCheckFusionCPStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jCheckFusionCPStateChanged
        jFusionCPStateChanged (null) ;
    }//GEN-LAST:event_jCheckFusionCPStateChanged

    private void jFusionCPStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jFusionCPStateChanged
        int p = (int) jFusionCP.getValue() ;
        if (OrthoCotation.baseValues.patientAge.years != 0) {
            double ds = OrthoCotation.baseValues.updateFusionCP ( p, jCheckFusionCP.isSelected() ) ;
            updateSheardVP () ;
            //Si exo & ! sheard
            if (OrthoCotation.baseValues.phorieP.raw < 0 & jChkSheardVP.isSelected() & this.sheardCriteriumVP < ds) {
                    OrthoCotation.baseValues.fusionCP.ds = ds = this.sheardCriteriumVP ;
                    OrthoCotation.polarChart.updateDataset();
            }  
            jFusionCPds.setText(String.format("%+.2f", ds) + " DS");
            jFusionCPds.setToolTipText(null);
            //jFusionCPds.setForeground(Color.RED);
        }
        else {
            jFusionCPds.setText("[age ?]") ;
            jFusionCPds.setToolTipText("Précisez la date de naissance...");
            jCheckFusionCP.setSelected(false) ;
            jFusionCPds.setForeground(Color.BLUE);
        }
        
    }//GEN-LAST:event_jFusionCPStateChanged

    private void jCheckFusionDLStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jCheckFusionDLStateChanged
        jFusionDLStateChanged (null) ;
    }//GEN-LAST:event_jCheckFusionDLStateChanged

    private void jFusionDLStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jFusionDLStateChanged
        int p = (int) jFusionDL.getValue() ;
        double ds = OrthoCotation.baseValues.updateFusionDL ( p, jCheckFusionDL.isSelected() ) ;
        updateSheardVL () ;
        //Si éso & ! sheard
        if (OrthoCotation.baseValues.phorieL.raw > 0 & jChkSheardVL.isSelected() & this.sheardCriteriumVL < ds) {
                OrthoCotation.baseValues.fusionDL.ds = ds = this.sheardCriteriumVL ;
                OrthoCotation.polarChart.updateDataset();
        }  
        jFusionDLds.setText(String.format("%+.2f", ds) + " DS");
        
    }//GEN-LAST:event_jFusionDLStateChanged

    private void jCheckFusionCLStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jCheckFusionCLStateChanged
        jFusionCLStateChanged (null) ;
    }//GEN-LAST:event_jCheckFusionCLStateChanged

    private void jFusionCLStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jFusionCLStateChanged
        int p = (int) jFusionCL.getValue() ;
        double ds = OrthoCotation.baseValues.updateFusionCL ( p, jCheckFusionCL.isSelected() ) ;
        updateSheardVL () ;
        //Si éso & ! sheard
        if (OrthoCotation.baseValues.phorieL.raw < 0 & jChkSheardVL.isSelected() & this.sheardCriteriumVL < ds) {
                OrthoCotation.baseValues.fusionCL.ds = ds = this.sheardCriteriumVL ;
                OrthoCotation.polarChart.updateDataset();
        }  
        jFusionCLds.setText(String.format("%+.2f", ds) + " DS");
        
    }//GEN-LAST:event_jFusionCLStateChanged

    private void jCheckFusionCLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckFusionCLActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckFusionCLActionPerformed

    private void jPPCStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jPPCStateChanged
        double p = (double) jPPC.getValue() ;
        double ds = OrthoCotation.baseValues.updatePPC ( p, jCheckPPC.isSelected() ) ;
        jPPCds.setText(String.format("%+.2f", ds) + " DS");
    }//GEN-LAST:event_jPPCStateChanged

    private void jPPCPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jPPCPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_jPPCPropertyChange

    public void updateFromAge () {
        // D'
        jFusionDPStateChanged (null) ;
        jCheckFusionDP.setSelected(true) ;
        jChkSheardVP.setEnabled(true);
        jChkSheardVP.setSelected(true);
        // C'
        jFusionCPStateChanged (null) ;
        jCheckFusionCP.setSelected(true) ;
        //PPA
        jPPAStateChanged (null) ;
        jCheckPPA.setSelected(true) ;
        //FDA
        jFDAStateChanged (null) ;
        jCheckFDA.setSelected(true) ;
    }
    
    private void jPPAStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jPPAStateChanged
        double p = (double) jPPA.getValue() ;
        p = 100 / p ;
        if (OrthoCotation.baseValues.patientAge.years != 0) {
            double ds = OrthoCotation.baseValues.updatePPA ( p, jCheckPPA.isSelected() ) ;
            jUnit8.setText ("cm (soit " + String.format("%+.2f", p) + " \u03B4)") ;
            jPPAds.setText(String.format("%+.2f", ds) + " DS");
            jPPAds.setToolTipText(null);
            //jPPAds.setForeground(Color.RED);
            jUnit8.setLocation(jUnit7.getX(), jUnit8.getY());
            jUnit8.setSize(150, jUnit8.getHeight());
        }
        else {
            jPPAds.setText("[age ?]") ;
            jPPAds.setForeground(Color.BLUE);
            jPPAds.setToolTipText("Précisez la date de naissance...");
            jCheckPPA.setSelected(false) ;
        }
    }//GEN-LAST:event_jPPAStateChanged

    private void jCheckPPCStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jCheckPPCStateChanged
        jPPCStateChanged (null) ;
    }//GEN-LAST:event_jCheckPPCStateChanged

    private void jCheckPPAStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jCheckPPAStateChanged
        jPPAStateChanged (null) ;
    }//GEN-LAST:event_jCheckPPAStateChanged

    private void jCheckACAStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jCheckACAStateChanged
        if (!gradientACA_isValid & !calculACA_isValid) jCheckACA.setSelected(false);
        else {
            OrthoCotation.baseValues.aca.selected = jCheckACA.isSelected() ;
            OrthoCotation.polarChart.updateDataset () ;
        }
    }//GEN-LAST:event_jCheckACAStateChanged

    private void jACA_0StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jACA_0StateChanged
        gradientACA () ;
    }//GEN-LAST:event_jACA_0StateChanged

    private void jACA_0PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jACA_0PropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_jACA_0PropertyChange

    private void jACA_1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jACA_1StateChanged
        gradientACA () ;
    }//GEN-LAST:event_jACA_1StateChanged

    private void jACA_1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jACA_1PropertyChange
        gradientACA () ;
    }//GEN-LAST:event_jACA_1PropertyChange

    private void jACA_2StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jACA_2StateChanged
        gradientACA () ;
    }//GEN-LAST:event_jACA_2StateChanged

    private void jACA_2PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jACA_2PropertyChange
        gradientACA () ;
    }//GEN-LAST:event_jACA_2PropertyChange

    private void jACA_3StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jACA_3StateChanged
        gradientACA () ;
    }//GEN-LAST:event_jACA_3StateChanged

    private void jACA_3PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jACA_3PropertyChange
        gradientACA () ;
    }//GEN-LAST:event_jACA_3PropertyChange

    private void jGradientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jGradientActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jGradientActionPerformed

    private void jEIPStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jEIPStateChanged
        calculACA () ;
    }//GEN-LAST:event_jEIPStateChanged

    private void jEIPPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jEIPPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_jEIPPropertyChange

    private void jACA_PLStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jACA_PLStateChanged
        calculACA () ;
    }//GEN-LAST:event_jACA_PLStateChanged

    private void jACA_PLPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jACA_PLPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_jACA_PLPropertyChange

    private void jACA_PPStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jACA_PPStateChanged
        calculACA () ;
    }//GEN-LAST:event_jACA_PPStateChanged

    private void jACA_PPPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jACA_PPPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_jACA_PPPropertyChange

    private void jACA_CalculActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jACA_CalculActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jACA_CalculActionPerformed

    private void jACAStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jACAStateChanged
        double a = (double) jACA.getValue() ;
        if (!gradientACA_isValid & !calculACA_isValid) jCheckACA.setSelected(false);
        else {
            double ds = OrthoCotation.baseValues.updateACA(a, jCheckACA.isSelected()) ;
            jACAds.setText(String.format("%+.2f", ds) + " DS");
            OrthoCotation.polarChart.updateDataset () ;
        }
    }//GEN-LAST:event_jACAStateChanged

    private void jCheckFDVStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jCheckFDVStateChanged
        jFDVStateChanged (null) ;
    }//GEN-LAST:event_jCheckFDVStateChanged

    private void jFDVStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jFDVStateChanged
        int p = (int) jFDV.getValue() ;
        double ds = OrthoCotation.baseValues.updateFDV ( p, jCheckFDV.isSelected() ) ;
        jFDVds.setText(String.format("%+.2f", ds) + " DS");
    }//GEN-LAST:event_jFDVStateChanged

    private void jFDVPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jFDVPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_jFDVPropertyChange

    private void jCheckFDAStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jCheckFDAStateChanged
        jFDAStateChanged (null) ;
    }//GEN-LAST:event_jCheckFDAStateChanged

    private void jFDAStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jFDAStateChanged
        int p = (int) jFDA.getValue() ;
        if (OrthoCotation.baseValues.patientAge.years != 0) {
            double ds = OrthoCotation.baseValues.updateFDA ( p, jCheckFDA.isSelected() ) ;
            jFDAds.setText(String.format("%+.2f", ds) + " DS");
            jFDAds.setToolTipText(null);
        }
        else {
            jFDAds.setText("[age ?]") ;
            jFDAds.setForeground(Color.BLUE);
            jFDAds.setToolTipText("Précisez la date de naissance...");
            jCheckFDA.setSelected(false) ;
        }
    }//GEN-LAST:event_jFDAStateChanged

    private void jFDAPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jFDAPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_jFDAPropertyChange

    private void jCheckFDAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckFDAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckFDAActionPerformed

    private void jChkSheardVPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jChkSheardVPActionPerformed
        jFusionDPStateChanged (null) ;
        jFusionCPStateChanged (null) ;
    }//GEN-LAST:event_jChkSheardVPActionPerformed

    private void jChkSheardVLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jChkSheardVLActionPerformed
        jFusionDLStateChanged (null) ;
        jFusionCLStateChanged (null) ;
    }//GEN-LAST:event_jChkSheardVLActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSpinner jACA;
    private javax.swing.JSpinner jACA_0;
    public static javax.swing.JSpinner jACA_1;
    public static javax.swing.JSpinner jACA_2;
    public static javax.swing.JSpinner jACA_3;
    private javax.swing.JTextField jACA_Calcul;
    private javax.swing.JSpinner jACA_PL;
    private javax.swing.JSpinner jACA_PP;
    private javax.swing.JLabel jACAds;
    private javax.swing.JCheckBox jCheckACA;
    private javax.swing.JCheckBox jCheckFDA;
    private javax.swing.JCheckBox jCheckFDV;
    private javax.swing.JCheckBox jCheckFusionCL;
    private javax.swing.JCheckBox jCheckFusionCP;
    private javax.swing.JCheckBox jCheckFusionDL;
    private javax.swing.JCheckBox jCheckFusionDP;
    private javax.swing.JCheckBox jCheckPPA;
    private javax.swing.JCheckBox jCheckPPC;
    private javax.swing.JCheckBox jCheckPhorieL;
    private javax.swing.JCheckBox jCheckPhorieP;
    private javax.swing.JCheckBox jChkSheardVL;
    private javax.swing.JCheckBox jChkSheardVP;
    private javax.swing.JSpinner jEIP;
    private javax.swing.JSpinner jFDA;
    private javax.swing.JLabel jFDAds;
    private javax.swing.JSpinner jFDV;
    private javax.swing.JLabel jFDVds;
    private javax.swing.JSpinner jFusionCL;
    private javax.swing.JLabel jFusionCLds;
    private javax.swing.JSpinner jFusionCP;
    private javax.swing.JLabel jFusionCPds;
    private javax.swing.JSpinner jFusionDL;
    private javax.swing.JLabel jFusionDLds;
    private javax.swing.JSpinner jFusionDP;
    private javax.swing.JLabel jFusionDPds;
    private javax.swing.JTextField jGradient;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JSpinner jPPA;
    private javax.swing.JLabel jPPAds;
    private javax.swing.JSpinner jPPC;
    private javax.swing.JLabel jPPCds;
    private javax.swing.JSpinner jPhorieL;
    private javax.swing.JLabel jPhorieLds;
    private javax.swing.JSpinner jPhorieP;
    private javax.swing.JLabel jPhoriePds;
    private javax.swing.JLabel jSheardVL;
    private javax.swing.JLabel jSheardVP;
    public static javax.swing.JLabel jSphere_1;
    public static javax.swing.JLabel jSphere_2;
    public static javax.swing.JLabel jSphere_3;
    private javax.swing.JLabel jUnit1;
    private javax.swing.JLabel jUnit10;
    private javax.swing.JLabel jUnit11;
    private javax.swing.JLabel jUnit12;
    private javax.swing.JLabel jUnit2;
    private javax.swing.JLabel jUnit3;
    private javax.swing.JLabel jUnit4;
    private javax.swing.JLabel jUnit5;
    private javax.swing.JLabel jUnit6;
    private javax.swing.JLabel jUnit7;
    private javax.swing.JLabel jUnit8;
    private javax.swing.JLabel jUnit9;
    // End of variables declaration//GEN-END:variables
}

class MyMouseListener extends MouseAdapter {
  public void mouseClicked(MouseEvent evt) {
        if (evt.getClickCount() != 2) return ;
        //Source ?
        Object source = evt.getSource () ;
        if (source == Panel_VB.jSphere_1) Panel_VB.jACA_1.setEnabled(!Panel_VB.jACA_1.isEnabled());
        else if (source == Panel_VB.jSphere_2) Panel_VB.jACA_2.setEnabled(!Panel_VB.jACA_2.isEnabled());
        else if (source == Panel_VB.jSphere_3) Panel_VB.jACA_3.setEnabled(!Panel_VB.jACA_3.isEnabled());
        
  }
}