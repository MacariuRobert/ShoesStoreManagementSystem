package com.shoestore;

import com.shoestore.db.DatabaseConnection;

import java.sql.Connection;

public class DatabaseConnectionTest {
    public static void main(String[] args) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            System.out.println("Database connection successful!");
        } catch (Exception e) {
            System.err.println("Database connection failed: " + e.getMessage());
        }
    }
}
