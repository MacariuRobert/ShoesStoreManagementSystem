package com.shoestore.models;
public class Address {
    private Long id;
    private String street;
    private String city;
    private String state;
    private String postalCode;
    private String country;
    //default Constructor
    public Address() {}
    //constructor
    public Address(Long id, String street, String city, String state, String postalCode, String country) {
        this.id = id;
        this.street = street;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.country = country;
    }
    //getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getStreet() { return street; }
    public void setStreet(String street) { this.street = street; }
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    public String getState() { return state; }
    public void setState(String state) { this.state = state; }
    public String getPostalCode() { return postalCode; }
    public void setPostalCode(String postalCode) { this.postalCode = postalCode; }
    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }
    //methods
    public boolean isValidAddress() {
        return street != null && !street.isEmpty() &&
                city != null && !city.isEmpty() &&
                postalCode != null && !postalCode.isEmpty() &&
                country != null && !country.isEmpty();
    }
    @Override
    public String toString() {
        return "Address{id=" + id + ", street='" + street + '\'' + ", city='" + city + '\'' +
                ", state='" + state + '\'' + ", postalCode='" + postalCode + '\'' +
                ", country='" + country + '\'' + '}';
    }
}
