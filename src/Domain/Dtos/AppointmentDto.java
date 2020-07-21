package Domain.Dtos;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDateTime;

public class AppointmentDto {
    private int appointmentId;
    private final SimpleStringProperty customerName = new SimpleStringProperty();
    private final SimpleStringProperty title = new SimpleStringProperty();
    private final SimpleStringProperty description = new SimpleStringProperty();
    private final SimpleStringProperty location = new SimpleStringProperty();
    private final SimpleStringProperty contact = new SimpleStringProperty();
    private final SimpleStringProperty type = new SimpleStringProperty();
    private final SimpleStringProperty url = new SimpleStringProperty();
    private LocalDateTime start;
    private LocalDateTime end;

    public AppointmentDto(int appointmentId, String customerName, String title, String description, String location,
                          String contact, String type, String url, LocalDateTime start, LocalDateTime end){
        this.appointmentId = appointmentId;
        this.customerName.set(customerName);
        this.title.set(title);
        this.description.set(description);
        this.location.set(location);
        this.contact.set(contact);
        this.type.set(type);
        this.url.set(url);
        this.start = start;
        this.end = end;
    }

    public int getAppointmentId() { return this.appointmentId; }
    public String getCustomerName() { return this.customerName.get(); }
    public String getTitle() { return this.title.get(); }
    public String getDescription() { return this.description.get(); }
    public String getLocation() { return this.location.get(); }
    public String getContact() { return this.contact.get(); }
    public String getType() { return this.type.get(); }
    public String getUrl() { return this.url.get(); }
    public LocalDateTime getStart() { return this.start; }
    public LocalDateTime getEnd() { return this.end; }

    public void setAppointmentId(int appointmentId){
        this.appointmentId = appointmentId;
    }

    public void setCustomerName(String customerName){
        this.customerName.set(customerName);
    }

    public void setTitle(String title){
        this.title.set(title);
    }

    public void setDescription(String description){
        this.description.set(description);
    }

    public void setLocation(String location){
        this.location.set(location);
    }

    public void setContact(String contact){
        this.contact.set(contact);
    }

    public void setType(String type){
        this.type.set(type);
    }

    public void setUrl(String url){
        this.url.set(url);
    }

    public void setStart(LocalDateTime start){
        this.start =  start;
    }

    public void setEnd(LocalDateTime end){
        this.end = end;
    }

    public StringProperty getTitleProperty(){
        return this.title;
    }

    public StringProperty getDescriptionProperty(){
        return this.description;
    }

    public StringProperty getCustomerNameProperty(){
        return this.customerName;
    }

    public StringProperty getLocationProperty(){
        return this.location;
    }

    public StringProperty getContactProperty(){
        return this.contact;
    }

    public StringProperty getTypeProperty(){
        return this.type;
    }

    public StringProperty getUrlProperty(){
        return this.url;
    }
}
