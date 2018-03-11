/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fmaillet.orthocotation;

import java.awt.Color;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author Fred
 */
public class MyBarChart {
    
    static JFreeChart chart ;
    ChartPanel panel ;
    
    DefaultCategoryDataset dataset ;
    
    public MyBarChart () {
        
        dataset = new DefaultCategoryDataset( );
        
         chart = ChartFactory.createBarChart(
         "TVPS-3",           
         "Category",            
         "Score",            
         createDataset(),          
         PlotOrientation.VERTICAL,           
         true, true, false);
         
         chart.removeLegend();
    }
    
    public JPanel addBarPanel () {
        panel = new ChartPanel(chart);
        panel.setMouseZoomable(false);
        return panel ;
    }
    
    public void updateGraph () {
        dataset.clear();
        
        dataset.addValue( Panel_TVPS.tvpsStdValues[0] , "base" , "DIS" );        
        dataset.addValue( Panel_TVPS.tvpsStdValues[1] , "base" , "MEM" );        
        dataset.addValue( Panel_TVPS.tvpsStdValues[2] , "base" , "SPA" ); 
        dataset.addValue( Panel_TVPS.tvpsStdValues[3] , "base" , "CON" );
        dataset.addValue( Panel_TVPS.tvpsStdValues[4] , "base" , "SEQ" );        
        dataset.addValue( Panel_TVPS.tvpsStdValues[5] , "base" , "FGR" ); 
        dataset.addValue( Panel_TVPS.tvpsStdValues[6] , "base" , "CLO" );
        
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.getDomainAxis().setLabel("Sub-Tests");
        plot.getRangeAxis().setLabel("Scores standards");
        
        BarRenderer barRenderer = (BarRenderer) plot.getRenderer();
        barRenderer.setSeriesPaint(0, Color.GRAY);
        
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