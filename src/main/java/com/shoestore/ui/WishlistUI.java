package com.shoestore.ui;
import com.shoestore.dao.WishlistDAO;
import com.shoestore.models.Wishlist;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDateTime;
import java.util.List;
public class WishlistUI extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private WishlistDAO wishlistDAO;
    public WishlistUI() {
        wishlistDAO = new WishlistDAO();
        setTitle("Wishlist Management");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        //table setup
        tableModel = new DefaultTableModel(new String[]{"ID", "Customer ID", "Shoe ID", "Added Date"}, 0);
        table = new JTable(tableModel);
        loadWishlistItems();
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
        //buttons
        JPanel buttonPanel = new JPanel();
        JButton btnAdd = new JButton("Add to Wishlist");
        JButton btnDelete = new JButton("Remove from Wishlist");
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnDelete);
        add(buttonPanel, BorderLayout.SOUTH);
        //button actions
        btnAdd.addActionListener(e -> addWishlistItem());
        btnDelete.addActionListener(e -> deleteWishlistItem());
    }
    private void loadWishlistItems() {
        tableModel.setRowCount(0);
        List<Wishlist> wishlistItems = wishlistDAO.getWishlistByCustomerId(1L); // Replace with dynamic customer ID as needed
        for (Wishlist wishlist : wishlistItems) {
            tableModel.addRow(new Object[]{
                    wishlist.getId(),
                    wishlist.getCustomerId(),
                    wishlist.getShoeId(),
                    wishlist.getAddedDate()
            });
        }
    }
    private void addWishlistItem() {
        try {
            String customerIdInput = JOptionPane.showInputDialog(this, "Enter Customer ID:");
            String shoeIdInput = JOptionPane.showInputDialog(this, "Enter Shoe ID:");
            if (customerIdInput == null || shoeIdInput == null || customerIdInput.trim().isEmpty() || shoeIdInput.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Invalid input. Please try again.");
                return;
            }
            Long customerId = Long.parseLong(customerIdInput);
            Long shoeId = Long.parseLong(shoeIdInput);
            LocalDateTime addedDate = LocalDateTime.now();
            Wishlist wishlist = new Wishlist(null, customerId, shoeId, addedDate);
            if (wishlistDAO.addWishlistItem(wishlist)) {
                JOptionPane.showMessageDialog(this, "Item added to wishlist successfully!");
                loadWishlistItems();
            } else {
                JOptionPane.showMessageDialog(this, "Error adding item to wishlist.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input format. Please enter numeric values for IDs.");
        }
    }
    private void deleteWishlistItem() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a wishlist item to remove!");
            return;
        }
        Long wishlistId = (Long) tableModel.getValueAt(selectedRow, 0);
        if (wishlistDAO.deleteWishlist(wishlistId)) {
            JOptionPane.showMessageDialog(this, "Wishlist item removed successfully!");
            loadWishlistItems();
        } else {
            JOptionPane.showMessageDialog(this, "Error removing wishlist item.");
        }
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new WishlistUI().setVisible(true));
    }
}
