package Controllers.Login;

import Contracts.Interfaces.Services.INavigationService;
import Domain.Dtos.LoginDto;
import Contracts.Interfaces.Services.ILoginService;
import Domain.Services.NavigationService;
import Domain.Services.LoginService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    ILoginService loginService = new LoginService();
    INavigationService navigationService = new NavigationService();
    String language;

    @FXML
    private TextField txtUsername;

    @FXML
    private Button btnLogin;

    @FXML
    private Label lblUsername;

    @FXML
    private Label lblPassword;

    @FXML
    private Button btnExit;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private Label lblLogoThe;

    @FXML
    private Label lblLogoScheduler;

    @FXML
    void onActionBtnLogin(ActionEvent event) throws IOException, SQLException {
        String username = txtUsername.getText();
        String password = txtPassword.getText();

        LoginDto loginDto = new LoginDto(username, password);
        boolean isValidLogin = loginService.login(loginDto);
        if (isValidLogin){
            navigationService.navigateToMainScreen(event);
        }
        else {
            if (language.equals("spa")){
                new Alert(Alert.AlertType.ERROR,"No se puede iniciar sesión: combinación de nombre de usuario / contraseña no válida").show();
            }
            else {
                new Alert(Alert.AlertType.ERROR, "Unable to login: Invalid username/password combination").show();
            }
        }
    }

    @FXML
    void onActionBtnExit(ActionEvent event) {
        System.exit(0);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        language = Locale.getDefault().getISO3Language();
        if (language.equals("spa")) {
            convertLoginScreenToSpanish();
        }
    }

    private void convertLoginScreenToSpanish(){
        lblUsername.setText("Nombre de usuario");
        txtUsername.setPromptText(lblUsername.getText());
        lblPassword.setText("Contraseña");
        txtPassword.setPromptText(lblPassword.getText());
        lblLogoThe.setText("El");
        lblLogoScheduler.setText("Planificador");
        btnLogin.setText("Iniciar Sesión");
        btnExit.setText("Salida");
    }
}
