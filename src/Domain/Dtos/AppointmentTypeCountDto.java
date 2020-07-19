package Domain.Dtos;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class AppointmentTypeCountDto {
    private final SimpleStringProperty type = new SimpleStringProperty();
    private final SimpleIntegerProperty count = new SimpleIntegerProperty();

    public AppointmentTypeCountDto(String type, int count){
        this.type.set(type);
        this.count.set(count);
    }

    public String getType(){
        return this.type.get();
    }

    public int getCount(){

        return this.count.get();
    }

    public void setType(String type){

        this.type.set(type);
    }

    public void setCount(int count){

        this.count.set(count);
    }

    public StringProperty getTypeProperty(){
        return this.type;
    }

    public IntegerProperty getCountProperty(){
        return this.count;
    }
}
