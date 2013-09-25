package SortAlgorithms;

import GUI.SortPanel;
import Logic.Bar;
import Repository.Methods;
import Repository.Variables;

/*****************************************************************
 * Written by: Simon Cicek                                       *
 * Last changed: 2012-04-04                                      *
 *                                                               *
 * The class implementing the sorting algorithm: Quick Sort.     *
 *****************************************************************/

public class QuickSort<T> implements SortAlgorithm
{
    int[] list;
    SortPanel sp = null;
    boolean stop = false;
    public static int counter = 0;
    
    public QuickSort(SortPanel sp)
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
        
        // Show the changes
        Variables.qsbars = Methods.initializeBars(list);
        sp.bars = Variables.qsbars;
        sp.repaint();
    }
    
    @Override
    public void sort(int[] l)
    {
        this.list = l;
        quickSort(0,list.length-1);
        Bar.clearBars(Variables.qsbars);
    }
        
    public void quickSort(int start, int end)
    {
        if(stop)
            return;
        // The pointers that will scan the array
        int left = start;
        int right = end;
        
        // Make sure that there are at least two elements to sort
        if (end - start >= 1)
        {
            ++counter;
            
            // Mark the first element as the pivot element
            int pivot = list[start];
            Variables.qsbars[start].setPivot();
            sp.repaint();
            
            // Loop as long as the pointers don't cross
            while (left < right)
            {
                if(stop)
                    return;
                ++counter;
                
                // Loop from the left, looking for the first element
                // larger than the pivot element
                while (list[left] <= pivot && left < end && left < right)
                {
                    if(stop)
                        return;
                    ++counter;
                    
                    // Mark the unique bars
                    Variables.qsbars[start].setPivot();
                    Variables.qsbars[left].setLeftHighlight();
                    Variables.qsbars[right].setRightHighlight();
                    sp.repaint();
                    Methods.pause();
                    
                    // Move the pointer towards the end of the array
                    left++;
                }  
                // Loop from the right, looking for the first element
                // smaller than the pivot element
                while (list[right] > pivot && right > start && right >= left)
                {
                    if(stop)
                        return;
                    ++counter;
                    
                    // Mark the unique bars
                    Variables.qsbars[start].setPivot();
                    Variables.qsbars[left].setLeftHighlight();
                    Variables.qsbars[right].setRightHighlight();
                    sp.repaint();
                    // If the pointers collide, mark the bar of collision
                    if(right == left)
                    {
                        ++counter;
                        Bar.setCollision(Variables.qsbars);
                        sp.repaint();
                    }
                    Methods.pause();
                                        
                    // Move the right pointer towards the start of the array
                    right--;
                }
                // If the pointers have not crossed, then a value larger and
                // a value smaller than the pivot have been found and need to be swapped
                if (left < right)
                {
                    ++counter;
                    swapPlace(list,left, right);
                }
            }
            // When the pointers have crossed, swap the pivot with the value at the collision
            swapPlace(list,start, right);
            
            // Mark the new bar representing the pivot
            Variables.qsbars[right].setPivot();
            sp.repaint();
            
            // Sort the left partition of the array
            quickSort(start, right - 1);
            // Sort the right partition of the array
            quickSort(right + 1, end);
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