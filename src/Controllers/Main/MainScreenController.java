package Controllers.Main;

import Contracts.Interfaces.Services.ILoginService;
import Domain.Services.LoginService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainScreenController implements Initializable {

    Stage stage;
    Parent root;
    ILoginService loginService = new LoginService();

    @FXML
    private ImageView imgViewCustomers;

    @FXML
    private Button btnLogout;

    @FXML
    private Button btnReports;

    @FXML
    private ImageView imgViewReports;

    @FXML
    private Button btnCustomers;

    @FXML
    private ImageView imgViewAppointments;

    @FXML
    private Button btnAppointments;

    @FXML
    void onActionBtnAppointments(ActionEvent event) throws IOException {
        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("/Views/Appointment/AppointmentList.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void onActionBtnCustomers(ActionEvent event) throws IOException {
        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("/Views/Customer/CustomerList.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void onActionBtnReports(ActionEvent event) throws IOException{
        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("/Views/Reports/Reports.fxml"));
    }

    @FXML
    void onActionBtnLogout(ActionEvent event) throws IOException {
        loginService.logout(event);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Image appointmentsImage = new Image("/Assets/calendar.png");
        Image customersImage = new Image("/Assets/customer.png");
        Image reportsImage = new Image("/Assets/reports.png");

        imgViewAppointments.setImage(appointmentsImage);
        imgViewCustomers.setImage(customersImage);
        imgViewReports.setImage(reportsImage);
    }
}
