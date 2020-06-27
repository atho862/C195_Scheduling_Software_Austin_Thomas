package Contracts.Interfaces.Repositories;

import Infrastructure.Models.Country;

public interface ICountryRepository {
    void insertCountry(Country country);
    Country getCountryById(int countryId);
    void updateCountry(Country country);
    void deleteCountry(int countryId);
}
