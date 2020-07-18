package Contracts.Interfaces.Repositories;

import Infrastructure.Models.City;

import java.sql.SQLException;

public interface ICityRepository {
    int getMaxId() throws SQLException;
    int insertCity(City city) throws SQLException;
    City getCityById(int cityId) throws SQLException;
    int updateCity(City city) throws SQLException;
    int deleteCity(int cityId);
    int getIdByCityName(String cityName) throws SQLException;
    boolean doesCityExist(String cityName) throws SQLException;
}
