package Contracts.Mappers;

import Contracts.Statics.RoleStatics;
import Domain.Dtos.UserDto;
import Infrastructure.Models.Role;
import Infrastructure.Models.User;

import java.time.LocalDateTime;

public class MapUserDtoToUser extends BaseMapper<User, UserDto>{
    public User Map(UserDto userDto){
        Role userRole = RoleStatics.getRoleByRoleName(userDto.getRoleName());
        User user = new User(userDto.getUserId(), userDto.getUsername(), userDto.getPassword(), userDto.isActive(),
                userRole.getRoleId(), userDto.getName());

        return user;
    }
}
