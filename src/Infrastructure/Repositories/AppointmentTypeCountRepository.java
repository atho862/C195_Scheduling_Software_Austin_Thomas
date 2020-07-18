package Infrastructure.Repositories;

import Contracts.Interfaces.Repositories.IAppointmentTypeCountRepository;
import Domain.Dtos.AppointmentTypeCountDto;
import Infrastructure.DatabaseConnection;
import com.mysql.jdbc.PreparedStatement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AppointmentTypeCountRepository implements IAppointmentTypeCountRepository {

    @Override
    public ObservableList<AppointmentTypeCountDto> getAppointmentTypeCountsByMonth(int month) throws SQLException {
        ObservableList<AppointmentTypeCountDto> appointmentTypeCounts = FXCollections.observableArrayList();
        PreparedStatement statement = (PreparedStatement) DatabaseConnection.dbConnection.prepareStatement(
                "SELECT type, COUNT(*) AS count FROM appointment WHERE MONTH(start) = ?");

        statement.setInt(1, month);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
            String type = resultSet.getString("type");
            int count = resultSet.getInt("count");

            AppointmentTypeCountDto appointmentTypeCount = new AppointmentTypeCountDto(type, count);
            appointmentTypeCounts.add(appointmentTypeCount);
        }

        return appointmentTypeCounts;
    }
}
