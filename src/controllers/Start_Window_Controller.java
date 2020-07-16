package controllers;

import aplikacja.ADO_Pracownicy;
import aplikacja.Data_Base_Connection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.sql.Connection;


public class Start_Window_Controller {

    private Main_Controller main_controller;

    @FXML
    private Label label_title;

    private Connection conn;
    private ObservableList<ADO_Pracownicy> pracownicy = FXCollections.observableArrayList();

    @FXML
    public void button_start(ActionEvent actionEvent) throws Exception {
        label_title.getScene().getWindow().hide();

        conn = aplikacja.Data_Base_Connection.getConnection();

        pracownicy = new ADO_Pracownicy().getAll(conn);

        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../fxml/login_window.fxml"));
        Parent root1 = (Parent) loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.show();

        Login_Controller login_controller = loader.getController();
        login_controller.setController(this);
    }

    public void setMain_Controller(Main_Controller main_controller) {
        this.main_controller = main_controller;
    }

}
