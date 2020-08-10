package Contracts.Interfaces.Services;

import Domain.Dtos.AppointmentDto;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface IAppointmentService {
    int saveAppointment(AppointmentDto appointmentDto) throws SQLException;
    ObservableList<AppointmentDto> getAppointmentsForUser(int userId) throws SQLException;
    AppointmentDto getAppointmentByAppointmentId(int appointmentId);
    int updateAppointment(AppointmentDto appointmentDto) throws SQLException;
    int deleteAppointment(int appointmentId) throws SQLException;
    ObservableList<AppointmentDto> getAppointmentsForCustomer(int customerId) throws SQLException;
    boolean checkForUpcomingAppointments() throws SQLException;
    ObservableList<AppointmentDto> searchAppointmentsByTitle(String searchText) throws SQLException;
}
