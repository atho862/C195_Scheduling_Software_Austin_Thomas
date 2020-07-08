package Controllers.Appointment;

import Contracts.Interfaces.Repositories.ICustomerRepository;
import Contracts.Interfaces.Services.IAppointmentService;
import Contracts.Interfaces.Services.ICustomerService;
import Contracts.Interfaces.Services.ILoginService;
import Contracts.Statics.AppointmentStatics;
import Domain.Daos.AppointmentDao;
import Domain.Services.AppointmentService;
import Domain.Services.CustomerService;
import Domain.Services.LoginService;
import Infrastructure.Models.Appointment;
import Infrastructure.Repositories.CustomerRepository;
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
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.ResourceBundle;

public class AddAppointmentController implements Initializable {

    Stage stage;
    Parent root;
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
        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("/Views/Appointment/AppointmentList.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void onActionBtnSave(ActionEvent event) throws SQLException, IOException {
        String title = txtTitle.getText();
        String description = txtDescription.getText();
        String type = drpdwnType.getValue();
        String customerName = drpdwnCustomer.getValue();
        String location = txtLocation.getText();
        String contact = txtContact.getText();
        String url = txtUrl.getText();
        LocalDateTime start = getStartDateForAppointment();
        LocalDateTime end = getEndDateForAppointment();

        AppointmentDao appointmentDao = new AppointmentDao(0, customerName, title, description, location,
                contact, type, url, start, end);

        int appointmentsAdded = appointmentService.saveAppointment(appointmentDao);
        if (appointmentsAdded == 1){
            stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("/Views/Appointment/AppointmentList.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
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
        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("/Views/Appointment/AppointmentList.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            drpdwnCustomer.setItems(customerService.getCustomerNames());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        drpdwnType.setItems(getAppointmentTypes());
        hours = AppointmentStatics.setHours();
        minutes = AppointmentStatics.setMinutes();
        drpdwnStartHours.setItems(hours);
        drpdwnStartMinutes.setItems(minutes);
        drpdwnEndHours.setItems(hours);
        drpdwnEndMinutes.setItems(minutes);
    }

    private ObservableList<String> getAppointmentTypes(){
        ObservableList<String> appointmentTypes = FXCollections.observableArrayList();
        appointmentTypes.addAll(AppointmentStatics.initialConsultation, AppointmentStatics.supportCall, AppointmentStatics.salesCall,
                AppointmentStatics.productImplementation);

        return appointmentTypes;
    }

    private LocalDateTime getStartDateForAppointment(){
        LocalDate localDate = dateStart.getValue();
        String hour = drpdwnStartHours.getValue();
        String minute = drpdwnStartMinutes.getValue();

        LocalDateTime localDateTime = LocalDateTime.of(localDate.getYear(), localDate.getMonth(), localDate.getDayOfMonth(),
                Integer.valueOf(hour), Integer.valueOf(minute));

        return localDateTime;
    }

    private LocalDateTime getEndDateForAppointment(){
        LocalDate localDate = dateEnd.getValue();
        String hour = drpdwnEndHours.getValue();
        String minute = drpdwnEndMinutes.getValue();

        LocalDateTime localDateTime = LocalDateTime.of(localDate.getYear(), localDate.getMonth(), localDate.getDayOfMonth(),
                Integer.valueOf(hour), Integer.valueOf(minute));

        return localDateTime;
    }
}
