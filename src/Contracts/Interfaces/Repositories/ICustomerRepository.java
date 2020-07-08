package Contracts.Interfaces.Repositories;

import Infrastructure.Models.Customer;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface ICustomerRepository {
    void insertCustomer(Customer customer);
    Customer getCustomerById(int customerId);
    void updateCustomer(Customer customer);
    void deleteCustomer(int customerId);
    String getCustomerNameById(int customerId) throws SQLException;
    ObservableList<String> getCustomerNames() throws SQLException;
    int getCustomerIdByCustomerName(String customerName) throws SQLException;
}
