package controllers;

import aplikacja.ADO_Wynagrodzenia;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;



import java.sql.Connection;

public class Salary_Controller {

    private Menu_Controller menu_controller;
    private Workers_Window_Controller workers_window_controller;
    private ADO_Wynagrodzenia ado_wynagrodzenia;
    private Connection conn;
    private ObservableList<ADO_Wynagrodzenia> wynagrodzenia = FXCollections.observableArrayList();

    @FXML
    private TableView<ADO_Wynagrodzenia> table_salary;

    @FXML
    private TableColumn<ADO_Wynagrodzenia, Integer> column_bonus;

    @FXML
    private TableColumn<ADO_Wynagrodzenia, Integer>  column_salary;

    @FXML
    private Label label_title;

    public void setTable_salary(String id){
        this.ado_wynagrodzenia = new ADO_Wynagrodzenia();
        conn = aplikacja.Data_Base_Connection.getNewConnrction();
        wynagrodzenia = ado_wynagrodzenia.getSalary(conn, id);

        column_salary.setCellValueFactory(new PropertyValueFactory<>("placa_brutto"));
        column_bonus.setCellValueFactory(new PropertyValueFactory<>("premia"));

        table_salary.setItems(wynagrodzenia);
    }

    @FXML
    public void button_ok(ActionEvent actionEvent) throws Exception {
        label_title.getScene().getWindow().hide();
    }

    public void setSalaryController(Menu_Controller menu_controller){
        this.menu_controller = menu_controller;
    }

    public void setSalController(Workers_Window_Controller workers_window_controller) { this.workers_window_controller = workers_window_controller ;}
}
