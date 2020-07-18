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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddCustomerController implements Initializable {

    INavigationService navigationService = new NavigationService();
    ICustomerService customerService = new CustomerService();
    ILoginService loginService = new LoginService();

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
        String city = txtCity.getText();
        String country = txtCountry.getText();
        boolean isActive;
        if (choiceBoxActive.getValue() == CustomerStatics.getIsActiveYes()){
            isActive = true;
        }
        else {
            isActive = false;
        }

        CustomerDto customerDto = new CustomerDto(0, customerName, isActive, 0, address, address2, postalCode,
                phone, 0, city, 0, country);

        int customersAdded = customerService.addCustomer(customerDto);
        if (customersAdded == 0){
            new Alert(Alert.AlertType.ERROR, "There was a problem saving your customer. Please try again.").show();
            return;
        }

        navigationService.navigateToCustomerListScreen(event);
    }

    @FXML
    void onActionBtnCancel(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.WARNING, "Are you sure you want to cancel? Any unsaved data will be lost!",
                ButtonType.YES, ButtonType.NO);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.NO){
            return;
        }

        navigationService.navigateToCustomerListScreen(event);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        choiceBoxActive.setItems(CustomerHelper.getIsActiveItems());
        choiceBoxActive.setValue(CustomerStatics.getIsActiveYes());
    }
}
