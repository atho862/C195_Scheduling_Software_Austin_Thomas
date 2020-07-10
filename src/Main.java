import Contracts.Statics.UserStatics;
import Infrastructure.DatabaseConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/Views/Login/Login.fxml"));
        primaryStage.setTitle("The Scheduler");
        primaryStage.setScene(new Scene(root, 1000, 800));
        primaryStage.show();
    }


    public static void main(String[] args) {
        int offsetHours = (ZonedDateTime.now().getOffset().getTotalSeconds() / 60) / 60;
        UserStatics.setUserOffset(offsetHours);
        DatabaseConnection.connectToDatabase();
        launch(args);
    }
}
