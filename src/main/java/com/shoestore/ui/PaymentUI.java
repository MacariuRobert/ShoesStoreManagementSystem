package com.shoestore.ui;
import com.shoestore.dao.PaymentDAO;
import com.shoestore.models.Payment;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
public class PaymentUI extends JFrame {
    private final JTable table;
    private final DefaultTableModel tableModel;
    private final PaymentDAO paymentDAO;
    public PaymentUI() {
        paymentDAO = new PaymentDAO();
        setTitle("Payment Management");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        //table setup
        tableModel = new DefaultTableModel(new String[]{"ID", "Order ID", "Method", "Date", "Amount", "Status", "Transaction ID"}, 0);
        table = new JTable(tableModel);
        loadPayments();
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
        //buttons
        JPanel buttonPanel = new JPanel();
        JButton btnValidate = new JButton("Validate Payment");
        JButton btnRefund = new JButton("Process Refund");
        buttonPanel.add(btnValidate);
        buttonPanel.add(btnRefund);
        add(buttonPanel, BorderLayout.SOUTH);
        //button actions
        btnValidate.addActionListener(e -> validatePayment());
        btnRefund.addActionListener(e -> processRefund());
    }
    private void loadPayments() {
        tableModel.setRowCount(0);
        List<Payment> payments = paymentDAO.getAllPayments();
        for (Payment payment : payments) {
            tableModel.addRow(new Object[]{
                    payment.getId(),
                    payment.getOrderId(),
                    payment.getPaymentMethod(),
                    payment.getPaymentDate(),
                    payment.getAmount(),
                    payment.getStatus(),
                    payment.getTransactionId()
            });
        }
    }
    private void validatePayment() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a payment to validate!");
            return;
        }
        Long paymentId = (Long) tableModel.getValueAt(selectedRow, 0);
        if (paymentDAO.validatePayment(paymentId)) {
            JOptionPane.showMessageDialog(this, "Payment validated successfully!");
            loadPayments();
        } else {
            JOptionPane.showMessageDialog(this, "Error validating payment.");
        }
    }
    private void processRefund() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a payment to refund!");
            return;
        }
        Long paymentId = (Long) tableModel.getValueAt(selectedRow, 0);
        if (paymentDAO.processRefund(paymentId)) {
            JOptionPane.showMessageDialog(this, "Refund processed successfully!");
            loadPayments();
        } else {
            JOptionPane.showMessageDialog(this, "Error processing refund.");
        }
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PaymentUI().setVisible(true));
    }
}
