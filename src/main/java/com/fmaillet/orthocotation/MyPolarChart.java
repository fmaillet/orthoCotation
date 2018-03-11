/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fmaillet.orthocotation;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTick;
import org.jfree.chart.plot.PolarPlot;
import org.jfree.chart.renderer.DefaultPolarItemRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.chart.title.Title;
import org.jfree.chart.ui.RectangleInsets;
import org.jfree.chart.ui.TextAnchor;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author Fred
 */
public class MyPolarChart {
    
    static JFreeChart chart ;
    static XYSeriesCollection dataset ;
    static DefaultPolarItemRenderer renderer ;
    Shape nullShape  = new Ellipse2D.Double(0,0,0,0);
    
    ChartPanel panel ;
    List ticks ;
    
    public MyPolarChart () {
        XYDataset dataset = getXYDataset();
        
        //On formate l'axe
        NumberAxis numberAxis = new NumberAxis();
        numberAxis.setTickLabelsVisible(true);
        numberAxis.setTickMarksVisible(false);
        numberAxis.setTickLabelInsets(new RectangleInsets(0.0, 0.0, 0.0, 0.0));
        numberAxis.setAxisLineVisible(false);
        numberAxis.setAutoRange(false);
        numberAxis.setRange(-5, +3);
        numberAxis.setVisible(true);
        
        //Renderer
        renderer = new DefaultPolarItemRenderer() ;
        
        
        renderer.setFillComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.25f));
        renderer.setShapesVisible(true);
        //renderer.setSeriesShape(1, Shape.);
        renderer.setSeriesPaint(0, Color.BLUE);
        renderer.setSeriesFilled(0, false);
        renderer.setSeriesFilled(1, true);
        renderer.setSeriesPaint(1, Color.RED);
        renderer.setSeriesStroke(1, new BasicStroke (0));
        renderer.setSeriesPaint(2, Color.GREEN);
        
        renderer.setSeriesShape(1, nullShape);
        renderer.setSeriesShape(2, nullShape);
        
        ticks = new ArrayList();
        
        PolarPlot plot = new PolarPlot(dataset, numberAxis, renderer) {

            @Override
            protected List refreshAngleTicks() {
                return ticks;
            }
        };
        
        
        plot.setAngleGridlinesVisible(true);
        plot.setRadiusMinorGridlinesVisible(false);
        plot.setBackgroundPaint(Color.white);
        plot.setAngleGridlinePaint(Color.black);
        plot.setRadiusGridlinePaint(Color.lightGray);
        plot.setOutlineVisible(false);
        
        TextTitle title = new TextTitle ("Ecarts à la norme des principaux indicateurs orthoptiques", new java.awt.Font("SansSerif", Font.BOLD, 14) ) ;
        
        chart = new JFreeChart ("Title", JFreeChart.DEFAULT_TITLE_FONT, plot, true);
        chart.setTitle(title);
        chart.setBackgroundPaint(Color.WHITE);
        chart.setBorderVisible(false);
        List<Title> subTitles = new ArrayList<Title>();
        subTitles.add(new TextTitle("(exprimé en déviation standard, DS)"));
        chart.setSubtitles(subTitles);
        chart.removeLegend();
        
        
    }
    
    public JPanel addPolarPanel () {
        panel = new ChartPanel(chart);
        panel.setMouseZoomable(false);
        //Menu
         JPopupMenu popup = panel.getPopupMenu();
         popup.remove(0); popup.remove(0);
         popup.remove(2); popup.remove(2);
         popup.remove(2); popup.remove(2);
         popup.remove(2); popup.remove(2);popup.remove(2);
         JMenuItem j = (JMenuItem) popup.getComponent(1) ;
         //System.out.println (j.getText());
         j.setText("Enregistrer");
        return panel ;
    }
    
    public int updateTicks () {
        ticks.clear();
        int n = (OrthoCotation.baseValues.phorieL.selected ? 1 : 0) +
                (OrthoCotation.baseValues.phorieP.selected ? 1 : 0) +
                (OrthoCotation.baseValues.fusionDP.selected ? 1 : 0) +
                (OrthoCotation.baseValues.fusionCP.selected ? 1 : 0) +
                (OrthoCotation.baseValues.fusionCL.selected ? 1 : 0) +
                (OrthoCotation.baseValues.fusionDL.selected ? 1 : 0) +
                (OrthoCotation.baseValues.ppc.selected ? 1 : 0) +
                (OrthoCotation.baseValues.ppa.selected ? 1 : 0) +
                (OrthoCotation.baseValues.aca.selected ? 1 : 0) +
                (OrthoCotation.baseValues.fdv.selected ? 1 : 0) +
                (OrthoCotation.baseValues.fda.selected ? 1 : 0) ;
        
        if (n > 0) n = 360 / n ;
        else return 0 ;
        
        //System.out.println (n) ;
        
        int index = 0 ;
        if (OrthoCotation.baseValues.phorieL.selected) {
            ticks.add(new NumberTick(index, "Phorie(L)", TextAnchor.TOP_LEFT, TextAnchor.TOP_LEFT, 0));
            index = index + n ;
        }
        if (OrthoCotation.baseValues.phorieP.selected) {
            ticks.add(new NumberTick(index, "Phorie(P)", TextAnchor.TOP_LEFT, TextAnchor.TOP_LEFT, 0));
            index = index + n ;
        }
        if (OrthoCotation.baseValues.fusionDP.selected) {
            ticks.add(new NumberTick(index, "D'", TextAnchor.TOP_LEFT, TextAnchor.TOP_LEFT, 0));
            index = index + n ;
        }
        if (OrthoCotation.baseValues.fusionCP.selected) {
            ticks.add(new NumberTick(index, "C'", TextAnchor.TOP_LEFT, TextAnchor.TOP_LEFT, 0));
            index = index + n ;
        }
        if (OrthoCotation.baseValues.fusionCL.selected) {
            ticks.add(new NumberTick(index, "C", TextAnchor.TOP_LEFT, TextAnchor.TOP_LEFT, 0));
            index = index + n ;
        }
        if (OrthoCotation.baseValues.fusionDL.selected) {
            ticks.add(new NumberTick(index, "D", TextAnchor.TOP_LEFT, TextAnchor.TOP_LEFT, 0));
            index = index + n ;
        }
        if (OrthoCotation.baseValues.ppc.selected) {
            ticks.add(new NumberTick(index, "PPC", TextAnchor.TOP_LEFT, TextAnchor.TOP_LEFT, 0));
            index = index + n ;
        }
        if (OrthoCotation.baseValues.ppa.selected) {
            ticks.add(new NumberTick(index, "PPA", TextAnchor.TOP_LEFT, TextAnchor.TOP_LEFT, 0));
            index = index + n ;
        }
        if (OrthoCotation.baseValues.aca.selected) {
            ticks.add(new NumberTick(index, "AC/A", TextAnchor.TOP_LEFT, TextAnchor.TOP_LEFT, 0));
            index = index + n ;
        }
        if (OrthoCotation.baseValues.fdv.selected) {
            ticks.add(new NumberTick(index, "FdV", TextAnchor.TOP_LEFT, TextAnchor.TOP_LEFT, 0));
            index = index + n ;
        }
        if (OrthoCotation.baseValues.fda.selected) {
            ticks.add(new NumberTick(index, "FdA", TextAnchor.TOP_LEFT, TextAnchor.TOP_LEFT, 0));
            index = index + n ;
        }
        
        return n ;
    }
    
    public void updateDataset () {
        dataset.removeAllSeries();
        
        //Cercle rouge interne
        XYSeries redRing = new XYSeries("redRing", true);
        for (int i=0; i<=360; i=i+5) redRing.add(i, -1.0);
        //Cercle vert externe
        XYSeries greenRing = new XYSeries("greenRing", false);
        for (int i=0; i<=360; i=i+5) greenRing.add(i, 1.0);
        for (int i=360; i>=0; i=i-5) greenRing.add(i, -1.0) ;
        //Cercle vert foncé externe
        XYSeries darkGreenRing = new XYSeries("darkGreenRing", false);
        for (int i=0; i<=360; i=i+5) darkGreenRing.add(i, 3.0);
        for (int i=360; i>=0; i=i-5) darkGreenRing.add(i, 1.0) ;
        
        float[] dash_array = new float[2];
        dash_array[0] = Float.MIN_VALUE; //visible
        dash_array[1] = Float.MAX_VALUE; //invisible
        BasicStroke stroke = new BasicStroke(0.0f,
            BasicStroke.CAP_SQUARE,
            BasicStroke.JOIN_ROUND,
            10f,
            dash_array,
            0f);
        
        //On adapte l'apparence
        renderer.setFillComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.30f));
        renderer.setShapesVisible(true);
        //On adapte l'apparence du cercle rouge
        renderer.setSeriesFilled(2, true);
        renderer.setSeriesPaint(2, Color.RED);
        renderer.setSeriesStroke(2, stroke);
        renderer.setSeriesShape(2, nullShape);
        //On adapte l'apparence du cercle vert
        renderer.setSeriesFilled(1, true);
        renderer.setSeriesPaint(1, Color.GREEN);
        renderer.setSeriesStroke(1, stroke);
        renderer.setSeriesShape(1, nullShape);
        //On adapte l'apparence du cercle vert foncé
        renderer.setSeriesFilled(0, true);
        renderer.setSeriesPaint(0, Color.GREEN.darker());
        renderer.setSeriesStroke(0, stroke);
        renderer.setSeriesShape(0, nullShape);
        //Apparence des données
        renderer.setSeriesPaint(3, Color.BLUE);
        renderer.setSeriesFilled(3, false);
        
        //Les données maintenant
        XYSeries datas = new XYSeries("datas");
        int index  = 0 ;
        int n = updateTicks () ;
        if (n == 0 ) return ;
        
        if (OrthoCotation.baseValues.phorieL.selected) {
            datas.add(index, OrthoCotation.baseValues.phorieL.ds);
            index = index + n ;
        }
        if (OrthoCotation.baseValues.phorieP.selected) {
            datas.add(index, OrthoCotation.baseValues.phorieP.ds);
            index = index + n ;
        }
        if (OrthoCotation.baseValues.fusionDP.selected) {
            datas.add(index, OrthoCotation.baseValues.fusionDP.ds);
            index = index + n ;
        }
        if (OrthoCotation.baseValues.fusionCP.selected) {
            datas.add(index, OrthoCotation.baseValues.fusionCP.ds);
            index = index + n ;
        }
        if (OrthoCotation.baseValues.fusionCL.selected) {
            datas.add(index, OrthoCotation.baseValues.fusionCL.ds);
            index = index + n ;
        }
        if (OrthoCotation.baseValues.fusionDL.selected) {
            datas.add(index, OrthoCotation.baseValues.fusionDL.ds);
            index = index + n ;
        }
        if (OrthoCotation.baseValues.ppc.selected) {
            datas.add(index, OrthoCotation.baseValues.ppc.ds);
            index = index + n ;
        }
        if (OrthoCotation.baseValues.ppa.selected) {
            datas.add(index, OrthoCotation.baseValues.ppa.ds);
            index = index + n ;
        }
        if (OrthoCotation.baseValues.aca.selected) {
            datas.add(index, OrthoCotation.baseValues.aca.ds);
            index = index + n ;
        }
        if (OrthoCotation.baseValues.fdv.selected) {
            datas.add(index, OrthoCotation.baseValues.fdv.ds);
            index = index + n ;
        }
        if (OrthoCotation.baseValues.fda.selected) {
            datas.add(index, OrthoCotation.baseValues.fda.ds);
            index = index + n ;
        }
        
        //On ajoute dans l'ordre
        dataset.addSeries(darkGreenRing);
        dataset.addSeries(greenRing);
        dataset.addSeries(redRing);
        dataset.addSeries (datas) ;
        
        
        
        
    }
    
    private static XYDataset getXYDataset() {
     
      dataset = new XYSeriesCollection();

      XYSeries series1 = new XYSeries("Series1");
      series1.add(0, -1);
      series1.add(45, +1);
      series1.add(90, -1.3);
      series1.add(135, 0);
      series1.add(180, +0.5);
      series1.add(225, +1.3);
      series1.add(270, -0.2);
      series1.add(315, 0.1);
      
      
      XYSeries series2 = new XYSeries("series2");
      for (int i=0; i<=360; i=i+5) series2.add(i, -1.5);
      /*series2.add(0, -1.5);
      series2.add(20, -1.5);
      series2.add(45, -1.5);
      series2.add(90, -1.5);
      series2.add(135, -1.5);
      series2.add(180, -1.5);
      series2.add(225, -1.5);
      series2.add(270, -1.5);
      series2.add(315, -1.5);*/
      
      
      XYSeries series3 = new XYSeries("series3");
      series3.add(0, 1);
      series3.add(45, 1);
      series3.add(90, 1);
      series3.add(135, 1);
      series3.add(180, 1);
      series3.add(225, 1);
      series3.add(270, 1);
      series3.add(315, 1);
      
      //On ajoute les séries
      dataset.addSeries(series1);
      dataset.addSeries(series2);
      dataset.addSeries(series3);
      
      return dataset;
   }
}
