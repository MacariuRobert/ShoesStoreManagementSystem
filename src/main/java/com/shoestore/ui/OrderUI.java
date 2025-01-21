package com.shoestore.ui;
import com.shoestore.models.Order;
import com.shoestore.dao.OrderDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
public class OrderUI extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private OrderDAO orderDAO;
    public OrderUI() {
        orderDAO = new OrderDAO();
        setTitle("Order Management");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        //table setup
        tableModel = new DefaultTableModel(new String[]{"ID", "Customer", "Total Price", "Status", "Tracking"}, 0);
        table = new JTable(tableModel);
        loadOrders();
        JScrollPane scrollPane = new JScrollPane(table);
        //buttons
        JButton btnUpdateStatus = new JButton("Update Order Status");
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnUpdateStatus);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        //button actions
        btnUpdateStatus.addActionListener(e -> updateOrderStatus());
    }
    private void loadOrders() {
        tableModel.setRowCount(0); //clear existing rows
        List<Order> orders = orderDAO.getAllOrders();
        for (Order order : orders) {
            tableModel.addRow(new Object[]{
                    order.getId(),
                    order.getCustomer() != null ? order.getCustomer().getName() : "Unknown",
                    order.getTotalPrice(),
                    order.getStatus(),
                    order.getTrackingDetails() != null ? order.getTrackingDetails().getTrackingNumber() : "N/A"
            });
        }
    }
    private void updateOrderStatus() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Select an order first!");
            return;
        }
        Long orderId = (Long) tableModel.getValueAt(selectedRow, 0);
        String newStatus = JOptionPane.showInputDialog(this, "Enter new status:", tableModel.getValueAt(selectedRow, 3));
        if (newStatus != null && !newStatus.trim().isEmpty()) {
            orderDAO.updateOrderStatus(orderId, newStatus.trim());
            //update the table directly
            tableModel.setValueAt(newStatus.trim(), selectedRow, 3);
            JOptionPane.showMessageDialog(this, "Order status updated successfully!");
        }
    }
}
