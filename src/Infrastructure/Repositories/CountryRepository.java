package Infrastructure.Repositories;

import Contracts.Interfaces.Repositories.ICountryRepository;
import Infrastructure.DatabaseConnection;
import Infrastructure.Models.Country;
import com.mysql.jdbc.PreparedStatement;
import com.sun.javaws.progress.PreloaderPostEventListener;

import javax.xml.crypto.Data;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class CountryRepository implements ICountryRepository {

    public int getMaxId() throws SQLException {
        PreparedStatement statement = (PreparedStatement) DatabaseConnection.dbConnection.prepareStatement(
                "SELECT MAX(countryId) from country");

        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
            int countryId = resultSet.getInt(1);

            return countryId;
        }

        return 0;
    }

    @Override
    public boolean doesCountryExist(String countryName) throws SQLException {

        PreparedStatement statement = (PreparedStatement) DatabaseConnection.dbConnection.prepareStatement(
                "SELECT * FROM country WHERE country = ?");

        statement.setString(1, countryName);

        try {
            int countryId = 0;
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                countryId = resultSet.getInt(1);
            }
            if (countryId == 0){
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

    @Override
    public int insertCountry(Country country) throws SQLException {
        int countryId = getMaxId() + 1;
        PreparedStatement statement = (PreparedStatement) DatabaseConnection.dbConnection.prepareStatement(
                "INSERT INTO country VALUES (?, ?, ?, ?, ?, ?)");
        statement.setInt(1, countryId);
        statement.setString(2, country.getCountry());
        statement.setTimestamp(3, Timestamp.valueOf(country.getCreateDate()));
        statement.setString(4, country.getCreatedBy());
        statement.setTimestamp(5, Timestamp.valueOf(country.getLastUpdate()));
        statement.setString(6, country.getLastUpdateBy());
        try {
            int countriesAdded = statement.executeUpdate();
            return countriesAdded;
        }
        catch (SQLException e){
            System.out.println(e);
            return 0;
        }
    }

    @Override
    public int getCountryId(String countryName) throws SQLException {
        PreparedStatement statement = (PreparedStatement) DatabaseConnection.dbConnection.prepareStatement(
                "SELECT countryId FROM country WHERE country = ?");

        statement.setString(1, countryName);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
            int countryId = resultSet.getInt(1);

            return countryId;
        }

        return 0;
    }

    @Override
    public Country getCountryById(int countryId) throws SQLException {
        Country country;
        PreparedStatement statement = (PreparedStatement) DatabaseConnection.dbConnection.prepareStatement(
                "SELECT * FROM country WHERE countryId = ?");
        statement.setInt(1, countryId);

        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
            String countryName = resultSet.getString("country");
            LocalDateTime createDate = resultSet.getTimestamp("createDate").toLocalDateTime();
            String createdBy = resultSet.getString("createdBy");
            LocalDateTime lastUpdate = resultSet.getTimestamp("lastUpdate").toLocalDateTime();
            String lastUpdateBy = resultSet.getString("lastUpdateBy");

            country = new Country(countryId, countryName, createDate, createdBy, lastUpdate, lastUpdateBy);

            return country;
        }

        return null;
    }

    @Override
    public int updateCountry(Country country) {
        return 0;
    }

    @Override
    public int deleteCountry(int countryId) {
        return 0;
    }

    @Override
    public String getCountryNameByCountryId(int countryId) throws SQLException {
        String countryName = "";

        PreparedStatement statement = (PreparedStatement) DatabaseConnection.dbConnection.prepareStatement(
                "SELECT country FROM country WHERE countryId = ?");

        statement.setInt(1, countryId);

        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
            countryName = resultSet.getString(1);
        }

        return countryName;
    }
}
