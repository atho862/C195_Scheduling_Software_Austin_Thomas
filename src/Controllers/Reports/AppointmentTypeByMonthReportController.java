package Controllers.Reports;

import Contracts.Interfaces.Repositories.IAppointmentTypeCountRepository;
import Contracts.Interfaces.Services.ILoginService;
import Contracts.Interfaces.Services.INavigationService;
import Contracts.Interfaces.Services.IReportsService;
import Domain.Dtos.AppointmentTypeCountDto;
import Domain.Services.LoginService;
import Domain.Services.NavigationService;
import Domain.Services.ReportsService;
import Infrastructure.Repositories.AppointmentTypeCountRepository;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ResourceBundle;

public class AppointmentTypeByMonthReportController implements Initializable {

    IAppointmentTypeCountRepository appointmentTypeCountRepository = new AppointmentTypeCountRepository();
    INavigationService navigationService = new NavigationService();
    ILoginService loginService = new LoginService();
    IReportsService reportsService = new ReportsService();

    @FXML
    private Button btnBack;

    @FXML
    private Button btnLogout;

    @FXML
    private Label lblMonth;

    @FXML
    private Button btnExportToExcel;

    @FXML
    private TableView<AppointmentTypeCountDto> tblAppointmentTypeCounts;

    @FXML
    private ComboBox<Month> cmboBoxMonth;

    @FXML
    private TableColumn<AppointmentTypeCountDto, String> tblColumnAppointmentType;

    @FXML
    private TableColumn<AppointmentTypeCountDto, Integer> tblColumnCount;

    @FXML
    void onActionBtnBack(ActionEvent event) throws IOException {
        navigationService.navigateToReportsScreen(event);
    }

    @FXML
    void onActionBtnLogout(ActionEvent event) throws IOException {
        loginService.logout(event);
    }

    @FXML
    void onActionBtnExportToExcel(ActionEvent event){
        if (tblAppointmentTypeCounts.getItems().size() < 1){
            new Alert(Alert.AlertType.ERROR, "Unable to export a report for a month with no appointments. Please select a month with an appointment").show();
            return;
        }
        reportsService.exportAppointmentTypeByMonthToExcel(tblAppointmentTypeCounts.getItems(), cmboBoxMonth.getValue());
    }

    @FXML
    void onActionCmboBoxMonth(ActionEvent event) throws SQLException {
        Month selectedMonth = cmboBoxMonth.getValue();
        int month = selectedMonth.getValue();
        tblAppointmentTypeCounts.setItems(appointmentTypeCountRepository.getAppointmentTypeCountsByMonth(month));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeAppointmentTypeCountTable();
        cmboBoxMonth.setItems(FXCollections.observableArrayList(Month.values()));
        cmboBoxMonth.setValue(LocalDateTime.now().getMonth());
        try {
            tblAppointmentTypeCounts.setItems(appointmentTypeCountRepository.getAppointmentTypeCountsByMonth(LocalDateTime.now().getMonthValue()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void initializeAppointmentTypeCountTable(){
        //Add lambdas to quickly initialize table property values
        tblColumnAppointmentType.setCellValueFactory(cellData -> cellData.getValue().getTypeProperty());
        tblColumnCount.setCellValueFactory(cellData -> cellData.getValue().getCountProperty().asObject());
    }
}
