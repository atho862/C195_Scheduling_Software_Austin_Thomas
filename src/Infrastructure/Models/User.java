package Infrastructure.Models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class User extends BaseModel{
    private int userId;
    private String userName;
    private String password;
    private boolean active;
    private int roleId;
    private String name;

    public User(int userId, String userName, String password, boolean isActive, int roleId, String name, LocalDateTime createDate, String createdBy,
                LocalDateTime lastUpdate, String lastUpdateBy){
        super(createDate, createdBy, lastUpdate, lastUpdateBy);
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.active = isActive;
        this.roleId = roleId;
        this.name = name;
    }

    public User(int userId, String userName, String password, boolean isActive, int roleId, String name){
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.active = isActive;
        this.roleId = roleId;
        this.name = name;
    }

    public int getUserId() { return this.userId; }
    public String getUserName() { return this.userName; }
    public String getPassword() { return this.password; }
    public boolean isActive() { return this.active; }
    public int getRoleId(){ return this.roleId; }
    public String getName() { return this.name; }

    public void setUserId(int userId){
        this.userId = userId;
    }

    public void setUserName(String userName){
        this.userName = userName;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public void setActive(boolean isActive){
        this.active = isActive;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
    public void setName(String name){
        this.name = name;
    }

}
