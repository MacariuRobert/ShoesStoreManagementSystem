package com.shoestore.dao;
import com.shoestore.db.DatabaseConnection;
import com.shoestore.models.Address;
import com.shoestore.models.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class CustomerDAO {
    //add a customer
    public void addCustomer(Customer customer) {
        String addressSql = "INSERT INTO address (street, city, state, postal_code, country) VALUES (?, ?, ?, ?, ?) RETURNING id_address";
        String customerSql = "INSERT INTO customer (name, email, password, address_id) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false); //start transaction
            //insert address
            Long addressId;
            try (PreparedStatement addressStmt = conn.prepareStatement(addressSql)) {
                addressStmt.setString(1, customer.getAddress().getStreet());
                addressStmt.setString(2, customer.getAddress().getCity());
                addressStmt.setString(3, customer.getAddress().getState());
                addressStmt.setString(4, customer.getAddress().getPostalCode());
                addressStmt.setString(5, customer.getAddress().getCountry());
                ResultSet rs = addressStmt.executeQuery();
                if (rs.next()) {
                    addressId = rs.getLong("id_address");
                } else {
                    conn.rollback();
                    throw new SQLException("Failed to insert address.");
                }
            }
            //insert customer
            try (PreparedStatement customerStmt = conn.prepareStatement(customerSql)) {
                customerStmt.setString(1, customer.getName());
                customerStmt.setString(2, customer.getEmail());
                customerStmt.setString(3, customer.getPassword());
                customerStmt.setLong(4, addressId);
                customerStmt.executeUpdate();
            }
            conn.commit(); //commit transaction
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //retrieve all customers
    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT c.id_customer, c.name, c.email, c.password, " +
                "a.id_address, a.street, a.city, a.state, a.postal_code, a.country " +
                "FROM customer c " +
                "LEFT JOIN address a ON c.address_id = a.id_address";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Address address = new Address(
                        rs.getLong("id_address"),
                        rs.getString("street"),
                        rs.getString("city"),
                        rs.getString("state"),
                        rs.getString("postal_code"),
                        rs.getString("country")
                );
                Customer customer = new Customer(
                        rs.getLong("id_customer"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("password"),
                        address,
                        null
                );
                customers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }
    //update a customer
    public void updateCustomer(Customer customer) {
        String addressSql = "UPDATE address SET street = ?, city = ?, state = ?, postal_code = ?, country = ? WHERE id_address = ?";
        String customerSql = "UPDATE customer SET name = ?, email = ?, password = ? WHERE id_customer = ?";
        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false);
            //update address
            try (PreparedStatement addressStmt = conn.prepareStatement(addressSql)) {
                addressStmt.setString(1, customer.getAddress().getStreet());
                addressStmt.setString(2, customer.getAddress().getCity());
                addressStmt.setString(3, customer.getAddress().getState());
                addressStmt.setString(4, customer.getAddress().getPostalCode());
                addressStmt.setString(5, customer.getAddress().getCountry());
                addressStmt.setLong(6, customer.getAddress().getId());
                addressStmt.executeUpdate();
            }
            //update customer
            try (PreparedStatement customerStmt = conn.prepareStatement(customerSql)) {
                customerStmt.setString(1, customer.getName());
                customerStmt.setString(2, customer.getEmail());
                customerStmt.setString(3, customer.getPassword());
                customerStmt.setLong(4, customer.getId());
                customerStmt.executeUpdate();
            }
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //delete a customer
    public void deleteCustomer(Long customerId) {
        String customerSql = "DELETE FROM customer WHERE id_customer = ?";
        String addressSql = "DELETE FROM address WHERE id_address = (SELECT address_id FROM customer WHERE id_customer = ?)";

        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false);

            //delete customer
            try (PreparedStatement customerStmt = conn.prepareStatement(customerSql)) {
                customerStmt.setLong(1, customerId);
                customerStmt.executeUpdate();
            }
            //delete address
            try (PreparedStatement addressStmt = conn.prepareStatement(addressSql)) {
                addressStmt.setLong(1, customerId);
                addressStmt.executeUpdate();
            }
            conn.commit(); //commit transaction
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
