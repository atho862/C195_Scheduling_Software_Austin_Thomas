package Contracts.Interfaces.Repositories;

import Domain.Dtos.AppointmentTypeCountDto;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface IAppointmentTypeCountRepository {

    ObservableList<AppointmentTypeCountDto> getAppointmentTypeCountsByMonth(int month) throws SQLException;
}
