package Contracts.Interfaces.Repositories;

import Infrastructure.Models.User;

public interface IUserRepository {
    void insertUser(User user);
    User getUserById(int userId);
    void updateUser(User user);
    void deleteUser(int userId);
}
