package Domain.Services;

import Contracts.Interfaces.Services.IUserService;
import Contracts.Mappers.MapUserDtoToUser;
import Contracts.Mappers.MapUserToUserDto;
import Domain.Dtos.UserDto;
import Infrastructure.Models.Role;
import Infrastructure.Models.User;
import Infrastructure.Repositories.RoleRepository;
import Infrastructure.Repositories.UserRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;

import java.sql.SQLException;

public class UserService implements IUserService {
    private UserRepository userRepository = new UserRepository();
    private RoleRepository roleRepository = new RoleRepository();
    private MapUserToUserDto userToUserDtoMapper = new MapUserToUserDto();
    private MapUserDtoToUser userDtoToUserMapper = new MapUserDtoToUser();


    @Override
    public void addUser(UserDto userDto) throws Exception {
        User user = userDtoToUserMapper.Map(userDto);
        userRepository.insertUser(user);
    }

    @Override
    public void updateUser(UserDto userDto) throws Exception {
        User user = userDtoToUserMapper.Map(userDto);
        userRepository.updateUser(user);
    }

    @Override
    public void deleteUser(UserDto userDto) throws SQLException, Exception {
        userRepository.deleteUser(userDto.getUserId());
    }

    @Override
    public ObservableList<UserDto> getAllUsers() {
        ObservableList<UserDto> userDtoList = FXCollections.observableArrayList();
        ObservableList<User> users = userRepository.getAllUsers();
        for (User user : users
             ) {
            UserDto userDto = userToUserDtoMapper.Map(user);
            userDtoList.add(userDto);
        }

        return userDtoList;
    }

    @Override
    public ObservableList<UserDto> getAllUsersInRole(Role role) {
        ObservableList<UserDto> userDtos = FXCollections.observableArrayList();
        ObservableList<User> users = userRepository.getAllUsersInRole(role);
        for (User user : users) {
            UserDto userDto = userToUserDtoMapper.Map(user);
            userDtos.add(userDto);
        }
        return userDtos;
    }

    @Override
    public ObservableList<Role> getAllRoles(){
        return roleRepository.getAllRoles();
    }

    public Role getRoleByRoleId(int roleId){
        ObservableList<Role> roles = roleRepository.getAllRoles();
        for (Role role : roles
             ) {
            if (role.getRoleId() == roleId){
                return role;
            }
        }
        return null;
    }

    @Override
    public Role getRoleByRoleName(String roleName){
        ObservableList<Role> roles = roleRepository.getAllRoles();
        for (Role role : roles
             ) {
            if (role.getRoleName().equals(roleName)){
                return role;
            }
        }

        return null;
    }

    @Override
    public ObservableList<UserDto> searchUsersByName(String searchText){
        ObservableList<UserDto> userDtos = FXCollections.observableArrayList();
        ObservableList<User> users = userRepository.getAllUsers();
        for (User user : users
             ) {
            if (user.getName().toLowerCase().contains(searchText)){
                UserDto userDto = userToUserDtoMapper.Map(user);
                userDtos.add(userDto);
            }
        }
        return userDtos;
    }
}
