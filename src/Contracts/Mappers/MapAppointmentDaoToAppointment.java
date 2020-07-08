package Contracts.Mappers;

import Contracts.Statics.UserStatics;
import Domain.Daos.AppointmentDao;
import Infrastructure.Models.Appointment;

public class MapAppointmentDaoToAppointment {
    public static Appointment Map(AppointmentDao appointmentDao){
        Appointment appointment = new Appointment(appointmentDao.getAppointmentId(), 0, UserStatics.getCurrentUserId(),
                appointmentDao.getTitle(), appointmentDao.getDescription(), appointmentDao.getLocation(), appointmentDao.getContact(),
                appointmentDao.getType(), appointmentDao.getUrl(), appointmentDao.getStart(), appointmentDao.getEnd(),
                null, null, null,null);

        return appointment;
    }
}
