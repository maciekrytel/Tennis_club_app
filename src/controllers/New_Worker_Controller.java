package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class New_Worker_Controller {

    private Workers_Window_Controller workers_window_controller;
    private String sex;
    private String degree;

    @FXML
    Label label_title;

    @FXML
    TextField imie;

    @FXML
    TextField nazwisko;

    @FXML
    TextField drugie_imie;

    @FXML
    TextField pesel;

    @FXML
    DatePicker data_uro;

    @FXML
    TextField miasto;

    @FXML
    TextField ulica;

    @FXML
    TextField nr_budynku;

    @FXML
    TextField nr_lokalu;

    @FXML
    ComboBox plec;

    @FXML
    TextField kod_pocztowy;

    @FXML
    ComboBox stopien_naukowy;

    @FXML
    TextField login;

    @FXML
    PasswordField haslo;

    @FXML
    TextField id_placowki;

    @FXML
    public void button_ok(ActionEvent actionEvent) throws Exception {
        if(! plec.getSelectionModel().isEmpty() && ! stopien_naukowy.getSelectionModel().isEmpty()) {

            if (plec.getSelectionModel().getSelectedItem().toString().equals("Mężczyzna")) {
                sex = "M";
            } else if (plec.getSelectionModel().getSelectedItem().toString().equals("Kobieta")) {
                sex = "K";
            }

            System.out.println(sex);

            if (stopien_naukowy.getSelectionModel().getSelectedItem().toString().equals("Licencjat")) {
                degree = "L";
            } else if (stopien_naukowy.getSelectionModel().getSelectedItem().toString().equals("Inżynier")) {
                degree = "I";
            } else if (stopien_naukowy.getSelectionModel().getSelectedItem().toString().equals("Magister")) {
                degree = "M";
            } else if (stopien_naukowy.getSelectionModel().getSelectedItem().toString().equals("Doktor")) {
                degree = "D";
            } else if (stopien_naukowy.getSelectionModel().getSelectedItem().toString().equals("Profesor")) {
                degree = "P";
            }
        }

        if(label_title.getText().equals("Dodaj pracownika")) {
            workers_window_controller.addRec(imie.getText(), nazwisko.getText(), drugie_imie.getText(), pesel.getText(), data_uro.getValue(), miasto.getText(), ulica.getText(), nr_budynku.getText(), nr_lokalu.getText(), sex, kod_pocztowy.getText(), degree, login.getText(), haslo.getText(), id_placowki.getText());
        }
        else if(label_title.getText().equals("Modyfikuj pracownika")){
            workers_window_controller.modifyRec(imie.getText(), nazwisko.getText(), drugie_imie.getText(), pesel.getText(), data_uro.getValue(), miasto.getText(), ulica.getText(), nr_budynku.getText(), nr_lokalu.getText(), sex, kod_pocztowy.getText(), degree, login.getText(), haslo.getText(), id_placowki.getText());
        }
        label_title.getScene().getWindow().hide();
    }

    @FXML
    public void button_cancel(ActionEvent actionEvent) throws Exception {
        label_title.getScene().getWindow().hide();
    }

    public void setNewWorkerController(Workers_Window_Controller workerController){
        this.workers_window_controller = workerController;
    }

}
