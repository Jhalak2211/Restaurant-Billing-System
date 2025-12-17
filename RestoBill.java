import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.event.*;

public class RestoBill {

    /*
        This is a Restaurant Billing System built using Java Swing.
        I used JCheckBox for selecting food items, JTextField for quantity input, and JTextArea to display the bill.
        When the Generate Bill button is clicked, ActionListener checks which items are selected and calculates the total amount accordingly.
    
        ⭐ HOW THIS LOOKS ON RESUME
            Project: Restaurant Billing System
            Tech: Java, Swing, JTable, Event Handling
            Features: Bill generation, tax calculation, input validation
        */
    public static void main(String[] args) {

        JFrame frame = new JFrame("Restaurant Billing System");
        frame.setSize(500, 500);
        frame.setLayout(null);

        // Checkboxes
        JCheckBox cbBurger = new JCheckBox("Burger (₹100)");
        JCheckBox cbPizza = new JCheckBox("Pizza (₹200)");
        JCheckBox cbCoffee = new JCheckBox("Coffee (₹50)");

        cbBurger.setBounds(30, 30, 150, 30);
        cbPizza.setBounds(30, 70, 150, 30);
        cbCoffee.setBounds(30, 110, 150, 30);

        // Quantity fields
        JTextField qBurger = new JTextField();
        JTextField qPizza = new JTextField();
        JTextField qCoffee = new JTextField();

        qBurger.setBounds(200, 30, 50, 30);
        qPizza.setBounds(200, 70, 50, 30);
        qCoffee.setBounds(200, 110, 50, 30);

        // Button
        JButton btnBill = new JButton("Generate Bill");
        btnBill.setBounds(300, 70, 150, 30);

        // JTable
        String[] columns = {"Item", "Qty", "Price", "Total"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        JTable table = new JTable(model);
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(30, 170, 420, 200);

        JLabel lblTotal = new JLabel("Final Amount: ₹0");
        lblTotal.setBounds(30, 390, 300, 30);

        // Button action
        btnBill.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                model.setRowCount(0); // clear table
                int total = 0;

                try {
                    if (cbBurger.isSelected()) {
                        int qty = Integer.parseInt(qBurger.getText());
                        int cost = qty * 100;
                        total += cost;
                        model.addRow(new Object[]{"Burger", qty, 100, cost});
                    }

                    if (cbPizza.isSelected()) {
                        int qty = Integer.parseInt(qPizza.getText());
                        int cost = qty * 200;
                        total += cost;
                        model.addRow(new Object[]{"Pizza", qty, 200, cost});
                    }

                    if (cbCoffee.isSelected()) {
                        int qty = Integer.parseInt(qCoffee.getText());
                        int cost = qty * 50;
                        total += cost;
                        model.addRow(new Object[]{"Coffee", qty, 50, cost});
                    }

                    // GST 5%
                    int gst = (int) (total * 0.05);

                    // Discount 10% if total > 500
                    int discount = 0;
                    if (total > 500) {
                        discount = (int) (total * 0.10);
                    }

                    int finalAmount = total + gst - discount;

                    model.addRow(new Object[]{"GST (5%)", "", "", gst});
                    model.addRow(new Object[]{"Discount", "", "", -discount});

                    lblTotal.setText("Final Amount: ₹" + finalAmount);

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame,
                            "Please enter valid quantity values!",
                            "Input Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Add components
        frame.add(cbBurger);
        frame.add(cbPizza);
        frame.add(cbCoffee);
        frame.add(qBurger);
        frame.add(qPizza);
        frame.add(qCoffee);
        frame.add(btnBill);
        frame.add(sp);
        frame.add(lblTotal);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}


