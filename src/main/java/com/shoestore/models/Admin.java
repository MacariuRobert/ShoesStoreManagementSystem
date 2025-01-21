package com.shoestore.models;
public class Admin {
    private Long id;
    private String username;
    private String password;
    //default Constructor
    public Admin() {}

    //constructor
    public Admin(Long id, String username, String password) {
        if (password == null || password.length() < 8) {
            throw new IllegalArgumentException("Password must be at least 8 characters long.");
        }
        this.id = id;
        this.username = username;
        this.password = password;
    }
    //getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) {
        if (password.length() < 8) {
            throw new IllegalArgumentException("Password must be at least 8 characters long.");
        }
        this.password = password;
    }
    //methods
    public boolean authenticate(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }
    @Override
    public String toString() {
        return "Admin{id=" + id + ", username='" + username + '\'' + '}';
    }
}
