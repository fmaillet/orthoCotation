/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fmaillet.orthocotation;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
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
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

/**
 *
 * @author Fred
 */
public class OrthoCotation extends JFrame {
    
    static OrthoCotation fen ;
    static JTabbedPane tabbedPane ;
    static JPanel basePanel ;
    
    //Dates bilan et naissance
    static JDatePickerImpl dateBilan ;
    static JDatePickerImpl dateBirth ;
    static JLabel labelAge ;
    
    public OrthoCotation () {
        setTitle ("orthoCotation ("+OrthoCotation.getSoftVersion()+") - MODE DEMONSTRATION (NON CONNECTE)");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);  setSize(1200, 700);
        
        getContentPane().setBackground(Color.CYAN);
        setLocationRelativeTo(null);
        
        //Menu
        JMenuBar barreMenus = new JMenuBar () ;
        setJMenuBar (barreMenus) ;
        //Entrée Menus "Fichiers"
        JMenu fileMenu = new JMenu ("Fichier") ;
        barreMenus.add(fileMenu) ;
        
        
        
    }
    
    private static void addTabbedPanes () {
        //Tabbed panes
        tabbedPane = new JTabbedPane();
        tabbedPane.setBounds(0, 0, 700, 500);
        
        //Premier tab
        tabbedPane.addTab("Base", addBasePanel () );
        
        //Second tab
        JPanel panel2 = new JPanel(false);
        tabbedPane.addTab("DEM", panel2);
         //third tab
        JPanel panel3 = new JPanel(false);
        tabbedPane.addTab("TVPS", panel3);
        
    }
    
    private static JPanel addBasePanel () {
        basePanel = new JPanel () ;
        basePanel.setLayout(null);
        
        // Date du bilan
        JLabel lab1 = new JLabel ("Date du bilan :") ;
        lab1.setBounds(20, 20, 150, 30);
        basePanel.add(lab1) ;
        
        UtilDateModel modelBilan = new UtilDateModel() ;
        Properties p = new Properties();
        p.put("text.today", "Aujourd'hui");
        p.put("text.month", "Mois");
        p.put("text.year", "Année");
        JDatePanelImpl datePanel = new JDatePanelImpl(modelBilan, p);
        dateBilan = new JDatePickerImpl(datePanel, new DateLabelFormatter());
        dateBilan.setBounds(110, 20, 130, 50);
        dateBilan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                calculateAge () ;
            }
            }) ;
        basePanel.add(dateBilan);
        // Date de naissance
        JLabel lab2 = new JLabel ("Date de naissance :") ;
        lab2.setBounds(280, 20, 150, 30);
        basePanel.add(lab2) ;
        
        UtilDateModel modelBirth = new UtilDateModel() ;
        JDatePanelImpl dateB = new JDatePanelImpl(modelBirth, p);
        dateBirth = new JDatePickerImpl(dateB, new DateLabelFormatter());
        dateBirth.setBounds(400, 20, 130, 50);
        dateBirth.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                calculateAge () ;
            }
            }) ;
        basePanel.add(dateBirth);
        
        // Age
        JLabel lab3 = new JLabel ("Age :") ;
        lab3.setBounds(20, 70, 50, 30);
        basePanel.add(lab3) ;
        labelAge = new JLabel ("...") ;
        labelAge.setBounds(150, 70, 200, 30);
        basePanel.add(labelAge) ;
        
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
        System.out.println (age.getYears() + "y " + age.getMonths() + "m") ;
        labelAge.setText(age.getYears() + " ans " + age.getMonths() + " mois");
    }
    
    public static void main(String[] args) {
        // fenetre principale
        fen = new OrthoCotation () ;
        addTabbedPanes () ;
        fen.getContentPane().add (tabbedPane) ;
        fen.setVisible(true) ;
        tabbedPane.setBounds (0, 0, 700, fen.getContentPane().getHeight() + 10);
        fen.setResizable(false);
    }
    
    public static String getSoftVersion () {
        return "v0.1.0-BETA du 26/02/2018" ;
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