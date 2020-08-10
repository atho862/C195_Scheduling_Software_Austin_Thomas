package Infrastructure.Models;

import java.time.LocalDateTime;
import java.util.Date;

public class City extends BaseModel{
    private int cityId;
    private String city;
    private int countryId;

    public City(int cityId, String city, int countryId, LocalDateTime createDate, String createdBy, LocalDateTime lastUpdate, String lastUpdateBy){
        super(createDate, createdBy, lastUpdate, lastUpdateBy);
        this.cityId = cityId;
        this.city = city;
        this.countryId = countryId;
    }

    public int getCityId() { return this.cityId; }
    public String getCity() { return this.city; }
    public int getCountryId() { return this.countryId; }

    public void setCityId(int cityId){
        this.cityId = cityId;
    }

    public void setCity(String city){
        this.city = city;
    }

    public void setCountryId(int countryId){
        this.countryId = countryId;
    }

}
