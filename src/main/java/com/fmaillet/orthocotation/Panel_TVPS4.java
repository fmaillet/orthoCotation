package com.fmaillet.orthocotation;

import com.fmaillet.orthocotation.ColorChooserButton.ColorChangedListener;
import java.awt.Color;
import javax.swing.JPanel;
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
public class Panel_TVPS4 extends JPanel {

    public static int[] tvpsSclValues = new int[7] ;
    public static int tvpsPctlValues[] = new int[7] ;
    public static boolean tvpsChkValues[] = {true, true, true, true, true, true, true } ;
    static public ColorChooserButton jChgeColor ;
    
    static public int[] transposeColumn = new int [7] ;
    
    int tvps_DIS[][] = { {3, 4, 5, 7, 8, 9, 10, 11, 13, 14, 15, 16, 17, 18, 18, 19, 19, 19, 19}, //5.0
                         {3, 4, 5, 6, 7, 8, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 19, 19, 19}, //5.6
                         {2, 3, 4, 5, 7, 8,  9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 19, 19},  //6.0
                         {1, 3, 4, 5, 6, 7,  8,  9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 19},  //6.6
                         {1, 2, 3, 4, 5, 7,  8,  9, 10, 11, 12, 13, 13, 14, 15, 16, 17, 18, 19},  //7.0
                         {1, 2, 3, 4, 5, 6,  7,  8,  9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 18},  //7.6
                         {1, 1, 2, 4, 5, 6,  7,  8,  9, 10, 10, 11, 12, 13, 14, 15, 16, 17, 18},  //8.0
                         {1, 1, 2, 3, 4, 5,  6,  7,  8,  9, 10, 11, 12, 13, 14, 14, 15, 16, 17},  //8.6
                         {1, 1, 2, 3, 4, 5,  6,  7,  8,  9,  9, 10, 11, 12, 13, 14, 15, 16, 17},  //9.0
                         {1, 1, 1, 2, 3, 4,  5,  6,  7,  8,  9, 10, 11, 12, 13, 14, 14, 15, 16},  //9.6
                         {1, 1, 1, 2, 3, 4,  5,  6,  7,  8,  9, 10, 10, 11, 12, 13, 14, 15, 16},  //10.0
                         {1, 1, 1, 2, 3, 4,  5,  6,  7,  8,  8,  9, 10, 11, 12, 13, 14, 15, 16},  //10.6
                         {1, 1, 1, 2, 3, 4,  5,  5,  6,  7,  8,  9, 10, 11, 12, 12, 13, 14, 15},  //11.0
                         {1, 1, 1, 1, 2, 3,  4,  5,  6,  7,  8,  9,  9, 10, 11, 12, 13, 14, 15},  //11.6
                         {1, 1, 1, 1, 2, 3,  4,  5,  6,  6,  7,  8,  9, 10, 11, 12, 13, 14, 15},  //12.0
                         {1, 1, 1, 1, 2, 3,  3,  4,  5,  6,  7,  8,  8,  9, 10, 11, 12, 13, 14},  //13.0
                         {1, 1, 1, 1, 2, 2,  3,  4,  5,  6,  6,  7,  8,  9, 10, 11, 12, 13, 14},  //14.0
                         {1, 1, 1, 1, 1, 2,  3,  4,  5,  5,  6,  7,  8,  9, 10, 11, 12, 13, 14},  //15.0
                         {1, 1, 1, 1, 1, 2,  3,  4,  4,  5,  6,  7,  8,  8,  9, 10, 11, 13, 14},  //16.0
                         {1, 1, 1, 1, 1, 2,  3,  4,  4,  5,  6,  7,  7,  8,  9, 10, 11, 12, 14},  //17.0
                         {1, 1, 1, 1, 1, 2,  3,  3,  4,  5,  6,  7,  7,  8,  9, 10, 11, 12, 14},  //18.0
                         {1, 1, 1, 1, 1, 2,  3,  3,  4,  5,  6,  6,  7,  8,  9, 10, 11, 12, 13},  //19.0
                         {1, 1, 1, 1, 1, 2,  3,  3,  4,  5,  5,  6,  7,  8,  9, 10, 11, 12, 13},  //20.0
                         {1, 1, 1, 1, 1, 1,  2,  3,  4,  5,  5,  6,  7,  8,  9, 10, 11, 12, 13},  //21.0
                        };
    int tvps_MEM[][] = { {3, 4, 5, 7, 8, 9, 10, 11, 13, 14, 15, 16, 17, 18, 18, 19, 19, 19, 19}, //5.0
                         {3, 4, 5, 6, 7, 8, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 19, 19, 19}, //5.6
                         {2, 3, 4, 5, 7, 8,  9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 19, 19},  //6.0
                         {1, 3, 4, 5, 6, 7,  8,  9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 19},  //6.6
                         {1, 2, 3, 4, 5, 7,  8,  9, 10, 11, 12, 13, 13, 14, 15, 16, 17, 18, 19},  //7.0
                         {1, 2, 3, 4, 5, 6,  7,  8,  9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 18},  //7.6
                         {1, 1, 2, 4, 5, 6,  7,  8,  9, 10, 10, 11, 12, 13, 14, 15, 16, 17, 18},  //8.0
                         {1, 1, 2, 3, 4, 5,  6,  7,  8,  9, 10, 11, 12, 13, 14, 14, 15, 16, 17},  //8.6
                         {1, 1, 2, 3, 4, 5,  6,  7,  8,  9,  9, 10, 11, 12, 13, 14, 15, 16, 17},  //9.0
                         {1, 1, 1, 2, 3, 4,  5,  6,  7,  8,  9, 10, 11, 12, 13, 14, 14, 15, 16},  //9.6
                         {1, 1, 1, 2, 3, 4,  5,  6,  7,  8,  9, 10, 10, 11, 12, 13, 14, 15, 16},  //10.0
                         {1, 1, 1, 2, 3, 4,  5,  6,  7,  8,  8,  9, 10, 11, 12, 13, 14, 15, 16},  //10.6
                         {1, 1, 1, 2, 3, 4,  5,  5,  6,  7,  8,  9, 10, 11, 12, 12, 13, 14, 15},  //11.0
                         {1, 1, 1, 1, 2, 3,  4,  5,  6,  7,  8,  9,  9, 10, 11, 12, 13, 14, 15},  //11.6
                         {1, 1, 1, 1, 2, 3,  4,  5,  6,  6,  7,  8,  9, 10, 11, 12, 13, 14, 15},  //12.0
                         {1, 1, 1, 1, 2, 3,  3,  4,  5,  6,  7,  8,  8,  9, 10, 11, 12, 13, 14},  //13.0
                         {1, 1, 1, 1, 2, 2,  3,  4,  5,  6,  6,  7,  8,  9, 10, 11, 12, 13, 14},  //14.0
                         {1, 1, 1, 1, 1, 2,  3,  4,  5,  5,  6,  7,  8,  9, 10, 11, 12, 13, 14},  //15.0
                         {1, 1, 1, 1, 1, 2,  3,  4,  4,  5,  6,  7,  8,  8,  9, 10, 11, 13, 14},  //16.0
                         {1, 1, 1, 1, 1, 2,  3,  4,  4,  5,  6,  7,  7,  8,  9, 10, 11, 12, 14},  //17.0
                         {1, 1, 1, 1, 1, 2,  3,  3,  4,  5,  6,  7,  7,  8,  9, 10, 11, 12, 14},  //18.0
                         {1, 1, 1, 1, 1, 2,  3,  3,  4,  5,  6,  6,  7,  8,  9, 10, 11, 12, 13},  //19.0
                         {1, 1, 1, 1, 1, 2,  3,  3,  4,  5,  5,  6,  7,  8,  9, 10, 11, 12, 13},  //20.0
                         {1, 1, 1, 1, 1, 1,  2,  3,  4,  5,  5,  6,  7,  8,  9, 10, 11, 12, 13},  //21.0
                        };
    
    int tvps_SPA[][] = { {3, 4, 5, 7, 8, 9, 10, 11, 13, 14, 15, 16, 17, 18, 18, 19, 19, 19, 19}, //5.0
                         {3, 4, 5, 6, 7, 8, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 19, 19, 19}, //5.6
                         {2, 3, 4, 5, 7, 8,  9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 19, 19},  //6.0
                         {1, 3, 4, 5, 6, 7,  8,  9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 19},  //6.6
                         {1, 2, 3, 4, 5, 7,  8,  9, 10, 11, 12, 13, 13, 14, 15, 16, 17, 18, 19},  //7.0
                         {1, 2, 3, 4, 5, 6,  7,  8,  9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 18},  //7.6
                         {1, 1, 2, 4, 5, 6,  7,  8,  9, 10, 10, 11, 12, 13, 14, 15, 16, 17, 18},  //8.0
                         {1, 1, 2, 3, 4, 5,  6,  7,  8,  9, 10, 11, 12, 13, 14, 14, 15, 16, 17},  //8.6
                         {1, 1, 2, 3, 4, 5,  6,  7,  8,  9,  9, 10, 11, 12, 13, 14, 15, 16, 17},  //9.0
                         {1, 1, 1, 2, 3, 4,  5,  6,  7,  8,  9, 10, 11, 12, 13, 14, 14, 15, 16},  //9.6
                         {1, 1, 1, 2, 3, 4,  5,  6,  7,  8,  9, 10, 10, 11, 12, 13, 14, 15, 16},  //10.0
                         {1, 1, 1, 2, 3, 4,  5,  6,  7,  8,  8,  9, 10, 11, 12, 13, 14, 15, 16},  //10.6
                         {1, 1, 1, 2, 3, 4,  5,  5,  6,  7,  8,  9, 10, 11, 12, 12, 13, 14, 15},  //11.0
                         {1, 1, 1, 1, 2, 3,  4,  5,  6,  7,  8,  9,  9, 10, 11, 12, 13, 14, 15},  //11.6
                         {1, 1, 1, 1, 2, 3,  4,  5,  6,  6,  7,  8,  9, 10, 11, 12, 13, 14, 15},  //12.0
                         {1, 1, 1, 1, 2, 3,  3,  4,  5,  6,  7,  8,  8,  9, 10, 11, 12, 13, 14},  //13.0
                         {1, 1, 1, 1, 2, 2,  3,  4,  5,  6,  6,  7,  8,  9, 10, 11, 12, 13, 14},  //14.0
                         {1, 1, 1, 1, 1, 2,  3,  4,  5,  5,  6,  7,  8,  9, 10, 11, 12, 13, 14},  //15.0
                         {1, 1, 1, 1, 1, 2,  3,  4,  4,  5,  6,  7,  8,  8,  9, 10, 11, 13, 14},  //16.0
                         {1, 1, 1, 1, 1, 2,  3,  4,  4,  5,  6,  7,  7,  8,  9, 10, 11, 12, 14},  //17.0
                         {1, 1, 1, 1, 1, 2,  3,  3,  4,  5,  6,  7,  7,  8,  9, 10, 11, 12, 14},  //18.0
                         {1, 1, 1, 1, 1, 2,  3,  3,  4,  5,  6,  6,  7,  8,  9, 10, 11, 12, 13},  //19.0
                         {1, 1, 1, 1, 1, 2,  3,  3,  4,  5,  5,  6,  7,  8,  9, 10, 11, 12, 13},  //20.0
                         {1, 1, 1, 1, 1, 1,  2,  3,  4,  5,  5,  6,  7,  8,  9, 10, 11, 12, 13},  //21.0
                        };
    
    int tvps_CON[][] = { {3, 4, 5, 7, 8, 9, 10, 11, 13, 14, 15, 16, 17, 18, 18, 19, 19, 19, 19}, //5.0
                         {3, 4, 5, 6, 7, 8, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 19, 19, 19}, //5.6
                         {2, 3, 4, 5, 7, 8,  9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 19, 19},  //6.0
                         {1, 3, 4, 5, 6, 7,  8,  9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 19},  //6.6
                         {1, 2, 3, 4, 5, 7,  8,  9, 10, 11, 12, 13, 13, 14, 15, 16, 17, 18, 19},  //7.0
                         {1, 2, 3, 4, 5, 6,  7,  8,  9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 18},  //7.6
                         {1, 1, 2, 4, 5, 6,  7,  8,  9, 10, 10, 11, 12, 13, 14, 15, 16, 17, 18},  //8.0
                         {1, 1, 2, 3, 4, 5,  6,  7,  8,  9, 10, 11, 12, 13, 14, 14, 15, 16, 17},  //8.6
                         {1, 1, 2, 3, 4, 5,  6,  7,  8,  9,  9, 10, 11, 12, 13, 14, 15, 16, 17},  //9.0
                         {1, 1, 1, 2, 3, 4,  5,  6,  7,  8,  9, 10, 11, 12, 13, 14, 14, 15, 16},  //9.6
                         {1, 1, 1, 2, 3, 4,  5,  6,  7,  8,  9, 10, 10, 11, 12, 13, 14, 15, 16},  //10.0
                         {1, 1, 1, 2, 3, 4,  5,  6,  7,  8,  8,  9, 10, 11, 12, 13, 14, 15, 16},  //10.6
                         {1, 1, 1, 2, 3, 4,  5,  5,  6,  7,  8,  9, 10, 11, 12, 12, 13, 14, 15},  //11.0
                         {1, 1, 1, 1, 2, 3,  4,  5,  6,  7,  8,  9,  9, 10, 11, 12, 13, 14, 15},  //11.6
                         {1, 1, 1, 1, 2, 3,  4,  5,  6,  6,  7,  8,  9, 10, 11, 12, 13, 14, 15},  //12.0
                         {1, 1, 1, 1, 2, 3,  3,  4,  5,  6,  7,  8,  8,  9, 10, 11, 12, 13, 14},  //13.0
                         {1, 1, 1, 1, 2, 2,  3,  4,  5,  6,  6,  7,  8,  9, 10, 11, 12, 13, 14},  //14.0
                         {1, 1, 1, 1, 1, 2,  3,  4,  5,  5,  6,  7,  8,  9, 10, 11, 12, 13, 14},  //15.0
                         {1, 1, 1, 1, 1, 2,  3,  4,  4,  5,  6,  7,  8,  8,  9, 10, 11, 13, 14},  //16.0
                         {1, 1, 1, 1, 1, 2,  3,  4,  4,  5,  6,  7,  7,  8,  9, 10, 11, 12, 14},  //17.0
                         {1, 1, 1, 1, 1, 2,  3,  3,  4,  5,  6,  7,  7,  8,  9, 10, 11, 12, 14},  //18.0
                         {1, 1, 1, 1, 1, 2,  3,  3,  4,  5,  6,  6,  7,  8,  9, 10, 11, 12, 13},  //19.0
                         {1, 1, 1, 1, 1, 2,  3,  3,  4,  5,  5,  6,  7,  8,  9, 10, 11, 12, 13},  //20.0
                         {1, 1, 1, 1, 1, 1,  2,  3,  4,  5,  5,  6,  7,  8,  9, 10, 11, 12, 13},  //21.0
                        };
        
    int tvps_SEQ[][] = { {3, 4, 5, 7, 8, 9, 10, 11, 13, 14, 15, 16, 17, 18, 18, 19, 19, 19, 19}, //5.0
                         {3, 4, 5, 6, 7, 8, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 19, 19, 19}, //5.6
                         {2, 3, 4, 5, 7, 8,  9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 19, 19},  //6.0
                         {1, 3, 4, 5, 6, 7,  8,  9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 19},  //6.6
                         {1, 2, 3, 4, 5, 7,  8,  9, 10, 11, 12, 13, 13, 14, 15, 16, 17, 18, 19},  //7.0
                         {1, 2, 3, 4, 5, 6,  7,  8,  9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 18},  //7.6
                         {1, 1, 2, 4, 5, 6,  7,  8,  9, 10, 10, 11, 12, 13, 14, 15, 16, 17, 18},  //8.0
                         {1, 1, 2, 3, 4, 5,  6,  7,  8,  9, 10, 11, 12, 13, 14, 14, 15, 16, 17},  //8.6
                         {1, 1, 2, 3, 4, 5,  6,  7,  8,  9,  9, 10, 11, 12, 13, 14, 15, 16, 17},  //9.0
                         {1, 1, 1, 2, 3, 4,  5,  6,  7,  8,  9, 10, 11, 12, 13, 14, 14, 15, 16},  //9.6
                         {1, 1, 1, 2, 3, 4,  5,  6,  7,  8,  9, 10, 10, 11, 12, 13, 14, 15, 16},  //10.0
                         {1, 1, 1, 2, 3, 4,  5,  6,  7,  8,  8,  9, 10, 11, 12, 13, 14, 15, 16},  //10.6
                         {1, 1, 1, 2, 3, 4,  5,  5,  6,  7,  8,  9, 10, 11, 12, 12, 13, 14, 15},  //11.0
                         {1, 1, 1, 1, 2, 3,  4,  5,  6,  7,  8,  9,  9, 10, 11, 12, 13, 14, 15},  //11.6
                         {1, 1, 1, 1, 2, 3,  4,  5,  6,  6,  7,  8,  9, 10, 11, 12, 13, 14, 15},  //12.0
                         {1, 1, 1, 1, 2, 3,  3,  4,  5,  6,  7,  8,  8,  9, 10, 11, 12, 13, 14},  //13.0
                         {1, 1, 1, 1, 2, 2,  3,  4,  5,  6,  6,  7,  8,  9, 10, 11, 12, 13, 14},  //14.0
                         {1, 1, 1, 1, 1, 2,  3,  4,  5,  5,  6,  7,  8,  9, 10, 11, 12, 13, 14},  //15.0
                         {1, 1, 1, 1, 1, 2,  3,  4,  4,  5,  6,  7,  8,  8,  9, 10, 11, 13, 14},  //16.0
                         {1, 1, 1, 1, 1, 2,  3,  4,  4,  5,  6,  7,  7,  8,  9, 10, 11, 12, 14},  //17.0
                         {1, 1, 1, 1, 1, 2,  3,  3,  4,  5,  6,  7,  7,  8,  9, 10, 11, 12, 14},  //18.0
                         {1, 1, 1, 1, 1, 2,  3,  3,  4,  5,  6,  6,  7,  8,  9, 10, 11, 12, 13},  //19.0
                         {1, 1, 1, 1, 1, 2,  3,  3,  4,  5,  5,  6,  7,  8,  9, 10, 11, 12, 13},  //20.0
                         {1, 1, 1, 1, 1, 1,  2,  3,  4,  5,  5,  6,  7,  8,  9, 10, 11, 12, 13},  //21.0
                        };
    int tvps_FGR[][] = { {3, 4, 5, 7, 8, 9, 10, 11, 13, 14, 15, 16, 17, 18, 18, 19, 19, 19, 19}, //5.0
                         {3, 4, 5, 6, 7, 8, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 19, 19, 19}, //5.6
                         {2, 3, 4, 5, 7, 8,  9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 19, 19},  //6.0
                         {1, 3, 4, 5, 6, 7,  8,  9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 19},  //6.6
                         {1, 2, 3, 4, 5, 7,  8,  9, 10, 11, 12, 13, 13, 14, 15, 16, 17, 18, 19},  //7.0
                         {1, 2, 3, 4, 5, 6,  7,  8,  9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 18},  //7.6
                         {1, 1, 2, 4, 5, 6,  7,  8,  9, 10, 10, 11, 12, 13, 14, 15, 16, 17, 18},  //8.0
                         {1, 1, 2, 3, 4, 5,  6,  7,  8,  9, 10, 11, 12, 13, 14, 14, 15, 16, 17},  //8.6
                         {1, 1, 2, 3, 4, 5,  6,  7,  8,  9,  9, 10, 11, 12, 13, 14, 15, 16, 17},  //9.0
                         {1, 1, 1, 2, 3, 4,  5,  6,  7,  8,  9, 10, 11, 12, 13, 14, 14, 15, 16},  //9.6
                         {1, 1, 1, 2, 3, 4,  5,  6,  7,  8,  9, 10, 10, 11, 12, 13, 14, 15, 16},  //10.0
                         {1, 1, 1, 2, 3, 4,  5,  6,  7,  8,  8,  9, 10, 11, 12, 13, 14, 15, 16},  //10.6
                         {1, 1, 1, 2, 3, 4,  5,  5,  6,  7,  8,  9, 10, 11, 12, 12, 13, 14, 15},  //11.0
                         {1, 1, 1, 1, 2, 3,  4,  5,  6,  7,  8,  9,  9, 10, 11, 12, 13, 14, 15},  //11.6
                         {1, 1, 1, 1, 2, 3,  4,  5,  6,  6,  7,  8,  9, 10, 11, 12, 13, 14, 15},  //12.0
                         {1, 1, 1, 1, 2, 3,  3,  4,  5,  6,  7,  8,  8,  9, 10, 11, 12, 13, 14},  //13.0
                         {1, 1, 1, 1, 2, 2,  3,  4,  5,  6,  6,  7,  8,  9, 10, 11, 12, 13, 14},  //14.0
                         {1, 1, 1, 1, 1, 2,  3,  4,  5,  5,  6,  7,  8,  9, 10, 11, 12, 13, 14},  //15.0
                         {1, 1, 1, 1, 1, 2,  3,  4,  4,  5,  6,  7,  8,  8,  9, 10, 11, 13, 14},  //16.0
                         {1, 1, 1, 1, 1, 2,  3,  4,  4,  5,  6,  7,  7,  8,  9, 10, 11, 12, 14},  //17.0
                         {1, 1, 1, 1, 1, 2,  3,  3,  4,  5,  6,  7,  7,  8,  9, 10, 11, 12, 14},  //18.0
                         {1, 1, 1, 1, 1, 2,  3,  3,  4,  5,  6,  6,  7,  8,  9, 10, 11, 12, 13},  //19.0
                         {1, 1, 1, 1, 1, 2,  3,  3,  4,  5,  5,  6,  7,  8,  9, 10, 11, 12, 13},  //20.0
                         {1, 1, 1, 1, 1, 1,  2,  3,  4,  5,  5,  6,  7,  8,  9, 10, 11, 12, 13},  //21.0
                        };
    int tvps_CLO[][] = { {3, 4, 5, 7, 8, 9, 10, 11, 13, 14, 15, 16, 17, 18, 18, 19, 19, 19, 19}, //5.0
                         {3, 4, 5, 6, 7, 8, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 19, 19, 19}, //5.6
                         {2, 3, 4, 5, 7, 8,  9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 19, 19},  //6.0
                         {1, 3, 4, 5, 6, 7,  8,  9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 19},  //6.6
                         {1, 2, 3, 4, 5, 7,  8,  9, 10, 11, 12, 13, 13, 14, 15, 16, 17, 18, 19},  //7.0
                         {1, 2, 3, 4, 5, 6,  7,  8,  9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 18},  //7.6
                         {1, 1, 2, 4, 5, 6,  7,  8,  9, 10, 10, 11, 12, 13, 14, 15, 16, 17, 18},  //8.0
                         {1, 1, 2, 3, 4, 5,  6,  7,  8,  9, 10, 11, 12, 13, 14, 14, 15, 16, 17},  //8.6
                         {1, 1, 2, 3, 4, 5,  6,  7,  8,  9,  9, 10, 11, 12, 13, 14, 15, 16, 17},  //9.0
                         {1, 1, 1, 2, 3, 4,  5,  6,  7,  8,  9, 10, 11, 12, 13, 14, 14, 15, 16},  //9.6
                         {1, 1, 1, 2, 3, 4,  5,  6,  7,  8,  9, 10, 10, 11, 12, 13, 14, 15, 16},  //10.0
                         {1, 1, 1, 2, 3, 4,  5,  6,  7,  8,  8,  9, 10, 11, 12, 13, 14, 15, 16},  //10.6
                         {1, 1, 1, 2, 3, 4,  5,  5,  6,  7,  8,  9, 10, 11, 12, 12, 13, 14, 15},  //11.0
                         {1, 1, 1, 1, 2, 3,  4,  5,  6,  7,  8,  9,  9, 10, 11, 12, 13, 14, 15},  //11.6
                         {1, 1, 1, 1, 2, 3,  4,  5,  6,  6,  7,  8,  9, 10, 11, 12, 13, 14, 15},  //12.0
                         {1, 1, 1, 1, 2, 3,  3,  4,  5,  6,  7,  8,  8,  9, 10, 11, 12, 13, 14},  //13.0
                         {1, 1, 1, 1, 2, 2,  3,  4,  5,  6,  6,  7,  8,  9, 10, 11, 12, 13, 14},  //14.0
                         {1, 1, 1, 1, 1, 2,  3,  4,  5,  5,  6,  7,  8,  9, 10, 11, 12, 13, 14},  //15.0
                         {1, 1, 1, 1, 1, 2,  3,  4,  4,  5,  6,  7,  8,  8,  9, 10, 11, 13, 14},  //16.0
                         {1, 1, 1, 1, 1, 2,  3,  4,  4,  5,  6,  7,  7,  8,  9, 10, 11, 12, 14},  //17.0
                         {1, 1, 1, 1, 1, 2,  3,  3,  4,  5,  6,  7,  7,  8,  9, 10, 11, 12, 14},  //18.0
                         {1, 1, 1, 1, 1, 2,  3,  3,  4,  5,  6,  6,  7,  8,  9, 10, 11, 12, 13},  //19.0
                         {1, 1, 1, 1, 1, 2,  3,  3,  4,  5,  5,  6,  7,  8,  9, 10, 11, 12, 13},  //20.0
                         {1, 1, 1, 1, 1, 1,  2,  3,  4,  5,  5,  6,  7,  8,  9, 10, 11, 12, 13},  //21.0
                        };
    
    int tvps_BASIC[] = {50, 51, 52, 54, 55, 56, 57, 59, 60, 61, 62, 64, 65, 66, 67, 69,
                        70, 71, 72, 74, 75, 76, 77, 79, 80, 81, 83, 84, 85, 86, 88, 
                        89, 90, 91, 93, 94, 95, 96, 98, 99, 100, 101, 103, 104, 105, 106,
                        108, 109, 110, 111, 113, 114, 115, 117, 118, 119, 120, 122, 123, 124, 125,
                        127, 128, 129, 130, 132, 133, 134, 135, 137, 138, 139, 140, 142, 143, 144, 145} ;
    
    int tvps_IdxSEQ[] = {50, 55, 60, 65, 70, 75, 80, 85, 90, 95, 100, 105, 110, 115, 120, 125,
                         130, 135, 140, 145} ;
    
    int tvps_CPLX[] =   {50, 52, 55, 58, 60, 62, 65, 68, 70, 73, 75, 78, 80, 83, 85, 88,
                         90, 93, 95, 98, 100, 103, 105, 108, 110, 113, 115, 118, 120, 123, 125,
                         128, 130, 133, 135, 138, 140, 143, 145} ;
    
    int tvps_ALL[] =    {0, 0, 0, 0, 0, 0, 0, 55, 56, 56, 57, 57, 58, 59, 60, 60, 61, 62, 63, 63,
                         64, 65, 66, 67, 67, 68, 69, 69, 70, 71, 72, 72, 73, 73, 74, 75, 76, 76, 77, 78,
                         79, 79, 80, 80, 81, 82, 83, 83, 84, 85, 86, 86, 87, 87, 88, 89, 90, 90, 91, 92,
                         93, 93, 94, 95, 96, 96, 97, 98, 99, 99, 100, 100, 101, 102, 103, 103, 104, 105, 106, 106,
                         107, 108, 109, 109, 110, 110, 111, 112, 113, 113, 114, 115, 116, 116, 117, 118, 119, 119, 120, 120,
                         121, 122, 123, 123, 124, 125, 126, 126, 127, 128, 129, 129, 130, 130, 131,
                         132, 133, 133, 134, 134, 135, 136, 137, 138, 139, 139, 140, 140, 141, 142, 143, 143, 144, 145} ;

    
    /**
     * Creates new form Panel_TVPS
     */
    public Panel_TVPS4() {
        initComponents();
        
        //Chge color button
        jChgeColor = new ColorChooserButton(new Color(51, 153, 255,155));
        jChgeColor.setBounds(jScoresUnit.getX(), jScoresUnit.getY()+50, 100, 25);
        jChgeColor.setText("Couleur");
        jChgeColor.setEnabled(false);
        this.add (jChgeColor) ;
        jChgeColor.addColorChangedListener(new ColorChangedListener() {
            @Override
            public void colorChanged(Color newColor) {
                    // do something with newColor ...
                    if (OrthoCotation.baseValues.patientAge.years != 0) 
                        OrthoCotation.barChartTVPS4.changeColor(newColor);
            }
        });
        //Scaled or Standard button
        

        //Positions
        jIdx_BAS.setLocation(jScl_CLO.getX(), jLabel12.getY()-2);
        jPctl_BAS.setLocation(jPctl_CLO.getX(), jLabel12.getY()-2);
        jStd_BAS.setLocation(jPctl_CLO.getX()+jPctl_CLO.getX()-jIdx_BAS.getX(), jLabel12.getY()-2);
        
        jIdx_SEQ.setLocation(jScl_CLO.getX(), jIdx_BAS.getY()+ (jScl_CLO.getY()-jScl_FGR.getY()));
        jPctl_IdxSEQ.setLocation(jPctl_CLO.getX(), jIdx_BAS.getY()+ (jScl_CLO.getY()-jScl_FGR.getY()));
        jIdxStd_SEQ.setLocation(jPctl_CLO.getX()+jPctl_CLO.getX()-jIdx_BAS.getX(), jIdx_BAS.getY()+ (jScl_CLO.getY()-jScl_FGR.getY()));
        
        jLabel13.setLocation(jLabel12.getX(), jIdx_SEQ.getY()+2);
        jIdx_CPX.setLocation(jScl_CLO.getX(), jIdx_SEQ.getY()+ (jScl_CLO.getY()-jScl_FGR.getY()));
        jPctl_CPX.setLocation(jPctl_CLO.getX(), jIdx_SEQ.getY()+ (jScl_CLO.getY()-jScl_FGR.getY()));
        jIdxStd_CPX.setLocation(jPctl_CLO.getX()+jPctl_CLO.getX()-jIdx_BAS.getX(), jIdx_SEQ.getY()+ (jScl_CLO.getY()-jScl_FGR.getY()));
        
        jLabel14.setLocation(jLabel12.getX(), jIdx_CPX.getY()+2);
        jSepIdx.setLocation(jLabel14.getX(), jLabel14.getY()+25);
        jIdx_ALL.setLocation(jScl_CLO.getX(), jIdx_CPX.getY()+ (jScl_CLO.getY()-jScl_FGR.getY()));
        jPctl_ALL.setLocation(jPctl_CLO.getX(), jIdx_CPX.getY()+ (jScl_CLO.getY()-jScl_FGR.getY()));
        jIdxStd_ALL.setLocation(jPctl_CLO.getX()+jPctl_CLO.getX()-jIdx_BAS.getX(), jIdx_CPX.getY()+ (jScl_CLO.getY()-jScl_FGR.getY()));
        jLabel16.setLocation(jLabel12.getX(), jIdx_ALL.getY()+2);
        
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
        if (OrthoCotation.baseValues.patientAge.years < 5) return 0 ;
        else if (OrthoCotation.baseValues.patientAge.years < 12) {
            idx = (OrthoCotation.baseValues.patientAge.years - 5) * 2 ;
            if (OrthoCotation.baseValues.patientAge.months >5) idx++ ;
        }
        else {
            idx = OrthoCotation.baseValues.patientAge.years + 2 ;
        }
        
        if (idx >= tvps_DIS.length) idx = tvps_DIS.length-1;
        System.out.println (idx) ;
        return idx ;
    }
    
    public void updateResults () {
        int tvps_idx[] = new int [4] ;
        
        tvpsSclValues[0] = (int) jDIS.getValue() ;
        tvpsSclValues[1] = (int) jMEM.getValue() ;
        tvpsSclValues[2] = (int) jSPA.getValue() ;
        tvpsSclValues[3] = (int) jCON.getValue() ;
        tvpsSclValues[4] = (int) jSEQ.getValue() ;
        tvpsSclValues[5] = (int) jFGR.getValue() ;
        tvpsSclValues[6] = (int) jCLO.getValue() ;
        
        /*if (OrthoCotation.user.nom == null) return ;
        if (OrthoCotation.baseValues.patientAge.years == 0) return ;
        jMsgTVPS.setText(null);*/
        
        //update column transposition
        int j = 0 ;
        for (int i=0; i<7; i++)
            if (tvpsChkValues[i]) transposeColumn[j++] = i ;
        
        //DIS
        tvpsSclValues[0] = tvps_DIS[indexAge()][tvpsSclValues[0]] ;
        jScl_DIS.setText(String.valueOf(tvpsSclValues[0]));
        double t = ( (double) tvpsSclValues[0] - 10.0 ) / 3.0 ;
        tvpsPctlValues[0] = (int) Math.round(zScoreToPercentile(t)) ;
        jPctl_DIS.setText(String.valueOf (tvpsPctlValues[0])) ;
        jStd_DIS.setText(String.valueOf (tvpsSclValues[0]*5+50)) ;
        //MEM
        tvpsSclValues[1] = tvps_MEM[indexAge()][tvpsSclValues[1]] ;
        jScl_MEM.setText(String.valueOf(tvpsSclValues[1]));
        t = ( (double) tvpsSclValues[1] - 10.0 ) / 3.0 ;
        tvpsPctlValues[1] = (int) Math.round(zScoreToPercentile(t)) ;
        jPctl_MEM.setText(String.valueOf (tvpsPctlValues[1])) ;
        jStd_MEM.setText(String.valueOf (tvpsSclValues[1]*5+50)) ;
        //SPA
        tvpsSclValues[2] = tvps_SPA[indexAge()][tvpsSclValues[2]] ;
        jScl_SPA.setText(String.valueOf(tvpsSclValues[2]));
        t = ( (double) tvpsSclValues[2] - 10.0 ) / 3.0 ;
        tvpsPctlValues[2] = (int) Math.round(zScoreToPercentile(t)) ;
        jPctl_SPA.setText(String.valueOf (tvpsPctlValues[2])) ;
        jStd_SPA.setText(String.valueOf (tvpsSclValues[2]*5+50)) ;
        //CON
        tvpsSclValues[3] = tvps_CON[indexAge()][tvpsSclValues[3]] ;
        jScl_CON.setText(String.valueOf(tvpsSclValues[3]));
        t = ( (double) tvpsSclValues[3] - 10.0 ) / 3.0 ;
        tvpsPctlValues[3] = (int) Math.round(zScoreToPercentile(t)) ;
        jPctl_CON.setText(String.valueOf (tvpsPctlValues[3])) ;
        jStd_CON.setText(String.valueOf (tvpsSclValues[3]*5+50)) ;
        //SEQ
        tvpsSclValues[4] = tvps_SEQ[indexAge()][tvpsSclValues[4]] ;
        jScl_SEQ.setText(String.valueOf(tvpsSclValues[4]));
        t = ( (double) tvpsSclValues[4] - 10.0 ) / 3.0 ;
        tvpsPctlValues[4] = (int) Math.round(zScoreToPercentile(t)) ;
        jPctl_SEQ.setText(String.valueOf (tvpsPctlValues[4])) ;
        jStd_SEQ.setText(String.valueOf (tvpsSclValues[4]*5+50)) ;
        //FGR
        tvpsSclValues[5] = tvps_FGR[indexAge()][tvpsSclValues[5]] ;
        jScl_FGR.setText(String.valueOf(tvpsSclValues[5]));
        t = ( (double) tvpsSclValues[5] - 10.0 ) / 3.0 ;
        tvpsPctlValues[5] = (int) Math.round(zScoreToPercentile(t)) ;
        jPctl_FGR.setText(String.valueOf (tvpsPctlValues[5])) ;
        jStd_FGR.setText(String.valueOf (tvpsSclValues[5]*5+50)) ;
        //CLO
        tvpsSclValues[6] = tvps_CLO[indexAge()][tvpsSclValues[6]] ;
        jScl_CLO.setText(String.valueOf(tvpsSclValues[6]));
        t = ( (double) tvpsSclValues[6] - 10.0 ) / 3.0 ;
        tvpsPctlValues[6] = (int) Math.round(zScoreToPercentile(t)) ;
        jPctl_CLO.setText(String.valueOf (tvpsPctlValues[6])) ;
        jStd_CLO.setText(String.valueOf (tvpsSclValues[6]*5+50)) ;
        
        //Basic proc.
        tvps_idx[0] = tvpsSclValues[0] + tvpsSclValues[1] + tvpsSclValues[2] + tvpsSclValues[3] ;
        jIdx_BAS.setText(String.valueOf (tvps_idx[0])) ;
        jStd_BAS.setText(String.valueOf (tvps_BASIC[tvps_idx[0]])) ;
        t = ((double) tvps_BASIC[tvps_idx[0]] -100.0 ) / 15.0 ;
        jPctl_BAS.setText(String.valueOf ((int) Math.round(zScoreToPercentile(t)))) ;
        //Sequencing
        tvps_idx[1] = tvpsSclValues[4] ;
        jIdx_SEQ.setText(String.valueOf (tvps_idx[1])) ;
        jIdxStd_SEQ.setText(String.valueOf (tvps_IdxSEQ[tvps_idx[1]])) ;
        t = ((double) tvps_IdxSEQ[tvps_idx[1]] -100.0 ) / 15.0 ;
        jPctl_IdxSEQ.setText(String.valueOf ((int) Math.round(zScoreToPercentile(t)))) ;
        //Complex
        tvps_idx[2] = tvpsSclValues[5] + tvpsSclValues[6] ;
        jIdx_CPX.setText(String.valueOf (tvps_idx[2])) ;
        jIdxStd_CPX.setText(String.valueOf (tvps_CPLX[tvps_idx[2]])) ;
        t = ((double) tvps_CPLX[tvps_idx[2]] -100.0 ) / 15.0 ;
        jPctl_CPX.setText(String.valueOf ((int) Math.round(zScoreToPercentile(t)))) ;
        //Overall
        int tvps_all = tvps_idx[0] + tvps_idx[1] + tvps_idx[2] ;
        jIdx_ALL.setText(String.valueOf (tvps_all)) ;
        if (tvps_all < 7) jIdxStd_ALL.setText("<55") ;
        else jIdxStd_ALL.setText(String.valueOf (tvps_ALL[tvps_all])) ;
        t = ((double) tvps_ALL[tvps_all] -100.0 ) / 15.0 ;
        jPctl_ALL.setText(String.valueOf ((int) Math.round(zScoreToPercentile(t)))) ;
        
        OrthoCotation.barChartTVPS4.updateGraph () ;
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
        jSepIdx = new javax.swing.JSeparator();
        jMsgTVPS = new javax.swing.JLabel();
        jDIS = new javax.swing.JSpinner();
        jMEM = new javax.swing.JSpinner();
        jSPA = new javax.swing.JSpinner();
        jCON = new javax.swing.JSpinner();
        jSEQ = new javax.swing.JSpinner();
        jFGR = new javax.swing.JSpinner();
        jCLO = new javax.swing.JSpinner();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jScl_DIS = new javax.swing.JTextField();
        jScl_MEM = new javax.swing.JTextField();
        jScl_SPA = new javax.swing.JTextField();
        jScl_CON = new javax.swing.JTextField();
        jScl_SEQ = new javax.swing.JTextField();
        jScl_FGR = new javax.swing.JTextField();
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
        jScl_CLO = new javax.swing.JTextField();
        jIdx_BAS = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jIdx_CPX = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel16 = new javax.swing.JLabel();
        jIdx_ALL = new javax.swing.JTextField();
        jPctl_BAS = new javax.swing.JTextField();
        jPctl_IdxSEQ = new javax.swing.JTextField();
        jPctl_CPX = new javax.swing.JTextField();
        jPctl_ALL = new javax.swing.JTextField();
        jStd_BAS = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jIdxStd_SEQ = new javax.swing.JTextField();
        jIdxStd_CPX = new javax.swing.JTextField();
        jIdxStd_ALL = new javax.swing.JTextField();
        jCheckDIS = new javax.swing.JCheckBox();
        jCheckMEM = new javax.swing.JCheckBox();
        jCheckSPA = new javax.swing.JCheckBox();
        jCheckCON = new javax.swing.JCheckBox();
        jCheckSEQ = new javax.swing.JCheckBox();
        jCheckFGR = new javax.swing.JCheckBox();
        jCheckCLO = new javax.swing.JCheckBox();
        jCheckSousTitre = new javax.swing.JCheckBox();
        jStd_CLO = new javax.swing.JTextField();
        jStd_FGR = new javax.swing.JTextField();
        jStd_SEQ = new javax.swing.JTextField();
        jStd_CON = new javax.swing.JTextField();
        jStd_SPA = new javax.swing.JTextField();
        jStd_MEM = new javax.swing.JTextField();
        jStd_DIS = new javax.swing.JTextField();
        jScoresUnit = new javax.swing.JComboBox<>();

        setBackground(new java.awt.Color(204, 255, 255));
        setMaximumSize(new java.awt.Dimension(900, 550));
        setMinimumSize(new java.awt.Dimension(900, 550));
        setPreferredSize(new java.awt.Dimension(900, 550));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setForeground(java.awt.Color.gray);
        jLabel15.setText("TVPS-4");

        jMsgTVPS.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        jMsgTVPS.setForeground(java.awt.Color.red);
        jMsgTVPS.setText("EN COURS !! VALEURS NON VALIDES");

        jDIS.setModel(new javax.swing.SpinnerNumberModel(11, 1, 18, 1));
        jDIS.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jDISStateChanged(evt);
            }
        });

        jMEM.setModel(new javax.swing.SpinnerNumberModel(11, 1, 18, 1));
        jMEM.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jMEMStateChanged(evt);
            }
        });

        jSPA.setModel(new javax.swing.SpinnerNumberModel(11, 1, 18, 1));
        jSPA.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSPAStateChanged(evt);
            }
        });

        jCON.setModel(new javax.swing.SpinnerNumberModel(11, 1, 18, 1));
        jCON.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jCONStateChanged(evt);
            }
        });

        jSEQ.setModel(new javax.swing.SpinnerNumberModel(11, 1, 18, 1));
        jSEQ.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSEQStateChanged(evt);
            }
        });

        jFGR.setModel(new javax.swing.SpinnerNumberModel(11, 1, 18, 1));
        jFGR.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jFGRStateChanged(evt);
            }
        });

        jCLO.setModel(new javax.swing.SpinnerNumberModel(11, 1, 18, 1));
        jCLO.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jCLOStateChanged(evt);
            }
        });

        jLabel8.setText("Raw");
        jLabel8.setToolTipText("Score brut");

        jLabel9.setText("Scaled");
        jLabel9.setToolTipText("Scaled Score");

        jScl_DIS.setEditable(false);
        jScl_DIS.setBackground(new java.awt.Color(255, 255, 255));
        jScl_DIS.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jScl_DIS.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jScl_DIS.setText("na");

        jScl_MEM.setEditable(false);
        jScl_MEM.setBackground(new java.awt.Color(255, 255, 255));
        jScl_MEM.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jScl_MEM.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jScl_MEM.setText("na");
        jScl_MEM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jScl_MEMActionPerformed(evt);
            }
        });

        jScl_SPA.setEditable(false);
        jScl_SPA.setBackground(new java.awt.Color(255, 255, 255));
        jScl_SPA.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jScl_SPA.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jScl_SPA.setText("na");
        jScl_SPA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jScl_SPAActionPerformed(evt);
            }
        });

        jScl_CON.setEditable(false);
        jScl_CON.setBackground(new java.awt.Color(255, 255, 255));
        jScl_CON.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jScl_CON.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jScl_CON.setText("na");
        jScl_CON.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jScl_CONActionPerformed(evt);
            }
        });

        jScl_SEQ.setEditable(false);
        jScl_SEQ.setBackground(new java.awt.Color(255, 255, 255));
        jScl_SEQ.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jScl_SEQ.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jScl_SEQ.setText("na");
        jScl_SEQ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jScl_SEQActionPerformed(evt);
            }
        });

        jScl_FGR.setEditable(false);
        jScl_FGR.setBackground(new java.awt.Color(255, 255, 255));
        jScl_FGR.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jScl_FGR.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jScl_FGR.setText("na");
        jScl_FGR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jScl_FGRActionPerformed(evt);
            }
        });

        jIdx_SEQ.setEditable(false);
        jIdx_SEQ.setBackground(new java.awt.Color(255, 255, 255));
        jIdx_SEQ.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jIdx_SEQ.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jIdx_SEQ.setText("na");

        jLabel10.setText("Pctl");
        jLabel10.setToolTipText("Percentile");

        jPctl_DIS.setEditable(false);
        jPctl_DIS.setBackground(new java.awt.Color(255, 255, 255));
        jPctl_DIS.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jPctl_DIS.setText("na");

        jPctl_MEM.setEditable(false);
        jPctl_MEM.setBackground(new java.awt.Color(255, 255, 255));
        jPctl_MEM.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jPctl_MEM.setText("na");

        jPctl_SPA.setEditable(false);
        jPctl_SPA.setBackground(new java.awt.Color(255, 255, 255));
        jPctl_SPA.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jPctl_SPA.setText("na");

        jPctl_CON.setEditable(false);
        jPctl_CON.setBackground(new java.awt.Color(255, 255, 255));
        jPctl_CON.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jPctl_CON.setText("na");

        jPctl_SEQ.setEditable(false);
        jPctl_SEQ.setBackground(new java.awt.Color(255, 255, 255));
        jPctl_SEQ.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jPctl_SEQ.setText("na");

        jPctl_FGR.setEditable(false);
        jPctl_FGR.setBackground(new java.awt.Color(255, 255, 255));
        jPctl_FGR.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jPctl_FGR.setText("na");

        jPctl_CLO.setEditable(false);
        jPctl_CLO.setBackground(new java.awt.Color(255, 255, 255));
        jPctl_CLO.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jPctl_CLO.setText("na");

        jCheckRange.setText("Normal Range");
        jCheckRange.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckRangeActionPerformed(evt);
            }
        });

        jCheckTitre.setSelected(true);
        jCheckTitre.setText("Titre");
        jCheckTitre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckTitreActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setText("INDEX SCORES :");

        jLabel12.setText("Basic Proc. :");

        jLabel13.setText("Sequencing :");

        jScl_CLO.setEditable(false);
        jScl_CLO.setBackground(new java.awt.Color(255, 255, 255));
        jScl_CLO.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jScl_CLO.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jScl_CLO.setText("na");

        jIdx_BAS.setEditable(false);
        jIdx_BAS.setBackground(new java.awt.Color(255, 255, 255));
        jIdx_BAS.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jIdx_BAS.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jIdx_BAS.setText("na");

        jLabel14.setText("Complex Proc. :");

        jIdx_CPX.setEditable(false);
        jIdx_CPX.setBackground(new java.awt.Color(255, 255, 255));
        jIdx_CPX.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jIdx_CPX.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jIdx_CPX.setText("na");

        jLabel16.setText("Overall :");

        jIdx_ALL.setEditable(false);
        jIdx_ALL.setBackground(new java.awt.Color(255, 255, 255));
        jIdx_ALL.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jIdx_ALL.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jIdx_ALL.setText("na");

        jPctl_BAS.setEditable(false);
        jPctl_BAS.setBackground(new java.awt.Color(255, 255, 255));
        jPctl_BAS.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jPctl_BAS.setText("na");

        jPctl_IdxSEQ.setEditable(false);
        jPctl_IdxSEQ.setBackground(new java.awt.Color(255, 255, 255));
        jPctl_IdxSEQ.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jPctl_IdxSEQ.setText("na");

        jPctl_CPX.setEditable(false);
        jPctl_CPX.setBackground(new java.awt.Color(255, 255, 255));
        jPctl_CPX.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jPctl_CPX.setText("na");

        jPctl_ALL.setEditable(false);
        jPctl_ALL.setBackground(new java.awt.Color(255, 255, 255));
        jPctl_ALL.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jPctl_ALL.setText("na");

        jStd_BAS.setEditable(false);
        jStd_BAS.setBackground(new java.awt.Color(255, 255, 255));
        jStd_BAS.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jStd_BAS.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jStd_BAS.setText("na");

        jLabel17.setText("Std");
        jLabel17.setToolTipText("Score standard");

        jIdxStd_SEQ.setEditable(false);
        jIdxStd_SEQ.setBackground(new java.awt.Color(255, 255, 255));
        jIdxStd_SEQ.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jIdxStd_SEQ.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jIdxStd_SEQ.setText("na");

        jIdxStd_CPX.setEditable(false);
        jIdxStd_CPX.setBackground(new java.awt.Color(255, 255, 255));
        jIdxStd_CPX.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jIdxStd_CPX.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jIdxStd_CPX.setText("na");

        jIdxStd_ALL.setEditable(false);
        jIdxStd_ALL.setBackground(new java.awt.Color(255, 255, 255));
        jIdxStd_ALL.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jIdxStd_ALL.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jIdxStd_ALL.setText("na");

        jCheckDIS.setSelected(true);
        jCheckDIS.setText("DIS :");
        jCheckDIS.setToolTipText("Discrimination");
        jCheckDIS.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jCheckDISStateChanged(evt);
            }
        });

        jCheckMEM.setSelected(true);
        jCheckMEM.setText("MEM :");
        jCheckMEM.setToolTipText("Mémoire");
        jCheckMEM.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jCheckMEMStateChanged(evt);
            }
        });

        jCheckSPA.setSelected(true);
        jCheckSPA.setText("SPA :");
        jCheckSPA.setToolTipText("Spatial");
        jCheckSPA.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jCheckSPAStateChanged(evt);
            }
        });

        jCheckCON.setSelected(true);
        jCheckCON.setText("CON :");
        jCheckCON.setToolTipText("Constance de forme");
        jCheckCON.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jCheckCONStateChanged(evt);
            }
        });

        jCheckSEQ.setSelected(true);
        jCheckSEQ.setText("SEQ :");
        jCheckSEQ.setToolTipText("Mémoire de séquences");
        jCheckSEQ.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jCheckSEQStateChanged(evt);
            }
        });

        jCheckFGR.setSelected(true);
        jCheckFGR.setText("FGR :");
        jCheckFGR.setToolTipText("Figure/Fond");
        jCheckFGR.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jCheckFGRStateChanged(evt);
            }
        });

        jCheckCLO.setSelected(true);
        jCheckCLO.setText("CLO :");
        jCheckCLO.setToolTipText("Closure");
        jCheckCLO.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jCheckCLOStateChanged(evt);
            }
        });

        jCheckSousTitre.setSelected(true);
        jCheckSousTitre.setText("\"Subtests\"");
        jCheckSousTitre.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jCheckSousTitreStateChanged(evt);
            }
        });

        jStd_CLO.setEditable(false);
        jStd_CLO.setBackground(new java.awt.Color(255, 255, 255));
        jStd_CLO.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jStd_CLO.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jStd_CLO.setText("na");

        jStd_FGR.setEditable(false);
        jStd_FGR.setBackground(new java.awt.Color(255, 255, 255));
        jStd_FGR.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jStd_FGR.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jStd_FGR.setText("na");

        jStd_SEQ.setEditable(false);
        jStd_SEQ.setBackground(new java.awt.Color(255, 255, 255));
        jStd_SEQ.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jStd_SEQ.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jStd_SEQ.setText("na");

        jStd_CON.setEditable(false);
        jStd_CON.setBackground(new java.awt.Color(255, 255, 255));
        jStd_CON.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jStd_CON.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jStd_CON.setText("na");

        jStd_SPA.setEditable(false);
        jStd_SPA.setBackground(new java.awt.Color(255, 255, 255));
        jStd_SPA.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jStd_SPA.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jStd_SPA.setText("na");

        jStd_MEM.setEditable(false);
        jStd_MEM.setBackground(new java.awt.Color(255, 255, 255));
        jStd_MEM.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jStd_MEM.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jStd_MEM.setText("na");

        jStd_DIS.setEditable(false);
        jStd_DIS.setBackground(new java.awt.Color(255, 255, 255));
        jStd_DIS.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jStd_DIS.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jStd_DIS.setText("na");

        jScoresUnit.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Scaled scores", "Standard Scores" }));
        jScoresUnit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jScoresUnitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCheckDIS)
                            .addComponent(jCheckMEM)
                            .addComponent(jCheckSPA)
                            .addComponent(jCheckCON)
                            .addComponent(jCheckSEQ)
                            .addComponent(jCheckFGR)
                            .addComponent(jCheckCLO))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jDIS, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jMEM, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jCON, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScl_DIS, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScl_MEM, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScl_CON, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jSPA, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScl_SPA, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jSEQ, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScl_SEQ, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jFGR, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScl_FGR, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jCLO, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScl_CLO, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(57, 57, 57)
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(25, 25, 25))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jIdx_BAS, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jIdx_SEQ, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jIdx_CPX, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jIdx_ALL, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPctl_BAS, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPctl_IdxSEQ, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPctl_CPX, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPctl_ALL, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jIdxStd_SEQ, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jIdxStd_CPX, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jIdxStd_ALL, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jStd_BAS, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScoresUnit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jCheckTitre, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jCheckRange, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jCheckSousTitre, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(108, 108, 108))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addGap(61, 61, 61)
                                .addComponent(jMsgTVPS, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 518, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel11))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(232, 232, 232)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPctl_DIS, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPctl_MEM, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPctl_SPA, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPctl_CON, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPctl_SEQ, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPctl_FGR, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPctl_CLO, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(53, 53, 53)
                                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(41, 41, 41)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jStd_FGR, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jStd_CLO, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jStd_SEQ, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jStd_CON, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jStd_SPA, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jStd_MEM, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jStd_DIS, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSepIdx, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 518, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(372, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addComponent(jMsgTVPS))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel8)
                    .addComponent(jLabel10)
                    .addComponent(jLabel17))
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jDIS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckDIS)
                    .addComponent(jScl_DIS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPctl_DIS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jStd_DIS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jMEM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckMEM)
                    .addComponent(jScl_MEM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPctl_MEM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jStd_MEM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jSPA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckSPA)
                    .addComponent(jScl_SPA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPctl_SPA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jStd_SPA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCON, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckCON)
                    .addComponent(jScl_CON, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPctl_CON, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jStd_CON, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jSEQ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckSEQ)
                    .addComponent(jScl_SEQ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPctl_SEQ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jStd_SEQ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFGR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckFGR)
                    .addComponent(jScl_FGR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPctl_FGR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jStd_FGR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCLO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckCLO)
                    .addComponent(jScl_CLO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPctl_CLO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jStd_CLO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jCheckTitre)
                            .addComponent(jCheckRange)
                            .addComponent(jScoresUnit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jCheckSousTitre))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel12)
                                .addComponent(jIdx_BAS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPctl_BAS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jStd_BAS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jIdx_SEQ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel13))
                            .addComponent(jPctl_IdxSEQ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jIdxStd_SEQ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jIdx_CPX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel14))
                            .addComponent(jPctl_CPX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jIdxStd_CPX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addComponent(jSepIdx, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jIdx_ALL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel16))
                    .addComponent(jPctl_ALL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jIdxStd_ALL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35))
        );
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

    private void jScl_MEMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jScl_MEMActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jScl_MEMActionPerformed

    private void jScl_SPAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jScl_SPAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jScl_SPAActionPerformed

    private void jScl_CONActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jScl_CONActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jScl_CONActionPerformed

    private void jScl_SEQActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jScl_SEQActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jScl_SEQActionPerformed

    private void jScl_FGRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jScl_FGRActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jScl_FGRActionPerformed

    private void jCheckRangeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckRangeActionPerformed
        OrthoCotation.barChartTVPS4.changeAspect (jCheckTitre.isSelected(), jCheckSousTitre.isSelected(), jCheckRange.isSelected()) ;
    }//GEN-LAST:event_jCheckRangeActionPerformed

    private void jCheckTitreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckTitreActionPerformed
        OrthoCotation.barChartTVPS4.changeAspect (jCheckTitre.isSelected(), jCheckSousTitre.isSelected(), jCheckRange.isSelected()) ;
    }//GEN-LAST:event_jCheckTitreActionPerformed

    private void jCheckDISStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jCheckDISStateChanged
        tvpsChkValues[0] = jCheckDIS.isSelected() ;
        updateResults () ;
    }//GEN-LAST:event_jCheckDISStateChanged

    private void jCheckMEMStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jCheckMEMStateChanged
        tvpsChkValues[1] = jCheckMEM.isSelected() ;
        updateResults () ;
    }//GEN-LAST:event_jCheckMEMStateChanged

    private void jCheckSPAStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jCheckSPAStateChanged
        tvpsChkValues[2] = jCheckSPA.isSelected() ;
        updateResults () ;
    }//GEN-LAST:event_jCheckSPAStateChanged

    private void jCheckCONStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jCheckCONStateChanged
        tvpsChkValues[3] = jCheckCON.isSelected() ;
        updateResults () ;
    }//GEN-LAST:event_jCheckCONStateChanged

    private void jCheckSEQStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jCheckSEQStateChanged
        tvpsChkValues[4] = jCheckSEQ.isSelected() ;
        updateResults () ;
    }//GEN-LAST:event_jCheckSEQStateChanged

    private void jCheckFGRStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jCheckFGRStateChanged
        tvpsChkValues[5] = jCheckFGR.isSelected() ;
        updateResults () ;
    }//GEN-LAST:event_jCheckFGRStateChanged

    private void jCheckCLOStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jCheckCLOStateChanged
        tvpsChkValues[6] = jCheckCLO.isSelected() ;
        updateResults () ;
    }//GEN-LAST:event_jCheckCLOStateChanged

    private void jCheckSousTitreStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jCheckSousTitreStateChanged
        OrthoCotation.barChartTVPS4.changeAspect (jCheckTitre.isSelected(), jCheckSousTitre.isSelected(), jCheckRange.isSelected()) ;
    }//GEN-LAST:event_jCheckSousTitreStateChanged

    private void jScoresUnitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jScoresUnitActionPerformed
        OrthoCotation.barChartTVPS4.updateVisibleScores();
    }//GEN-LAST:event_jScoresUnitActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSpinner jCLO;
    private javax.swing.JSpinner jCON;
    private javax.swing.JCheckBox jCheckCLO;
    private javax.swing.JCheckBox jCheckCON;
    private javax.swing.JCheckBox jCheckDIS;
    private javax.swing.JCheckBox jCheckFGR;
    private javax.swing.JCheckBox jCheckMEM;
    public static javax.swing.JCheckBox jCheckRange;
    private javax.swing.JCheckBox jCheckSEQ;
    private javax.swing.JCheckBox jCheckSPA;
    public static javax.swing.JCheckBox jCheckSousTitre;
    public static javax.swing.JCheckBox jCheckTitre;
    private javax.swing.JSpinner jDIS;
    private javax.swing.JSpinner jFGR;
    private javax.swing.JTextField jIdxStd_ALL;
    private javax.swing.JTextField jIdxStd_CPX;
    private javax.swing.JTextField jIdxStd_SEQ;
    private javax.swing.JTextField jIdx_ALL;
    private javax.swing.JTextField jIdx_BAS;
    private javax.swing.JTextField jIdx_CPX;
    private javax.swing.JTextField jIdx_SEQ;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JSpinner jMEM;
    public static javax.swing.JLabel jMsgTVPS;
    private javax.swing.JTextField jPctl_ALL;
    private javax.swing.JTextField jPctl_BAS;
    private javax.swing.JTextField jPctl_CLO;
    private javax.swing.JTextField jPctl_CON;
    private javax.swing.JTextField jPctl_CPX;
    private javax.swing.JTextField jPctl_DIS;
    private javax.swing.JTextField jPctl_FGR;
    private javax.swing.JTextField jPctl_IdxSEQ;
    private javax.swing.JTextField jPctl_MEM;
    private javax.swing.JTextField jPctl_SEQ;
    private javax.swing.JTextField jPctl_SPA;
    private javax.swing.JSpinner jSEQ;
    private javax.swing.JSpinner jSPA;
    private javax.swing.JTextField jScl_CLO;
    private javax.swing.JTextField jScl_CON;
    private javax.swing.JTextField jScl_DIS;
    private javax.swing.JTextField jScl_FGR;
    private javax.swing.JTextField jScl_MEM;
    private javax.swing.JTextField jScl_SEQ;
    private javax.swing.JTextField jScl_SPA;
    public static javax.swing.JComboBox<String> jScoresUnit;
    private javax.swing.JSeparator jSepIdx;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTextField jStd_BAS;
    private javax.swing.JTextField jStd_CLO;
    private javax.swing.JTextField jStd_CON;
    private javax.swing.JTextField jStd_DIS;
    private javax.swing.JTextField jStd_FGR;
    private javax.swing.JTextField jStd_MEM;
    private javax.swing.JTextField jStd_SEQ;
    private javax.swing.JTextField jStd_SPA;
    // End of variables declaration//GEN-END:variables
}

