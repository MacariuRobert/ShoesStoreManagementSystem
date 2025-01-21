package com.shoestore.models;
public class Shoe {
    private Long id;
    private String name;
    private String brand;
    private double price;
    private String[] sizeOptions;
    private String stock;
    //default constructor
    public Shoe() {}
    //constructor for all fields
    public Shoe(Long id, String name, String brand, double price, String[] sizeOptions, String stock) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.sizeOptions = sizeOptions;
        this.stock = stock;
    }
    //constructor without id (for adding a new shoe)
    public Shoe(String name, String brand, double price, String[] sizeOptions, String stock) {
        this(null, name, brand, price, sizeOptions, stock);
    }
    //getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public String[] getSizeOptions() { return sizeOptions; }
    public void setSizeOptions(String[] sizeOptions) { this.sizeOptions = sizeOptions; }
    public String getStock() { return stock; }
    public void setStock(String stock) { this.stock = stock; }
}
