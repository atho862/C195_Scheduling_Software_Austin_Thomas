package Infrastructure.Repositories;

import Contracts.Interfaces.Repositories.ICustomerRepository;
import Infrastructure.DatabaseConnection;
import Infrastructure.Models.Customer;
import com.mysql.jdbc.PreparedStatement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class CustomerRepository implements ICustomerRepository {

    @Override
    public int getMaxId() throws SQLException {
        PreparedStatement statement = (PreparedStatement) DatabaseConnection.dbConnection.prepareStatement(
                "SELECT MAX(customerId) FROM customer");

        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
            int customerId = resultSet.getInt(1);

            return customerId;
        }

        return 0;
    }

    @Override
    public int insertCustomer(Customer customer) throws SQLException {
        int customerId = getMaxId() + 1;
        PreparedStatement statement = (PreparedStatement) DatabaseConnection.dbConnection.prepareStatement(
                "INSERT INTO customer VALUES (?, ?, ?, ?, ?, ?, ?, ?)");

        statement.setInt(1, customerId);
        statement.setString(2, customer.getCustomerName());
        statement.setInt(3, customer.getAddressId());
        statement.setBoolean(4, customer.isActive());
        statement.setTimestamp(5, Timestamp.valueOf(customer.getCreateDate()));
        statement.setString(6, customer.getCreatedBy());
        statement.setTimestamp(7, Timestamp.valueOf(customer.getLastUpdate()));
        statement.setString(8, customer.getLastUpdateBy());

        int affectedRows = statement.executeUpdate();

        return affectedRows;
    }

    @Override
    public Customer getCustomerById(int customerId) {
        return null;
    }

    @Override
    public String getCustomerNameById(int customerId) throws SQLException {

        String customerName = new String();
        PreparedStatement statement = (PreparedStatement) DatabaseConnection.dbConnection.prepareStatement(
                "SELECT customerName FROM customer WHERE customerId = ?");
        statement.setInt(1, customerId);

        ResultSet results = statement.executeQuery();
        while (results.next()){
            customerName = results.getString("customerName");
        }

        return customerName;
    }

    @Override
    public int getCustomerIdByCustomerName(String customerName) throws SQLException {

        com.mysql.jdbc.PreparedStatement statement = (com.mysql.jdbc.PreparedStatement) DatabaseConnection.dbConnection.prepareStatement(
                "SELECT customerId FROM customer WHERE customerName = ?");

        statement.setString(1, customerName);

        ResultSet resultSet = statement.executeQuery();
        while(resultSet.next()){
            int customerId = resultSet.getInt("customerId");
            return customerId;
        }

        return 0;
    }

    @Override
    public ObservableList<Customer> getAllCustomers() throws SQLException {

        ObservableList<Customer> customers = FXCollections.observableArrayList();
        PreparedStatement preparedStatement = (PreparedStatement) DatabaseConnection.dbConnection.prepareStatement(
                "SELECT * FROM customer");

        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            int customerId = resultSet.getInt("customerId");
            String customerName = resultSet.getString("customerName");
            boolean isActive = resultSet.getBoolean("active");
            int addressId = resultSet.getInt("addressId");
            LocalDateTime createDate = resultSet.getTimestamp("createDate").toLocalDateTime();
            String createdBy = resultSet.getString("createdBy");
            LocalDateTime lastUpdate = resultSet.getTimestamp("lastUpdate").toLocalDateTime();
            String lastUpdateBy = resultSet.getString("lastUpdateBy");

            Customer customer = new Customer(customerId, customerName, addressId, isActive, createDate, createdBy,
                    lastUpdate, lastUpdateBy);

            customers.add(customer);
        }

        return customers;
    }

    @Override
    public ObservableList<String> getCustomerNames() throws SQLException {
        ObservableList<String> customerNames = FXCollections.observableArrayList();
        com.mysql.jdbc.PreparedStatement statement = (com.mysql.jdbc.PreparedStatement) DatabaseConnection.dbConnection.prepareStatement(
                "SELECT customerName FROM customer");

        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
            String customerName = resultSet.getString("customerName");
            customerNames.add(customerName);
        }

        return customerNames;
    }

    @Override
    public int updateCustomer(Customer customer) throws SQLException {
        PreparedStatement statement = (PreparedStatement) DatabaseConnection.dbConnection.prepareStatement(
                "UPDATE customer SET customerName = ?, addressId = ?, active = ?, lastUpdate = ?, lastUpdateBy = ? WHERE customerId = ?");

        statement.setString(1, customer.getCustomerName());
        statement.setInt(2, customer.getAddressId());
        statement.setBoolean(3, customer.isActive());
        statement.setTimestamp(4, Timestamp.valueOf(customer.getLastUpdate()));
        statement.setString(5, customer.getLastUpdateBy());
        statement.setInt(6, customer.getCustomerId());

        try {
            int updatedCustomers = statement.executeUpdate();
            return updatedCustomers;
        }
        catch (SQLException e){
            System.out.println(e);
            return 0;
        }
    }

    @Override
    public int deleteCustomer(int customerId) throws SQLException {
        PreparedStatement statement = (PreparedStatement) DatabaseConnection.dbConnection.prepareStatement(
                "DELETE FROM customer WHERE customerId = ?");
        statement.setInt(1, customerId);

        int deletedCustomers = statement.executeUpdate();

        return deletedCustomers;
    }
}
