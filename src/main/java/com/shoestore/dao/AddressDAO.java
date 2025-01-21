package com.shoestore.dao;
import com.shoestore.db.DatabaseConnection;
import com.shoestore.models.Address;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class AddressDAO {

    //retrieve an address by ID
    public Address getAddressById(Long addressId) {
        String sql = "SELECT * FROM Address WHERE id_address = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, addressId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Address(
                        rs.getLong("id_address"),
                        rs.getString("street"),
                        rs.getString("city"),
                        rs.getString("state"),
                        rs.getString("country"),
                        rs.getString("zipcode")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
