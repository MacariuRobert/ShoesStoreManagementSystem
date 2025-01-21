package com.shoestore.models;
import java.time.LocalDate;
public class Coupon {
    private Long id;
    private String code;
    private String discountType; //must be "percentage" or "fixed amount"
    private Double discountValue;
    private LocalDate expiryDate;
    private boolean isActive;
    //default Constructor
    public Coupon() {}
    //constructor
    public Coupon(Long id, String code, String discountType, Double discountValue, LocalDate expiryDate, boolean isActive) {
        validateDiscountType(discountType);
        validateDiscountValue(discountValue);
        this.id = id;
        this.code = code;
        this.discountType = discountType;
        this.discountValue = discountValue;
        this.expiryDate = expiryDate;
        this.isActive = isActive;
    }
    //validation Methods
    private void validateDiscountType(String discountType) {
        if (!"percentage".equalsIgnoreCase(discountType) && !"fixed amount".equalsIgnoreCase(discountType)) {
            throw new IllegalArgumentException("Invalid discount type. Use 'percentage' or 'fixed amount'.");
        }
    }
    private void validateDiscountValue(Double discountValue) {
        if (discountValue == null || discountValue <= 0) {
            throw new IllegalArgumentException("Discount value must be positive.");
        }
    }
    //getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
    public String getDiscountType() { return discountType; }
    public void setDiscountType(String discountType) {
        validateDiscountType(discountType);
        this.discountType = discountType;
    }
    public Double getDiscountValue() { return discountValue; }
    public void setDiscountValue(Double discountValue) {
        validateDiscountValue(discountValue);
        this.discountValue = discountValue;
    }
    public LocalDate getExpiryDate() { return expiryDate; }
    public void setExpiryDate(LocalDate expiryDate) { this.expiryDate = expiryDate; }
    public boolean isActive() { return isActive; }
    public void setActive(boolean active) { isActive = active; }
    //methods
    public boolean isExpired() {
        return !isActive || LocalDate.now().isAfter(expiryDate);
    }
    public void deactivate() {
        this.isActive = false;
    }
    @Override
    public String toString() {
        return "Coupon{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", discountType='" + discountType + '\'' +
                ", discountValue=" + discountValue +
                ", expiryDate=" + expiryDate +
                ", isActive=" + isActive +
                '}';
    }
}
