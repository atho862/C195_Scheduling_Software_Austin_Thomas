package Contracts.Interfaces.Services;

import Domain.Dtos.LoginDto;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.sql.SQLException;

public interface ILoginService {
    boolean login(LoginDto loginDto) throws SQLException, IOException;
    void logout(ActionEvent actionEvent) throws IOException;
}
