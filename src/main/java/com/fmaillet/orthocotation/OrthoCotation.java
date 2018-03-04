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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
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
    
    public Image tinyTrophy ;
    
    //Synthèse des valeurs
    public static BaseDSValues baseValues = new BaseDSValues () ;
    
    //Dates bilan et naissance
    static JDatePickerImpl dateBilan ;
    static JDatePickerImpl dateBirth ;
    static JLabel labelAge ;
    static BaseDatas baseDatas ;
    
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
        //tinyTrophy = getToolkit().getImage(getClass().getResource("resources/connect.png"));
        
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
    
    private static void addTabbedPanes () {
        //Tabbed panes
        tabbedPane = new JTabbedPane();
        tabbedPane.setBounds(0, 0, 700, 500);
        
        //Premier tab
        tabbedPane.addTab("Vision binoculaire", addTabPanel_VB () );
        
        //Second tab
        JPanel panel2 = new JPanel(false);
        tabbedPane.addTab("DEM", panel2);
         //third tab
        JPanel panel3 = new JPanel(false);
        tabbedPane.addTab("TVPS", panel3);
        
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
        p.put("text.year", "AnnÃ©e");
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
        dateBirth.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                calculateAge () ;
            }
            }) ;
        getContentPane().add(dateBirth);
        
        labelAge = new JLabel ("[no age]") ;
        labelAge.setBounds(535, 10, 200, 30);
        labelAge.setFont(new Font(labelAge.getName(), Font.PLAIN, 18));
        labelAge.setForeground(Color.BLUE);
        getContentPane().add(labelAge) ;
    }
    
    private static JPanel addTabPanel_VB () {
        basePanel = new JPanel () ;
        basePanel.setLayout(null);
        
        //Panel Base Datas
        baseDatas = new BaseDatas () ;
        baseDatas.setBounds(10,10, 600, 460);
        baseDatas.setVisible(true);
        basePanel.add(baseDatas) ;
        //basePanel.revalidate() ;
        //basePanel.repaint() ;
        
        return basePanel ;
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
        baseDatas.updateFromAge () ;
    }
    
    public static void connected () {
        System.out.println ("From main : Connected " + OrthoCotation.user.nom) ;
        if (OrthoCotation.user.nom != null) {
            comMenu.setEnabled(false) ;
            barreMenus.setEnabled(true);
        }
        else comMenu.setEnabled(true) ;
    }
    
    public static void main(String[] args) {
        
        //For connection
        user = new UserInfo () ;
        mySQLConnection = new MySQLClass () ;
        
        // fenetre principale
        fen = new OrthoCotation () ;
        
        addTabbedPanes () ;
        fen.getContentPane().add (tabbedPane) ;
        fen.setVisible(true) ;
        tabbedPane.setBounds (0, 50, 700, fen.getContentPane().getHeight() + 10);
        fen.setResizable(false);
        
        
        
        radioPanel = polarChart.addPolarPanel();
        radioPanel.setBounds(750, 20, 420, 420);
        radioPanel.setVisible(true);
        fen.getContentPane().add (radioPanel) ;
        //fen.getContentPane().revalidate();
        fen.repaint();
        
        //AutoConnect
        AutoConnect auto = new AutoConnect () ;
        auto.start () ;
    }
    
    public static String getSoftVersion () {
        return "v0.6.1 du 04/03/2018" ;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Source ?
        Object source = e.getSource () ;
        //A Propos
        if (source == aboutItem) {
            AboutJDialog about = new AboutJDialog(this, true);
            about.pack();
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

        private String datePattern = "dd-MMM-yyyy";
        private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);


        @Override
        public Object stringToValue(String text) throws ParseException {
            return dateFormatter.parseObject(text);
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