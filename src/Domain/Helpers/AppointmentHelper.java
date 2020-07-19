package Domain.Helpers;

import Contracts.Statics.AppointmentStatics;
import Contracts.Statics.UserStatics;
import Domain.Dtos.AppointmentDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class AppointmentHelper {
    public static LocalDateTime convertToUtc(LocalDateTime date){
        date = date.minusHours(UserStatics.getUserOffset());
        return date;
    }

    public static LocalDateTime convertFromUtc(LocalDateTime date){
        date = date.plusHours(UserStatics.getUserOffset());
        return date;
    }

    public static boolean isDuringBusinessHours(LocalDateTime dateTime){
        boolean isDuringBusinessHours = true;
        if (dateTime.getHour() < 9 || dateTime.getHour() > 17) {
                isDuringBusinessHours = false;
        }
        if (dateTime.getDayOfWeek() == DayOfWeek.SATURDAY || dateTime.getDayOfWeek() == DayOfWeek.SUNDAY){
            isDuringBusinessHours = false;
        }

        return isDuringBusinessHours;
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

    public static ObservableList<Integer> getDatesForThisWeek(LocalDate date){
        ObservableList<Integer> datesForThisWeek = FXCollections.observableArrayList();
        int currentDate = date.getDayOfYear();
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        if (dayOfWeek == DayOfWeek.MONDAY){
            datesForThisWeek.addAll(currentDate, currentDate + 1, currentDate + 2, currentDate + 3, currentDate + 4);
        }
        if (dayOfWeek == DayOfWeek.TUESDAY){
            datesForThisWeek.addAll(currentDate - 1, currentDate, currentDate + 1, currentDate + 2, currentDate + 3);
        }
        if (dayOfWeek == DayOfWeek.WEDNESDAY) {
            datesForThisWeek.addAll(currentDate - 2, currentDate - 1, currentDate, currentDate + 1, currentDate + 2);
        }
        if (dayOfWeek == DayOfWeek.THURSDAY) {
            datesForThisWeek.addAll(currentDate - 3, currentDate - 2, currentDate - 1, currentDate, currentDate + 1);
        }
        if (dayOfWeek == DayOfWeek.FRIDAY) {
            datesForThisWeek.addAll(currentDate - 4, currentDate - 3, currentDate - 2, currentDate - 1, currentDate);
        }
        if (dayOfWeek == DayOfWeek.SATURDAY){
            datesForThisWeek.addAll(currentDate + 2, currentDate + 3, currentDate + 4, currentDate + 5, currentDate + 6);
        }
        if (dayOfWeek == DayOfWeek.SUNDAY){
            datesForThisWeek.addAll(currentDate + 1, currentDate + 2, currentDate + 3, currentDate + 4, currentDate + 5);
        }

        return datesForThisWeek;
    }

    public static boolean checkForOverlappingAppointments(LocalDateTime start, LocalDateTime end, ObservableList<AppointmentDto> appointments){
        boolean isOverlapping = false;
        for (AppointmentDto appointment : appointments
             ) {
            if (start.isAfter(appointment.getStart()) && start.isBefore(appointment.getEnd())){
                isOverlapping = true;
            }
            if (end.isAfter(appointment.getStart()) && end.isBefore(appointment.getEnd())){
                isOverlapping = true;
            }
        }

        return isOverlapping;
    }
}
