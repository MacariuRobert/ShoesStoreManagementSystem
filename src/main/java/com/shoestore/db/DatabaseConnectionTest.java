package com.shoestore.db;
import java.sql.Connection;
public class DatabaseConnectionTest {
    public static void main(String[] args) {
        Connection connection = DatabaseConnection.getConnection();
        if (connection != null) {
            System.out.println("Connection test successful!");
        } else {
            System.out.println("Connection test failed.");
        }
    }
}
