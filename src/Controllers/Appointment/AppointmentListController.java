package Controllers.Appointment;

import Contracts.Interfaces.Services.IAppointmentService;
import Contracts.Interfaces.Services.ILoginService;
import Contracts.Statics.UserStatics;
import Domain.Daos.AppointmentDao;
import Domain.Services.AppointmentService;
import Domain.Services.LoginService;
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
import java.time.LocalDateTime;
import java.util.Date;
import java.util.ResourceBundle;

public class AppointmentListController implements Initializable {

    ILoginService loginService = new LoginService();
    IAppointmentService appointmentService = new AppointmentService();
    Stage stage;
    Parent root;

    @FXML
    private TableColumn<AppointmentDao, String> tblColumnType;

    @FXML
    private TableColumn<AppointmentDao, Integer> tblColumnId;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnLogout;

    @FXML
    private TableColumn<AppointmentDao, String> tblColumnLocation;

    @FXML
    private ToggleGroup tglGroupAppointmentFilter;

    @FXML
    private TableColumn<AppointmentDao, String> tblColumnTitle;

    @FXML
    private TableView<AppointmentDao> tblViewAppointments;

    @FXML
    private DatePicker datePicker;

    @FXML
    private TableColumn<AppointmentDao, LocalDateTime> tblColumnEnd;

    @FXML
    private TableColumn<AppointmentDao, String> tblColumnCustomer;

    @FXML
    private Button btnDeleteAppointment;

    @FXML
    private TableColumn<AppointmentDao, String> tblColumnDescription;

    @FXML
    private Button btnAddAppointment;

    @FXML
    private TableColumn<AppointmentDao, String> tblColumnContact;

    @FXML
    private TableColumn<AppointmentDao, String> tblColumnUrl;

    @FXML
    private TableColumn<AppointmentDao, LocalDateTime> tblColumnStart;

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
    void onActionViewAllAppointments(ActionEvent event) {

    }


    @FXML
    void onActionBtnRadioViewByWeek(ActionEvent event) {

    }


    @FXML
    void onActionBtnRadioViewByMonth(ActionEvent event) {

    }


    @FXML
    void onActionDatePicker(ActionEvent event) {

    }

    @FXML
    void onActionBtnAddAppointment(ActionEvent event) throws IOException {
        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("/Views/Appointment/AddAppointment.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void onActionBtnEditAppointment(ActionEvent event) throws IOException, SQLException {

        AppointmentDao appointmentDao = tblViewAppointments.getSelectionModel().getSelectedItem();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Views/Appointment/EditAppointment.fxml"));
        loader.load();

        try {
            EditAppointmentController controller = loader.getController();
            controller.sendAppointment(appointmentDao);
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
        AppointmentDao appointmentDao = tblViewAppointments.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.WARNING, String.format("Are you sure you want to delete '%s'", appointmentDao.getTitle()), ButtonType.YES, ButtonType.NO);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.NO){
            return;
        }

        appointmentService.deleteAppointment(appointmentDao.getAppointmentId());
        tblViewAppointments.setItems(appointmentService.getAppointmentsForUser(UserStatics.getCurrentUserId()));
    }

    @FXML
    void onActionBtnBack(ActionEvent event) throws IOException {
        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("/Views/Main/MainScreen.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
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
        tblColumnId.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        tblColumnTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        tblColumnCustomer.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        tblColumnDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        tblColumnContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        tblColumnLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
        tblColumnType.setCellValueFactory(new PropertyValueFactory<>("type"));
        tblColumnUrl.setCellValueFactory(new PropertyValueFactory<>("url"));
        tblColumnStart.setCellValueFactory(new PropertyValueFactory<>("start"));
        tblColumnEnd.setCellValueFactory(new PropertyValueFactory<>("end"));
    }
}
