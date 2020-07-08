package Contracts.Interfaces.Services;

import Domain.Daos.LoginDao;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.sql.SQLException;

public interface ILoginService {
    boolean login(LoginDao loginDao) throws SQLException;
    void logout(ActionEvent actionEvent) throws IOException;
}
