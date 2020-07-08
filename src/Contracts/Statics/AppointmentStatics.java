package Contracts.Statics;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AppointmentStatics {

    public static String initialConsultation = "Initial Consultation";
    public static String salesCall = "Sales Call";
    public static String productImplementation = "Product Implementation";
    public static String supportCall = "Support Call";

    public static ObservableList<String> setHours(){
        ObservableList<String> hours = FXCollections.observableArrayList();
        hours.addAll("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16","17",
                "18", "19", "20", "21", "22", "23");

        return hours;
    }

    public static ObservableList<String> setMinutes(){
        ObservableList<String> minutes = FXCollections.observableArrayList();
        minutes.addAll("00", "15", "30", "45");

        return minutes;
    }
}
