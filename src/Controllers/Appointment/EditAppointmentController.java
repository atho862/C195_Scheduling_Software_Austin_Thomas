package Controllers.Appointment;

import Contracts.Interfaces.Services.ICustomerService;
import Contracts.Statics.AppointmentStatics;
import Domain.Daos.AppointmentDao;
import Domain.Services.CustomerService;
import Infrastructure.Models.Appointment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class EditAppointmentController implements Initializable {

    Stage stage;
    Parent root;
    ObservableList<String> customerNames;
    ObservableList<String> types;
    ObservableList<String> hours;
    ObservableList<String> minutes;
    ICustomerService customerService = new CustomerService();

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
    void onActionBtnLogout(ActionEvent event) {

    }

    @FXML
    void onActionBtnBack(ActionEvent event) {

    }

    @FXML
    void onActionBtnSave(ActionEvent event) {

    }

    @FXML
    void onActionBtnCancel(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void sendAppointment(AppointmentDao appointmentDao) throws SQLException {
        txtTitle.setText(appointmentDao.getTitle());
        txtDescription.setText(appointmentDao.getDescription());
        txtContact.setText(appointmentDao.getContact());
        txtLocation.setText(appointmentDao.getLocation());
        txtUrl.setText(appointmentDao.getUrl());
        setCustomerNames();
        setTypes();
        hours = AppointmentStatics.setHours();
        minutes = AppointmentStatics.setMinutes();
        drpdwnCustomer.setItems(customerNames);
        drpdwnType.setItems(types);
        drpdwnType.setValue(appointmentDao.getType());
        drpdwnCustomer.setValue(appointmentDao.getCustomerName());
        dateEnd.setValue(appointmentDao.getEnd().toLocalDate());
        dateStart.setValue(appointmentDao.getStart().toLocalDate());
        drpdwnStartHours.setItems(hours);
        drpdwnStartMinutes.setItems(minutes);
        drpdwnEndHours.setItems(hours);
        drpdwnEndMinutes.setItems(minutes);
        drpdwnStartHours.setValue(String.valueOf(appointmentDao.getStart().getHour()));
        drpdwnStartMinutes.setValue(String.valueOf(appointmentDao.getStart().getMinute()));
        drpdwnEndHours.setValue(String.valueOf(appointmentDao.getEnd().getHour()));
        drpdwnEndMinutes.setValue(String.valueOf(appointmentDao.getEnd().getMinute()));
    }

    private void setCustomerNames() throws SQLException {
        customerNames = FXCollections.observableArrayList();
        customerNames.addAll(customerService.getCustomerNames());
    }

    private void setTypes(){
        types = FXCollections.observableArrayList();
        types.addAll(AppointmentStatics.initialConsultation, AppointmentStatics.productImplementation,
                AppointmentStatics.salesCall, AppointmentStatics.supportCall);
    }
}
