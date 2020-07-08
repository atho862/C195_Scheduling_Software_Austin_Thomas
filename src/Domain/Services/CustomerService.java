package Domain.Services;

import Contracts.Interfaces.Repositories.ICustomerRepository;
import Contracts.Interfaces.Services.ICustomerService;
import Infrastructure.Repositories.CustomerRepository;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class CustomerService implements ICustomerService {
    ICustomerRepository customerRepository = new CustomerRepository();


    @Override
    public ObservableList<String> getCustomerNames() throws SQLException {
        return customerRepository.getCustomerNames();
    }
}
