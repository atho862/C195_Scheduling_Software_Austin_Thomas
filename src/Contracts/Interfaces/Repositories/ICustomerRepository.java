package Contracts.Interfaces.Repositories;

import Infrastructure.Models.Customer;

public interface ICustomerRepository {
    void insertCustomer(Customer customer);
    Customer getCustomerById(int customerId);
    void updateCustomer(Customer customer);
    void deleteCustomer(int customerId);
}
