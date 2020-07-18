package Contracts.Mappers;

import Domain.Dtos.CustomerDto;
import Infrastructure.Models.Address;

public class MapCustomerDtoToAddress {

    public static Address Map(CustomerDto customerDto){
        Address address = new Address(customerDto.getAddressId(), customerDto.getAddress(), customerDto.getAddress2(),
                customerDto.getCityId(), customerDto.getPostalCode(), customerDto.getPhone(), null, null, null, null);

        return address;
    }
}
