package controllers;

import aplikacja.ADO_Wynagrodzenia;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.sql.Connection;


public class Menu_Controller{

    private String id_number;

    private Login_Controller login_controller;
    private Salary_Controller salary_controller;
    private Password_Change_Controller password_change_controller;

    @FXML
    private Label label_menu;

    @FXML
    private Label label_username;

    @FXML
    public ListView list_data;

    private Connection conn;
    public String id_num;
    private Object[] rekord;
    private String[] columns;

    public void setListView() {
        conn = aplikacja.Data_Base_Connection.getNewConnrction();
        rekord = login_controller.ado_pracownicy.getUserData(conn, login_controller.getId_n());
        columns = login_controller.ado_pracownicy.getColumnName(conn);
        label_username.setText(rekord[1].toString() + " " + rekord[2].toString());
        for (int i = 0; i< rekord.length - 1; i++){
            if(rekord[i] != null) {
                list_data.getItems().add(columns[i] + ": " + rekord[i].toString());
            }
            else{
                list_data.getItems().add(columns[i] + ": " + " - ");
            }
        }
    }

    @FXML
    public void button_pass_change(ActionEvent actionEvent) throws Exception{
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../fxml/password_change_window.fxml"));
        Parent root1 = (Parent) loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.show();

        Password_Change_Controller password_change_controller = loader.getController();
        password_change_controller.setPassword_Change_Controller(this);
    }

    @FXML
    public void button_salary_history(ActionEvent actionEvent) throws Exception{
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../fxml/salary.fxml"));
        Parent root1 = (Parent) loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.show();

        Salary_Controller salary_controller = loader.getController();
        salary_controller.setSalaryController(this);

        salary_controller.setTable_salary(login_controller.getId_n());
    }


    @FXML
    public void button_logout(ActionEvent actionEvent) throws Exception{
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../fxml/login_window.fxml"));
        Parent root1 = (Parent) loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.show();

        label_menu.getScene().getWindow().hide();

        Login_Controller login_controller = loader.getController();
        login_controller.setLogController(this);
    }


    public void setMenuController(Login_Controller login_controller){
        this.login_controller = login_controller;
    }

    public boolean setNewPass(Connection conn, String oldpass, String newpass, String confirmpass){
        System.out.println("obecne " + oldpass);
        return login_controller.ado_pracownicy.setPassword(conn, login_controller.getId_n(), oldpass, newpass, confirmpass);
    }
}
