package Contracts.Interfaces.Services;

import Domain.Dtos.CustomerDto;
import Infrastructure.Models.Customer;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface ICustomerService {
    ObservableList<String> getCustomerNames() throws SQLException;
    ObservableList<CustomerDto> getAllCustomers() throws SQLException;
    int addCustomer(CustomerDto customerDto) throws SQLException;
    int updateCustomer(CustomerDto customerDto) throws SQLException;
    int deleteCustomer(int customerId) throws SQLException;
}
