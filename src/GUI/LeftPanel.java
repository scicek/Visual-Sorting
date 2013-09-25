package GUI;

import Logic.Bar;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

/**************************************************************
 * Written by: Simon Cicek                                    *
 * Last changed: 2012-04-04                                   *
 *                                                            *
 * The left panel containing the first and third panel/label. * 
 **************************************************************/

public class LeftPanel extends JPanel
{
    // The components of the panel
    public JLabel l1 = new JLabel();
    public JLabel l3 = new JLabel();
    public SortPanel p1 = new SortPanel(new Bar[0]);
    public SortPanel p3 = new SortPanel(new Bar[0]);
    
    public LeftPanel()
    {
        // Initialize the title of the panels
        l1.setText("<html><strong><font size='6'>N/A</font></strong></html>");
        l3.setText("<html><strong><font size='6'>N/A</font></strong></html>");
        
        this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
        // Add the first label
        c.gridx = 0;
        c.gridy = 0;
        this.add(l1,c);
        
        // Add the first panel
        c.gridy = 1;
        this.add(p1,c);
        
        // Add some spacing
        c.gridy = 2;
        this.add(Box.createVerticalStrut(15),c);
        
        // Add the third label
        c.gridy = 3;
        this.add(l3,c);
        
        // Add the third panel
        c.gridy = 4;
        this.add(p3,c);
    }
}