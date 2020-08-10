package Contracts.Mappers;

import Domain.Dtos.CustomerDto;
import Infrastructure.Models.Customer;

public class MapCustomerToCustomerDto extends BaseMapper<CustomerDto, Customer> {

    public CustomerDto Map(Customer customer){
        CustomerDto customerDto = new CustomerDto(customer.getCustomerId(), customer.getCustomerName(), customer.isActive(),
                customer.getAddressId(), null, null, null, null, 0, null, 0, null);

        return customerDto;
    }
}
