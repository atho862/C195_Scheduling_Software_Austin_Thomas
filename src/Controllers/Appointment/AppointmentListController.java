package Controllers.Appointment;

import Contracts.Interfaces.Services.IAppointmentService;
import Contracts.Interfaces.Services.ILoginService;
import Contracts.Statics.AppointmentStatics;
import Contracts.Statics.CustomerStatics;
import Contracts.Statics.UserStatics;
import Domain.Dtos.AppointmentDto;
import Domain.Helpers.AppointmentHelper;
import Domain.Services.NavigationService;
import Domain.Services.AppointmentService;
import Domain.Services.LoginService;
import Infrastructure.Models.Appointment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class AppointmentListController implements Initializable {

    ILoginService loginService = new LoginService();
    IAppointmentService appointmentService = new AppointmentService();
    NavigationService navigationService = new NavigationService();
    Stage stage;
    Parent root;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm");

    @FXML
    private TableColumn<AppointmentDto, String> tblColumnType;

    @FXML
    private TableColumn<AppointmentDto, Integer> tblColumnId;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnLogout;

    @FXML
    private TableColumn<AppointmentDto, String> tblColumnContact;

    @FXML
    private ToggleGroup tglGroupAppointmentFilter;

    @FXML
    private TableColumn<AppointmentDto, String> tblColumnTitle;

    @FXML
    private TableView<AppointmentDto> tblViewAppointments;

    @FXML
    private DatePicker datePicker;

    @FXML
    private TableColumn<AppointmentDto, LocalDateTime> tblColumnEnd;

    @FXML
    private TableColumn<AppointmentDto, String> tblColumnCustomer;

    @FXML
    private Button btnDeleteAppointment;

    @FXML
    private Button btnAddAppointment;

    @FXML
    private TableColumn<AppointmentDto, LocalDateTime> tblColumnStart;

    @FXML
    private RadioButton btnRadioViewAllAppointments;

    @FXML
    private Button btnEditAppointment;

    @FXML
    private RadioButton btnRadioViewByWeek;

    @FXML
    private RadioButton btnRadioViewByMonth;

    @FXML
    void onActionBtnLogout(ActionEvent event) throws IOException {
        loginService.logout(event);
    }


    @FXML
    void onActionViewAllAppointments(ActionEvent event) throws SQLException {
        tblViewAppointments.setItems(appointmentService.getAppointmentsForUser(UserStatics.getCurrentUserId()));
    }


    @FXML
    void onActionBtnRadioViewByWeek(ActionEvent event) throws SQLException {
        LocalDate date = datePicker.getValue();
        ObservableList<AppointmentDto> appointmentsForWeek = FXCollections.observableArrayList();
        ObservableList<AppointmentDto> appointments = appointmentService.getAppointmentsForUser(UserStatics.getCurrentUserId());
        for (AppointmentDto appointment : appointments
             ) {
            if (AppointmentHelper.getDatesForThisWeek(date).contains(appointment.getStart().getDayOfYear())){
                appointmentsForWeek.add(appointment);
            }
        }

        tblViewAppointments.setItems(appointmentsForWeek);
    }


    @FXML
    void onActionBtnRadioViewByMonth(ActionEvent event) throws SQLException {
        LocalDate date = datePicker.getValue();
        ObservableList<AppointmentDto> appointments = appointmentService.getAppointmentsForUser(UserStatics.getCurrentUserId());
        ObservableList<AppointmentDto> appointmentsForMonth = FXCollections.observableArrayList();
        for (AppointmentDto appointment : appointments
             ) {
            if (appointment.getStart().getMonth() == date.getMonth()){
                appointmentsForMonth.add(appointment);
            }
        }

        tblViewAppointments.setItems(appointmentsForMonth);
    }


    @FXML
    void onActionDatePicker(ActionEvent event) throws SQLException {
        if (btnRadioViewByMonth.isSelected()){
            onActionBtnRadioViewByMonth(event);
        }
        if (btnRadioViewByWeek.isSelected()){
            onActionBtnRadioViewByWeek(event);
        }
    }

    @FXML
    void onActionBtnAddAppointment(ActionEvent event) throws IOException {
        navigationService.navigateToAddAppointmentScreen(event);
    }

    @FXML
    void onActionBtnEditAppointment(ActionEvent event) throws IOException, SQLException {

        AppointmentDto appointmentDto = tblViewAppointments.getSelectionModel().getSelectedItem();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Views/Appointment/EditAppointment.fxml"));
        loader.load();

        try {
            EditAppointmentController controller = loader.getController();
            controller.sendAppointment(appointmentDto);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }


        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        root = loader.getRoot();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void onActionBtnDeleteAppointment(ActionEvent event) throws SQLException {
        AppointmentDto appointmentDto = tblViewAppointments.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.WARNING, String.format("Are you sure you want to delete '%s'", appointmentDto.getTitle()), ButtonType.YES, ButtonType.NO);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.NO){
            return;
        }

        int deletedRows = appointmentService.deleteAppointment(appointmentDto.getAppointmentId());
        if (deletedRows == 0){
            new Alert(Alert.AlertType.ERROR, "There was an error deleting the appointment. Please try again.");
            return;
        }

        tblViewAppointments.setItems(appointmentService.getAppointmentsForUser(UserStatics.getCurrentUserId()));
    }

    @FXML
    void onActionBtnBack(ActionEvent event) throws IOException {
        navigationService.navigateToMainScreen(event);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            initializeAppointmentsTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void initializeAppointmentsTable() throws SQLException {
        tblViewAppointments.setItems(appointmentService.getAppointmentsForUser(UserStatics.getCurrentUserId()));
        //Use lambdas for properties on the table view
        tblColumnTitle.setCellValueFactory(cellData -> cellData.getValue().getTitleProperty());
        tblColumnCustomer.setCellValueFactory(cellData -> cellData.getValue().getCustomerNameProperty());
        tblColumnType.setCellValueFactory(cellData -> cellData.getValue().getTypeProperty());
        tblColumnContact.setCellValueFactory(cellData -> cellData.getValue().getContactProperty());
        tblColumnStart.setCellValueFactory(new PropertyValueFactory<>("start"));
        tblColumnEnd.setCellValueFactory(new PropertyValueFactory<>("end"));
        tblColumnId.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        btnRadioViewAllAppointments.setSelected(true);
        datePicker.setValue(LocalDate.now());

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
