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
    static public Values phorieP ;
    static public Values phorieL ;
    static Values fusionCP ;
    static public Values fusionDP ;
    static public Values fusionCL ;
    static public Values fusionDL ;
    static Values ppa ;
    static Values ppc ;
    static Values aca ;
    static Values fdv, fda ;
    
    static Age patientAge ;
    static boolean homme ;
    
    public BaseDSValues () {
        phorieP = new Values () ;
        phorieL = new Values () ;
        fusionDP = new Values () ;
        fusionCP = new Values () ;
        fusionDL = new Values () ;
        fusionCL = new Values () ;
        aca = new Values () ;
        ppc = new Values () ;
        ppa = new Values () ;
        fdv = new Values () ;
        fda = new Values () ;
        
        patientAge = new Age () ;
        homme = true ;
    }
    
    public double updateFDV (int a, boolean s) {
        double ds =  ((double) a - 15.0)  / 3.0 ;
        fdv.raw = (double) a ;
        fdv.ds = ds ;
        fdv.selected = s ;
        //maj du graph
        OrthoCotation.polarChart.updateDataset () ;
        return ds ;
    }
    
    public double updateFDA (int p, boolean s) {
        double ds ;
        if (patientAge.years <= 6)
            ds = ((double) p - 3.0 ) / 2.5 ;
        else if (patientAge.years == 7)
            ds = ((double) p - 3.5 ) / 2.5 ;
        else if (patientAge.years >= 8 & patientAge.years <= 12)
            ds = ((double) p - 5.0 ) / 2.5 ;
        else
            ds = ((double) p - 10.0 ) / 5.0 ;
        fda.raw = (double) p ;
        fda.ds = ds ;
        fda.selected = s ;
        //maj du graph
        OrthoCotation.polarChart.updateDataset () ;
        return ds ;
    }
    
    public double updateACA (double a, boolean s) {
        double ds = - Math.abs( a - 4.0 ) / 2.0 ;
        aca.raw = (double) a ;
        aca.ds = ds ;
        aca.selected = s ;
        //maj du graph
        OrthoCotation.polarChart.updateDataset () ;
        return ds ;
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
        double ds ;
        if (patientAge.years <= 12)
            ds = ((double) p - 12.0 ) / 5.0 ;
        else
            ds = ((double) p - 13.0 ) / 6.0 ;
        fusionDP.raw = (double) p ;
        fusionDP.ds = ds ;
        fusionDP.selected = s ;
        //maj du graph
        OrthoCotation.polarChart.updateDataset () ;
        return ds ;
    }
    
    public double updateFusionCP (int p, boolean s) {
        double ds ;
        if (patientAge.years<= 12 )
            ds = ((double) p - 23.0 ) / 8.0 ;
        else
            ds = ((double) p - 19.0 ) / 9.0 ;
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
        double ds = ( 2.5 - p ) / 2.5 ;
        ppc.raw = (double) p ;
        ppc.ds = ds ;
        ppc.selected = s ;
        //maj du graph
        OrthoCotation.polarChart.updateDataset () ;
        return ds ;
    }
    
    public double updatePPA (double p, boolean s) {
        double attendu = (18 - patientAge.years / 3) ;
        double ds = ( p - attendu ) / 2.0 ;
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