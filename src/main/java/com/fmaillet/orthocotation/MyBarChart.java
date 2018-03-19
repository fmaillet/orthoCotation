/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fmaillet.orthocotation;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.text.DecimalFormat;
import java.text.NumberFormat;
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
    
    public MyBarChart () {
        
        dataset = new DefaultCategoryDataset( );
        
         chart = ChartFactory.createBarChart(
         "TVPS-3",           
         "Category",            
         "Score",            
         dataset,          
         PlotOrientation.VERTICAL,           
         true, true, false);
         panel = new ChartPanel(chart);
         chart.removeLegend();
         //Menu
         JPopupMenu popup = panel.getPopupMenu();
         popup.remove(0); popup.remove(0);
         popup.remove(2); popup.remove(2);
         popup.remove(2); popup.remove(2);
         popup.remove(2); popup.remove(2);popup.remove(2);
         JMenuItem j = (JMenuItem) popup.getComponent(1) ;
         //System.out.println (j.getText());
         j.setText("Enregistrer");
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
        
        dataset.addValue( Panel_TVPS.tvpsStdValues[0] , "base" , "DIS" );        
        dataset.addValue( Panel_TVPS.tvpsStdValues[1] , "base" , "MEM" );        
        dataset.addValue( Panel_TVPS.tvpsStdValues[2] , "base" , "SPA" ); 
        dataset.addValue( Panel_TVPS.tvpsStdValues[3] , "base" , "CON" );
        dataset.addValue( Panel_TVPS.tvpsStdValues[4] , "base" , "SEQ" );        
        dataset.addValue( Panel_TVPS.tvpsStdValues[5] , "base" , "FGR" ); 
        dataset.addValue( Panel_TVPS.tvpsStdValues[6] , "base" , "CLO" );
        
        plot = (CategoryPlot) chart.getPlot();
        plot.setNoDataMessage("NO DATA!"); 
        plot.getDomainAxis().setLabel("Subtests");
        plot.getRangeAxis().setLabel("Scaled scores");
        plot.getRangeAxis().setAutoRange(false);
        plot.getRangeAxis().setRange(0.0, 20);
        plot.getRangeAxis().setUpperMargin(plot.getRangeAxis().getUpperMargin() * 2);
        plot.setBackgroundPaint(ChartColor.white); 
        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(ChartColor.GRAY); 
        
        barRenderer = (BarRenderer) plot.getRenderer();
        Color lightBlue= new Color(51, 153, 255,155);
        barRenderer.setSeriesPaint(0, lightBlue);
        //barRenderer.setSeriesPositiveItemLabelPosition(0,new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_CENTER));
        
        plot.setForegroundAlpha(0.95f);
        
        barRenderer.setDrawBarOutline(false);
        //barRenderer.setItemMargin(3.0);
        
        
        //barRenderer.setDefaultItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        
        barRenderer.setDefaultItemLabelGenerator(
            new StandardCategoryItemLabelGenerator() {
             @Override
             public String generateLabel(CategoryDataset dataset, int row, int column) {
                 
                    return String.valueOf(Panel_TVPS.tvpsPctlValues[column]);
             }
            });
        barRenderer.setSeriesItemLabelFont(0, new Font("Arial", Font.ITALIC, 10));
        barRenderer.setDefaultItemLabelsVisible(true);
        ItemLabelPosition position = new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, 
        TextAnchor.BASELINE_CENTER);
        barRenderer.setDefaultPositiveItemLabelPosition(position);
        
        /*final GradientPaint gp0 = new GradientPaint(
            0.0f, 0.0f, Color.blue, 
            0.0f, 0.0f, Color.lightGray
        );
        barRenderer.setSeriesPaint(0, gp0);*/
        barRenderer.setDrawBarOutline(false);
        barRenderer.setItemMargin(0.2);
        barRenderer.setMaximumBarWidth(.08);
        barRenderer.setShadowVisible(false);
        
        /*final IntervalMarker target = new IntervalMarker(8.0, 10.0);
        target.setLabel("Target Range");
        target.setLabelFont(new Font("SansSerif", Font.ITALIC, 11));
        target.setLabelAnchor(RectangleAnchor.LEFT);
        target.setLabelTextAnchor(TextAnchor.CENTER_LEFT);
        target.setPaint(new Color(222, 222, 255, 128));
        plot.addRangeMarker(target, Layer.BACKGROUND);*/
    }
    
    public void changeColor(Color c) {
        barRenderer.setSeriesPaint(0, c);
    }
    
    private CategoryDataset createDataset( ) {
            

      dataset.addValue( 1.0 , "base" , "DIS" );        
      dataset.addValue( 3.0 , "base" , "MEM" );        
      dataset.addValue( 5.0 , "base" , "SPA" ); 
      dataset.addValue( 5.0 , "base" , "CON" );
      dataset.addValue( 3.0 , "base" , "SEQ" );        
      dataset.addValue( 5.0 , "base" , "FGR" ); 
      dataset.addValue( 5.0 , "base" , "CLO" );
     

                    

      return dataset; 
   }
    
}
