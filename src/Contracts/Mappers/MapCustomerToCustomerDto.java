package Contracts.Mappers;

import Domain.Dtos.CustomerDto;
import Infrastructure.Models.Customer;

public class MapCustomerToCustomerDto {

    public static CustomerDto Map(Customer customer){
        CustomerDto customerDto = new CustomerDto(customer.getCustomerId(), customer.getCustomerName(), customer.isActive(),
                customer.getAddressId(), null, null, null, null, 0, null, 0, null);

        return customerDto;
    }
}
