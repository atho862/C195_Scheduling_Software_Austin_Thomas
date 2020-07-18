package Contracts.Interfaces.Services;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;

import javax.swing.*;
import java.io.IOException;

public interface INavigationService {
    void navigateToMainScreen(ActionEvent event) throws IOException;
    void navigateToLoginScreen(ActionEvent event) throws IOException;
    void navigateToAppointmentListScreen(ActionEvent event) throws IOException;
    void navigateToAddAppointmentScreen(ActionEvent event) throws IOException;
    void navigateToCustomerListScreen(ActionEvent event) throws IOException;
    void navigateToAddCustomerScreen(ActionEvent event) throws IOException;
    void navigateToReportsScreen(ActionEvent event) throws IOException;
    void navigateToEditCustomerScreen(ActionEvent event, FXMLLoader loader) throws IOException;
    void navigateToAppointmentTypeByMonthScreen(ActionEvent event) throws IOException;
    void navigateToScheduleByConsultantScreen(ActionEvent event) throws IOException;
    void navigateToAppointmentsByCustomerScreen(ActionEvent event) throws IOException;
}
