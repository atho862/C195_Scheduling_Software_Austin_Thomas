package Domain.Services;

import Contracts.Interfaces.Repositories.IAppointmentRepository;
import Contracts.Interfaces.Repositories.ICustomerRepository;
import Contracts.Interfaces.Services.IAppointmentService;
import Contracts.Mappers.MapAppointmentDtoToAppointment;
import Contracts.Mappers.MapAppointmentToAppointmentDto;
import Contracts.Statics.AppointmentStatics;
import Contracts.Statics.UserStatics;
import Domain.Dtos.AppointmentDto;
import Domain.Helpers.AppointmentHelper;
import Infrastructure.Models.Appointment;
import Infrastructure.Repositories.AppointmentRepository;
import Infrastructure.Repositories.CustomerRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class AppointmentService implements IAppointmentService {

    IAppointmentRepository appointmentRepository = new AppointmentRepository();
    ICustomerRepository customerRepository = new CustomerRepository();

    @Override
    public int saveAppointment(AppointmentDto appointmentDto) throws SQLException {
        Appointment appointment = MapAppointmentDtoToAppointment.Map(appointmentDto);
        appointment.setCreateDate(AppointmentHelper.convertToUtc(LocalDateTime.now()));
        appointment.setLastUpdate(AppointmentHelper.convertToUtc(LocalDateTime.now()));
        appointment.setCreatedBy(UserStatics.getCurrentUserName());
        appointment.setLastUpdatedBy(UserStatics.getCurrentUserName());
        appointment.setCustomerId(customerRepository.getCustomerIdByCustomerName(appointmentDto.getCustomerName()));

        int affectedAppointments = appointmentRepository.insertAppointment(appointment);
        return affectedAppointments;
    }

    @Override
    public ObservableList<AppointmentDto> getAppointmentsForUser(int userId) throws SQLException {
        ObservableList<Appointment> appointments =  appointmentRepository.getAppointmentsByUserId(userId);
        ObservableList<AppointmentDto> appointmentDtos = FXCollections.observableArrayList();
        for (Appointment appointment : appointments
             ) {
            AppointmentDto appointmentDto = MapAppointmentToAppointmentDto.Map(appointment);
            appointmentDto.setCustomerName(customerRepository.getCustomerNameById(appointment.getCustomerId()));
            appointmentDtos.add(appointmentDto);
        }
        return appointmentDtos;
    }

    @Override
    public AppointmentDto getAppointmentByAppointmentId(int appointmentId) {
        return null;
    }

    @Override
    public int updateAppointment(AppointmentDto appointmentDto) throws SQLException {
        Appointment appointment = MapAppointmentDtoToAppointment.Map(appointmentDto);
        appointment.setLastUpdatedBy(UserStatics.getCurrentUserName());
        appointment.setLastUpdate(AppointmentHelper.convertToUtc(LocalDateTime.now()));
        int updatedAppointments = appointmentRepository.updateAppointment(appointment);
        return updatedAppointments;
    }

    @Override
    public int deleteAppointment(int appointmentId) throws SQLException {
        int deletedAppointments = appointmentRepository.deleteAppointment(appointmentId);
        return deletedAppointments;
    }

    @Override
    public ObservableList<AppointmentDto> getAppointmentsForCustomer(int customerId) throws SQLException {
        ObservableList<Appointment> appointments = appointmentRepository.getAppointmentsByCustomerId(customerId);
        ObservableList<AppointmentDto> appointmentDtos = FXCollections.observableArrayList();
        for (Appointment appointment : appointments
             ) {
            AppointmentDto appointmentDto = MapAppointmentToAppointmentDto.Map(appointment);
            appointmentDtos.add(appointmentDto);
        }

        return appointmentDtos;
    }

    @Override
    public boolean checkForUpcomingAppointments() throws SQLException {
        boolean hasUpcomingAppointment = false;
        ObservableList<Appointment> appointments = appointmentRepository.getAppointmentsByUserId(UserStatics.getCurrentUserId());

        for (Appointment appointment : appointments
             ) {
            appointment.setStart(AppointmentHelper.convertFromUtc(appointment.getStart()));
            if (LocalDateTime.now().isBefore(appointment.getStart()) && LocalDateTime.now().isAfter(appointment.getStart().minusMinutes(15))){
                hasUpcomingAppointment = true;
            }
        }

        return hasUpcomingAppointment;
    }
}
