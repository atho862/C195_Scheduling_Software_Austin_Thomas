package Contracts.Interfaces.Services;

import Domain.Dtos.AppointmentDto;
import Domain.Dtos.AppointmentTypeCountDto;
import javafx.collections.ObservableList;

import java.time.Month;

public interface IReportsService {
    void exportAppointmentsByCustomerReportToExcel(String customerName, ObservableList<AppointmentDto> appointmentDtos);
    void exportAppointmentTypeByMonthToExcel(ObservableList<AppointmentTypeCountDto> appointmentTypeCountDtos, Month month);
    void exportScheduleByConsultantToExcel(String consultantName, ObservableList<AppointmentDto> appointments);
}
