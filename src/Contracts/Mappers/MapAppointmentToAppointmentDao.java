package Contracts.Mappers;

import Domain.Daos.AppointmentDao;
import Infrastructure.Models.Appointment;

public class MapAppointmentToAppointmentDao {

    public static AppointmentDao Map(Appointment appointment){
        AppointmentDao appointmentDao = new AppointmentDao(appointment.getAppointmentId(), null, appointment.getTitle(),
                appointment.getDescription(), appointment.getLocation(), appointment.getContact(), appointment.getType(),
                appointment.getUrl(), appointment.getStart(), appointment.getEnd());

        return appointmentDao;
    }
}
