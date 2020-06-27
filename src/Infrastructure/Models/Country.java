package Infrastructure.Models;

import java.util.Date;

public class Country {
    private int countryId;
    private String country;
    private Date createDate;
    private String createdBy;
    private Date lastUpdate;
    private String lastUpdateBy;

    public Country(int countryId, String country, Date createDate, String createdBy, Date lastUpdate, String lastUpdateBy){
        this.countryId = countryId;
        this.country = country;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdateBy = lastUpdateBy;
    }

    public int getCountryId() { return this.countryId; }
    public String getCountry() { return this.country; }
    public Date getCreateDate() { return this.createDate; }
    public String getCreatedBy() { return this.createdBy; }
    public Date getLastUpdate() { return this.lastUpdate; }
    public String getLastUpdateBy() { return this.lastUpdateBy; }

    public void setCountryId(int countryId){
        this.countryId = countryId;
    }

    public void setCountry(String country){
        this.country = country;
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
