package Contracts.Interfaces.Repositories;

import Infrastructure.Models.City;

public interface ICityRepository {
    void insertCity(City city);
    City getCityById(int cityId);
    void updateCity(City city);
    void deleteCity(int cityId);
}
