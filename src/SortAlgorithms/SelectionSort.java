package SortAlgorithms;

import GUI.SortPanel;
import Logic.Bar;
import Repository.Methods;
import Repository.Variables;

/*****************************************************************
 * Written by: Simon Cicek                                       *
 * Last changed: 2012-04-04                                      *
 *                                                               *
 * The class implementing the sorting algorithm: Selection Sort. *
 *****************************************************************/

public class SelectionSort implements SortAlgorithm
{
    int[] list;
    SortPanel sp = null;
    boolean stop = false;
    public static int counter = 0;
    
    public SelectionSort(SortPanel sp)
    {
        this.sp = sp;
    }
    
    // Swap two elements
    public void swapPlace (int[] list, int e1, int e2)
    {
        if(stop)
            return;
        Methods.pause();
        int tmp = list[e1];
        list[e1] = list[e2];
        list[e2] = tmp;
        
        // Show the changes
        Variables.ssbars = Methods.initializeBars(list);
        sp.bars = Variables.ssbars;
        sp.repaint();
    }
    
    @Override
    public void sort(int[] list)
    {
        int smallest;
        
        // Loop that iterates the entire array
        for(int left = 0; left < list.length-1; left++)
        {
            if(stop)
                return;
            
            ++counter;
            
            // Initialize the pointer to the smallest value
            smallest = left;
            
            // Mark the bar representing the smallest value
            Variables.ssbars[smallest].setPivot();
            sp.repaint();
            
            // Loop that iterates the values subsequent to the value targeted by the left pointer 
            for(int right = left + 1; right < list.length; right++)
            {
                if(stop)
                    return;
            
                ++counter;
                Methods.pause();
                // Mark the bar that represents the right pointer
                Variables.ssbars[right].setRightHighlight();
                sp.repaint();
                
                // If a smaller value has been found, mark it as the smallest
                if(list[right] < list[smallest])
                {
                    ++counter;
                    smallest = right;
                    
                    // Mark the smallest bar as unique
                    Variables.ssbars[smallest].setPivot();
                    sp.repaint();
                }
            }
            
            if(stop)
                return;
            
            // If the smallest value is not at it's right index, swap the values
            if(smallest != left)
            {
                ++counter;
                swapPlace(list, left, smallest);
                
                // Mark the smallest value
                Variables.ssbars[smallest].setPivot();
                sp.repaint();
            }
        }
        Bar.clearBars(Variables.ssbars);
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