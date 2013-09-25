package GUI;

import Logic.Bar;
import Logic.Main;
import Repository.Methods;
import Repository.Variables;
import SortAlgorithms.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/*********************************************
 * Written by: Simon Cicek                   *
 * Last changed: 2012-04-04                  *
 *                                           *
 * The panel containing the control options. *
 *********************************************/

public class ControlPanel extends JPanel implements ActionListener, 
                                                    ItemListener, ChangeListener
{
    // All the components of the controlpanel
    JPanel top    = new JPanel();
    JPanel center = new JPanel();
    JLabel p1 = new JLabel("<html><strong>Panel 1:</strong></html>");
    JLabel p2 = new JLabel("<html><strong>Panel 2:</strong></html>");
    JLabel p3 = new JLabel("<html><strong>Panel 3:</strong></html>");
    JLabel p4 = new JLabel("<html><strong>Panel 4:</strong></html>");
    JLabel speed  = new JLabel("<html><strong>Speed:</strong></html>");
    JLabel elementOrder = new JLabel("<html><strong>Order Of Elements:</strong></html>");
    JLabel elementCount  = new JLabel("<html><strong>Number Of Elements:</strong></html>");
    JButton sort    = new JButton("<html><strong>Sort</strong></html>");
    JButton stop    = new JButton("<html><strong>Stop</strong></html>");
    JSlider elements = new JSlider();
    JComboBox algorithm1;
    JComboBox algorithm2;
    JComboBox algorithm3;
    JComboBox algorithm4;
    JComboBox order;
    JComboBox delay;
    ArrayList<SortAlgorithm> runningAlgorithms = new ArrayList<>();
    
    public ControlPanel()
    {        
        // Add the information to the comboboxes
        String[] algorithms = {"Choose one","Quick Sort","Merge Sort", "Selection Sort", 
                               "Insertion Sort", "Bubble Sort"};
        String[] orders = {"Almost Sorted", "Reversed A.S.","Random"};
        String[] delays = {"Very Fast","Fast", "Normal","Slow","Very Slow"};
        
        algorithm1 = new JComboBox(algorithms);
        algorithm2 = new JComboBox(algorithms);
        algorithm3 = new JComboBox(algorithms);
        algorithm4 = new JComboBox(algorithms);
        order = new JComboBox(orders);
        delay = new JComboBox(delays);
        
        // Settings for the element slider
        elements.setMinimum(0);
        elements.setMaximum(Variables.elementCount);
        elements.setMajorTickSpacing(10);
        elements.setValue(0);
        elements.setPaintLabels(true);
        elements.setPaintTicks(true);
        elements.setPaintTrack(true);
        elements.setEnabled(false);
        
        // Settings for the comboboxes
        sort.setEnabled(false);
        stop.setEnabled(false);
        order.setPreferredSize(new Dimension(90,20));
        delay.setPreferredSize(new Dimension(90,20));
        algorithm1.setSelectedIndex(0);
        algorithm2.setSelectedIndex(0);
        algorithm3.setSelectedIndex(0);
        algorithm4.setSelectedIndex(0);
        delay.setSelectedIndex(2);
        
        // Set the layout for the top panel
        top.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(2,2,2,0);
        
        // Add the first label
        c.gridx = 0;
        c.gridy = 0;
        top.add(p1,c);        
        // Add the first combobox
        c.gridy = 1;
        top.add(algorithm1,c);        
        // Add the second label
        c.gridx = 2;
        c.gridy = 0;
        top.add(p2,c);        
        // Add the second combobox
        c.gridy = 1;
        top.add(algorithm2,c);
        // Add the third label
        c.gridx = 0;
        c.gridy = 2;
        top.add(p3,c);        
        // Add the third combobox
        c.gridy = 3;
        top.add(algorithm3,c);        
        // Add the fourth label
        c.gridx = 2;
        c.gridy = 2;
        top.add(p4,c);        
        // Add the fourth combobox
        c.gridy = 3;
        top.add(algorithm4,c);        
        // Add the delay label
        c.gridx = 2;
        c.gridy = 5;
        top.add(speed,c);        
        // Add the delay combobox
        c.gridy = 6;
        top.add(delay,c);        
        // Add the element order label
        c.gridx = 0;
        c.gridy = 5;
        top.add(elementOrder,c);        
        // Add the element order combobox
        c.gridy = 6;
        top.add(order,c);
        
        // Add the control components
        center.add(elementCount);
        center.add(elements);
        center.add(sort);
        center.add(stop);
        
        // Panel settings
        this.setLayout(new BorderLayout());
        this.add(top,BorderLayout.NORTH);
        this.add(center,BorderLayout.CENTER);
        this.setBorder(BorderFactory.createRaisedBevelBorder());
        this.setPreferredSize(new Dimension(200,300));
        
        // Add listeners
        sort.addActionListener(this);
        stop.addActionListener(this);
        elements.addChangeListener(this);
        algorithm1.addItemListener(this);
        algorithm2.addItemListener(this);
        algorithm3.addItemListener(this);
        algorithm4.addItemListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        // Set the delay rate for the algorithms
        if(delay.getSelectedIndex() == 0)
            Variables.delay = 10;
        else if(delay.getSelectedIndex() == 1)
            Variables.delay = 50;
        else if(delay.getSelectedIndex() == 2)
            Variables.delay = 100;
        else if(delay.getSelectedIndex() == 3)
            Variables.delay = 250;
        else if(delay.getSelectedIndex() == 4)
            Variables.delay = 700;

        if(e.getSource() == sort)
        {
            // Make sure that each panel uses a unique sorting algorithm
            if(algorithm1.getSelectedItem().equals(algorithm2.getSelectedItem()) || 
               algorithm1.getSelectedItem().equals(algorithm3.getSelectedItem()) ||
               algorithm1.getSelectedItem().equals(algorithm4.getSelectedItem()) || 
               algorithm2.getSelectedItem().equals(algorithm3.getSelectedItem()) ||
               algorithm2.getSelectedItem().equals(algorithm4.getSelectedItem()) || 
               algorithm3.getSelectedItem().equals(algorithm4.getSelectedItem()))
            {
                // Alert the user of the conflict and return
                JOptionPane.showMessageDialog(null,
                        "Please choose unique algorithms for every window!", 
                        "Error!", JOptionPane.ERROR_MESSAGE); 
                return;
            }
            
            // Make sure that each panel has an algorithm assigned
            if(algorithm1.getSelectedIndex() == 0 || 
               algorithm2.getSelectedIndex() == 0 ||
               algorithm3.getSelectedIndex() == 0 ||
               algorithm4.getSelectedIndex() == 0)
            {
                // Alert the user of the conflict and return
                JOptionPane.showMessageDialog(null,
                        "Make sure to choose an algorithm for each window!", 
                        "Error!", JOptionPane.ERROR_MESSAGE); 
                return;
            }
                
            // Make sure there at at least two elements to sort
            if(elements.getValue() < 2)
                return;
            
            // Start the selected sorting algorithms for the four panels
            startSorting(Variables.p1,algorithm1.getSelectedIndex());
            startSorting(Variables.p2,algorithm2.getSelectedIndex());
            startSorting(Variables.p3,algorithm3.getSelectedIndex());
            startSorting(Variables.p4,algorithm4.getSelectedIndex());
            
            stop.setEnabled(true);
            enableControls(false);
        }
        else if(e.getSource() == stop)
        {
            // Stop all the running algorithms
            for(SortAlgorithm a : runningAlgorithms)
                a.stop();
            
            // Enable/Disable controls
            sort.setEnabled(true);
            delay.setEnabled(true);
            order.setEnabled(true);
            elements.setEnabled(true);
            stop.setEnabled(false);
        }
    }
    
    // Enable/Disable controls
    public void enableControls(boolean enable)
    {
        sort.setEnabled(enable);
        delay.setEnabled(enable);
        order.setEnabled(enable);
        elements.setEnabled(enable);
        algorithm1.setEnabled(enable);
        algorithm2.setEnabled(enable);
        algorithm3.setEnabled(enable);
        algorithm4.setEnabled(enable);
    }
    
    public void initPanels()
    {
        // Create mappings between sorting algorithms and the bars to be sorted
        HashMap mapping = new HashMap();
        mapping.put(1, Variables.qsbars);
        mapping.put(2, Variables.msbars);
        mapping.put(3, Variables.ssbars);
        mapping.put(4, Variables.isbars);
        mapping.put(5, Variables.bsbars);
        
        // Set the bars of the panels to the bars of the selected sorting algorithm
        Variables.p1.bars = (Bar[]) mapping.get(algorithm1.getSelectedIndex());
        Variables.p2.bars = (Bar[]) mapping.get(algorithm2.getSelectedIndex());
        Variables.p3.bars = (Bar[]) mapping.get(algorithm3.getSelectedIndex());
        Variables.p4.bars = (Bar[]) mapping.get(algorithm4.getSelectedIndex());
    }
    
    public void startSorting(final SortPanel p, final int i)
    {
        // Makes sure that the panel is marked as unsorted
        p.sorted = false; 
        
        // Create mappings between the sorting algorithms and 
        // the selected index of the combobox
        final HashMap sortMapping = new HashMap();
        sortMapping.put(1, new QuickSort(p));
        sortMapping.put(2, new MergeSort(p));
        sortMapping.put(3, new SelectionSort(p));
        sortMapping.put(4, new InsertionSort(p));
        sortMapping.put(5, new BubbleSort(p));
        
        // Create mappings between the values and the selected index of the combobox
        final HashMap valueMapping = new HashMap();
        valueMapping.put(1, Variables.quickSort);
        valueMapping.put(2, Variables.mergeSort);
        valueMapping.put(3, Variables.selectionSort);
        valueMapping.put(4, Variables.insertionSort);
        valueMapping.put(5, Variables.bubbleSort);
        
        final SortAlgorithm algo = ((SortAlgorithm)sortMapping.get(i));
        runningAlgorithms.add(algo);
        
        // Create and start a new thread that sorts the bars of the defined panel 
        new Thread() 
        {
            public void run() 
            {
                algo.sort((int[])valueMapping.get(i));
                
                // Declare the bars of the panel sorted
                if(!algo.stopped())
                    p.sorted = true;
                
                runningAlgorithms.remove(algo);
                
                // Check if all the panels have been sorted and if so, 
                // enable/disable the controls
                if(Variables.p1.sorted == true && Variables.p2.sorted == true && 
                   Variables.p3.sorted == true && Variables.p4.sorted == true)
                {
                    enableControls(true);
                    stop.setEnabled(false);
                }
            }
        }.start();
    }

    @Override
    public void itemStateChanged(ItemEvent e) 
    {
        // Set the titles of the panels
        Main.mainFrame.setTitles();
        if(algorithm1.getSelectedIndex() != 0 && 
           algorithm2.getSelectedIndex() != 0 &&
           algorithm3.getSelectedIndex() != 0 &&
           algorithm4.getSelectedIndex() != 0)
            elements.setEnabled(true);
    }

    @Override
    public void stateChanged(ChangeEvent e) 
    {
        if(e.getSource() == elements)
        {
            // Set the amount of elements to sort
            Variables.elementCount = elements.getValue();
            
            // Initialize the values and the element order of the arrays to sort 
            Methods.initializeValues(order.getSelectedIndex());
            
            // Initialize the bars according to the values set above
            Variables.qsbars = Methods.initializeBars(Variables.quickSort);
            Variables.msbars = Methods.initializeBars(Variables.mergeSort);
            Variables.ssbars = Methods.initializeBars(Variables.selectionSort);
            Variables.isbars = Methods.initializeBars(Variables.insertionSort);
            Variables.bsbars = Methods.initializeBars(Variables.bubbleSort);
            
            // Set the global references to point to the panels
            Variables.p1 = Main.mainFrame.left.p1;
            Variables.p2 = Main.mainFrame.right.p2;
            Variables.p3 = Main.mainFrame.left.p3;
            Variables.p4 = Main.mainFrame.right.p4;
            
            // Initialize the panels (show the bars)
            initPanels();
                        
            // Declare the bars of the panels unsorted
            Variables.p1.sorted = false;
            Variables.p2.sorted = false;
            Variables.p3.sorted = false;
            Variables.p4.sorted = false;
            
            // Enable the sort button
            sort.setEnabled(true);
            
            // Ensure everything is painted
            Methods.repaintPanels();
        }
    }
}