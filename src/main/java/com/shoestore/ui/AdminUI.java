package com.shoestore.ui;
import com.shoestore.dao.AdminDAO;
import com.shoestore.models.Admin;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
public class AdminUI extends JFrame {
    private AdminDAO adminDAO;
    private JTable adminTable;
    private DefaultTableModel tableModel;
    public AdminUI() {
        adminDAO = new AdminDAO();
        initializeUI();
        loadAdminData();
    }
    private void initializeUI() {
        setTitle("Admin Management");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        //table setup
        tableModel = new DefaultTableModel(new Object[]{"ID", "Username"}, 0);
        adminTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(adminTable);
        add(scrollPane, BorderLayout.CENTER);
        //button panel
        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Add Admin");
        JButton editButton = new JButton("Edit Admin");
        JButton deleteButton = new JButton("Delete Admin");
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        add(buttonPanel, BorderLayout.SOUTH);
        //button actions
        addButton.addActionListener(e -> addAdmin());
        editButton.addActionListener(e -> editAdmin());
        deleteButton.addActionListener(e -> deleteAdmin());
    }
    private void loadAdminData() {
        tableModel.setRowCount(0); // Clear existing rows
        List<Admin> admins = adminDAO.getAllAdmins();
        for (Admin admin : admins) {
            tableModel.addRow(new Object[]{admin.getId(), admin.getUsername()});
        }
    }
    private void addAdmin() {
        String username = JOptionPane.showInputDialog(this, "Enter Username:");
        if (username == null || username.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Username cannot be empty!");
            return;
        }
        String password = JOptionPane.showInputDialog(this, "Enter Password:");
        if (password == null || password.length() < 8) {
            JOptionPane.showMessageDialog(this, "Password must be at least 8 characters long!");
            return;
        }
        Admin admin = new Admin(null, username, password);
        adminDAO.addAdmin(admin);
        JOptionPane.showMessageDialog(this, "Admin added successfully!");
        loadAdminData();
    }
    private void editAdmin() {
        int selectedRow = adminTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select an admin to edit!");
            return;
        }
        Long adminId = (Long) tableModel.getValueAt(selectedRow, 0);
        String currentUsername = (String) tableModel.getValueAt(selectedRow, 1);
        String newUsername = JOptionPane.showInputDialog(this, "Enter New Username:", currentUsername);
        if (newUsername == null || newUsername.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Username cannot be empty!");
            return;
        }
        String newPassword = JOptionPane.showInputDialog(this, "Enter New Password:");
        if (newPassword == null || newPassword.length() < 8) {
            JOptionPane.showMessageDialog(this, "Password must be at least 8 characters long!");
            return;
        }
        Admin admin = new Admin(adminId, newUsername, newPassword);
        adminDAO.updateAdmin(admin);
        JOptionPane.showMessageDialog(this, "Admin updated successfully!");
        loadAdminData();
    }
    private void deleteAdmin() {
        int selectedRow = adminTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select an admin to delete!");
            return;
        }
        Long adminId = (Long) tableModel.getValueAt(selectedRow, 0);
        int confirmation = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this admin?",
                "Confirm Deletion", JOptionPane.YES_NO_OPTION);
        if (confirmation == JOptionPane.YES_OPTION) {
            adminDAO.deleteAdmin(adminId);
            JOptionPane.showMessageDialog(this, "Admin deleted successfully!");
            loadAdminData();
        }
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AdminUI().setVisible(true));
    }
}
