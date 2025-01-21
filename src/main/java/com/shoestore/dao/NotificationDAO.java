package com.shoestore.dao;
import com.shoestore.db.DatabaseConnection;
import com.shoestore.models.Notification;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
public class NotificationDAO {
    //add a notification
    public void addNotification(Notification notification) {
        String sql = "INSERT INTO Notification (recipient_id, message, notification_type, is_read, created_at) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, notification.getRecipientId());
            stmt.setString(2, notification.getMessage());
            stmt.setString(3, notification.getNotificationType());
            stmt.setBoolean(4, notification.isRead());
            stmt.setTimestamp(5, Timestamp.valueOf(notification.getCreatedAt()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //retrieve notifications by recipient ID
    public List<Notification> getNotificationsByRecipientId(Long recipientId) {
        List<Notification> notifications = new ArrayList<>();
        String sql = "SELECT * FROM Notification WHERE recipient_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, recipientId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                notifications.add(new Notification(
                        rs.getLong("id"),
                        rs.getLong("recipient_id"),
                        rs.getString("message"),
                        rs.getString("notification_type"),
                        rs.getBoolean("is_read"),
                        rs.getTimestamp("created_at").toLocalDateTime()
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return notifications;
    }
    //mark a notification as read
    public void markNotificationAsRead(Long id) {
        String sql = "UPDATE Notification SET is_read = TRUE WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //delete a notification
    public void deleteNotification(Long id) {
        String sql = "DELETE FROM Notification WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
