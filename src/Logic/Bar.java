package Logic;

import Repository.Variables;
import java.awt.Color;
import java.awt.geom.Rectangle2D;

/********************************
 * Written by: Simon Cicek      *
 * Last changed: 2012-04-04     *
 *                              *
 * The representation of a bar. *
 ********************************/

public class Bar 
{
    Bar[] bars;
    public Rectangle2D body;
    public Rectangle2D head;
    public Color cBody;
    public Color cHead;
    public boolean isPivot = false;
    public boolean isLeftHighlighted = false;
    public boolean isRightHighlighted = false;
    
    public Bar (Rectangle2D b, Rectangle2D h, Bar[] bars)
    {
        this.bars = bars;
        this.body = b;
        this.head = h;
        this.cBody = Variables.barBody;
        this.cHead = Variables.barHead;
    }
    
    // Mark a unique bar
    public void setPivot()
    {        
        for(Bar bar : bars)
        {
            if (bar.isPivot)
            {
                bar.isPivot = false;
                bar.setBar();
            }
        }
        
        this.isPivot = true;
        cBody = Variables.pivotBody;
        cHead = Variables.pivotHead;
    }
    
    // Revert a bar to it's original state
    public void setBar ()
    {
        this.isLeftHighlighted = false;
        this.isRightHighlighted = false;
        cBody = Variables.barBody;
        cHead = Variables.barHead;
    }
    
    // Revert all bars to their original states
    public static void clearBars(Bar[] bars)
    {
        for(Bar b : bars)
        {
            b.setBar();
            b.isPivot = false;
        }
    }
    
    // Mark a collision
    public static void setCollision(Bar[] bars)
    {
        for(Bar b : bars)
        {
            if(b.isLeftHighlighted || b.isRightHighlighted)
            {
                b.cBody = Color.yellow;
                b.cHead = Color.green;
            }
        }
    }
    
    // Mark a bar to the left of another unique bar
    public void setLeftHighlight()
    {    
        for(Bar b : bars)
        {
            if(!b.isPivot && !b.isRightHighlighted)
            b.setBar();
            if(!b.isLeftHighlighted)
            {
                this.isLeftHighlighted = true;
                cBody = Variables.barLeftHighlightBody;
                cHead = Variables.barLeftHighlightHead;
            }
        }
    }
    
    // Mark a bar to the right of another unique bar
    public void setRightHighlight()
    {    
        for(Bar b : bars)
        {
            if(!b.isPivot && !b.isLeftHighlighted)
                b.setBar();
            if(!b.isRightHighlighted)
            {
                this.isRightHighlighted = true;
                cBody = Variables.barRightHighlightBody;
                cHead = Variables.barRightHighlightHead;
            }
        }
    }
}