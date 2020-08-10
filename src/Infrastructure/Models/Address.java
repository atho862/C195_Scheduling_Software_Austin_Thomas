package Infrastructure.Models;

import java.time.LocalDateTime;
import java.util.Date;

public class Address extends BaseModel{
    private int addressId;
    private String address;
    private String address2;
    private int cityId;
    private String postalCode;
    private String phone;

    public Address(int id, String address, String address2, int cityId, String postalCode, String phone, LocalDateTime createdDate,
                   String createdBy, LocalDateTime lastUpdate, String lastUpdateBy){
        super(createdDate, createdBy, lastUpdate, lastUpdateBy);
        this.addressId = id;
        this.address = address;
        this.address2 = address2;
        this.cityId = cityId;
        this.postalCode = postalCode;
        this.phone = phone;

    }

    public int getAddressId() { return this.addressId; }
    public String getAddress() { return this.address; }
    public String getAddress2() { return this.address2; }
    public int getCityId() { return this.cityId; }
    public String getPostalCode() { return this.postalCode; }
    public String getPhone() { return this.phone; }

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
}
