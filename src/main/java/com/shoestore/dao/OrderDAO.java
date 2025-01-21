package com.shoestore.dao;

import com.shoestore.db.DatabaseConnection;
import com.shoestore.models.Order;
import com.shoestore.models.Customer;
import com.shoestore.models.TrackingDetails;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {

    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT o.id_order, o.status, o.total_price, " +
                "c.id_customer, c.name AS customer_name, " +
                "t.id_trackingdetails, t.tracking_number " + // Corrected column name
                "FROM \"Order\" o " +
                "LEFT JOIN Customer c ON o.customer_id = c.id_customer " +
                "LEFT JOIN TrackingDetails t ON o.tracking_details_id = t.id_trackingdetails"; // Corrected column name

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                // Populate Customer if exists
                Customer customer = null;
                if (rs.getObject("id_customer") != null) {
                    customer = new Customer(
                            rs.getLong("id_customer"),
                            rs.getString("customer_name"),
                            null, null, null, null
                    );
                }

                // Populate TrackingDetails if exists
                TrackingDetails trackingDetails = null;
                if (rs.getObject("id_trackingdetails") != null) {
                    trackingDetails = new TrackingDetails(
                            rs.getLong("id_trackingdetails"),
                            rs.getString("tracking_number"),
                            null, null, null
                    );
                }

                // Create the Order object
                Order order = new Order(
                        rs.getLong("id_order"),
                        customer,
                        rs.getString("status"),
                        trackingDetails
                );

                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orders;
    }

    public Order getOrderById(Long orderId) {
        String sql = "SELECT o.id_order, o.status, o.total_price, " +
                "c.id_customer, c.name AS customer_name, " +
                "t.id_trackingdetails, t.tracking_number " + // Corrected column name
                "FROM \"Order\" o " +
                "LEFT JOIN Customer c ON o.customer_id = c.id_customer " +
                "LEFT JOIN TrackingDetails t ON o.tracking_details_id = t.id_trackingdetails " + // Corrected column name
                "WHERE o.id_order = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, orderId);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                // Populate Customer if exists
                Customer customer = null;
                if (rs.getObject("id_customer") != null) {
                    customer = new Customer(
                            rs.getLong("id_customer"),
                            rs.getString("customer_name"),
                            null, null, null, null
                    );
                }

                // Populate TrackingDetails if exists
                TrackingDetails trackingDetails = null;
                if (rs.getObject("id_trackingdetails") != null) {
                    trackingDetails = new TrackingDetails(
                            rs.getLong("id_trackingdetails"),
                            rs.getString("tracking_number"),
                            null, null, null
                    );
                }

                // Return the Order object
                return new Order(
                        rs.getLong("id_order"),
                        customer,
                        rs.getString("status"),
                        trackingDetails
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void updateOrderStatus(Long orderId, String newStatus) {
        String sql = "UPDATE \"Order\" SET status = ? WHERE id_order = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, newStatus);
            stmt.setLong(2, orderId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
