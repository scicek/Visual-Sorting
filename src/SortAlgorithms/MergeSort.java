package SortAlgorithms;

import GUI.SortPanel;
import Repository.Methods;
import Repository.Variables;

/*************************************************************
 * Written by: Simon Cicek                                   *
 * Last changed: 2012-04-04                                  *
 *                                                           *
 * The class implementing the sorting algorithm: Merge Sort. *
 *************************************************************/

public class MergeSort implements SortAlgorithm
{
    int[] list, tmp;           
    SortPanel sp = null;
    boolean stop = false;
    public static int counter = 0;
    
    public MergeSort(SortPanel sp)
    {
        this.sp = sp;
    }
    
    @Override
    public void sort(int[] sl)
    {
        this.list = sl;
        int n = list.length;
        tmp = new int[n];
        
        mergesort(0, n-1);
        
        // Show the changes
        Variables.msbars = Methods.initializeBars(sl);
        sp.bars = Variables.msbars;
        sp.repaint();
    }
    
    private void mergesort (int start, int end) 
    {
        if(stop)
            return;
        // Make sure that there are atleast two elements in the array
        if (start < end) 
        {
            ++counter;
            // Find the middle point
            int middle = (start + end) / 2;            
            
            // Repeat the procedure for the left part
            mergesort(start, middle);
            // Repeat the procedure for the right part
            mergesort(middle + 1, end);
            // Merge the parts together (and sort it)
            merge(start, middle, end);
        }
    }
    
    private void merge(int start, int middle, int end)
    {
        int left, right, finalIndex;
        
        // Make a copy of the array
        for (left = start; left <= end; left++)
        {
            if(stop)
                return;
            
            ++counter;
            tmp[left] = list[left];
        }
        
        // Set up the pointers so that they split the temporary array in two parts
        left = start; 
        right = middle + 1; 
        // finalIndex points to the final array holding the sorted/merged values
        finalIndex = start;
        
        // Loop as long as the pointers are in the bounds of the temporary array
        while (left <= middle && right <= end)
        {
            if(stop)
                return;
            
            ++counter;
            
            // Mark the start and end of the area being merged/sorted
            Variables.msbars[start].setLeftHighlight();
            Variables.msbars[end].setRightHighlight();
            sp.repaint();
            Methods.pause();
            
            // Compare the values in the two parts of the temporary array
            // moving the smallest to the final array, thus sorting it
            if (tmp[left] <= tmp[right])
            {
                ++counter;
                // If the value on the left side is smallest, move it 
                // to the final array and update the pointers
                list[finalIndex++] = tmp[left++];
                
                // show the changes
                Variables.msbars = Methods.initializeBars(list);
                sp.bars = Variables.msbars;
                sp.repaint();
            }
            else
            {
                ++counter;
                // If the value on the right side is smallest, move it 
                // to the final array and update the pointers
                list[finalIndex++] = tmp[right++];
                
                // Show the changes
                Variables.msbars = Methods.initializeBars(list);
                sp.bars = Variables.msbars;
                sp.repaint();
            }
        }
        
        // Copy the remainder of the temporary array to the final array
        if(left <= middle)
        {
            ++counter;
            for(int i = left; i <= middle; i++)
            {
                if(stop)
                    return;
                ++counter;
                list[finalIndex++] = tmp[left++];
            }
        }
        else if (right <= end)
        {
            ++counter;
            for(int i = right;i <= end; i++)
            {
                if(stop)
                    return;
                ++counter;
                list[finalIndex++] = tmp[right++];
            }
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