package Domain.Helpers;

import Contracts.Statics.AppointmentStatics;
import Contracts.Statics.UserStatics;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class AppointmentHelper {
    public static LocalDateTime convertToUtc(LocalDateTime date){
        date = date.plusHours(UserStatics.getUserOffset());
        return date;
    }

    public static LocalDateTime convertFromUtc(LocalDateTime date){
        date = date.minusHours(UserStatics.getUserOffset());
        return date;
    }

    public static boolean isDuringBusinessHours(int hour){
        if (hour < 9 || hour > 17) {
                return false;
        }
        else {
                return true;
        }
    }

    public static ObservableList<String> setTypes(){
        ObservableList<String> appointmentTypes = FXCollections.observableArrayList();
        appointmentTypes.addAll(AppointmentStatics.initialConsultation, AppointmentStatics.productImplementation,
                AppointmentStatics.salesCall, AppointmentStatics.supportCall);

        return appointmentTypes;
    }

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

    public static LocalDateTime getLocalDateTimeForAppointment(LocalDate date, String hour, String minute){
        LocalDateTime localDateTime = LocalDateTime.of(date.getYear(), date.getMonth(), date.getDayOfMonth(),
                Integer.valueOf(hour), Integer.valueOf(minute));

        return localDateTime;
    }
}
