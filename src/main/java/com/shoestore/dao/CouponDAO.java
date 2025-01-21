package com.shoestore.dao;
import com.shoestore.db.DatabaseConnection;
import com.shoestore.models.Coupon;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class CouponDAO {
    //add new coupon
    public void addCoupon(Coupon coupon) {
        String sql = "INSERT INTO Coupon (code, discount_type, discount_value, expiry_date, is_active) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, coupon.getCode());
            stmt.setString(2, coupon.getDiscountType());
            stmt.setDouble(3, coupon.getDiscountValue());
            stmt.setDate(4, Date.valueOf(coupon.getExpiryDate()));
            stmt.setBoolean(5, coupon.isActive());
            stmt.executeUpdate();
            //retrieve and set the ID
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    coupon.setId(generatedKeys.getLong(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //retrieve all coupons
    public List<Coupon> getAllCoupons() {
        List<Coupon> coupons = new ArrayList<>();
        String sql = "SELECT * FROM Coupon ORDER BY id_coupon"; // Added ORDER BY to retain row order
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                coupons.add(new Coupon(
                        rs.getLong("id_coupon"),
                        rs.getString("code"),
                        rs.getString("discount_type"),
                        rs.getDouble("discount_value"),
                        rs.getDate("expiry_date").toLocalDate(),
                        rs.getBoolean("is_active")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return coupons;
    }
    //update a coupon
    public void updateCoupon(Coupon coupon) {
        String sql = "UPDATE Coupon SET code = ?, discount_type = ?, discount_value = ?, expiry_date = ?, is_active = ? WHERE id_coupon = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, coupon.getCode());
            stmt.setString(2, coupon.getDiscountType());
            stmt.setDouble(3, coupon.getDiscountValue());
            stmt.setDate(4, Date.valueOf(coupon.getExpiryDate()));
            stmt.setBoolean(5, coupon.isActive());
            stmt.setLong(6, coupon.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //delete a coupon
    public void deleteCoupon(Long id) {
        String sql = "DELETE FROM Coupon WHERE id_coupon = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
