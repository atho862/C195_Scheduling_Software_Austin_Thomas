package Infrastructure.Repositories;

import Contracts.Interfaces.Repositories.IUserRepository;
import Infrastructure.DatabaseConnection;
import Infrastructure.Models.User;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.xml.crypto.Data;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository implements IUserRepository {

    @Override
    public void insertUser(User user) {

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
    public User getUserByUsername(String username) throws SQLException {
        String userName = new String();
        String password = new String();
        int id = 0;
        Statement statement = (Statement) DatabaseConnection.dbConnection.createStatement();
        String sql = "SELECT * FROM user WHERE userName = '" + username + "'";
        ResultSet results = statement.executeQuery(sql);
        while (results.next()) {
            userName = results.getString("userName");
            password = results.getString("password");
            id = results.getInt("userId");
        }
        if (userName != null && password != null && id != 0) {
            User user = new User(userName, password, id);
            return user;
        }
        else {
            return null;
        }
    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public void deleteUser(int userId) {

    }
}
