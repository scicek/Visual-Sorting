package SortAlgorithms;

import GUI.SortPanel;
import Logic.Bar;
import Repository.Methods;
import Repository.Variables;

/**************************************************************
 * Written by: Simon Cicek                                    *
 * Last changed: 2012-04-04                                   *
 *                                                            *
 * The class implementing the sorting algorithm: Bubble Sort. *
 **************************************************************/

public class BubbleSort implements SortAlgorithm
{
    int[] list;
    SortPanel sp = null;
    boolean stop = false;
    public static int counter = 0;
    
    public BubbleSort(SortPanel sp)
    {
        this.sp = sp;
    }
    
    // Swap two elements
    public void swapPlace (int[] list, int e1, int e2)
    {
        Methods.pause();
        int tmp = list[e1];
        list[e1] = list[e2];
        list[e2] = tmp;
        
        // Show the swap
        Variables.bsbars = Methods.initializeBars(list);
        sp.bars = Variables.bsbars;
        sp.repaint();
    }
    
    @Override
    public void sort(int[] list) 
    {
        int n = list.length;

        // Outer loop, iterating the entire array
        for(int i = 0; i < n; i++)
        {
            if(stop)
                return;
            ++counter;
            // Inner loop, comparing all the pairs in the array
            for(int j = 1; j < (n-i); j++)
            {
                if(stop)
                    return;
                ++counter;
                // Show the two values being compared
                Variables.bsbars[j-1].setPivot();
                Variables.bsbars[j].setRightHighlight();
                sp.repaint();
                Methods.pause();
                
                // Perform a swap if the right value is smaller than the left value
                // thus pushing the largest values to the end of the array
                if(list[j-1] > list[j])
                {
                    ++counter;
                    swapPlace(list,j,j-1);
                }
            }
        }
        // When done sorting, unmark the bars
        Bar.clearBars(Variables.bsbars);
        sp.repaint();
    }
    
    @Override
    public void stop()
    {
        stop = true;
    }
    
    @Override
    public boolean stopped() 
    {
        return stop;
    }
}
