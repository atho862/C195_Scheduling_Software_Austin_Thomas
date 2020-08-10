package Infrastructure.Models;

import java.time.LocalDateTime;
import java.util.Date;

public class Country extends BaseModel{
    private int countryId;
    private String country;

    public Country(int countryId, String country, LocalDateTime createDate, String createdBy, LocalDateTime lastUpdate, String lastUpdateBy){
        super(createDate, createdBy, lastUpdate, lastUpdateBy);
        this.countryId = countryId;
        this.country = country;
    }

    public int getCountryId() { return this.countryId; }
    public String getCountry() { return this.country; }

    public void setCountryId(int countryId){
        this.countryId = countryId;
    }

    public void setCountry(String country){
        this.country = country;
    }
}
