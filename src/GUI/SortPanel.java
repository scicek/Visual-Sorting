package GUI;

import Logic.Bar;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

/************************************************************
 * Written by: Simon Cicek                                  *
 * Last changed: 2012-04-04                                 *
 *                                                          *
 * The panel used to show the sorting algorithms in action. *
 ************************************************************/

public class SortPanel extends JPanel
{
    // The bars to be shown
    public Bar[] bars = null;
    // Declares if the bars are sorted
    public boolean sorted = false;
    
    public SortPanel(Bar[] b)
    {
        this.bars = b;
        
        // Settings for the panel
        this.setPreferredSize(new Dimension(505,240));
        this.setBorder(BorderFactory.createLoweredBevelBorder());
        this.setBackground(Color.white);
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        
        if(bars == null)
            return;
        
        // Draw the bars
        for(Bar b : bars)
        {
            g2.setColor(b.cBody);
            g2.fill(b.body);
            g2.setColor(b.cHead);
            g2.fill(b.head);
        }
        
        // Draw the "special" bars
        for(Bar b : bars)
        {
            if(b.isPivot || b.isLeftHighlighted || b.isRightHighlighted)
            {
                g2.setColor(b.cHead);
                g2.fill(new Rectangle2D.Float(0, (int)(b.head.getY()), 10000, 2));
            }
        }
      }
}
