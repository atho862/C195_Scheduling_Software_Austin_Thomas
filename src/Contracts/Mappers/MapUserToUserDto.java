package Contracts.Mappers;

import Contracts.Statics.RoleStatics;
import Domain.Dtos.UserDto;
import Infrastructure.Models.Role;
import Infrastructure.Models.User;

public class MapUserToUserDto extends BaseMapper<UserDto, User> {
    public UserDto Map(User user){
        Role currentUserRole = RoleStatics.getRoleByRoleId(user.getRoleId());
        UserDto userDto = new UserDto(user.getUserId(), user.getUserName(), user.getPassword(), currentUserRole.getRoleName(),
                user.isActive(), user.getName());
        return userDto;
    }
}
