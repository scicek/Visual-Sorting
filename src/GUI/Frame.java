package GUI;

import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.BoxLayout;
import javax.swing.JFrame;

/************************************************************************
 * Written by: Simon Cicek                                              *
 * Last changed: 2012-04-04                                             *
 *                                                                      *
 * The main frame containing the panels showing the sorting algorithms. *
 ************************************************************************/

public class Frame extends JFrame
{
    public LeftPanel left = new LeftPanel();
    public RightPanel right = new RightPanel();
    ControlPanel ctrl = new ControlPanel();
    Image icon = Toolkit.getDefaultToolkit().getImage("Images/icon.png");
    
    public Frame()
    {        
        // Add the panels
        this.getContentPane().setLayout(new BoxLayout(getContentPane(), 
                                            BoxLayout.X_AXIS));
        this.add(left);
        this.add(right);
        this.add(ctrl);
        
        // Frame Settings
        this.setTitle("Simon Cicek - Visual Sorting");
	this.setSize(1290,600);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setIconImage(icon);
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    // Dynamically update the titles of the panels
    public void setTitles()
    {
        left.l1.setText("<html><strong><font size='6'>"  + 
                        ctrl.algorithm1.getSelectedItem().toString()  + 
                        "</font></strong></html>");
        
        right.l2.setText("<html><strong><font size='6'>" + 
                         ctrl.algorithm2.getSelectedItem().toString() + 
                         "</font></strong></html>");
        
        left.l3.setText("<html><strong><font size='6'>"  + 
                        ctrl.algorithm3.getSelectedItem().toString()  + 
                        "</font></strong></html>");
        
        right.l4.setText("<html><strong><font size='6'>" + 
                         ctrl.algorithm4.getSelectedItem().toString() + 
                         "</font></strong></html>");        
        left.repaint();
        right.repaint();
    }
}