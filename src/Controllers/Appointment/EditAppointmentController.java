package Controllers.Appointment;

import Contracts.Interfaces.Services.IAppointmentService;
import Contracts.Interfaces.Services.ICustomerService;
import Contracts.Interfaces.Services.ILoginService;
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

public class EditAppointmentController implements Initializable {

    ObservableList<String> customerNames;
    ObservableList<String> types;
    ObservableList<String> hours;
    ObservableList<String> minutes;
    ICustomerService customerService = new CustomerService();
    ILoginService loginService = new LoginService();
    IAppointmentService appointmentService = new AppointmentService();
    NavigationService navigationService = new NavigationService();
    int appointmentId;

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
        LocalDateTime start = AppointmentHelper.getLocalDateTimeForAppointment(dateStart.getValue(), drpdwnStartHours.getValue(), drpdwnEndHours.getValue());
        LocalDateTime end = AppointmentHelper.getLocalDateTimeForAppointment(dateEnd.getValue(), drpdwnEndHours.getValue(), drpdwnEndMinutes.getValue());

        if (!AppointmentHelper.isDuringBusinessHours(start.getHour())) {
            new Alert(Alert.AlertType.ERROR, "Your appointment is currently scheduled to start outside of business hours. Please select a time between 9am and 5pm").show();
            return;
        }

        if (!AppointmentHelper.isDuringBusinessHours(end.getHour())) {
            new Alert(Alert.AlertType.ERROR, "Your appointment is currently scheduled to end outside of business hours. Please select a time between 9am and 5pm.").show();
            return;
        }

        String title = txtTitle.getText();
        String description = txtDescription.getText();
        String type = drpdwnType.getValue();
        String customerName = drpdwnCustomer.getValue();
        String contact = txtContact.getText();
        String location = txtLocation.getText();
        String url = txtUrl.getText();


        AppointmentDto appointmentDto = new AppointmentDto(appointmentId, customerName, title, description, location,
                contact, type, url, start, end);

        int updatedAppointments = appointmentService.updateAppointment(appointmentDto);
        if (updatedAppointments != 1) {
            new Alert(Alert.AlertType.ERROR, "An error occurred while updating this appointment. Please try again.").show();
            return;
        }

        navigationService.navigateToAppointmentListScreen(event);
    }

    @FXML
    void onActionBtnCancel(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.WARNING, "Are you sure you want to leave this screen? Any unsaved data will be lost",
                ButtonType.YES, ButtonType.NO);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.NO) {
            return;
        }

        navigationService.navigateToAppointmentListScreen(event);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            customerNames = customerService.getCustomerNames();
            drpdwnCustomer.setItems(customerNames);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        types = AppointmentHelper.setTypes();
        drpdwnType.setItems(types);
        hours = AppointmentHelper.setHours();
        minutes = AppointmentHelper.setMinutes();
        drpdwnStartHours.setItems(hours);
        drpdwnStartMinutes.setItems(minutes);
        drpdwnEndHours.setItems(hours);
        drpdwnEndMinutes.setItems(minutes);
    }

    public void sendAppointment(AppointmentDto appointmentDto) throws SQLException {
        appointmentId = appointmentDto.getAppointmentId();
        txtTitle.setText(appointmentDto.getTitle());
        txtDescription.setText(appointmentDto.getDescription());
        txtContact.setText(appointmentDto.getContact());
        txtLocation.setText(appointmentDto.getLocation());
        txtUrl.setText(appointmentDto.getUrl());
        drpdwnCustomer.setItems(customerNames);
        drpdwnType.setItems(types);
        drpdwnType.setValue(appointmentDto.getType());
        drpdwnCustomer.setValue(appointmentDto.getCustomerName());
        dateEnd.setValue(appointmentDto.getEnd().toLocalDate());
        dateStart.setValue(appointmentDto.getStart().toLocalDate());
        drpdwnStartHours.setValue(String.valueOf(appointmentDto.getStart().getHour()));
        if (appointmentDto.getEnd().getMinute() == 0) {
            drpdwnStartMinutes.setValue("00");
        } else {
            drpdwnStartMinutes.setValue(String.valueOf(appointmentDto.getStart().getMinute()));
        }
        drpdwnEndHours.setValue(String.valueOf(appointmentDto.getEnd().getHour()));
        if (appointmentDto.getEnd().getMinute() == 0) {
            drpdwnEndMinutes.setValue("00");
        } else {
            drpdwnEndMinutes.setValue(String.valueOf(appointmentDto.getEnd().getMinute()));
        }
    }
}