package Contracts.Interfaces.Services;

import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface ICustomerService {
    ObservableList<String> getCustomerNames() throws SQLException;
}
