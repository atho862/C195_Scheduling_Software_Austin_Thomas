package Infrastructure.Models;

import java.time.LocalDateTime;
import java.util.Date;

public class Address {
    private int addressId;
    private String address;
    private String address2;
    private int cityId;
    private String postalCode;
    private String phone;
    private LocalDateTime createDate;
    private String createdBy;
    private LocalDateTime lastUpdate;
    private String lastUpdateBy;

    public Address(int id, String address, String address2, int cityId, String postalCode, String phone, LocalDateTime createdDate,
                   String createdBy, LocalDateTime lastUpdate, String lastUpdateBy){
        this.addressId = id;
        this.address = address;
        this.address2 = address2;
        this.cityId = cityId;
        this.postalCode = postalCode;
        this.phone = phone;
        this.createDate = createdDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdateBy = lastUpdateBy;
    }

    public int getAddressId() { return this.addressId; }
    public String getAddress() { return this.address; }
    public String getAddress2() { return this.address2; }
    public int getCityId() { return this.cityId; }
    public String getPostalCode() { return this.postalCode; }
    public String getPhone() { return this.phone; }
    public LocalDateTime getCreateDate() { return this.createDate; }
    public String getCreatedBy() { return this.createdBy; }
    public LocalDateTime getLastUpdate() { return this.lastUpdate; }
    public String getLastUpdateBy() { return this.lastUpdateBy; }

    public void setAddressId(int addressId){
        this.addressId = addressId;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public void setAddress2(String address2){
        this.address2 = address2;
    }

    public void setCityId(int cityId){
        this.cityId = cityId;
    }

    public void setPostalCode(String postalCode){
        this.postalCode = postalCode;
    }

    public void setPhone(String phone){
        this.phone = phone;
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
