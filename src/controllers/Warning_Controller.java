package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;


public class Warning_Controller {

    private Workers_Window_Controller workers_window_controller;

    @FXML
    Label label_title;

    @FXML
    public void button_yes(ActionEvent actionEvent) throws Exception {
        workers_window_controller.deleteSelectedRec();
        label_title.getScene().getWindow().hide();
    }

    @FXML
    public void button_no(ActionEvent actionEvent) throws Exception {
        label_title.getScene().getWindow().hide();
    }

    public void setWorkController(Workers_Window_Controller workers_window_controller){
        this.workers_window_controller = workers_window_controller;
    }
}
