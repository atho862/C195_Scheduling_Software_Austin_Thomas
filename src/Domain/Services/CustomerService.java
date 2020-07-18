package Domain.Services;

import Contracts.Interfaces.Repositories.IAddressRepository;
import Contracts.Interfaces.Repositories.ICityRepository;
import Contracts.Interfaces.Repositories.ICountryRepository;
import Contracts.Interfaces.Repositories.ICustomerRepository;
import Contracts.Interfaces.Services.ICustomerService;
import Contracts.Mappers.*;
import Contracts.Statics.UserStatics;
import Domain.Dtos.CustomerDto;
import Domain.Helpers.CustomerHelper;
import Infrastructure.DatabaseConnection;
import Infrastructure.Models.Address;
import Infrastructure.Models.City;
import Infrastructure.Models.Country;
import Infrastructure.Models.Customer;
import Infrastructure.Repositories.AddressRepository;
import Infrastructure.Repositories.CityRepository;
import Infrastructure.Repositories.CountryRepository;
import Infrastructure.Repositories.CustomerRepository;
import com.mysql.jdbc.PreparedStatement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class CustomerService implements ICustomerService {

    ICustomerRepository customerRepository = new CustomerRepository();
    IAddressRepository addressRepository = new AddressRepository();
    ICountryRepository countryRepository = new CountryRepository();
    ICityRepository cityRepository = new CityRepository();
    CustomerHelper customerHelper = new CustomerHelper();

    @Override
    public ObservableList<String> getCustomerNames() throws SQLException {
        return customerRepository.getCustomerNames();
    }

    @Override
    public ObservableList<CustomerDto> getAllCustomers() throws SQLException {
        ObservableList<CustomerDto> customerDtos = FXCollections.observableArrayList();
        ObservableList<Customer> customers = customerRepository.getAllCustomers();
        for (Customer customer : customers
             ) {
            CustomerDto customerDto = MapCustomerToCustomerDto.Map(customer);
            setCustomerAddressFields(customerDto);
            setCustomerCityFields(customerDto);
            setCustomerCountryFields(customerDto);
            customerDtos.add(customerDto);
        }

        return customerDtos;
    }

    @Override
    public int addCustomer(CustomerDto customerDto) throws SQLException {
        boolean doesCountryExist = doesCountryExist(customerDto.getCountry());
        boolean doesCityExist = doesCityExist(customerDto.getCity());

        if (!doesCountryExist){
            customerDto.setCountryId(saveCountry(customerDto));
        }
        else {
            int countryId = countryRepository.getCountryId(customerDto.getCountry());
            customerDto.setCountryId(countryId);
        }

        if (!doesCityExist) {
            customerDto.setCityId(saveCity(customerDto));
        }
        else {
            int cityId = cityRepository.getIdByCityName(customerDto.getCity());
            customerDto.setCityId(cityId);
        }

        customerDto.setAddressId(saveAddress(customerDto));
        Customer customer = MapCustomerDtoToCustomer.Map(customerDto);
        customer.setCreateDate(LocalDateTime.now());
        customer.setCreatedBy(UserStatics.getCurrentUserName());
        customer.setLastUpdate(LocalDateTime.now());
        customer.setLastUpdateBy(UserStatics.getCurrentUserName());
        int customersAdded = customerRepository.insertCustomer(customer);

        return customersAdded;
    }

    @Override
    public int updateCustomer(CustomerDto customerDto) throws SQLException {
        boolean didCountryChange = didCountryChange(customerDto.getCountryId(), customerDto.getCountry());
        boolean didCityChange = didCityChange(customerDto.getCityId(), customerDto.getCity());
        boolean didAddressChange = didAddressChange(customerDto);

        if (didCountryChange){
            boolean doesCountryExist = doesCountryExist(customerDto.getCountry());
            if (!doesCountryExist){
                customerDto.setCountryId(saveCountry(customerDto));
                City city = cityRepository.getCityById(customerDto.getCityId());
                city.setCountryId(customerDto.getCountryId());
                city.setLastUpdateBy(UserStatics.getCurrentUserName());
                city.setLastUpdate(LocalDateTime.now());
                cityRepository.updateCity(city);
            }
            else {
                int countryId = countryRepository.getCountryId(customerDto.getCountry());
                customerDto.setCountryId(countryId);
                City city = cityRepository.getCityById(customerDto.getCityId());
                city.setCountryId(customerDto.getCountryId());
                city.setLastUpdateBy(UserStatics.getCurrentUserName());
                city.setLastUpdate(LocalDateTime.now());
                cityRepository.updateCity(city);
            }
        }

        if (didCityChange){
            boolean doesCityExist = doesCityExist(customerDto.getCity());
            if (!doesCityExist){
                customerDto.setCityId(saveCity(customerDto));
            }
            else {
                int cityId = cityRepository.getIdByCityName(customerDto.getCity());
                customerDto.setCityId(cityId);
            }
        }

        if (didAddressChange){
            Address address = MapCustomerDtoToAddress.Map(customerDto);
            address.setLastUpdateBy(UserStatics.getCurrentUserName());
            address.setLastUpdate(LocalDateTime.now());
            addressRepository.updateAddress(address);
        }

        Customer customer = MapCustomerDtoToCustomer.Map(customerDto);
        customer.setLastUpdateBy(UserStatics.getCurrentUserName());
        customer.setLastUpdate(LocalDateTime.now());
        int customersUpdated = customerRepository.updateCustomer(customer);

        return customersUpdated;
    }

    @Override
    public int deleteCustomer(int customerId) throws SQLException {

        Customer customer = customerRepository.getCustomerById(customerId);
        int deletedCustomer = customerRepository.deleteCustomer(customerId);
        int deletedAddresses = addressRepository.deleteAddress(customer.getAddressId());

        return deletedCustomer;
    }
    private void setCustomerAddressFields(CustomerDto customerDto){
        try {
            Address address = addressRepository.getAddressById(customerDto.getAddressId());
            customerDto.setAddress(address.getAddress());
            customerDto.setAddress2(address.getAddress2());
            customerDto.setCityId(address.getCityId());
            customerDto.setPostalCode(address.getPostalCode());
            customerDto.setPhone(address.getPhone());
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    private void setCustomerCityFields(CustomerDto customerDto) throws SQLException {
        City city = cityRepository.getCityById(customerDto.getCityId());
        customerDto.setCity(city.getCity());
        customerDto.setCountryId(city.getCountryId());
    }

    private void setCustomerCountryFields(CustomerDto customerDto) throws SQLException {
        String countryName = countryRepository.getCountryNameByCountryId(customerDto.getCountryId());
        customerDto.setCountry(countryName);
    }

    private boolean doesCountryExist(String countryName) throws SQLException {
        return countryRepository.doesCountryExist(countryName);
    }

    private boolean doesCityExist(String cityName) throws SQLException {
        return cityRepository.doesCityExist(cityName);
    }

    private int saveCountry(CustomerDto customerDto) throws SQLException {
        Country country = MapCustomerDtoToCountry.Map(customerDto);
        country.setCreateDate(LocalDateTime.now());
        country.setLastUpdate(LocalDateTime.now());
        country.setCreatedBy(UserStatics.getCurrentUserName());
        country.setLastUpdateBy(UserStatics.getCurrentUserName());
        countryRepository.insertCountry(country);

        return countryRepository.getMaxId();
    }

    private int saveCity(CustomerDto customerDto) throws SQLException {
        City city = MapCustomerDtoToCity.Map(customerDto);
        city.setCreateDate(LocalDateTime.now());
        city.setLastUpdate(LocalDateTime.now());
        city.setCreatedBy(UserStatics.getCurrentUserName());
        city.setLastUpdateBy(UserStatics.getCurrentUserName());
        cityRepository.insertCity(city);

        return cityRepository.getMaxId();
    }

    private int saveAddress(CustomerDto customerDto) throws SQLException {
        Address address = MapCustomerDtoToAddress.Map(customerDto);
        address.setCreateDate(LocalDateTime.now());
        address.setCreatedBy(UserStatics.getCurrentUserName());
        address.setLastUpdate(LocalDateTime.now());
        address.setLastUpdateBy(UserStatics.getCurrentUserName());
        addressRepository.insertAddress(address);
        int addressId = addressRepository.getMaxId();

        return addressId;
    }

    private boolean didCountryChange(int countryId, String countryName) throws SQLException {
        Country country = countryRepository.getCountryById(countryId);
        if (country.getCountry().equals(countryName)){
            return false;
        }
        else {
            return true;
        }
    }

    private boolean didCityChange(int cityId, String cityName) throws SQLException {
        City city = cityRepository.getCityById(cityId);
        if (city.getCity().equals(cityName)){
            return false;
        }
        else {
            return true;
        }
    }

    private boolean didAddressChange(CustomerDto customerDto) throws SQLException {
        Address address = addressRepository.getAddressById(customerDto.getAddressId());
        boolean didAddressChange = false;

        if (address.getAddress() != customerDto.getAddress()){
            didAddressChange = true;
        }

        if (address.getAddress2() != customerDto.getAddress2()){
            didAddressChange = true;
        }

        if (address.getPostalCode() != customerDto.getPostalCode()){
            didAddressChange = true;
        }

        if (address.getPhone() != customerDto.getPhone()){
            didAddressChange = true;
        }

        return didAddressChange;
    }
}
