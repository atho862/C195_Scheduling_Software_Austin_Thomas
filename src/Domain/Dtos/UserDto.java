package Domain.Dtos;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class UserDto {
    private int userId;
    private final SimpleStringProperty username = new SimpleStringProperty();
    private final SimpleStringProperty password = new SimpleStringProperty();
    private final SimpleStringProperty roleName = new SimpleStringProperty();
    private final SimpleBooleanProperty active = new SimpleBooleanProperty();
    private final SimpleStringProperty name = new SimpleStringProperty();

    public UserDto(int userId, String username, String password, String roleName, boolean isActive, String name){
        this.userId = userId;
        this.username.set(username);
        this.password.set(password);
        this.roleName.set(roleName);
        this.active.set(isActive);
        this.name.set(name);
    }

    public int getUserId() {
        return this.userId;
    }

    public String getUsername() {
        return username.get();
    }

    public void setUsername(String username) {
        this.username.set(username);
    }

    public String getPassword() {
        return password.get();
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public String getRoleName() {
        return roleName.get();
    }

    public void setRoleId(String roleName) {
        this.roleName.set(roleName);
    }

    public boolean isActive() {
        return active.get();
    }

    public void setActive(boolean active) {
        this.active.set(active);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public StringProperty getUsernameProperty(){
        return this.username;
    }

    public StringProperty getPasswordProperty(){
        return this.password;
    }

    public StringProperty getRoleNameProperty(){
        return this.roleName;
    }

    public StringProperty getNameProperty(){
        return this.name;
    }

    public BooleanProperty getActiveProperty(){ return this.active; }
}
