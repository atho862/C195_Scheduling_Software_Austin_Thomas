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
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class ScheduleByConsultantController implements Initializable {
    IAppointmentService appointmentService = new AppointmentService();
    INavigationService navigationService = new NavigationService();
    ILoginService loginService = new LoginService();
    IUserRepository userRepository = new UserRepository();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm");

    @FXML
    private TableColumn<AppointmentDto, String> tblColumnType;

    @FXML
    private Button btnBack;

    @FXML
    private ComboBox<String> comboBoxConsultant;

    @FXML
    private Button btnLogout;

    @FXML
    private Label lblConsultant;

    @FXML
    private TableColumn<AppointmentDto, String> tblColumnTitle;

    @FXML
    private TableColumn<AppointmentDto, LocalDateTime> tblColumnStart;

    @FXML
    private TableView<AppointmentDto> tblAppointments;

    @FXML
    private TableColumn<AppointmentDto, LocalDateTime> tblColumnEnd;

    @FXML
    private TableColumn<AppointmentDto, String> tblColumnCustomer;

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
        tblColumnCustomer.setCellValueFactory(cellData -> cellData.getValue().getCustomerNameProperty());
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
