package com.shoestore.ui;
import com.shoestore.dao.CouponDAO;
import com.shoestore.models.Coupon;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;
public class CouponUI extends JFrame {
    private CouponDAO couponDAO;
    private JTable couponTable;
    private DefaultTableModel tableModel;
    public CouponUI() {
        couponDAO = new CouponDAO();
        initializeUI();
        loadCoupons();
    }
    private void initializeUI() {
        setTitle("Coupon Management");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        //table setup
        tableModel = new DefaultTableModel(new Object[]{"ID", "Code", "Type", "Value", "Expiry Date", "Active"}, 0);
        couponTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(couponTable);
        add(scrollPane, BorderLayout.CENTER);
        //button panel
        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Add");
        JButton editButton = new JButton("Edit");
        JButton deleteButton = new JButton("Delete");
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        add(buttonPanel, BorderLayout.SOUTH);
        //button actions
        addButton.addActionListener(e -> addCoupon());
        editButton.addActionListener(e -> editCoupon());
        deleteButton.addActionListener(e -> deleteCoupon());
    }
    private void loadCoupons() {
        tableModel.setRowCount(0); // Clear existing rows
        List<Coupon> coupons = couponDAO.getAllCoupons();
        for (Coupon coupon : coupons) {
            tableModel.addRow(new Object[]{
                    coupon.getId(),
                    coupon.getCode(),
                    coupon.getDiscountType(),
                    coupon.getDiscountValue(),
                    coupon.getExpiryDate(),
                    coupon.isActive()
            });
        }
    }
    private void addCoupon() {
        try {
            String code = JOptionPane.showInputDialog(this, "Enter Code:");
            String type = JOptionPane.showInputDialog(this, "Enter Type (percentage/fixed amount):");
            double value = Double.parseDouble(JOptionPane.showInputDialog(this, "Enter Value:"));
            LocalDate expiry = LocalDate.parse(JOptionPane.showInputDialog(this, "Enter Expiry Date (YYYY-MM-DD):"));
            Coupon coupon = new Coupon(null, code, type, value, expiry, true);
            couponDAO.addCoupon(coupon);
            loadCoupons();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Invalid input.");
        }
    }
    private void editCoupon() {
        int selectedRow = couponTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Select a row to edit!");
            return;
        }
        try {
            Long id = (Long) tableModel.getValueAt(selectedRow, 0);
            String code = JOptionPane.showInputDialog(this, "Enter Code:", tableModel.getValueAt(selectedRow, 1));
            String type = JOptionPane.showInputDialog(this, "Enter Type:", tableModel.getValueAt(selectedRow, 2));
            double value = Double.parseDouble(JOptionPane.showInputDialog(this, "Enter Value:", tableModel.getValueAt(selectedRow, 3)));
            LocalDate expiry = LocalDate.parse(JOptionPane.showInputDialog(this, "Enter Expiry Date:", tableModel.getValueAt(selectedRow, 4)));
            Coupon coupon = new Coupon(id, code, type, value, expiry, true);
            couponDAO.updateCoupon(coupon);
            loadCoupons();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Invalid input.");
        }
    }
    private void deleteCoupon() {
        int selectedRow = couponTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Select a row to delete!");
            return;
        }
        Long id = (Long) tableModel.getValueAt(selectedRow, 0);
        couponDAO.deleteCoupon(id);
        loadCoupons();
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CouponUI().setVisible(true));
    }
}
