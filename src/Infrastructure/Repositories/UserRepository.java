package Infrastructure.Repositories;

import Contracts.Interfaces.Repositories.IUserRepository;
import Infrastructure.DatabaseConnection;
import Infrastructure.Models.User;
import com.mysql.jdbc.Statement;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository implements IUserRepository {

    @Override
    public void insertUser(User user) {

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
