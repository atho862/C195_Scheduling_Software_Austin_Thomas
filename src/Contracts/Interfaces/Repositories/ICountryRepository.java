package Contracts.Interfaces.Repositories;

import Infrastructure.Models.Country;

import java.sql.SQLException;

public interface ICountryRepository {
    int getMaxId() throws SQLException;
    int insertCountry(Country country) throws SQLException;
    Country getCountryById(int countryId) throws SQLException;
    int updateCountry(Country country);
    int deleteCountry(int countryId);
    String getCountryNameByCountryId(int countryId) throws SQLException;
    boolean doesCountryExist(String country) throws SQLException;
    int getCountryId(String countryName) throws SQLException;
}
