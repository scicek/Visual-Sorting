package Logic;

import GUI.Frame;
import javax.swing.UIManager;

/****************************************************************
 * Written by: Simon Cicek                                      *
 * Last changed: 2012-04-04                                     *
 *                                                              *
 * The main class, responsible for initializing key components. *
 ****************************************************************/

public class Main 
{
    public static Frame mainFrame;
    
    public static void main(String[] args)
    {
        // Set the look and feel of the frame
        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        
        mainFrame = new Frame();
    }
}
