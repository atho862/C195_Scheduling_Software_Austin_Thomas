package Infrastructure.Repositories;

import Contracts.Interfaces.Repositories.IAddressRepository;
import Infrastructure.DatabaseConnection;
import Infrastructure.Models.Address;
import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class AddressRepository implements IAddressRepository {

    public int getMaxId() throws SQLException {
        PreparedStatement statement = (PreparedStatement) DatabaseConnection.dbConnection.prepareStatement(
                "SELECT MAX(addressId) FROM address");

        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
            int id = resultSet.getInt(1);
            return id;
        }

        return 0;
    }

    @Override
    public int insertAddress(Address address) throws SQLException {
        int addressId = getMaxId() + 1;
        PreparedStatement statement = (PreparedStatement) DatabaseConnection.dbConnection.prepareStatement(
                "INSERT INTO address VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

        statement.setInt(1, addressId);
        statement.setString(2, address.getAddress());
        statement.setString(3, address.getAddress2());
        statement.setInt(4, address.getCityId());
        statement.setString(5, address.getPostalCode());
        statement.setString(6, address.getPhone());
        statement.setTimestamp(7, Timestamp.valueOf(address.getCreateDate()));
        statement.setString(8, address.getCreatedBy());
        statement.setTimestamp(9, Timestamp.valueOf(address.getLastUpdate()));
        statement.setString(10, address.getLastUpdateBy());
        try {
            int affectedRows = statement.executeUpdate();
            return affectedRows;
        }
        catch (SQLException e){
            System.out.println(e);
        }

        return 0;
    }

    @Override
    public Address getAddressById(int addressId) {
        try {
            Address address = null;
            PreparedStatement statement = null;

            statement = (PreparedStatement) DatabaseConnection.dbConnection.prepareStatement(
                    "SELECT * FROM address WHERE addressId = ?");

            statement.setInt(1, addressId);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("addressId");
                String address1 = resultSet.getString("address");
                String address2 = resultSet.getString("address2");
                int cityId = resultSet.getInt("cityId");
                String postalCode = resultSet.getString("postalCode");
                String phone = resultSet.getString("phone");
                LocalDateTime createDate = resultSet.getTimestamp("createDate").toLocalDateTime();
                String createdBy = resultSet.getString("createdBy");
                LocalDateTime lastUpdate = resultSet.getTimestamp("lastUpdate").toLocalDateTime();
                String lastUpdateBy = resultSet.getString("lastUpdateBy");

                address = new Address(id, address1, address2, cityId, postalCode, phone, createDate, createdBy, lastUpdate, lastUpdateBy);

                return address;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public int updateAddress(Address address) throws SQLException {

        PreparedStatement statement = (PreparedStatement) DatabaseConnection.dbConnection.prepareStatement(
                "UPDATE address SET address = ?, address2 = ?, cityId = ?, postalCode = ?, phone = ?, lastUpdate = ?, lastUpdateBy = ? WHERE addressId = ?");
        statement.setString(1, address.getAddress());
        statement.setString(2, address.getAddress2());
        statement.setInt(3, address.getCityId());
        statement.setString(4, address.getPostalCode());
        statement.setString(5, address.getPhone());
        statement.setTimestamp(6, Timestamp.valueOf(address.getLastUpdate()));
        statement.setString(7, address.getLastUpdateBy());
        statement.setInt(8, address.getAddressId());

        try {
            int updatedRows = statement.executeUpdate();

            return updatedRows;
        }
        catch (SQLException e){
            System.out.println(e);
            return 0;
        }
    }

    @Override
    public int deleteAddress(int addressId) throws SQLException {

        PreparedStatement statement = (PreparedStatement) DatabaseConnection.dbConnection.prepareStatement(
                "DELETE FROM address WHERE addressId = ?");

        statement.setInt(1, addressId);
        try {
            int deletedAddresses = statement.executeUpdate();
            return deletedAddresses;
        }
        catch (SQLException e){
            System.out.println(e);
            return 0;
        }
    }
}
