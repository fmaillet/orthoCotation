/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fmaillet.orthocotation;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
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
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;


/**
 *
 * @author Fred
 */
public class OrthoCotation extends JFrame implements ActionListener {
    
    static public UserInfo user ;
    static public MySQLClass mySQLConnection ;
    
    static OrthoCotation fen ;
    static JTabbedPane tabbedPane ;
    static JPanel basePanel ;
    
    public Image iconConnect, iconApp ;
    
    //Synthèse des valeurs
    public static BaseDSValues baseValues = new BaseDSValues () ;
    
    //Dates bilan et naissance
    static JDatePickerImpl dateBilan ;
    static JDatePickerImpl dateBirth ;
    static JLabel labelAge ;
    static Panel_VB panelVB ;
    static Panel_DEM panelDEM ;
    
    //Graphique
    public static MyPolarChart polarChart = new MyPolarChart () ;
    public static JPanel radioPanel ;
    
    //Menus
    static JMenuBar barreMenus ;
    static JMenuItem exitItem, aboutItem, helpVBItem, helpNormsItem, comMenu ;
    
    public OrthoCotation () {
        setTitle ("orthoCotation ("+OrthoCotation.getSoftVersion()+") - MODE DEMO (NON CONNECTE)");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);  setSize(1200, 700);
        
        //Image du trophé
        iconConnect = getToolkit().getImage(getClass().getResource("connect.png"));
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
        //Entrée menu Aide
        JMenu helpMenu = new JMenu ("Aide") ;
        barreMenus.add(helpMenu) ;
        helpVBItem = new JMenuItem ("Vision binoculaire") ;
        helpVBItem.addActionListener(this);
        helpMenu.add(helpVBItem) ;
        helpNormsItem = new JMenuItem ("Normes utilisées") ;
        helpNormsItem.addActionListener(this);
        helpMenu.add(helpNormsItem) ;
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
    
    private static void addTabbedPanes (int w, int h) {
        //Tabbed panes
        tabbedPane = new JTabbedPane();
        //tabbedPane.setBounds(0, 0, 700, h-200);

        //Premier tab
        tabbedPane.addTab("Vision binoculaire", addTabPanel_VB () );
        
        //Second tab
        tabbedPane.addTab("DEM, NSUCO", addTabPanel_DEM ());
         //third tab
        JPanel panel3 = new JPanel(false);
        tabbedPane.addTab("TVPS-3", panel3);
        JPanel panel4 = new JPanel(false);
        tabbedPane.addTab("TVPS-4", panel4);
        
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
        
        modelBilan.setDate(now.getYear(), now.getMonthValue(), now.getDayOfMonth());
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
        
        return panelVB ;
    }
    
    private static JPanel addTabPanel_DEM () {
        
        
        //Panel Base Datas
        panelDEM = new Panel_DEM () ;
        panelDEM.setSize(700, 460);
        panelDEM.setVisible(true);
        //basePanel.add(baseDatas) ;
        //basePanel.revalidate() ;
        //basePanel.repaint() ;
        
        return panelDEM ;
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
    }
    
    public static void connected () {
        //System.out.println ("From main : Connected " + OrthoCotation.user.nom) ;
        if (OrthoCotation.user.nom != null) {
            comMenu.setEnabled(false) ;
            barreMenus.setEnabled(true);
            // msg panel DEM
            Panel_DEM.jMsgDemo.setVisible(false);
        }
        else comMenu.setEnabled(true) ;
    }
    
    public static void main(String[] args) {
        
        //For connection
        user = new UserInfo () ;
        mySQLConnection = new MySQLClass () ;
        
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
        AutoConnect auto = new AutoConnect () ;
        auto.start () ;
    }
    
    public static String getSoftVersion () {
        return "v1.1.0 du 09/03/2018" ;
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
        else if (source == helpNormsItem) {
            Norms_JDialog about = new Norms_JDialog(this, true);
            about.pack();
            about.setLocationRelativeTo(null) ;
            about.setVisible(true);
        }
        else if (source == comMenu) {
            ConnectDialog connect = new ConnectDialog (this, true) ;
            connect.setLocationRelativeTo(null);
            connect.setVisible (true) ;
            comMenu.setEnabled(OrthoCotation.user.nom != null);
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