package Contracts.Interfaces.Repositories;

import Infrastructure.Models.Address;

import java.sql.SQLException;

public interface IAddressRepository {
    int getMaxId() throws SQLException;
    int insertAddress(Address address) throws SQLException;
    Address getAddressById(int addressId) throws SQLException;
    int updateAddress(Address address) throws SQLException;
    int deleteAddress(int addressId) throws SQLException;
}
