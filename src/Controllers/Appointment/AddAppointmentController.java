package Controllers.Appointment;

import Contracts.Interfaces.Services.IAppointmentService;
import Contracts.Interfaces.Services.ICustomerService;
import Contracts.Interfaces.Services.ILoginService;
import Contracts.Statics.UserStatics;
import Domain.Dtos.AppointmentDto;
import Domain.Helpers.AppointmentHelper;
import Domain.Services.NavigationService;
import Domain.Services.AppointmentService;
import Domain.Services.CustomerService;
import Domain.Services.LoginService;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class AddAppointmentController implements Initializable {

    Stage stage;
    Parent root;
    NavigationService navigationService = new NavigationService();
    ICustomerService customerService = new CustomerService();
    ILoginService loginService = new LoginService();
    IAppointmentService appointmentService = new AppointmentService();
    ObservableList<String> hours;
    ObservableList<String> minutes;

    @FXML
    private Button btnBack;

    @FXML
    private TextField txtContact;

    @FXML
    private Button btnLogout;

    @FXML
    private TextArea txtDescription;

    @FXML
    private Label lblType;

    @FXML
    private ChoiceBox<String> drpdwnCustomer;

    @FXML
    private Label lblCustomer;

    @FXML
    private Label lblEnd;

    @FXML
    private Label lblLocation;

    @FXML
    private DatePicker dateEnd;

    @FXML
    private Label lblDescription;

    @FXML
    private ChoiceBox<String> drpdwnType;

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnSave;

    @FXML
    private DatePicker dateStart;

    @FXML
    private Label lblContact;

    @FXML
    private Label lblStart;

    @FXML
    private Label lblUrl;

    @FXML
    private Label lblTitle;

    @FXML
    private TextField txtUrl;

    @FXML
    private TextField txtTitle;

    @FXML
    private TextField txtLocation;

    @FXML
    private ChoiceBox<String> drpdwnStartHours;

    @FXML
    private ChoiceBox<String> drpdwnStartMinutes;

    @FXML
    private ChoiceBox<String> drpdwnEndHours;

    @FXML
    private ChoiceBox<String> drpdwnEndMinutes;

    @FXML
    void onActionBtnLogout(ActionEvent event) throws IOException {
        loginService.logout(event);
    }

    @FXML
    void onActionBtnBack(ActionEvent event) throws IOException {
        navigationService.navigateToAppointmentListScreen(event);
    }

    @FXML
    void onActionBtnSave(ActionEvent event) throws SQLException, IOException {
        LocalDateTime start = AppointmentHelper.getLocalDateTimeForAppointment(dateStart.getValue(), drpdwnStartHours.getValue(), drpdwnStartMinutes.getValue());
        LocalDateTime end = AppointmentHelper.getLocalDateTimeForAppointment(dateEnd.getValue(), drpdwnEndHours.getValue(), drpdwnEndMinutes.getValue());

        if (!AppointmentHelper.isDuringBusinessHours(start)){
            new Alert(Alert.AlertType.ERROR, "Your appointment is currently scheduled to start outside of business hours. Please schedule a time between 9am and 5pm.").show();
            return;
        }
        if (!AppointmentHelper.isDuringBusinessHours(end)){
            new Alert(Alert.AlertType.ERROR, "Your appointment is currently scheduled to end outside business hours. Please schedule a time between 9am and 5pm.").show();
            return;
        }
        if (AppointmentHelper.checkForOverlappingAppointments(start, end, appointmentService.getAppointmentsForUser(UserStatics.getCurrentUserId()))){
            new Alert(Alert.AlertType.ERROR, "Your appointment conflicts with another scheduled appointment. Please choose another time for this appointment.").show();
            return;
        }

        String title = txtTitle.getText();
        String description = txtDescription.getText();
        String type = drpdwnType.getValue();
        String customerName = drpdwnCustomer.getValue();
        String location = txtLocation.getText();
        String contact = txtContact.getText();
        String url = txtUrl.getText();

        AppointmentDto appointmentDto = new AppointmentDto(0, customerName, title, description, location,
                contact, type, url, start, end);

        int appointmentsAdded = appointmentService.saveAppointment(appointmentDto);
        if (appointmentsAdded == 1){
            navigationService.navigateToAppointmentListScreen(event);
        }
        else {
            new Alert(Alert.AlertType.ERROR, "Unable to save appointment. Please try again.").show();
        }

    }

    @FXML
    void onActionBtnCancel(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.WARNING, "Are you sure you want to leave this page? Any unsaved data will be lost!",
                ButtonType.YES, ButtonType.NO);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.NO){
            return;
        }
        navigationService.navigateToAppointmentListScreen(event);
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            drpdwnCustomer.setItems(customerService.getCustomerNames());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        drpdwnType.setItems(AppointmentHelper.setTypes());
        hours = AppointmentHelper.setHours();
        minutes = AppointmentHelper.setMinutes();
        drpdwnStartHours.setItems(hours);
        drpdwnStartMinutes.setItems(minutes);
        drpdwnStartMinutes.setValue("00");
        drpdwnEndHours.setItems(hours);
        drpdwnEndMinutes.setItems(minutes);
        drpdwnEndMinutes.setValue("00");
    }
}
