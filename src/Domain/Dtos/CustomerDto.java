package Domain.Dtos;

import Infrastructure.Models.Customer;

public class CustomerDto {
    private int customerId;
    private String customerName;
    private boolean active;
    private int addressId;
    private String address;
    private String address2;
    private String postalCode;
    private String phone;
    private int cityId;
    private String city;
    private int countryId;
    private String country;

    public CustomerDto(int customerId, String customerName, boolean active, int addressId, String address, String address2,
                       String postalCode, String phone, int cityId, String city, int countryId, String country) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.active = active;
        this.addressId = addressId;
        this.address = address;
        this.address2 = address2;
        this.postalCode = postalCode;
        this.phone = phone;
        this.cityId = cityId;
        this.city = city;
        this.countryId = countryId;
        this.country = country;
    }


    public int getAddressId() {
        return addressId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
