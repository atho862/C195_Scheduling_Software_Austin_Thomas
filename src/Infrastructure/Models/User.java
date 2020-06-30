package Infrastructure.Models;

import java.util.Date;

public class User {
    private int userId;
    private String userName;
    private String password;
    private boolean active;
    private Date createDate;
    private String createdBy;
    private Date lastUpdate;
    private String lastUpdateBy;

    public User(String userName, String password, int userId){
        this.userName = userName;
        this.password = password;
        this.userId = userId;
    }

    public User(int userId, String userName, String password, boolean isActive, Date createDate, String createdBy,
                Date lastUpdate, String lastUpdateBy){
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.active = isActive;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdateBy = lastUpdateBy;
    }

    public int getUserId() { return this.userId; }
    public String getUserName() { return this.userName; }
    public String getPassword() { return this.password; }
    public boolean isActive() { return this.active; }
    public Date getCreateDate() { return this.createDate; }
    public String getCreatedBy() { return this.createdBy; }
    public Date getLastUpdate() { return this.lastUpdate; }
    public String getLastUpdateBy() { return this.lastUpdateBy; }

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

    public void setCreateDate(Date createDate){
        this.createDate = createDate;
    }

    public void setCreatedBy(String createdBy){
        this.createdBy = createdBy;
    }

    public void setLastUpdate(Date lastUpdate){
        this.lastUpdate = lastUpdate;
    }

    public void setLastUpdateBy(String lastUpdateBy){
        this.lastUpdateBy = lastUpdateBy;
    }
}
