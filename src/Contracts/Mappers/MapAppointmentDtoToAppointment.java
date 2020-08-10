package Contracts.Mappers;

import Contracts.Statics.UserStatics;
import Domain.Dtos.AppointmentDto;
import Domain.Helpers.AppointmentHelper;
import Infrastructure.Models.Appointment;

public class MapAppointmentDtoToAppointment extends BaseMapper<Appointment, AppointmentDto>{

    public Appointment Map(AppointmentDto appointmentDto){
        Appointment appointment = new Appointment(appointmentDto.getAppointmentId(), 0, UserStatics.getCurrentUserId(),
                appointmentDto.getTitle(), appointmentDto.getDescription(), appointmentDto.getLocation(), appointmentDto.getContact(),
                appointmentDto.getType(), appointmentDto.getUrl(), AppointmentHelper.convertToUtc(appointmentDto.getStart()),
                AppointmentHelper.convertToUtc(appointmentDto.getEnd()), null, null, null,null);

        return appointment;
    }
}
