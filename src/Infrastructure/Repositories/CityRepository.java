package Infrastructure.Repositories;

import Contracts.Interfaces.Repositories.ICityRepository;
import Infrastructure.DatabaseConnection;
import Infrastructure.Models.City;
import com.mysql.jdbc.PreparedStatement;

import javax.xml.crypto.Data;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class CityRepository implements ICityRepository {

    @Override
    public int getMaxId() throws SQLException {
        PreparedStatement statement = (PreparedStatement) DatabaseConnection.dbConnection.prepareStatement(
                "SELECT MAX(cityId) FROM city");

        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            int maxId = resultSet.getInt(1);

            return maxId;
        }

        return 0;
    }

    @Override
    public int insertCity(City city) throws SQLException {
        int cityId = getMaxId() + 1;
        PreparedStatement statement = (PreparedStatement) DatabaseConnection.dbConnection.prepareStatement(
                "INSERT INTO city VALUES (?, ?, ?, ?, ?, ?, ?)");

        statement.setInt(1, cityId);
        statement.setString(2, city.getCity());
        statement.setInt(3, city.getCountryId());
        statement.setTimestamp(4, Timestamp.valueOf(city.getCreateDate()));
        statement.setString(5, city.getCreatedBy());
        statement.setTimestamp(6, Timestamp.valueOf(city.getLastUpdate()));
        statement.setString(7, city.getLastUpdateBy());

        int affectedRows = statement.executeUpdate();

        return affectedRows;
    }

    @Override
    public int getIdByCityName(String cityName) throws SQLException {
        PreparedStatement statement = (com.mysql.jdbc.PreparedStatement) DatabaseConnection.dbConnection.prepareStatement(
                "SELECT cityId FROM city WHERE city = ?");

        statement.setString(1, cityName);

        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
            int cityId = resultSet.getInt(1);

            return cityId;
        }

        return 0;
    }

    @Override
    public City getCityById(int cityId) throws SQLException {
        City city = null;
        PreparedStatement statement = (com.mysql.jdbc.PreparedStatement) DatabaseConnection.dbConnection.prepareStatement(
                "SELECT * FROM city WHERE cityId = ?");

        statement.setInt(1, cityId);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            int id = resultSet.getInt("cityId");
            String cityName = resultSet.getString("city");
            int countryId = resultSet.getInt("countryId");
            LocalDateTime createDate = resultSet.getTimestamp("createDate").toLocalDateTime();
            String createdBy = resultSet.getString("createdBy");
            LocalDateTime lastUpdate = resultSet.getTimestamp("lastUpdate").toLocalDateTime();
            String lastUpdateBy = resultSet.getString("lastUpdateBy");

            city = new City(id, cityName, countryId, createDate, createdBy, lastUpdate, lastUpdateBy);
        }

        return city;
    }

    @Override
    public int updateCity(City city) throws SQLException {

        PreparedStatement statement = (PreparedStatement) DatabaseConnection.dbConnection.prepareStatement(
                "UPDATE city SET city = ?, countryId = ?, lastUpdate = ?, lastUpdateBy = ? WHERE cityId = ?");

        statement.setString(1, city.getCity());
        statement.setInt(2, city.getCountryId());
        statement.setTimestamp(3, Timestamp.valueOf(city.getLastUpdate()));
        statement.setString(4, city.getLastUpdateBy());
        statement.setInt(5, city.getCityId());

        int updatedCities = statement.executeUpdate();

        return updatedCities;
    }

    @Override
    public int deleteCity(int cityId) {
        return 0;
    }

    @Override
    public boolean doesCityExist(String cityName) throws SQLException {
        PreparedStatement statement = (PreparedStatement) DatabaseConnection.dbConnection.prepareStatement(
                "SELECT * FROM city WHERE city = ?");

        statement.setString(1, cityName);

        try {
            int cityId = 0;
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                cityId = resultSet.getInt(1);
            }

            if (cityId == 0){
                return false;
            }
            else {
                return true;
            }
        }
        catch (SQLException e){
            System.out.println(e);
            return false;
        }

    }
}
