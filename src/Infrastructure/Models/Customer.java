package Infrastructure.Models;

import java.util.Date;

public class Customer {
    private int customerId;
    private String customerName;
    private int addressId;
    private boolean active;
    private Date createDate;
    private String createdBy;
    private Date lastUpdate;
    private String lastUpdateBy;

    public Customer(int customerId, String customerName, int addressId, boolean isActive, Date createDate, String createdBy,
                    Date lastUpdate, String lastUpdateBy){
        this.customerId = customerId;
        this.customerName = customerName;
        this.addressId = addressId;
        this.active = isActive;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdateBy = lastUpdateBy;
    }

    public int getCustomerId(){ return this.customerId; }
    public String getCustomerName() { return this.customerName; }
    public int getAddressId() { return this.addressId; }
    public boolean isActive() { return this.active; }
    public Date getCreateDate() { return this.createDate; }
    public String getCreatedBy() { return this.createdBy; }
    public Date getLastUpdate() { return this.lastUpdate; }
    public String getLastUpdateBy() { return this.lastUpdateBy; }

    public void setCustomerId(int customerId){
        this.customerId = customerId;
    }

    public void setCustomerName(String customerName){
        this.customerName = customerName;
    }

    public void setAddressId(int addressId){
        this.addressId = addressId;
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
