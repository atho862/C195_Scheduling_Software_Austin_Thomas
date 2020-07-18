package Infrastructure.Repositories;

import Contracts.Interfaces.Repositories.IAppointmentRepository;
import Infrastructure.DatabaseConnection;
import Infrastructure.Models.Appointment;
import com.mysql.jdbc.PreparedStatement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAccessor;

public class AppointmentRepository implements IAppointmentRepository {

    @Override
    public int getMaxId() throws SQLException {
        int maxId = 0;
        PreparedStatement preparedStatement = (PreparedStatement) DatabaseConnection.dbConnection.prepareStatement(
                "SELECT MAX(appointmentId) FROM appointment");

        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            maxId = resultSet.getInt(1);
        }

        return maxId + 1;
    }

    @Override
    public int insertAppointment(Appointment appointment) throws SQLException {
        try {
            int appointmentId = getMaxId();
            PreparedStatement statement = (PreparedStatement) DatabaseConnection.dbConnection.prepareStatement(
                    "INSERT INTO appointment (appointmentId, customerId, userId, title, description, location, contact, type, url, start, end, createDate, createdBy, lastUpdate, lastUpdateBy)" +
                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
            );

            statement.setInt(1, appointmentId);
            statement.setInt(2, appointment.getCustomerId());
            statement.setInt(3, appointment.getUserId());
            statement.setString(4, appointment.getTitle());
            statement.setString(5, appointment.getDescription());
            statement.setString(6, appointment.getLocation());
            statement.setString(7, appointment.getContact());
            statement.setString(8, appointment.getType());
            statement.setString(9, appointment.getUrl());
            statement.setTimestamp(10, Timestamp.valueOf(appointment.getStart()));
            statement.setTimestamp(11, Timestamp.valueOf(appointment.getEnd()));
            statement.setTimestamp(12, Timestamp.valueOf(appointment.getCreateDate()));
            statement.setString(13, appointment.getCreatedBy());
            statement.setTimestamp(14, Timestamp.valueOf(appointment.getLastUpdate()));
            statement.setString(15, appointment.getLastUpdatedBy());

            int affectedRows =  statement.executeUpdate();
            return affectedRows;
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
            return 0;
        }
    }

    @Override
    public Appointment getAppointmentById(int appointmentId) throws SQLException {

        PreparedStatement statement = (PreparedStatement) DatabaseConnection.dbConnection.prepareStatement(
                "SELECT * FROM appointment WHERE appointmentId = ?");
        statement.setInt(1, appointmentId);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
            int id = resultSet.getInt("appointmentId");
            int customerId = resultSet.getInt("customerId");
            int userId = resultSet.getInt("userId");
            String title = resultSet.getString("title");
            String description = resultSet.getString("description");
            String location = resultSet.getString("location");
            String contact = resultSet.getString("contact");
            String type = resultSet.getString("type");
            String url = resultSet.getString("url");
            LocalDateTime start = LocalDateTime.from((TemporalAccessor) resultSet.getDate("start"));
            LocalDateTime end = LocalDateTime.from((TemporalAccessor) resultSet.getDate("end"));
            LocalDateTime createDate = LocalDateTime.from((TemporalAccessor) resultSet.getDate("createDate"));
            String createdBy = resultSet.getString("createdBy");
            LocalDateTime lastUpdate = LocalDateTime.from((TemporalAccessor) resultSet.getDate("lastUpdate"));
            String lastUpdateBy = resultSet.getString("lastUpdateBy");

            Appointment appointment = new Appointment(id, customerId, userId, title, description, location, contact, type,
                    url, start, end, createDate, createdBy, lastUpdate, lastUpdateBy);

            return appointment;
        }

        return null;
    }

    @Override
    public ObservableList<Appointment> getAppointmentsByUserId(int userId) throws SQLException {
        ObservableList<Appointment> appointments = FXCollections.observableArrayList();
        LocalDateTime currentTime = LocalDateTime.now();
        PreparedStatement statement = (PreparedStatement) DatabaseConnection.dbConnection.prepareStatement(
                "SELECT * FROM appointment WHERE userId = ?");
        statement.setInt(1, userId);

        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            int id = resultSet.getInt("appointmentId");
            int customerId = resultSet.getInt("customerId");
            int uId = resultSet.getInt("userId");
            String title = resultSet.getString("title");
            String description = resultSet.getString("description");
            String location = resultSet.getString("location");
            String contact = resultSet.getString("contact");
            String type = resultSet.getString("type");
            String url = resultSet.getString("url");
            LocalDateTime start = resultSet.getTimestamp("start").toLocalDateTime();
            LocalDateTime end = resultSet.getTimestamp("end").toLocalDateTime();
            LocalDateTime createDate =  resultSet.getTimestamp("createDate").toLocalDateTime();
            String createBy = resultSet.getString("createdBy");
            LocalDateTime lastUpdate = resultSet.getTimestamp("lastUpdate").toLocalDateTime();
            String lastUpdateBy = resultSet.getString("lastUpdateBy");

            Appointment appointment = new Appointment(id, customerId, uId, title, description, location, contact, type,
                    url, start, end, createDate, createBy, lastUpdate, lastUpdateBy);

            appointments.add(appointment);
        }

        return appointments;
    }

    @Override
    public int updateAppointment(Appointment appointment) throws SQLException {

        PreparedStatement statement = (PreparedStatement) DatabaseConnection.dbConnection.prepareStatement(
                "UPDATE appointment SET title = ?, description = ?, location = ?, contact = ?, type = ?, url = ?, " +
                        "start = ?, end = ?, lastUpdate = ?, lastUpdateBy = ? WHERE appointmentId = ?");

        statement.setString(1, appointment.getTitle());
        statement.setString(2, appointment.getDescription());
        statement.setString(3, appointment.getLocation());
        statement.setString(4, appointment.getContact());
        statement.setString(5, appointment.getType());
        statement.setString(6, appointment.getUrl());
        statement.setTimestamp(7, Timestamp.valueOf(appointment.getStart()));
        statement.setTimestamp(8, Timestamp.valueOf(appointment.getEnd()));
        statement.setTimestamp(9, Timestamp.valueOf(appointment.getLastUpdate()));
        statement.setString(10, appointment.getLastUpdatedBy());
        statement.setInt(11, appointment.getAppointmentId());
        try {
            int updatedAppointments = statement.executeUpdate();
            return updatedAppointments;
        }
        catch (SQLException e){
            System.out.println(e);
            return 0;
        }
    }

    @Override
    public int deleteAppointment(int appointmentId) throws SQLException {
        PreparedStatement statement = (PreparedStatement) DatabaseConnection.dbConnection.prepareStatement(
                "DELETE FROM appointment WHERE appointmentId = ?");

        statement.setInt(1, appointmentId);
        try {
            int deletedAppointments = statement.executeUpdate();
            return deletedAppointments;
        }
        catch (SQLException e){
            System.out.println(e);
            return 0;
        }
    }

    @Override
    public ObservableList<Appointment> getAppointmentsByCustomerId(int customerId) throws SQLException {
        ObservableList<Appointment> appointments = FXCollections.observableArrayList();
        PreparedStatement statement = (PreparedStatement) DatabaseConnection.dbConnection.prepareStatement(
                "SELECT * FROM appointment WHERE customerId = ?");

        statement.setInt(1, customerId);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
            int appointmentId = resultSet.getInt("appointmentId");
            int userId = resultSet.getInt("userId");
            String title = resultSet.getString("title");
            String description = resultSet.getString("description");
            String contact = resultSet.getString("contact");
            String location = resultSet.getString("location");
            String type = resultSet.getString("type");
            String url = resultSet.getString("url");
            LocalDateTime start = resultSet.getTimestamp("start").toLocalDateTime();
            LocalDateTime end = resultSet.getTimestamp("end").toLocalDateTime();
            LocalDateTime createDate = resultSet.getTimestamp("createDate").toLocalDateTime();
            String createdBy = resultSet.getString("createdBy");
            LocalDateTime lastUpdate = resultSet.getTimestamp("lastUpdate").toLocalDateTime();
            String lastUpdateBy = resultSet.getString("lastUpdateBy");

            Appointment appointment = new Appointment(appointmentId, customerId, userId, title, description, location, contact,
                    type, url, start, end, createDate, createdBy, lastUpdate, lastUpdateBy);

            appointments.add(appointment);
        }

        return appointments;
    }
}
