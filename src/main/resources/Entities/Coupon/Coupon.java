import java.time.LocalDate;

public class Coupon {
    private Long id;
    private String code;
    private String discountType;
    private Double discountValue;
    private LocalDate expiryDate;
    private boolean isActive;

    //constructor, getters and setters
    public Coupon(Long id, String code, String discountType, Double discountValue, LocalDate expiryDate, boolean isActive) {
        this.id = id;
        this.code = code;
        this.discountType = discountType;
        this.discountValue = discountValue;
        this.expiryDate = expiryDate;
        this.isActive = isActive;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getDiscountType() { return discountType; }
    public void setDiscountType(String discountType) { this.discountType = discountType; }

    public Double getDiscountValue() { return discountValue; }
    public void setDiscountValue(Double discountValue) { this.discountValue = discountValue; }

    public LocalDate getExpiryDate() { return expiryDate; }
    public void setExpiryDate(LocalDate expiryDate) { this.expiryDate = expiryDate; }

    public boolean isActive() { return isActive; }
    public void setActive(boolean active) { isActive = active; }

    //methods
    public boolean isExpired() {
        return LocalDate.now().isAfter(expiryDate);
    }

    public void deactivate() {
        this.isActive = false;
    }
}
