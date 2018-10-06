/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fmaillet.orthocotation;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import org.jfree.chart.ChartUtils;


/**
 *
 * @author Fred
 */
public class OrthoCotation extends JFrame implements ActionListener {
    
    static public boolean master = false ;
    static public UserInfo user ;
    static public MySQLClass mySQLConnection ;
    
    static OrthoCotation fen ;
    static JTabbedPane tabbedPane ;
    static JPanel basePanel ;
    
    public Image iconApp ;
    Image logo ;
    
    //Synthèse des valeurs
    public static BaseDSValues baseValues = new BaseDSValues () ;
    
    //Dates bilan et naissance
    static JDatePickerImpl dateBilan ;
    static JDatePickerImpl dateBirth ;
    static JLabel labelAge ;
    static Panel_VB panelVB ;
    static Panel_Optic panelOPTIC ;
    static Panel_DEM panelDEM ;
    static Panel_TVPS3 panelTVPS3 ;
    static Panel_TVPS4 panelTVPS4 ;
    static Panel_NEURO panelNEURO ;
    
    //Graphique
    public static MyPolarChart polarChart = new MyPolarChart () ;
    public static BarChartTVPS3 barChartTVPS3 = new BarChartTVPS3 () ;
    public static BarChartTVPS4 barChartTVPS4 = new BarChartTVPS4 () ;
    public static JPanel radioPanel, tvps3ChartPanel, tvps4ChartPanel ;
    
    //Genre
    public static JRadioButton h, f ;
    
    
    //Menus
    static JMenuBar barreMenus ;
    static JMenu helpNormsMenu ;
    static JMenuItem exitItem, aboutItem, helpVBItem, helpNormsVBItem, comMenu, helpCriteriumItem ;
    static JMenuItem redactionItem, helpRedactionItem, helpTvps4NormsItem ; 
    
    public OrthoCotation () {
        setTitle ("orthoCotation ("+OrthoCotation.getSoftVersion()+") - MODE DEMO (NON CONNECTE)");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);  setSize(1200, 700);
        
        //Image du trophé
        //iconConnect = getToolkit().getImage(getClass().getResource("connect.png"));
        //Icone de l'appli
        //iconApp = getToolkit().getImage(getClass().getResource("iconorg.png"));
        List<Image> icons = new ArrayList<Image>();
        URL iconURL = getClass().getResource("iconOrg.png_256x256.png");
        icons.add(new ImageIcon(iconURL).getImage());
        iconURL = getClass().getResource("iconOrg.png_64x64.png");
        icons.add(new ImageIcon(iconURL).getImage());
        iconURL = getClass().getResource("iconOrg.png_128x128.png");
        icons.add(new ImageIcon(iconURL).getImage());
        iconURL = getClass().getResource("iconOrg.png_16x16.png");
        icons.add(new ImageIcon(iconURL).getImage());
        iconURL = getClass().getResource("iconOrg.png_48x48.png");
        icons.add(new ImageIcon(iconURL).getImage());
        iconURL = getClass().getResource("iconOrg.png_32x32.png");
        icons.add(new ImageIcon(iconURL).getImage());
        //ImageIcon icon = new ImageIcon(iconURL);
        setIconImages(icons);
        
        iconURL = getClass().getResource("logo-eye1.png");
        logo = new ImageIcon(iconURL).getImage() ;
        System.out.println (logo.getHeight(null)) ;
        //Logo
        BufferedImage buf = toBufferedImage (logo) ;
        //Save logo png
        File myLogo = new File("logo.png");
        try {
            ImageIO.write( buf, "PNG", myLogo);
        } catch (IOException ex) {
            Logger.getLogger(OrthoCotation.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        getContentPane().setBackground(Color.CYAN);
        setLocationRelativeTo(null);
        
        //Menu
        barreMenus = new JMenuBar () ;
        setJMenuBar (barreMenus) ;
        barreMenus.setEnabled(false);
        //Entrée Menus "Fichiers"
        JMenu fileMenu = new JMenu ("Fichier") ;
        barreMenus.add(fileMenu) ;
        comMenu = new JMenuItem ("Connexion au serveur") ;
        fileMenu.add(comMenu) ;
        comMenu.addActionListener(this);
        comMenu.setEnabled(true);
        comMenu.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
              if (user.nom != null)
                  setTitle ("orthoCotation ("+OrthoCotation.getSoftVersion()+") - Connecté au serveur : " + OrthoCotation.user.titre + " " +OrthoCotation.user.nom.toUpperCase() + " " + OrthoCotation.user.prenom ) ;

            }
          });
        //Menu rédaction
        JMenu redactionMenu = new JMenu ("Rédaction") ;
        barreMenus.add(redactionMenu) ;
        redactionItem = new JMenuItem ("Rédaction") ;
        redactionMenu.add(redactionItem) ;
        redactionItem.addActionListener(this);
        //Entrée menu Aide
        JMenu helpMenu = new JMenu ("Aide") ;
        barreMenus.add(helpMenu) ;
        helpVBItem = new JMenuItem ("Vision binoculaire") ;
        helpVBItem.addActionListener(this);
        helpMenu.add(helpVBItem) ;
        
        // submenu normes
        helpNormsMenu = new JMenu ("Normes utilisées") ;
        helpMenu.add(helpNormsMenu) ;
        helpCriteriumItem = new JMenuItem ("Critères VB") ;
        helpCriteriumItem.addActionListener(this);
        helpNormsMenu.add(helpCriteriumItem) ;
        helpNormsVBItem = new JMenuItem ("Vision binoculaire") ;
        helpNormsVBItem.addActionListener(this);
        helpNormsMenu.add(helpNormsVBItem) ;
        helpNormsMenu.addSeparator();
        helpTvps4NormsItem = new JMenuItem ("TVPS-4") ;
        helpTvps4NormsItem.addActionListener(this);
        helpNormsMenu.add(helpTvps4NormsItem) ;
        
        helpRedactionItem = new JMenuItem ("Rédaction") ;
        helpRedactionItem.addActionListener(this);
        
        helpMenu.addSeparator();
        helpMenu.add(helpRedactionItem) ;
        // A propos
        helpMenu.addSeparator();
        aboutItem = new JMenuItem ("A Propos") ;
        aboutItem.addActionListener(this);
        helpMenu.add(aboutItem) ;
        //Entrée menu Exit
        exitItem = new JMenuItem("Quitter");
        exitItem.addActionListener(this);
        //fileMenu.addSeparator();
        fileMenu.add(exitItem);
        
        
        //Barre d'âge
        initDateLabels () ;
    }
    
    public static BufferedImage toBufferedImage(Image img)
{
    if (img instanceof BufferedImage)
    {
        return (BufferedImage) img;
    }

    // Create a buffered image with transparency
    BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

    // Draw the image on to the buffered image
    Graphics2D bGr = bimage.createGraphics();
    bGr.drawImage(img, 0, 0, null);
    bGr.dispose();

    // Return the buffered image
    return bimage;
}
    
    private static void addTabbedPanes (int w, int h) {
        //Tabbed panes
        tabbedPane = new JTabbedPane();
        //tabbedPane.setBounds(0, 0, 700, h-200);

        //Premier tab
        tabbedPane.addTab("Vision binoculaire", addTabPanel_VB () );
        //Optique
        tabbedPane.addTab("Optique", addTabPanel_OPTIC () );
        //Second tab
        tabbedPane.addTab("DEM, NSUCO", addTabPanel_DEM ());
         //third tab
        tabbedPane.addTab("TVPS-3", addTabPanel_TVPS3 ());
        //fourth tab
        tabbedPane.addTab("TVPS-4", addTabPanel_TVPS4 ());
        //fith tab
        if (master) {
            tabbedPane.addTab("Neuro", addTabPanel_NEURO ());
        }   
        
    }
    
    private void initDateLabels () {
        // Date du bilan
        JLabel lab1 = new JLabel ("Date du bilan :") ;
        lab1.setBounds(10, 10, 150, 30);
        getContentPane().add(lab1) ;
        UtilDateModel modelBilan = new UtilDateModel() ;
        Properties p = new Properties();
        p.put("text.today", "Aujourd'hui");
        p.put("text.month", "Mois");
        p.put("text.year", "Année");
        JDatePanelImpl datePanel = new JDatePanelImpl(modelBilan, p);
        dateBilan = new JDatePickerImpl(datePanel, new DateLabelFormatter());
        dateBilan.setBounds(100, 10, 130, 27);
        dateBilan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                calculateAge () ;
            }
            }) ;
        getContentPane().add(dateBilan);
        //Date du jour par défaut
        LocalDate now = LocalDate.now();
        
        modelBilan.setDate(now.getYear(), now.getMonthValue()-1, now.getDayOfMonth());
        modelBilan.setSelected(true);
        
        // Date de naissance
        JLabel lab2 = new JLabel ("Date de naissance :") ;
        lab2.setBounds(250, 10, 150, 30);
        getContentPane().add(lab2) ;
        
        UtilDateModel modelBirth = new UtilDateModel() ;
        JDatePanelImpl dateB = new JDatePanelImpl(modelBirth, p);
        dateBirth = new JDatePickerImpl(dateB, new DateLabelFormatter());
        dateBirth.setBounds(375, 10, 130, 27);
        ((JTextField) dateBirth.getComponent(0)).setToolTipText("Format : jj/mm/aaaa");
        ((JButton) dateBirth.getComponent(1)).setToolTipText("Format : jj/mm/aaaa");
        dateBirth.setTextEditable(true);
        dateBirth.getJFormattedTextField().setFocusLostBehavior(JFormattedTextField.COMMIT_OR_REVERT);
        dateBirth.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                calculateAge () ;
            }
        }) ;
        
        dateBirth.getModel().addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {

                calculateAge() ;
            }
        });
        
        
        getContentPane().add(dateBirth);
        
        labelAge = new JLabel ("[age ?]") ;
        labelAge.setBounds(535, 10, 200, 30);
        labelAge.setFont(new Font(labelAge.getName(), Font.PLAIN, 18));
        labelAge.setForeground(Color.BLUE);
        getContentPane().add(labelAge) ;
        
        //Sexe
        ButtonGroup grp = new ButtonGroup();
        h = new JRadioButton("Garçon"); h.setSelected(false);
        f = new JRadioButton("Fille"); f.setSelected(false);
        grp.add(h); grp.add(f);
        h.setBounds(750, 13, 90, 27); f.setBounds(850, 13, 90, 27);
        h.setOpaque(false); f.setOpaque(false);
        h.setFont(new Font(h.getName(), Font.PLAIN, 18));
        
        f.setFont(new Font(h.getName(), Font.PLAIN, 18));
        getContentPane().add(h) ; getContentPane().add(f) ;
        h.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                OrthoCotation.baseValues.homme = h.isSelected() ;
                panelDEM.updateNSUCO();
            }
        });
        f.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                OrthoCotation.baseValues.homme = h.isSelected() ;
                panelDEM.updateNSUCO();
            }
        });
    }
    
    public static boolean isGenreDefined () {
        return h.isSelected() | f.isSelected() ;
    }
    
    private static JPanel addTabPanel_VB () {
        basePanel = new JPanel () ;
        basePanel.setLayout(null);
        
        //Panel Base Datas
        panelVB = new Panel_VB () ;
        panelVB.setSize(600, 460);
        panelVB.setVisible(true);
        //basePanel.add(baseDatas) ;
        //basePanel.revalidate() ;
        //basePanel.repaint() ;
        
        radioPanel = polarChart.addPolarPanel();
        radioPanel.setBounds(750, 20, 420, 420);
        radioPanel.setVisible(true);
        panelVB.add (radioPanel) ;
        
        //Label "clic droit"
        JLabel infoClic = new JLabel ("(Clic droit sur le schéma pour enregistrer ou copier)") ;
        infoClic.setFont(new Font(infoClic.getName(), Font.ITALIC, 11));
        infoClic.setBounds(radioPanel.getX(), radioPanel.getY()+radioPanel.getHeight()+10, 270, 25);
        panelVB.add (infoClic) ;
        //Transparency
        JSlider jTransparency = new JSlider ();
        jTransparency.setMaximum(100); jTransparency.setMinimum(0); jTransparency.setValue(25);
        jTransparency.setBounds(infoClic.getX()+120, infoClic.getY() + 40, 200, 17);
        ChangeListener l = new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                polarChart.changeTransparency( (int) jTransparency.getValue());
            }
            
        } ;
        
        jTransparency.addChangeListener(l);
        panelVB.add (jTransparency) ;
        JLabel infoTransparency = new JLabel("Transparence :") ;
        infoTransparency.setBounds(infoClic.getX(), jTransparency.getY()-4, 90, 25);
        panelVB.add (infoTransparency) ;
        
        return panelVB ;

            
    }
    
    private static JPanel addTabPanel_DEM () {
        //Panel Base Datas
        panelDEM = new Panel_DEM () ;
        panelDEM.setSize(700, 460);
        panelDEM.setVisible(true);

        return panelDEM ;
    }
    
    private static JPanel addTabPanel_NEURO () {
        //Panel Base Datas
        panelNEURO = new Panel_NEURO () ;
        panelNEURO.setSize(700, 460);
        panelNEURO.setVisible(true);

        return panelNEURO ;
    }
    
    private static JPanel addTabPanel_OPTIC () {
        
        
        //Panel Base Datas
        panelOPTIC = new Panel_Optic () ;
        panelOPTIC.setSize(700, 460);
        panelOPTIC.setVisible(true);

        return panelOPTIC ;
    }
    
    private static JPanel addTabPanel_TVPS3 () {
        
        
        //Panel Base Datas
        panelTVPS3 = new Panel_TVPS3 () ;
        panelTVPS3.setSize(700, 460);
        panelTVPS3.setVisible(true);
        //basePanel.add(baseDatas) ;
        //basePanel.revalidate() ;
        //basePanel.repaint() ;
        
        tvps3ChartPanel = barChartTVPS3.addBarPanel();
        tvps3ChartPanel.setBounds(660, 20, 500, 300);
        tvps3ChartPanel.setVisible(true);
        panelTVPS3.add (tvps3ChartPanel) ;
        
        //Label "clic droit"
        JLabel infoClic = new JLabel ("(Clic droit sur le schéma pour enregistrer ou copier)") ;
        infoClic.setFont(new Font(infoClic.getName(), Font.ITALIC, 11));
        infoClic.setBounds(tvps3ChartPanel.getX(), tvps3ChartPanel.getY()+tvps3ChartPanel.getHeight()+10, 270, 25);
        panelTVPS3.add (infoClic) ;
        
        //On déplace certains items
        panelTVPS3.jChgeColor.setLocation(infoClic.getX()+70, infoClic.getY()+infoClic.getHeight()+60);
        panelTVPS3.jCheckTitre.setLocation(infoClic.getX()+30+panelTVPS3.jChgeColor.getWidth()+15, infoClic.getY()+infoClic.getHeight()+15);
        panelTVPS3.jCheckRange.setLocation(infoClic.getX()+30+panelTVPS3.jChgeColor.getWidth()+panelTVPS3.jCheckTitre.getWidth()+15, infoClic.getY()+infoClic.getHeight()+15);
        
        return panelTVPS3 ;
    }
    
    private static JPanel addTabPanel_TVPS4 () {
        
        
        //Panel Base Datas
        panelTVPS4 = new Panel_TVPS4 () ;
        panelTVPS4.setSize(700, 460);
        panelTVPS4.setVisible(true);
        //basePanel.add(baseDatas) ;
        //basePanel.revalidate() ;
        //basePanel.repaint() ;
        
        tvps4ChartPanel = barChartTVPS4.addBarPanel();
        tvps4ChartPanel.setBounds(660, 20, 500, 300);
        tvps4ChartPanel.setVisible(true);
        panelTVPS4.add (tvps4ChartPanel) ;
        
        //Label "clic droit"
        JLabel infoClic = new JLabel ("(Clic droit sur le schéma pour enregistrer ou copier)") ;
        infoClic.setFont(new Font(infoClic.getName(), Font.ITALIC, 11));
        infoClic.setBounds(tvps4ChartPanel.getX(), tvps4ChartPanel.getY()+tvps4ChartPanel.getHeight()+10, 270, 25);
        panelTVPS4.add (infoClic) ;
        
        //On déplace certains items
        panelTVPS4.jChgeColor.setLocation(infoClic.getX()+70, infoClic.getY()+infoClic.getHeight()+60);
        panelTVPS4.jCheckTitre.setLocation(infoClic.getX()+30+panelTVPS3.jChgeColor.getWidth()+15, infoClic.getY()+infoClic.getHeight()+15);
        panelTVPS4.jCheckRange.setLocation(infoClic.getX()+30+panelTVPS3.jChgeColor.getWidth()+panelTVPS3.jCheckTitre.getWidth()+15, infoClic.getY()+infoClic.getHeight()+15);
        
        return panelTVPS4 ;
    }
    
    private static void calculateAge () {
        Date b = (Date) dateBilan.getModel().getValue() ;
        if (b == null) return ;
        LocalDate bilan = b.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        
        Date n = (Date) dateBirth.getModel().getValue() ;
        if (n == null) return ;
        LocalDate birth = n.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        
        Period age = Period.between(birth, bilan);
        
        labelAge.setText("<html><b>" + age.getYears() + "</b> ans <b>" + age.getMonths() + "</b> mois (" + age.getDays() + " j.)<html>");
        //Update Base
        baseValues.patientAge.years = age.getYears() ;
        baseValues.patientAge.months = age.getMonths() ;
        baseValues.patientAge.days = age.getDays() ;
        //Update values
        panelVB.updateFromAge () ;
        panelDEM.updateResults();
        panelDEM.updateNSUCO();
        panelDEM.updateGROFFMAN () ;
        panelTVPS3.updateResults();
        panelTVPS4.updateResults();
        if (master) {
            panelNEURO.updateThurstoneFI() ;
            panelNEURO.updateBentonJLO () ;
        }
    }
    
    public static void connected () {
        //System.out.println ("From main : Connected " + OrthoCotation.user.nom) ;
        if (OrthoCotation.user.nom != null) {
            barreMenus.setEnabled(true);
            comMenu.setEnabled(false) ;
            // msg panel DEM
            Panel_DEM.jMsgDEM.setText("(Age ? Vous devez indiquer une date de naissance)");
            Panel_DEM.jMsgDEM.setForeground(Color.BLUE);
            Panel_TVPS3.jMsgTVPS.setText("(Age ? Vous devez indiquer une date de naissance)");
            Panel_TVPS3.jMsgTVPS.setForeground(Color.BLUE);
            Panel_DEM.jMsgNSUCO.setText("(Age ? Vous devez indiquer une date de naissance)");
            Panel_DEM.jMsgNSUCO.setForeground(Color.BLUE);
            Panel_DEM.jMsgGROFFMAN.setText("(Age ? Vous devez indiquer une date de naissance)");
            Panel_DEM.jMsgGROFFMAN.setForeground(Color.BLUE);
            if (master) {
                Panel_NEURO.jMsgThurstone.setText("(Age ? Vous devez indiquer une date de naissance)");
                Panel_NEURO.jMsgThurstone.setForeground(Color.BLUE);
            }
        }
        else comMenu.setEnabled(true) ;
    }
    
    public static void main(String[] args) {
        
        //For connection
        user = new UserInfo () ;
        mySQLConnection = new MySQLClass () ;
        if (args.length > 0) {
            master = true ;
            user.nom = "Maillet" ;
            user.prenom = "Frédéric" ;
            user.activite = "Orthoptiste Neuropsychologue" ;
            user.adr1 = "209 avenue de castres" ;
            user.adr2 = "RDC BAT G" ;
        }
        
        // fenetre principale
        fen = new OrthoCotation () ;
        
        addTabbedPanes (fen.getContentPane().getWidth(), fen.getContentPane().getHeight()) ;
        fen.getContentPane().add (tabbedPane) ;
        fen.setVisible(true) ;
        tabbedPane.setBounds (0, 50, fen.getContentPane().getWidth()+10, fen.getContentPane().getHeight()-40);
        fen.setResizable(false);
        
        
        
        /*radioPanel = polarChart.addPolarPanel();
        radioPanel.setBounds(750, 20, 420, 420);
        radioPanel.setVisible(true);
        fen.getContentPane().add (radioPanel) ;*/
        //fen.getContentPane().revalidate();
        fen.repaint();
        
        //Label "clic droit"
        JLabel infoClic = new JLabel ("(Clic droit sur le schéma pour sauver ou copier)") ;
        infoClic.setFont(new Font(infoClic.getName(), Font.ITALIC, 11));
        infoClic.setBounds(radioPanel.getX(), radioPanel.getY()+radioPanel.getHeight()+10, 250, 25);
        fen.getContentPane().add (infoClic) ;
        
        //AutoConnect
        if (master) connected () ;
        else {
            AutoConnect auto = new AutoConnect () ;
            auto.start () ;
        }
    }
    
    public static String getSoftVersion () {
        return "v1.7.5beta du 16/07/2018" ;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Source ?
        Object source = e.getSource () ;
        //A Propos
        if (source == aboutItem) {
            AboutJDialog about = new AboutJDialog(this, true);
            about.pack();
            about.getContentPane().setBackground(Color.CYAN);
            about.setLocationRelativeTo(null) ;
            about.setVisible(true);
        }
        else if (source == helpVBItem) {
            VB_JDialog about = new VB_JDialog(this, true);
            about.pack();
            about.setLocationRelativeTo(null) ;
            about.setVisible(true);
        }
        else if (source == helpNormsVBItem) {
            Norms_JDialog about = new Norms_JDialog(this, true);
            about.pack();
            about.setLocationRelativeTo(null) ;
            about.setVisible(true);
        }
        else if (source == helpCriteriumItem) {
            Criterium_JDialog about = new Criterium_JDialog(this, true);
            about.pack();
            about.setLocationRelativeTo(null) ;
            about.setVisible(true);
        }
        else if (source == helpTvps4NormsItem) {
            TVPS4Norms_JDialog about = new TVPS4Norms_JDialog(this, true);
            about.pack();
            about.setLocationRelativeTo(null) ;
            about.setVisible(true);
        }
        else if (source == comMenu) {
            ConnectDialog connect = new ConnectDialog (this, true) ;
            connect.setLocationRelativeTo(null);
            connect.setVisible (true) ;
            //comMenu.setEnabled(OrthoCotation.user.nom != null);
        }
        else if (source == helpRedactionItem) {
            JOptionPane.showMessageDialog(this,
                "Expérimental !!\n"
                        + "Compilateur de compte-rendu...\n\n"
                        + " Vous devez installer MikTex: https://miktex.org/",
                "Aide à la rédaction",
                JOptionPane.WARNING_MESSAGE);
        }
        else if (source == redactionItem) {
            try {

                OutputStream out = new FileOutputStream("polarChart.png");
                /*polarChart.chart.setTitle("");
                polarChart.subTitles.clear();
                polarChart.chart.setSubtitles(polarChart.subTitles);*/
                ChartUtils.writeChartAsPNG(out,
                        polarChart.chart,
                        radioPanel.getWidth(),
                        radioPanel.getHeight(), null, true, 0);
                //TVPS-3
                out = new FileOutputStream("tvps3.png");
                ChartUtils.writeChartAsPNG(out,
                        barChartTVPS3.chart,
                        tvps3ChartPanel.getWidth(),
                        tvps3ChartPanel.getHeight(), null, true, 0);
                //TVPS-4
                out = new FileOutputStream("tvps4.png");
                ChartUtils.writeChartAsPNG(out,
                        barChartTVPS4.chart,
                        tvps4ChartPanel.getWidth(),
                        tvps4ChartPanel.getHeight(), null, true, 0);
            } catch (IOException ex) { System.out.println ("Erreur polar Chart cration !") ;}
            RedactionFrame redac = new RedactionFrame () ;
            redac.setVisible(true);
            redac.addComponents();
            
            
        }
        else if (source == exitItem) {
            System.exit(0);
        }
    }
}

class DateLabelFormatter extends JFormattedTextField.AbstractFormatter {

        private String datePattern = "dd/MM/yyyy";
        private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);


        @Override
        public Object stringToValue(String text) throws ParseException {
            Calendar cal = Calendar.getInstance();
            cal.setTime((Date) dateFormatter.parseObject(text));
            return cal;
        }

        @Override
        public String valueToString(Object value) throws ParseException {
            if (value != null) {
                Calendar cal = (Calendar) value;
                return dateFormatter.format(cal.getTime());
            }
            return "";
        }
}