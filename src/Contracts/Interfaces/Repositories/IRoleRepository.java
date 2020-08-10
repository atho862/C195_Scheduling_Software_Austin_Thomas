package Contracts.Interfaces.Repositories;

import Infrastructure.Models.Role;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface IRoleRepository {
    ObservableList<Role> getAllRoles() throws SQLException;
}
