package com.fmaillet.orthocotation;

import com.fmaillet.orthocotation.ColorChooserButton.ColorChangedListener;
import java.awt.Color;
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
    public static boolean tvpsChkValues[] = {true, true, true, true, true, true, true } ;
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
        jPctl_BAS.setLocation(jPctl_CLO.getX(), jLabel12.getY()-2);
        jStd_BAS.setLocation(jPctl_CLO.getX()+jPctl_CLO.getX()-jIdx_BAS.getX(), jLabel12.getY()-2);
        
        jIdx_SEQ.setLocation(jStd_CLO.getX(), jIdx_BAS.getY()+ (jStd_CLO.getY()-jStd_FGR.getY()));
        jPctl_IdxSEQ.setLocation(jPctl_CLO.getX(), jIdx_BAS.getY()+ (jStd_CLO.getY()-jStd_FGR.getY()));
        jIdxStd_SEQ.setLocation(jPctl_CLO.getX()+jPctl_CLO.getX()-jIdx_BAS.getX(), jIdx_BAS.getY()+ (jStd_CLO.getY()-jStd_FGR.getY()));
        
        jLabel13.setLocation(jLabel12.getX(), jIdx_SEQ.getY()+2);
        jIdx_CPX.setLocation(jStd_CLO.getX(), jIdx_SEQ.getY()+ (jStd_CLO.getY()-jStd_FGR.getY()));
        jPctl_CPX.setLocation(jPctl_CLO.getX(), jIdx_SEQ.getY()+ (jStd_CLO.getY()-jStd_FGR.getY()));
        jIdxStd_CPX.setLocation(jPctl_CLO.getX()+jPctl_CLO.getX()-jIdx_BAS.getX(), jIdx_SEQ.getY()+ (jStd_CLO.getY()-jStd_FGR.getY()));
        
        jLabel14.setLocation(jLabel12.getX(), jIdx_CPX.getY()+2);
        jSepIdx.setLocation(jLabel14.getX(), jLabel14.getY()+25);
        jIdx_ALL.setLocation(jStd_CLO.getX(), jIdx_CPX.getY()+ (jStd_CLO.getY()-jStd_FGR.getY()));
        jPctl_ALL.setLocation(jPctl_CLO.getX(), jIdx_CPX.getY()+ (jStd_CLO.getY()-jStd_FGR.getY()));
        jIdxStd_ALL.setLocation(jPctl_CLO.getX()+jPctl_CLO.getX()-jIdx_BAS.getX(), jIdx_CPX.getY()+ (jStd_CLO.getY()-jStd_FGR.getY()));
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
        int tvps_idx[] = new int [4] ;
        
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
        
        //Basic proc.
        tvps_idx[0] = tvpsStdValues[0] + tvpsStdValues[1] + tvpsStdValues[2] + tvpsStdValues[3] ;
        jIdx_BAS.setText(String.valueOf (tvps_idx[0])) ;
        jStd_BAS.setText(String.valueOf (tvps_BASIC[tvps_idx[0]])) ;
        t = ((double) tvps_BASIC[tvps_idx[0]] -100.0 ) / 15.0 ;
        jPctl_BAS.setText(String.valueOf ((int) Math.round(zScoreToPercentile(t)))) ;
        //Sequencing
        tvps_idx[1] = tvpsStdValues[4] ;
        jIdx_SEQ.setText(String.valueOf (tvps_idx[1])) ;
        jIdxStd_SEQ.setText(String.valueOf (tvps_IdxSEQ[tvps_idx[1]])) ;
        t = ((double) tvps_IdxSEQ[tvps_idx[1]] -100.0 ) / 15.0 ;
        jPctl_IdxSEQ.setText(String.valueOf ((int) Math.round(zScoreToPercentile(t)))) ;
        //Complex
        tvps_idx[2] = tvpsStdValues[5] + tvpsStdValues[6] ;
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

        setMaximumSize(new java.awt.Dimension(800, 550));
        setMinimumSize(new java.awt.Dimension(800, 550));
        setPreferredSize(new java.awt.Dimension(800, 550));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setForeground(java.awt.Color.gray);
        jLabel15.setText("TVPS-3");

        jMsgTVPS.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jMsgTVPS.setForeground(java.awt.Color.red);
        jMsgTVPS.setText("(Pas de calculs en mode démo)");

        jDIS.setModel(new javax.swing.SpinnerNumberModel(11, 1, 16, 1));
        jDIS.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jDISStateChanged(evt);
            }
        });

        jMEM.setModel(new javax.swing.SpinnerNumberModel(11, 1, 16, 1));
        jMEM.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jMEMStateChanged(evt);
            }
        });

        jSPA.setModel(new javax.swing.SpinnerNumberModel(11, 1, 16, 1));
        jSPA.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSPAStateChanged(evt);
            }
        });

        jCON.setModel(new javax.swing.SpinnerNumberModel(11, 1, 16, 1));
        jCON.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jCONStateChanged(evt);
            }
        });

        jSEQ.setModel(new javax.swing.SpinnerNumberModel(11, 1, 16, 1));
        jSEQ.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSEQStateChanged(evt);
            }
        });

        jFGR.setModel(new javax.swing.SpinnerNumberModel(11, 1, 16, 1));
        jFGR.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jFGRStateChanged(evt);
            }
        });

        jCLO.setModel(new javax.swing.SpinnerNumberModel(11, 1, 16, 1));
        jCLO.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jCLOStateChanged(evt);
            }
        });

        jLabel8.setText("Raw");
        jLabel8.setToolTipText("Score brut");

        jLabel9.setText("Scaled");
        jLabel9.setToolTipText("Scaled Score");

        jStd_DIS.setEditable(false);
        jStd_DIS.setBackground(new java.awt.Color(255, 255, 255));
        jStd_DIS.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jStd_DIS.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jStd_DIS.setText("na");

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

        jLabel14.setText("Complex Proc. :");

        jIdx_CPX.setEditable(false);
        jIdx_CPX.setBackground(new java.awt.Color(255, 255, 255));
        jIdx_CPX.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jIdx_CPX.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jIdx_CPX.setText("na");
        jIdx_CPX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jIdx_CPXActionPerformed(evt);
            }
        });

        jLabel16.setText("Overall :");

        jIdx_ALL.setEditable(false);
        jIdx_ALL.setBackground(new java.awt.Color(255, 255, 255));
        jIdx_ALL.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jIdx_ALL.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jIdx_ALL.setText("na");
        jIdx_ALL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jIdx_ALLActionPerformed(evt);
            }
        });

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
        jCheckDIS.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jCheckDISStateChanged(evt);
            }
        });

        jCheckMEM.setSelected(true);
        jCheckMEM.setText("MEM :");
        jCheckMEM.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jCheckMEMStateChanged(evt);
            }
        });

        jCheckSPA.setSelected(true);
        jCheckSPA.setText("SPA :");
        jCheckSPA.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jCheckSPAStateChanged(evt);
            }
        });

        jCheckCON.setSelected(true);
        jCheckCON.setText("CON :");
        jCheckCON.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jCheckCONStateChanged(evt);
            }
        });

        jCheckSEQ.setSelected(true);
        jCheckSEQ.setText("SEQ :");
        jCheckSEQ.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jCheckSEQStateChanged(evt);
            }
        });

        jCheckFGR.setSelected(true);
        jCheckFGR.setText("FGR :");
        jCheckFGR.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jCheckFGRStateChanged(evt);
            }
        });

        jCheckCLO.setSelected(true);
        jCheckCLO.setText("CLO :");
        jCheckCLO.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jCheckCLOStateChanged(evt);
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
                                    .addComponent(jStd_DIS, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jStd_MEM, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jStd_CON, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jSPA, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jStd_SPA, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jSEQ, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jStd_SEQ, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jFGR, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jStd_FGR, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jCLO, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jStd_CLO, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))))
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jCheckTitre, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jCheckRange, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(77, 77, 77))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jIdxStd_SEQ, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jIdxStd_CPX, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jIdxStd_ALL, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jStd_BAS, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(448, Short.MAX_VALUE))))
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
                        .addGap(53, 53, 53)
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSepIdx, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 518, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                    .addComponent(jStd_DIS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPctl_DIS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jMEM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckMEM)
                    .addComponent(jStd_MEM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPctl_MEM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jSPA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckSPA)
                    .addComponent(jStd_SPA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPctl_SPA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCON, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckCON)
                    .addComponent(jStd_CON, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPctl_CON, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jSEQ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckSEQ)
                    .addComponent(jStd_SEQ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPctl_SEQ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFGR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckFGR)
                    .addComponent(jStd_FGR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPctl_FGR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCLO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckCLO)
                    .addComponent(jStd_CLO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPctl_CLO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jCheckTitre)
                            .addComponent(jCheckRange)))
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

    private void jIdx_CPXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jIdx_CPXActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jIdx_CPXActionPerformed

    private void jIdx_ALLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jIdx_ALLActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jIdx_ALLActionPerformed

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

