package aplikacja;

import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ADO_Stanowiska {

    private int id_stanowiska;
    private String nazwa;
    private String opis;
    private int id_placowki;

    public int getId_stanowiska() {
        return id_stanowiska;
    }

    public void setId_stanowiska(int id_stanowiska) {
        this.id_stanowiska = id_stanowiska;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public int getId_placowki() {
        return id_placowki;
    }

    public void setId_placowki(int id_placowki) {
        this.id_placowki = id_placowki;
    }

    public String getNazwaStanowiska(Connection conn, String id) {
        String sql = "SELECT nazwa from STANOWISKA where id_stanowiska = " + id;
        Statement stmt;
        ResultSet rs;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                nazwa=rs.getString(1);
            }
        } catch (SQLException exc) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errots with data access");
            alert.setContentText("Details: " + exc.getMessage());
            alert.showAndWait();
        }
        return nazwa;
    }
}
