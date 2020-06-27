package Infrastructure.Models;

import java.util.Date;

public class Appointment {
    private int appointmentId;
    private int customerId;
    private int userId;
    private String title;
    private String description;
    private String location;
    private String contact;
    private String type;
    private String url;
    private Date start;
    private Date end;
    private Date createDate;
    private String createdBy;
    private Date lastUpdate;
    private String lastUpdatedBy;

    public Appointment(int appointmentId, int customerId, int userId, String title, String description, String location,
                       String contact, String type, String url, Date start, Date end, Date createDate, String createdBy,
                       Date lastUpdate, String lastUpdatedBy) {
        this.appointmentId = appointmentId;
        this.customerId = customerId;
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.location = location;
        this.contact = contact;
        this.type = type;
        this.url = url;
        this.start = start;
        this.end = end;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public int getAppointmentId() { return this.appointmentId; }
    public int getCustomerId() { return this.customerId; }
    public int getUserId() { return this.userId; }
    public String getTitle() { return this.title; }
    public String getDescription() { return this.description; }
    public String getLocation() { return this.location; }
    public String getContact() { return this.contact; }
    public String getType() { return this.type; }
    public String getUrl() { return this.url; }
    public Date getStart() { return this.start; }
    public Date getEnd() { return this.end; }
    public Date getCreateDate() { return this.createDate; }
    public String getCreatedBy() { return this.createdBy; }
    public Date getLastUpdate() { return this.lastUpdate; }
    public String getLastUpdatedBy() { return this.lastUpdatedBy; }

    public void setAppointmentId(int appointmentId){
        this.appointmentId = appointmentId;
    }

    public void setCustomerId(int customerId){
        this.customerId = customerId;
    }

    public void setUserId(int userId){
        this.userId = userId;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public void setLocation(String location){
        this.location = location;
    }

    public void setContact(String contact){
        this.contact = contact;
    }

    public void setType(String type){
        this.type = type;
    }

    public void setUrl(String url){
        this.url = url;
    }

    public void setStart(Date start){
        this.start = start;
    }

    public void setEnd(Date end){
        this.end = end;
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

    public void setLastUpdatedBy(String lastUpdatedBy){
        this.lastUpdatedBy = lastUpdatedBy;
    }
}
