package Repository;

import GUI.SortPanel;
import Logic.Bar;
import java.awt.Color;

/**********************************************************************
 * Written by: Simon Cicek                                            *
 * Last changed: 2012-04-04                                           *
 *                                                                    *
 * A class holding key information used by many of the other classes. *
 **********************************************************************/

public class Variables 
{
    // Control information
    public static boolean play = false;
    public static int delay = 100;
    public static int elementCount = 42;
    public static int elementHighestValue = 210;
    
    // Bar information
    public static Color pivotBody = Color.red;
    public static Color pivotHead = new Color(205,0,0);
    public static Color barBody = new Color(204,204,204);
    public static Color barHead = Color.darkGray;
    public static Color barLeftHighlightBody = new Color(224,102,255);
    public static Color barRightHighlightBody = new Color(255,131,250);
    public static Color barLeftHighlightHead = new Color(122,55,139);
    public static Color barRightHighlightHead = new Color(204,50,153);
    public static int barWidth = 10;
    public static int barLength = 100000;
    
    // Arrays of bars belonging to the different sorting algorithms
    public static Bar[] qsbars;
    public static Bar[] ssbars;
    public static Bar[] isbars;
    public static Bar[] msbars;
    public static Bar[] bsbars;
    
    // Arrays of values belonging to the different sorting algorithms
    public static int[] quickSort = new int[Variables.elementCount];
    public static int[] mergeSort = new int[Variables.elementCount];
    public static int[] selectionSort = new int[Variables.elementCount];
    public static int[] insertionSort = new int[Variables.elementCount];
    public static int[] bubbleSort = new int[Variables.elementCount];
    
    // References to the four panels
    public static SortPanel p1 = null;
    public static SortPanel p2 = null;
    public static SortPanel p3 = null;
    public static SortPanel p4 = null;
}
