package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import java.sql.Connection;

public class Main_Controller {

    @FXML
    public StackPane mainStackPane;


    @FXML
    public void initialize() throws Exception{
        loadStartWindow();
    }

    public void loadStartWindow() throws Exception{
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../fxml/start.fxml"));
        AnchorPane anchorpane = loader.load();

        Start_Window_Controller start_window_controller = loader.getController();
        start_window_controller.setMain_Controller(this);
        setScreen(anchorpane);
    }


    public void setScreen(AnchorPane anchorpane) {
        this.mainStackPane.getChildren().clear();
        this.mainStackPane.getChildren().add(anchorpane);
    }
}
