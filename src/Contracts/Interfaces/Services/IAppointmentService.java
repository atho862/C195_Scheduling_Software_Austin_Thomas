package Contracts.Interfaces.Services;

import Domain.Daos.AppointmentDao;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface IAppointmentService {
    int saveAppointment(AppointmentDao appointmentDao) throws SQLException;
    ObservableList<AppointmentDao> getAppointmentsForUser(int userId) throws SQLException;
    AppointmentDao getAppointmentByAppointmentId(int appointmentId);
    int updateAppointment(AppointmentDao appointmentDao) throws SQLException;
    int deleteAppointment(int appointmentId) throws SQLException;
}
