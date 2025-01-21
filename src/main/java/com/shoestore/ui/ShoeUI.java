package com.shoestore.ui;
import com.shoestore.dao.ShoeDAO;
import com.shoestore.models.Shoe;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
public class ShoeUI extends JFrame {
    private JTable shoeTable;
    private DefaultTableModel tableModel;
    private ShoeDAO shoeDAO;
    public ShoeUI() {
        shoeDAO = new ShoeDAO();
        setTitle("Shoe Management");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        //table for displaying shoes
        tableModel = new DefaultTableModel(new Object[]{"ID", "Name", "Brand", "Price", "Sizes", "Stock"}, 0);
        shoeTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(shoeTable);
        //buttons
        JButton btnAdd = new JButton("Add");
        JButton btnEdit = new JButton("Edit");
        JButton btnDelete = new JButton("Delete");
        //button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnEdit);
        buttonPanel.add(btnDelete);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        //load initial data
        loadShoes();
        //button actions
        btnAdd.addActionListener(e -> addShoe());
        btnEdit.addActionListener(e -> editShoe());
        btnDelete.addActionListener(e -> deleteShoe());
    }
    private void loadShoes() {
        tableModel.setRowCount(0); // Clear the table
        List<Shoe> shoes = shoeDAO.getAllShoes();
        for (Shoe shoe : shoes) {
            tableModel.addRow(new Object[]{
                    shoe.getId(),
                    shoe.getName(),
                    shoe.getBrand(),
                    shoe.getPrice(),
                    String.join(", ", shoe.getSizeOptions()),
                    shoe.getStock()
            });
        }
    }
    private void addShoe() {
        String name = JOptionPane.showInputDialog(this, "Enter shoe name:");
        String brand = JOptionPane.showInputDialog(this, "Enter shoe brand:");
        String priceStr = JOptionPane.showInputDialog(this, "Enter shoe price:");
        String sizesStr = JOptionPane.showInputDialog(this, "Enter shoe sizes (comma-separated):");
        String stock = JOptionPane.showInputDialog(this, "Enter shoe stock:");
        if (name != null && brand != null && priceStr != null && sizesStr != null && stock != null) {
            try {
                double price = Double.parseDouble(priceStr);
                String[] sizes = sizesStr.split(",");
                Shoe newShoe = new Shoe(null, name, brand, price, sizes, stock);
                shoeDAO.addShoe(newShoe);
                loadShoes();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid input. Please try again.");
            }
        }
    }
    private void editShoe() {
        int selectedRow = shoeTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a shoe to edit.");
            return;
        }
        Long shoeId = (Long) tableModel.getValueAt(selectedRow, 0);
        String newName = JOptionPane.showInputDialog(this, "Enter new shoe name:", tableModel.getValueAt(selectedRow, 1));
        String newBrand = JOptionPane.showInputDialog(this, "Enter new shoe brand:", tableModel.getValueAt(selectedRow, 2));
        String newPriceStr = JOptionPane.showInputDialog(this, "Enter new shoe price:", tableModel.getValueAt(selectedRow, 3));
        String newSizesStr = JOptionPane.showInputDialog(this, "Enter new shoe sizes (comma-separated):", tableModel.getValueAt(selectedRow, 4));
        String newStock = JOptionPane.showInputDialog(this, "Enter new shoe stock:", tableModel.getValueAt(selectedRow, 5));
        if (newName != null && newBrand != null && newPriceStr != null && newSizesStr != null && newStock != null) {
            try {
                double newPrice = Double.parseDouble(newPriceStr);
                String[] newSizes = newSizesStr.split(",");
                Shoe updatedShoe = new Shoe(shoeId, newName, newBrand, newPrice, newSizes, newStock);
                shoeDAO.updateShoe(updatedShoe);
                //update table row without reloading all data
                tableModel.setValueAt(newName, selectedRow, 1);
                tableModel.setValueAt(newBrand, selectedRow, 2);
                tableModel.setValueAt(newPrice, selectedRow, 3);
                tableModel.setValueAt(String.join(", ", newSizes), selectedRow, 4);
                tableModel.setValueAt(newStock, selectedRow, 5);
                JOptionPane.showMessageDialog(this, "Shoe updated successfully!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid input. Please try again.");
            }
        }
    }
    private void deleteShoe() {
        int selectedRow = shoeTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a shoe to delete.");
            return;
        }
        Long shoeId = (Long) tableModel.getValueAt(selectedRow, 0);
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this shoe?");
        if (confirm == JOptionPane.YES_OPTION) {
            shoeDAO.deleteShoe(shoeId);
            loadShoes();
        }
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ShoeUI().setVisible(true);
        });
    }
}
