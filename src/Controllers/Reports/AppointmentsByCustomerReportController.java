package Controllers.Reports;

import Contracts.Interfaces.Repositories.ICustomerRepository;
import Contracts.Interfaces.Services.IAppointmentService;
import Contracts.Interfaces.Services.ILoginService;
import Contracts.Interfaces.Services.INavigationService;
import Contracts.Interfaces.Services.IReportsService;
import Domain.Dtos.AppointmentDto;
import Domain.Services.AppointmentService;
import Domain.Services.LoginService;
import Domain.Services.NavigationService;
import Domain.Services.ReportsService;
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
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class AppointmentsByCustomerReportController implements Initializable {

    ILoginService loginService = new LoginService();
    INavigationService navigationService = new NavigationService();
    IAppointmentService appointmentService = new AppointmentService();
    ICustomerRepository customerRepository = new CustomerRepository();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm");
    IReportsService reportsService = new ReportsService();

    @FXML
    private TableColumn<AppointmentDto, String> tblColumnType;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnLogout;

    @FXML
    private Button btnExportToExcel;

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

    @FXML
    void onActionBtnExportToExcel(ActionEvent event){
        if (tblAppointments.getItems().size() < 1){
            new Alert(Alert.AlertType.WARNING, "Unable to generate a report for a customer with 0 appointments. Please select a customer that has appointments").show();
            return;
        }
        reportsService.exportAppointmentsByCustomerReportToExcel(choiceBoxCustomer.getValue(), tblAppointments.getItems());
        new Alert(Alert.AlertType.CONFIRMATION, "Report successfully exported to Excel!").show();
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
        tblColumnTitle.setCellValueFactory(cellData -> cellData.getValue().getTitleProperty());
        tblColumnType.setCellValueFactory(cellData -> cellData.getValue().getTypeProperty());
        tblColumnStart.setCellValueFactory(new PropertyValueFactory<>("start"));
        tblColumnEnd.setCellValueFactory(new PropertyValueFactory<>("end"));
        tblColumnStart.setCellFactory(tc -> new TableCell<AppointmentDto, LocalDateTime>(){
            @Override
            protected void updateItem(LocalDateTime item, boolean empty) {
                if (item == null){
                    setText(null);
                }
                else {
                    setText(item.format(formatter));
                }
            }
        });
        tblColumnEnd.setCellFactory(tc -> new TableCell<AppointmentDto, LocalDateTime>(){
            @Override
            protected void updateItem(LocalDateTime item, boolean empty) {
                if (item == null){
                    setText(null);
                }
                else {
                    setText(item.format(formatter));
                }
            }
        });
    }
}