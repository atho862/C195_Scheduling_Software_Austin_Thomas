package Controllers.User;

import Contracts.Interfaces.Services.INavigationService;
import Contracts.Interfaces.Services.IUserService;
import Domain.Dtos.UserDto;
import Domain.Services.NavigationService;
import Domain.Services.UserService;
import Infrastructure.Models.Role;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddEditUserController implements Initializable {
    private IUserService userService = new UserService();
    private INavigationService navigationService = new NavigationService();
    private int userId = 0;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnCancel;

    @FXML
    private TextField txtUsername;

    @FXML
    private Button btnSave;

    @FXML
    private TextField txtName;

    @FXML
    private ChoiceBox<String> chcBoxActive;

    @FXML
    private ChoiceBox<String> chcBoxRole;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private PasswordField txtConfirmPassword;

    @FXML
    private Label lblTitle;

    @FXML
    void onActionBtnBack(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.WARNING, "Are you sure you want to leave this page? Any unsaved information will be lost!",
                ButtonType.YES, ButtonType.NO);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.NO){
            return;
        }

        navigationService.navigateToUserListScreen(event);
    }

    @FXML
    void onActionBtnSave(ActionEvent event) throws IOException {
        String name = txtName.getText();
        String userName = txtUsername.getText();
        String password = txtPassword.getText();
        String confirmPassword = txtConfirmPassword.getText();
        boolean isActive = chcBoxActive.getValue().equals("Yes") ? true : false;
        int roleId = userService.getRoleByRoleName(chcBoxRole.getValue()).getRoleId();
        try {
            validateName(name);
            validateUserName(userName);
            validatePassword(password, confirmPassword);
        }
        catch (Exception e){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            return;
        }

        UserDto userDto = new UserDto(userId, userName, password, chcBoxRole.getValue(), isActive, name);

        if (userId == 0){
            try {
                userService.addUser(userDto);
            }
            catch (Exception e){
                new Alert(Alert.AlertType.ERROR, "There was a problem saving this user. Please try again").show();
                return;
            }

            navigationService.navigateToUserListScreen(event);
        }
        else {
            try {
                userService.updateUser(userDto);
            }
            catch (Exception e){
                new Alert(Alert.AlertType.ERROR, "There was a problem updating this user. Please try again").show();
                return;
            }

            navigationService.navigateToUserListScreen(event);
        }
    }

    @FXML
    void onActionBtnCancel(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.WARNING, "Are you sure you want to leave this page? Any unsaved information will be lost!",
                ButtonType.YES, ButtonType.NO);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.NO){
            return;
        }

        navigationService.navigateToUserListScreen(event);
    }

    public void sendUserDto(UserDto user){
        userId = user.getUserId();
        txtName.setText(user.getName());
        txtUsername.setText(user.getUsername());
        txtPassword.setText(user.getPassword());
        txtConfirmPassword.setText(user.getPassword());
        if (user.isActive()){
            chcBoxActive.setValue("Yes");
        }
        else {
            chcBoxActive.setValue("No");
        }
        chcBoxRole.setValue(user.getRoleName());
        lblTitle.setText("Edit User");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initActiveChoiceBox();
        initRoleChoicebox();
        if (userId != 0){
            lblTitle.setText("Edit User");
        }
    }

    private void initActiveChoiceBox(){
        ObservableList<String> activeChoices = FXCollections.observableArrayList();
        activeChoices.addAll("Yes", "No");
        chcBoxActive.setItems(activeChoices);
        chcBoxActive.setValue("Yes");
    }

    private void initRoleChoicebox(){
        ObservableList<String> roleChoices = FXCollections.observableArrayList();
        for (Role role : userService.getAllRoles()
             ) {
            roleChoices.add(role.getRoleName());
        }
        chcBoxRole.setItems(roleChoices);
        chcBoxRole.setValue(roleChoices.get(0));
    }

    private void validateName(String name) throws Exception {
        if (name.trim().isEmpty()){
            throw new Exception("Name cannot be empty");
        }
    }

    private void validateUserName(String userName) throws Exception {
        if (userName.trim().isEmpty()){
            throw new Exception("Username cannot be empty");
        }
        if (userName.contains(" ")) {
            throw new Exception("Username cannot contain spaces");
        }
    }

    private void validatePassword(String password, String confirmPassword) throws Exception {
        if (!password.equals(confirmPassword)){
            throw new Exception("Passwords must match!");
        }

        if (password.length() < 6){
            throw new Exception("Password must be at least 6 characters long");
        }
    }
}
