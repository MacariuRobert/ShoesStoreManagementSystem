import java.time.LocalDate;

public class TrackingDetails {
    private String trackingNumber;
    private String courier;
    private LocalDate estimatedDeliveryDate;
    private String currentStatus;

    //constructor, getters and setters
    public TrackingDetails(String trackingNumber, String courier, LocalDate estimatedDeliveryDate, String currentStatus) {
        this.trackingNumber = trackingNumber;
        this.courier = courier;
        this.estimatedDeliveryDate = estimatedDeliveryDate;
        this.currentStatus = currentStatus;
    }

    public String getTrackingNumber() { return trackingNumber; }
    public void setTrackingNumber(String trackingNumber) { this.trackingNumber = trackingNumber; }

    public String getCourier() { return courier; }
    public void setCourier(String courier) { this.courier = courier; }

    public LocalDate getEstimatedDeliveryDate() { return estimatedDeliveryDate; }
    public void setEstimatedDeliveryDate(LocalDate estimatedDeliveryDate) { this.estimatedDeliveryDate = estimatedDeliveryDate; }

    public String getCurrentStatus() { return currentStatus; }
    public void setCurrentStatus(String currentStatus) { this.currentStatus = currentStatus; }

    //method
    public void updateStatus(String status) {
        this.currentStatus = status;
    }
}
