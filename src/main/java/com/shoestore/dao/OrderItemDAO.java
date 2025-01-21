package com.shoestore.dao;

import com.shoestore.db.DatabaseConnection;
import com.shoestore.models.OrderItem;
import com.shoestore.models.Shoe;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderItemDAO {

    public void addOrderItem(OrderItem orderItem) {
        String sql = "INSERT INTO OrderItem (order_id, shoe_id, size, quantity, price) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setLong(1, orderItem.getOrderId());
            stmt.setLong(2, orderItem.getShoe().getId());
            stmt.setInt(3, orderItem.getSize());
            stmt.setInt(4, orderItem.getQuantity());
            stmt.setDouble(5, orderItem.getPrice());

            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    orderItem.setId(generatedKeys.getLong(1));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error adding order item: " + e.getMessage());
        }
    }

    public List<OrderItem> getOrderItemsByOrderId(Long orderId) {
        List<OrderItem> orderItems = new ArrayList<>();
        String sql = "SELECT oi.id_orderitem, oi.size, oi.quantity, oi.price, " +
                "s.id_shoe, s.name, s.brand, s.price AS shoe_price, s.size_options, s.stock " +
                "FROM OrderItem oi " +
                "JOIN Shoe s ON oi.shoe_id = s.id_shoe " +
                "WHERE oi.order_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, orderId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Shoe shoe = new Shoe(
                        rs.getLong("id_shoe"),
                        rs.getString("name"),
                        rs.getString("brand"),
                        rs.getDouble("shoe_price"),
                        (String[]) rs.getArray("size_options").getArray(),
                        rs.getString("stock")
                );

                OrderItem orderItem = new OrderItem(
                        rs.getLong("id_orderitem"),
                        orderId,
                        shoe,
                        rs.getInt("size"),
                        rs.getInt("quantity"),
                        rs.getDouble("price")
                );

                orderItems.add(orderItem);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving order items: " + e.getMessage());
        }
        return orderItems;
    }

    public void deleteOrderItemsByOrderId(Long orderId) {
        String sql = "DELETE FROM OrderItem WHERE order_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, orderId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error deleting order items: " + e.getMessage());
        }
    }
}
