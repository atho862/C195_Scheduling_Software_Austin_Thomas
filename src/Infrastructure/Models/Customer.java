package Infrastructure.Models;

import java.time.LocalDateTime;
import java.util.Date;

public class Customer extends BaseModel{
    private int customerId;
    private String customerName;
    private int addressId;
    private boolean active;

    public Customer(int customerId, String customerName, int addressId, boolean isActive, LocalDateTime createDate, String createdBy,
                    LocalDateTime lastUpdate, String lastUpdateBy){
        super(createDate, createdBy, lastUpdate, lastUpdateBy);
        this.customerId = customerId;
        this.customerName = customerName;
        this.addressId = addressId;
        this.active = isActive;
    }

    public int getCustomerId(){ return this.customerId; }
    public String getCustomerName() { return this.customerName; }
    public int getAddressId() { return this.addressId; }
    public boolean isActive() { return this.active; }

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

}
