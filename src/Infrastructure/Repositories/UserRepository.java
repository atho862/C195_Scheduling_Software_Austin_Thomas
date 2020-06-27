package Infrastructure.Repositories;

import Contracts.Interfaces.Repositories.IUserRepository;
import Infrastructure.DatabaseConnection;
import Infrastructure.Models.User;

public class UserRepository implements IUserRepository {

    @Override
    public void insertUser(User user) {

    }

    @Override
    public User getUserById(int userId) {
        return null;
    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public void deleteUser(int userId) {

    }
}
