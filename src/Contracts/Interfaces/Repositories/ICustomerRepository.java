package Contracts.Interfaces.Repositories;

import Infrastructure.Models.Customer;
import javafx.collections.ObservableList;
import sun.management.snmp.jvmmib.JvmThreadInstanceTableMeta;

import java.sql.SQLException;

public interface ICustomerRepository {
    int getMaxId() throws SQLException;
    int insertCustomer(Customer customer) throws SQLException;
    Customer getCustomerById(int customerId);
    int updateCustomer(Customer customer) throws SQLException;
    int deleteCustomer(int customerId) throws SQLException;
    String getCustomerNameById(int customerId) throws SQLException;
    ObservableList<String> getCustomerNames() throws SQLException;
    int getCustomerIdByCustomerName(String customerName) throws SQLException;
    ObservableList<Customer> getAllCustomers() throws SQLException;
}
