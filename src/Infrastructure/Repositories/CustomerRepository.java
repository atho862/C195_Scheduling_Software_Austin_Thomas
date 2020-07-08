package Infrastructure.Repositories;

import Contracts.Interfaces.Repositories.ICustomerRepository;
import Infrastructure.DatabaseConnection;
import Infrastructure.Models.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerRepository implements ICustomerRepository {
    @Override
    public void insertCustomer(Customer customer) {

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
    public void updateCustomer(Customer customer) {

    }

    @Override
    public void deleteCustomer(int customerId) {

    }
}
