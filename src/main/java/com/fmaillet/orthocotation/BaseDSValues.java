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
    static Values fusionCp ;
    static Values fusionDp ;
    static Values fusionCl ;
    static Values fusionDl ;
    static Values ppa ;
    static Values ppc ;
    
    public BaseDSValues () {
        phorieP = new Values () ;
        phorieL = new Values () ;
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
        double ds = - Math.abs((double) p + 3.0 ) / 3.0 ;
        phorieL.raw = (double) p ;
        phorieL.ds = ds ;
        phorieL.selected = s ;
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