package com.shoestore.models;
import java.util.ArrayList;
import java.util.List;
public class Order {
    private Long id;
    private Customer customer;
    private List<OrderItem> orderItems;
    private String status;
    private TrackingDetails trackingDetails;
    //full constructor
    public Order(Long id, Customer customer, List<OrderItem> orderItems, String status, TrackingDetails trackingDetails) {
        this.id = id;
        this.customer = customer;
        this.orderItems = (orderItems != null) ? orderItems : new ArrayList<>();
        this.status = status;
        this.trackingDetails = trackingDetails;
    }
    //constructor without orderItems
    public Order(Long id, Customer customer, String status, TrackingDetails trackingDetails) {
        this(id, customer, new ArrayList<>(), status, trackingDetails);
    }
    //default constructor
    public Order() {
        this(null, null, new ArrayList<>(), null, null);
    }
    //getters and setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Customer getCustomer() {
        return customer;
    }
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    public List<OrderItem> getOrderItems() {
        return orderItems;
    }
    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = (orderItems != null) ? orderItems : new ArrayList<>();
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public TrackingDetails getTrackingDetails() {
        return trackingDetails;
    }
    public void setTrackingDetails(TrackingDetails trackingDetails) {
        this.trackingDetails = trackingDetails;
    }
    //method to calculate the total price of the order
    public double calculateTotal() {
        return this.orderItems.stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();
    }
    public double getTotalPrice() {
        return calculateTotal();
    }
    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customer=" + customer +
                ", orderItems=" + orderItems +
                ", status='" + status + '\'' +
                ", trackingDetails=" + trackingDetails +
                '}';
    }
}
