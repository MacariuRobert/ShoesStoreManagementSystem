package com.shoestore.ui;
import com.shoestore.dao.TrackingDetailsDAO;
import com.shoestore.models.TrackingDetails;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
public class TrackingDetailsUI extends JFrame {
    private final JTable table;
    private final DefaultTableModel tableModel;
    private final TrackingDetailsDAO trackingDetailsDAO;
    public TrackingDetailsUI() {
        trackingDetailsDAO = new TrackingDetailsDAO();
        setTitle("Tracking Details Management");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        //table setup
        tableModel = new DefaultTableModel(new String[]{"ID", "Tracking Number", "Courier", "Estimated Delivery Date", "Current Status"}, 0);
        table = new JTable(tableModel);
        loadTrackingDetails();
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
        //buttons
        JPanel buttonPanel = new JPanel();
        JButton btnUpdate = new JButton("Update Tracking Detail");
        JButton btnDelete = new JButton("Delete Tracking Detail");
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);
        add(buttonPanel, BorderLayout.SOUTH);
        //button actions
        btnUpdate.addActionListener(e -> updateTrackingDetail());
        btnDelete.addActionListener(e -> deleteTrackingDetail());
    }
    private void loadTrackingDetails() {
        tableModel.setRowCount(0);
        List<TrackingDetails> trackingDetailsList = trackingDetailsDAO.getAllTrackingDetails();
        for (TrackingDetails trackingDetail : trackingDetailsList) {
            tableModel.addRow(new Object[]{
                    trackingDetail.getId(),
                    trackingDetail.getTrackingNumber(),
                    trackingDetail.getCourier(),
                    trackingDetail.getEstimatedDeliveryDate(),
                    trackingDetail.getCurrentStatus()
            });
        }
    }
    private void updateTrackingDetail() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a tracking detail to update!");
            return;
        }
        Long trackingId = (Long) tableModel.getValueAt(selectedRow, 0);
        String newStatus = JOptionPane.showInputDialog(this, "Enter New Status:", tableModel.getValueAt(selectedRow, 4));
        if (newStatus == null || newStatus.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Invalid status!");
            return;
        }
        TrackingDetails trackingDetails = new TrackingDetails(
                trackingId,
                (String) tableModel.getValueAt(selectedRow, 1),
                (String) tableModel.getValueAt(selectedRow, 2),
                (java.time.LocalDate) tableModel.getValueAt(selectedRow, 3),
                newStatus
        );
        if (trackingDetailsDAO.updateTrackingDetails(trackingDetails)) {
            tableModel.setValueAt(newStatus, selectedRow, 4); // Update directly in the table
            JOptionPane.showMessageDialog(this, "Tracking detail updated successfully!");
        } else {
            JOptionPane.showMessageDialog(this, "Error updating tracking detail.");
        }
    }
    private void deleteTrackingDetail() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a tracking detail to delete!");
            return;
        }
        Long trackingId = (Long) tableModel.getValueAt(selectedRow, 0);

        if (trackingDetailsDAO.deleteTrackingDetails(trackingId)) {
            tableModel.removeRow(selectedRow); // Remove row from the table
            JOptionPane.showMessageDialog(this, "Tracking detail deleted successfully!");
        } else {
            JOptionPane.showMessageDialog(this, "Error deleting tracking detail.");
        }
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TrackingDetailsUI().setVisible(true));
    }
}
