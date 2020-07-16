package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

import java.sql.Connection;

public class Password_Change_Controller {

    private Menu_Controller menu_controller;
    private Error_Controller error_controller;

    @FXML
    Label label_title;

    @FXML
    PasswordField current_password;

    @FXML
    PasswordField new_password;

    @FXML
    PasswordField confirm_new_password;

    private Connection conn;
    private boolean info;

    @FXML
    public void button_confirm(ActionEvent actionEvent) throws Exception{
        conn = aplikacja.Data_Base_Connection.getNewConnrction();
        info = menu_controller.setNewPass(conn, current_password.getText(), new_password.getText(), confirm_new_password.getText());
        if(info == true){
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../fxml/error.fxml"));
            Parent root1 = (Parent) loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();

            Error_Controller error_controller = loader.getController();
            error_controller.setPassword_change_controller(this);

            error_controller.label.setText("Hasło zmienione!");
            label_title.getScene().getWindow().hide();
        }
        else {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../fxml/error.fxml"));
            Parent root1 = (Parent) loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();

            Error_Controller error_controller = loader.getController();
            error_controller.setPassword_change_controller(this);

            error_controller.label.setText("Błąd");
        }
    }

    @FXML
    public void button_cancel(ActionEvent actionEvent) throws Exception{
        label_title.getScene().getWindow().hide();
    }

    public void setPassword_Change_Controller(Menu_Controller menu_controller){
        this.menu_controller = menu_controller;
    }

}
