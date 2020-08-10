package Domain.Services;

import Contracts.Interfaces.Services.IAppointmentService;
import Contracts.Statics.RoleStatics;
import Domain.Dtos.LoginDto;
import Contracts.Interfaces.Repositories.IUserRepository;
import Contracts.Interfaces.Services.ILoginService;
import Contracts.Statics.UserStatics;
import Infrastructure.Models.User;
import Infrastructure.Repositories.UserRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class LoginService implements ILoginService {
    IUserRepository userRepository = new UserRepository();
    IAppointmentService appointmentService = new AppointmentService();
    Stage stage;
    Parent root;

    @Override
    public boolean login(LoginDto loginDto) throws SQLException, IOException {
        boolean isValidLogin;
        User user = userRepository.getUserByUsername(loginDto.getUsername());
        if (user.getUserName().equals(loginDto.getUsername()) && user.getPassword().equals(loginDto.getPassword())){
            isValidLogin = true;
            setCurrentUserId(user.getUserId());
            setCurrentUserName(user.getUserName());
            RoleStatics.getRoles();
            UserStatics.setCurrentUserRoleId(user.getRoleId());
            FileWriter writer = new FileWriter("LoginHistory.txt", true);
            writer.append("User: " + user.getUserName() + " logged in at " + LocalDateTime.now() + System.lineSeparator());
            writer.close();
            if (appointmentService.checkForUpcomingAppointments()){
                new Alert(Alert.AlertType.INFORMATION, "You have an appointment starting in the next 15 minutes!").show();
            }
        }
        else {
            isValidLogin = false;
        }

        return isValidLogin;
    }

    @Override
    public void logout(ActionEvent actionEvent) throws IOException {
        stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("/Views/Login/Login.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private void setCurrentUserName(String userName) {
        UserStatics.setCurrentUserName(userName);
    }

    private void setCurrentUserId(int id){
        UserStatics.setCurrentUserId(id);
    }
}
