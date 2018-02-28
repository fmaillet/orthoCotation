/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fmaillet.orthocotation;

import java.awt.Color;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTick;
import org.jfree.chart.plot.PolarPlot;
import org.jfree.chart.plot.SpiderWebPlot;
import org.jfree.chart.renderer.DefaultPolarItemRenderer;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import static org.jfree.chart.ui.Align.TOP_LEFT;
import org.jfree.chart.ui.RectangleEdge;
import org.jfree.chart.ui.RectangleInsets;
import org.jfree.chart.ui.TextAnchor;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;


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
    
    //Graphique
    static CategoryDataset dataset ;
    static JPanel radioPanel ;
    
    public OrthoCotation () {
        setTitle ("orthoCotation ("+OrthoCotation.getSoftVersion()+") - MODE DEMONSTRATION (NON CONNECTE)");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);  setSize(1200, 700);
        
        getContentPane().setBackground(Color.CYAN);
        setLocationRelativeTo(null);
        
        //Menu
        JMenuBar barreMenus = new JMenuBar () ;
        setJMenuBar (barreMenus) ;
        //EntrÃ©e Menus "Fichiers"
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
        p.put("text.year", "AnnÃ©e");
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
    
    private static JPanel  addRadioChart () {
        dataset = createDataset () ;
        SpiderWebPlot plot = new SpiderWebPlot(dataset);
        plot.setStartAngle(0);
        plot.setInteriorGap(0.40);
        plot.setWebFilled(false);
        
        JFreeChart chart = new JFreeChart("Ecarts à la norme (DS) des principaux\nindicateurs orthoptiques",
            TextTitle.DEFAULT_FONT, plot, false);
        //chart.getTitle().setFont;
        LegendTitle legendtitle = new LegendTitle(plot);   
        legendtitle.setPosition(RectangleEdge.BOTTOM);   
        chart.addSubtitle(legendtitle);
        
        return new ChartPanel(chart);
    }
    
    private static JPanel addPolarChart () {
        XYDataset dataset = getXYDataset();
        
        NumberAxis numberAxis = new NumberAxis();
        numberAxis.setTickLabelsVisible(true);
        numberAxis.setTickMarksVisible(false);
        numberAxis.setTickLabelInsets(new RectangleInsets(0.0, 0.0, 0.0, 0.0));
        numberAxis.setAxisLineVisible(false);
        numberAxis.setAutoRange(false);
        numberAxis.setRange(-3, +3);
        numberAxis.setVisible(true);
        
        DefaultPolarItemRenderer renderer = new DefaultPolarItemRenderer();
        renderer.setShapesVisible(true);
        
        PolarPlot plot = new PolarPlot(dataset, numberAxis, renderer) {

            @Override
            protected List refreshAngleTicks() {
                List ticks = new ArrayList();
                ticks.add(new NumberTick(0, "PPC", TextAnchor.TOP_LEFT, TextAnchor.TOP_LEFT, 0));
                ticks.add(new NumberTick(45, "P", TextAnchor.TOP_LEFT, TextAnchor.TOP_LEFT, 0));
                ticks.add(new NumberTick(90, "C", TextAnchor.TOP_LEFT, TextAnchor.TOP_LEFT, 0));
                ticks.add(new NumberTick(135, "D", TextAnchor.TOP_LEFT, TextAnchor.TOP_LEFT, 0));
                ticks.add(new NumberTick(180, "C'", TextAnchor.TOP_LEFT, TextAnchor.TOP_LEFT, 0));
                ticks.add(new NumberTick(180, "D'", TextAnchor.TOP_LEFT, TextAnchor.TOP_LEFT, 0));
                ticks.add(new NumberTick(225, "PPA", TextAnchor.TOP_LEFT, TextAnchor.TOP_LEFT, 0));
                ticks.add(new NumberTick(270, "AC/A", TextAnchor.TOP_LEFT, TextAnchor.TOP_LEFT, 0));
                ticks.add(new NumberTick(315, "P'", TextAnchor.TOP_LEFT, TextAnchor.TOP_LEFT, 0));
                return ticks;
            }
        };
        
        plot.setAngleGridlinesVisible(true);
        plot.setRadiusMinorGridlinesVisible(false);
        
        JFreeChart chart = new JFreeChart ("Ecarts à la norme (DS) des principaux\nindicateurs orthoptiques", JFreeChart.DEFAULT_TITLE_FONT, plot, true);
        
        /*JFreeChart chart = ChartFactory.createPolarChart(
            "Polar Chart Example | WWW.BORAJI.COM", // Chart title
            dataset,
            true,
            true,
            false
            );*/
        
      
      ChartPanel panel = new ChartPanel(chart);
      panel.setMouseZoomable(false);
      return panel ;
    }
    
    private static XYDataset getXYDataset() {
     
      XYSeriesCollection dataset = new XYSeriesCollection();

      XYSeries series1 = new XYSeries("Series1");
      series1.add(0, -1);
      series1.add(45, +1);
      series1.add(90, -1.3);
      series1.add(135, 0);
      series1.add(180, +0.5);
      series1.add(225, +1.3);
      series1.add(270, -0.2);
      series1.add(315, 0.1);
      dataset.addSeries(series1);
      
      return dataset;
   }
    
    private static CategoryDataset createDataset() {

        // row keys...
        String series1 = "First";
        String series2 = "Second";
        String series3 = "Third";

        // column keys...
        String category1 = "Phorie (P)";
        String category2 = "Phorie (L)";
        String category3 = "D'";
        String category4 = "C'";
        String category5 = "C";

        // create the dataset...
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        dataset.addValue(1.0, series1, category1);
        dataset.addValue(4.0, series1, category2);
        dataset.addValue(3.0, series1, category3);
        dataset.addValue(5.0, series1, category4);
        dataset.addValue(5.0, series1, category5);

        dataset.addValue(5.0, series2, category1);
        dataset.addValue(7.0, series2, category2);
        dataset.addValue(6.0, series2, category3);
        dataset.addValue(8.0, series2, category4);
        dataset.addValue(4.0, series2, category5);

        dataset.addValue(4.0, series3, category1);
        dataset.addValue(3.0, series3, category2);
        dataset.addValue(2.0, series3, category3);
        dataset.addValue(3.0, series3, category4);
        dataset.addValue(6.0, series3, category5);

        return dataset;

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
        
        radioPanel = addPolarChart () ;
        radioPanel.setBounds(750, 20, 350, 350);
        radioPanel.setVisible(true);
        fen.getContentPane().add (radioPanel) ;
        //fen.getContentPane().revalidate();
        fen.repaint();
        
        /*JPanel test = new JPanel () ;
        test.setBackground(Color.red);
        radioPanel.setBounds(700, 300, 100, 100);
        fen.getContentPane().add (test) ;*/
    }
    
    public static String getSoftVersion () {
        return "v0.2.0-BETA du 27/02/2018" ;
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