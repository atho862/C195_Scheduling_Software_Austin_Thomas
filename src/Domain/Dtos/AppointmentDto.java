package Domain.Dtos;

import java.time.LocalDateTime;

public class AppointmentDto {
    private int appointmentId;
    private String customerName;
    private String title;
    private String description;
    private String location;
    private String contact;
    private String type;
    private String url;
    private LocalDateTime start;
    private LocalDateTime end;

    public AppointmentDto(int appointmentId, String customerName, String title, String description, String location,
                          String contact, String type, String url, LocalDateTime start, LocalDateTime end){
        this.appointmentId = appointmentId;
        this.customerName = customerName;
        this.title = title;
        this.description = description;
        this.location = location;
        this.contact = contact;
        this.type = type;
        this.url = url;
        this.start = start;
        this.end = end;
    }

    public int getAppointmentId() { return this.appointmentId; }
    public String getCustomerName() { return this.customerName; }
    public String getTitle() { return this.title; }
    public String getDescription() { return this.description; }
    public String getLocation() { return this.location; }
    public String getContact() { return this.contact; }
    public String getType() { return this.type; }
    public String getUrl() { return this.url; }
    public LocalDateTime getStart() { return this.start; }
    public LocalDateTime getEnd() { return this.end; }

    public void setAppointmentId(int appointmentId){
        this.appointmentId = appointmentId;
    }

    public void setCustomerName(String customerName){
        this.customerName = customerName;
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

    public void setStart(LocalDateTime start){
        this.start = start;
    }

    public void setEnd(LocalDateTime end){
        this.end = end;
    }
}
