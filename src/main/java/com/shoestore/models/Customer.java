package com.shoestore.models;
import java.util.List;
public class Customer {
    private Long id;
    private String name;
    private String email;
    private String password;
    private Address address;
    private List<Order> orders;
    //default Constructor
    public Customer() {}

    //constructor
    public Customer(Long id, String name, String email, String password, Address address, List<Order> orders) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
        this.orders = orders;
    }
    //getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public Address getAddress() { return address; }
    public void setAddress(Address address) { this.address = address; }
    public List<Order> getOrders() { return orders; }
    public void setOrders(List<Order> orders) { this.orders = orders; }
}
