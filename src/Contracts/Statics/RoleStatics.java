package Contracts.Statics;

import Infrastructure.Models.Role;
import Infrastructure.Repositories.RoleRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class RoleStatics {
    private static ObservableList<Role> roles;
    private static RoleRepository repository = new RoleRepository();

    public static ObservableList<Role> getRoles(){
        roles = repository.getAllRoles();
        roles.addAll(new Role(1, "User"), new Role(2, "Administrator"));
        return roles;
    }

    public static Role getRoleByRoleId(int roleId){
        for (Role role: roles ){
            if (role.getRoleId() == roleId){
                return role;
            }
        }

        return null;
    }

    public static Role getRoleByRoleName(String roleName){
        for (Role role : roles
             ) {
            if (role.getRoleName().equals(roleName)){
                return role;
            }
        }

        return null;
    }
}
