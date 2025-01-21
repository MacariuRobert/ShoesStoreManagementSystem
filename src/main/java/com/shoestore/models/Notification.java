package com.shoestore.models;
import java.time.LocalDateTime;
public class Notification {
    private Long id;
    private Long recipientId;
    private String message;
    private String notificationType;
    private boolean isRead;
    private LocalDateTime createdAt;
    //default Constructor
    public Notification() {}
    //constructor
    public Notification(Long id, Long recipientId, String message, String notificationType, boolean isRead, LocalDateTime createdAt) {
        this.id = id;
        this.recipientId = recipientId;
        this.message = message;
        this.notificationType = notificationType;
        this.isRead = isRead;
        this.createdAt = createdAt;
    }
    //getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getRecipientId() { return recipientId; }
    public void setRecipientId(Long recipientId) { this.recipientId = recipientId; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public String getNotificationType() { return notificationType; }
    public void setNotificationType(String notificationType) { this.notificationType = notificationType; }
    public boolean isRead() { return isRead; }
    public void setRead(boolean read) { isRead = read; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    //methods
    public void markAsRead() {
        this.isRead = true;
    }
}
