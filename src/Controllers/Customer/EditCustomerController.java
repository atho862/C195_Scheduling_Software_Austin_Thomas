package Controllers.Customer;

import Contracts.Interfaces.Services.ICustomerService;
import Contracts.Interfaces.Services.ILoginService;
import Contracts.Interfaces.Services.INavigationService;
import Contracts.Statics.CustomerStatics;
import Domain.Dtos.CustomerDto;
import Domain.Helpers.CustomerHelper;
import Domain.Services.CustomerService;
import Domain.Services.LoginService;
import Domain.Services.NavigationService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class EditCustomerController implements Initializable {

    INavigationService navigationService = new NavigationService();
    ILoginService loginService = new LoginService();
    ICustomerService customerService = new CustomerService();
    int customerId;
    int addressId;
    int cityId;
    int countryId;

    @FXML
    private TextField txtCity;

    @FXML
    private TextField txtPhone;

    @FXML
    private Button btnBack;

    @FXML
    private ChoiceBox<String> choiceBoxActive;

    @FXML
    private Button btnLogout;

    @FXML
    private Label lblAddress;

    @FXML
    private TextField txtAddress2;

    @FXML
    private Label lblCity;

    @FXML
    private TextField txtCustomerName;

    @FXML
    private Label lblCustomerName;

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnSave;

    @FXML
    private TextField txtCountry;

    @FXML
    private TextField txtAddress;

    @FXML
    private Label lblAddress2;

    @FXML
    private Label lblPostalCode;

    @FXML
    private Label lblActive;

    @FXML
    private Label lblCountry;

    @FXML
    private Label lblPhone;

    @FXML
    private TextField txtPostalCode;

    @FXML
    void onActionBtnBack(ActionEvent event) throws IOException {
        navigationService.navigateToCustomerListScreen(event);
    }

    @FXML
    void onActionBtnLogout(ActionEvent event) throws IOException {
        loginService.logout(event);
    }

    @FXML
    void onActionBtnSave(ActionEvent event) throws SQLException, IOException {
        String customerName = txtCustomerName.getText();
        String address = txtAddress.getText();
        String address2 = txtAddress2.getText();
        String postalCode = txtPostalCode.getText();
        String phone = txtPhone.getText();
        String country = txtCountry.getText();
        String city = txtCity.getText();
        boolean isActive;
        if (choiceBoxActive.getValue() == CustomerStatics.getIsActiveYes()) {
            isActive = true;
        }
        else {
            isActive = false;
        }

        CustomerDto customerDto = new CustomerDto(customerId, customerName, isActive, addressId, address, address2, postalCode,
                phone, cityId, city, countryId, country);
        int customersUpdated = customerService.updateCustomer(customerDto);
        if (customersUpdated == 0){
            new Alert(Alert.AlertType.ERROR, "There was a problem saving your customer. Please try again.");
        }

        navigationService.navigateToCustomerListScreen(event);
    }

    @FXML
    void onActionBtnCancel(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.WARNING, "Are you sure you want to leave this page? Any unsaved changes will be lost!",
                ButtonType.YES, ButtonType.NO);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.NO){
            return;
        }
        navigationService.navigateToCustomerListScreen(event);
    }

    public void sendCustomer(CustomerDto customerDto){
        txtCustomerName.setText(customerDto.getCustomerName());
        txtAddress.setText(customerDto.getAddress());
        txtAddress2.setText(customerDto.getAddress2());
        txtPhone.setText(customerDto.getPhone());
        txtPostalCode.setText(customerDto.getPostalCode());
        txtCity.setText(customerDto.getCity());
        txtCountry.setText(customerDto.getCountry());
        addressId = customerDto.getAddressId();
        customerId = customerDto.getCustomerId();
        cityId = customerDto.getCityId();
        countryId = customerDto.getCountryId();
        if (customerDto.isActive()) {
            choiceBoxActive.setValue("Yes");
        }
        else {
            choiceBoxActive.setValue("No");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        choiceBoxActive.setItems(CustomerHelper.getIsActiveItems());
    }

}
