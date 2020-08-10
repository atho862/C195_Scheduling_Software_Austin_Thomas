package Contracts.Interfaces.Repositories;

import Infrastructure.Models.Role;
import Infrastructure.Models.User;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface IUserRepository {
    int getMaxId() throws SQLException;
    void insertUser(User user) throws Exception;
    User getUserById(int userId);
    User getUserByUsername(String username) throws SQLException;
    void updateUser(User user) throws SQLException, Exception;
    void deleteUser(int userId) throws SQLException, Exception;
    ObservableList<String> getAllUsernames() throws SQLException;
    int getUserIdByUsername(String username) throws SQLException;
    ObservableList<User> getAllUsers();
    ObservableList<User> getAllUsersInRole(Role role);
}
