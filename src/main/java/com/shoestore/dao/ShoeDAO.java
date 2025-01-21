package com.shoestore.dao;
import com.shoestore.db.DatabaseConnection;
import com.shoestore.models.Shoe;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class ShoeDAO {
    //retrieve all shoes
    public List<Shoe> getAllShoes() {
        List<Shoe> shoes = new ArrayList<>();
        String sql = "SELECT * FROM Shoe";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                shoes.add(new Shoe(
                        rs.getLong("id_shoe"),
                        rs.getString("name"),
                        rs.getString("brand"),
                        rs.getDouble("price"),
                        (String[]) rs.getArray("size_options").getArray(),
                        rs.getString("stock")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return shoes;
    }
    //add a new shoe
    public boolean addShoe(Shoe shoe) {
        String sql = "INSERT INTO Shoe (name, brand, price, size_options, stock) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, shoe.getName());
            stmt.setString(2, shoe.getBrand());
            stmt.setDouble(3, shoe.getPrice());
            stmt.setArray(4, conn.createArrayOf("VARCHAR", shoe.getSizeOptions()));
            stmt.setString(5, shoe.getStock());
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        shoe.setId(rs.getLong(1));
                    }
                }
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    //update a shoe
    public boolean updateShoe(Shoe shoe) {
        String sql = "UPDATE Shoe SET name = ?, brand = ?, price = ?, size_options = ?, stock = ? WHERE id_shoe = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, shoe.getName());
            stmt.setString(2, shoe.getBrand());
            stmt.setDouble(3, shoe.getPrice());
            stmt.setArray(4, conn.createArrayOf("VARCHAR", shoe.getSizeOptions()));
            stmt.setString(5, shoe.getStock());
            stmt.setLong(6, shoe.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    //delete a shoe
    public boolean deleteShoe(Long shoeId) {
        String sql = "DELETE FROM Shoe WHERE id_shoe = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, shoeId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
