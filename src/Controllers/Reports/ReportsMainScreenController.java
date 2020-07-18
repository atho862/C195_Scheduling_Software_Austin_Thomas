package Controllers.Reports;

import Contracts.Interfaces.Services.ILoginService;
import Contracts.Interfaces.Services.INavigationService;
import Domain.Services.LoginService;
import Domain.Services.NavigationService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import sun.rmi.runtime.Log;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ReportsMainScreenController implements Initializable {

    ILoginService loginService = new LoginService();
    INavigationService navigationService = new NavigationService();

    @FXML
    private Button btnBack;

    @FXML
    private Button btnLogout;

    @FXML
    private Button btnApptTypes;

    @FXML
    private Button btnConsultant;

    @FXML
    private Button btnApptCustomer;

    @FXML
    void onActionBtnBack(ActionEvent event) throws IOException {
        navigationService.navigateToMainScreen(event);
    }

    @FXML
    void onActionBtnLogout(ActionEvent event) throws IOException {
        loginService.logout(event);
    }

    @FXML
    void onActionBtnApptTypes(ActionEvent event) throws IOException {
        navigationService.navigateToAppointmentTypeByMonthScreen(event);
    }

    @FXML
    void onActionBtnApptCustomer(ActionEvent event) throws IOException {
        navigationService.navigateToAppointmentsByCustomerScreen(event);
    }

    @FXML
    void onActionBtnConsultant(ActionEvent event) throws IOException {
        navigationService.navigateToScheduleByConsultantScreen(event);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
