package aplikacja;
import javafx.scene.control.Alert;
import java.sql.*;

public class Data_Base_Connection {

    private static Connection conn;

    public static Connection getConnection() {
        String DB_URL = "jdbc:oracle:thin:@ora3.elka.pw.edu.pl:1521:ora3inf";
        String DB_USER = "mrytel1";
        String DB_PASS = "mrytel1";
        try {
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("You are connected to database...");
            alert.showAndWait();
        } catch (SQLException exc) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error to database connection");
            alert.setContentText("Details: " + exc.getMessage());
            alert.showAndWait();
        }
        return conn;
    }

    public static Connection getNewConnrction() {
        String DB_URL = "jdbc:oracle:thin:@ora3.elka.pw.edu.pl:1521:ora3inf";
        String DB_USER = "mrytel1";
        String DB_PASS = "mrytel1";
        try {
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        } catch (SQLException exc) {
        }
        return conn;

    }

}
