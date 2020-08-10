package Controllers.Customer;

import Contracts.Interfaces.Services.ICustomerService;
import Contracts.Interfaces.Services.ILoginService;
import Contracts.Interfaces.Services.INavigationService;
import Domain.Dtos.AppointmentDto;
import Domain.Dtos.CustomerDto;
import Domain.Services.CustomerService;
import Domain.Services.LoginService;
import Domain.Services.NavigationService;
import com.sun.org.apache.xpath.internal.operations.Bool;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CustomerListController implements Initializable {

    INavigationService navigationService = new NavigationService();
    ILoginService loginService = new LoginService();
    ICustomerService customerService = new CustomerService();

    @FXML
    private TableView<CustomerDto> tblCustomers;

    @FXML
    private Button btnBack;

    @FXML
    private TableColumn<CustomerDto, Integer> tblColumnId;

    @FXML
    private Button btnLogout;

    @FXML
    private TableColumn<CustomerDto, String> tblColumnCustomerName;

    @FXML
    private Button btnDeleteCustomer;

    @FXML
    private Button btnAddCustomer;

    @FXML
    private Button btnEditCustomer;

    @FXML
    private TableColumn<CustomerDto, Boolean> tblColumnActive;

    @FXML
    private TextField txtSearch;

    @FXML
    private Button btnSearch;

    @FXML
    void onActionBtnBack(ActionEvent event) throws IOException {
        navigationService.navigateToMainScreen(event);
    }

    @FXML
    void onActionBtnLogout(ActionEvent event) throws IOException {
        loginService.logout(event);
    }

    @FXML
    void onActionBtnAdd(ActionEvent event) throws IOException {
        navigationService.navigateToAddCustomerScreen(event);
    }

    @FXML
    void onActionBtnEdit(ActionEvent event) throws IOException {
        CustomerDto customerDto = tblCustomers.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Views/Customer/EditCustomer.fxml"));
        loader.load();

        EditCustomerController controller = loader.getController();
        controller.sendCustomer(customerDto);
        navigationService.navigateToEditCustomerScreen(event, loader);
    }

    @FXML
    void onActionBtnDelete(ActionEvent event) throws SQLException {
        CustomerDto customerDto = tblCustomers.getSelectionModel().getSelectedItem();
        int customersDeleted = customerService.deleteCustomer(customerDto.getCustomerId());

        if (customersDeleted == 0){
            new Alert(Alert.AlertType.ERROR, "There was a problem deleting your customer. Please try again.").show();
        }

        tblCustomers.setItems(customerService.getAllCustomers());
    }

    @FXML
    void onActionBtnSearch(ActionEvent event){
        String searchText = txtSearch.getText();
        if (searchText.trim().isEmpty()){
            new Alert(Alert.AlertType.ERROR, "Search text cannot be empty").show();
            return;
        }
        try {
            ObservableList<CustomerDto> filteredCustomers = customerService.searchCustomersByCustomerName(searchText);
            tblCustomers.setItems(filteredCustomers);
        }
        catch (Exception e){
            System.out.println(e);
            new Alert(Alert.AlertType.ERROR, "There was a problem searching customers. Please try again.").show();
            return;
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            initializeCustomerTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void initializeCustomerTable() throws SQLException {
        tblCustomers.setItems(customerService.getAllCustomers());
        tblColumnCustomerName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        tblColumnId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        tblColumnActive.setCellValueFactory(new PropertyValueFactory<>("active"));
        tblColumnActive.setCellFactory(tc -> new TableCell<CustomerDto, Boolean>(){
            @Override
            protected void updateItem(Boolean item, boolean empty) {
                if (item == null){
                    setText(null);
                }
                else if (item.booleanValue() == true){
                    setText("Yes");
                }
                else {
                    setText("No");
                }
            }
        });
    }
}
