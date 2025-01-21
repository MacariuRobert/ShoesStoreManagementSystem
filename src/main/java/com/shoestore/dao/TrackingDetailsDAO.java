package com.shoestore.dao;
import com.shoestore.db.DatabaseConnection;
import com.shoestore.models.TrackingDetails;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class TrackingDetailsDAO {
    //retrieve all tracking details
    public List<TrackingDetails> getAllTrackingDetails() {
        List<TrackingDetails> trackingDetailsList = new ArrayList<>();
        String sql = "SELECT * FROM TrackingDetails";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                trackingDetailsList.add(new TrackingDetails(
                        rs.getLong("id_tracking_details"),
                        rs.getString("tracking_number"),
                        rs.getString("courier"),
                        rs.getDate("estimated_delivery_date") != null ? rs.getDate("estimated_delivery_date").toLocalDate() : null,
                        rs.getString("current_status")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return trackingDetailsList;
    }
    //update a tracking detail
    public boolean updateTrackingDetails(TrackingDetails trackingDetails) {
        String sql = "UPDATE TrackingDetails SET tracking_number = ?, courier = ?, estimated_delivery_date = ?, current_status = ? WHERE id_tracking_details = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, trackingDetails.getTrackingNumber());
            stmt.setString(2, trackingDetails.getCourier());
            stmt.setDate(3, trackingDetails.getEstimatedDeliveryDate() != null ? Date.valueOf(trackingDetails.getEstimatedDeliveryDate()) : null);
            stmt.setString(4, trackingDetails.getCurrentStatus());
            stmt.setLong(5, trackingDetails.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    //delete a tracking detail
    public boolean deleteTrackingDetails(Long trackingId) {
        String sql = "DELETE FROM TrackingDetails WHERE id_tracking_details = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, trackingId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
