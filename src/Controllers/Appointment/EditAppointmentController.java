package Controllers.Appointment;

import Contracts.Interfaces.Services.IAppointmentService;
import Contracts.Interfaces.Services.ICustomerService;
import Contracts.Interfaces.Services.ILoginService;
import Contracts.Statics.AppointmentStatics;
import Domain.Daos.AppointmentDao;
import Domain.Helpers.AppointmentHelper;
import Domain.Services.AppointmentService;
import Domain.Services.CustomerService;
import Domain.Services.LoginService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class EditAppointmentController implements Initializable {

    Stage stage;
    Parent root;
    ObservableList<String> customerNames;
    ObservableList<String> types;
    ObservableList<String> hours;
    ObservableList<String> minutes;
    ICustomerService customerService = new CustomerService();
    ILoginService loginService = new LoginService();
    IAppointmentService appointmentService = new AppointmentService();
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
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("/Views/Appointment/AppointmentList.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void onActionBtnSave(ActionEvent event) throws SQLException, IOException {
        int appointmentStartHour = Integer.valueOf(drpdwnStartHours.getValue());
        int appointmentEndHour = Integer.valueOf(drpdwnEndHours.getValue());

        if (!AppointmentHelper.isDuringBusinessHours(appointmentStartHour)) {
            new Alert(Alert.AlertType.ERROR, "Your appointment is currently scheduled to start outside of business hours. Please select a time between 9am and 5pm").show();
            return;
        }

        if (!AppointmentHelper.isDuringBusinessHours(appointmentEndHour)) {
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
        LocalDateTime start = AppointmentHelper.getLocalDateTimeForAppointment(dateStart.getValue(), drpdwnStartHours.getValue(), drpdwnEndHours.getValue());
        LocalDateTime end = AppointmentHelper.getLocalDateTimeForAppointment(dateEnd.getValue(), drpdwnEndHours.getValue(), drpdwnEndMinutes.getValue());

        AppointmentDao appointmentDao = new AppointmentDao(appointmentId, customerName, title, description, location,
                contact, type, url, start, end);

        int updatedAppointments = appointmentService.updateAppointment(appointmentDao);
        if (updatedAppointments != 1) {
            new Alert(Alert.AlertType.ERROR, "An error occurred while updating this appointment. Please try again.").show();
            return;
        }

        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("/Views/Appointment/AppointmentList.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void onActionBtnCancel(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.WARNING, "Are you sure you want to leave this screen? Any unsaved data will be lost",
                ButtonType.YES, ButtonType.NO);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.NO) {
            return;
        }

        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("/Views/Appointment/AppointmentList.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
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

    public void sendAppointment(AppointmentDao appointmentDao) throws SQLException {
        appointmentId = appointmentDao.getAppointmentId();
        txtTitle.setText(appointmentDao.getTitle());
        txtDescription.setText(appointmentDao.getDescription());
        txtContact.setText(appointmentDao.getContact());
        txtLocation.setText(appointmentDao.getLocation());
        txtUrl.setText(appointmentDao.getUrl());
        drpdwnCustomer.setItems(customerNames);
        drpdwnType.setItems(types);
        drpdwnType.setValue(appointmentDao.getType());
        drpdwnCustomer.setValue(appointmentDao.getCustomerName());
        dateEnd.setValue(appointmentDao.getEnd().toLocalDate());
        dateStart.setValue(appointmentDao.getStart().toLocalDate());
        drpdwnStartHours.setValue(String.valueOf(appointmentDao.getStart().getHour()));
        if (appointmentDao.getEnd().getMinute() == 0) {
            drpdwnStartMinutes.setValue("00");
        } else {
            drpdwnStartMinutes.setValue(String.valueOf(appointmentDao.getStart().getMinute()));
        }
        drpdwnEndHours.setValue(String.valueOf(appointmentDao.getEnd().getHour()));
        if (appointmentDao.getEnd().getMinute() == 0) {
            drpdwnEndMinutes.setValue("00");
        } else {
            drpdwnEndMinutes.setValue(String.valueOf(appointmentDao.getEnd().getMinute()));
        }
    }

    private Boolean isDuringBusinessHours(int hour) {
        if (hour < 9 || hour > 17) {
            return false;
        } else {
            return true;
        }
    }
}