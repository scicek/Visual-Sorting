package Repository;

import Logic.Bar;
import java.awt.geom.Rectangle2D;
import java.util.Random;

/**************************************************
 * Written by: Simon Cicek                        *
 * Last changed: 2012-04-04                       *
 *                                                *
 * A class containing some useful static methods. *
 **************************************************/

public class Methods 
{    
    // Initialize an array of bars given an array of values
    public static Bar[] initializeBars (int[] list)
    {
        int x = -10;
        Bar[] bars = new Bar[list.length];
        for(int i = 0; i < list.length; i++)
        {
            x += Variables.barWidth + 2;
            bars[i] = new Bar(new Rectangle2D.Float(x,210 - list[i], 
                              Variables.barWidth, Variables.barLength),
                              new Rectangle2D.Float(x, 210 - list[i], 
                              Variables.barWidth, 10),bars);
        }
        return bars;
    }
    
    // Initialize an array of values given an option 
    // 1 = almost sorted, 2 = reversed almost sorted and 3 = random
    public static void initializeValues (int c)
    {
        int[] b = new int[Variables.elementCount];
        Random r = new Random();
        
        if(c == 0)
        {    
            for(int i = 0; i < Variables.elementCount; i++)
                b[i] = i + 3*i;
            for (int j = 0; j < Variables.elementCount/4; j++)
            {
                int k = r.nextInt(Variables.elementHighestValue);
                int l = r.nextInt(Variables.elementCount - 1);
                b[l] = k;
            }
        }
        else if (c == 1)
        {
            int tmp = 0;
            for(int i = Variables.elementCount - 1; i >= 0; i--)
            {
                b[tmp] = i+3*i;
                tmp++;
            }
            for (int j = 0; j < Variables.elementCount/4; j++)
            {
                int k = r.nextInt(Variables.elementHighestValue);
                int l = r.nextInt(Variables.elementCount - 1);
                b[l] = k;
            }
        }
        else if(c == 2)
            for(int i = 0; i < Variables.elementCount; i++)
                b[i] = r.nextInt(Variables.elementHighestValue);
 
        // Make sure all algorithms sort the same array of values
        Variables.quickSort = b.clone();
        Variables.mergeSort = b.clone();
        Variables.selectionSort = b.clone();
        Variables.insertionSort = b.clone();
        Variables.bubbleSort = b.clone();
    }
    
    // Repaint all the panels
    public static void repaintPanels()
    {
        Variables.p1.repaint();
        Variables.p2.repaint();
        Variables.p3.repaint();
        Variables.p4.repaint();
    }
    
    // Cause the thread to pause according to the global delay variable
    public static void pause()
    {
        try
        {
            Thread.sleep(Variables.delay);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
