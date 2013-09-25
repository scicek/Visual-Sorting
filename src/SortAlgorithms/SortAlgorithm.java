package SortAlgorithms;

import GUI.SortPanel;

/************************************************************************
 * Written by: Simon Cicek                                              *
 * Last changed: 2012-04-04                                             *
 *                                                                      *
 * An interface defining key parts of the sorting algorithms.           *
 * Also required in order to point to all the implementing classes with * 
 * a reference of this interface.                                       *
 ************************************************************************/

public interface SortAlgorithm 
{
    void sort(int[] list);
    void stop();
    boolean stopped();
}
