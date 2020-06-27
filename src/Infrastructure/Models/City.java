package Infrastructure.Models;

import java.util.Date;

public class City {
    private int cityId;
    private String city;
    private int countryId;
    private Date createDate;
    private String createdBy;
    private Date lastUpdate;
    private String lastUpdateBy;

    public City(int cityId, String city, int countryId, Date createDate, String createdBy, Date lastUpdate, String lastUpdateBy){
        this.cityId = cityId;
        this.city = city;
        this.countryId = countryId;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdateBy = lastUpdateBy;
    }

    public int getCityId() { return this.cityId; }
    public String getCity() { return this.city; }
    public int getCountryId() { return this.countryId; }
    public Date getCreateDate() { return this.createDate; }
    public String getCreatedBy() { return this.createdBy; }
    public Date getLastUpdate() { return this.lastUpdate; }
    public String getLastUpdateBy() { return this.lastUpdateBy; }

    public void setCityId(int cityId){
        this.cityId = cityId;
    }

    public void setCity(String city){
        this.city = city;
    }

    public void setCountryId(int countryId){
        this.countryId = countryId;
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
