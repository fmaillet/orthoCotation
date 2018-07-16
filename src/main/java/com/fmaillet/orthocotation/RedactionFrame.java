/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fmaillet.orthocotation;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.icepdf.ri.common.SwingController;
import org.icepdf.ri.common.SwingViewBuilder;
import org.icepdf.ri.common.views.DocumentViewController;
import org.icepdf.ri.util.PropertiesManager;

/**
 *
 * @author Fred
 */
public class RedactionFrame extends JFrame implements ActionListener, WindowListener {
    
    final Path dst = Paths.get("document.tex");
    
    //Buttons
    JButton compile ;
    //PDF Viewer
    SwingController controller ;
    
    public RedactionFrame () {
        setTitle ("orthoCotation ("+OrthoCotation.getSoftVersion()+") - MODE DEMO (NON CONNECTE)");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(null);  setSize(1300, 800);
        setLocationRelativeTo(null);
        
        addWindowListener(this);
        
        System.getProperties().put("org.icepdf.core.scaleImages", "false"); 
        System.getProperties().put("org.icepdf.core.imageReference","smoothScaled");
        System.getProperties().put("org.icepdf.core.target.dither", "VALUE_DITHER_DISABLE"); 
        System.getProperties().put("org.icepdf.core.target.fractionalmetrics", "VALUE_FRACTIONALMETRICS_OFF"); 
        System.getProperties().put("org.icepdf.core.target.interpolation", "VALUE_INTERPOLATION_NEAREST_ NEIGHBOR");
        System.getProperties().put("org.icepdf.core.screen.interpolation", "VALUE_INTERPOLATION_NEAREST_NEIGHBOR");
        System.getProperties().put("org.icepdf.core.awtFontLoading", "true");
        
    }
    
    public void addComponents () {
        //Add compile button
        compile = new JButton("Compile") ;
        compile.setBounds(10, 10, 100, 30);
        getContentPane().add(compile);
        compile.addActionListener(this);
        
        // build a component controller
        controller = new SwingController();
        
        //Customize toolbar
        Properties props = new Properties();
        props.setProperty( PropertiesManager.PROPERTY_SHOW_UTILITY_SAVE,
               "false" );
        props.setProperty(PropertiesManager.PROPERTY_SHOW_TOOLBAR_ANNOTATION, 
              "false" );
        props.setProperty("application.showLocalStorageDialogs",  "false" );
        
        PropertiesManager properties =
        new PropertiesManager(System.getProperties(), props,
                               ResourceBundle.getBundle(PropertiesManager.DEFAULT_MESSAGE_BUNDLE));
        
        //properties.setBoolean("application.showLocalStorageDialogs", Boolean.FALSE);
        properties.set("application.showLocalStorageDialogs", "no");
        //properties.set(PropertiesManager.PROPERTY_SHOW_TOOLBAR_FORMS, "false");
        //properties.set("application.showLocalStorageDialogs", "false");
        properties.setBoolean(PropertiesManager.PROPERTY_SHOW_TOOLBAR_ANNOTATION, Boolean.FALSE);
        properties.setBoolean(PropertiesManager.PROPERTY_SHOW_TOOLBAR_FIT, Boolean.FALSE);
        properties.setBoolean(PropertiesManager.PROPERTY_SHOW_UTILITY_SEARCH, Boolean.FALSE);
        //properties.setBoolean(PropertiesManager.PROPERTY_HIDE_UTILITYPANE, Boolean.TRUE);
        //properties.setBoolean(PropertiesManager.PROPERTY_SHOW_TOOLBAR_UTILITY, Boolean.FALSE);
        properties.setBoolean(PropertiesManager.PROPERTY_SHOW_TOOLBAR_ROTATE, Boolean.FALSE);
        //properties.setBoolean(PropertiesManager.PROPERTY_SHOW_TOOLBAR_ANNOTATION, Boolean.FALSE);
        

        SwingViewBuilder factory = new SwingViewBuilder(controller, properties);
        
        // add interactive mouse link annotation support via callback
        controller.getDocumentViewController().setAnnotationCallback(
                new org.icepdf.ri.common.MyAnnotationCallback(
                        controller.getDocumentViewController()));

        JPanel viewerComponentPanel = factory.buildViewerPanel();
        viewerComponentPanel.setBounds(2*getContentPane().getWidth()/3,0, getContentPane().getWidth()/3, getContentPane().getHeight());
        getContentPane().add(viewerComponentPanel);
        
        //controller.openDocument("document.pdf");
        //controller.setZoom(DocumentViewController.PAGE_FIT_WINDOW_WIDTH);
        //controller.setPageFitMode(DocumentViewController.PAGE_FIT_WINDOW_WIDTH, true);
        //controller.setToolBarVisible(false);
        
        
        
    }
    
    private void createTexFile () {
        // NOTE: you should really be using UTF-8
        final Charset charset = Charset.defaultCharset();
        //final Path dst = Paths.get("document.tex");
        try {
            final BufferedWriter writer = Files.newBufferedWriter(dst, charset);
            /*writer.write("\\documentclass[12pt,a4paper, oneside,french]{article}"); writer.newLine();
            writer.write("\\usepackage{babel}"); writer.newLine();
            writer.write("\\usepackage[utf8]{inputenc}"); writer.newLine();
            writer.write("\\usepackage{libertine}"); writer.newLine();
            writer.write("\\usepackage{graphicx}"); writer.newLine();
            writer.write("\\usepackage{microtype}"); writer.newLine();
            writer.write("\\DisableLigatures{encoding = *, family = * }"); writer.newLine();
            
            writer.write("\\usepackage{datetime}"); writer.newLine();
            writer.write("\\usepackage{lipsum}"); writer.newLine();*/
            
            writePreamble (writer) ;
            writeStructure (writer) ;
            
            writer.write("\\title{Bilan Orthoptique}"); writer.newLine();
            writer.write("\\author{" + OrthoCotation.user.titre + " " + OrthoCotation.user.nom + " " + OrthoCotation.user.prenom
                    + "}"); writer.newLine();
            writer.write("\\date{\\today}"); writer.newLine();
            
            writer.write("\\begin{document}"); writer.newLine();
            writer.write("\\maketitle"); writer.newLine();
            writer.write("\\currenttime"); writer.newLine();
            writer.write("\\\\"); writer.newLine();
            writer.write("\\lipsum[1-3]. fited tty."); writer.newLine();
            
            writer.write("\\begin{figure}"); writer.newLine();
            writer.write("\\centering"); writer.newLine();
            writer.write("\\includegraphics[scale=0.5]{polarChart.png}"); writer.newLine();
            writer.write("\\end{figure}"); writer.newLine();
            writer.write("\\lipsum[1]"); writer.newLine();
            writer.write("\\end{document}"); writer.newLine();
            writer.write(""); writer.newLine();
            writer.close(); 
        } catch (IOException ex) { 
            JOptionPane.showMessageDialog(this, "Erreur à la création du document !!",
                "document.tex", JOptionPane.WARNING_MESSAGE);
            System.out.println (ex) ;
        }   
        System.out.println ("Creation document.tex ok") ;
    }
    
    private void writePreamble(BufferedWriter writer) {
        try {
            writer.write("\\documentclass[12pt,a4paper, oneside,french]{article}"); writer.newLine();
            writer.write("\\usepackage{babel}"); writer.newLine();
            writer.write("\\usepackage[OT1]{fontenc}"); writer.newLine();
            writer.write("\\usepackage[utf8]{inputenc}"); writer.newLine();
            writer.write("\\usepackage{libertine}"); writer.newLine();
            writer.write("\\usepackage{graphicx}"); writer.newLine();
            writer.write("\\usepackage{microtype}"); writer.newLine();
            writer.write("\\DisableLigatures{encoding = *, family = * }"); writer.newLine();
            writer.write("\\usepackage{datetime}"); writer.newLine();
            writer.write("\\usepackage{lipsum}"); writer.newLine();
            writer.write("\\usepackage{fancyhdr,lastpage, framed, color, fancybox}"); writer.newLine();
            
        } catch (IOException ex) {
            System.out.println ("Error writing document.tex preamble");
        }
    }
    
    private void writeStructure(BufferedWriter writer) {
        try {
            //writer.write("\\usepackage{gfsdidot}"); writer.newLine(); // fonts
            writer.write("\\pagestyle{empty}"); writer.newLine();
            writer.write("\\makeatletter"); writer.newLine();
            writer.write("\\newcommand{\\vhrulefill}[1]{\\leavevmode\\leaders\\hrule\\@height#1\\hfill \\kern\\z@}"); writer.newLine();
            writer.write("\\makeatother"); writer.newLine();
            
            writer.write("\\usepackage{geometry}"); writer.newLine();
            writer.write("\\geometry{top=1cm,bottom=1.5cm,left=3cm,right=3cm}"); writer.newLine();
            
        } catch (IOException ex) {
            System.out.println ("Error writing document.tex preamble");
        }
    }
    
    private void showCrossPlatformPDF () {
        //final Path pdf = Paths.get("document.pdf");
        File pdfFile = new File("document.pdf");
        
        try {
            if (pdfFile.exists()) {

			if (Desktop.isDesktopSupported()) {
				Desktop.getDesktop().open(pdfFile);
			} else {
				System.out.println("Awt Desktop is not supported!");
			}

		} else {
			System.out.println("File is not exists!");
		}
        } catch (Exception ex) {}
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Source ?
        Object source = e.getSource () ;
        //A Propos
        if (source == compile) {
            createTexFile () ;
            try {
                Process p = Runtime.getRuntime().exec("pdflatex document.tex");
                // any error message?
                StreamGobbler errorGobbler = new 
                    StreamGobbler(p.getErrorStream(), "ERROR");            

                // any output?
                StreamGobbler outputGobbler = new 
                    StreamGobbler(p.getInputStream(), "OUTPUT");

                // kick them off
                errorGobbler.start();
                outputGobbler.start();
                
                // any error???
                int exitVal = p.waitFor();
                System.out.println("ExitValue: " + exitVal);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Erreur à la compilation du document !!",
                    "pdflatex :", JOptionPane.WARNING_MESSAGE);
            } catch (InterruptedException ex) {
                System.out.println ("pdflatex runtime error !!") ;
            }
            controller.openDocument("document.pdf");
            System.out.println ("Load document.pdf ok") ;
            controller.setPageFitMode(DocumentViewController.PAGE_FIT_WINDOW_WIDTH, true);
            //controller.updateDocumentView();
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {
        
        
        //showCrossPlatformPDF () ;
    }

    @Override
    public void windowClosing(WindowEvent e) {
        //Delete temps files
        boolean result = false;
        try {
            result = Files.deleteIfExists(dst);
        } catch (IOException ex) {
            Logger.getLogger(RedactionFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        //System.out.println (result) ;
    }

    @Override
    public void windowClosed(WindowEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowIconified(WindowEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowActivated(WindowEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        //System.out.println (result) ;
    }
    
}

class StreamGobbler extends Thread
{
    InputStream is;
    String type;
    
    StreamGobbler(InputStream is, String type)
    {
        this.is = is;
        this.type = type;
    }
    
    public void run()
    {
        try
        {
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String line=null;
            while ( (line = br.readLine()) != null)
                System.out.println(type + ">" + line);    
            } catch (IOException ioe)
              {
                ioe.printStackTrace();  
              }
    }
}