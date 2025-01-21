package com.shoestore.ui;
import com.shoestore.dao.CustomerDAO;
import com.shoestore.models.Address;
import com.shoestore.models.Customer;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
public class CustomerUI extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private CustomerDAO customerDAO;
    public CustomerUI() {
        customerDAO = new CustomerDAO();
        setTitle("Customer Management");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        //table setup
        tableModel = new DefaultTableModel(new String[]{"ID", "Name", "Email", "Address"}, 0);
        table = new JTable(tableModel);
        loadCustomers();
        JScrollPane scrollPane = new JScrollPane(table);
        //buttons for actions
        JButton btnAdd = new JButton("Add Customer");
        JButton btnEdit = new JButton("Edit Customer");
        JButton btnDelete = new JButton("Delete Customer");
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnEdit);
        buttonPanel.add(btnDelete);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        //button actions
        btnAdd.addActionListener(e -> addCustomer());
        btnEdit.addActionListener(e -> editCustomer());
        btnDelete.addActionListener(e -> deleteCustomer());
    }
    private void loadCustomers() {
        tableModel.setRowCount(0); // Clear existing rows
        List<Customer> customers = customerDAO.getAllCustomers();
        for (Customer customer : customers) {
            String address = customer.getAddress() != null
                    ? customer.getAddress().getStreet() + ", " +
                    customer.getAddress().getCity() + ", " +
                    customer.getAddress().getState() + " " +
                    customer.getAddress().getPostalCode() + ", " +
                    customer.getAddress().getCountry()
                    : "invalid";
            tableModel.addRow(new Object[]{
                    customer.getId(),
                    customer.getName(),
                    customer.getEmail(),
                    address
            });
        }
    }
    private void addCustomer() {
        String name = JOptionPane.showInputDialog(this, "Enter Name:");
        String email = JOptionPane.showInputDialog(this, "Enter Email:");
        String password = JOptionPane.showInputDialog(this, "Enter Password:");
        String street = JOptionPane.showInputDialog(this, "Enter Address Street:");
        String city = JOptionPane.showInputDialog(this, "Enter Address City:");
        String state = JOptionPane.showInputDialog(this, "Enter Address State:");
        String postalCode = JOptionPane.showInputDialog(this, "Enter Address Postal Code:");
        String country = JOptionPane.showInputDialog(this, "Enter Address Country:");
        if (name != null && email != null && password != null &&
                street != null && city != null && state != null &&
                postalCode != null && country != null) {
            Address address = new Address(null, street, city, state, postalCode, country);
            Customer customer = new Customer(null, name, email, password, address, null);
            customerDAO.addCustomer(customer);
            loadCustomers();
        }
    }
    private void editCustomer() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Select a customer first!");
            return;
        }
        Long id = (Long) tableModel.getValueAt(selectedRow, 0);
        String name = JOptionPane.showInputDialog(this, "Enter Name:", tableModel.getValueAt(selectedRow, 1));
        String email = JOptionPane.showInputDialog(this, "Enter Email:", tableModel.getValueAt(selectedRow, 2));
        String street = JOptionPane.showInputDialog(this, "Enter Address Street:");
        String city = JOptionPane.showInputDialog(this, "Enter Address City:");
        String state = JOptionPane.showInputDialog(this, "Enter Address State:");
        String postalCode = JOptionPane.showInputDialog(this, "Enter Address Postal Code:");
        String country = JOptionPane.showInputDialog(this, "Enter Address Country:");
        if (name != null && email != null && street != null && city != null &&
                state != null && postalCode != null && country != null) {
            Address address = new Address(null, street, city, state, postalCode, country);
            Customer customer = new Customer(id, name, email, null, address, null);
            customerDAO.updateCustomer(customer);
            loadCustomers();
        }
    }
    private void deleteCustomer() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Select a customer first!");
            return;
        }
        Long id = (Long) tableModel.getValueAt(selectedRow, 0);
        customerDAO.deleteCustomer(id);
        loadCustomers();
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CustomerUI().setVisible(true));
    }
}
