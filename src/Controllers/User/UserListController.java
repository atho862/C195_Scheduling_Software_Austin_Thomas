package Controllers.User;

import Contracts.Interfaces.Services.ILoginService;
import Contracts.Interfaces.Services.INavigationService;
import Contracts.Interfaces.Services.IUserService;
import Contracts.Statics.RoleStatics;
import Domain.Dtos.UserDto;
import Domain.Services.LoginService;
import Domain.Services.NavigationService;
import Domain.Services.UserService;
import Infrastructure.Models.Role;
import Infrastructure.Models.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserListController implements Initializable {
    private ILoginService loginService = new LoginService();
    private INavigationService navigationService = new NavigationService();
    private IUserService userService = new UserService();
    private ObservableList<String> userFilterOptions = FXCollections.observableArrayList();

    @FXML
    private Button btnBack;

    @FXML
    private Button btnEditUser;

    @FXML
    private Button btnLogout;

    @FXML
    private Button btnSearch;

    @FXML
    private TableColumn<UserDto, String> tblColumnRole;

    @FXML
    private TableView<UserDto> tblViewUsers;

    @FXML
    private TableColumn<UserDto, String> tblColumnUsername;

    @FXML
    private ComboBox<String> cmboBoxRoles;

    @FXML
    private TableColumn<UserDto, String> tblColumnName;

    @FXML
    private Button btnAddUser;

    @FXML
    private TextField txtSearch;

    @FXML
    private TableColumn<UserDto, Boolean> tblColumnActive;

    @FXML
    private Button btnDeleteUser;

    @FXML
    void onActionBtnBack(ActionEvent event) throws IOException {
        navigationService.navigateToMainScreen(event);
    }

    @FXML
    void onActionBtnLogout(ActionEvent event) throws IOException {
        navigationService.navigateToLoginScreen(event);
    }

    @FXML
    void onActionBtnSearch(ActionEvent event) {
        String searchText = txtSearch.getText();
        if (searchText.trim().isEmpty()){
            new Alert(Alert.AlertType.ERROR, "Search text cannot be empty").show();
            return;
        }
        tblViewUsers.setItems(userService.searchUsersByName(searchText));
    }

    @FXML
    void onActionCmboBoxRoles(ActionEvent event) {
        String filterValue = cmboBoxRoles.getValue();
        if (filterValue.equals("All Users")){
            tblViewUsers.setItems(userService.getAllUsers());
        }
        else {
            Role role = userService.getRoleByRoleName(filterValue);
            tblViewUsers.setItems(userService.getAllUsersInRole(role));
        }
    }

    @FXML
    void onActionBtnAddUser(ActionEvent event) throws IOException {
        navigationService.navigateToAddEditUserScreen(event, null);
    }

    @FXML
    void onActionBtnEditUser(ActionEvent event) throws IOException {
        UserDto userDto = tblViewUsers.getSelectionModel().getSelectedItem();
        if (userDto == null){
            new Alert(Alert.AlertType.ERROR, "Please select an existing user to edit").show();
            return;
        }
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Views/User/AddEditUser.fxml"));
        loader.load();

        AddEditUserController controller = loader.getController();
        controller.sendUserDto(userDto);

        navigationService.navigateToAddEditUserScreen(event, loader);
    }

    @FXML
    void onActionBtnDeleteUser(ActionEvent event) {
        UserDto userDto = tblViewUsers.getSelectionModel().getSelectedItem();
        if (userDto == null){
            new Alert(Alert.AlertType.ERROR, "Please select an existing user to delete").show();
            return;
        }
        try {
            userService.deleteUser(userDto);
        }
        catch (Exception e){
            System.out.println(e);
            new Alert(Alert.AlertType.ERROR, "There was a problem deleting this user. Please try again").show();
        }
        tblViewUsers.setItems(userService.getAllUsers());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initTableView();
        initComboBox();
    }

    private void initTableView(){
        ObservableList<UserDto> userDtos = userService.getAllUsers();
        tblViewUsers.setItems(userDtos);
        tblColumnName.setCellValueFactory(cellView -> cellView.getValue().getNameProperty());
        tblColumnUsername.setCellValueFactory(cellView -> cellView.getValue().getUsernameProperty());
        tblColumnRole.setCellValueFactory(cellView -> cellView.getValue().getRoleNameProperty());
        tblColumnActive.setCellValueFactory(cellView -> cellView.getValue().getActiveProperty());
        tblColumnActive.setCellFactory(tc -> new TableCell<UserDto, Boolean>(){
            @Override
            protected void updateItem(Boolean item, boolean empty) {
                if (item == null){
                    setText(null);
                }
                else if (item.booleanValue()){
                    setText("Yes");
                }
                else {
                    setText("No");
                }
            }
        });
    }

    private void initComboBox(){
        userFilterOptions.add("All Users");
        for (Role role : userService.getAllRoles()
             ) {
            userFilterOptions.add(role.getRoleName());
        }

        cmboBoxRoles.setItems(userFilterOptions);
        cmboBoxRoles.setValue(userFilterOptions.get(0));
    }
}
