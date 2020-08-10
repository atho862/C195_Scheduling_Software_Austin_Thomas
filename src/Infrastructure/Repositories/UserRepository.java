package Infrastructure.Repositories;

import Contracts.Interfaces.Repositories.IUserRepository;
import Contracts.Statics.UserStatics;
import Infrastructure.DatabaseConnection;
import Infrastructure.Models.Role;
import Infrastructure.Models.User;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import com.sun.org.apache.xerces.internal.xs.XSTerm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.xml.crypto.Data;
import javax.xml.transform.Result;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class UserRepository implements IUserRepository {
    @Override
    public int getMaxId(){
        return 0;
    }

    @Override
    public void insertUser(User user) throws Exception {
        try {
            PreparedStatement statement = (PreparedStatement) DatabaseConnection.dbConnection.prepareStatement(
                    "INSERT INTO user (userId, userName, password, name, roleId, active, createDate, createdBy, lastUpdate, lastUpdateBy)" +
                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            statement.setInt(1, user.getUserId());
            statement.setString(2, user.getUserName());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getName());
            statement.setInt(5, user.getRoleId());
            statement.setBoolean(6, user.isActive());
            statement.setTimestamp(7, Timestamp.valueOf(LocalDateTime.now()));
            statement.setString(8, UserStatics.getCurrentUserName());
            statement.setTimestamp(9, Timestamp.valueOf(LocalDateTime.now()));
            statement.setString(10, UserStatics.getCurrentUserName());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated == 0){
                throw new Exception("Error saving User");
            }
        }
        catch (SQLException e){
            System.out.println(e);
        }
    }

    @Override
    public ObservableList<String> getAllUsernames() throws SQLException {
        ObservableList<String> usernames = FXCollections.observableArrayList();
        PreparedStatement statement = (PreparedStatement) DatabaseConnection.dbConnection.prepareStatement(
                "SELECT username FROM user");

        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
            String username = resultSet.getString(1);
            usernames.add(username);
        }

        return usernames;
    }

    @Override
    public int getUserIdByUsername(String username) throws SQLException {
        PreparedStatement statement = (PreparedStatement) DatabaseConnection.dbConnection.prepareStatement(
                "SELECT userId FROM user WHERE username = ?");

        statement.setString(1, username);

        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
            int userId = resultSet.getInt(1);

            return userId;
        }

        return 0;
    }

    @Override
    public User getUserById(int userId) {
        return null;
    }

    @Override
    public User getUserByUsername(String existingUsername) {
        String userName = new String();
        String password = new String();
        String name = new String();
        boolean isActive = true;
        int roleId = 0;
        LocalDateTime createDate = LocalDateTime.now();
        String createdBy = new String();
        LocalDateTime lastUpdate = LocalDateTime.now();
        String lastUpdateBy = new String();

        int id = 0;

        try {
            PreparedStatement statement = (PreparedStatement) DatabaseConnection.dbConnection.prepareStatement(
                    "SELECT * FROM user WHERE userName = ?");
            statement.setString(1, existingUsername);
            ResultSet results = statement.executeQuery();
            while (results.next()) {
                id = results.getInt("userId");
                userName = results.getString("userName");
                password = results.getString("password");
                isActive = results.getBoolean("active");
                roleId = results.getInt("roleId");
                name = results.getString("name");
                createDate = results.getTimestamp("createDate").toLocalDateTime();
                createdBy = results.getString("createdBy");
                lastUpdate = results.getTimestamp("lastUpdate").toLocalDateTime();
                lastUpdateBy = results.getString("lastUpdateBy");
            }
            if (userName != null && password != null && id != 0) {
                User user = new User(id, userName, password, isActive, roleId, name, createDate, createdBy, lastUpdate, lastUpdateBy);
                return user;
            }
            else {
                return null;
            }
        }
        catch (SQLException e){
            System.out.println(e);
            return null;
        }
    }

    @Override
    public void updateUser(User user) throws SQLException, Exception {
        PreparedStatement statement = (PreparedStatement) DatabaseConnection.dbConnection.prepareStatement(
                "UPDATE user "+
                "SET userName = ?, password = ?, name = ?, roleId = ?, active = ?, lastUpdate = ?, lastUpdateBy = ? " +
                "WHERE userId = ?");
        statement.setString(1, user.getUserName());
        statement.setString(2, user.getPassword());
        statement.setString(3, user.getName());
        statement.setInt(4, user.getRoleId());
        statement.setBoolean(5, user.isActive());
        statement.setTimestamp(6, Timestamp.valueOf(LocalDateTime.now()));
        statement.setString(7, UserStatics.getCurrentUserName());
        statement.setInt(8, user.getUserId());

        int rowsUpdated = statement.executeUpdate();
        if (rowsUpdated == 0){
            throw new Exception("Unable to update user");
        }
    }

    @Override
    public void deleteUser(int userId) throws SQLException, Exception{
        PreparedStatement statement = (PreparedStatement) DatabaseConnection.dbConnection.prepareStatement(
                "DELETE FROM user WHERE userId = ?");
        statement.setInt(1, userId);
        int rowsDeleted = statement.executeUpdate();
        if (rowsDeleted == 0){
            throw new Exception("Unable to delete user");
        }
    }

    @Override
    public ObservableList<User> getAllUsers(){
        ObservableList<User> users = FXCollections.observableArrayList();
        try {
            PreparedStatement statement = (PreparedStatement) DatabaseConnection.dbConnection.prepareStatement(
                    "SELECT * FROM user");
            ResultSet results = statement.executeQuery();
            while (results.next()){
                int userId = results.getInt("userId");
                String username = results.getString("userName");
                String password = results.getString("password");
                int roleId = results.getInt("roleId");
                boolean isActive = results.getBoolean("active");
                String name = results.getString("name");
                User user = new User(userId, username, password, isActive, roleId, name);
                users.add(user);
            }

            return users;
        }
        catch (SQLException e){
            System.out.println(e);
            return users;
        }
    }

    @Override
    public ObservableList<User> getAllUsersInRole(Role role) {
        ObservableList<User> users = FXCollections.observableArrayList();
        try {
            PreparedStatement statement = (PreparedStatement) DatabaseConnection.dbConnection.prepareStatement(
                    "SELECT * FROM user WHERE roleId = ?");
            statement.setInt(1, role.getRoleId());
            ResultSet results = statement.executeQuery();
            while (results.next()){
                int userId = results.getInt("userId");
                String username = results.getString("userName");
                String password = results.getString("password");
                int roleId = results.getInt("roleId");
                String name = results.getString("name");
                boolean isActive = results.getBoolean("active");
                User user = new User(userId, username, password, isActive, roleId, name);
                users.add(user);
            }
            return users;
        }
        catch (SQLException e){
            System.out.println(e);
            return users;
        }
    }

}
