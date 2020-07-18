package Contracts.Interfaces.Repositories;

import Infrastructure.Models.Appointment;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface IAppointmentRepository {
    int insertAppointment(Appointment appointment) throws SQLException;
    Appointment getAppointmentById(int appointmentId) throws SQLException;
    ObservableList<Appointment> getAppointmentsByUserId(int userId) throws SQLException;
    int updateAppointment(Appointment appointment) throws SQLException;
    int deleteAppointment(int addressId) throws SQLException;
    int getMaxId() throws SQLException;
    ObservableList<Appointment> getAppointmentsByCustomerId(int customerId) throws SQLException;
}
