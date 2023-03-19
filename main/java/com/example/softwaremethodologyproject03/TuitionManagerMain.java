package com.example.softwaremethodologyproject03;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class TuitionManagerMain extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(TuitionManagerMain.class.getResource("TuitionManagerView.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 900, 800);
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            stage.setTitle("Software Methodology Project03");
            stage.setScene(scene);
            stage.show();
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }

    }

    public static void main(String[] args) {
        launch();
    }
}