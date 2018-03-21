package com.fmaillet.orthocotation;

import com.fmaillet.orthocotation.ColorChooserButton.ColorChangedListener;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import org.apache.commons.math3.distribution.NormalDistribution;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Fred
 */
public class Panel_TVPS extends javax.swing.JPanel {

    public static int tvpsStdValues[] = new int[7] ;
    public static int tvpsPctlValues[] = new int[7] ;
    static public ColorChooserButton jChgeColor ;
    
    int tvps_DIS[][] = { {7, 8, 9, 10, 11, 12, 13, 14, 16, 17, 18, 19, 19, 19, 19, 19}, //4.0
                         {5, 7, 8, 9, 10, 11, 13, 14, 16, 17, 18, 19, 19, 19, 19, 19},  //4.6
                         {4, 6, 7, 8, 10, 11, 12, 13, 15, 16, 18, 19, 19, 19, 19, 19},  //5.0
                         {3, 5, 7, 8, 9, 10, 11, 12, 14, 15, 17, 19, 19, 19, 19, 19},   //5.6
                         {3, 4, 6, 7, 8, 10, 11, 12, 13, 14, 16, 18, 19, 19, 19, 19},   //6.0
                         {2, 4, 5, 7, 8, 9, 10, 11, 12, 14, 15, 17, 19, 19, 19, 19},    //6.6
                         {2, 3, 5, 6, 7, 8, 9, 10, 12, 13, 14, 16, 18, 19, 19, 19},     //7.0
                         {1, 3, 4, 6, 7, 8, 9, 10, 11, 12, 14, 15, 17, 18, 19, 19},     //7.5
                         {1, 2, 4, 5, 6, 7, 8, 9, 10, 12, 13, 15, 16, 18, 19, 19},      //8.0
                         {1, 2, 3, 5, 6, 7, 8, 9, 10, 11, 12, 14, 15, 18, 19, 19},      //8.6
                         {0, 1, 3, 4, 5, 6, 7, 8, 9, 11, 12, 13, 15, 17, 19, 19},       //9.0
                         {0, 1, 2, 4, 5, 6, 7, 8, 9, 10, 11, 13, 14, 16, 18, 19},        //9.5
                         {0, 0, 1, 3, 4, 5, 6, 7, 8, 9, 10, 12, 13, 15, 18, 19},        //10.0
                         {0, 0, 1, 2, 3, 4, 6, 6, 7, 8, 9, 11, 12, 14, 16, 18},         //11.0
                         {0, 0, 0, 1, 3, 4, 5, 6, 7, 8, 9, 10, 12, 13, 15, 18},         //12.0
                         {0, 0, 0, 1, 2, 3, 5, 5, 6, 7, 8, 10, 11, 12, 14, 17},         //13.0
                         {0, 0, 0, 0, 1, 3, 4, 5, 6, 7, 8, 9, 10, 12, 14, 17},          //14.0
                         {0, 0, 0, 0, 1, 2, 3, 4, 5, 6, 7, 9, 10, 11, 13, 16},          //15.0
                         {0, 0, 0, 0, 0, 1, 3, 4, 5, 6, 7, 8, 10, 11, 13, 16},          //16.0
                         {0, 0, 0, 0, 0, 1, 2, 3, 4, 5, 6, 8, 9, 10, 12, 15},           //17.0
                         {0, 0, 0, 0, 0, 1, 2, 3, 4, 5, 6, 7, 8, 10, 12, 14}            //18.0
                        };
    int tvps_MEM[][] = { {6, 7, 9, 10, 11, 12, 13, 15, 16, 17, 18, 19, 19, 19, 19, 19}, //4.0
                         {5, 6, 8, 10, 11, 12, 13, 15, 16, 17, 18, 19, 19, 19, 19, 19}, //4.6
                         {3, 5, 7, 8, 10, 11, 12, 13, 15, 16, 17, 18, 19, 19, 19, 19},  //5.0
                         {2, 4, 6, 7, 8, 10, 11, 12, 13, 15, 16, 18, 19, 19, 19, 19},   //5.6
                         {2, 3, 5, 6, 7, 9, 10, 11, 12, 14, 15, 17, 18, 19, 19, 19},    //6.0
                         {1, 2, 4, 5, 6, 8, 9, 10, 11, 13, 14, 16, 18, 19, 19, 19},     //6.6
                         {1, 2, 3, 4, 6, 7, 8, 9, 10, 12, 13, 15, 17, 19, 19, 19},      //7.0
                         {0, 1, 2, 4, 5, 6, 7, 8, 9, 11, 12, 14, 16, 18, 19, 19},       //7.6
                         {0, 1, 2, 3, 4, 5, 7, 8, 9, 10, 11, 13, 15, 17, 19, 19},       //8.0
                         {0, 0, 1, 2, 4, 5, 6, 7, 8, 9, 11, 12, 14, 16, 18, 19},        //8.6
                         {0, 0, 1, 2, 3, 4, 5, 7, 8, 9, 10, 12, 14, 16, 18, 19},        //9.0
                         {0, 0, 0, 1, 3, 4, 5, 6, 7, 8, 9, 11, 13, 15, 17, 18},         //9.6
                         {0, 0, 0, 1, 2, 3, 4, 6, 7, 8, 9, 10, 12, 14, 16, 18},         //10.0
                         {0, 0, 0, 0, 1, 3, 4, 5, 6, 7, 8, 10, 11, 13, 15, 17},         //11.0
                         {0, 0, 0, 0, 1, 2, 3, 4, 5, 6, 8, 9, 10, 12, 14, 17},          //12.0
                         {0, 0, 0, 0, 0, 1, 2, 3, 5, 6, 7, 8, 10, 12, 14, 16},          //13.0
                         {0, 0, 0, 0, 0, 1, 2, 3, 4, 5, 7, 8, 9, 11, 13, 16},           //14.0
                         {0, 0, 0, 0, 0, 0, 1, 2, 3, 5, 6, 7, 9, 10, 12, 15},           //15.0
                         {0, 0, 0, 0, 0, 0, 1, 2, 3, 4, 6, 7, 8, 10, 12, 15},           //16.0
                         {0, 0, 0, 0, 0, 0, 0, 1, 2, 4, 5, 6, 8, 9, 11, 14},            //17.0
                         {0, 0, 0, 0, 0, 0, 0, 1, 2, 3, 5, 6, 7, 9, 11, 14}             //18.0
                        };
    
    int tvps_SPA[][] = { {8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 19, 19, 19, 19}, //4.0
                         {6, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 19, 19, 19},  //4.6
                         {5, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 19, 19, 19, 19},   //5.0
                         {4, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 17, 19, 19, 19, 19},    //5.6
                         {3, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 16, 18, 19, 19, 19},     //6.0
                         {2, 4, 5, 7, 8, 9, 9, 10, 11, 12, 14, 15, 17, 18, 19, 19},      //6.6
                         {1, 3, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 16, 18, 19, 19},      //7.0
                         {0, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 15, 17, 19, 19},       //7.6
                         {0, 2, 4, 5, 6, 7, 7, 8, 9, 10, 11, 13, 14, 16, 18, 19},        //8.0
                         {0, 2, 3, 4, 5, 6, 7, 8, 9, 9, 10, 12, 13, 15, 18, 19},         //8.6
                         {0, 1, 3, 4, 5, 6, 6, 7, 8, 9, 10, 11, 12, 15, 17, 18},         //9.0
                         {0, 1, 2, 3, 4, 5, 6, 7, 7, 8, 9, 10, 11, 14, 16, 18},          //9.6
                         {0, 1, 2, 3, 4, 5, 5, 6, 7, 8, 9, 10, 11, 13, 15, 17},          //10.0
                         {0, 0, 1, 2, 3, 4, 5, 6, 6, 7, 8, 9, 10, 12, 14, 16},           //11.0
                         {0, 0, 0, 1, 2, 3, 4, 5, 6, 6, 7, 8, 10, 11, 13, 16},           //12.0
                         {0, 0, 0, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 12, 15},            //13.0
                         {0, 0, 0, 0, 0, 2, 3, 4, 5, 5, 6, 7, 8, 10, 12, 15},            //14.0
                         {0, 0, 0, 0, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 14},             //15.0
                         {0, 0, 0, 0, 0, 0, 1, 2, 3, 4, 5, 6, 7, 8, 10, 13},             //16.0
                         {0, 0, 0, 0, 0, 0, 0, 1, 2, 4, 5, 6, 7, 8, 10, 13},             //17.0
                         {0, 0, 0, 0, 0, 0, 0, 0, 1, 3, 4, 5, 6, 7, 9, 11}               //18.0
                        };
    
    int tvps_CON[][] = { {7, 9, 10, 11, 12, 14, 15, 16, 18, 19, 19, 19, 19, 19, 19, 19}, //4.0
                         {5, 7, 9, 10, 11, 13, 14, 15, 17, 18, 19, 19, 19, 19, 19, 19},  //4.6
                         {4, 6, 7, 9, 10, 12, 13, 14, 16, 17, 18, 19, 19, 19, 19, 19},   //5.0
                         {3, 5, 6, 8, 10, 11, 12, 14, 15, 16, 18, 19, 19, 19, 19, 19},    //5.6
                         {2, 4, 6, 8, 9, 10, 11, 13, 14, 16, 17, 18, 19, 19, 19, 19},     //6.0
                         {2, 4, 5, 7, 8, 9, 11, 12, 13, 15, 16, 18, 19, 19, 19, 19},      //6.6
                         {1, 3, 5, 6, 7, 9, 10, 11, 13, 14, 15, 17, 18, 19, 19, 19},      //7.0
                         {1, 2, 4, 5, 7, 8, 9, 10, 12, 13, 15, 16, 18, 19, 19, 19},       //7.6
                         {1, 2, 4, 5, 6, 7, 9, 10, 11, 12, 14, 15, 17, 19, 19, 19},        //8.0
                         {1, 2, 3, 4, 6, 7, 8, 9, 10, 12, 13, 15, 17, 18, 19, 19},         //8.6
                         {0, 1, 3, 4, 5, 6, 7, 8, 9, 10, 11, 13, 14, 16, 18, 19, 19},         //9.0
                         {0, 1, 2, 4, 5, 6, 7, 8, 9, 10, 12, 14, 15, 17, 18, 19},          //9.6
                         {0, 0, 1, 3, 4, 5, 6, 8, 9, 10, 11, 13, 14, 16, 18, 19},          //10.0
                         {0, 0, 1, 2, 3, 5, 6, 7, 8, 9, 10, 12, 14, 16, 17, 18},           //11.0
                         {0, 0, 0, 1, 3, 4, 5, 6, 8, 9, 10, 11, 13, 15, 16, 18},           //12.0
                         {0, 0, 0, 1, 2, 3, 5, 6, 7, 8, 9, 10, 12, 14, 16, 18},            //13.0
                         {0, 0, 0, 1, 2, 3, 4, 5, 7, 8, 9, 10, 11, 13, 15, 17},            //14.0
                         {0, 0, 0, 0, 1, 2, 4, 5, 6, 7, 8, 10, 11, 12, 15, 17},             //15.0
                         {0, 0, 0, 0, 1, 2, 3, 5, 6, 7, 8, 9, 10, 12, 14, 17},             //16.0
                         {0, 0, 0, 0, 1, 2, 3, 4, 5, 7, 8, 9, 10, 11, 14, 16},             //17.0
                         {0, 0, 0, 0, 0, 1, 2, 4, 5, 6, 7, 8, 10, 11, 13, 16}               //18.0
                        };
        
    int tvps_SEQ[][] = { {9, 10, 11, 12, 13, 14, 15, 17, 18, 19, 19, 19, 19, 19, 19, 19}, //4.0
                         {7, 8, 10, 11, 12, 13, 14, 15, 17, 18, 19, 19, 19, 19, 19, 19},  //4.6
                         {6, 7, 9, 10, 11, 12, 13, 14, 15, 17, 18, 19, 19, 19, 19, 19},   //5.0
                         {5, 6, 8, 9, 10, 11, 12, 13, 14, 16, 17, 18, 19, 19, 19, 19},    //5.6
                         {4, 5, 7, 8, 9, 10, 11, 12, 13, 14, 16, 17, 18, 19, 19, 19},     //6.0
                         {3, 4, 6, 7, 8, 9, 10, 11, 12, 13, 15, 16, 18, 19, 19, 19},      //6.6
                         {2, 4, 5, 6, 7, 8, 9, 10, 11, 13, 14, 15, 17, 18, 19, 19},      //7.0
                         {2, 3, 4, 5, 6, 8, 9, 10, 11, 12, 13, 14, 16, 18, 19, 19},       //7.6
                         {1, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 14, 15, 17, 19, 19},        //8.0
                         {1, 2, 3, 4, 6, 7, 8, 9, 10, 11, 12, 13, 15, 17, 19, 19},         //8.6
                         {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 14, 16, 18, 19},         //9.0
                         {0, 1, 2, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 16, 18, 19},          //9.6
                         {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 13, 15, 17, 19},          //10.0
                         {0, 0, 1, 3, 4, 5, 5, 7, 8, 8, 9, 11, 12, 14, 16, 18},           //11.0
                         {0, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 13, 15, 18},           //12.0
                         {0, 0, 1, 2, 3, 4, 5, 6, 7, 7, 8, 10, 11, 13, 15, 18},            //13.0
                         {0, 0, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 12, 14, 17},            //14.0
                         {0, 0, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 12, 14, 17},             //15.0
                         {0, 0, 0, 1, 2, 3, 4, 5, 5, 6, 7, 9, 10, 11, 13, 16},             //16.0
                         {0, 0, 0, 0, 1, 2, 3, 4, 5, 6, 7, 8, 10, 11, 13, 16},             //17.0
                         {0, 0, 0, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 12, 15}               //18.0
                        };
    int tvps_FGR[][] = { {8, 10, 11, 12, 13, 14, 16, 18, 19, 19, 19, 19, 19, 19, 19, 19}, //4.0
                         {6, 8, 10, 11, 12, 13, 15, 17, 18, 19, 19, 19, 19, 19, 19, 19},  //4.6
                         {4, 7, 8, 10, 11, 12, 14, 16, 17, 19, 19, 19, 19, 19, 19, 19},   //5.0
                         {3, 6, 7, 9, 10, 11, 13, 14, 16, 18, 19, 19, 19, 19, 19, 19},    //5.6
                         {3, 5, 6, 8, 3, 11, 12, 13, 15, 17, 18, 19, 19, 19, 19, 19},     //6.0
                         {2, 4, 6, 7, 9, 10, 11, 12, 14, 15, 17, 19, 19, 19, 19, 19},      //6.6
                         {2, 4, 5, 6, 8, 9, 10, 12, 13, 14, 16, 18, 19, 19, 19, 19},      //7.0
                         {1, 3, 5, 6, 7, 8, 10, 11, 12, 13, 15, 17, 18, 19, 19, 19},       //7.6
                         {1, 2, 4, 5, 6, 8, 9, 10, 11, 13, 14, 16, 18, 19, 19, 19},        //8.0
                         {1, 2, 4, 5, 6, 7, 8, 9, 11, 12, 13, 15, 17, 19, 19, 19},         //8.6
                         {0, 2, 3, 4, 5, 7, 8, 9, 10, 11, 13, 14, 16, 18, 19, 19},         //9.0
                         {0, 1, 3, 4, 5, 6, 7, 8, 10, 11, 12, 13, 15, 17, 19, 19},          //9.6
                         {0, 1, 2, 3, 4, 5, 6, 8, 9, 10, 11, 12, 14, 16, 18, 19},          //10.0
                         {0, 0, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 13, 15, 17, 19},           //11.0
                         {0, 0, 1, 2, 3, 4, 5, 6, 7, 8, 10, 11, 12, 14, 17, 19},           //12.0
                         {0, 0, 0, 2, 3, 4, 5, 6, 7, 8, 9, 10, 12, 13, 16, 19},            //13.0
                         {0, 0, 0, 1, 2, 3, 4, 5, 6, 7, 8, 10, 11, 13, 15, 18},            //14.0
                         {0, 0, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 12, 15, 18},             //15.0
                         {0, 0, 1, 2, 3, 4, 5, 6, 6, 7, 8, 9, 10, 11, 13, 16},             //16.0
                         {0, 0, 0, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 13, 16},             //17.0
                         {0, 0, 0, 0, 1, 2, 3, 3, 5, 6, 7, 8, 9, 10, 13, 16}               //18.0
                        };
    int tvps_CLO[][] = { {8, 9, 10, 11, 12, 13, 15, 16, 17, 18, 19, 19, 19, 19, 19, 19}, //4.0
                         {7, 8, 9, 10, 12, 13, 14, 16, 17, 18, 19, 19, 19, 19, 19, 19},  //4.6
                         {6, 7, 9, 10, 11, 12, 14, 15, 16, 17, 18, 19, 19, 19, 19, 19},   //5.0
                         {5, 7, 8, 9, 10, 11, 13, 14, 15, 16, 18, 19, 19, 19, 19, 19},    //5.6
                         {4, 6, 8, 9, 10, 11, 12, 13, 14, 16, 17, 18, 19, 19, 19, 19},     //6.0
                         {3, 5, 7, 8, 9, 10, 11, 13, 14, 15, 16, 18, 19, 19, 19, 19},      //6.6
                         {3, 5, 7, 8, 9, 10, 11, 12, 13, 14, 16, 17, 19, 19, 19, 19},      //7.0
                         {2, 5, 6, 7, 8, 9, 10, 11, 12, 14, 15, 16, 18, 19, 19, 19},       //7.6
                         {2, 4, 6, 7, 8, 9, 10, 11, 12, 13, 14, 16, 17, 19, 19, 19},        //8.0
                         {2, 4, 5, 6, 7, 8, 9, 10, 11, 12, 14, 15, 17, 19, 19, 19},         //8.6
                         {1, 3, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 16, 18, 19, 19},         //9.0
                         {1, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 14, 15, 17, 18, 19},          //9.6
                         {1, 2, 4, 5, 6, 7, 8, 9, 9, 10, 11, 13, 14, 16, 18, 19},          //10.0
                         {0, 1, 3, 4, 5, 6, 7, 8, 9, 9, 10, 12, 13, 15, 17, 19},           //11.0
                         {0, 1, 2, 4, 5, 6, 7, 8, 8, 9, 10, 11, 12, 14, 16, 18},           //12.0
                         {0, 1, 2, 3, 4, 5, 6, 7, 8, 8, 9, 10, 11, 13, 15, 17},            //13.0
                         {0, 0, 1, 3, 4, 5, 6, 7, 7, 8, 9, 10, 11, 12, 15, 17},            //14.0
                         {0, 0, 1, 2, 3, 4, 5, 6, 7, 7, 8, 9, 10, 12, 14, 16},             //15.0
                         {0, 0, 1, 2, 3, 4, 5, 6, 6, 7, 8, 9, 10, 11, 13, 16},             //16.0
                         {0, 0, 0, 1, 2, 3, 4, 5, 6, 6, 7, 8, 9, 10, 13, 15},             //17.0
                         {0, 0, 0, 1, 2, 3, 4, 5, 5, 6, 7, 8, 9, 10, 12, 14}               //18.0
                        };
    
    /**
     * Creates new form Panel_TVPS
     */
    public Panel_TVPS() {
        initComponents();
        
        jChgeColor = new ColorChooserButton(Color.CYAN);
        jChgeColor.setBounds(200, 200, 100, 25);
        jChgeColor.setText("Couleur");
        jChgeColor.setEnabled(false);
        this.add (jChgeColor) ;
        jChgeColor.addColorChangedListener(new ColorChangedListener() {
            @Override
            public void colorChanged(Color newColor) {
                    // do something with newColor ...
                    if (OrthoCotation.baseValues.patientAge.years != 0) 
                        OrthoCotation.barChart.changeColor(newColor);
            }
        });

        //Positions
        jIdx_BAS.setLocation(jStd_CLO.getX(), jLabel12.getY()-2);
        jIdx_SEQ.setLocation(jStd_CLO.getX(), jIdx_BAS.getY()+ (jStd_CLO.getY()-jStd_FGR.getY()));
        jLabel13.setLocation(jLabel12.getX(), jIdx_SEQ.getY()+2);
        
        updateResults () ;
    }
    
    public static double zScoreToPercentile(double zScore) {
		double percentile = 0;
		
		NormalDistribution dist = new NormalDistribution();
		percentile = dist.cumulativeProbability(zScore) * 100;
		return percentile;
    }
    
    private int indexAge (){
        int idx ;
        if (OrthoCotation.baseValues.patientAge.years < 4) return 0 ;
        else if (OrthoCotation.baseValues.patientAge.years < 10) {
            idx = (OrthoCotation.baseValues.patientAge.years - 4) * 2 ;
            if (OrthoCotation.baseValues.patientAge.months >5) idx++ ;
        }
        else {
            idx = OrthoCotation.baseValues.patientAge.years + 2 ;
        }
        
        if (idx >= tvps_DIS.length) idx = tvps_DIS.length-1;
        return idx ;
    }
    
    public void updateResults () {
        tvpsStdValues[0] = (int) jDIS.getValue() ;
        tvpsStdValues[1] = (int) jMEM.getValue() ;
        tvpsStdValues[2] = (int) jSPA.getValue() ;
        tvpsStdValues[3] = (int) jCON.getValue() ;
        tvpsStdValues[4] = (int) jSEQ.getValue() ;
        tvpsStdValues[5] = (int) jFGR.getValue() ;
        tvpsStdValues[6] = (int) jCLO.getValue() ;
        
        if (OrthoCotation.baseValues.patientAge.years == 0) return ;
        jMsgTVPS.setText(null);
        
        //DIS
        tvpsStdValues[0] = tvps_DIS[indexAge()][tvpsStdValues[0]-1] ;
        jStd_DIS.setText(String.valueOf(tvpsStdValues[0]));
        double t = ( (double) tvpsStdValues[0] - 10.0 ) / 3.0 ;
        tvpsPctlValues[0] = (int) Math.round(zScoreToPercentile(t)) ;
        jPctl_DIS.setText(String.valueOf (tvpsPctlValues[0])) ;
        //MEM
        tvpsStdValues[1] = tvps_MEM[indexAge()][tvpsStdValues[1]-1] ;
        jStd_MEM.setText(String.valueOf(tvpsStdValues[1]));
        t = ( (double) tvpsStdValues[1] - 10.0 ) / 3.0 ;
        tvpsPctlValues[1] = (int) Math.round(zScoreToPercentile(t)) ;
        jPctl_MEM.setText(String.valueOf (tvpsPctlValues[1])) ;
        //SPA
        tvpsStdValues[2] = tvps_SPA[indexAge()][tvpsStdValues[2]-1] ;
        jStd_SPA.setText(String.valueOf(tvpsStdValues[2]));
        t = ( (double) tvpsStdValues[2] - 10.0 ) / 3.0 ;
        tvpsPctlValues[2] = (int) Math.round(zScoreToPercentile(t)) ;
        jPctl_SPA.setText(String.valueOf (tvpsPctlValues[2])) ;
        //CON
        tvpsStdValues[3] = tvps_CON[indexAge()][tvpsStdValues[3]-1] ;
        jStd_CON.setText(String.valueOf(tvpsStdValues[3]));
        t = ( (double) tvpsStdValues[3] - 10.0 ) / 3.0 ;
        tvpsPctlValues[3] = (int) Math.round(zScoreToPercentile(t)) ;
        jPctl_CON.setText(String.valueOf (tvpsPctlValues[3])) ;
        //SEQ
        tvpsStdValues[4] = tvps_SEQ[indexAge()][tvpsStdValues[4]-1] ;
        jStd_SEQ.setText(String.valueOf(tvpsStdValues[4]));
        t = ( (double) tvpsStdValues[4] - 10.0 ) / 3.0 ;
        tvpsPctlValues[4] = (int) Math.round(zScoreToPercentile(t)) ;
        jPctl_SEQ.setText(String.valueOf (tvpsPctlValues[4])) ;
        //FGR
        tvpsStdValues[5] = tvps_FGR[indexAge()][tvpsStdValues[5]-1] ;
        jStd_FGR.setText(String.valueOf(tvpsStdValues[5]));
        t = ( (double) tvpsStdValues[5] - 10.0 ) / 3.0 ;
        tvpsPctlValues[5] = (int) Math.round(zScoreToPercentile(t)) ;
        jPctl_FGR.setText(String.valueOf (tvpsPctlValues[5])) ;
        //CLO
        tvpsStdValues[6] = tvps_CLO[indexAge()][tvpsStdValues[6]-1] ;
        jStd_CLO.setText(String.valueOf(tvpsStdValues[6]));
        t = ( (double) tvpsStdValues[6] - 10.0 ) / 3.0 ;
        tvpsPctlValues[6] = (int) Math.round(zScoreToPercentile(t)) ;
        jPctl_CLO.setText(String.valueOf (tvpsPctlValues[6])) ;
        
        OrthoCotation.barChart.updateGraph () ;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel15 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jMsgTVPS = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jDIS = new javax.swing.JSpinner();
        jMEM = new javax.swing.JSpinner();
        jSPA = new javax.swing.JSpinner();
        jCON = new javax.swing.JSpinner();
        jSEQ = new javax.swing.JSpinner();
        jFGR = new javax.swing.JSpinner();
        jCLO = new javax.swing.JSpinner();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jStd_DIS = new javax.swing.JTextField();
        jStd_MEM = new javax.swing.JTextField();
        jStd_SPA = new javax.swing.JTextField();
        jStd_CON = new javax.swing.JTextField();
        jStd_SEQ = new javax.swing.JTextField();
        jStd_FGR = new javax.swing.JTextField();
        jIdx_SEQ = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jPctl_DIS = new javax.swing.JTextField();
        jPctl_MEM = new javax.swing.JTextField();
        jPctl_SPA = new javax.swing.JTextField();
        jPctl_CON = new javax.swing.JTextField();
        jPctl_SEQ = new javax.swing.JTextField();
        jPctl_FGR = new javax.swing.JTextField();
        jPctl_CLO = new javax.swing.JTextField();
        jCheckRange = new javax.swing.JCheckBox();
        jCheckTitre = new javax.swing.JCheckBox();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jStd_CLO = new javax.swing.JTextField();
        jIdx_BAS = new javax.swing.JTextField();

        setMinimumSize(new java.awt.Dimension(700, 500));
        setPreferredSize(new java.awt.Dimension(700, 500));
        setLayout(null);

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setForeground(java.awt.Color.gray);
        jLabel15.setText("TVPS-3");
        add(jLabel15);
        jLabel15.setBounds(10, 11, 52, 17);
        add(jSeparator1);
        jSeparator1.setBounds(10, 340, 518, 10);

        jMsgTVPS.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jMsgTVPS.setForeground(java.awt.Color.red);
        jMsgTVPS.setText("(Pas de calculs en mode démo)");
        add(jMsgTVPS);
        jMsgTVPS.setBounds(123, 11, 310, 14);

        jLabel1.setText("DIS :");
        jLabel1.setMaximumSize(new java.awt.Dimension(30, 14));
        jLabel1.setMinimumSize(new java.awt.Dimension(30, 14));
        jLabel1.setPreferredSize(new java.awt.Dimension(30, 14));
        add(jLabel1);
        jLabel1.setBounds(10, 81, 30, 14);

        jLabel2.setText("MEM :");
        jLabel2.setMaximumSize(new java.awt.Dimension(35, 14));
        jLabel2.setMinimumSize(new java.awt.Dimension(35, 14));
        jLabel2.setPreferredSize(new java.awt.Dimension(35, 14));
        add(jLabel2);
        jLabel2.setBounds(10, 112, 35, 14);

        jLabel3.setText("SPA :");
        add(jLabel3);
        jLabel3.setBounds(10, 147, 40, 14);

        jLabel4.setText("CON :");
        add(jLabel4);
        jLabel4.setBounds(10, 188, 40, 14);

        jLabel5.setText("SEQ :");
        add(jLabel5);
        jLabel5.setBounds(10, 226, 40, 14);

        jLabel6.setText("FGR :");
        add(jLabel6);
        jLabel6.setBounds(10, 264, 40, 14);

        jLabel7.setText("CLO :");
        add(jLabel7);
        jLabel7.setBounds(10, 302, 40, 14);

        jDIS.setModel(new javax.swing.SpinnerNumberModel(11, 1, 16, 1));
        jDIS.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jDISStateChanged(evt);
            }
        });
        add(jDIS);
        jDIS.setBounds(57, 78, 48, 20);

        jMEM.setModel(new javax.swing.SpinnerNumberModel(11, 1, 16, 1));
        jMEM.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jMEMStateChanged(evt);
            }
        });
        add(jMEM);
        jMEM.setBounds(57, 109, 48, 20);

        jSPA.setModel(new javax.swing.SpinnerNumberModel(11, 1, 16, 1));
        jSPA.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSPAStateChanged(evt);
            }
        });
        add(jSPA);
        jSPA.setBounds(57, 147, 48, 20);

        jCON.setModel(new javax.swing.SpinnerNumberModel(11, 1, 16, 1));
        jCON.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jCONStateChanged(evt);
            }
        });
        add(jCON);
        jCON.setBounds(57, 185, 48, 20);

        jSEQ.setModel(new javax.swing.SpinnerNumberModel(11, 1, 16, 1));
        jSEQ.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSEQStateChanged(evt);
            }
        });
        add(jSEQ);
        jSEQ.setBounds(57, 223, 48, 20);

        jFGR.setModel(new javax.swing.SpinnerNumberModel(11, 1, 16, 1));
        jFGR.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jFGRStateChanged(evt);
            }
        });
        add(jFGR);
        jFGR.setBounds(57, 261, 48, 20);

        jCLO.setModel(new javax.swing.SpinnerNumberModel(11, 1, 16, 1));
        jCLO.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jCLOStateChanged(evt);
            }
        });
        add(jCLO);
        jCLO.setBounds(57, 299, 48, 20);

        jLabel8.setText("Raw");
        add(jLabel8);
        jLabel8.setBounds(70, 50, 30, 14);

        jLabel9.setText("Scaled");
        add(jLabel9);
        jLabel9.setBounds(147, 50, 40, 14);

        jStd_DIS.setEditable(false);
        jStd_DIS.setBackground(new java.awt.Color(255, 255, 255));
        jStd_DIS.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jStd_DIS.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jStd_DIS.setText("na");
        add(jStd_DIS);
        jStd_DIS.setBounds(147, 78, 40, 20);

        jStd_MEM.setEditable(false);
        jStd_MEM.setBackground(new java.awt.Color(255, 255, 255));
        jStd_MEM.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jStd_MEM.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jStd_MEM.setText("na");
        jStd_MEM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jStd_MEMActionPerformed(evt);
            }
        });
        add(jStd_MEM);
        jStd_MEM.setBounds(147, 109, 40, 20);

        jStd_SPA.setEditable(false);
        jStd_SPA.setBackground(new java.awt.Color(255, 255, 255));
        jStd_SPA.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jStd_SPA.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jStd_SPA.setText("na");
        jStd_SPA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jStd_SPAActionPerformed(evt);
            }
        });
        add(jStd_SPA);
        jStd_SPA.setBounds(147, 147, 40, 20);

        jStd_CON.setEditable(false);
        jStd_CON.setBackground(new java.awt.Color(255, 255, 255));
        jStd_CON.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jStd_CON.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jStd_CON.setText("na");
        jStd_CON.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jStd_CONActionPerformed(evt);
            }
        });
        add(jStd_CON);
        jStd_CON.setBounds(147, 185, 40, 20);

        jStd_SEQ.setEditable(false);
        jStd_SEQ.setBackground(new java.awt.Color(255, 255, 255));
        jStd_SEQ.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jStd_SEQ.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jStd_SEQ.setText("na");
        jStd_SEQ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jStd_SEQActionPerformed(evt);
            }
        });
        add(jStd_SEQ);
        jStd_SEQ.setBounds(147, 223, 40, 20);

        jStd_FGR.setEditable(false);
        jStd_FGR.setBackground(new java.awt.Color(255, 255, 255));
        jStd_FGR.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jStd_FGR.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jStd_FGR.setText("na");
        jStd_FGR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jStd_FGRActionPerformed(evt);
            }
        });
        add(jStd_FGR);
        jStd_FGR.setBounds(147, 261, 40, 20);

        jIdx_SEQ.setEditable(false);
        jIdx_SEQ.setBackground(new java.awt.Color(255, 255, 255));
        jIdx_SEQ.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jIdx_SEQ.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jIdx_SEQ.setText("na");
        jIdx_SEQ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jIdx_SEQActionPerformed(evt);
            }
        });
        add(jIdx_SEQ);
        jIdx_SEQ.setBounds(140, 430, 40, 20);

        jLabel10.setText("Percentile");
        add(jLabel10);
        jLabel10.setBounds(223, 50, 60, 14);

        jPctl_DIS.setEditable(false);
        jPctl_DIS.setBackground(new java.awt.Color(255, 255, 255));
        jPctl_DIS.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jPctl_DIS.setText("na");
        add(jPctl_DIS);
        jPctl_DIS.setBounds(223, 78, 40, 20);

        jPctl_MEM.setEditable(false);
        jPctl_MEM.setBackground(new java.awt.Color(255, 255, 255));
        jPctl_MEM.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jPctl_MEM.setText("na");
        add(jPctl_MEM);
        jPctl_MEM.setBounds(223, 109, 40, 20);

        jPctl_SPA.setEditable(false);
        jPctl_SPA.setBackground(new java.awt.Color(255, 255, 255));
        jPctl_SPA.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jPctl_SPA.setText("na");
        add(jPctl_SPA);
        jPctl_SPA.setBounds(223, 147, 40, 20);

        jPctl_CON.setEditable(false);
        jPctl_CON.setBackground(new java.awt.Color(255, 255, 255));
        jPctl_CON.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jPctl_CON.setText("na");
        add(jPctl_CON);
        jPctl_CON.setBounds(223, 185, 40, 20);

        jPctl_SEQ.setEditable(false);
        jPctl_SEQ.setBackground(new java.awt.Color(255, 255, 255));
        jPctl_SEQ.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jPctl_SEQ.setText("na");
        add(jPctl_SEQ);
        jPctl_SEQ.setBounds(223, 223, 40, 20);

        jPctl_FGR.setEditable(false);
        jPctl_FGR.setBackground(new java.awt.Color(255, 255, 255));
        jPctl_FGR.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jPctl_FGR.setText("na");
        add(jPctl_FGR);
        jPctl_FGR.setBounds(223, 261, 40, 20);

        jPctl_CLO.setEditable(false);
        jPctl_CLO.setBackground(new java.awt.Color(255, 255, 255));
        jPctl_CLO.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jPctl_CLO.setText("na");
        add(jPctl_CLO);
        jPctl_CLO.setBounds(223, 299, 40, 20);

        jCheckRange.setText("Normal Range");
        jCheckRange.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckRangeActionPerformed(evt);
            }
        });
        add(jCheckRange);
        jCheckRange.setBounds(560, 360, 110, 23);

        jCheckTitre.setSelected(true);
        jCheckTitre.setText("Titre");
        jCheckTitre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckTitreActionPerformed(evt);
            }
        });
        add(jCheckTitre);
        jCheckTitre.setBounds(481, 364, 70, 23);
        add(jSeparator2);
        jSeparator2.setBounds(10, 34, 518, 10);

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setText("INDEX SCORES :");
        add(jLabel11);
        jLabel11.setBounds(10, 360, 85, 14);

        jLabel12.setText("Basic Proc. :");
        add(jLabel12);
        jLabel12.setBounds(30, 400, 80, 14);

        jLabel13.setText("Sequencing :");
        add(jLabel13);
        jLabel13.setBounds(30, 430, 90, 14);

        jStd_CLO.setEditable(false);
        jStd_CLO.setBackground(new java.awt.Color(255, 255, 255));
        jStd_CLO.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jStd_CLO.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jStd_CLO.setText("na");
        jStd_CLO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jStd_CLOActionPerformed(evt);
            }
        });
        add(jStd_CLO);
        jStd_CLO.setBounds(147, 299, 40, 20);

        jIdx_BAS.setEditable(false);
        jIdx_BAS.setBackground(new java.awt.Color(255, 255, 255));
        jIdx_BAS.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jIdx_BAS.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jIdx_BAS.setText("na");
        jIdx_BAS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jIdx_BASActionPerformed(evt);
            }
        });
        add(jIdx_BAS);
        jIdx_BAS.setBounds(140, 400, 40, 20);
    }// </editor-fold>//GEN-END:initComponents

    private void jDISStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jDISStateChanged
        updateResults () ;
    }//GEN-LAST:event_jDISStateChanged

    private void jMEMStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jMEMStateChanged
        updateResults () ;
    }//GEN-LAST:event_jMEMStateChanged

    private void jSPAStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSPAStateChanged
        updateResults () ;
    }//GEN-LAST:event_jSPAStateChanged

    private void jCONStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jCONStateChanged
        updateResults () ;
    }//GEN-LAST:event_jCONStateChanged

    private void jSEQStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSEQStateChanged
        updateResults () ;
    }//GEN-LAST:event_jSEQStateChanged

    private void jFGRStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jFGRStateChanged
        updateResults () ;
    }//GEN-LAST:event_jFGRStateChanged

    private void jCLOStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jCLOStateChanged
        updateResults () ;
    }//GEN-LAST:event_jCLOStateChanged

    private void jStd_MEMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jStd_MEMActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jStd_MEMActionPerformed

    private void jStd_SPAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jStd_SPAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jStd_SPAActionPerformed

    private void jStd_CONActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jStd_CONActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jStd_CONActionPerformed

    private void jStd_SEQActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jStd_SEQActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jStd_SEQActionPerformed

    private void jStd_FGRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jStd_FGRActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jStd_FGRActionPerformed

    private void jIdx_SEQActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jIdx_SEQActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jIdx_SEQActionPerformed

    private void jCheckRangeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckRangeActionPerformed
        OrthoCotation.barChart.changeAspect (jCheckTitre.isSelected(), jCheckRange.isSelected()) ;
    }//GEN-LAST:event_jCheckRangeActionPerformed

    private void jCheckTitreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckTitreActionPerformed
        OrthoCotation.barChart.changeAspect (jCheckTitre.isSelected(), jCheckRange.isSelected()) ;
    }//GEN-LAST:event_jCheckTitreActionPerformed

    private void jStd_CLOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jStd_CLOActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jStd_CLOActionPerformed

    private void jIdx_BASActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jIdx_BASActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jIdx_BASActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSpinner jCLO;
    private javax.swing.JSpinner jCON;
    public static javax.swing.JCheckBox jCheckRange;
    public static javax.swing.JCheckBox jCheckTitre;
    private javax.swing.JSpinner jDIS;
    private javax.swing.JSpinner jFGR;
    private javax.swing.JTextField jIdx_BAS;
    private javax.swing.JTextField jIdx_SEQ;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JSpinner jMEM;
    public static javax.swing.JLabel jMsgTVPS;
    private javax.swing.JTextField jPctl_CLO;
    private javax.swing.JTextField jPctl_CON;
    private javax.swing.JTextField jPctl_DIS;
    private javax.swing.JTextField jPctl_FGR;
    private javax.swing.JTextField jPctl_MEM;
    private javax.swing.JTextField jPctl_SEQ;
    private javax.swing.JTextField jPctl_SPA;
    private javax.swing.JSpinner jSEQ;
    private javax.swing.JSpinner jSPA;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextField jStd_CLO;
    private javax.swing.JTextField jStd_CON;
    private javax.swing.JTextField jStd_DIS;
    private javax.swing.JTextField jStd_FGR;
    private javax.swing.JTextField jStd_MEM;
    private javax.swing.JTextField jStd_SEQ;
    private javax.swing.JTextField jStd_SPA;
    // End of variables declaration//GEN-END:variables
}

