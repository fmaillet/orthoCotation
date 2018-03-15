package com.fmaillet.orthocotation;

import org.apache.commons.math3.distribution.NormalDistribution;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Fred
 */
public class Panel_TVPS extends javax.swing.JPanel {

    public static int tvpsStdValues[] = new int[7] ;
    
    int tvps_DIS[][] = { {7, 8, 9, 10, 11, 12, 13, 14, 16, 17, 18, 19, 19, 19, 19, 19}, //4.0
                         {5, 7, 8, 9, 10, 11, 13, 14, 16, 17, 18, 19, 19, 19, 19, 19},  //4.6
                         {4, 6, 7, 8, 10, 11, 12, 13, 15, 16, 18, 19, 19, 19, 19, 19},  //5.0
                         {3, 5, 7, 8, 9, 10, 11, 12, 14, 15, 17, 19, 19, 19, 19, 19},   //5.6
                         {3, 4, 6, 7, 8, 10, 11, 12, 13, 14, 16, 18, 19, 19, 19, 19},   //6.0
                         {2, 4, 5, 7, 8, 9, 10, 11, 12, 14, 15, 17, 19, 19, 19, 19},    //6.6
                         {2, 3, 5, 6, 7, 8, 9, 10, 12, 13, 14, 16, 18, 19, 19, 19},     //7.0
                         {1, 3, 4, 6, 7, 8, 9, 10, 11, 12, 14, 15, 17, 18, 19, 19},     //7.5
                         {1, 2, 4, 5, 6, 7, 8, 9, 10, 12, 13, 15, 16, 18, 19, 19},      //8.0
                         {1, 2, 3, 5, 6, 7, 8, 9, 10, 11, 12, 14, 15, 18, 19, 19},      //8.6
                         {0, 1, 3, 4, 5, 6, 7, 8, 9, 11, 12, 13, 15, 17, 19, 19},       //9.0
                         {0, 1, 2, 4, 5, 6, 7, 8, 9, 10, 11, 13, 14, 16, 18, 19},        //9.5
                         {0, 0, 1, 3, 4, 5, 6, 7, 8, 9, 10, 12, 13, 15, 18, 19},        //10.0
                         {0, 0, 1, 2, 3, 4, 6, 6, 7, 8, 9, 11, 12, 14, 16, 18},         //11.0
                         {0, 0, 0, 1, 3, 4, 5, 6, 7, 8, 9, 10, 12, 13, 15, 18},         //12.0
                         {0, 0, 0, 1, 2, 3, 5, 5, 6, 7, 8, 10, 11, 12, 14, 17},         //13.0
                         {0, 0, 0, 0, 1, 3, 4, 5, 6, 7, 8, 9, 10, 12, 14, 17},          //14.0
                         {0, 0, 0, 0, 1, 2, 3, 4, 5, 6, 7, 9, 10, 11, 13, 16},          //15.0
                         {0, 0, 0, 0, 0, 1, 3, 4, 5, 6, 7, 8, 10, 11, 13, 16},          //16.0
                         {0, 0, 0, 0, 0, 1, 2, 3, 4, 5, 6, 8, 9, 10, 12, 15},           //17.0
                         {0, 0, 0, 0, 0, 1, 2, 3, 4, 5, 6, 7, 8, 10, 12, 14}            //18.0
                        };
    int tvps_MEM[][] = { {6, 7, 9, 10, 11, 12, 13, 15, 16, 17, 18, 19, 19, 19, 19, 19}, //4.0
                         {5, 6, 8, 10, 11, 12, 13, 15, 16, 17, 18, 19, 19, 19, 19, 19}, //4.6
                         {3, 5, 7, 8, 10, 11, 12, 13, 15, 16, 17, 18, 19, 19, 19, 19},  //5.0
                         {2, 4, 6, 7, 8, 10, 11, 12, 13, 15, 16, 18, 19, 19, 19, 19},   //5.6
                         {2, 3, 5, 6, 7, 9, 10, 11, 12, 14, 15, 17, 18, 19, 19, 19},    //6.0
                         {1, 2, 4, 5, 6, 8, 9, 10, 11, 13, 14, 16, 18, 19, 19, 19}      //6.6
                        };
    
    /**
     * Creates new form Panel_TVPS
     */
    public Panel_TVPS() {
        initComponents();
        updateResults () ;
    }
    
    public static double zScoreToPercentile(double zScore) {
		double percentile = 0;
		
		NormalDistribution dist = new NormalDistribution();
		percentile = dist.cumulativeProbability(zScore) * 100;
		return percentile;
    }
    
    private int indexAge (){
        int idx ;
        if (OrthoCotation.baseValues.patientAge.years < 4) return 0 ;
        else if (OrthoCotation.baseValues.patientAge.years < 10) {
            idx = (OrthoCotation.baseValues.patientAge.years - 4) * 2 ;
            if (OrthoCotation.baseValues.patientAge.months >5) idx++ ;
        }
        else {
            idx = OrthoCotation.baseValues.patientAge.years + 2 ;
        }
        
        if (idx >= tvps_DIS.length) idx = tvps_DIS.length-1;
        return idx ;
    }
    
    public void updateResults () {
        tvpsStdValues[0] = (int) jDIS.getValue() ;
        tvpsStdValues[1] = (int) jMEM.getValue() ;
        tvpsStdValues[2] = (int) jSPA.getValue() ;
        tvpsStdValues[3] = (int) jCON.getValue() ;
        tvpsStdValues[4] = (int) jSEQ.getValue() ;
        tvpsStdValues[5] = (int) jFGR.getValue() ;
        tvpsStdValues[6] = (int) jCLO.getValue() ;
        
        if (OrthoCotation.baseValues.patientAge.years == 0) return ;
        
        //DIS
        tvpsStdValues[0] = tvps_DIS[indexAge()][tvpsStdValues[0]-1] ;
        jStd_DIS.setText(String.valueOf(tvpsStdValues[0]));
        double t = ( (double) tvpsStdValues[0] - 10.0 ) / 3.0 ;
        jPctl_DIS.setText(String.valueOf (Math.round (zScoreToPercentile(t)))) ;
        //MEM
        tvpsStdValues[1] = tvps_MEM[indexAge()][tvpsStdValues[1]-1] ;
        jStd_MEM.setText(String.valueOf(tvpsStdValues[1]));
        t = ( (double) tvpsStdValues[1] - 10.0 ) / 3.0 ;
        jPctl_MEM.setText(String.valueOf (Math.round (zScoreToPercentile(t)))) ;
        
        OrthoCotation.barChart.updateGraph () ;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel15 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jMsgDEM = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jDIS = new javax.swing.JSpinner();
        jMEM = new javax.swing.JSpinner();
        jSPA = new javax.swing.JSpinner();
        jCON = new javax.swing.JSpinner();
        jSEQ = new javax.swing.JSpinner();
        jFGR = new javax.swing.JSpinner();
        jCLO = new javax.swing.JSpinner();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jStd_DIS = new javax.swing.JTextField();
        jStd_MEM = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jPctl_DIS = new javax.swing.JTextField();
        jPctl_MEM = new javax.swing.JTextField();
        jStd_DIS3 = new javax.swing.JTextField();
        jStd_DIS4 = new javax.swing.JTextField();
        jStd_DIS5 = new javax.swing.JTextField();
        jStd_DIS6 = new javax.swing.JTextField();
        jStd_DIS7 = new javax.swing.JTextField();

        setPreferredSize(new java.awt.Dimension(700, 500));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setForeground(java.awt.Color.gray);
        jLabel15.setText("TVPS-3");

        jMsgDEM.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jMsgDEM.setForeground(java.awt.Color.red);
        jMsgDEM.setText("(En cours de développement...)");

        jLabel1.setText("DIS :");

        jLabel2.setText("MEM :");

        jLabel3.setText("SPA :");

        jLabel4.setText("CON :");

        jLabel5.setText("SEQ :");

        jLabel6.setText("FGR :");

        jLabel7.setText("CLO :");

        jDIS.setModel(new javax.swing.SpinnerNumberModel(11, 1, 16, 1));
        jDIS.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jDISStateChanged(evt);
            }
        });

        jMEM.setModel(new javax.swing.SpinnerNumberModel(11, 1, 16, 1));
        jMEM.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jMEMStateChanged(evt);
            }
        });

        jSPA.setModel(new javax.swing.SpinnerNumberModel(11, 1, 16, 1));
        jSPA.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSPAStateChanged(evt);
            }
        });

        jCON.setModel(new javax.swing.SpinnerNumberModel(11, 1, 16, 1));
        jCON.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jCONStateChanged(evt);
            }
        });

        jSEQ.setModel(new javax.swing.SpinnerNumberModel(11, 1, 16, 1));
        jSEQ.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSEQStateChanged(evt);
            }
        });

        jFGR.setModel(new javax.swing.SpinnerNumberModel(11, 1, 16, 1));
        jFGR.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jFGRStateChanged(evt);
            }
        });

        jCLO.setModel(new javax.swing.SpinnerNumberModel(11, 1, 16, 1));
        jCLO.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jCLOStateChanged(evt);
            }
        });

        jLabel8.setText("Raw");

        jLabel9.setText("Scaled");

        jStd_DIS.setEditable(false);
        jStd_DIS.setBackground(new java.awt.Color(255, 255, 255));
        jStd_DIS.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jStd_DIS.setText("na");

        jStd_MEM.setEditable(false);
        jStd_MEM.setBackground(new java.awt.Color(255, 255, 255));
        jStd_MEM.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jStd_MEM.setText("na");
        jStd_MEM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jStd_MEMActionPerformed(evt);
            }
        });

        jTextField3.setEditable(false);
        jTextField3.setBackground(new java.awt.Color(255, 255, 255));
        jTextField3.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField3.setText("na");
        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });

        jTextField4.setEditable(false);
        jTextField4.setBackground(new java.awt.Color(255, 255, 255));
        jTextField4.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField4.setText("na");
        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });

        jTextField5.setEditable(false);
        jTextField5.setBackground(new java.awt.Color(255, 255, 255));
        jTextField5.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField5.setText("na");
        jTextField5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField5ActionPerformed(evt);
            }
        });

        jTextField6.setEditable(false);
        jTextField6.setBackground(new java.awt.Color(255, 255, 255));
        jTextField6.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField6.setText("na");
        jTextField6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField6ActionPerformed(evt);
            }
        });

        jTextField7.setEditable(false);
        jTextField7.setBackground(new java.awt.Color(255, 255, 255));
        jTextField7.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField7.setText("na");
        jTextField7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField7ActionPerformed(evt);
            }
        });

        jLabel10.setText("Percentile");

        jPctl_DIS.setEditable(false);
        jPctl_DIS.setBackground(new java.awt.Color(255, 255, 255));
        jPctl_DIS.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jPctl_DIS.setText("na");

        jPctl_MEM.setEditable(false);
        jPctl_MEM.setBackground(new java.awt.Color(255, 255, 255));
        jPctl_MEM.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jPctl_MEM.setText("na");

        jStd_DIS3.setEditable(false);
        jStd_DIS3.setBackground(new java.awt.Color(255, 255, 255));
        jStd_DIS3.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jStd_DIS3.setText("na");

        jStd_DIS4.setEditable(false);
        jStd_DIS4.setBackground(new java.awt.Color(255, 255, 255));
        jStd_DIS4.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jStd_DIS4.setText("na");

        jStd_DIS5.setEditable(false);
        jStd_DIS5.setBackground(new java.awt.Color(255, 255, 255));
        jStd_DIS5.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jStd_DIS5.setText("na");

        jStd_DIS6.setEditable(false);
        jStd_DIS6.setBackground(new java.awt.Color(255, 255, 255));
        jStd_DIS6.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jStd_DIS6.setText("na");

        jStd_DIS7.setEditable(false);
        jStd_DIS7.setBackground(new java.awt.Color(255, 255, 255));
        jStd_DIS7.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jStd_DIS7.setText("na");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 518, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addGap(61, 61, 61)
                        .addComponent(jMsgDEM))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel2)
                                        .addComponent(jLabel3))
                                    .addGap(18, 18, 18)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jSPA, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jMEM, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel1)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jDIS, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(13, 13, 13)
                                            .addComponent(jLabel8)))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jCLO, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jFGR, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jCON, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jSEQ, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(42, 42, 42)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jTextField7, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                                .addComponent(jTextField6)
                                .addComponent(jTextField5)
                                .addComponent(jTextField4)
                                .addComponent(jTextField3)
                                .addComponent(jStd_MEM)
                                .addComponent(jStd_DIS)))
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jPctl_DIS, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPctl_MEM, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jStd_DIS3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jStd_DIS4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jStd_DIS5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jStd_DIS6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jStd_DIS7, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(172, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jMsgDEM)
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10))
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jDIS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jStd_DIS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPctl_DIS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jMEM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jStd_MEM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPctl_MEM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jSPA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jStd_DIS3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jCON, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jStd_DIS4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jSEQ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jStd_DIS5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jFGR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jStd_DIS6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jCLO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jStd_DIS7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(181, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jDISStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jDISStateChanged
        updateResults () ;
    }//GEN-LAST:event_jDISStateChanged

    private void jMEMStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jMEMStateChanged
        updateResults () ;
    }//GEN-LAST:event_jMEMStateChanged

    private void jSPAStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSPAStateChanged
        updateResults () ;
    }//GEN-LAST:event_jSPAStateChanged

    private void jCONStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jCONStateChanged
        updateResults () ;
    }//GEN-LAST:event_jCONStateChanged

    private void jSEQStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSEQStateChanged
        updateResults () ;
    }//GEN-LAST:event_jSEQStateChanged

    private void jFGRStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jFGRStateChanged
        updateResults () ;
    }//GEN-LAST:event_jFGRStateChanged

    private void jCLOStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jCLOStateChanged
        updateResults () ;
    }//GEN-LAST:event_jCLOStateChanged

    private void jStd_MEMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jStd_MEMActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jStd_MEMActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4ActionPerformed

    private void jTextField5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField5ActionPerformed

    private void jTextField6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField6ActionPerformed

    private void jTextField7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField7ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSpinner jCLO;
    private javax.swing.JSpinner jCON;
    private javax.swing.JSpinner jDIS;
    private javax.swing.JSpinner jFGR;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JSpinner jMEM;
    public static javax.swing.JLabel jMsgDEM;
    private javax.swing.JTextField jPctl_DIS;
    private javax.swing.JTextField jPctl_MEM;
    private javax.swing.JSpinner jSEQ;
    private javax.swing.JSpinner jSPA;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField jStd_DIS;
    private javax.swing.JTextField jStd_DIS3;
    private javax.swing.JTextField jStd_DIS4;
    private javax.swing.JTextField jStd_DIS5;
    private javax.swing.JTextField jStd_DIS6;
    private javax.swing.JTextField jStd_DIS7;
    private javax.swing.JTextField jStd_MEM;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    // End of variables declaration//GEN-END:variables
}
