/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fmaillet.orthocotation;

/**
 *
 * @author Fred
 */
public class BaseDSValues {
    static Values phorieP ;
    static Values phorieL ;
    static Values fusionCP ;
    static Values fusionDP ;
    static Values fusionCL ;
    static Values fusionDL ;
    static Values ppa ;
    static Values ppc ;
    
    static Age patientAge ;
    
    public BaseDSValues () {
        phorieP = new Values () ;
        phorieL = new Values () ;
        fusionDP = new Values () ;
        fusionCP = new Values () ;
        fusionDL = new Values () ;
        fusionCL = new Values () ;
        ppc = new Values () ;
        ppa = new Values () ;
        
        patientAge = new Age () ;
    }
    
    public double updatePhorieP (int p, boolean s) {
        double ds = - Math.abs((double) p + 3.0 ) / 3.0 ;
        phorieP.raw = (double) p ;
        phorieP.ds = ds ;
        phorieP.selected = s ;
        //maj du graph
        OrthoCotation.polarChart.updateDataset () ;
        return ds ;
    }
    
    public double updatePhorieL (int p, boolean s) {
        double ds = - Math.abs((double) p + 1.0 ) / 2.0 ;
        phorieL.raw = (double) p ;
        phorieL.ds = ds ;
        phorieL.selected = s ;
        //maj du graph
        OrthoCotation.polarChart.updateDataset () ;
        return ds ;
    }
    
    public double updateFusionDP (int p, boolean s) {
        double ds = ((double) p - 12.0 ) / 5.0 ;
        fusionDP.raw = (double) p ;
        fusionDP.ds = ds ;
        fusionDP.selected = s ;
        //maj du graph
        OrthoCotation.polarChart.updateDataset () ;
        return ds ;
    }
    
    public double updateFusionCP (int p, boolean s) {
        double ds = ((double) p - 23.0 ) / 8.0 ;
        fusionCP.raw = (double) p ;
        fusionCP.ds = ds ;
        fusionCP.selected = s ;
        //maj du graph
        OrthoCotation.polarChart.updateDataset () ;
        return ds ;
    }
    public double updateFusionCL (int p, boolean s) {
        double ds = ((double) p - 11.0 ) / 7.0 ;
        fusionCL.raw = (double) p ;
        fusionCL.ds = ds ;
        fusionCL.selected = s ;
        //maj du graph
        OrthoCotation.polarChart.updateDataset () ;
        return ds ;
    }
    
    public double updateFusionDL (int p, boolean s) {
        double ds = ((double) p - 7.0 ) / 3.0 ;
        fusionDL.raw = (double) p ;
        fusionDL.ds = ds ;
        fusionDL.selected = s ;
        //maj du graph
        OrthoCotation.polarChart.updateDataset () ;
        return ds ;
    }
    
    public double updatePPC (double p, boolean s) {
        double ds = ( p - 2.5 ) / 2.5 ;
        ppc.raw = (double) p ;
        ppc.ds = ds ;
        ppc.selected = s ;
        //maj du graph
        OrthoCotation.polarChart.updateDataset () ;
        return ds ;
    }
    
    public double updatePPA (int p, boolean s) {
        double ds = ((double) p - 12.0 ) / 8.0 ;
        ppa.raw = (double) p ;
        ppa.ds = ds ;
        ppa.selected = s ;
        //maj du graph
        OrthoCotation.polarChart.updateDataset () ;
        return ds ;
    }
    
}

class Values {
    boolean selected ;
    double raw;
    double ds;
}

class Age {
    int years ;
    int months ;
    int days ;
}