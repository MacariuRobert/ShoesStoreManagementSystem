package com.shoestore.models;
public class OrderItem {
    private Long id;
    private Long orderId; // Changed from Order object to orderId for simplicity
    private Shoe shoe;
    private int size;
    private int quantity;
    private double price;
    //full constructor
    public OrderItem(Long id, Long orderId, Shoe shoe, int size, int quantity, double price) {
        this.id = id;
        this.orderId = orderId;
        this.shoe = shoe;
        this.size = size;
        this.quantity = quantity;
        this.price = price;
    }
    //constructor without ID
    public OrderItem(Long orderId, Shoe shoe, int size, int quantity, double price) {
        this(null, orderId, shoe, size, quantity, price);
    }
    //default constructor
    public OrderItem() {}
    //getters and setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getOrderId() {
        return orderId;
    }
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
    public Shoe getShoe() {
        return shoe;
    }
    public void setShoe(Shoe shoe) {
        this.shoe = shoe;
    }
    public int getSize() {
        return size;
    }
    public void setSize(int size) {
        this.size = size;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", shoe=" + shoe +
                ", size=" + size +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
