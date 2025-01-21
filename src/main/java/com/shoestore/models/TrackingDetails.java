package com.shoestore.models;
import java.time.LocalDate;
public class TrackingDetails {
    private Long id;
    private String trackingNumber;
    private String courier;
    private LocalDate estimatedDeliveryDate;
    private String currentStatus;
    //default constructor
    public TrackingDetails() {}
    //constructor
    public TrackingDetails(Long id, String trackingNumber, String courier, LocalDate estimatedDeliveryDate, String currentStatus) {
        this.id = id;
        this.trackingNumber = trackingNumber;
        this.courier = courier;
        this.estimatedDeliveryDate = estimatedDeliveryDate;
        this.currentStatus = currentStatus;
    }
    //getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTrackingNumber() { return trackingNumber; }
    public void setTrackingNumber(String trackingNumber) { this.trackingNumber = trackingNumber; }
    public String getCourier() { return courier; }
    public void setCourier(String courier) { this.courier = courier; }
    public LocalDate getEstimatedDeliveryDate() { return estimatedDeliveryDate; }
    public void setEstimatedDeliveryDate(LocalDate estimatedDeliveryDate) { this.estimatedDeliveryDate = estimatedDeliveryDate; }
    public String getCurrentStatus() { return currentStatus; }
    public void setCurrentStatus(String currentStatus) { this.currentStatus = currentStatus; }
}
