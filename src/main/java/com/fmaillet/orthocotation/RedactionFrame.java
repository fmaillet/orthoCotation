/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fmaillet.orthocotation;

import java.awt.Desktop;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.Timer;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.icepdf.ri.common.SwingController;
import org.icepdf.ri.common.SwingViewBuilder;
import org.icepdf.ri.common.views.DocumentViewController;
import org.icepdf.ri.util.PropertiesManager;

/**
 *
 * @author Fred
 */
public class RedactionFrame extends JFrame implements ActionListener, WindowListener, FocusListener {
    
    final Path dst = Paths.get("document.tex");
    
    //Buttons
    JButton compile ;
    //PDF Viewer
    SwingController controller ;
    
    //To update "prénom" change
    JTextField childrenFirstName, childrenLastName ;
    String oldPrenomValue ;
    JTextArea intro, sm, moc ;
    boolean waitForChange = false;
    
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
        
        // Just to be able to update prenom
        Runnable taskUpdatePrenom = new Runnable() {
            public void run() {
                System.out.println ("old: " + oldPrenomValue + " new: " + childrenFirstName.getText()) ;
                if (waitForChange  || oldPrenomValue.contentEquals(childrenFirstName.getText())) return ;
                String replacedStr = intro.getText().replaceAll(oldPrenomValue, childrenFirstName.getText());
                intro.setText(null);
                intro.append(replacedStr);
                oldPrenomValue = childrenFirstName.getText() ;
            }
        };
        ScheduledExecutorService scheduler
                            = Executors.newSingleThreadScheduledExecutor();
        
        // Children name
        JLabel j1 = new JLabel ("Prénom :") ;
        j1.setBounds(130, 10, 90, 30);
        this.getContentPane().add(j1);
        oldPrenomValue = "Prénom" ;
        childrenFirstName = new JTextField (oldPrenomValue);
        childrenFirstName.setBounds(190, 10, 120, 30);
        childrenFirstName.setVisible(true);
        this.getContentPane().add(childrenFirstName);
        childrenFirstName.addFocusListener(this);
        JLabel j2 = new JLabel ("Nom :") ;
        j2.setBounds(320, 10, 90, 30);
        this.getContentPane().add(j2);
        childrenLastName = new JTextField ("Nom");
        childrenLastName.setBounds(370, 10, 120, 30);
        childrenLastName.setVisible(true);
        this.getContentPane().add(childrenLastName);
        // Add listeners
        childrenFirstName.getDocument().addDocumentListener(new DocumentListener() {
            Future<?> resultScheduled = null ;
            @Override
            public void insertUpdate(DocumentEvent e) {
                
                int delay = 3;
                if (resultScheduled == null)
                    resultScheduled = scheduler.schedule(taskUpdatePrenom, delay, TimeUnit.SECONDS);
                else if (resultScheduled.isDone())
                    resultScheduled = scheduler.schedule(taskUpdatePrenom, delay, TimeUnit.SECONDS);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                
            }
        });
        
        //Intro
        intro = new JTextArea(5, 20);
        JScrollPane scrollIntro = new JScrollPane(intro);
        scrollIntro.setBounds(10, 65, 800, 120);
        this.getContentPane().add(scrollIntro);
        scrollIntro.setOpaque(false); intro.setOpaque(false);
        scrollIntro.setBorder(new TitledBorder("Introduction"));
        scrollIntro.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        intro.append("Je vois Prénom dans le cadre de difficultés scolaires. \n");
        intro.append("Il n'y a pas d'antécédents visuels particuliers. Prénom dit bien voir et n'exprime pas de plainte visuelle.\n");
        //Sensorimoteur
        sm = new JTextArea(5, 20);
        JScrollPane scrollSm = new JScrollPane(sm);
        scrollSm.setBounds(10, 190, 800, 150);
        this.getContentPane().add(scrollSm);
        scrollSm.setBorder(new TitledBorder("Sensorimoteur"));
        scrollSm.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollSm.setOpaque(false); sm.setOpaque(false);
        sm.append("La vision binoculaire est normale. \n");
        sm.append("L'acuité visuelle avec correction est à 10/10ème ODG.\n");
        //Motricité oculaire
        moc = new JTextArea(5, 20);
        JScrollPane scrollMoc = new JScrollPane(moc);
        scrollMoc.setBounds(10, 345, 800, 150);
        this.getContentPane().add(scrollMoc);
        scrollMoc.setBorder(new TitledBorder("Motricité oculaire conjuguée"));
        scrollMoc.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollMoc.setOpaque(false); moc.setOpaque(false);
        moc.append("Le NSUCO rapporte une précision correcte des saccades et des poursuites. \n");
        moc.append("La fixation est normale et le réflexe vestibulo-oculaire semble à même d'en garantir la stabilité.\n");
    }
    
    private void changePrenomUpdate () {
        waitForChange = true ;
        System.out.println ("old: " + oldPrenomValue + " new: " + childrenFirstName.getText()) ;
        String replacedStr = intro.getText().replaceAll(oldPrenomValue, childrenFirstName.getText());
        intro.setText(null);
        intro.append(replacedStr);
        oldPrenomValue = childrenFirstName.getText() ;
        waitForChange = false ;
    }

    @Override
    public void focusGained(FocusEvent e) {
        
    }

    @Override
    public void focusLost(FocusEvent e) {
        Object source = e.getSource () ;
        if (source == childrenFirstName)
            changePrenomUpdate () ;
    }
    
    class RemindTask extends TimerTask {
        public void run() {
          System.out.println("Time's up!");
          
          //timer.cancel(); //Not necessary because we call System.exit
          
        }
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
            
            
            writer.write("\\usepackage{lipsum}"); writer.newLine();*/
            
            writePreamble (writer) ;
            writeStructure (writer) ;
            
            //writer.write("\\opening{}"); writer.newLine();
            
            writer.write(intro.getText()); writer.newLine();
            writer.write("\\vspace{0.5cm}"); writer.newLine();
            
            writer.write("\\begin{snugshade}Sensorimoteur\\end{snugshade}"); writer.newLine();
            writer.write("\\begin{wrapfigure}{r}{4.5cm}"); writer.newLine();
            //writer.write("\\caption{Ecarts à la norme}"); writer.newLine();
            writer.write("\\centering"); writer.newLine();
            writer.write("\\includegraphics[width=6cm, height=6cm]{polarChart.png}"); writer.newLine();
            writer.write("\\vspace{-70pt}"); writer.newLine();
            writer.write("\\end{wrapfigure}"); writer.newLine();
            writer.write(sm.getText()); writer.newLine();
            
            writer.write("\\begin{snugshade}Motricité oculaire conjuguée\\end{snugshade}"); writer.newLine();
            writer.write(moc.getText()); writer.newLine();
            writer.write("\\begin{snugshade}Perception visuelle et spatiale\\end{snugshade}"); writer.newLine();
            writer.write("\\lipsum[2]"); writer.newLine();
            writer.write("\\begin{snugshade}Conclusion\\end{snugshade}"); writer.newLine();
            writer.write("\\lipsum[2]"); writer.newLine();
            writer.write("\\closing{Respectueusement,}"); writer.newLine();
                  
            //writer.write("\\end{letter}"); writer.newLine();
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
            writer.write("\\documentclass[11pt,a4paper, french]{letter}"); writer.newLine();
            writer.write("\\usepackage[right=2cm, bottom=2cm, left=2.5cm, top=2cm]{geometry}"); writer.newLine();
            writer.write("\\usepackage{babel}"); writer.newLine();
            writer.write("\\usepackage[OT1]{fontenc}"); writer.newLine();
            writer.write("\\usepackage[latin1]{inputenc}"); writer.newLine();
            writer.write("\\usepackage{libertine}"); writer.newLine();
            writer.write("\\usepackage{graphicx}"); writer.newLine();
            writer.write("\\usepackage{wrapfig}"); writer.newLine();
            
            writer.write("\\usepackage{framed}"); writer.newLine();
            writer.write("\\usepackage{microtype}"); writer.newLine();
            writer.write("\\DisableLigatures{encoding = *, family = * }"); writer.newLine();
            writer.write("\\usepackage{datetime}"); writer.newLine();
            writer.write("\\usepackage{lipsum}"); writer.newLine();
            writer.write("\\usepackage{fancyhdr,lastpage, framed, color, fancybox}"); writer.newLine();
            writer.write("\\definecolor{Navy}{RGB}{50,90,122}"); writer.newLine();
            writer.write("\\definecolor{shadecolor}{rgb}{1,0.8,0.3}"); writer.newLine();
            
        } catch (IOException ex) {
            System.out.println ("Error writing document.tex preamble");
        }
    }
    
    private void writeStructure(BufferedWriter writer) {
        
        DateFormat df = new SimpleDateFormat("dd MMM yyyy");
        Date b = (Date) OrthoCotation.dateBilan.getModel().getValue() ;
        Date n = (Date) OrthoCotation.dateBirth.getModel().getValue() ;
        try {
            writer.write("\\pagestyle{fancy}"); writer.newLine();
            writer.write("\\fancyhf{}"); writer.newLine();
            writer.write("\\renewcommand{\\headrulewidth}{0pt}"); writer.newLine();
            writer.write("\\cfoot{Page \\thepage \\hspace{1pt}/\\pageref{LastPage}}"); writer.newLine(); // fonts
            writer.write("\\begin{document}"); writer.newLine();
            
            writer.write("\\colorbox{Navy}{"); writer.newLine();
            writer.write("\\parbox[t]{\\linewidth}{"); writer.newLine();
            writer.write("\\vspace*{14pt}"); writer.newLine();
            writer.write("\\hfill \\color{white} \\textsc{\\huge BILAN NEUROVISUEL}"); writer.newLine();
            writer.write("\\vspace*{14pt}"); writer.newLine();
            writer.write("}}"); writer.newLine();
            writer.write("\\vspace{28pt}"); writer.newLine();
            //writer.write("\\thispagestyle{fancy}"); writer.newLine();
            writer.write("\\date{\\begin{flushright}\\today\\end{flushright}}"); writer.newLine();
            /*if (n != null) 
                writer.write("\\begin{letter}{\\Large \\bfseries " + childrenFirstName.getText() + " " + childrenLastName.getText() + " (" + df.format(n) + ")"); 
            else
                writer.write("\\begin{letter}{\\Large \\bfseries " + childrenFirstName.getText() + " " + childrenLastName.getText() + " (" + "???" + ")");
            writer.newLine();*/
            
            writer.write("\\\\ " + "\\large Bilan réalisé le " + df.format(b) ); writer.newLine();
            writer.write("\\\\ "); writer.newLine();
            
            
            if (OrthoCotation.user.nom != null) {
                writer.write("\\signature{"); writer.newLine();
                writer.write(OrthoCotation.user.prenom + " " + OrthoCotation.user.nom + " \\\\"); writer.newLine();
                writer.write("\\textbf{" + OrthoCotation.user.activite + "} \\\\"); writer.newLine();
                writer.write("\\small{" + OrthoCotation.user.adr1 + "} \\\\"); writer.newLine();
                if (OrthoCotation.user.adr1 != "")
                    writer.write("\\small{" + OrthoCotation.user.adr2 + "} \\\\"); writer.newLine();
                writer.write("\\small{E: john@initech.com | M: (000) 111 1111}"); writer.newLine();
                writer.write("}"); writer.newLine();
            }
            else {
                writer.write("\\signature{"); writer.newLine();
                writer.write("John Smith \\\\"); writer.newLine();
                writer.write("\\textbf{Orthoptiste} \\\\"); writer.newLine();
                writer.write("\\small{123, avenue de Toulouse} \\\\"); writer.newLine();
                writer.write("\\small{E: john@demo.com | M: (000) 111 1111}"); writer.newLine();
                writer.write("}"); writer.newLine();
            }
            
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

