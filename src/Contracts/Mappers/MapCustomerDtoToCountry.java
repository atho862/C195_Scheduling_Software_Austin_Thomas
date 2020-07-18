package Contracts.Mappers;

import Domain.Dtos.CustomerDto;
import Infrastructure.Models.Country;

public class MapCustomerDtoToCountry {

    public static Country Map(CustomerDto customerDto){
        Country country = new Country(customerDto.getCountryId(), customerDto.getCountry(), null, null, null, null);

        return country;
    }
}
