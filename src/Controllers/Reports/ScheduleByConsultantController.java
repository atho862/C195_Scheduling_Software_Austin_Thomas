package Controllers.Reports;

import Contracts.Interfaces.Repositories.IUserRepository;
import Contracts.Interfaces.Services.IAppointmentService;
import Contracts.Interfaces.Services.ILoginService;
import Contracts.Interfaces.Services.INavigationService;
import Contracts.Statics.UserStatics;
import Domain.Dtos.AppointmentDto;
import Domain.Services.AppointmentService;
import Domain.Services.LoginService;
import Domain.Services.NavigationService;
import Infrastructure.Repositories.UserRepository;
import javafx.beans.value.ObservableValue;
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

public class ScheduleByConsultantController implements Initializable {
    IAppointmentService appointmentService = new AppointmentService();
    INavigationService navigationService = new NavigationService();
    ILoginService loginService = new LoginService();
    IUserRepository userRepository = new UserRepository();

    @FXML
    private TableColumn<String, AppointmentDto> tblColumnType;

    @FXML
    private Button btnBack;

    @FXML
    private ComboBox<String> comboBoxConsultant;

    @FXML
    private Button btnLogout;

    @FXML
    private Label lblConsultant;

    @FXML
    private TableColumn<String, AppointmentDto> tblColumnTitle;

    @FXML
    private TableColumn<LocalDateTime, AppointmentDto> tblColumnStart;

    @FXML
    private TableView<AppointmentDto> tblAppointments;

    @FXML
    private TableColumn<LocalDateTime, AppointmentDto> tblColumnEnd;

    @FXML
    private TableColumn<String, AppointmentDto> tblColumnCustomer;

    @FXML
    void onActionBtnBack(ActionEvent event) throws IOException {
        navigationService.navigateToReportsScreen(event);
    }

    @FXML
    void onActionBtnLogout(ActionEvent event) throws IOException {
        loginService.logout(event);
    }

    @FXML
    void onActionComboBoxConsultant(ActionEvent event) throws SQLException {
        String username = comboBoxConsultant.getValue();
        int userId = userRepository.getUserIdByUsername(username);
        tblAppointments.setItems(appointmentService.getAppointmentsForUser(userId));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            initializeAppointmentsTable();
            tblAppointments.setItems(appointmentService.getAppointmentsForUser(UserStatics.getCurrentUserId()));
            comboBoxConsultant.setItems(userRepository.getAllUsernames());
            comboBoxConsultant.setValue(UserStatics.getCurrentUserName());
        }
        catch (SQLException e){
            System.out.println(e);
        }
    }

    private void initializeAppointmentsTable(){
        //Lambdas to speed up binding of table columns
        tblColumnCustomer.setCellValueFactory(cellData -> (ObservableValue<AppointmentDto>) new PropertyValueFactory<>("customerName"));
        tblColumnTitle.setCellValueFactory(cellData -> (ObservableValue<AppointmentDto>) new PropertyValueFactory<>("title"));
        tblColumnType.setCellValueFactory(cellData -> (ObservableValue<AppointmentDto>) new PropertyValueFactory<>("type"));
        tblColumnStart.setCellValueFactory(cellData -> (ObservableValue<AppointmentDto>) new PropertyValueFactory<>("start"));
        tblColumnEnd.setCellValueFactory(cellData -> (ObservableValue<AppointmentDto>) new PropertyValueFactory<>("end"));
    }
}
