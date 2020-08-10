package Contracts.Mappers;

import Domain.Dtos.CustomerDto;
import Infrastructure.Models.City;
import Infrastructure.Models.Customer;

public class MapCustomerDtoToCity extends BaseMapper<City, CustomerDto> {

    public City Map(CustomerDto customerDto){
        City city = new City(customerDto.getCityId(), customerDto.getCity(), customerDto.getCountryId(), null, null,
                null, null);

        return city;
    }
}
