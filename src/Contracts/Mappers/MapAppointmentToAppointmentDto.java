package Contracts.Mappers;

import Domain.Dtos.AppointmentDto;
import Domain.Helpers.AppointmentHelper;
import Infrastructure.Models.Appointment;

public class MapAppointmentToAppointmentDto {

    public static AppointmentDto Map(Appointment appointment){
        AppointmentDto appointmentDto = new AppointmentDto(appointment.getAppointmentId(), null, appointment.getTitle(),
                appointment.getDescription(), appointment.getLocation(), appointment.getContact(), appointment.getType(),
                appointment.getUrl(), AppointmentHelper.convertFromUtc(appointment.getStart()),
                AppointmentHelper.convertFromUtc(appointment.getEnd()));

        return appointmentDto;
    }
}
