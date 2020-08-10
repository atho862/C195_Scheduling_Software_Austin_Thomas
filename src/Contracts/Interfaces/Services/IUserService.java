package Contracts.Interfaces.Services;

import Domain.Dtos.UserDto;
import Infrastructure.Models.Role;
import Infrastructure.Models.User;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface IUserService {

    void addUser(UserDto userDto) throws Exception;
    void updateUser(UserDto userDto) throws Exception;
    void deleteUser(UserDto userDto) throws SQLException, Exception;
    ObservableList<UserDto> getAllUsers();
    ObservableList<UserDto> getAllUsersInRole(Role role);
    ObservableList<Role> getAllRoles();
    Role getRoleByRoleName(String roleName);
    ObservableList<UserDto> searchUsersByName(String searchText);
    Role getRoleByRoleId(int roleId);
}
