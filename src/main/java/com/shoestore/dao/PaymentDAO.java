package com.shoestore.dao;
import com.shoestore.db.DatabaseConnection;
import com.shoestore.models.Payment;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class PaymentDAO {
    //retrieve all payments
    public List<Payment> getAllPayments() {
        List<Payment> payments = new ArrayList<>();
        String sql = "SELECT * FROM Payment ORDER BY id_payment"; // Use id_payment for consistent ordering
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                payments.add(new Payment(
                        rs.getLong("id_payment"), // Updated column name
                        rs.getLong("order_id"),
                        rs.getString("payment_method"),
                        rs.getTimestamp("payment_date").toLocalDateTime(),
                        rs.getDouble("amount"),
                        rs.getString("status"),
                        rs.getString("transaction_id")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return payments;
    }
    //retrieve a payment by ID
    public Payment getPaymentById(Long id) {
        String sql = "SELECT * FROM Payment WHERE id_payment = ?"; // Updated column name
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Payment(
                        rs.getLong("id_payment"), // Updated column name
                        rs.getLong("order_id"),
                        rs.getString("payment_method"),
                        rs.getTimestamp("payment_date").toLocalDateTime(),
                        rs.getDouble("amount"),
                        rs.getString("status"),
                        rs.getString("transaction_id")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    //validate a payment
    public boolean validatePayment(Long paymentId) {
        String sql = "UPDATE Payment SET status = 'Validated' WHERE id_payment = ?"; // Updated column name
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, paymentId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    //process a refund
    public boolean processRefund(Long paymentId) {
        String sql = "UPDATE Payment SET status = 'Refunded' WHERE id_payment = ?"; // Updated column name
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, paymentId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
