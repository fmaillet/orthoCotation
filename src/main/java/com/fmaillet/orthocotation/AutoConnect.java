package com.fmaillet.orthocotation;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Fred
 */
public class AutoConnect extends Thread {
    public AutoConnect () {
        setName ("AutoConnect") ;
    }
    
    Connection laConnection = null ;
    Statement transmission ;
    ResultSet leResultat ;
    String nom, prenom, titre, message, version, activite, dateLimite ;
    
    @Override
    public void run () {
        
          
        int n = 0 ;
        
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date dt = new java.util.Date();
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            DriverManager.setLoginTimeout(5);
            laConnection = DriverManager.getConnection ("jdbc:mysql://fredericmaillet.fr/fmaillet_professionnels", "fmaillet_fredo", "mastercog");
            if (laConnection != null) {
            transmission = laConnection.createStatement (ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE) ;
            //On cherche la macAdress
            leResultat = transmission.executeQuery ("select ADELI, PWD, JETON, LASTCONNECTION, LIMITE, NOM, PRENOM, TITRE, ACTIVITE, MSG, ADR1, ADR2, CP, VILLE, TEL1, TEL2, MAIL"
                    + " from Pro where MACADR = '"+ OrthoCotation.user.macaddress+"'") ;
            
            while (leResultat.next()) {
                n++ ;
                //Check quickly if there's only one result
                if (leResultat.next()) break ;
                else leResultat.previous() ;
                //à ce niveau là on sait qu'il n'y a qu'une macAdress correspondante
                //Prendre le jeton ! si on ne peut pas, sortir
                if ( ! MySQLClass.getJeton (leResultat) ) break ;
                //On vérifie la date limite...
                dateLimite = leResultat.getString("LIMITE") ;
                sdf = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    Date d = sdf.parse(dateLimite);
                    if (! d.after(dt)) //
                        break ;
                } catch (ParseException e) {}
                
                //Récupérer les données
                OrthoCotation.user.nom = leResultat.getString("NOM") ;
                OrthoCotation.user.prenom = leResultat.getString("PRENOM") ;
                OrthoCotation.user.titre = leResultat.getString("TITRE") ;
                OrthoCotation.user.activite = leResultat.getString("ACTIVITE") ;
                message = "" ;
                message = leResultat.getString("MSG") ;
                //Coordonnées
                OrthoCotation.user.adr1 = leResultat.getString("ADR1") ;
                OrthoCotation.user.adr2 = leResultat.getString("ADR2") ;
                OrthoCotation.user.cp = leResultat.getString("CP") ;
                OrthoCotation.user.ville = leResultat.getString("VILLE") ;
                OrthoCotation.user.tel1 = leResultat.getString("TEL1") ;
                OrthoCotation.user.tel2 = leResultat.getString("TEL2") ;
                OrthoCotation.user.mail = leResultat.getString("MAIL") ;
                OrthoCotation.user.adeli = leResultat.getString("ADELI") ;
                OrthoCotation.user.code = leResultat.getString("PWD") ;
                //Mise à jour date de la dernière connection
                String currentTime = sdf.format(dt);
                leResultat.updateString("LASTCONNECTION", currentTime);
                leResultat.updateRow () ;
                
                //On met à jour la fenêtre :
                OrthoCotation.connected () ;
                
            }
        }//fin de la "if laconnection != null
        } catch (SQLException e) {  } 

        //On ferme la connection
        try {
            if (laConnection != null) laConnection.close();
            if (transmission != null) transmission.close () ;
            if (leResultat != null) leResultat.close () ;
        } catch (SQLException e) {}
        
        
    }
    
    
}
