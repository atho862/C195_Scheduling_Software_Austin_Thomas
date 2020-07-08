package Domain.Services;

import Contracts.Interfaces.Repositories.IAppointmentRepository;
import Contracts.Interfaces.Repositories.ICustomerRepository;
import Contracts.Interfaces.Services.IAppointmentService;
import Contracts.Mappers.MapAppointmentDaoToAppointment;
import Contracts.Mappers.MapAppointmentToAppointmentDao;
import Contracts.Statics.UserStatics;
import Domain.Daos.AppointmentDao;
import Infrastructure.Models.Appointment;
import Infrastructure.Repositories.AppointmentRepository;
import Infrastructure.Repositories.CustomerRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Date;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDateTime;

public class AppointmentService implements IAppointmentService {

    IAppointmentRepository appointmentRepository = new AppointmentRepository();
    ICustomerRepository customerRepository = new CustomerRepository();

    @Override
    public int saveAppointment(AppointmentDao appointmentDao) throws SQLException {
        Appointment appointment = MapAppointmentDaoToAppointment.Map(appointmentDao);
        appointment.setCreateDate(LocalDateTime.now());
        appointment.setLastUpdate(LocalDateTime.now());
        appointment.setCreatedBy(UserStatics.getCurrentUserName());
        appointment.setLastUpdatedBy(UserStatics.getCurrentUserName());
        appointment.setCustomerId(customerRepository.getCustomerIdByCustomerName(appointmentDao.getCustomerName()));

        int affectedAppointments = appointmentRepository.insertAppointment(appointment);
        return affectedAppointments;
    }

    @Override
    public ObservableList<AppointmentDao> getAppointmentsForUser(int userId) throws SQLException {
        ObservableList<Appointment> appointments =  appointmentRepository.getAppointmentsByUserId(userId);
        ObservableList<AppointmentDao> appointmentDaos = FXCollections.observableArrayList();
        for (Appointment appointment : appointments
             ) {
            AppointmentDao appointmentDao = MapAppointmentToAppointmentDao.Map(appointment);
            appointmentDao.setCustomerName(customerRepository.getCustomerNameById(appointment.getCustomerId()));
            appointmentDaos.add(appointmentDao);
        }
        return appointmentDaos;
    }

    @Override
    public AppointmentDao getAppointmentByAppointmentId(int appointmentId) {
        return null;
    }

    @Override
    public void updateAppointment(AppointmentDao appointmentDao) {

    }

    @Override
    public boolean deleteAppointment(int appointmentId) {
        return false;
    }
}
