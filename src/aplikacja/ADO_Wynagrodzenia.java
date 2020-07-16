package aplikacja;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ADO_Wynagrodzenia {

    private int placa_brutto;
    private int premia;

    public int getPlaca_brutto() {
        return placa_brutto;
    }

    public void setPlaca_brutto(int placa_brutto) {
        this.placa_brutto = placa_brutto;
    }

    public int getPremia() {
        return premia;
    }

    public void setPremia(int premia) {
        this.premia = premia;
    }

    public ObservableList<ADO_Wynagrodzenia> getSalary(Connection conn, String id) {
        ObservableList<ADO_Wynagrodzenia> lista_plac = FXCollections.observableArrayList();
        String sql = "SELECT placa_brutto, premia from WYNAGRODZENIA where id_pracownika = " + id;
        Statement stmt;
        ResultSet rs;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                ADO_Wynagrodzenia wynagrodzenia = new ADO_Wynagrodzenia();
                wynagrodzenia.placa_brutto = rs.getInt(1);
                wynagrodzenia.premia = rs.getInt(2);
                lista_plac.add(wynagrodzenia);
            }
        } catch (SQLException exc) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errots with data access");
            alert.setContentText("Details: " + exc.getMessage());
            alert.showAndWait();
        }
        return lista_plac;
    }
}
