package aplikacja;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.sql.*;
import java.time.LocalDate;

public class ADO_Pracownicy {
    private int id_pracownika;
    private String imie;
    private String nazwisko;
    private String drugie_imie;
    private long nr_pesel;
    private String data_urodzenia;
    private String miasto;
    private String ulica;
    private int nr_budynku;
    private int nr_lokalu;
    private String plec;
    private String kod_pocztowy;
    private String stopien_naukowy;
    private String login;
    private String haslo;
    private Object[] userInfo = new Object[4];
    private Object[] userData = new Object[15];
    private String[] columnName = new String[15];
    private ObservableList<ADO_Pracownicy> lista = FXCollections.observableArrayList();
    private boolean isPasCorr;
    private int id_placowki;

    private boolean pasAndLogCheck;

    public int getId_pracownika() {
        return id_pracownika;
    }

    public void setId_pracownika(int id_pracownika) {
        this.id_pracownika = id_pracownika;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getDrugie_imie() {
        return drugie_imie;
    }

    public void setDrugie_imie(String drugie_imie) {
        this.drugie_imie = drugie_imie;
    }

    public long getNr_pesel() {
        return nr_pesel;
    }

    public void setNr_pesel(int nr_pesel) {
        this.nr_pesel = nr_pesel;
    }

    public String getData_urodzenia() {
        return data_urodzenia;
    }

    public void setData_urodzenia(String data_urodzenia) {
        this.data_urodzenia = data_urodzenia;
    }

    public String getMiasto() {
        return miasto;
    }

    public void setMiasto(String miasto) {
        this.miasto = miasto;
    }

    public String getUlica() {
        return ulica;
    }

    public void setUlica(String ulica) {
        this.ulica = ulica;
    }

    public int getNr_budynku() {
        return nr_budynku;
    }

    public void setNr_budynku(int nr_budynku) {
        this.nr_budynku = nr_budynku;
    }

    public int getNr_lokalu() {
        return nr_lokalu;
    }

    public void setNr_lokalu(int nr_lokalu) {
        this.nr_lokalu = nr_lokalu;
    }

    public String getKod_pocztowy() {
        return kod_pocztowy;
    }

    public void setKod_pocztowy(String kod_pocztowy) {
        this.kod_pocztowy = kod_pocztowy;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getHaslo() {
        return haslo;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }

    public String getPlec() {
        return plec;
    }

    public void setPlec(String plec) {
        this.plec = plec;
    }

    public String getStopien_naukowy() {
        return stopien_naukowy;
    }

    public void setStopien_naukowy(String stopien_naukowy) {
        this.stopien_naukowy = stopien_naukowy;
    }

    public ObservableList<ADO_Pracownicy> getAll(Connection conn) {
        ObservableList<ADO_Pracownicy> lista_pracownikow = FXCollections.observableArrayList();
        String sql = "SELECT id_pracownika,imie,nazwisko,drugie_imie, pesel, data_urodzenia, miasto, ulica, nr_budynku, nr_lokalu, kod_pocztowy, login, haslo from PRACOWNICY order by id_pracownika";
        Statement stmt;
        ResultSet rs;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                ADO_Pracownicy pracownicy = new ADO_Pracownicy();
                pracownicy.id_pracownika = rs.getInt(1);
                pracownicy.imie = rs.getString(2);
                pracownicy.nazwisko = rs.getString(3);
                pracownicy.drugie_imie = rs.getString(4);
                pracownicy.nr_pesel = rs.getLong(5);
                pracownicy.data_urodzenia = rs.getString(6);
                pracownicy.miasto = rs.getString(7);
                pracownicy.ulica = rs.getString(8);
                pracownicy.nr_budynku = rs.getInt(9);
                pracownicy.nr_lokalu = rs.getInt(10);
                pracownicy.kod_pocztowy = rs.getString(11);
                pracownicy.login = rs.getString(12);
                pracownicy.haslo = rs.getString(13);
                lista_pracownikow.add(pracownicy);
            }
        } catch (SQLException exc) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errots with data access");
            alert.setContentText("Details: " + exc.getMessage());
            alert.showAndWait();
        }
        return lista_pracownikow;
    }

    public Object[] getLogAndPass(Connection conn, String log, String pass) {
        ObservableList<ADO_Pracownicy> lista_pracownikow = FXCollections.observableArrayList();
        String sql = "SELECT id_pracownika, imie, nazwisko, login, haslo from PRACOWNICY order by id_pracownika";
        Statement stmt;
        ResultSet rs;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                ADO_Pracownicy pracownicy = new ADO_Pracownicy();
                pracownicy.id_pracownika = rs.getInt(1);
                pracownicy.imie = rs.getString(2);
                pracownicy.nazwisko = rs.getString(3);
                pracownicy.login = rs.getString(4);
                pracownicy.haslo = rs.getString(5);
                lista_pracownikow.add(pracownicy);
                if ((pracownicy.login).equals(log) && (pracownicy.haslo).equals(pass)) {
                    pasAndLogCheck = true;
                    userInfo[0] = "1";
                    userInfo[1] = pracownicy.id_pracownika;
                    userInfo[2] = pracownicy.imie;
                    userInfo[3] = pracownicy.nazwisko;
                    break;
                } else {
                    pasAndLogCheck = false;
                    userInfo[0] = "0";
                }
                lista_pracownikow.remove(0);
            }
        } catch (SQLException exc) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errots with data access");
            alert.setContentText("Details: " + exc.getMessage());
            alert.showAndWait();
        }
        return userInfo;
    }

    public Object[] getUserData(Connection conn, String id) {
        String sql = "SELECT id_pracownika,imie,nazwisko,drugie_imie, pesel, data_urodzenia, miasto, ulica, nr_budynku, nr_lokalu, plec, kod_pocztowy, stopien_naukowy, login, haslo from PRACOWNICY where id_pracownika = " + id;
        Statement stmt;
        ResultSet rs;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                ADO_Pracownicy pracownicy = new ADO_Pracownicy();
                userData[0] = rs.getInt(1);
                userData[1] = rs.getString(2);
                userData[2] = rs.getString(3);
                userData[3] = rs.getString(4);
                userData[4] = rs.getLong(5);
                userData[5] = rs.getString(6);
                userData[6] = rs.getString(7);
                userData[7] = rs.getString(8);
                userData[8] = rs.getInt(9);
                userData[9] = rs.getInt(10);
                if (rs.getString(11).equals("M")) {
                    userData[10] = "Mężczyzna";
                } else if (rs.getString(11).equals("K")) {
                    userData[10] = "Kobieta";
                }
                userData[11] = rs.getString(12);
                if (rs.getString(13).equals("L")) {
                    userData[12] = "Licencjat";
                } else if (rs.getString(13).equals("I")) {
                    userData[12] = "Inżynier";
                } else if (rs.getString(13).equals("M")) {
                    userData[12] = "Magister";
                } else if (rs.getString(13).equals("D")) {
                    userData[12] = "Doktor";
                } else if (rs.getString(13).equals("P")) {
                    userData[12] = "Profesor";
                }
                userData[13] = rs.getString(14);
                userData[14] = rs.getString(15);
                lista.add(pracownicy);
            }
        } catch (SQLException exc) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errots with data access");
            alert.setContentText("Details: " + exc.getMessage());
            alert.showAndWait();
        }
        return userData;
    }

    public String[] getColumnName(Connection conn) {
        String sql = "SELECT id_pracownika,imie,nazwisko,drugie_imie, pesel, data_urodzenia, miasto, ulica, nr_budynku, nr_lokalu, plec, kod_pocztowy, stopien_naukowy, login, haslo from PRACOWNICY order by id_pracownika ";
        Statement stmt;
        ResultSet rs;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            ResultSetMetaData rsmd = rs.getMetaData();
            while (rs.next()) {
                columnName[0] = rsmd.getColumnName(1);
                columnName[1] = rsmd.getColumnName(2);
                columnName[2] = rsmd.getColumnName(3);
                columnName[3] = rsmd.getColumnName(4);
                columnName[4] = rsmd.getColumnName(5);
                columnName[5] = rsmd.getColumnName(6);
                columnName[6] = rsmd.getColumnName(7);
                columnName[7] = rsmd.getColumnName(8);
                columnName[8] = rsmd.getColumnName(9);
                columnName[9] = rsmd.getColumnName(10);
                columnName[10] = rsmd.getColumnName(11);
                columnName[11] = rsmd.getColumnName(12);
                columnName[12] = rsmd.getColumnName(13);
                columnName[13] = rsmd.getColumnName(14);
                columnName[14] = rsmd.getColumnName(15);
            }
        } catch (SQLException exc) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errots with data access");
            alert.setContentText("Details: " + exc.getMessage());
            alert.showAndWait();
        }
        return columnName;
    }

    public boolean setPassword(Connection conn, String id, String oldpass, String newpass, String confirmpass) {
        String sql = "SELECT haslo from PRACOWNICY where id_pracownika = " + id;
        String newsql = "UPDATE PRACOWNICY SET haslo = " + "'" + newpass + "'" + " where id_pracownika = " + id;
        Statement stmt;
        ResultSet rs;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                haslo = rs.getString(1);
            }
            if (haslo.equals(oldpass) && newpass.equals(confirmpass)) {
                stmt.executeUpdate(newsql);
                isPasCorr = true;
            } else {
                isPasCorr = false;
            }
        } catch (SQLException exc) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errots with data access");
            alert.setContentText("Details: " + exc.getMessage());
            alert.showAndWait();
        }
        return isPasCorr;
    }

    public ObservableList<ADO_Pracownicy> getFullInfo(Connection conn) {
        ObservableList<ADO_Pracownicy> lista_pracownikow = FXCollections.observableArrayList();
        String sql = "SELECT id_pracownika,imie,nazwisko,drugie_imie, pesel, data_urodzenia, miasto, ulica, nr_budynku, nr_lokalu, plec, kod_pocztowy, stopien_naukowy, login from PRACOWNICY order by id_pracownika";
        Statement stmt;
        ResultSet rs;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                ADO_Pracownicy pracownicy = new ADO_Pracownicy();
                pracownicy.id_pracownika = rs.getInt(1);
                pracownicy.imie = rs.getString(2);
                pracownicy.nazwisko = rs.getString(3);
                pracownicy.drugie_imie = rs.getString(4);
                pracownicy.nr_pesel = rs.getLong(5);
                pracownicy.data_urodzenia = rs.getString(6);
                pracownicy.miasto = rs.getString(7);
                pracownicy.ulica = rs.getString(8);
                pracownicy.nr_budynku = rs.getInt(9);
                pracownicy.nr_lokalu = rs.getInt(10);
                if (rs.getString(11).equals("M")) {
                    pracownicy.plec = "Mężczyzna";
                } else if (rs.getString(11).equals("K")) {
                    pracownicy.plec = "Kobieta";
                }
                pracownicy.kod_pocztowy = rs.getString(12);
                if (rs.getString(13).equals("L")) {
                    pracownicy.stopien_naukowy = "Licencjat";
                } else if (rs.getString(13).equals("I")) {
                    pracownicy.stopien_naukowy = "Inżynier";
                } else if (rs.getString(13).equals("M")) {
                    pracownicy.stopien_naukowy = "Magister";
                } else if (rs.getString(13).equals("D")) {
                    pracownicy.stopien_naukowy = "Doktor";
                } else if (rs.getString(13).equals("P")) {
                    pracownicy.stopien_naukowy = "Profesor";
                }
                pracownicy.login = rs.getString(14);
                lista_pracownikow.add(pracownicy);
            }
        } catch (SQLException exc) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errots with data access");
            alert.setContentText("Details: " + exc.getMessage());
            alert.showAndWait();
        }
        return lista_pracownikow;
    }

    public void deleteRekord(Connection con, String id) {
        String sql = "DELETE from WYNAGRODZENIA where id_pracownika = " + id + " AND EXISTS(SELECT * from WYNAGRODZENIA where id_pracownika = " + id + ")";
        String sql2 = " DELETE from PRACOWNICY where id_pracownika = " + id;
        Statement stmt;
        try {
            stmt = con.createStatement();
            stmt.executeUpdate(sql);
            stmt.executeUpdate(sql2);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ok");
            alert.setContentText("Record deleted");
            alert.showAndWait();
        } catch (SQLException exc) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errots with data access");
            alert.setContentText("Details: " + exc.getMessage());
            alert.showAndWait();
        }

    }

    public void addRecord(Connection conn, int id, String name, String surname, String second_name, String pesel, LocalDate birth_date, String city, String street, String house_number, String apartment, String sex, String postcode, String degree, String login, String pass, String id_facility) {
        String sql = "INSERT INTO PRACOWNICY VALUES ('" + id + "','" + name + "','" + surname + "','" + second_name + "','" + pesel + "', " + "TO_DATE('" + birth_date + "', 'YYYY/MM/DD')" + ",'" + city + "','" + street + "','" + house_number + "','" + apartment + "','" + sex + "','" + postcode + "','" + degree + "','" + login + "','" + pass + "','" + id_facility + "')";
        Statement stmt;
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ok");
            alert.setContentText("Record added");
            alert.showAndWait();
        } catch (SQLException exc) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errots with data access");
            alert.setContentText("Details: " + exc.getMessage());
            alert.showAndWait();
        }
    }

    public void modifyRec(Connection conn, int id, String name, String surname, String second_name, String pesel, LocalDate birth_date, String city, String street, String house_number, String apartment, String sex, String postcode, String degree, String login, String pass, String id_facility) {
        String sql = "UPDATE PRACOWNICY SET imie = '" + name + "', nazwisko = '" + surname + "', drugie_imie = '" + second_name + "', pesel = '" + pesel + "', data_urodzenia = " + "TO_DATE('" + birth_date + "', 'YYYY/MM/DD')" + ", miasto = '" + city + "', ulica = '" + street + "', nr_budynku = '" + house_number + "', nr_lokalu = '" + apartment + "', plec = '" + sex + "', kod_pocztowy = '" + postcode + "', stopien_naukowy = '" + degree + "', login = '" + login + "', haslo = '" + pass + "', id_placowki = '" + id_facility + "' where id_pracownika = " + id;
        Statement stmt;
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ok");
            alert.setContentText("Record modified");
            alert.showAndWait();
        } catch (SQLException exc) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errots with data access");
            alert.setContentText("Details: " + exc.getMessage());
            alert.showAndWait();
        }
    }

    public int getIdPlacowki(Connection connection, String id){
        String sql = "SELECT id_placowki from PRACOWNICY where id_pracownika = " + id;
        Statement stmt;
        ResultSet rs;
        try {
            stmt = connection.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                id_placowki = rs.getInt(1);
            }
        } catch (SQLException exc) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errots with data access");
            alert.setContentText("Details: " + exc.getMessage());
            alert.showAndWait();
        }
        return id_placowki;
    }

    public String getIdNumber() {
        return this.userInfo[1].toString();
    }

}
