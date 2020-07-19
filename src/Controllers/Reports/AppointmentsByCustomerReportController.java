package Controllers.Reports;

import Contracts.Interfaces.Repositories.ICustomerRepository;
import Contracts.Interfaces.Services.IAppointmentService;
import Contracts.Interfaces.Services.ILoginService;
import Contracts.Interfaces.Services.INavigationService;
import Domain.Dtos.AppointmentDto;
import Domain.Services.AppointmentService;
import Domain.Services.LoginService;
import Domain.Services.NavigationService;
import Infrastructure.Models.Appointment;
import Infrastructure.Models.Customer;
import Infrastructure.Repositories.CustomerRepository;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class AppointmentsByCustomerReportController implements Initializable {

    ILoginService loginService = new LoginService();
    INavigationService navigationService = new NavigationService();
    IAppointmentService appointmentService = new AppointmentService();
    ICustomerRepository customerRepository = new CustomerRepository();


    @FXML
    private TableColumn<AppointmentDto, String> tblColumnType;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnLogout;

    @FXML
    private ComboBox<String> choiceBoxCustomer;

    @FXML
    private TableColumn<AppointmentDto, String> tblColumnTitle;

    @FXML
    private TableColumn<AppointmentDto, LocalDateTime> tblColumnStart;

    @FXML
    private Label lblCustomer;

    @FXML
    private TableView<AppointmentDto> tblAppointments;

    @FXML
    private TableColumn<AppointmentDto, LocalDateTime> tblColumnEnd;

    @FXML
    void onActionBtnBack(ActionEvent event) throws IOException  {
        navigationService.navigateToReportsScreen(event);
    }

    @FXML
    void onActionBtnLogout(ActionEvent event) throws IOException {
        loginService.logout(event);
    }

    @FXML
    void onAction(ActionEvent event) throws SQLException {
        int customerId = customerRepository.getCustomerIdByCustomerName(choiceBoxCustomer.getValue());
        tblAppointments.setItems(appointmentService.getAppointmentsForCustomer(customerId));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            initializeAppointmentsTable();
            ObservableList<Customer> customers = customerRepository.getAllCustomers();
            tblAppointments.setItems(appointmentService.getAppointmentsForCustomer(customers.get(1).getCustomerId()));
            choiceBoxCustomer.setItems(customerRepository.getCustomerNames());
            choiceBoxCustomer.setValue(customers.get(1).getCustomerName());
        }
        catch (SQLException e){
            System.out.println(e);
        }
    }

    private void initializeAppointmentsTable(){
        //Lambdas to quickly initialize the value bindings of the table
        tblColumnTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        tblColumnType.setCellValueFactory(new PropertyValueFactory<>("type"));
        tblColumnStart.setCellValueFactory(new PropertyValueFactory<>("start"));
        tblColumnEnd.setCellValueFactory(new PropertyValueFactory<>("end"));
    }
}
