package Contracts.Mappers;

import Domain.Dtos.CustomerDto;
import Infrastructure.Models.City;

public class MapCustomerDtoToCity {

    public static City Map(CustomerDto customerDto){
        City city = new City(customerDto.getCityId(), customerDto.getCity(), customerDto.getCountryId(), null, null,
                null, null);

        return city;
    }
}
