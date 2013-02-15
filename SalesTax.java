//*********************************************************************** 
// Project: Lab6
// 
// Author: Craig Tuschhoff 
// 
// Completion time: 3 hours total 
// 
// Honor Code: I pledge that this program represents my own program code. 
// I received help from (write names here or no one) in designing and 
// debugging my program. 
//***********************************************************************

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;

public class SalesTax {  
   
   //constants
   final int WINDOW_WIDTH = 250;
   final int WINDOW_HIEGHT = 135;
   
   //create component ref variables
   JFrame frame;
   JPanel labelPanel;
   JPanel textFieldPanel;
   JPanel buttonPanel;
   JLabel label;
   JTextField textField;
   JButton button;
   
   //Constructor method
   public SalesTax() {
      
      //Create the three GUI Components
      label = new JLabel("Enter total sales:");
      textField = new JTextField(10);
      button = new JButton("Calculate Taxes");
      button.addActionListener(new ButtonListener());
      
      //Create panels and add elements to them
      labelPanel = new JPanel();
      labelPanel.add(label);
      
      textFieldPanel = new JPanel();
      textFieldPanel.add(textField);
      
      buttonPanel = new JPanel();
      buttonPanel.add(button);
      
      //Create the frame and add the panels to it
      frame = new JFrame("Tax Calculator");
      
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setSize(WINDOW_WIDTH, WINDOW_HIEGHT);
      frame.setLayout(new BorderLayout());
      frame.getRootPane().setDefaultButton(button);
      
      frame.add(labelPanel, BorderLayout.NORTH);
      frame.add(textFieldPanel, BorderLayout.CENTER);
      frame.add(buttonPanel, BorderLayout.SOUTH);
      
      //Display the frame on screen
      frame.setSize(WINDOW_WIDTH, WINDOW_HIEGHT);
      frame.setLocationRelativeTo(null);
      frame.setVisible(true);
      
   }
   
   public class ButtonListener implements  ActionListener{
      public void actionPerformed(ActionEvent a) {
         
         //constants for calculating sales tax
         final double STATE_TAX = .043;
         final double COUNTY_TAX = .0287;
         
         //variable to store tax total
         double stateSalesTax;
         double countySalesTax;
         double total;
         
         try {
            //get sale total from user
            double input = Double.parseDouble(textField.getText());
            if(input < 0) throw new NumberFormatException("Negative Value");
         
            //calculate sales tax and totals
            stateSalesTax = STATE_TAX * input;
            countySalesTax = COUNTY_TAX * input; 
            total = input + stateSalesTax + countySalesTax;
            
            //display results
            DecimalFormat currency = new DecimalFormat("$0.00");
            JOptionPane.showMessageDialog(null, "Subtotal: " +
               currency.format(input) + "\nState Sales Tax: " + 
               currency.format(stateSalesTax) + "\nCounty Sales Tax: " + 
               currency.format(countySalesTax) +
               "\n__________________________________\n" +
               "Total: " + currency.format(total), "Taxes Owed", 
               JOptionPane.PLAIN_MESSAGE);
               
            //clear text field for next entry
            textField.setText("");
         
         } catch(NumberFormatException e){
         
            //show error message
            JOptionPane.showMessageDialog(null, "Invalid Amount: " +
            e.getMessage(), "Input Error", JOptionPane.ERROR_MESSAGE);
            
            //clear text field for next entry
            textField.setText("");
         }
      }
   }

   public static void main(String[] args) {
      new SalesTax();
   }
}