package Contracts.Mappers;

import Domain.Dtos.CustomerDto;
import Infrastructure.Models.Customer;

public class MapCustomerDtoToCustomer extends BaseMapper<Customer, CustomerDto>{

    public Customer Map(CustomerDto customerDto){
        Customer customer = new Customer(customerDto.getCustomerId(), customerDto.getCustomerName(), customerDto.getAddressId(),
                customerDto.isActive(), null, null, null, null);

        return customer;
    }
}
