package controllers;

import aplikacja.ADO_Pracownicy;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.sql.Connection;


public class Admin_Controller {

    private Login_Controller login_controller;
    private Workers_Window_Controller workers_window_controller;
    private Connection conn;
    private Object[] dane;
    private String[] columns;
    private ADO_Pracownicy ado_pracownicy;

    @FXML
    Label label_title;

    @FXML
    ListView list_data;

    public void setListView() {
        conn = aplikacja.Data_Base_Connection.getNewConnrction();
        dane = login_controller.ado_pracownicy.getUserData(conn, login_controller.getId_n());
        columns = login_controller.ado_pracownicy.getColumnName(conn);
        for (int i = 0; i< dane.length - 1; i++){
            if(dane[i] != null) {
                list_data.getItems().add(columns[i] + ": " + dane[i].toString());
            }
            else{
                list_data.getItems().add(columns[i] + ": " + " - ");
            }
        }
    }

    @FXML
    public void button_workers(ActionEvent actionEvent) throws Exception{
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../fxml/workers.fxml"));
        Parent root1 = (Parent) loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.show();

        Workers_Window_Controller workers_window_controller = loader.getController();
        workers_window_controller.setAdminController(this);

        workers_window_controller.setTable_workers(login_controller.ado_pracownicy);
    }

    @FXML
    public void button_logout(ActionEvent actionEvent) throws Exception{
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../fxml/login_window.fxml"));
        Parent root1 = (Parent) loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.show();

        label_title.getScene().getWindow().hide();

        Login_Controller login_controller = loader.getController();
        login_controller.setAdmController(this);
    }

    public void setAdminController(Login_Controller login_controller){
        this.login_controller = login_controller;
    }

    public ADO_Pracownicy getAdo_pracownicy(){
        return login_controller.ado_pracownicy;
    }
}
