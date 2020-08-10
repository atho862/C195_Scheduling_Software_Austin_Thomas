package Infrastructure.Repositories;

import Contracts.Interfaces.Repositories.IRoleRepository;
import Infrastructure.DatabaseConnection;
import Infrastructure.Models.Role;
import com.mysql.jdbc.PreparedStatement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleRepository implements IRoleRepository {

    @Override
    public ObservableList<Role> getAllRoles() {
        ObservableList<Role> roles = FXCollections.observableArrayList();
        try {
            PreparedStatement statement = (PreparedStatement) DatabaseConnection.dbConnection.prepareStatement(
                    "SELECT * FROM role");
            ResultSet results = statement.executeQuery();
            while(results.next()){
                int roleId = results.getInt("roleId");
                String roleName = results.getString("roleName");
                Role role = new Role(roleId, roleName);
                roles.add(role);
            }

            return roles;
        }
        catch (SQLException e){
            System.out.println(e);
            return null;
        }

    }
}
