package Contracts.Interfaces.Repositories;

import Infrastructure.Models.Appointment;

public interface IAppointmentRepository {
    void insertAppointment(Appointment appointment);
    Appointment getAppointmentById(int addressId);
    void updateAppointment(Appointment appointment);
    void deleteAppointment(int addressId);
}
