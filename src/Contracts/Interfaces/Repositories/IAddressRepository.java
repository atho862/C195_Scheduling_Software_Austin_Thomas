package Contracts.Interfaces.Repositories;

import Infrastructure.Models.Address;

public interface IAddressRepository {
    void insertAddress(Address address);
    Address getAddressById(int addressId);
    void updateAddress(Address address);
    void deleteAddress(int addressId);
}
