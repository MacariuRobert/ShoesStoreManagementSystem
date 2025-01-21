package com.shoestore.models;
import java.time.LocalDateTime;
public class Wishlist {
    private Long id;
    private Long customerId;
    private Long shoeId;
    private LocalDateTime addedDate;
    //default Constructor
    public Wishlist() {}
    //constructor
    public Wishlist(Long id, Long customerId, Long shoeId, LocalDateTime addedDate) {
        this.id = id;
        this.customerId = customerId;
        this.shoeId = shoeId;
        this.addedDate = addedDate;
    }
    //getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getCustomerId() { return customerId; }
    public void setCustomerId(Long customerId) { this.customerId = customerId; }
    public Long getShoeId() { return shoeId; }
    public void setShoeId(Long shoeId) { this.shoeId = shoeId; }
    public LocalDateTime getAddedDate() { return addedDate; }
    public void setAddedDate(LocalDateTime addedDate) { this.addedDate = addedDate; }
}
