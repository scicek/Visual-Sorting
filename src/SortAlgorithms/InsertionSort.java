package SortAlgorithms;

import GUI.SortPanel;
import Repository.Methods;
import Repository.Variables;

/*****************************************************************
 * Written by: Simon Cicek                                       *
 * Last changed: 2012-04-04                                      *
 *                                                               *
 * The class implementing the sorting algorithm: Insertion Sort. *
 *****************************************************************/

public class InsertionSort implements SortAlgorithm
{
    int[] list;
    SortPanel sp = null;
    boolean stop = false;
    public static int counter = 0;

    public InsertionSort(SortPanel sp)
    {
        this.sp = sp;
    }
    
    @Override
     public void sort(int[] l) 
     {
        this.list = l;
        int left, right, selected;
        
        // Outer loop, iterating all the values in the array (except the first)
        for (left = 1; left < list.length; left++) 
        {
            if(stop)
                return;
            ++counter;
            // Select the second element (index 1)
            selected = list[left];
            right = left;
            
            // Set the second element as the unique bar
            Variables.isbars[left].setPivot();
            sp.repaint();
            
            // Loop that iterates the array (in reverse) as long as the selected value 
            // is the smallest value
            while (right > 0 && list[right - 1] > selected) 
            {
                if(stop)
                    return;
                ++counter;
                Methods.pause();
                
                // Copy the larger value to the index of the pointer: right
                list[right] = list[right - 1];
                
                // Mark the bar being compared to the unique bar
                if(right > 0)
                {
                    ++counter;
                    Variables.isbars[right-1].setLeftHighlight();
                    sp.repaint();
                }                
                // Move the pointer towards the start of the array
                right--;
            }
            Methods.pause();
            
            // Copy the value that was overwritten to the index of the pointer: right
            list[right] = selected;
            
            // Display the changes made
            Variables.isbars = Methods.initializeBars(list);
            sp.bars = Variables.isbars;
            sp.repaint();
        }
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
