/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fmaillet.orthocotation;

import java.awt.Color;
import java.awt.Font;
import java.awt.Paint;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import org.jfree.chart.ChartColor;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.IntervalMarker;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.chart.ui.Layer;
import org.jfree.chart.ui.RectangleAnchor;
import org.jfree.chart.ui.TextAnchor;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author Fred
 */
public class MyBarChart {
    
    static JFreeChart chart ;
    ChartPanel panel ;
    
    CategoryPlot plot ;
    
            
    DefaultCategoryDataset dataset ;
    BarRenderer barRenderer ;
    final IntervalMarker target ;
    
    public MyBarChart () {
        
        dataset = new DefaultCategoryDataset( );
        
         chart = ChartFactory.createBarChart(
            "TVPS-3 scores & percentiles",           
            "Subtests",            
            "Scaled Scores",            
            dataset,          
            PlotOrientation.VERTICAL,           
            true, true, false);
         panel = new ChartPanel(chart);
         chart.removeLegend();
         chart.setTitle(new TextTitle("TVPS-3 scores & percentiles", new Font (chart.getTitle().getFont().getFontName(), Font.BOLD , 17)));
         //Menu
         JPopupMenu popup = panel.getPopupMenu();
         popup.remove(0); popup.remove(0);
         popup.remove(2); popup.remove(2);
         popup.remove(2); popup.remove(2);
         popup.remove(2); popup.remove(2);popup.remove(2);
         JMenuItem j = (JMenuItem) popup.getComponent(1) ;
         //System.out.println (j.getText());
         j.setText("Enregistrer");
         
         //Le plot
         plot = (CategoryPlot) chart.getPlot();
         plot.getRangeAxis().setAutoRange(false);
        plot.getRangeAxis().setRange(0.0, 20);
        plot.getRangeAxis().setUpperMargin(plot.getRangeAxis().getUpperMargin() * 2);
        plot.setBackgroundPaint(ChartColor.white); 
        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(ChartColor.GRAY); 
        
        //barRenderer = (BarRenderer) plot.getRenderer();
        barRenderer = new CustomRenderer (OrthoCotation.panelTVPS) ;
        plot.setRenderer(barRenderer) ;
        Color lightBlue= new Color(51, 153, 255,155);
        barRenderer.setSeriesPaint(0, lightBlue);
        plot.setForegroundAlpha(0.95f);
        
        barRenderer.setDrawBarOutline(false);
        
        barRenderer.setDefaultItemLabelGenerator(
            new StandardCategoryItemLabelGenerator() {
             @Override
             public String generateLabel(CategoryDataset dataset, int row, int column) {
                 if (Panel_TVPS.tvpsPctlValues[column] < 1)
                     return "<1er p." ;
                 else if (Panel_TVPS.tvpsPctlValues[column] == 1)
                     return "1er p." ;
                 else if (Panel_TVPS.tvpsPctlValues[column] < 100)
                    return String.valueOf(Panel_TVPS.tvpsPctlValues[column]) + "è p." ;
                else
                    return ">99è p." ;
             }
            });
        barRenderer.setSeriesItemLabelFont(0, new Font("Arial", Font.ITALIC, 10));
        barRenderer.setDefaultItemLabelsVisible(true);
        ItemLabelPosition position = new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, 
            TextAnchor.BASELINE_CENTER);
        barRenderer.setDefaultPositiveItemLabelPosition(position);
        
        barRenderer.setDrawBarOutline(false);
        barRenderer.setItemMargin(0.2);
        barRenderer.setMaximumBarWidth(.08);
        barRenderer.setShadowVisible(false);
        
        //Prepare Interval Range Marker
        target = new IntervalMarker(7.0, 13.0);
        //target.setLabel("Target Range");
        target.setLabelFont(new Font("SansSerif", Font.ITALIC, 11));
        target.setLabelAnchor(RectangleAnchor.LEFT);
        target.setLabelTextAnchor(TextAnchor.CENTER_LEFT);
        target.setPaint(new Color(222, 222, 255, 128));
        
    }
    
    public JPanel addBarPanel () {
        
        panel.setMouseZoomable(false);
        return panel ;
    }
    
    public void updateGraph () {
        dataset.clear();
        
        if (OrthoCotation.user.nom == null) {
            panel.setEnabled(false);
            return ;
        }
        else panel.setEnabled(true);
        
        if (Panel_TVPS.tvpsChkValues[0]) dataset.addValue( Panel_TVPS.tvpsStdValues[0] , "base" , "DIS" );        
        if (Panel_TVPS.tvpsChkValues[1]) dataset.addValue( Panel_TVPS.tvpsStdValues[1] , "base" , "MEM" );        
        if (Panel_TVPS.tvpsChkValues[2]) dataset.addValue( Panel_TVPS.tvpsStdValues[2] , "base" , "SPA" ); 
        if (Panel_TVPS.tvpsChkValues[3]) dataset.addValue( Panel_TVPS.tvpsStdValues[3] , "base" , "CON" );
        if (Panel_TVPS.tvpsChkValues[4]) dataset.addValue( Panel_TVPS.tvpsStdValues[4] , "base" , "SEQ" );        
        if (Panel_TVPS.tvpsChkValues[5]) dataset.addValue( Panel_TVPS.tvpsStdValues[5] , "base" , "FGR" ); 
        if (Panel_TVPS.tvpsChkValues[6]) dataset.addValue( Panel_TVPS.tvpsStdValues[6] , "base" , "CLO" );
        
        
        /*plot.setNoDataMessage("NO DATA!"); 
        plot.getDomainAxis().setLabel("Subtests");
        plot.getRangeAxis().setLabel("Scaled scores");*/
        
        
        
        
        //barRenderer.setSeriesPositiveItemLabelPosition(0,new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_CENTER));
        
        
        //barRenderer.setItemMargin(3.0);
        
        
        //barRenderer.setDefaultItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        
        
        
        /*final GradientPaint gp0 = new GradientPaint(
            0.0f, 0.0f, Color.blue, 
            0.0f, 0.0f, Color.lightGray
        );
        barRenderer.setSeriesPaint(0, gp0);*/
        
        
        
        Panel_TVPS.jChgeColor.setEnabled(true);
    }
    
    public void changeColor(Color c) {
        barRenderer.setSeriesPaint(0, c);
    }
    
    public void changeAspect (boolean titre, boolean subTitle, boolean rge) {
        //Titre
        if (titre) chart.setTitle(new TextTitle("TVPS-3 scores & percentiles", new Font (chart.getTitle().getFont().getFontName(), Font.BOLD , 17)));
        else chart.setTitle("");
        //Sous-titre
        if (subTitle) plot.getDomainAxis().setLabel("Subtests");
        else plot.getDomainAxis().setLabel("");
        //Normal range
        if (rge) {
            if (plot.getRangeMarkers(Layer.BACKGROUND) == null) plot.addRangeMarker(target, Layer.BACKGROUND);
            else if (plot.getRangeMarkers(Layer.BACKGROUND).isEmpty()) plot.addRangeMarker(target, Layer.BACKGROUND);
        }
        else plot.removeRangeMarker(target, Layer.BACKGROUND) ;
    }   
}

class CustomRenderer extends BarRenderer {
    static Panel_TVPS panel ;
    
    public CustomRenderer (Panel_TVPS panel) {
        this.panel = panel ;
    }
    
    public Paint getItemPaint(final int row, final int column) {
            
            if (panel.tvpsPctlValues[column] <= 5) return Color.RED;
            else return Color.CYAN ;
        }
}