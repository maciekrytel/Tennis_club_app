package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;


public class Error_Controller {

    private Login_Controller login_controller;
    private Password_Change_Controller password_change_controller;

    @FXML
    public Label label;

    @FXML
    public void button_ok(ActionEvent actionEvent) throws Exception{
        label.getScene().getWindow().hide();
    }

    public void setPassword_change_controller(Password_Change_Controller password_change_controller) {
        this.password_change_controller = password_change_controller;
    }

    public void setLogin_controller(Login_Controller login_controller) {
        this.login_controller = login_controller;
    }
}
