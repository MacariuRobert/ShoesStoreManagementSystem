package com.shoestore.models;
import java.time.LocalDateTime;
public class Review {
    private Long id;
    private Customer customer;
    private Shoe shoe;
    private Integer rating;
    private String comment;
    private LocalDateTime timestamp;
    //default Constructor
    public Review() {}

    //constructor
    public Review(Long id, Customer customer, Shoe shoe, Integer rating, String comment, LocalDateTime timestamp) {
        if (rating < 1 || rating > 5) {
            throw new IllegalArgumentException("Rating must be between 1 and 5.");
        }
        this.id = id;
        this.customer = customer;
        this.shoe = shoe;
        this.rating = rating;
        this.comment = comment;
        this.timestamp = timestamp == null ? LocalDateTime.now() : timestamp;
    }
    //getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }
    public Shoe getShoe() { return shoe; }
    public void setShoe(Shoe shoe) { this.shoe = shoe; }
    public Integer getRating() { return rating; }
    public void setRating(Integer rating) {
        if (rating < 1 || rating > 5) {
            throw new IllegalArgumentException("Rating must be between 1 and 5.");
        }
        this.rating = rating;
    }
    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
    //methods
    public boolean isVerifiedPurchase() {
        return customer != null && shoe != null;
    }
    @Override
    public String toString() {
        return "Review{id=" + id + ", customer=" + customer + ", shoe=" + shoe +
                ", rating=" + rating + ", comment='" + comment + '\'' +
                ", timestamp=" + timestamp + '}';
    }
}
