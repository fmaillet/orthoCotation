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
public class BarChartTVPS4 {
    
    static JFreeChart chart ;
    ChartPanel panel ;
    
    CategoryPlot plot ;
    Color currentColor ;
    
            
    DefaultCategoryDataset dataset ;
    BarRenderer barRenderer ;
    final IntervalMarker target ;
    
    public BarChartTVPS4 () {
        
        dataset = new DefaultCategoryDataset( );
        
         chart = ChartFactory.createBarChart(
            "TVPS-4 scores & percentiles",           
            "Subtests",            
            "Scaled Scores",            
            dataset,          
            PlotOrientation.VERTICAL,           
            true, true, false);
         panel = new ChartPanel(chart);
         chart.removeLegend();
         chart.setTitle(new TextTitle("TVPS-4 scores & percentiles", new Font (chart.getTitle().getFont().getFontName(), Font.BOLD , 17)));
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
        
        Color lightBlue = new Color(51, 153, 255,155);
        currentColor = lightBlue ;
        //barRenderer = (BarRenderer) plot.getRenderer();
        barRenderer = new CustomRenderer4 (OrthoCotation.panelTVPS4, currentColor) ;
        plot.setRenderer(barRenderer) ;
        
        //barRenderer.setSeriesPaint(0, lightBlue);
        
        plot.setForegroundAlpha(0.95f);
        
        barRenderer.setDrawBarOutline(false);
        
        barRenderer.setDefaultItemLabelGenerator(
            new StandardCategoryItemLabelGenerator() {
             @Override
             public String generateLabel(CategoryDataset dataset, int row, int column) {
                 if (Panel_TVPS4.tvpsPctlValues[Panel_TVPS4.transposeColumn[column]] < 1)
                     return "<1er p." ;
                 else if (Panel_TVPS4.tvpsPctlValues[Panel_TVPS4.transposeColumn[column]] == 1)
                     return "1er p." ;
                 else if (Panel_TVPS4.tvpsPctlValues[Panel_TVPS4.transposeColumn[column]] < 100)
                    return String.valueOf(Panel_TVPS4.tvpsPctlValues[Panel_TVPS4.transposeColumn[column]]) + "è p." ;
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
    
    public void updateVisibleScores () {
        if (Panel_TVPS4.jScoresUnit.getSelectedIndex() == 0) {
            //Scaled datas
            plot.getRangeAxis().setRange(0.0, 20);
            plot.getRangeAxis().setUpperMargin(plot.getRangeAxis().getUpperMargin() * 2);
            barRenderer.setSeriesVisible(1, false) ;
            barRenderer.setSeriesVisible(0, true) ;
            target.setStartValue(7.0); target.setEndValue(13.0);
            plot.getRangeAxis().setLabel("Scaled scores");
        }
        else {
            plot.getRangeAxis().setRange(40.0, 150);
            plot.getRangeAxis().setUpperMargin(plot.getRangeAxis().getUpperMargin() * 2);
            barRenderer.setSeriesVisible(1, true) ;
            barRenderer.setSeriesVisible(0, false) ;
            target.setStartValue(85.0); target.setEndValue(115.0);
            plot.getRangeAxis().setLabel("Standard scores");
        }
    }
    
    public void updateGraph () {
        dataset.clear();
                
        if (OrthoCotation.user.nom == null) {
            panel.setEnabled(false);
            return ;
        }
        else panel.setEnabled(true);
        int count = 0 ;
        
        if (Panel_TVPS4.tvpsChkValues[0]) {
            dataset.addValue( Panel_TVPS4.tvpsSclValues[0] , "Scaled" , "DIS" );
            dataset.addValue( Panel_TVPS4.tvpsSclValues[0]*5+50 , "Standard" , "DIS" );
            count++;
        }        
        if (Panel_TVPS4.tvpsChkValues[1]) {
            dataset.addValue( Panel_TVPS4.tvpsSclValues[1] , "Scaled" , "MEM" );
            dataset.addValue( Panel_TVPS4.tvpsSclValues[1]*5+50 , "Standard" , "MEM" );
            count++;
        }        
        if (Panel_TVPS4.tvpsChkValues[2]) {
            dataset.addValue( Panel_TVPS4.tvpsSclValues[2] , "Scaled" , "SPA" );
            dataset.addValue( Panel_TVPS4.tvpsSclValues[2]*5+50 , "Standard" , "SPA" );
            count++;
        } 
        if (Panel_TVPS4.tvpsChkValues[3]) {
            dataset.addValue( Panel_TVPS4.tvpsSclValues[3] , "Scaled" , "CON" );
            dataset.addValue( Panel_TVPS4.tvpsSclValues[3]*5+50 , "Standard" , "CON" );
            count++;
        }
        if (Panel_TVPS4.tvpsChkValues[4]) {
            dataset.addValue( Panel_TVPS4.tvpsSclValues[4] , "Scaled" , "SEQ" );
            dataset.addValue( Panel_TVPS4.tvpsSclValues[4]*5+50 , "Standard" , "SEQ" );
            count++;
        }        
        if (Panel_TVPS4.tvpsChkValues[5]) {
            dataset.addValue( Panel_TVPS4.tvpsSclValues[5] , "Scaled" , "FGR" );
            dataset.addValue( Panel_TVPS4.tvpsSclValues[5]*5+50 , "Standard" , "FGR" );
            count++;
        } 
        if (Panel_TVPS4.tvpsChkValues[6]) {
            dataset.addValue( Panel_TVPS4.tvpsSclValues[6] , "Scaled" , "CLO" );
            dataset.addValue( Panel_TVPS4.tvpsSclValues[6]*5+50 , "Standard" , "CLO" );
            count++;
        }

        // on change la taille du graphique s'il n'y a pas beaucoup d'items'
        if (OrthoCotation.tvps4ChartPanel != null){
            if (count < 5) {
                OrthoCotation.tvps4ChartPanel.setSize(400, 250);
            }
            else
                OrthoCotation.tvps4ChartPanel.setSize(500, 300);
        }
        // mise à jour 
        updateVisibleScores () ;    
        
        
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

        Panel_TVPS4.jChgeColor.setEnabled(true);
    }
    
    public void changeColor(Color c) {
        currentColor = c ;
        CustomRenderer4.chgColor (c) ;
        updateGraph () ;
    }
    
    public void changeAspect (boolean titre, boolean subTitle, boolean rge) {
        //Titre
        if (titre) chart.setTitle(new TextTitle("TVPS-4 scores & percentiles", new Font (chart.getTitle().getFont().getFontName(), Font.BOLD , 17)));
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

class CustomRenderer4 extends BarRenderer {
    static Panel_TVPS4 panel ;
    static Color c ;
    Color lightRed = new Color(255, 51, 51);
    
    public CustomRenderer4 (Panel_TVPS4 panel, Color c) {
        this.c = c ; 
        this.panel = panel ;
    }
    
    public static void chgColor (Color col) {
        c  = col ;
    }
    
    public Paint getItemPaint(final int row, final int column) {
            
            if (panel.tvpsPctlValues[panel.transposeColumn[column]] <= 5) return lightRed;
            else return this.c ;
        }
}