package GUI;

import Logic.Bar;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

/*****************************************************************
 * Written by: Simon Cicek                                       *
 * Last changed: 2012-04-04                                      *
 *                                                               *
 * The right panel containing the second and fourth panel/label. * 
 *****************************************************************/

public class RightPanel extends JPanel
{
    // The components of the panel
    public JLabel l2 = new JLabel();
    public JLabel l4 = new JLabel();
    public SortPanel p2 = new SortPanel(new Bar[0]);
    public SortPanel p4 = new SortPanel(new Bar[0]);
    
    public RightPanel()
    {
        // Initialize the title of the panels
        l2.setText("<html><strong><font size='6'>N/A</font></strong></html>");
        l4.setText("<html><strong><font size='6'>N/A</font></strong></html>");

        this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
        // Add the second label
        c.gridx = 0;
        c.gridy = 0;
        this.add(l2,c);
        
        // Add the second panel
        c.gridy = 1;
        this.add(p2,c);
        
        // Add some spacing
        c.gridy = 2;
        this.add(Box.createVerticalStrut(15),c);
        
        // Add the fourth label
        c.gridy = 3;
        this.add(l4,c);
        
        // Add the fourth panel
        c.gridy = 4;
        this.add(p4,c);
    }
}
