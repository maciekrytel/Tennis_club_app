package controllers;

import aplikacja.ADO_Pracownicy;
import aplikacja.ADO_Stanowiska;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;

public class Login_Controller {


    public Stage sstage;
    public ADO_Pracownicy ado_pracownicy;
    public ADO_Stanowiska ado_stanowiska;
    private String nazwa;
    private Start_Window_Controller start_window_controller;

    private Menu_Controller menu_controller;
    private Error_Controller error_controller;
    private Admin_Controller adminController;

    @FXML
    public TextField login;

    @FXML
    public PasswordField password;

    private Connection conn;
    private String log;
    private String pass;
    private Object[] checking;
    private ObservableList<ADO_Pracownicy> pracownicy;
    private String id;
    private String id_n;

    @FXML
    public void button_login (ActionEvent actionEvent) throws Exception{
        this.ado_pracownicy = new ADO_Pracownicy();
        this.ado_stanowiska = new ADO_Stanowiska();
        log = login.getText();
        pass = password.getText();
        conn = aplikacja.Data_Base_Connection.getNewConnrction();
        checking = ado_pracownicy.getLogAndPass(conn, log, pass);
        if (checking[0] == "1"){
            nazwa = ado_stanowiska.getNazwaStanowiska(conn, checking[1].toString());
            if(nazwa.equals("administrator")){
                loadAdminScreen();
            }
            else {
                loadMenuScreen();
            }
        }
        else{
            loadErrorScreen();
        }
    }

    public void loadMenuScreen() throws Exception{
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../fxml/menu.fxml"));
        Parent root1 = (Parent) loader.load();

        Menu_Controller menu_controller = loader.getController();
        menu_controller.setMenuController(this);

        Stage stage = new Stage();
        stage.setTitle(checking[1].toString() + " " + checking[2].toString() + " " + checking[3].toString());
        stage.setScene(new Scene(root1));
        sstage = stage;
        stage.show();

        this.menu_controller = new Menu_Controller();
        menu_controller.setListView();

        login.getScene().getWindow().hide();
    }

    public void loadErrorScreen() throws Exception{
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../fxml/error.fxml"));
        Parent root1 = (Parent) loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        sstage = stage;
        stage.show();

        Error_Controller error_controller = loader.getController();
        error_controller.setLogin_controller(this);

        error_controller.label.setText("Błąd loggowania");
    }

    public void loadAdminScreen() throws Exception {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../fxml/admin_menu.fxml"));
        Parent root1 = (Parent) loader.load();

        Admin_Controller admin_controller = loader.getController();
        admin_controller.setAdminController(this);

        Stage stage = new Stage();
        stage.setTitle(checking[1].toString() + " " + checking[2].toString() + " " + checking[3].toString());
        stage.setScene(new Scene(root1));
        sstage = stage;
        stage.show();

        this.adminController = new Admin_Controller();
        admin_controller.setListView();

        login.getScene().getWindow().hide();
    }


    public void setController(Start_Window_Controller start_window_controller){
        this.start_window_controller = start_window_controller;
    }

    public void setLogController(Menu_Controller menu_controller){
        this.menu_controller = menu_controller;
    }

    public void setAdmController(Admin_Controller admController){
        this.adminController = admController;
    }

    public String getId_n() {
        return this.ado_pracownicy.getIdNumber();
    }

}
