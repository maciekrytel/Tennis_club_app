package controllers;

import aplikacja.ADO_Pracownicy;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Workers_Window_Controller {

    private Connection conn;
    private ObservableList<ADO_Pracownicy> pracownicy = FXCollections.observableArrayList();
    private Object [] pracownikInfo;
    private Admin_Controller adminController;
    private Salary_Controller salary_controller;
    private Warning_Controller warning_controller;
    private New_Worker_Controller new_worker_controller;
    private ObservableList person;
    private int id;
    private ADO_Pracownicy ado_pracownicy;
    private int how_many_records;
    private int id_to_add;
    private int id_placowki;
    private int id_rek;

    @FXML
    Label label_pracownicy;

    @FXML
    private TableView<ADO_Pracownicy> table_workers;

    @FXML
    private TableColumn<ADO_Pracownicy, Integer> column_id;

    @FXML
    private TableColumn<ADO_Pracownicy, String>  column_name;

    @FXML
    private TableColumn<ADO_Pracownicy, String>  column_surname;

    @FXML
    private TableColumn<ADO_Pracownicy, String>  column_second_name;

    @FXML
    private TableColumn<ADO_Pracownicy, Long>  column_pesel;

    @FXML
    private TableColumn<ADO_Pracownicy, String>  column_birth;

    @FXML
    private TableColumn<ADO_Pracownicy, String>  column_city;

    @FXML
    private TableColumn<ADO_Pracownicy, String>  column_street;

    @FXML
    private TableColumn<ADO_Pracownicy, Integer>  column_nr_house;

    @FXML
    private TableColumn<ADO_Pracownicy, Integer>  column_apartment;

    @FXML
    private TableColumn<ADO_Pracownicy, String>  column_sex;

    @FXML
    private TableColumn<ADO_Pracownicy, String>  column_post;

    @FXML
    private TableColumn<ADO_Pracownicy, String>  column_degree;

    @FXML
    private TableColumn<ADO_Pracownicy, String>  column_login;

    public void setTable_workers(ADO_Pracownicy ado_pracownicy){
        conn = aplikacja.Data_Base_Connection.getNewConnrction();
        pracownicy = ado_pracownicy.getFullInfo(conn);
        column_id.setCellValueFactory(new PropertyValueFactory<>("id_pracownika"));
        column_name.setCellValueFactory(new PropertyValueFactory<>("imie"));
        column_surname.setCellValueFactory(new PropertyValueFactory<>("nazwisko"));
        column_second_name.setCellValueFactory(new PropertyValueFactory<>("drugie_imie"));
        column_pesel.setCellValueFactory(new PropertyValueFactory<>("nr_pesel"));
        column_birth.setCellValueFactory(new PropertyValueFactory<>("data_urodzenia"));
        column_city.setCellValueFactory(new PropertyValueFactory<>("miasto"));
        column_street.setCellValueFactory(new PropertyValueFactory<>("ulica"));
        column_nr_house.setCellValueFactory(new PropertyValueFactory<>("nr_budynku"));
        column_apartment.setCellValueFactory(new PropertyValueFactory<>("nr_lokalu"));
        column_sex.setCellValueFactory(new PropertyValueFactory<>("plec"));
        column_post.setCellValueFactory(new PropertyValueFactory<>("kod_pocztowy"));
        column_degree.setCellValueFactory(new PropertyValueFactory<>("stopien_naukowy"));
        column_login.setCellValueFactory(new PropertyValueFactory<>("login"));

        table_workers.setItems(pracownicy);
    }

    public void deleteSelectedRec(){
        conn = aplikacja.Data_Base_Connection.getNewConnrction();
        id = table_workers.getSelectionModel().getSelectedIndex();
        if(id != -1) {
            adminController.getAdo_pracownicy().deleteRekord(conn, column_id.getCellData(id).toString());
        }
    }

    @FXML
    public void button_salaries(ActionEvent actionEvent) throws Exception {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../fxml/salary.fxml"));
        Parent root1 = (Parent) loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.show();

        Salary_Controller salary_controller = loader.getController();
        salary_controller.setSalController(this);

        id = table_workers.getSelectionModel().getSelectedIndex();
        if(id != -1) {
            salary_controller.setTable_salary(column_id.getCellData(id).toString());
        }
    }

    @FXML
    public void button_add(ActionEvent actionEvent) throws Exception {
        conn = aplikacja.Data_Base_Connection.getNewConnrction();
        for (int i = 0; i< table_workers.getItems().size(); i++){
            if(i + 1 != table_workers.getItems().get(i).getId_pracownika()){
                id_to_add = i +1;
                break;
            }
            else {
                id_to_add = i+2;
            }
        }
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../fxml/new_worker.fxml"));
        Parent root1 = (Parent) loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.show();

        New_Worker_Controller new_worker_controller = loader.getController();
        new_worker_controller.setNewWorkerController(this);

        new_worker_controller.label_title.setText("Dodaj pracownika");
        new_worker_controller.plec.getItems().addAll("Mężczyzna", "Kobieta");
        new_worker_controller.stopien_naukowy.getItems().addAll("Licencjat", "Inżynier", "Magister", "Doktor", "Profesor");

    }

    @FXML
    public void button_modify(ActionEvent actionEvent) throws Exception {
        conn = aplikacja.Data_Base_Connection.getNewConnrction();

        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../fxml/new_worker.fxml"));
        Parent root1 = (Parent) loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.show();

        New_Worker_Controller new_worker_controller = loader.getController();
        new_worker_controller.setNewWorkerController(this);

        id = table_workers.getSelectionModel().getSelectedIndex();
        if(id != -1) {
            new_worker_controller.plec.getItems().addAll("Mężczyzna", "Kobieta");
            new_worker_controller.stopien_naukowy.getItems().addAll("Licencjat", "Inżynier", "Magister", "Doktor", "Profesor");

            id_rek = column_id.getCellData(id);
            id_placowki  = adminController.getAdo_pracownicy().getIdPlacowki(conn, column_id.getCellData(id).toString());
            pracownikInfo = adminController.getAdo_pracownicy().getUserData(conn, column_id.getCellData(id).toString());

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate localDate = LocalDate.parse(pracownikInfo[5].toString().substring(0, 10), formatter);

            new_worker_controller.label_title.setText("Modyfikuj pracownika");
            new_worker_controller.imie.setText(pracownikInfo[1].toString());
            new_worker_controller.nazwisko.setText(pracownikInfo[2].toString());
            if(pracownikInfo[3] != null) {
                new_worker_controller.drugie_imie.setText(pracownikInfo[3].toString());
            }
            new_worker_controller.pesel.setText(pracownikInfo[4].toString());
            new_worker_controller.data_uro.setValue(localDate);
            new_worker_controller.miasto.setText(pracownikInfo[6].toString());
            new_worker_controller.ulica.setText(pracownikInfo[7].toString());
            new_worker_controller.nr_budynku.setText(pracownikInfo[8].toString());
            new_worker_controller.nr_lokalu.setText(pracownikInfo[9].toString());
            new_worker_controller.plec.getSelectionModel().select(pracownikInfo[10].toString());
            new_worker_controller.kod_pocztowy.setText(pracownikInfo[11].toString());
            new_worker_controller.stopien_naukowy.getSelectionModel().select(pracownikInfo[12].toString());
            new_worker_controller.login.setText(pracownikInfo[13].toString());
            new_worker_controller.haslo.setText(pracownikInfo[14].toString());
            new_worker_controller.id_placowki.setText("" + id_placowki + "");
        }
    }

    @FXML
    public void button_delete(ActionEvent actionEvent) throws Exception {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../fxml/warning.fxml"));
        Parent root1 = (Parent) loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.show();

        Warning_Controller warning_controller = loader.getController();
        warning_controller.setWorkController(this);
    }

    @FXML
    public void button_ok(ActionEvent actionEvent) throws Exception {
        label_pracownicy.getScene().getWindow().hide();
    }

    public void setAdminController(Admin_Controller admin_controller){
        this.adminController = admin_controller;
    }

    public void addRec(String name, String surname, String second_name, String pesel, LocalDate birth_date, String city, String street, String house_number, String apartment, String sex, String postcode, String degree, String login, String pass, String id_fac){
        adminController.getAdo_pracownicy().addRecord(conn, id_to_add,name,surname,second_name,pesel,birth_date, city, street, house_number, apartment, sex, postcode, degree, login, pass, id_fac);
    }

    public void modifyRec(String name, String surname, String second_name, String pesel, LocalDate birth_date, String city, String street, String house_number, String apartment, String sex, String postcode, String degree, String login, String pass, String id_fac){
        adminController.getAdo_pracownicy().modifyRec(conn, id_rek,name,surname,second_name,pesel,birth_date, city, street, house_number, apartment, sex, postcode, degree, login, pass, id_fac);
    }
}
