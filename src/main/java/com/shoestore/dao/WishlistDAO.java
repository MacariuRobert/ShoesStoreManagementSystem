package com.shoestore.dao;
import com.shoestore.db.DatabaseConnection;
import com.shoestore.models.Wishlist;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
public class WishlistDAO {
    //retrieve all wishlist items for a customer
    public List<Wishlist> getWishlistByCustomerId(Long customerId) {
        List<Wishlist> wishlistItems = new ArrayList<>();
        String sql = "SELECT * FROM Wishlist WHERE customer_id = ? ORDER BY id_wishlist"; // Fixed column name
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, customerId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                wishlistItems.add(new Wishlist(
                        rs.getLong("id_wishlist"), // Fixed column name
                        rs.getLong("customer_id"),
                        rs.getLong("shoe_id"),
                        rs.getTimestamp("added_date").toLocalDateTime()
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return wishlistItems;
    }
    //add an item to the wishlist
    public boolean addWishlistItem(Wishlist wishlist) {
        String sql = "INSERT INTO Wishlist (customer_id, shoe_id, added_date) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setLong(1, wishlist.getCustomerId());
            stmt.setLong(2, wishlist.getShoeId());
            stmt.setTimestamp(3, Timestamp.valueOf(wishlist.getAddedDate()));
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        wishlist.setId(generatedKeys.getLong(1)); // Retrieve and set the generated ID
                    }
                }
            }
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    //delete an item from the wishlist
    public boolean deleteWishlist(Long wishlistId) {
        String sql = "DELETE FROM Wishlist WHERE id_wishlist = ?"; // Fixed column name
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, wishlistId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
