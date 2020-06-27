package Infrastructure.Repositories;

import Contracts.Interfaces.Repositories.ICustomerRepository;
import Infrastructure.Models.Customer;

public class CustomerRepository implements ICustomerRepository {
    @Override
    public void insertCustomer(Customer customer) {

    }

    @Override
    public Customer getCustomerById(int customerId) {
        return null;
    }

    @Override
    public void updateCustomer(Customer customer) {

    }

    @Override
    public void deleteCustomer(int customerId) {

    }
}
