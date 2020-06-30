package Contracts.Interfaces.Repositories;

import Infrastructure.Models.User;

import java.sql.SQLException;

public interface IUserRepository {
    void insertUser(User user);
    User getUserById(int userId);
    User getUserByUsername(String username) throws SQLException;
    void updateUser(User user);
    void deleteUser(int userId);
}
