package controllers;

import aplikacja.Data_Base_Connection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {

    public Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        StackPane mainStackPane = FXMLLoader.load(getClass().getResource("../fxml/sample.fxml"));
        primaryStage.setScene(new Scene(mainStackPane, 600, 400));
        primaryStage.show();
        primaryStage.setAlwaysOnTop(true);
        stage = primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
