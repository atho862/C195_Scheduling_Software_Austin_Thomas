package Infrastructure.Models;

import java.time.LocalDateTime;
import java.util.Date;

public class Customer {
    private int customerId;
    private String customerName;
    private int addressId;
    private boolean active;
    private LocalDateTime createDate;
    private String createdBy;
    private LocalDateTime lastUpdate;
    private String lastUpdateBy;

    public Customer(int customerId, String customerName, int addressId, boolean isActive, LocalDateTime createDate, String createdBy,
                    LocalDateTime lastUpdate, String lastUpdateBy){
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
    public LocalDateTime getCreateDate() { return this.createDate; }
    public String getCreatedBy() { return this.createdBy; }
    public LocalDateTime getLastUpdate() { return this.lastUpdate; }
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

    public void setCreateDate(LocalDateTime createDate){
        this.createDate = createDate;
    }

    public void setCreatedBy(String createdBy){
        this.createdBy = createdBy;
    }

    public void setLastUpdate(LocalDateTime lastUpdate){
        this.lastUpdate = lastUpdate;
    }

    public void setLastUpdateBy(String lastUpdateBy){
        this.lastUpdateBy = lastUpdateBy;
    }
}
