package com.shoestore.ui;
import javax.swing.*;
import java.awt.*;
public class MainMenu extends JFrame {
    public MainMenu() {
        //setting up the main menu window
        setTitle("Shoe Store Management");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centers the window on the screen
        setLayout(new GridLayout(7, 1, 10, 10)); // Adds spacing between buttons
        //buttons for navigation
        JButton btnCustomerManagement = new JButton("Manage Customers");
        JButton btnShoeManagement = new JButton("Manage Shoes");
        JButton btnOrderManagement = new JButton("Manage Orders");
        JButton btnCouponManagement = new JButton("Manage Coupons");
        JButton btnWishlistManagement = new JButton("Manage Wishlists");
        JButton btnPaymentManagement = new JButton("Manage Payments");
        JButton btnExit = new JButton("Exit");
        //adding buttons for each
        add(btnCustomerManagement);
        add(btnShoeManagement);
        add(btnOrderManagement);
        add(btnCouponManagement);
        add(btnWishlistManagement);
        add(btnPaymentManagement);
        add(btnExit);
        //button actions
        btnCustomerManagement.addActionListener(e -> new CustomerUI().setVisible(true));
        btnShoeManagement.addActionListener(e -> new ShoeUI().setVisible(true));
        btnOrderManagement.addActionListener(e -> new OrderUI().setVisible(true));
        btnCouponManagement.addActionListener(e -> new CouponUI().setVisible(true));
        btnWishlistManagement.addActionListener(e -> new WishlistUI().setVisible(true));
        btnPaymentManagement.addActionListener(e -> new PaymentUI().setVisible(true));
        btnExit.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to exit?",
                    "Exit Confirmation",
                    JOptionPane.YES_NO_OPTION
            );
            if (confirm == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainMenu menu = new MainMenu();
            menu.setVisible(true);
        });
    }
}
