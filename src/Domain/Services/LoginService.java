package Domain.Services;

import Domain.Daos.LoginDao;
import Contracts.Interfaces.Repositories.IUserRepository;
import Contracts.Interfaces.Services.ILoginService;
import Contracts.Statics.UserStatics;
import Infrastructure.Models.User;
import Infrastructure.Repositories.UserRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.SQLException;

public class LoginService implements ILoginService {
    private IUserRepository userRepository;
    Stage stage;
    Parent root;

    @Override
    public boolean login(LoginDao loginDao) throws SQLException {
        boolean isValidLogin;
        userRepository = new UserRepository();
        User user = userRepository.getUserByUsername(loginDao.getUsername());
        if (user.getUserName().equals(loginDao.getUsername()) && user.getPassword().equals(loginDao.getPassword())){
            isValidLogin = true;
            setCurrentUserId(user.getUserId());
            setCurrentUserName(user.getUserName());

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
